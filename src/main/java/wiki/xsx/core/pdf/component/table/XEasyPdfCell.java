package wiki.xsx.core.pdf.component.table;

import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.XEasyPdfLine;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * pdf单元格组件
 * @author xsx
 * @date 2020/6/6
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
     * 设置宽度
     * @param width 宽度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setWidth(float width) {
        this.param.setWidth(width);
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
     * 设置背景颜色
     * @param backgroundColor 背景颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBackgroundColor(Color backgroundColor) {
        this.param.setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 设置边框颜色（开启边框时生效）
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
     * 开启边框
     * @return 返回单元格组件
     */
    public XEasyPdfCell enableBorder() {
        this.param.setHasBorder(true);
        return this;
    }

    /**
     * 关闭边框
     * @return 返回单元格组件
     */
    public XEasyPdfCell disableBorder() {
        this.param.setHasBorder(false);
        return this;
    }

    /**
     * 边框宽度
     * @param lineWidth 宽度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBorderWidth(float lineWidth) {
        this.param.setBorderWidth(lineWidth);
        return this;
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回单元格组件
     */
    @Deprecated
    public XEasyPdfCell setFont(PDFont font) {
        this.param.setFont(font);
        this.param.setFontPath("");
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
     * 设置表格样式（居左、居中、居右）
     * @param style 样式
     * @return 返回单元格组件
     */
    public XEasyPdfCell setStyle(XEasyPdfTableStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 获取文档字体
     * @return 返回pdfBox字体
     */
    @Deprecated
    public PDFont getFont() {
        return this.param.getFont();
    }

    /**
     * 开启组件自动换行
     * @return 返回单元格组件
     */
    public XEasyPdfCell enableNewLine() {
        this.param.setNewLine(true);
        return this;
    }

    /**
     * 关闭组件自动换行
     * @return 返回单元格组件
     */
    public XEasyPdfCell disableNewLine() {
        this.param.setNewLine(false);
        return this;
    }

    /**
     * 添加内容
     * @param components 组件列表
     * @return 返回单元格组件
     */
    public XEasyPdfCell addContent(XEasyPdfComponent ...components) {
        Collections.addAll(this.param.getComponentList(), components);
        return this;
    }

    /**
     * 添加内容
     * @param componentList 组件列表
     * @return 返回单元格组件
     */
    public XEasyPdfCell addContent(List<XEasyPdfComponent> componentList) {
        this.param.getComponentList().addAll(componentList);
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
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    void doDraw(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) throws IOException {
        // 初始化参数
        this.param.init(document, page, row);
        // 如果带有边框，则进行写入边框
        if (this.param.isHasBorder()) {
            // 写入边框
            this.writeBorder(document, page, row);
        }
        // 获取组件列表
        List<XEasyPdfComponent> componentList = this.param.getComponentList();
        // 如果开启组件自动换行，则开启页面自动定位
        if (this.param.isNewLine()) {
            // 开启页面自动定位
            page.enablePosition();
        }
        // 获取当前页面Y轴起始坐标
        float pageY = page.getParam().getPageY();
        // 获取当前行X轴起始坐标
        float rowBeginX = row.getParam().getBeginX();
        // 遍历组件列表
        for (XEasyPdfComponent component : componentList) {
            // 如果组件属于文本组件，则写入文本
            if (component instanceof XEasyPdfText) {
                // 写入文本
                this.writeText(document, page, row, (XEasyPdfText) component);
            }
            // 如果组件属于图片组件，则写入图片
            else if (component instanceof XEasyPdfImage) {
                // 写入图片
                this.writeImage(document, page, row, (XEasyPdfImage) component);
            }
            // 如果组件属于线条组件，则写入线条
            else if (component instanceof XEasyPdfLine) {
                // 写入线条
                this.writeLine(document, page, row, (XEasyPdfLine) component);
            }
            // TODO 后续有需要，再加入其他组件
        }
        // 重置为当前行X轴原始坐标
        row.getParam().setBeginX(rowBeginX);
        // 重置为当前页面Y轴原始坐标
        page.getParam().setPageY(pageY);
        // 关闭页面自动定位
        page.disablePosition();
        // 字体路径不为空，说明该组件设置字体，则直接进行字体关联
        if (this.param.getFontPath()!=null&&this.param.getFontPath().length()>0) {
            // 关联字体
            this.param.getFont().subset();
            // 重置字体为null
            this.param.setFont(null);
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
        XEasyPdfHandler.Rect.build(this.param.getWidth(), this.param.getHeight(), row.getParam().getBeginX(), row.getParam().getBeginY())
                .setContentMode(this.param.getContentMode())
                .setBackgroundColor(this.param.getBackgroundColor())
                .setBorderColor(this.param.getBorderColor())
                .setBorderWidth(this.param.getBorderWidth())
                .setNewLine(false)
                .setHasBorder(true)
                .disableCheckPage()
                .draw(document, page);
    }

    /**
     * 写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param text pdf文本
     * @throws IOException IO异常
     */
    private void writeText(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfText text) throws IOException {
        float width = this.param.getWidth() - this.param.getBorderWidth() * 2;
        float height = this.param.getHeight() - this.param.getBorderWidth() * 2;
        text.setContentMode(this.param.getContentMode())
            .enableChildComponent()
            .setWidth(width)
            .setHeight(height)
            .setFont(this.param.getFont())
            .setFontSize(this.param.getFontSize())
            .setFontColor(this.param.getFontColor())
            .setStyle(text.isUseSelfStyle()?text.getStyle():this.param.getStyle().getTextStyle())
            .setPosition(
                        row.getParam().getBeginX() + this.param.getBorderWidth(),
                        page.getParam().getPageY() - row.getParam().getMarginTop() - text.getMarginTop() - this.param.getFontSize() + this.param.getBorderWidth()
            ).draw(document, page);
    }

    /**
     * 写入图片
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param image pdf图片
     * @throws IOException IO异常
     */
    private void writeImage(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfImage image) throws IOException {
        float width = Math.min(image.getWidth(document, page), this.param.getWidth()) - this.param.getBorderWidth();
        float height = Math.min(image.getHeight(document, page), this.param.getHeight()) - this.param.getBorderWidth();
        image.setContentMode(this.param.getContentMode())
             .setWidth(width - this.param.getBorderWidth() * 2)
             .setMaxWidth(this.param.getWidth() - this.param.getBorderWidth() * 2)
             .setHeight(height - this.param.getBorderWidth() * 2)
             .setStyle(image.isUseSelfStyle()?image.getStyle():this.param.getStyle().getImageStyle())
             .setPosition(
                    row.getParam().getBeginX() + this.param.getBorderWidth() / 2,
                    page.getParam().getPageY() - row.getParam().getMarginTop() - image.getMarginTop() - image.getHeight(document, page) - this.param.getBorderWidth() / 2
             ).draw(document, page);
    }

    /**
     * 写入分割线
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param line pdf线条
     * @throws IOException IO异常
     */
    private void writeLine(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfLine line) throws IOException {
        line.setContentMode(this.param.getContentMode())
                .setWidth(this.param.getWidth() - this.param.getBorderWidth() * 2)
                .setPosition(
                        row.getParam().getBeginX() + this.param.getBorderWidth() / 2,
                        page.getParam().getPageY() - row.getParam().getMarginTop() - line.getLineWidth() - this.param.getBorderWidth() / 2
                ).draw(document, page);
    }
}
