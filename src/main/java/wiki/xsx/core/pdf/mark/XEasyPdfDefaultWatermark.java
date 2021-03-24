package wiki.xsx.core.pdf.mark;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * pdf水印组件
 * @author xsx
 * @date 2020/3/25
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
public class XEasyPdfDefaultWatermark implements XEasyPdfWatermark {

    /**
     * pdf水印参数
     */
    private final XEasyPdfWatermarkParam param = new XEasyPdfWatermarkParam();

    /**
     * 有参构造
     * @param text 水印文本
     */
    public XEasyPdfDefaultWatermark(String text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param fontSize 字体大小
     * @param text 水印文本
     */
    public XEasyPdfDefaultWatermark(float fontSize, String text) {
        this.param.setFontSize(fontSize).setText(text);
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setFont(PDFont font) {
        this.param.setFont(font);
        this.param.setFontPath("");
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setFontColor(Color fontColor) {
        this.param.setFontColor(fontColor);
        return this;
    }

    /**
     * 设置文本透明度
     * @param alpha 文本透明度
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setAlpha(float alpha) {
        this.param.setAlpha(alpha);
        return this;
    }

    /**
     * 设置文本弧度
     * @param radians 文本弧度
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setRadians(double radians) {
        this.param.setRadians(radians);
        return this;
    }

    /**
     * 设置水印文本
     * @param text 水印文本
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setText(String text) {
        this.param.setText(text);
        return this;
    }

    /**
     * 设置水印文本间距
     * @param wordSpace 水印文本间距
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setWordSpace(float wordSpace) {
        this.param.setWordSpace(wordSpace);
        return this;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page     pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化水印参数，获取pdfBox扩展图形对象
        PDExtendedGraphicsState state = this.param.init(document, page);
        // 获取pdfBox页面列表
        List<PDPage> pageList = page.getParam().getPageList();
        // 遍历pdfBox页面列表
        for (PDPage pdPage : pageList) {
            // 执行画水印
            this.doDraw(document, pdPage, state);
        }
        // 获取新的pdfBox页面列表
        pageList = page.getParam().getNewPageList();
        // 遍历pdfBox页面列表
        for (PDPage pdPage : pageList) {
            // 执行画水印
            this.doDraw(document, pdPage, state);
        }
        // 字体路径不为空，说明该组件设置字体，则直接进行字体关联
        if (this.param.getFontPath()!=null&&this.param.getFontPath().length()>0) {
            // 关联字体
            this.param.getFont().subset();
            // 重置字体为null
            this.param.setFont(null);
        }
    }

    /**
     * 执行绘制
     * @param document pdf文档
     * @param pdPage pdf页面
     * @param state pdfBox扩展图形对象
     * @throws IOException IO异常
     */
    private void doDraw(XEasyPdfDocument document, PDPage pdPage, PDExtendedGraphicsState state) throws IOException {
        // 获取页面高度
        float height = pdPage.getMediaBox().getHeight();
        // 获取页面宽度
        float width = pdPage.getMediaBox().getWidth();
        // 初始化内容流
        PDPageContentStream cs = new PDPageContentStream(
                document.getTarget(),
                pdPage,
                PDPageContentStream.AppendMode.APPEND,
                true,
                true
        );
        // 设置图形参数
        cs.setGraphicsStateParameters(state);
        // 设置字体颜色
        cs.setNonStrokingColor(this.param.getFontColor());
        // 设置行间距
        cs.setLeading(this.param.getLeading());
        // 开启文本输入
        cs.beginText();
        // 设置字体
        cs.setFont(this.param.getFont(), this.param.getFontSize());
        // 设置文本坐标
        cs.newLineAtOffset(0F, height);
        // 设置文本弧度
        cs.setTextMatrix(Matrix.getRotateInstance(this.param.getRadians(), -width, height / 2));
        // 获取行数
        int lineCount = (int) (height / (this.param.getFontSize() + this.param.getLeading())) * 2;
        // 循环写入文本
        for (int i = 0; i < lineCount; i++) {
            // 文本输入
            cs.showText(this.param.getText());
            // 换行
            cs.newLine();
        }
        // 结束文本写入
        cs.endText();
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
