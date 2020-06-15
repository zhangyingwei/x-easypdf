package wiki.xsx.core.pdf.doc;

import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 * @author xsx
 * @date 2020/6/8
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 * </p>
 */
public class XEasyPdfDocumentTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
    private static final String OUTPUT_PATH = "C:\\Users\\xsx\\Desktop\\pdf\\test\\doc\\";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void testAddPage() throws IOException {
        String filePath = OUTPUT_PATH + "testAddPage.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("Hello World").setStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build("你好，世界！"),
                        XEasyPdfHandler.Text.build("我是第一页")
                ),
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("你好，世界！").setStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build("我是第二页").setStyle(XEasyPdfTextStyle.RIGHT)
                )
        ).setFontPath(FONT_PATH).setBackgroundColor(Color.YELLOW).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testSave() throws IOException {
        String filePath = OUTPUT_PATH + "doc1.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("Hello World").setStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build("你好，世界！")
                ).setFontPath(FONT_PATH)
        ).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testSetInfo() throws IOException {
        String filePath = OUTPUT_PATH + "info.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH).setInfo()
            .setTitle("test info")
            .setAuthor("xsx")
            .setSubject("info")
            .setCreator("my-creator")
            .setKeywords("pdf,xsx")
            .setCreateTime(Calendar.getInstance())
            .setUpdateTime(Calendar.getInstance())
            .finish()
            .save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testSetPermission() throws IOException {
        String filePath = OUTPUT_PATH + "permission.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH).setPermission()
                .setCanPrintDegraded(false)
                .setCanPrint(false)
                .setCanAssembleDocument(false)
                .setCanExtractContent(false)
                .setCanExtractForAccessibility(false)
                .setCanFillInForm(false)
                .setCanModify(false)
                .setCanModifyAnnotations(false)
                .finish()
                .save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testSetBackgroundColor() throws IOException {
        String filePath = OUTPUT_PATH + "backgroundColor.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(),
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH)
                .setBackgroundColor(Color.YELLOW)
                .save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testInsertPage() throws IOException {
        String sourcePath = OUTPUT_PATH + "backgroundColor.pdf";
        String filePath = OUTPUT_PATH + "insertPage.pdf";
        XEasyPdfHandler.Document.build(sourcePath).insertPage(
                -100,
                XEasyPdfHandler.Page.build(XEasyPdfHandler.Text.build("插入首页"))
        ).insertPage(
                100,
                XEasyPdfHandler.Page.build(XEasyPdfHandler.Text.build("插入尾页"))
        ).setFontPath(FONT_PATH)
                .setBackgroundColor(Color.BLUE)
                .save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testMerge() throws IOException {
        String sourcePath = OUTPUT_PATH + "backgroundColor.pdf";
        String mergePath1 = OUTPUT_PATH + "insertPage.pdf";
        String mergePath2 = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH + "merge.pdf";
        XEasyPdfHandler.Document.build(sourcePath).merge(
                XEasyPdfHandler.Document.build(mergePath1),
                XEasyPdfHandler.Document.build(mergePath2)
        ).setFontPath(FONT_PATH).save(filePath);
        System.out.println("finish");
    }

    @Test
    public void testImage1() throws IOException {
        String sourcePath = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH;
        XEasyPdfHandler.Document.build(sourcePath).image(filePath, "png").close();
        System.out.println("finish");
    }

    @Test
    public void testImage2() throws IOException {
        String sourcePath = OUTPUT_PATH + "insertPage.pdf";
        String filePath = OUTPUT_PATH;
        String prefix = "x-easypdf";
        XEasyPdfHandler.Document.build(sourcePath).image(filePath, "jpg", prefix).close();
        System.out.println("finish");
    }

    @Test
    public void testImage3() throws IOException {
        String sourcePath = OUTPUT_PATH + "merge.pdf";
        String filePath1 = OUTPUT_PATH + "merge0.jpg";
        String filePath2 = OUTPUT_PATH + "merge6.jpg";
        try(
                OutputStream outputStream1 = Files.newOutputStream(Paths.get(filePath1));
                OutputStream outputStream2 = Files.newOutputStream(Paths.get(filePath2))
        ) {
            XEasyPdfHandler.Document.build(sourcePath)
                    .image(outputStream1, "jpg", 0)
                    .image(outputStream2, "jpg", 6)
                    .close();
        }
        System.out.println("finish");
    }

    @Test
    public void testSplit1() throws IOException {
        String sourcePath = OUTPUT_PATH + "merge.pdf";
        String filePath = OUTPUT_PATH;
        XEasyPdfHandler.Document.build(sourcePath).split(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testSplit2() throws IOException {
        String sourcePath = OUTPUT_PATH + "merge.pdf";
        String filePath = OUTPUT_PATH;
        XEasyPdfHandler.Document.build(sourcePath).split(
                filePath,
                XEasyPdfDocumentSplitter.build().addDocument(0, 1, 2).addDocument(2, 1, 0),
                "mypdf"
        ).close();
        System.out.println("finish");
    }

    @Test
    public void testSplit3() throws IOException {
        String sourcePath = OUTPUT_PATH + "merge.pdf";
        String filePath = OUTPUT_PATH + "test.pdf";
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(filePath))) {
            XEasyPdfHandler.Document.build(sourcePath).split(
                    outputStream,
                    3, 4, 5, 6
            ).close();
        }
        System.out.println("finish");
    }
}
