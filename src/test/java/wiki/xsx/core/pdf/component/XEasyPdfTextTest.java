package wiki.xsx.core.pdf.component;

import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xsx
 * @date 2020/6/15
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
public class XEasyPdfTextTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\simfang.ttf";
    private static final String OUTPUT_PATH = "E:\\pdf\\test\\component\\text\\";

    @Before
    public void setup() {
        File dir = new File(OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Test
    public void testText() throws IOException {
        String filePath = OUTPUT_PATH + "testText.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        PDRectangle.A4,
                        XEasyPdfHandler.Text.build(20F, "贵阳（贵州省省会）").setHorizontalStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build(
                                "    贵阳，简称“筑”，别称林城、筑城，是贵州省省会，国务院批复确定的中国西南地区重要的区域创新中心、中国重要的生态休闲度假旅游城市 [1]  。" +
                                        "截至2018年，全市下辖6个区、3个县、代管1个县级市，总面积8034平方千米，" +
                                        "建成区面积360平方千米，常住人口488.19万人，城镇人口368.24万人，城镇化率75.43%。"
                        ).setMarginLeft(10).setMarginRight(10),
                        XEasyPdfHandler.Text.build(
                                "    贵阳地处中国西南地区、贵州中部，是西南地区重要的中心城市之一 [3]  ，" +
                                        "贵州省的政治、经济、文化、科教、交通中心，西南地区重要的交通、通信枢纽、工业基地及商贸旅游服务中心 [4-5]  ，" +
                                        "全国综合性铁路枢纽 [6]  ，也是国家级大数据产业发展集聚区 [7]  、呼叫中心与服务外包集聚区 [8]  、大数据交易中心、数据中心集聚区。"
                        ).setMarginLeft(10).setMarginRight(10),
                        XEasyPdfHandler.Text.build(
                                "    贵阳之名较早见于明（弘治）《贵州图经新志》，因境内贵山之南而得名，元代始建顺元城，明永乐年间，" +
                                        "贵州建省，贵阳成为贵州省的政治、军事、经济、文化中心。境内有30多种少数民族，" +
                                        "有山地、河流、峡谷、湖泊、岩溶、洞穴、瀑布、原始森林、人文、古城楼阁等32种旅游景点 [10]  ，" +
                                        "是首个国家森林城市 [11]  、国家循环经济试点城市 [12]  、中国避暑之都 [13]  ，荣登“中国十大避暑旅游城市”榜首。 [14] "
                        ).setMarginLeft(10).setMarginRight(10),
                        XEasyPdfHandler.Text.build(
                                "    2017年，复查确认保留全国文明城市称号。 [15]  2018年度《中国国家旅游》最佳优质旅游城市。 [16]  " +
                                        "2018年重新确认国家卫生城市。2019年1月12日，中国开放发展与合作高峰论坛暨第八届环球总评榜，" +
                                        "贵阳市荣获“2018中国国际营商环境标杆城市”“2018绿色发展和生态文明建设十佳城市”两项大奖。"
                        ).setMarginLeft(10).setMarginRight(10),
                        XEasyPdfHandler.Text.build("-- 摘自百度百科").setHorizontalStyle(XEasyPdfTextStyle.RIGHT).setMarginRight(10F)
                ).setWatermark(
                        XEasyPdfHandler.Watermark.build("贵阳").setFontColor(new Color(51,153,255))
                )
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testText2() throws IOException {
        String filePath = OUTPUT_PATH + "testText2.pdf";
        StringBuilder textBuild = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            textBuild.append("分页分页分页分页分页分页分页分页分页分页分页");
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build(
                                20F,
                                textBuild.toString()
                        ).setHorizontalStyle(XEasyPdfTextStyle.CENTER)
                )
//                        .setHeader(
//                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("页眉").setStyle(XEasyPdfTextStyle.RIGHT))
//                )
        ).setFontPath(FONT_PATH).setGlobalHeader(
                XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("页眉"))
        ).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("页脚"))
        ).setGlobalWatermark(
                XEasyPdfHandler.Watermark.build("贵阳")
        ).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testText3() throws IOException {
        String filePath = OUTPUT_PATH + "testText3.pdf";
        StringBuilder textBuild = new StringBuilder();
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build(
                                20F,
                                "贵阳"
                        ).setMarginRight(50F)
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        System.out.println("finish");
    }

    @Test
    public void testText4() throws IOException {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testText4.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build(
                                14F,
                                "爽爽的贵阳"
                        ).enableTextAppend().setFontColor(Color.GREEN),
                        XEasyPdfHandler.Text.build(
                                14F,
                                "，"
                        ).enableTextAppend(),
                        XEasyPdfHandler.Text.build(
                                14F,
                                "避暑的天堂"
                        ).enableTextAppend().setFontColor(Color.cyan),
                        XEasyPdfHandler.Text.build(
                                14F,
                                "。"
                        ).enableTextAppend(),
                        XEasyPdfHandler.Text.build(
                                14F,
                                "贵阳，简称“筑”，别称林城、筑城，是贵州省省会，" +
                                        "国务院批复确定的中国西南地区重要的区域创新中心、中国重要的生态休闲度假旅游城市 [1]  ；" +
                                        "截至2020年11月，贵阳全市下辖6个区、3个县、代管1个县级市，" +
                                        "总面积8034平方公里，建成区面积360平方公里，常住人口497.14万人，城镇人口378.47万人，城镇化率76.13%。" +
                                        "777777777777777777777777777777"
                        ).enableTextAppend().setFontColor(Color.MAGENTA).enableUnderline().setUnderlineColor(Color.RED),
                        XEasyPdfHandler.Text.build(
                                30F,
                                "新的段落"
                        )
                )
        ).setFontPath(FONT_PATH).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void testText5() {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testText5.pdf";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            builder.append("爽爽的贵阳，避暑的天堂。");
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        PDRectangle.A4,
                        XEasyPdfHandler.Text.build(builder.toString()).setMargin(10f).setAutoIndent(9)
                )
        ).setGlobalWatermark(
                XEasyPdfHandler.Watermark.build("爽爽的贵阳")
        ).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void testText6() {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testText6.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        PDRectangle.A4,
                        XEasyPdfHandler.Text.build("2022心想事成10")
                                .setRadians(10D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成20")
                                .setRadians(20D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成30")
                                .setRadians(30D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成40")
                                .setRadians(40D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成45")
                                .setRadians(45D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成60")
                                .setRadians(60D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成70")
                                .setRadians(70D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成80")
                                .setRadians(80D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成90")
                                .setRadians(90D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-10")
                                .setRadians(-10D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-20")
                                .setRadians(-20D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-30")
                                .setRadians(-30D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-40")
                                .setRadians(-40D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-45")
                                .setRadians(-45D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-60")
                                .setRadians(-60D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-70")
                                .setRadians(-70D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-80")
                                .setRadians(-80D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline(),
                        XEasyPdfHandler.Text.build("2022心想事成-90")
                                .setRadians(-90D).setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(30).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(2F).enableUnderline()
                )
        ).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void testText7() {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testText7.pdf";
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        PDRectangle.A4,
                        XEasyPdfHandler.Text.build("2022心想事成")
                                .setHorizontalStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10F)
                                .setFontSize(12F).setFontColor(Color.BLACK).setUnderlineColor(Color.RED)
                                .setUnderlineWidth(1F).enableUnderline()
                                .setHighlightColor(new Color(0x5EB7F0)).enableHighlight(),
                        XEasyPdfHandler.Text.build("爽爽的贵阳，避暑的天堂，也是堵车的天堂")
                                .setHorizontalStyle(XEasyPdfTextStyle.RIGHT)
                                .setFontSize(12F).setFontColor(Color.BLACK)
                                .setDeleteLineWidth(1F).enableDeleteLine(),
                        XEasyPdfHandler.Text.build("112233")
                                .setVerticalStyle(XEasyPdfTextStyle.BOTTOM)
                                .setHorizontalStyle(XEasyPdfTextStyle.RIGHT)
                )
        ).setGlobalFooter(
                XEasyPdfHandler.Footer.build(
                        XEasyPdfHandler.Text.build(Arrays.asList("第一行", "第二行"))
                ).setMarginLeft(200F)
        ).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @SneakyThrows
    @Test
    public void testText8() {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testText8.pdf";
        PDDocument doc = new PDDocument();
        PDPage pdPage = new PDPage(PDRectangle.A4);
        doc.addPage(pdPage);
        // 新建内容流
        PDPageContentStream contentStream = new PDPageContentStream(
                doc,
                pdPage,
                PDPageContentStream.AppendMode.APPEND,
                true,
                false
        );
        /**
         * mData[0] = 0;
         *     float mCircleRadius = 200;  //圆半径
         *     mData[1] = mCircleRadius;
         *
         *     mData[2] = mCircleRadius;
         *     mData[3] = 0;
         *
         *     mData[4] = 0;
         *     mData[5] = -mCircleRadius;
         *
         *     mData[6] = -mCircleRadius;
         *     mData[7] = 0;
         *     float mDifference = mCircleRadius * C;  //圆形的控制点与数据点的差值
         *     mCtrl[0]  = mData[0]+ mDifference;
         *     mCtrl[1]  = mData[1];
         *
         *     mCtrl[2]  = mData[2];
         *     mCtrl[3]  = mData[3]+ mDifference;
         *
         *     mCtrl[4]  = mData[2];
         *     mCtrl[5]  = mData[3]- mDifference;
         *
         *     mCtrl[6]  = mData[4]+ mDifference;
         *     mCtrl[7]  = mData[5];
         *
         *     mCtrl[8]  = mData[4]- mDifference;
         *     mCtrl[9]  = mData[5];
         *
         *     mCtrl[10] = mData[6];
         *     mCtrl[11] = mData[7]- mDifference;
         *
         *     mCtrl[12] = mData[6];
         *     mCtrl[13] = mData[7]+ mDifference;
         *
         *     mCtrl[14] = mData[0]- mDifference;
         *     mCtrl[15] = mData[1];
         *     path.cubicTo(mCtrl[0],  mCtrl[1],  mCtrl[2],  mCtrl[3],     mData[2], mData[3]);
         *     path.cubicTo(mCtrl[4],  mCtrl[5],  mCtrl[6],  mCtrl[7],     mData[4], mData[5]);
         *     path.cubicTo(mCtrl[8],  mCtrl[9],  mCtrl[10], mCtrl[11],    mData[6], mData[7]);
         *     path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15],    mData[0], mData[1]);
         */
        float[] mData = initData(300, 600, 100);
        float[] mCtrl = initCtrl(mData, 100);
        // 设置定位
        contentStream.moveTo(mData[0], mData[1]);
        contentStream.curveTo(mCtrl[0],  mCtrl[1],  mCtrl[2],  mCtrl[3],     mData[2], mData[3]);
        contentStream.curveTo(mCtrl[4],  mCtrl[5],  mCtrl[6],  mCtrl[7],     mData[4], mData[5]);
        contentStream.curveTo(mCtrl[8],  mCtrl[9],  mCtrl[10], mCtrl[11],    mData[6], mData[7]);
        contentStream.curveTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15],    mData[0], mData[1]);
//        contentStream.stroke();
        contentStream.fillAndStroke();
        contentStream.close();
        doc.save(filePath);
        doc.close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    /**
     *  mData[0] = 0;
     *     float mCircleRadius = 200;  //圆半径
     *     mData[1] = mCircleRadius;
     *
     *     mData[2] = mCircleRadius;
     *     mData[3] = 0;
     *
     *     mData[4] = 0;
     *     mData[5] = -mCircleRadius;
     *
     *     mData[6] = -mCircleRadius;
     *     mData[7] = 0;
     * @param x
     * @param y
     * @param mCircleRadius
     * @return
     */
    private float[] initData(float x, float y, float mCircleRadius) {
        float[] mData = new float[8];               // 顺时针记录绘制圆形的四个数据点
        // 初始化数据点
        mData[0] = x;
        mData[1] = y + mCircleRadius;

        mData[2] = x + mCircleRadius;
        mData[3] = y;

        mData[4] = x;
        mData[5] = y - mCircleRadius;

        mData[6] = x - mCircleRadius;
        mData[7] = y;
        return mData;
    }

    private float[] initCtrl(float[] mData, float mCircleRadius) {
        Point point = new Point(1, 1);
        float[] mCtrl = new float[16];              // 顺时针记录绘制圆形的八个控制点
        // 初始化控制点
        float mDifference = mCircleRadius * 0.551915024494F ;  //圆形的控制点与数据点的差值
        mCtrl[0]  = mData[0] + mDifference;
        mCtrl[1]  = mData[1];

        mCtrl[2]  = mData[2];
        mCtrl[3]  = mData[3] + mDifference;

        mCtrl[4]  = mData[2];
        mCtrl[5]  = mData[3] - mDifference;

        mCtrl[6]  = mData[4] + mDifference;
        mCtrl[7]  = mData[5];

        mCtrl[8]  = mData[4] - mDifference;
        mCtrl[9]  = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7] - mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7] + mDifference;

        mCtrl[14] = mData[0] - mDifference;
        mCtrl[15] = mData[1];
        return mCtrl;
    }

    @SneakyThrows
    @Test
    public void testText9() {
        long begin = System.currentTimeMillis();
        String sourcePath = OUTPUT_PATH + "testText7.pdf";
        String filePath = OUTPUT_PATH + "testText10.pdf";
        PDDocument doc = PDDocument.load(new File(sourcePath));

        PDPage page = doc.getPage(0);
        PDResources resources = page.getResources();
        PDFont font = resources.getFont(resources.getFontNames().iterator().next());
        System.out.println("font.getName() = " + font.getName());
        PDFStreamParser parser = new PDFStreamParser(page);
        parser.parse();
        List<Object> tokens = parser.getTokens();
        StringBuilder builder;
        String replace = "地";
        try (InputStream inputStream = XEasyPdfFontUtil.class.getResourceAsStream(XEasyPdfDefaultFontStyle.NORMAL.getPath())) {
            PDStream updatedStream = new PDStream(doc);
            OutputStream outputStream = updatedStream.createOutputStream(COSName.FLATE_DECODE);
            PDFont font1 = PDType0Font.load(doc, inputStream, true);
            for (Object token : tokens) {
                if (token instanceof COSString) {
                    builder = new StringBuilder();
                    COSString string = (COSString) token;
                    try (InputStream in = new ByteArrayInputStream(string.getBytes())) {
                        while (in.available()>0) {
                            builder.append(font.toUnicode(font.readCode(in)));
                        }
                        System.out.println("COSString = " + builder);
                    }
                    String newValue = builder.toString().replace("的", replace);
                    string.setValue(font1.encode(newValue));
                }
            }
            // 获取默认资源字体名称
            Iterable<COSName> fontNames = resources.getFontNames();
            for (COSName fontName : fontNames) {
                System.out.println("fontName = " + fontName);
                // 字体替换为当前文档字体
                resources.put(fontName, font1);
            }
//            List<Object> list = replaceText(page, font1, "的", "地");

            ContentStreamWriter tokenWriter = new ContentStreamWriter(outputStream);
            tokenWriter.writeTokens(tokens);
            page.setContents(updatedStream);
            outputStream.close();
            font1.subset();
        }

        doc.save(filePath);
        doc.close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void testText10() {
        long begin = System.currentTimeMillis();
        String sourcePath = OUTPUT_PATH + "testText9.pdf";
        String filePath = OUTPUT_PATH + "testText10.pdf";
        XEasyPdfHandler.Document.load(sourcePath).setGlobalWatermark(
                XEasyPdfHandler.Watermark.build("爽爽的贵阳")
        ).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void testTextLink() {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "testText12.pdf";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append("hello world");
        }
        XEasyPdfHandler.Document.build().addPage(
                XEasyPdfHandler.Page.build(
                        XEasyPdfHandler.Text.build(builder.toString())
                                .setLink("https://www.baidu.com")
                                .setComment("转跳百度地址")
                                .setHorizontalStyle(XEasyPdfTextStyle.CENTER)
                )
        ).setGlobalWatermark(
                XEasyPdfHandler.Watermark.build("爽爽的贵阳")
        ).save(filePath).close();
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }

    @Test
    public void testExtractorForm() {
        long begin = System.currentTimeMillis();
        String filePath = OUTPUT_PATH + "fillform.pdf";
        Map<String, String> data = new HashMap<>(2);
        XEasyPdfHandler.Document.load(filePath).extractor().extractForm(data).finish().close();
        System.out.println("data = " + data);
        long end = System.currentTimeMillis();
        System.out.println("完成，耗时： " + (end-begin));
    }
}
