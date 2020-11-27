package wiki.xsx.core.pdf.component;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.table.XEasyPdfCell;
import wiki.xsx.core.pdf.component.table.XEasyPdfRow;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;
import wiki.xsx.core.pdf.util.XEasyPdfUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xsx
 * @date 2020/6/15
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class XEasyPdfTableTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
    private static final String OUTPUT_PATH = "C:\\Users\\xsx\\Desktop\\pdf\\test\\component\\table\\";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void testTable() throws IOException {
        String filePath = OUTPUT_PATH + "testTable.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build("row1-cell1").setWidth(100F),
                                        XEasyPdfHandler.Table.Row.Cell.build("row1-cell2").setWidth(100F),
                                        XEasyPdfHandler.Table.Row.Cell.build("row1-cell3").setWidth(100F),
                                        XEasyPdfHandler.Table.Row.Cell.build("row1-cell4").setWidth(100F)
                                ),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build("row2-cell1").setWidth(100F).setStyle(XEasyPdfTextStyle.LEFT),
                                        XEasyPdfHandler.Table.Row.Cell.build("row2-cell2").setWidth(100F).setStyle(XEasyPdfTextStyle.LEFT),
                                        XEasyPdfHandler.Table.Row.Cell.build("row2-cell3").setWidth(100F).setStyle(XEasyPdfTextStyle.LEFT),
                                        XEasyPdfHandler.Table.Row.Cell.build("row2-cell4").setWidth(100F).setStyle(XEasyPdfTextStyle.LEFT)
                                ),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build("row3-cell1").setWidth(100F).setStyle(XEasyPdfTextStyle.RIGHT),
                                        XEasyPdfHandler.Table.Row.Cell.build("row3-cell2").setWidth(100F).setStyle(XEasyPdfTextStyle.RIGHT),
                                        XEasyPdfHandler.Table.Row.Cell.build("row3-cell3").setWidth(100F).setStyle(XEasyPdfTextStyle.RIGHT),
                                        XEasyPdfHandler.Table.Row.Cell.build("row3-cell4").setWidth(100F).setStyle(XEasyPdfTextStyle.RIGHT)
                                )
                        ).setStyle(XEasyPdfTextStyle.CENTER)
                )
        ).setFontPath(FONT_PATH).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testTable2() throws IOException {
        String filePath = OUTPUT_PATH + "testTable2.pdf";
        List<XEasyPdfRow> rowList = new ArrayList<>(50);
        List<XEasyPdfCell> cellList;
        for (int i = 0; i < 100; i++) {
            cellList = new ArrayList<>(5);
            for (int j = 0; j < 5; j++) {
                cellList.add(
                        XEasyPdfHandler.Table.Row.Cell.build("row"+i+"-cell"+j).setWidth(100F)
                );
            }
            rowList.add(
                    XEasyPdfHandler.Table.Row.build(cellList)
            );
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(rowList).setStyle(XEasyPdfTextStyle.CENTER).setMarginLeft(50F)
                )
        ).setFontPath(FONT_PATH).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testTable3() throws IOException {
        String filePath = OUTPUT_PATH + "testTable3.pdf";
        try {
            XEasyPdfUtil.create(
                    filePath,
                    XEasyPdfHandler.Page.build(
                            new PDRectangle(480F,160F),
                            XEasyPdfHandler.Table.build(
                                    XEasyPdfHandler.Table.Row.build(
                                            XEasyPdfHandler.Table.Row.Cell.build("环境与市政工程学院").setHasBorder(false).setWidth(200F),
                                            XEasyPdfHandler.Table.Row.Cell.build("环境工程").setHasBorder(false).setWidth(200F)
                                    ),
                                    XEasyPdfHandler.Table.Row.build(
                                            XEasyPdfHandler.Table.Row.Cell.build("1606020426").setHasBorder(false).setWidth(200F),
                                            XEasyPdfHandler.Table.Row.Cell.build("曾慧敏").setHasBorder(false).setWidth(200F)
                                    )
                            ).setMarginLeft(100F),
                            XEasyPdfHandler.Text.build(16F, "天津市诚铭人力资源服务有限公司").setStyle(XEasyPdfTextStyle.CENTER).setMarginTop(20F),
                            XEasyPdfHandler.Text.build(14F, "D017001").setStyle(XEasyPdfTextStyle.RIGHT).setMarginRight(100F)
                    ).setFontPath(FONT_PATH)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
