package wiki.xsx.core.pdf.component;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Before;
import org.junit.Test;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.io.File;
import java.io.IOException;

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
public class XEasyPdfTextTest {

    private static final String FONT_PATH = "C:\\Windows\\Fonts\\ARIALUNI.TTF";
    private static final String OUTPUT_PATH = "C:\\Users\\xsx\\Desktop\\pdf\\test\\component\\text\\";

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
                        PDRectangle.A1,
                        XEasyPdfHandler.Text.build(20F, "贵阳（贵州省省会）").setStyle(XEasyPdfTextStyle.CENTER),
                        XEasyPdfHandler.Text.build(
                                "    贵阳，简称“筑”，别称林城、筑城，是贵州省省会，国务院批复确定的中国西南地区重要的区域创新中心、中国重要的生态休闲度假旅游城市 [1]  。" +
                                        "截至2018年，全市下辖6个区、3个县、代管1个县级市，总面积8034平方千米，" +
                                        "建成区面积360平方千米，常住人口488.19万人，城镇人口368.24万人，城镇化率75.43%。"
                        ),
                        XEasyPdfHandler.Text.build(
                                "    贵阳地处中国西南地区、贵州中部，是西南地区重要的中心城市之一 [3]  ，" +
                                        "贵州省的政治、经济、文化、科教、交通中心，西南地区重要的交通、通信枢纽、工业基地及商贸旅游服务中心 [4-5]  ，" +
                                        "全国综合性铁路枢纽 [6]  ，也是国家级大数据产业发展集聚区 [7]  、呼叫中心与服务外包集聚区 [8]  、大数据交易中心、数据中心集聚区。"
                        ),
                        XEasyPdfHandler.Text.build(
                                "    贵阳之名较早见于明（弘治）《贵州图经新志》，因境内贵山之南而得名，元代始建顺元城，明永乐年间，" +
                                        "贵州建省，贵阳成为贵州省的政治、军事、经济、文化中心。境内有30多种少数民族，" +
                                        "有山地、河流、峡谷、湖泊、岩溶、洞穴、瀑布、原始森林、人文、古城楼阁等32种旅游景点 [10]  ，" +
                                        "是首个国家森林城市 [11]  、国家循环经济试点城市 [12]  、中国避暑之都 [13]  ，荣登“中国十大避暑旅游城市”榜首。 [14] "
                        ),
                        XEasyPdfHandler.Text.build(
                                "    2017年，复查确认保留全国文明城市称号。 [15]  2018年度《中国国家旅游》最佳优质旅游城市。 [16]  " +
                                        "2018年重新确认国家卫生城市。2019年1月12日，中国开放发展与合作高峰论坛暨第八届环球总评榜，" +
                                        "贵阳市荣获“2018中国国际营商环境标杆城市”“2018绿色发展和生态文明建设十佳城市”两项大奖。"
                        ),
                        XEasyPdfHandler.Text.build("-- 摘自百度百科").setStyle(XEasyPdfTextStyle.RIGHT).setMarginRight(10F)
                ).setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("页眉"))
                ).setWatermark(
                        XEasyPdfHandler.Watermark.build("贵阳").setFontSize(150F)
                )
        ).setFontPath(FONT_PATH).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("页脚"))
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
                        ).setStyle(XEasyPdfTextStyle.CENTER)
                ).setHeader(
                        XEasyPdfHandler.Header.build(XEasyPdfHandler.Text.build("页眉"))
                )
        ).setFontPath(FONT_PATH).setGlobalFooter(
                XEasyPdfHandler.Footer.build(XEasyPdfHandler.Text.build("页脚"))
        ).setGlobalWatermark(
                XEasyPdfHandler.Watermark.build("贵阳")
        ).save(filePath).close();
        System.out.println("finish");
    }
}
