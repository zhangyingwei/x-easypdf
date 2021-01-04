package wiki.xsx.core.pdf.component;

import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author xsx
 * @date 2020/6/12
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
public class XEasyPdfHeaderTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
    private static final String OUTPUT_PATH = "C:\\Users\\xsx\\Desktop\\pdf\\test\\component\\header\\";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void testGlobalHeader() throws IOException {
        String filePath = OUTPUT_PATH + "testGlobalHeader.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH).setGlobalHeader(
                XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("Hello World"))
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testPageHeader() throws IOException {
        String filePath = OUTPUT_PATH + "testPageHeader.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build().setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("Hello XSX"))
                )
        ).setFontPath(FONT_PATH).setGlobalHeader(
                XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("Hello World"))
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testPageHeader2() throws IOException {
        String filePath = OUTPUT_PATH + "testPageHeader2.pdf";
        String imagePath = "D:\\temp\\header2.png";
        Color lineColor = new Color(0, 191, 255);
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build().setHeader(
                        XEasyPdfHandler.Header.build(
                                XEasyPdfHandler.Image.build(new File(imagePath)).disableSelfAdaption().setHeight(100F),
                                XEasyPdfHandler.Text.build(
                                        "Hello XSX!!!Hello XSX!!!Hello XSX!!!Hello XSX!!!"
                                ).setStyle(XEasyPdfTextStyle.CENTER).setFontSize(30F)
                        ).addSplitLine(
                                XEasyPdfHandler.SplitLine.SolidLine.build().setColor(lineColor).setMarginTop(5F),
                                XEasyPdfHandler.SplitLine.DottedLine.build().setMarginTop(5F),
                                XEasyPdfHandler.SplitLine.SolidLine.build().setColor(lineColor).setMarginTop(5F)
                        )
                ).addComponent(
                        XEasyPdfHandler.Text.build(
                            Arrays.asList(
                                "1.这是第一行。。。。",
                                "2.这是第二行。。。。",
                                "3.这是第三行。。。。",
                                "4.这是第四行。。。。",
                                "5.这是第五行。。。。"
                            )
                        ).setStyle(XEasyPdfTextStyle.CENTER).setFontSize(16F)
                )
        ).setFontPath(FONT_PATH).setGlobalHeader(
                XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("Hello World"))
        ).save(filePath).close();
        System.out.println("finish");
    }
}
