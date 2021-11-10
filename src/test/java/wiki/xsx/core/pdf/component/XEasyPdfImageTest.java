package wiki.xsx.core.pdf.component;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageStyle;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        String imagePath = "C:\\Users\\Administrator\\Desktop\\testImage9.jpg";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Image.build(new File(imagePath)).setMarginLeft(50F)
                ).setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("My Header"))
                )
        ).setFontPath(FONT_PATH).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("this is my footer"))
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testImage2() throws IOException {
        String filePath = OUTPUT_PATH + "testImage2.pdf";
        String imagePath = "C:\\Users\\Administrator\\Desktop\\testImage9.jpg";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Image.build(new File(imagePath)).setMarginLeft(150F),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setWidth(100F)
                                .setHeight(100F)
                                .setStyle(XEasyPdfImageStyle.LEFT),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setWidth(100F)
                                .setHeight(100F)
                                .setStyle(XEasyPdfImageStyle.CENTER),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setWidth(100F)
                                .setHeight(100F)
                                .setStyle(XEasyPdfImageStyle.RIGHT),
                        XEasyPdfHandler.Image.build(new File(imagePath))
                                .setWidth(300F)
                                .setHeight(300F)
                ).setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("My Header"))
                )
        ).setFontPath(FONT_PATH).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("this is my footer"))
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testImage3() throws IOException {
        String sourcePath = OUTPUT_PATH + "testImage2.pdf";
        String filePath = OUTPUT_PATH + "testImage3.pdf";
        String imagePath = "C:\\Users\\Administrator\\Desktop\\testImage9.jpg";
        XEasyPdfDocument document = XEasyPdfHandler.Document.load(sourcePath);
        List<XEasyPdfPage> pageList = document.getPageList();
        XEasyPdfPage xEasyPdfPage = pageList.get(pageList.size() - 1);
        xEasyPdfPage.addComponent(
                XEasyPdfHandler.Image.build(new File(imagePath))
                        .setHeight(150)
                        .setWidth(150)
                        .setPosition(20F, 200F)
        );
        document.save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testImage4() throws IOException {
        String filePath = OUTPUT_PATH + "testImage4.pdf";
        try (InputStream inputStream = new URL("https://images.gitee.com/uploads/images/2021/0527/104627_bc14225e_668748.png").openStream()) {
            XEasyPdfHandler.Document.build().addPage(
                    XEasyPdfHandler.Page.build(
                            PDRectangle.A4,
                            XEasyPdfHandler.Image.build(inputStream, XEasyPdfImageType.PNG).setRadians(-45D)
                    )
            ).save(filePath).close();
        }
        System.out.println("finish");
    }
}
