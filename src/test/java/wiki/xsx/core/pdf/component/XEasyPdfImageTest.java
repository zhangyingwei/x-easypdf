package wiki.xsx.core.pdf.component;

import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
public class XEasyPdfImageTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
    private static final String OUTPUT_PATH = "C:\\Users\\xsx\\Desktop\\pdf\\test\\component\\image\\";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void testImage() throws IOException {
        String filePath = OUTPUT_PATH + "testImage.pdf";
        String imagePath = "D:\\temp\\0020033143720852_b.jpg";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Image.build(new File(imagePath)).setMarginLeft(50F)
                ).setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("My Header"))
                )
        ).setFontPath(FONT_PATH).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("this is my footer"))
        ).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testImage2() throws IOException {
        String filePath = OUTPUT_PATH + "testImage2.pdf";
        String imagePath = "D:\\temp\\0020033143720852_b.jpg";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Image.build(new File(imagePath)).setMarginLeft(150F),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setScaleMode(XEasyPdfImage.ScaleMode.FAST)
                                .setWidth(100F)
                                .setHeight(100F)
                                .setStyle(XEasyPdfImageStyle.LEFT),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setScaleMode(XEasyPdfImage.ScaleMode.SMOOTH)
                                .setWidth(100F)
                                .setHeight(100F)
                                .setStyle(XEasyPdfImageStyle.CENTER),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setScaleMode(XEasyPdfImage.ScaleMode.FAST)
                                .setWidth(100F)
                                .setHeight(100F)
                                .setStyle(XEasyPdfImageStyle.RIGHT),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setScaleMode(XEasyPdfImage.ScaleMode.DEFAULT)
                                .setWidth(300F)
                                .setHeight(300F)
                ).setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("My Header"))
                )
        ).setFontPath(FONT_PATH).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("this is my footer"))
        ).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testImage3() throws IOException {
        String sourcePath = OUTPUT_PATH + "testImage2.pdf";
        String filePath = OUTPUT_PATH + "testImage3.pdf";
        String imagePath = "D:\\temp\\0020033143720852_b.jpg";
        XEasyPdfDocument document = XEasyPdfHandler.Document.build(sourcePath).setFontPath(FONT_PATH);
        List<XEasyPdfPage> pageList = document.getPageList();
        XEasyPdfPage xEasyPdfPage = pageList.get(pageList.size() - 1);
        xEasyPdfPage.addComponent(
                XEasyPdfHandler.Image.build(new File(imagePath))
                        .setHeight(50)
                        .setWidth(50)
                        .setPosition(20F, 50F)
        );
        document.save(filePath);
        System.out.println("finish");
    }
}
