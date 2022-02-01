package wiki.xsx.core.pdf.component.table;

import lombok.SneakyThrows;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.XEasyPdfLine;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.doc.XEasyPdfPositionStyle;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;

/**
 * pdf单元格组件
 * @author xsx
 * @date 2020/6/6
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
public class XEasyPdfCell {

    /**
     * pdf单元格参数
     */
    private final XEasyPdfCellParam param = new XEasyPdfCellParam();

    /**
     * 有参构造
     * @param width 宽度
     */
    public XEasyPdfCell(float width) {
        this.param.setWidth(Math.abs(width));
    }

    /**
     * 有参构造
     * @param width 宽度
     * @param height 高度
     */
    public XEasyPdfCell(float width, float height) {
        this.param.setWidth(Math.abs(width)).setHeight(Math.abs(height));
    }

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setWidth(float width) {
        this.param.setWidth(Math.abs(width));
        return this;
    }

    /**
     * 设置高度
     * @param height 高度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setHeight(float height) {
        this.param.setHeight(Math.abs(height));
        return this;
    }

    /**
     * 设置背景颜色
     * @param backgroundColor 背景颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBackgroundColor(Color backgroundColor) {
        if (backgroundColor!=null) {
            this.param.setBackgroundColor(backgroundColor);
        }
        return this;
    }

    /**
     * 边框宽度
     * @param lineWidth 宽度
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBorderWidth(float lineWidth) {
        this.param.setBorderWidth(Math.abs(lineWidth));
        return this;
    }

    /**
     * 设置边框颜色（开启边框时生效）
     * @param borderColor 边框颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setBorderColor(Color borderColor) {
        if (borderColor!=null) {
            this.param.setBorderColor(borderColor);
        }
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
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回单元格组件
     */
    public XEasyPdfCell setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFontSize(float fontSize) {
        this.param.setFontSize(Math.abs(fontSize));
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回单元格组件
     */
    public XEasyPdfCell setFontColor(Color fontColor) {
        if (fontColor!=null) {
            this.param.setFontColor(fontColor);
        }
        return this;
    }

    /**
     * 设置水平样式（居左、居中、居右）
     * @param style 样式
     * @return 返回单元格组件
     */
    public XEasyPdfCell setHorizontalStyle(XEasyPdfPositionStyle style) {
        if (style!=null) {
            if (style==XEasyPdfPositionStyle.LEFT||style==XEasyPdfPositionStyle.CENTER||style==XEasyPdfPositionStyle.RIGHT) {
                this.param.setHorizontalStyle(style);
            }else {
                throw new IllegalArgumentException("only set LEFT, CENTER or RIGHT style");
            }
        }
        return this;
    }

    /**
     * 设置垂直样式（居上、居中、居下）
     * @param style 样式
     * @return 返回单元格组件
     */
    public XEasyPdfCell setVerticalStyle(XEasyPdfPositionStyle style) {
        if (style!=null) {
            if (style==XEasyPdfPositionStyle.TOP||style==XEasyPdfPositionStyle.CENTER||style==XEasyPdfPositionStyle.BOTTOM) {
                this.param.setVerticalStyle(style);
            }else {
                throw new IllegalArgumentException("only set TOP, CENTER or BOTTOM style");
            }
        }
        return this;
    }

    /**
     * 开启上下左右居中
     * @return 返回单元格组件
     */
    public XEasyPdfCell enableCenterStyle() {
        this.param.setHorizontalStyle(XEasyPdfPositionStyle.CENTER).setVerticalStyle(XEasyPdfPositionStyle.CENTER);
        return this;
    }

    /**
     * 合并垂直单元格
     * @return 返回单元格组件
     */
    public XEasyPdfCell enableVerticalMerge() {
        this.param.setVerticalMerge(true);
        return this;
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
     * @param component 组件
     * @return 返回单元格组件
     */
    public XEasyPdfCell addContent(XEasyPdfComponent component) {
        if (component!=null) {
            this.param.setComponent(component);
        }
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
     * @param page pdf页面
     * @param row pdf表格行
     */
    float init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) {
        // 初始化参数
        this.param.init(document, page, row);
        // 获取行高
        Float rowHeight = this.param.getHeight();
        // 获取组件列表
        XEasyPdfComponent component = this.param.getComponent();
        // 如果组件属于文本组件，则初始化文本
        if (component instanceof XEasyPdfText) {
            // 获取文本组件
            XEasyPdfText text = (XEasyPdfText) component;
            // 初始化文本组件
            this.initText(text);
            // 如果行高为空，则重置行高
            if (rowHeight==null) {
                // 重置行高
                rowHeight = text.getHeight(document, page);
            }
        }
        // 如果组件属于图片组件，则初始化图片
        else if (component instanceof XEasyPdfImage) {
            // 获取图片组件
            XEasyPdfImage image = (XEasyPdfImage) component;
            // 初始化图片组件
            this.initImage(document, page, row, image);
            // 如果行高为空，则重置行高
            if (rowHeight==null) {
                // 重置行高
                rowHeight = Float.valueOf(image.getHeight(document, page));
            }
        }
        return rowHeight;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @param row pdf表格行
     */
    void doDraw(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table, XEasyPdfRow row) {
        // 如果列高未初始化，则进行初始化
        if (this.param.getHeight()==null) {
            // 初始化列高
            this.param.setHeight(row.getParam().getHeight());
        }
        // 写入边框
        this.writeBorder(document, page, table, row);
        // 填充背景色
        this.fillBackgroundColor(document, page, row);
        // 如果开启组件自动换行，则开启页面自动定位
        if (this.param.isNewLine()) {
            // 开启页面自动定位
            page.enablePosition();
        }
        // 获取当前页面Y轴起始坐标
        float pageY = page.getParam().getPageY();
        // 获取当前行X轴起始坐标
        float rowBeginX = row.getParam().getBeginX();
        // 获取组件
        XEasyPdfComponent component = this.param.getComponent();
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
        // 重置为当前行X轴原始坐标
        row.getParam().setBeginX(rowBeginX);
        // 重置为当前页面Y轴原始坐标
        page.getParam().setPageY(pageY);
        // 关闭页面自动定位
        page.disablePosition();
        // 重置字体为null
        this.param.setFont(null);
    }

    /**
     * 填充背景色
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     */
    private void fillBackgroundColor(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) {
        // 如果背景色不为白色，则填充背景色
        if (!Color.WHITE.equals(this.param.getBackgroundColor())) {
            // 获取边框宽度
            float borderWidth = this.param.getBorderWidth();
            // 绘制矩形填充背景色
            XEasyPdfHandler.Rect.build(
                    this.param.getWidth(),
                    this.param.getHeight()-borderWidth,
                    row.getParam().getBeginX()+borderWidth/2,
                    row.getParam().getBeginY()-this.param.getHeight()-this.param.getMarginTop()+borderWidth/2
            ).setContentMode(this.param.getContentMode())
                    .setBackgroundColor(this.param.getBackgroundColor())
                    .setBorderColor(this.param.getBackgroundColor())
                    .setNewLine(false)
                    .disableCheckPage()
                    .draw(document, page);
        }
    }

    /**
     * 写入边框
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     */
    @SneakyThrows
    private void writeBorder(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table, XEasyPdfRow row) {
        // 如果带有边框，则进行写入边框
        if (this.param.getHasBorder()) {
            // 构建单元格边框
            XEasyPdfCellBorder cellBorder = new XEasyPdfCellBorder().setDocument(document.getTarget())
                    .setPage(page.getLastPage())
                    .setContentMode(this.param.getContentMode().getMode())
                    .setWidth(this.param.getWidth())
                    .setHeight(this.param.getHeight())
                    .setBorderColor(this.param.getBorderColor())
                    .setBorderWidth(this.param.getBorderWidth())
                    .setBeginX(row.getParam().getBeginX())
                    .setBeginY(row.getParam().getBeginY()-this.param.getMarginTop());
            // 获取表格参数
            XEasyPdfTableParam tableParam = table.getParam();
            // 如果单元格边框颜色与表格边框颜色相同，则直接绘制边框
            if (this.param.getBorderColor().equals(tableParam.getBorderColor())) {
                // 绘制边框
                cellBorder.drawBorder();
            }
            // 否则添加单元格边框列表
            else {
                // 添加单元格边框列表
                tableParam.getCellBorderList().add(cellBorder);
            }
        }
    }

    /**
     * 写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param text pdf文本
     */
    private void writeText(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfText text) {
        // 设置定位及绘制
        text.setPosition(
                row.getParam().getBeginX(),
                this.initYForText(document, page, row, text)
        ).draw(document, page);
    }

    /**
     * 写入图片
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param image pdf图片
     */
    private void writeImage(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfImage image) {
        // 设置定位及绘制
        image.setPosition(
                row.getParam().getBeginX()+this.param.getBorderWidth()/2,
                this.initYForImage(document, page, row, image)
        ).draw(document, page);
    }

    /**
     * 写入分割线
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param line pdf线条
     */
    private void writeLine(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfLine line) {
        line.setContentMode(this.param.getContentMode())
                .setWidth(this.param.getWidth() - this.param.getBorderWidth() * 2)
                .setPosition(
                        row.getParam().getBeginX() + this.param.getBorderWidth() / 2,
                        page.getParam().getPageY() - row.getParam().getMarginTop() - line.getLineWidth() - this.param.getBorderWidth() / 2
                ).draw(document, page);
    }

    /**
     * 写入文本
     * @param text pdf文本
     */
    private void initText(XEasyPdfText text) {
        text.setContentMode(this.param.getContentMode())
            .enableChildComponent()
            .setWidth(this.param.getWidth())
            .setFontPath(this.param.getFontPath())
            .setDefaultFontStyle(this.param.getDefaultFontStyle())
            .setFontSize(this.param.getFontSize())
            .setFontColor(this.param.getFontColor())
            .setHorizontalStyle(this.param.getHorizontalStyle())
            .setVerticalStyle(this.param.getVerticalStyle());
    }

    /**
     * 写入图片
     * @param document pdf文档
     * @param page pdf页面
     * @param image pdf图片
     */
    private void initImage(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfImage image) {
        // 获取单元格高度
        Float height = this.param.getHeight();
        // 如果高度为空，则重置为行高
        if (height==null) {
            // 重置为行高
            height = row.getParam().getHeight();
        }
        // 如果仍然为空，则重置为图片高度
        if (height==null) {
            // 重置为图片高度
            height = Float.valueOf(image.getHeight(document, page));
        }
        // 设置图片参数
        image.setContentMode(this.param.getContentMode())
             .enableChildComponent()
             .setWidth(Math.min(image.getWidth(document, page), this.param.getWidth()) - this.param.getBorderWidth())
             .setHeight(Math.min(image.getHeight(document, page), height) - this.param.getBorderWidth())
             .setMaxWidth(this.param.getWidth() - this.param.getBorderWidth())
             .setMaxHeight(height - this.param.getBorderWidth())
             .setHorizontalStyle(this.param.getHorizontalStyle())
             .setVerticalStyle(this.param.getVerticalStyle());
    }

    /**
     * 初始化文本Y轴起始坐标
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param text pdf文本
     * @return 返回Y轴起始坐标
     */
    private float initYForText(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfText text) {
        // 获取文本高度
        float height = text.getHeight(document, page);
        // 定义Y轴起始坐标为页面Y轴起始坐标
        float y = page.getParam().getPageY();
        // 如果垂直样式为居上，则重置Y轴起始坐标为Y轴起始坐标-字体大小-边框宽度-行上边距
        if (this.param.getVerticalStyle()==XEasyPdfPositionStyle.TOP) {
            // 重置Y轴起始坐标为Y轴起始坐标-字体大小-边框宽度-行上边距
            y = y - this.param.getFontSize() - this.param.getBorderWidth() - row.getParam().getMarginTop();
            return y;
        }
        // 如果垂直样式为居中，则重置Y轴起始坐标为Y轴起始坐标-字体大小-(单元格高度-文本高度)/2-行上边距
        if (this.param.getVerticalStyle()== XEasyPdfPositionStyle.CENTER) {
            // 重置Y轴起始坐标为Y轴起始坐标-字体大小-(单元格高度-文本高度)/2-行上边距
            y = y - this.param.getFontSize() - (this.param.getHeight() - height) / 2 - row.getParam().getMarginTop();
            return y;
        }
        // 如果垂直样式为居下，则重置Y轴起始坐标为Y轴起始坐标-字体大小-单元格高度+文本高度-行上边距
        if (this.param.getVerticalStyle()==XEasyPdfPositionStyle.BOTTOM) {
            // 重置Y轴起始坐标为Y轴起始坐标-字体大小-单元格高度+文本高度-行上边距
            y = y - this.param.getFontSize() - this.param.getHeight() + height - row.getParam().getMarginTop();
        }
        return y;
    }

    /**
     * 初始化图片Y轴起始坐标
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     * @param image pdf图片
     * @return 返回Y轴起始坐标
     */
    private float initYForImage(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row, XEasyPdfImage image) {
        // 获取图片高度
        float height = image.getHeight(document, page);
        // 定义Y轴起始坐标为页面Y轴起始坐标
        float y = page.getParam().getPageY();
        // 如果图片高度等于单元格高度-边框宽度或垂直样式为居上，则重置Y轴起始坐标为Y轴起始坐标-图片高度-边框宽度/2-行上边距
        if (height==this.param.getHeight()-this.param.getBorderWidth()||this.param.getVerticalStyle()==XEasyPdfPositionStyle.TOP) {
            // 重置Y轴起始坐标为Y轴起始坐标-图片高度-边框宽度/2-行上边距
            y = y - height - this.param.getBorderWidth() / 2 - row.getParam().getMarginTop();
            return y;
        }
        // 如果垂直样式为居中，则重置Y轴起始坐标为Y轴起始坐标-图片高度-(单元格高度-图片高度)/2-行上边距
        if (this.param.getVerticalStyle()== XEasyPdfPositionStyle.CENTER) {
            // 重置Y轴起始坐标为Y轴起始坐标-图片高度-(单元格高度-图片高度)/2-行上边距
            y = y - height - (this.param.getHeight() - height) / 2 - row.getParam().getMarginTop();
            return y;
        }
        // 如果垂直样式为居下，则重置Y轴起始坐标为Y轴起始坐标-单元格高度+边框宽度/2-行上边距
        if (this.param.getVerticalStyle()==XEasyPdfPositionStyle.BOTTOM) {
            // 重置Y轴起始坐标为Y轴起始坐标-单元格高度+边框宽度/2-行上边距
            y = y - this.param.getHeight() + this.param.getBorderWidth() / 2 - row.getParam().getMarginTop();
        }
        return y;
    }
}
