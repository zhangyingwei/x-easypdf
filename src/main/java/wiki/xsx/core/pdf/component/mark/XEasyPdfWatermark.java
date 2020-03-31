package wiki.xsx.core.pdf.component.mark;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.io.IOException;
import java.util.List;

/**
 * 页面水印组件
 * @author xsx
 * @date 2020/3/25
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
public class XEasyPdfWatermark {

    /**
     * 页面水印参数
     */
    private XEasyPdfWatermarkParam param = new XEasyPdfWatermarkParam();

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param text 水印文本
     */
    public XEasyPdfWatermark(String fontPath, String text) {
        this.param.setFontPath(fontPath).setText(text);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param fontSize 字体大小
     * @param text 水印文本
     */
    public XEasyPdfWatermark(String fontPath, float fontSize, String text) {
        this.param.setFontPath(fontPath).setFontSize(fontSize).setText(text);
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回页面水印组件
     */
    public XEasyPdfWatermark setFont(PDFont font) {
        this.param.setFont(font);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回页面水印组件
     */
    public XEasyPdfWatermark setFontSize(Float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置文本透明度
     * @param alpha 文本透明度
     * @return 返回页面水印组件
     */
    public XEasyPdfWatermark setAlpha(Float alpha) {
        this.param.setAlpha(alpha);
        return this;
    }

    /**
     * 设置文本弧度
     * @param radians 文本弧度
     * @return 返回页面水印组件
     */
    public XEasyPdfWatermark setRadians(Double radians) {
        this.param.setRadians(radians);
        return this;
    }

    /**
     * 设置水印文本
     * @param text 水印文本
     * @return 返回页面水印组件
     */
    public XEasyPdfWatermark setText(String text) {
        this.param.setText(text);
        return this;
    }

    /**
     * 设置水印文本间距
     * @param wordSpace 水印文本间距
     * @return 返回页面水印组件
     */
    public XEasyPdfWatermark setWordSpace(Float wordSpace) {
        this.param.setWordSpace(wordSpace);
        return this;
    }

    /**
     * 画图
     *
     * @param document pdf文档
     * @param page     pdf页面
     * @throws IOException IO异常
     */
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化水印参数，获取pdfBox扩展图形对象
        PDExtendedGraphicsState state = this.param.init(document, page);
        // 获取pdfBox页面列表
        List<PDPage> pageList = page.getPageList();
        // 遍历pdfBox页面列表
        for (PDPage pdPage : pageList) {
            // 执行画水印
            this.doDraw(document, pdPage, state);
        }
    }

    /**
     * 执行画水印
     * @param document pdf文档
     * @param pdPage pdf页面
     * @param state pdfBox扩展图形对象
     * @throws IOException IO异常
     */
    private void doDraw(XEasyPdfDocument document, PDPage pdPage, PDExtendedGraphicsState state) throws IOException {
        // 定义循环添加次数
        int count = 11;
        // 定义字体颜色
        float color = 0.3F;
        // 定义X轴起始坐标
        float beginX = 0;
        // 定义Y轴起始坐标
        float beginY = 0;
        // 初始化内容流
        PDPageContentStream cs = new PDPageContentStream(
                document.getDocument(),
                pdPage,
                PDPageContentStream.AppendMode.APPEND,
                true,
                true
        );
        // 设置图形参数
        cs.setGraphicsStateParameters(state);
        // 设置字体颜色
        cs.setNonStrokingColor(color);
        // Y轴循环添加水印
        for (int i = 0; i < count; i++) {
            // 写入文本
            this.writeText(cs, beginX, beginY);
            // X轴循环添加水印
            for (int j = 0; j < count; j++) {
                // 递增X轴坐标，X轴起始坐标 = X轴起始坐标 + 文本间距
                beginX += this.param.getWordSpace();
                // 写入文本
                this.writeText(cs, beginX, beginY);
            }
            // 重置X轴起始坐标
            beginX = 0;
            // Y轴起始坐标递增，Y轴起始坐标 = Y轴起始坐标 + 文本间距
            beginY += this.param.getWordSpace();
        }
        // 关闭内容流
        cs.close();
    }

    /**
     * 写入水印文本
     * @param cs 内容流
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     * @throws IOException IO异常
     */
    private void writeText(PDPageContentStream cs, float beginX, float beginY) throws IOException {
        // 开启文本输入
        cs.beginText();
        // 设置字体
        cs.setFont(this.param.getFont(), this.param.getFontSize());
        // 设置文本弧度
        cs.setTextMatrix(Matrix.getRotateInstance(this.param.getRadians(), beginX, beginY));
        // 文本输入
        cs.showText(this.param.getText());
        // 结束文本写入
        cs.endText();
    }
}
