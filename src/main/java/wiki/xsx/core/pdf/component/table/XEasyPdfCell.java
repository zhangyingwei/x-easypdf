package wiki.xsx.core.pdf.component.table;

import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.component.rect.XEasyPdfRect;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;

import java.awt.*;
import java.io.IOException;

/**
 * pdf单元格组件
 * @author xsx
 * @date 2020/6/6
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
public class XEasyPdfCell {

    /**
     * pdf单元格参数
     */
    private final XEasyPdfCellParam param = new XEasyPdfCellParam();

    /**
     * 有参构造
     * @param width 宽度
     * @param height 高度
     */
    public XEasyPdfCell(float width, float height) {
        this.param.setWidth(width).setHeight(height);
    }

    /**
     * 有参构造
     * @param text 待写入文本
     */
    public XEasyPdfCell(String text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param text 待写入文本
     */
    public XEasyPdfCell(String fontPath, String text) {
        this.param.setFontPath(fontPath).setText(text);
    }

    /**
     * 有参构造
     * @param text 待写入文本
     * @param hasBorder 是否有边框
     */
    public XEasyPdfCell(String text, boolean hasBorder) {
        this.param.setText(text).setHasBorder(hasBorder);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param text 待写入文本
     * @param hasBorder 是否有边框
     */
    public XEasyPdfCell(String fontPath, String text, boolean hasBorder) {
        this.param.setFontPath(fontPath).setText(text).setHasBorder(hasBorder);
    }

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setWidth(float width) {
        this.param.setWidth(width+1F);
        return this;
    }

    /**
     * 设置高度
     * @param height 高度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setHeight(float height) {
        this.param.setHeight(height);
        return this;
    }

    /**
     * 设置是否有边框
     * @param hasBorder 是否有边框
     * @return 返回单元格组件
     */
    public XEasyPdfCell setHasBorder(boolean hasBorder) {
        this.param.setHasBorder(hasBorder);
        return this;
    }

    /**
     * 设置背景颜色
     * @param backgroundColor 背景颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBackgroundColor(Color backgroundColor) {
        this.param.setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 设置边框颜色（设置是否有边框为true时生效）
     * @param borderColor 边框颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBorderColor(Color borderColor) {
        this.param.setBorderColor(borderColor);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置文本边距（上下左右）
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setTextMargin(float margin) {
        this.param.setTextMarginLeft(margin).setTextMarginRight(margin).setTextMarginTop(margin).setTextMarginBottom(margin);
        return this;
    }

    /**
     * 设置文本左边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setTextMarginLeft(float margin) {
        this.param.setTextMarginLeft(margin);
        return this;
    }

    /**
     * 设置文本右边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setTextMarginRight(float margin) {
        this.param.setTextMarginRight(margin);
        return this;
    }

    /**
     * 设置文本上边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setTextMarginTop(float margin) {
        this.param.setTextMarginTop(margin);
        return this;
    }

    /**
     * 设置文本下边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfCell setTextMarginBottom(float margin) {
        this.param.setTextMarginBottom(margin);
        return this;
    }

    /**
     * 设置字体
     * @param fontPath 字体路径
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFont(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFontColor(Color fontColor) {
        this.param.setFontColor(fontColor);
        return this;
    }

    /**
     * 设置文本
     * @param text 待写入文本
     * @return 返回单元格组件
     */
    public XEasyPdfCell setText(String text) {
        this.param.setText(text);
        return this;
    }

    /**
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回单元格组件
     */
    public XEasyPdfCell setStyle(XEasyPdfTextStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 获取pdf单元格参数
     * @return 返回单元格参数
     */
    XEasyPdfCellParam getParam() {
        return param;
    }

    /**
     * 初始化
     * @param document pdf文档
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    void init(XEasyPdfDocument document, XEasyPdfRow row) throws IOException {
        // 初始化参数
        this.param.init(document, row);
        // 设置表格行高度 = 当前行单元格最大高度
        row.getParam().setHeight(Math.max(row.getParam().getHeight(), this.param.getHeight()));
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    void doDraw(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) throws IOException {
        // 如果带有边框，则进行写入边框
        if (this.param.isHasBorder()) {
            // 写入边框
            this.writeBorder(document, page, row);
        }
        // 如果待添加文本列表不为空，则进行写入文本
        if (this.param.getSplitTextList()!=null) {
            // 写入文本
            this.writeText(document, page, row);
        }
    }

    /**
     * 写入边框
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    private void writeBorder(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) throws IOException {
        new XEasyPdfRect(
                this.param.getWidth(),
                row.getParam().getHeight(),
                row.getParam().getBeginX(),
                row.getParam().getBeginY() - this.param.getMarginTop()
        ).setHasBorder(true)
        .setBackgroundColor(this.param.getBackgroundColor())
        .setBorderColor(this.param.getBorderColor())
        .setCheckPage(false)
        .setNewLine(false)
        .draw(document, page);
    }

    /**
     * 写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    private void writeText(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) throws IOException {
        new XEasyPdfText()
                .setFont(this.param.getFont())
                .setFontSize(this.param.getFontSize())
                .setFontColor(this.param.getFontColor())
                .setWidth(this.param.getWidth())
                .setHeight(row.getParam().getHeight())
                .setMarginLeft(this.param.getTextMarginLeft())
                .setMarginRight(this.param.getTextMarginRight())
                .setMarginTop(this.param.getTextMarginTop())
                .setMarginBottom(this.param.getTextMarginBottom())
                .setSplitTextList(this.param.getSplitTextList())
                .setStyle(this.param.getStyle())
                .setPosition(
                        row.getParam().getBeginX() + 1,
                        row.getParam().getBeginY() - this.param.getMarginTop() - this.param.getFontSize() + row.getParam().getHeight()

                ).draw(document, page);
    }
}
