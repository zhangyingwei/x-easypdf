package wiki.xsx.core.pdf.component;

import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.component.table.simple.XEasyPdfSimpleRow;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xsx
 * @date 2021/5/11
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
public class XEasyPdfSimpleTableTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
    private static final String OUTPUT_PATH = "C:\\Users\\xsx\\Desktop\\pdf\\test\\component\\table\\simple\\";
    private static final String IMAGE_PATH = "C:\\Users\\Administrator\\Desktop\\testImage3.jpg";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void testTable1() throws IOException {
        String filePath = OUTPUT_PATH + "testTable1.pdf";
        List<XEasyPdfSimpleRow> rowList = new ArrayList<>(3);
        for (int i = 1; i <= 100; i++) {
            rowList.add(
                    XEasyPdfHandler.SimpleTable.Row.build(
                            XEasyPdfHandler.SimpleTable.Row.Cell.build(100F).addContent(
                                    XEasyPdfHandler.Text.build("row"+i+"-cell1"),
                                    XEasyPdfHandler.Text.build("hello"),
                                    XEasyPdfHandler.Text.build("world")
                            ),
                            XEasyPdfHandler.SimpleTable.Row.Cell.build(100F).addContent(
                                    XEasyPdfHandler.Text.build("row"+i+"-cell2"),
                                    XEasyPdfHandler.Text.build("hello"),
                                    XEasyPdfHandler.Text.build("world"),
                                    XEasyPdfHandler.Text.build("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                            ),
                            XEasyPdfHandler.SimpleTable.Row.Cell.build(100F).addContent(
                                    XEasyPdfHandler.Text.build("row"+i+"-cell3"),
                                    XEasyPdfHandler.Text.build("hello"),
                                    XEasyPdfHandler.Text.build("world")
                            )
                    )
            );
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.SimpleTable.build(rowList).setStyle(XEasyPdfTableStyle.CENTER).setMarginLeft(50F).setMarginBottom(50F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testTable2() throws IOException {
        String filePath = OUTPUT_PATH + "testTable2.pdf";
        List<XEasyPdfSimpleRow> rowList = new ArrayList<>(3);
        File image = new File(IMAGE_PATH);
        for (int i = 1; i <= 100; i++) {
            rowList.add(
                    XEasyPdfHandler.SimpleTable.Row.build(
                            XEasyPdfHandler.SimpleTable.Row.Cell.build(150F).addContent(
                                    XEasyPdfHandler.Text.build("row"+i+"-cell1"),
                                    XEasyPdfHandler.Text.build("hello"),
                                    XEasyPdfHandler.Text.build("world")
                            ),
                            XEasyPdfHandler.SimpleTable.Row.Cell.build(150F).addContent(
                                    XEasyPdfHandler.Text.build("row"+i+"-cell1"),
                                    XEasyPdfHandler.Text.build("hello"),
                                    XEasyPdfHandler.Text.build("world"),
                                    XEasyPdfHandler.Image.build(image, 100, 100)
                            ),
                            XEasyPdfHandler.SimpleTable.Row.Cell.build(150F).addContent(
                                    XEasyPdfHandler.Image.build(image, 100, 100).setMarginTop(20F)
                            )
                    )
            );
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.SimpleTable.build(rowList).setStyle(XEasyPdfTableStyle.CENTER).setMarginLeft(50F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }
}
