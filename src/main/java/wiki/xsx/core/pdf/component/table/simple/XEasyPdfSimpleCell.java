package wiki.xsx.core.pdf.component.table.simple;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * pdf单元格组件（简单表格）
 * @author xsx
 * @date 2021/4/25
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
public class XEasyPdfSimpleCell {

    /**
     * pdf单元格参数
     */
    private final XEasyPdfSimpleCellParam param = new XEasyPdfSimpleCellParam();

    /**
     * 有参构造
     * @param width 宽度
     */
    public XEasyPdfSimpleCell(float width) {
        this.param.setWidth(width);
    }

    /**
     * 有参构造
     * @param width 宽度
     * @param height 高度
     */
    public XEasyPdfSimpleCell(float width, float height) {
        this.param.setWidth(width).setHeight(height);
    }

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setWidth(float width) {
        this.param.setWidth(width+1F);
        return this;
    }

    /**
     * 设置高度
     * @param height 高度
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setHeight(float height) {
        this.param.setHeight(height);
        return this;
    }

    /**
     * 设置背景颜色
     * @param backgroundColor 背景颜色
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setBackgroundColor(Color backgroundColor) {
        this.param.setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 设置边框颜色（开启边框时生效）
     * @param borderColor 边框颜色
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setBorderColor(Color borderColor) {
        this.param.setBorderColor(borderColor);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setFontColor(Color fontColor) {
        this.param.setFontColor(fontColor);
        return this;
    }

    /**
     * 设置表格样式（居左、居中、居右）
     * @param style 样式
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell setStyle(XEasyPdfTableStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 开启组件自动换行
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell enableNewLine() {
        this.param.setNewLine(true);
        return this;
    }

    /**
     * 关闭组件自动换行
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell disableNewLine() {
        this.param.setNewLine(false);
        return this;
    }

    /**
     * 添加内容
     * @param components 组件列表
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell addContent(XEasyPdfComponent ...components) {
        Collections.addAll(this.param.getComponentList(), components);
        return this;
    }

    /**
     * 添加内容
     * @param componentList 组件列表
     * @return 返回单元格组件
     */
    public XEasyPdfSimpleCell addContent(List<XEasyPdfComponent> componentList) {
        this.param.getComponentList().addAll(componentList);
        return this;
    }

    /**
     * 获取pdf单元格参数
     * @return 返回单元格参数
     */
    XEasyPdfSimpleCellParam getParam() {
        return param;
    }

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    float init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleRow row) throws IOException {
        // 初始化参数
        this.param.init(document, page, row);
        // 定义行高
        float rowHeight = 0F;
        // 获取组件列表
        List<XEasyPdfComponent> componentList = this.param.getComponentList();
        // 遍历组件列表
        for (XEasyPdfComponent component : componentList) {
            // 如果组件属于文本组件，则初始化文本
            if (component instanceof XEasyPdfText) {
                // 获取文本组件
                XEasyPdfText text = (XEasyPdfText) component;
                // 初始化文本组件
                this.initText(document, page, row, text);
                // 计算行高
                rowHeight += text.getHeight(document, page, text.getMarginLeft(), text.getMarginRight());
            }
            // 如果组件属于图片组件，则初始化图片
            else if (component instanceof XEasyPdfImage) {
                // 获取图片组件
                XEasyPdfImage image = (XEasyPdfImage) component;
                // 初始化图片组件
                this.initImage(document, page, row, (XEasyPdfImage) component);
                // 计算行高
                rowHeight += image.getHeight(document, page);
            }
        }
        return rowHeight;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    void doDraw(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleRow row) throws IOException {
        // 如果列高未初始化，则进行初始化
        if (this.param.getHeight()==null) {
            // 初始化列高
            this.param.setHeight(row.getParam().getHeight());
        }
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
            if (component instanceof XEasyPdfText) {
                XEasyPdfText text = (XEasyPdfText) component;
                text.setPosition(
                        row.getParam().getBeginX() + this.param.getBorderWidth(),
                        page.getParam().getPageY() - row.getParam().getMarginTop() - text.getMarginTop() - this.param.getFontSize() + this.param.getBorderWidth()
                ).draw(document, page);
            }
            else if(component instanceof XEasyPdfImage) {
                XEasyPdfImage image = (XEasyPdfImage) component;
                image.setPosition(
                        row.getParam().getBeginX() + this.param.getBorderWidth() / 2,
                        page.getParam().getPageY() - row.getParam().getMarginTop() - image.getMarginTop() - image.getHeight(document, page) - this.param.getBorderWidth() / 2
                ).draw(document, page);
            }
        }
        // 重置为当前行X轴原始坐标
        row.getParam().setBeginX(rowBeginX);
        // 重置为当前页面Y轴原始坐标
        page.getParam().setPageY(pageY);
        // 关闭页面自动定位
        page.disablePosition();
        // 字体路径不为空，说明该组件设置字体，则直接进行字体关联
        if (this.param.getFontPath()!=null&&this.param.getFontPath().length()>0) {
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
    private void writeBorder(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleRow row) throws IOException {
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
     * 初始化文本
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param text pdf文本
     */
    void initText(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleRow row, XEasyPdfText text) {
        float width = this.param.getWidth() - this.param.getBorderWidth() * 2;
        text.setContentMode(this.param.getContentMode())
            .setWidth(width)
            .setFontPath(this.param.getFontPath())
            .setDefaultFontStyle(this.param.getDefaultFontStyle())
            .setFontSize(this.param.getFontSize())
            .setFontColor(this.param.getFontColor())
            .setStyle(text.isUseSelfStyle()?text.getStyle():this.param.getStyle().getTextStyle())
            .enableChildComponent();
    }

    /**
     * 初始化图片
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param image pdf图片
     * @throws IOException IO异常
     */
    void initImage(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleRow row, XEasyPdfImage image) throws IOException {
        float width = image.getWidth(document, page);
        float height = image.getHeight(document, page);
        image.setContentMode(this.param.getContentMode())
             .setWidth(width - this.param.getBorderWidth() * 2)
             .setMaxWidth(this.param.getWidth() - this.param.getBorderWidth() * 2)
             .setHeight(height - this.param.getBorderWidth() * 2)
             .setStyle(image.isUseSelfStyle()?image.getStyle():this.param.getStyle().getImageStyle());
    }
}
