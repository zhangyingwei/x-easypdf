package wiki.xsx.core.pdf.component.footer;

import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * pdf页脚组件
 * @author xsx
 * @date 2020/6/7
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
public class XEasyPdfDefaultFooter implements XEasyPdfFooter {

    /**
     * 页脚参数
     */
    private final XEasyPdfFooterParam param = new XEasyPdfFooterParam();

    /**
     * 有参构造
     * @param text 待写入文本
     */
    public XEasyPdfDefaultFooter(String text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param fontSize 字体大小
     * @param text 待写入文本
     */
    public XEasyPdfDefaultFooter(float fontSize, String text) {
        this.param.setText(text);
    }

    /**
     * 设置边距（左右下）
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置行间距
     * @param leading 行间距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setLeading(float leading) {
        if (leading>0) {
            this.param.setLeading(leading);
        }
        return this;
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setFont(PDFont font) {
        this.param.setFont(font);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setFontColor(Color fontColor) {
        this.param.setFontColor(fontColor);
        return this;
    }

    /**
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setStyle(XEasyPdfTextStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 设置拆分后的待添加文本列表
     * @param splitTextList 拆分后的待添加文本列表
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setSplitTextList(List<String> splitTextList) {
        this.param.setSplitTextList(splitTextList);
        return this;
    }
    
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化参数
        this.param.init(document, page);
        // 关闭页面自动重置定位
        page.disablePosition();
        // 写入文本
        new XEasyPdfText(this.param.getText())
                .setFontPath(this.param.getFontPath())
                .setFont(this.param.getFont())
                .setFontSize(this.param.getFontSize())
                .setLeading(this.param.getLeading())
                .setFontColor(this.param.getFontColor())
                .setMarginLeft(this.param.getMarginLeft())
                .setMarginRight(this.param.getMarginRight())
                .setMarginBottom(this.param.getMarginBottom())
                .setSplitTextList(this.param.getSplitTextList())
                .setStyle(this.param.getStyle())
                .setPosition(this.param.getBeginX(), this.param.getBeginY())
                .setCheckPage(false)
                .draw(document, page);
        // 开启页面自动重置定位
        page.enablePosition();
    }

    @Override
    public float getHeight() {
        return this.param.getHeight();
    }
}
