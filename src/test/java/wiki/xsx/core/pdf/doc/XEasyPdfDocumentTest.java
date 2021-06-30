package wiki.xsx.core.pdf.doc;

import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageStyle;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

/**
 * @author xsx
 * @date 2020/6/8
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
public class XEasyPdfDocumentTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\ARIALUNI.TTF";
//    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
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
                        XEasyPdfHandler.Text.build("可以，世界！"),
                        XEasyPdfHandler.Text.build("我是第一页")
                ),
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("你好，世界！").setStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build("我是第二页").setStyle(XEasyPdfTextStyle.RIGHT)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testSave() throws IOException {
        String sourcePath = OUTPUT_PATH + "testAddPage.pdf";
        String filePath = OUTPUT_PATH + "doc1.pdf";
        XEasyPdfHandler.Document.load(sourcePath).addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("Hello World").setStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build("你好，世界！")
                ).setFontPath(FONT_PATH)
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testSetInfo() throws IOException {
        String filePath = OUTPUT_PATH + "info.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH).information()
            .setTitle("test info")
            .setAuthor("xsx")
            .setSubject("info")
            .setCreator("my-creator")
            .setKeywords("pdf,xsx")
            .setCreateTime(Calendar.getInstance())
            .setUpdateTime(Calendar.getInstance())
            .finish()
            .save(filePath)
            .close();
        System.out.println("finish");
    }

    @Test
    public void testSetPermission() throws IOException {
        String filePath = OUTPUT_PATH + "permission.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH).permission()
                .setCanPrintDegraded(false)
                .setCanPrint(false)
                .setCanAssembleDocument(false)
                .setCanExtractContent(false)
                .setCanExtractForAccessibility(false)
                .setCanFillInForm(false)
                .setCanModify(false)
                .setCanModifyAnnotations(false)
                .finish()
                .save(filePath)
                .close();
        System.out.println("finish");
    }

    @Test
    public void testSetBackgroundColor() throws IOException {
        String filePath = OUTPUT_PATH + "backgroundColor.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(),
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH)
                .setGlobalBackgroundColor(Color.YELLOW)
                .save(filePath)
                .close();
        System.out.println("finish");
    }

    @Test
    public void testSetBackgroundImage() throws IOException {
        String filePath = OUTPUT_PATH + "backgroundImage.pdf";
        String imagePath = "D:\\temp\\0020033143720852_b.jpg";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build().addComponent(
                        XEasyPdfHandler.Text.build(
                                "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                                        + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!" + "hello world!!!"
                        ).setFontSize(20)
                ).setBackgroundColor(Color.WHITE),
                XEasyPdfHandler.Page.build()
        ).setFontPath(FONT_PATH)
                .setGlobalBackgroundColor(new Color(0,191,255))
                .setGlobalBackgroundImage(XEasyPdfHandler.Image.build(new File(imagePath)).setStyle(XEasyPdfImageStyle.CENTER).setMarginTop(300))
                .save(filePath)
                .close();
        System.out.println("finish");
    }

    @Test
    public void testInsertPage() throws IOException {
        String sourcePath = OUTPUT_PATH + "backgroundColor.pdf";
        String filePath = OUTPUT_PATH + "insertPage.pdf";
        XEasyPdfHandler.Document.load(sourcePath).insertPage(
                -100,
                XEasyPdfHandler.Page.build(XEasyPdfHandler.Text.build("插入首页"))
        ).insertPage(
                100,
                XEasyPdfHandler.Page.build(XEasyPdfHandler.Text.build("插入尾页"))
        ).setFontPath(FONT_PATH)
                .setGlobalBackgroundColor(new Color(0,191,255))
                .save(filePath)
                .close();
        System.out.println("finish");
    }

    @Test
    public void testMerge() throws IOException {
        String sourcePath = OUTPUT_PATH + "backgroundColor.pdf";
        String mergePath1 = OUTPUT_PATH + "insertPage.pdf";
        String mergePath2 = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH + "merge.pdf";
        XEasyPdfHandler.Document.load(sourcePath).merge(
                XEasyPdfHandler.Document.load(mergePath1),
                XEasyPdfHandler.Document.load(mergePath2)
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testImage1() throws IOException {
        String sourcePath = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH;
        XEasyPdfHandler.Document.load(sourcePath).imager().image(filePath, XEasyPdfImageType.PNG).finish().close();
        System.out.println("finish");
    }

    @Test
    public void testImage2() throws IOException {
        String sourcePath = OUTPUT_PATH + "insertPage.pdf";
        String filePath = OUTPUT_PATH;
        String prefix = "x-easypdf";
        XEasyPdfHandler.Document.load(sourcePath).imager().image(filePath, XEasyPdfImageType.JPEG, prefix).finish().close();
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
            XEasyPdfHandler.Document.load(sourcePath)
                    .imager()
                    .image(outputStream1, XEasyPdfImageType.JPEG, 0)
                    .image(outputStream2, XEasyPdfImageType.PNG, 6)
                    .finish()
                    .close();
        }
        System.out.println("finish");
    }

    @Test
    public void testSplit1() throws IOException {
        String sourcePath = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH;
        XEasyPdfHandler.Document.load(sourcePath).splitter().split(filePath).finish().close();
        System.out.println("finish");
    }

    @Test
    public void testSplit2() throws IOException {
        String sourcePath = OUTPUT_PATH + "testAddPage.pdf";
        String filePath = OUTPUT_PATH;
        XEasyPdfHandler.Document.load(sourcePath)
                .splitter()
                .addDocument(1)
                .addDocument(1, 0)
                .split(filePath, "mypdf")
                .finish()
                .close();
        System.out.println("finish");
    }

    @Test
    public void testSplit3() throws IOException {
        String sourcePath = "C:\\Users\\xsx\\Desktop\\spring-boot-reference .pdf";
        String filePath = OUTPUT_PATH + "test.pdf";
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(filePath))) {
            XEasyPdfHandler.Document.load(sourcePath).splitter().split(outputStream, 41).finish().close();
        }
        System.out.println("finish");
    }

    @Test
    public void testStripText() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH + "testText.pdf";
        List<String> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extract(list, "《.*》").finish().close();
        for (String s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void testStripText1() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH + "testText.pdf";
        List<String> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extract(list).finish().close();
        for (String s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void testStripText2() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = "C:\\Users\\xsx\\Desktop\\pdf\\test\\component\\table\\testTable.pdf";
        List<Map<String, String>> dataList = new ArrayList<>();
        XEasyPdfHandler.Document.load(sourcePath).extractor().addRegion("test1", new Rectangle(600,2000)).extractByRegions(dataList, 0).finish().close();
        System.out.println("dataList = " + dataList);
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void testStripTable() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = "C:\\Users\\xsx\\Desktop\\RyxtaeBSbg3z6y9rK9BX0-ccgfdt-u4q3a4UL9y05FS51LrPcf6wJVdpHC5T4Z6iVCjkkZQym4pZ4Wn0AQ8ScQ.pdf";
        List<List<String>> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extractForSimpleTable(list, 0).finish().close();
        for (List<String> s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void testStripTable2() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = "C:\\Users\\xsx\\Desktop\\spring-boot-reference .pdf";
        List<List<String>> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extractByRegionsForSimpleTable(list, new Rectangle(0,0, 800, 170),320).finish().close();
        for (List<String> s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void testStripTable3() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = "C:\\Users\\xsx\\Desktop\\spring-boot-reference .pdf";
        List<Map<String, String>> dataList = new ArrayList<>();
        XEasyPdfHandler.Document.load(sourcePath).extractor().addRegion("test1", new Rectangle(0,320,800,540)).extractByRegions(dataList, 0).finish().close();
        System.out.println("dataList = " + dataList);
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void testFillForm() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH + "fillForm.pdf";
        final String outputPath = OUTPUT_PATH + "test_fill2.pdf";
        Map<String, String> form = new HashMap<>(2);
        form.put("test", "爽爽的贵阳");
        form.put("test2", "堵车的天堂");
        XEasyPdfHandler.Document.load(sourcePath).setFontPath("C:\\Users\\xsx\\Desktop\\pdf\\test\\simhei.ttf").fillForm(form).save(outputPath).close();
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test() throws IOException {
        final String fontPath = "C:\\Windows\\Fonts\\STSONG.TTF";
        final String backgroundImagePath = "D:\\temp\\background.jpg";
        final String outputPath = "C:\\Users\\xsx\\Desktop\\pdf\\text.pdf";

        // 设置背景图片
        XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                // 构建图片
                XEasyPdfHandler.Image.build(new File(backgroundImagePath)).setHeight(800F)
            // 设置全局页眉
        ).setGlobalHeader(
                // 构建页眉
                XEasyPdfHandler.Header.build(
                        // 构建页眉文本，并居中显示
                        XEasyPdfHandler.Text.build("这是页眉").setStyle(XEasyPdfTextStyle.CENTER)
                )
            // 设置全局页脚
        ).setGlobalFooter(
                // 构建页脚
                XEasyPdfHandler.Footer.build(
                        // 构建页脚文本
                        XEasyPdfHandler.Text.build("这是页脚")
                )
            // 添加页面
        ).addPage(
                // 构建页面
                XEasyPdfHandler.Page.build(
                        // 构建文本
                        XEasyPdfHandler.Text.build(
                                "Hello World(这是一个DEMO)"
                        ).setStyle(XEasyPdfTextStyle.CENTER).setFontSize(20F).setMargin(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "        这里是正文（这是一个基于PDFBOX开源工具，专注于PDF文件导出功能，" +
                                        "以组件形式进行拼接，简单、方便，上手及其容易，" +
                                        "目前有TEXT(文本)、LINE(分割线)等组件，后续还会补充更多组件，满足各种需求）。"
                        ).setStyle(XEasyPdfTextStyle.LEFT).setFontSize(14F).setMargin(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "-- by xsx"
                        ).setStyle(XEasyPdfTextStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "2020.03.12"
                        ).setStyle(XEasyPdfTextStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建实线分割线
                        ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F)
                        // 构建虚线分割线
                        ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F)
                        // 构建实线分割线
                        ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build( "完结").setStyle(XEasyPdfTextStyle.CENTER)
                )
            // 设置字体路径，并保存
        ).setFontPath(fontPath).save(outputPath).close();
    }
}
