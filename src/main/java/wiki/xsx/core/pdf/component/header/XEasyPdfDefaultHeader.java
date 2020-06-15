package wiki.xsx.core.pdf.component.header;

import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.line.XEasyPdfSolidSplitLine;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * pdf页眉组件
 * @author xsx
 * @date 2020/6/7
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
public class XEasyPdfDefaultHeader implements XEasyPdfHeader{

    /**
     * 页眉参数
     */
    private final XEasyPdfHeaderParam param = new XEasyPdfHeaderParam();

    /**
     * 有参构造
     * @param text 待写入文本
     */
    public XEasyPdfDefaultHeader(String text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param fontSize 字体大小
     * @param text 待写入文本
     */
    public XEasyPdfDefaultHeader(float fontSize, String text) {
        this.param.setText(text);
    }

    /**
     * 设置边距（上左右）
     * @param margin 边距
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置行间距
     * @param leading 行间距
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setLeading(float leading) {
        if (leading>0) {
            this.param.setLeading(leading);
        }
        return this;
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setFont(PDFont font) {
        this.param.setFont(font);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setFontColor(Color fontColor) {
        this.param.setFontColor(fontColor);
        return this;
    }

    /**
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setStyle(XEasyPdfTextStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 设置拆分后的待添加文本列表
     * @param splitTextList 拆分后的待添加文本列表
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setSplitTextList(List<String> splitTextList) {
        this.param.setSplitTextList(splitTextList);
        return this;
    }

    /**
     * 设置是否有分割线
     * @param hasSplitLine 是否有分割线
     * @return 返回页眉组件
     */
    public XEasyPdfDefaultHeader setHasSplitLine(boolean hasSplitLine) {
        this.param.setHasSplitLine(hasSplitLine);
        return this;
    }

    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 写入文本
        new XEasyPdfText(this.param.getText())
                .setFontPath(this.param.getFontPath())
                .setFont(this.param.getFont())
                .setFontSize(this.param.getFontSize())
                .setLeading(this.param.getLeading())
                .setFontColor(this.param.getFontColor())
                .setMarginLeft(this.param.getMarginLeft())
                .setMarginRight(this.param.getMarginRight())
                .setMarginTop(this.param.getMarginTop())
                .setSplitTextList(this.param.getSplitTextList())
                .setStyle(this.param.getStyle())
                .draw(document, page);
        // 如果带有分割线，则进行分割线绘制
        if (this.param.isHasSplitLine()) {
            // 定义分割线边距
            float margin = 5F;
            // 绘制分割线
            new XEasyPdfSolidSplitLine()
                    .setFontPath(this.param.getFontPath())
                    .setFont(this.param.getFont())
                    .setMarginLeft(margin)
                    .setMarginRight(margin)
                    .draw(document, page);
            // 绘制分割线
            new XEasyPdfSolidSplitLine()
                    .setFontPath(this.param.getFontPath())
                    .setFont(this.param.getFont())
                    .setMarginTop(1F)
                    .setMarginLeft(margin)
                    .setMarginRight(margin)
                    .draw(document, page);
        }
    }
}
