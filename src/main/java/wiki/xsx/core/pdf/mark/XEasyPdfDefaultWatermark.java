package wiki.xsx.core.pdf.mark;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.awt.*;
import java.util.List;


/**
 * pdf水印组件
 * @author xsx
 * @date 2020/3/25
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
        this.param.setFontSize(Math.abs(fontSize)).setText(text);
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
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        if (style!=null) {
            this.param.setDefaultFontStyle(style);
        }
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setFontSize(float fontSize) {
        this.param.setFontSize(Math.abs(fontSize));
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setFontColor(Color fontColor) {
        if (fontColor!=null) {
            this.param.setFontColor(fontColor);
        }
        return this;
    }

    /**
     * 设置文本透明度
     * @param alpha 文本透明度
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setAlpha(float alpha) {
        if (0<=alpha&&alpha<=1) {
            this.param.setAlpha(alpha);
        }
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
        this.param.setWordSpace(Math.abs(wordSpace));
        return this;
    }

    /**
     * 设置水印文本行数
     * @param wordLine 水印文本行数
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark setWordLine(int wordLine) {
        this.param.setWordLine(Math.abs(wordLine));
        return this;
    }

    /**
     * 开启外水印模式
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark enableOuterMode() {
        this.param.setContentMode(XEasyPdfComponent.ContentMode.APPEND);
        return this;
    }

    /**
     * 开启内水印模式
     * @return 返回页面水印组件
     */
    public XEasyPdfDefaultWatermark enableInnerMode() {
        this.param.setContentMode(XEasyPdfComponent.ContentMode.PREPEND);
        return this;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page     pdf页面
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果需要初始化，则进行参数初始化
        if (this.param.isNeedInit()) {
            // 初始化水印参数
            this.param.init(document, page);
        }
        // 获取任务文档
        PDDocument target = document.getTarget();
        // 获取pdfBox页面列表
        List<PDPage> pageList = page.getParam().getPageList();
        // 遍历pdfBox页面列表
        for (PDPage pdPage : pageList) {
            // 执行画水印
            this.doDraw(target, pdPage);
        }
        // 获取新的pdfBox页面列表
        pageList = page.getParam().getNewPageList();
        // 遍历pdfBox页面列表
        for (PDPage pdPage : pageList) {
            // 执行画水印
            this.doDraw(target, pdPage);
        }
    }

    /**
     * 执行绘制
     * @param document pdfbox文档
     * @param pdPage pdfbox页面
     */
    @SneakyThrows
    private void doDraw(PDDocument document, PDPage pdPage) {
        // 获取页面高度
        float height = pdPage.getMediaBox().getHeight();
        // 获取页面宽度
        float width = pdPage.getMediaBox().getWidth();
        // 定义X轴起始坐标
        float beginX = 0F;
        // 定义Y轴起始坐标
        float beginY = height;
        // 初始化pdfBox扩展图形对象
        PDExtendedGraphicsState state = new PDExtendedGraphicsState();
        // 设置文本透明度
        state.setNonStrokingAlphaConstant(this.param.getAlpha());
        // 设置透明度标记
        state.setAlphaSourceFlag(true);
        // 设置融合模式
        state.setBlendMode(BlendMode.MULTIPLY);
        // 初始化内容流
        PDPageContentStream cs = new PDPageContentStream(
                document,
                pdPage,
                this.param.getContentMode().getMode(),
                true,
                true
        );
        // 设置图形参数
        cs.setGraphicsStateParameters(state);
        // 设置字体颜色
        cs.setNonStrokingColor(this.param.getFontColor());
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
            do {
                // 设置文本坐标
                cs.newLineAtOffset(beginX, beginY);
                // 文本输入
                cs.showText(this.param.getText());
                // 重置X轴起始坐标为X轴起始+文本间隔
                beginX = beginX + this.param.getWordSpace();
            }
            // 如果X轴起始坐标大于页面宽度，则结束循环
            while (!(beginX >= width));
            // 重置X轴起始坐标为0
            beginX = 0F;
            // 重置Y轴起始坐标为Y轴起始坐标-字体大小-行间距
            beginY = beginY - this.param.getFontSize() - this.param.getLeading();
        }
        // 结束文本写入
        cs.endText();
        // 关闭内容流
        cs.close();
    }
}
