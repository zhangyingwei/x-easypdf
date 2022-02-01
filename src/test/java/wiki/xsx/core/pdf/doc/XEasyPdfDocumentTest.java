package wiki.xsx.core.pdf.doc;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;
import wiki.xsx.core.pdf.util.XEasyPdfFileUtil;
import wiki.xsx.core.pdf.util.XEasyPdfImageUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

/**
 * @author xsx
 * @date 2020/6/8
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XEasyPdfDocumentTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\msyh.ttf";
    private static final String OUTPUT_PATH = "E:\\pdf\\test\\doc\\";
    private static final String IMAGE_PATH = OUTPUT_PATH + "test.png";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void test01AddPage() throws IOException {
        String filePath = OUTPUT_PATH + "testAddPage.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("Hello World").setHorizontalStyle(XEasyPdfPositionStyle.CENTER),
                        XEasyPdfHandler.Text.build("可以，世界！"),
                        XEasyPdfHandler.Text.build("我是第一页")
                ),
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("你好，世界！").setHorizontalStyle(XEasyPdfPositionStyle.CENTER),
                        XEasyPdfHandler.Text.build("我是第二页").setHorizontalStyle(XEasyPdfPositionStyle.RIGHT)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void test02Save() throws IOException {
        String sourcePath = OUTPUT_PATH + "testAddPage.pdf";
        String filePath = OUTPUT_PATH + "doc1.pdf";
        XEasyPdfHandler.Document.load(sourcePath).addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("Hello World").setHorizontalStyle(XEasyPdfPositionStyle.CENTER),
                        XEasyPdfHandler.Text.build("你好，世界！")
                ).setFontPath(FONT_PATH)
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void test03SetInfo() throws IOException {
        String filePath = OUTPUT_PATH + "info.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).information()
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
    public void test04SetPermission() throws IOException {
        String filePath = OUTPUT_PATH + "permission.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build()
        ).permission()
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
    public void test05SetBackgroundColor() throws IOException {
        String filePath = OUTPUT_PATH + "backgroundColor.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(),
                XEasyPdfHandler.Page.build()
        ).setGlobalBackgroundColor(Color.YELLOW)
                .save(filePath)
                .close();
        System.out.println("finish");
    }

    @Test
    public void test06SetBackgroundImage() throws IOException {
        String filePath = OUTPUT_PATH + "backgroundImage.pdf";
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
                .setGlobalBackgroundImage(XEasyPdfHandler.Image.build(new File(IMAGE_PATH)).setHorizontalStyle(XEasyPdfPositionStyle.CENTER))
                .save(filePath)
                .close();
        System.out.println("finish");
    }

    @Test
    public void test07InsertPage() throws IOException {
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
    public void test08Merge() throws IOException {
        String sourcePath = OUTPUT_PATH + "backgroundColor.pdf";
        String mergePath1 = OUTPUT_PATH + "insertPage.pdf";
        String mergePath2 = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH + "merge.pdf";
        XEasyPdfHandler.Document.load(sourcePath).merge(
                XEasyPdfHandler.Document.load(mergePath1),
                XEasyPdfHandler.Document.load(mergePath2)
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void test09Image1() throws IOException {
        String sourcePath = OUTPUT_PATH + "doc1.pdf";
        XEasyPdfHandler.Document.load(sourcePath).imager().image(OUTPUT_PATH, XEasyPdfImageType.PNG).finish().close();
        System.out.println("finish");
    }

    @Test
    public void test10Image2() throws IOException {
        String sourcePath = OUTPUT_PATH + "insertPage.pdf";
        String prefix = "x-easypdf";
        XEasyPdfHandler.Document.load(sourcePath).imager().image(OUTPUT_PATH, XEasyPdfImageType.JPEG, prefix).finish().close();
        System.out.println("finish");
    }

    @Test
    public void test11Image3() throws IOException {
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
    public void test12Split1() throws IOException {
        String sourcePath = OUTPUT_PATH + "doc1.pdf";
        XEasyPdfHandler.Document.load(sourcePath).splitter().split(OUTPUT_PATH).finish().close();
        System.out.println("finish");
    }

    @Test
    public void test13Split2() throws IOException {
        String sourcePath = OUTPUT_PATH + "testAddPage.pdf";
        XEasyPdfHandler.Document.load(sourcePath)
                .splitter()
                .addDocument(1)
                .addDocument(1, 0)
                .split(OUTPUT_PATH, "mypdf")
                .finish()
                .close();
        System.out.println("finish");
    }

    @Test
    public void test14Split3() throws IOException {
        String sourcePath = OUTPUT_PATH + "doc1.pdf";
        String filePath = OUTPUT_PATH + "testSplit3.pdf";
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(filePath))) {
            XEasyPdfHandler.Document.load(sourcePath).splitter().split(outputStream, 1).finish().close();
        }
        System.out.println("finish");
    }

    @Test
    public void test15StripText() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH + "doc1.pdf";
        List<String> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extract(list, ".*第.*").finish().close();
        for (String s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test16StripText1() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH + "doc1.pdf";
        List<String> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extract(list).finish().close();
        for (String s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test17StripText2() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH + "doc1.pdf";
        List<Map<String, String>> dataList = new ArrayList<>();
        XEasyPdfHandler.Document.load(sourcePath).extractor().addRegion("test1", new Rectangle(600,2000)).extractByRegions(dataList, 0).finish().close();
        System.out.println("dataList = " + dataList);
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test18StripTable() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH +"testAddPage.pdf";
        List<List<String>> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extractForSimpleTable(list, 0).finish().close();
        for (List<String> s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test19StripTable2() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH +"testAddPage.pdf";
        List<List<String>> list = new ArrayList<>(1024);
        XEasyPdfHandler.Document.load(sourcePath).extractor().extractByRegionsForSimpleTable(list, new Rectangle(0,0, 800, 170),1).finish().close();
        for (List<String> s : list) {
            System.out.println("s = " + s);
        }
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test20StripTable3() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH +"testAddPage.pdf";
        List<Map<String, String>> dataList = new ArrayList<>();
        XEasyPdfHandler.Document.load(sourcePath).extractor().addRegion("test1", new Rectangle(0,320,800,540)).extractByRegions(dataList, 0).finish().close();
        System.out.println("dataList = " + dataList);
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test21FillForm() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = "E:\\pdf\\hi.pdf";
        final String outputPath = OUTPUT_PATH + "test_fill2.pdf";
        Map<String, String> form = new HashMap<>(2);
        form.put("test1", "爽爽的贵阳");
        form.put("test2", "堵车的天堂");
        XEasyPdfHandler.Document.load(sourcePath).formFiller().fill(form).finish(outputPath);
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test22() throws IOException {
        final String outputPath = OUTPUT_PATH+"text.pdf";

        // 设置背景图片
        XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                // 构建图片
                XEasyPdfHandler.Image.build(new File(IMAGE_PATH)).setHeight(800F).enableCenterStyle()
                // 设置全局页眉
        ).setGlobalHeader(
                // 构建页眉
                XEasyPdfHandler.Header.build(
                        // 构建页眉文本，并居中显示
                        XEasyPdfHandler.Text.build("这是页眉").setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
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
                        ).setHorizontalStyle(XEasyPdfPositionStyle.CENTER).setFontSize(20F).setMargin(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "这里是正文（这是一个基于PDFBOX开源工具，专注于PDF文件导出功能，" +
                                        "以组件形式进行拼接，简单、方便，上手及其容易，" +
                                        "目前有TEXT(文本)、LINE(分割线)等组件，后续还会补充更多组件，满足各种需求）。"
                        ).setHorizontalStyle(XEasyPdfPositionStyle.LEFT).setFontSize(14F).setMargin(10F).setAutoIndent(9)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "-- by xsx"
                        ).setHorizontalStyle(XEasyPdfPositionStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "2020.03.12"
                        ).setHorizontalStyle(XEasyPdfPositionStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建实线分割线
                        ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F)
                        // 构建虚线分割线
                        ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F)
                        // 构建实线分割线
                        ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build( "完结").setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                )
                // 设置字体路径，并保存
        ).save(outputPath).close();
    }

    @Test
    public void test23() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH+"hi.pdf";
        final String outputPath = OUTPUT_PATH+"test_fill4.pdf";
        Map<String, String> form = new HashMap<>(5);
        form.put("test1", "爽爽的贵阳");
        form.put("test2", "堵车的天堂");
        form.put("text1", "xxx");
        form.put("text2", "sss");
        form.put("hi", "我是xsx");
        XEasyPdfHandler.Document.load(sourcePath).formFiller().fill(form).finish(outputPath);;
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }


    @Test
    public void test24() throws IOException {
        long begin = System.currentTimeMillis();
        final String sourcePath = OUTPUT_PATH+"hi.pdf";
        final String outputPath = OUTPUT_PATH+"ZZZ.pdf";
        Map<String, String> form = new HashMap<>(2);
        form.put("hi", "静静");
        form.put("test1", "7");
        form.put("test2", "xxx");
        XEasyPdfHandler.Document.load(sourcePath).setFontPath(FONT_PATH).addPage(
                XEasyPdfHandler.Page.build(XEasyPdfHandler.Text.build("哈哈"))
        ).formFiller().fill(form).finish(outputPath);
        long end = System.currentTimeMillis();
        System.out.println("finish("+(end-begin)+"ms)");
    }

    @Test
    public void test26() throws IOException {
        long begin = System.currentTimeMillis();
        // 定义保存路径
        final String outputPath = OUTPUT_PATH + "merge.pdf";
        XEasyPdfDocument document = XEasyPdfHandler.Document.build();
        document.merge(
                XEasyPdfHandler.Document.build().addPage(
                        XEasyPdfHandler.Page.build(
                                XEasyPdfHandler.Text.build("第一个文件")
                        )
                ),
                XEasyPdfHandler.Document.build().addPage(
                        XEasyPdfHandler.Page.build(
                                XEasyPdfHandler.Text.build("第二个文件")
                        )
                )
        ).setGlobalHeader(
                XEasyPdfHandler.Header.build(
                        XEasyPdfHandler.Text.build("当前页码："+XEasyPdfHandler.Page.getCurrentPagePlaceholder()+"" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
                                "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                        )
                )
        ).setGlobalFooter(
                XEasyPdfHandler.Footer.build(
                        XEasyPdfHandler.Text.build("当前页码："+XEasyPdfHandler.Page.getCurrentPagePlaceholder())
                )
        ).bookmark()
                .setBookMark(0, "第1个文件")
                .setBookMark(
                        XEasyPdfDocumentBookmark.BookmarkNode.build()
                                .setTitle("第二个文件")
                                .setPage(1)
                                .setTitleColor(new Color(255, 0, 153))
                                .addChild(
                                        XEasyPdfDocumentBookmark.BookmarkNode.build()
                                                .setTitle("第二个文件子节点")
                                                .setPage(1)
                                                .setTop(300)
                                                .setTitleColor(new Color(255,50,100))
                                )
                )
                .finish()
                .save(outputPath)
                .close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void test27() throws IOException {
        long begin = System.currentTimeMillis();
        // 定义保存路径
        final String outputPath = OUTPUT_PATH + "mutilPage.pdf";
        XEasyPdfDocument document = XEasyPdfHandler.Document.build();
        document.addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("第一个页面文件第一行"),
                        XEasyPdfHandler.Text.build("第一个页面文件第二行"),
                        XEasyPdfHandler.Text.build("第一个页面文件第三行")
                ),
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("第二个页面文件")
                ),
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("第三个页面文件第一行"),
                        XEasyPdfHandler.Text.build("第三个页面文件第二行")
                )
        ).setGlobalHeader(
                XEasyPdfHandler.Header.build(
                        XEasyPdfHandler.Text.build(Arrays.asList("当前页码："+XEasyPdfHandler.Page.getCurrentPagePlaceholder(), "页眉第二行", "页眉XXXXXX")).setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                )
        ).setGlobalFooter(
                XEasyPdfHandler.Footer.build(
                        XEasyPdfHandler.Text.build("当前页码："+XEasyPdfHandler.Page.getCurrentPagePlaceholder())
                )
        ).save(outputPath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void test28() throws IOException {
        long begin = System.currentTimeMillis();
        // 定义保存路径
        final String outputPath = OUTPUT_PATH + "sign.pdf";
        final String imagePath = "C:\\Users\\Administrator\\Desktop\\2022010115431859.gif";
        final String certPath = "E:\\pdf\\file.p12";
        try (OutputStream outputStream = Files.newOutputStream(XEasyPdfFileUtil.createDirectories(Paths.get(outputPath)))) {
            XEasyPdfHandler.Document.build(
                    XEasyPdfHandler.Page.build(
                            XEasyPdfHandler.Text.build("爽爽的贵阳，避暑的天堂").setFontSize(16).setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                    )
            ).signer().setSignerInfo(
                    "xsx", "贵阳市", "测试", "qq: 344646090"
            ).setCertificate(
                    XEasyPdfDocumentSigner.SignAlgorithm.MD5withRSA, XEasyPdfDocumentSigner.KeyStoreType.PKCS12, new File(certPath), "123456"
            ).setSignImage(
                    XEasyPdfImageUtil.read(new File(imagePath)), 240F, 30F, 50F
            ).sign(0, outputStream);
        }
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void test29() throws IOException {
        long begin = System.currentTimeMillis();
        // 定义源路径
        final String sourcePath = OUTPUT_PATH + "merge.pdf";
        // 定义保存路径
        final String outputPath = OUTPUT_PATH + "replaceText.pdf";
        Map<String, String> replaceMap = new HashMap<>(1);
        replaceMap.put("\\d", "数字");
        try (OutputStream outputStream = Files.newOutputStream(XEasyPdfFileUtil.createDirectories(Paths.get(outputPath)))) {
            XEasyPdfHandler.Document.load(sourcePath).replacer().replaceText(replaceMap).finish(outputStream);
        }
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void test30() throws IOException {
        long begin = System.currentTimeMillis();
        // 定义保存路径
        final String outputPath = OUTPUT_PATH + "flush.pdf";
        XEasyPdfHandler.Document.build(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("你好")
                )
        ).flush().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build("贵阳")
                )
        ).save(outputPath);
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void allTest() throws IOException {
        long begin = System.currentTimeMillis();
        // 定义保存路径
        final String outputPath = OUTPUT_PATH + "allTest.pdf";
        // 定义页眉与页脚字体颜色
        Color headerAndFooterColor = new Color(10, 195, 255);
        // 定义分割线颜色
        Color lineColor = new Color(210, 0, 210);
        // 获取背景图片
        try (InputStream backgroundImageInputStream = new URL("https://i0.hdslb.com/bfs/article/1e60a08c2dfdcfcd5bab0cae4538a1a7fe8fc0f3.png").openStream()) {
            // 设置背景图片
            XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                            // 构建图片并垂直居中
                            XEasyPdfHandler.Image.build(backgroundImageInputStream, XEasyPdfImageType.PNG).setVerticalStyle(XEasyPdfPositionStyle.CENTER)
                            // 设置全局页眉
                    ).setGlobalHeader(
                            // 构建页眉
                            XEasyPdfHandler.Header.build(
                                    // 构建页眉图片，并居中显示
                                    XEasyPdfHandler.Image.build(new File(IMAGE_PATH)).setHeight(50F).enableCenterStyle(),
                                    // 构建页眉文本，并居中显示
                                    XEasyPdfHandler.Text.build("这是粗体页眉")
                                            // 设置水平垂直居中
                                            .enableCenterStyle()
                                            // 设置字体大小
                                            .setFontSize(20F)
                                            // 设置字体颜色
                                            .setFontColor(headerAndFooterColor)
                                            // 使用粗体字
                                            .setDefaultFontStyle(XEasyPdfDefaultFontStyle.BOLD)
                            )
                            // 设置全局页脚
                    ).setGlobalFooter(
                            // 构建页脚
                            XEasyPdfHandler.Footer.build(
                                    // 构建页眉图片，并居中显示
                                    XEasyPdfHandler.Image.build(new File(IMAGE_PATH)).setHeight(50F).setVerticalStyle(XEasyPdfPositionStyle.BOTTOM),
                                    // 构建页脚文本（手动分行），并居中显示
                                    XEasyPdfHandler.Text.build(Arrays.asList("这是粗体页脚第一行", "这是粗体页脚第二行"))
                                            // 设置水平居中
                                            .setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                                            // 设置垂直居下
                                            .setVerticalStyle(XEasyPdfPositionStyle.BOTTOM)
                                            // 设置字体大小
                                            .setFontSize(20F)
                                            // 设置字体颜色
                                            .setFontColor(headerAndFooterColor)
                                            // 使用粗体字
                                            .setDefaultFontStyle(XEasyPdfDefaultFontStyle.BOLD)
                            )
                            // 添加页面
                    ).addPage(
                            // 构建页面
                            XEasyPdfHandler.Page.build(
                                    // 构建文本
                                    XEasyPdfHandler.Text.build(
                                            "x-easypdf简介（细体）"
                                    ).setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                                            // 设置字体大小
                                            .setFontSize(30F)
                                            // 使用细体字
                                            .setDefaultFontStyle(XEasyPdfDefaultFontStyle.LIGHT)
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "x-easypdf是一个基于PDFBOX的开源框架，"
                                    ).setFontSize(16F).setFontColor(new Color(51, 0, 153))
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "专注于PDF文件导出功能，"
                                    ).enableTextAppend().setFontSize(16F).setFontColor(new Color(102, 0, 153))
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "以组件形式进行拼接，"
                                    ).enableTextAppend().setFontSize(16F).setFontColor(new Color(153, 0, 153))
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "简单、方便，功能丰富，"
                                    ).enableTextAppend().setFontSize(16F).setFontColor(new Color(204, 0, 153))
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "欢迎大家试用并提出宝贵意见。"
                                    ).enableTextAppend().setFontSize(16F).setFontColor(new Color(255, 0, 153))
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "-- by xsx"
                                    ).setHorizontalStyle(XEasyPdfPositionStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                                    // 构建文本
                                    ,XEasyPdfHandler.Text.build(
                                            "2021.10.10"
                                    ).setHorizontalStyle(XEasyPdfPositionStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                                    // 构建实线分割线
                                    ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                                    // 构建虚线分割线
                                    ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                                    // 构建实线分割线
                                    ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                                    // 构建表格
                                    ,XEasyPdfHandler.Table.build(
                                            // 构建行
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                            // 添加文本
                                                            XEasyPdfHandler.Text.build("第一行第一列")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                            XEasyPdfHandler.Text.build("第一行第二列")
                                                    ),
                                                    // 构建单元格并设置字体大小为15，边框颜色为绿色
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                            XEasyPdfHandler.Text.build("第一行第三列")
                                                    ).setFontSize(15F).setBorderColor(Color.GREEN),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                            XEasyPdfHandler.Text.build("第一行第四列")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                            XEasyPdfHandler.Text.build("第一行第五列")
                                                    )
                                                // 设置该行字体大小为20
                                            ).setFontSize(20F),
                                            // 构建行
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格，合并三行
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 300F).addContent(
                                                            XEasyPdfHandler.Text.build(Arrays.asList("第二行", "第一列", "合并三行"))
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(300F).addContent(
                                                            XEasyPdfHandler.Text.build("第二行第二列，合并三列")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 300F).addContent(
                                                            XEasyPdfHandler.Text.build("第二行第三列，合并三行")
                                                    )
                                                // 设置行高为100（合并行需要设置平均行高）
                                            ).setHeight(100F),
                                            // 构建行
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格，开启垂直合并
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).enableVerticalMerge(),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第三行第一列")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第三行第二列")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第三行第三列")
                                                    )
                                                // 设置行高为100，设置边框颜色为红色，设置字体颜色为蓝色
                                            ).setHeight(100F).setBorderColor(Color.RED).setFontColor(Color.BLUE),
                                            // 构建行（单元格已设置高度，则合并行无需设置行高）
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格，开启垂直合并
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F, 100F).enableVerticalMerge(),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(300F, 100F).addContent(
                                                            XEasyPdfHandler.Text.build("第四行第一列，合并三列")
                                                    )
                                            ),
                                            // 构建行（根据文本高度自适应行高）
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第五行第一列，自适应高度！")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第五行第二列，自适应高度！！！！！！！！！！")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第五行第三列，自适应高度！！！！！！！！！！！！！！！！！！！！！！")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第五行第四列，自适应高度！！！！！！！！！！")
                                                    ),
                                                    // 构建单元格
                                                    XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                            XEasyPdfHandler.Text.build("第五行第五列，自适应高度！")
                                                    )
                                            ),
                                            // 构建行
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格，并设置边框颜色为橘色
                                                    XEasyPdfHandler.Table.Row.Cell.build(500F, 100F).addContent(
                                                            XEasyPdfHandler.Text.build("分页测试1")
                                                    ).setBorderColor(Color.ORANGE)
                                            )
                                        // 设置表头行
                                    ).setTileRow(
                                            // 构建行
                                            XEasyPdfHandler.Table.Row.build(
                                                    // 构建单元格，并设置边框颜色为黑色，字体大小为30，字体颜色为紫色
                                                    XEasyPdfHandler.Table.Row.Cell.build(500F, 100F).addContent(
                                                            XEasyPdfHandler.Text.build("表头")
                                                    ).setBorderColor(Color.BLACK).setFontSize(30F).setFontColor(Color.MAGENTA)
                                            )
                                        // 开启表格内容上下左右居中
                                    ).enableCenterStyle()
                                            // 设置左边距为50
                                            .setMarginLeft(50F)
                                            // 设置上边距为10
                                            .setMarginTop(10F)
                                            // 设置边框颜色为灰色
                                            .setBorderColor(Color.GRAY)
                                            // 开启自动表头（分页时，自动添加表头行）
                                            .enableAutoTitle()
                            )
                            // 保存、关闭
                    ).save(outputPath).close();
        }
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }
}
