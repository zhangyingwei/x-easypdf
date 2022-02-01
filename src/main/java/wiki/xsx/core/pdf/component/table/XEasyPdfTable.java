package wiki.xsx.core.pdf.component.table;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.doc.XEasyPdfPositionStyle;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * pdf表格组件
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
public class XEasyPdfTable implements XEasyPdfComponent {

    /**
     * pdf表格参数
     */
    private final XEasyPdfTableParam param = new XEasyPdfTableParam();

    /**
     * 有参构造
     * @param rows pdf表格行
     */
    public XEasyPdfTable(XEasyPdfRow...rows) {
        if (rows!=null) {
            Collections.addAll(this.param.getRows(), rows);
        }
    }

    /**
     * 有参构造
     * @param rowList pdf表格行列表
     */
    public XEasyPdfTable(List<XEasyPdfRow> rowList) {
        if (rowList!=null) {
            this.param.getRows().addAll(rowList);
        }
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回表格组件
     */
    public XEasyPdfTable setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回表格组件
     */
    public XEasyPdfTable setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回表格组件
     */
    public XEasyPdfTable setFontSize(float fontSize) {
        this.param.setFontSize(Math.abs(fontSize));
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回表格组件
     */
    public XEasyPdfTable setFontColor(Color fontColor) {
        if (fontColor!=null) {
            this.param.setFontColor(fontColor);
        }
        return this;
    }

    /**
     * 边框宽度
     * @param lineWidth 宽度
     * @return 返回表格组件
     */
    public XEasyPdfTable setBorderWidth(float lineWidth) {
        this.param.setBorderWidth(Math.abs(lineWidth));
        return this;
    }

    /**
     * 设置边框颜色（开启边框时生效）
     * @param borderColor 边框颜色
     * @return 返回表格组件
     */
    public XEasyPdfTable setBorderColor(Color borderColor) {
        if (borderColor!=null) {
            this.param.setBorderColor(borderColor);
        }
        return this;
    }

    /**
     * 设置背景颜色
     * @param backgroundColor 背景颜色
     * @return 返回表格组件
     */
    public XEasyPdfTable setBackgroundColor(Color backgroundColor) {
        if (backgroundColor!=null) {
            this.param.setBackgroundColor(backgroundColor);
        }
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回表格组件
     */
    public XEasyPdfTable setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回表格组件
     */
    public XEasyPdfTable setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回表格组件
     */
    public XEasyPdfTable setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置水平样式（居左、居中、居右）
     * @param style 样式
     * @return 返回表格组件
     */
    public XEasyPdfTable setHorizontalStyle(XEasyPdfPositionStyle style) {
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
     * @return 返回表格组件
     */
    public XEasyPdfTable setVerticalStyle(XEasyPdfPositionStyle style) {
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
     * @return 返回表格组件
     */
    public XEasyPdfTable enableCenterStyle() {
        this.param.setHorizontalStyle(XEasyPdfPositionStyle.CENTER).setVerticalStyle(XEasyPdfPositionStyle.CENTER);
        return this;
    }

    /**
     * 关闭边框
     * @return 返回表格组件
     */
    public XEasyPdfTable disableBorder() {
        this.param.setHasBorder(false);
        return this;
    }

    /**
     * 开启自动表头（分页自动添加表头）
     * @return 返回表格组件
     */
    public XEasyPdfTable enableAutoTitle() {
        this.param.setIsAutoTitle(true);
        return this;
    }

    /**
     * 设置坐标
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     * @return 返回表格组件
     */
    @Override
    public XEasyPdfTable setPosition(float beginX, float beginY) {
        this.param.setBeginX(beginX).setBeginY(beginY);
        return this;
    }

    /**
     * 设置宽度（无效）
     * @param width 宽度
     * @return 返回表格组件
     */
    @Deprecated
    @Override
    public XEasyPdfTable setWidth(float width) {
        return this;
    }

    /**
     * 设置高度（无效）
     * @param height 高度
     * @return 返回表格组件
     */
    @Deprecated
    @Override
    public XEasyPdfTable setHeight(float height) {
        return this;
    }

    /**
     * 设置内容模式
     * @param mode 内容模式
     * @return 返回表格组件
     */
    @Override
    public XEasyPdfTable setContentMode(ContentMode mode) {
        if (mode!=null) {
            this.param.setContentMode(mode);
        }
        return this;
    }

    /**
     * 设置表头行
     * @param row pdf表格行
     * @return 返回表格组件
     */
    public XEasyPdfTable setTileRow(XEasyPdfRow row) {
        this.param.setTitleRow(row);
        return this;
    }

    /**
     * 添加表格行
     * @param rows pdf表格行
     * @return 返回表格组件
     */
    public XEasyPdfTable addRow(XEasyPdfRow...rows) {
        if (rows!=null) {
            Collections.addAll(this.param.getRows(), rows);
        }
        return this;
    }

    /**
     * 添加表格行
     * @param rowList pdf表格行列表
     * @return 返回表格组件
     */
    public XEasyPdfTable addRow(List<XEasyPdfRow> rowList) {
        if (rowList!=null) {
            this.param.getRows().addAll(rowList);
        }
        return this;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) {
        // 关闭页面自动重置定位
        page.disablePosition();
        // 初始化参数
        this.param.init(document, page);
        // 如果X轴起始坐标不为空，则设置页面X轴起始坐标
        if (this.param.getBeginX()!=null) {
            // 设置页面X轴起始坐标 = 表格X轴起始坐标
            page.getParam().setPageX(this.param.getBeginX());
        }
        // 如果Y轴起始坐标不为空，则设置页面Y轴起始坐标
        if (this.param.getBeginY()!=null) {
            // 设置页面Y轴起始坐标 = 表格Y轴起始坐标 - 上边距
            page.getParam().setPageY(this.param.getBeginY() - this.param.getMarginTop());
        }else {
            // 设置页面Y轴起始坐标 = 页面Y轴起始坐标 - 上边距
            page.getParam().setPageY(page.getParam().getPageY()==null?page.getLastPage().getMediaBox().getHeight() - this.param.getMarginTop() : page.getParam().getPageY() - this.param.getMarginTop());
        }
        // 获取表头行
        XEasyPdfRow titleRow = this.param.getTitleRow();
        // 如果表头行不为空，则绘制表头行
        if (titleRow!=null) {
            // 绘制表头行
            titleRow.doDraw(document, page, this);
        }
        // 获取表格行列表
        List<XEasyPdfRow> rows = this.param.getRows();
        // 遍历表格行列表
        for (XEasyPdfRow row : rows) {
            // 绘制表格行
            row.doDraw(document, page, this);
        }
        // 获取单元格边框列表
        List<XEasyPdfCellBorder> cellBorderList = this.param.getCellBorderList();
        // 如果单元格边框列表不为空，则绘制单元格边框
        if (!cellBorderList.isEmpty()) {
            // 遍历单元格边框列表
            for (XEasyPdfCellBorder cellBorder : cellBorderList) {
                // 绘制单元格边框
                cellBorder.drawBorder();
            }
            // 重置单元格边框列表
            cellBorderList.clear();
        }
        // 开启页面自动重置定位
        page.enablePosition();
        // 完成标记
        this.param.setDraw(true);
        // 重置字体为null
        this.param.setFont(null);
    }

    /**
     * 是否完成绘制
     * @return 返回布尔值，完成为true，未完成为false
     */
    @Override
    public boolean isDraw() {
        return this.param.isDraw();
    }

    /**
     * 获取pdf表格参数
     * @return 返回表格参数
     */
    XEasyPdfTableParam getParam() {
        return this.param;
    }
}
