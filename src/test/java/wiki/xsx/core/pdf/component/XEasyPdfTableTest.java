package wiki.xsx.core.pdf.component;

import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.table.XEasyPdfCell;
import wiki.xsx.core.pdf.component.table.XEasyPdfRow;
import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;
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
    public void testTable1() throws IOException {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testTable1.pdf";
        List<XEasyPdfRow> rowList = new ArrayList<>(50);
        List<XEasyPdfCell> cellList;
        for (int i = 0; i < 100; i++) {
            cellList = new ArrayList<>(5);
            for (int j = 0; j < 5; j++) {
                cellList.add(
                        i%2==0?
                        XEasyPdfHandler.Table.Row.Cell.build(100F, 90F).addContent(
                                XEasyPdfHandler.Text.build("row"+i+"-cell"+j+"中文中文中文中文中文中文中文中文中文中文中文中文")
                        ).setFontPath(FONT_PATH):
                        XEasyPdfHandler.Table.Row.Cell.build(100F, 90F).addContent(
                                XEasyPdfHandler.Text.build("row"+i+"-cell"+j+"中文中文中文中文中文中文中文中文中文中文中文中文")
                        ).setBackgroundColor(new Color(0,191,255))
                );
            }
            rowList.add(XEasyPdfHandler.Table.Row.build(cellList));
        }

        XEasyPdfHandler.Document.build().setGlobalHeader(
                XEasyPdfHandler.Header.build(
                        XEasyPdfHandler.Text.build(
                                "页眉第"+XEasyPdfHandler.Page.getCurrentPagePlaceholder()+"页，共13页"
                        ).setFontSize(20F).setStyle(XEasyPdfTextStyle.CENTER)
                )
        ).addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(rowList).setStyle(XEasyPdfTableStyle.CENTER).setMarginLeft(50F).setMarginBottom(50F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("finish，耗时：" + (end-begin) + " ms");
    }

    @Test
    public void testTable2() throws IOException {
        String filePath = OUTPUT_PATH + "testTable2.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("1-1")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("1-2")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("1-3")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("1-4")
                                        )
                                ).setStyle(XEasyPdfTableStyle.LEFT),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("2-1")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("2-2")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("2-3")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("2-4")
                                        )
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("3-1")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("3-2")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("3-3")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,15F).addContent(
                                                XEasyPdfHandler.Text.build("3-4")
                                        ).setBorderWidth(2F)
                                ).setStyle(XEasyPdfTableStyle.RIGHT)
                        ).setMarginLeft(100F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testTable3() throws IOException {
        String imagePath = "D:\\temp\\qrcode_for_gh_cefdd88ec44c_430.jpg";
        String filePath = OUTPUT_PATH + "testTable3.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(199F,30F).addContent(
                                                XEasyPdfHandler.Image.build(new File(imagePath)).setWidth(28F).setHeight(28F)
                                        ).setMarginLeft(99F)
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,30F).addContent(
                                                XEasyPdfHandler.Text.build("2-1")
                                        ).setMarginLeft(99F),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,30F).addContent(
                                                XEasyPdfHandler.Text.build("2-2")
                                        )
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,88F).addContent(
                                                XEasyPdfHandler.Text.build("3-1").setMarginTop(-30F)
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,30F).addContent(
                                                XEasyPdfHandler.Text.build("3-2")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,30F).addContent(
                                                XEasyPdfHandler.Text.build("3-3")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,88F).addContent(
                                                XEasyPdfHandler.Text.build("3-4").setMarginTop(-30F)
                                        )
                                ).setHeight(30F).setStyle(XEasyPdfTableStyle.CENTER)
                        ).setMarginLeft(100F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testTable4() throws IOException {
        String filePath = OUTPUT_PATH + "testTable4.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("1")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("2")
                                        ).setMarginLeft(100F)
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("3")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("4")
                                        ).setMarginLeft(100F)
                                ).setMarginTop(100F).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("5")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("6")
                                        ).setMarginLeft(100F)
                                ).setMarginTop(100F).setStyle(XEasyPdfTableStyle.CENTER)
                        ).setMarginLeft(150F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testTable5() throws IOException {
        String filePath = OUTPUT_PATH + "testTable5.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("1")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("2")
                                        ).setMarginLeft(100F)
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(101F,100F).addContent(
                                                XEasyPdfHandler.Text.build("5")
                                        ).setMarginLeft(99F)
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("3")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("4")
                                        ).setMarginLeft(100F)
                                ).setStyle(XEasyPdfTableStyle.CENTER)
                        ).setMarginLeft(150F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testTable6() throws IOException {
        String filePath = OUTPUT_PATH + "testTable6.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Table.build(
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("1")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("2")
                                        ).setMarginLeft(50F)
                                ).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(101F,100F).addContent(
                                                XEasyPdfHandler.Text.build("3")
                                        ).setMarginLeft(74F)
                                ).setMarginTop(-50F).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("4")
                                        ),
                                        XEasyPdfHandler.Table.Row.Cell.build(100F,100F).addContent(
                                                XEasyPdfHandler.Text.build("5")
                                        ).setMarginLeft(50F)
                                ).setMarginTop(-50F).setStyle(XEasyPdfTableStyle.CENTER),
                                XEasyPdfHandler.Table.Row.build(
                                        XEasyPdfHandler.Table.Row.Cell.build(101F,100F).addContent(
                                                XEasyPdfHandler.Text.build("6")
                                        ).setMarginLeft(74F)
                                ).setMarginTop(-50F).setStyle(XEasyPdfTableStyle.CENTER)
                        ).setMarginLeft(150F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }


}
