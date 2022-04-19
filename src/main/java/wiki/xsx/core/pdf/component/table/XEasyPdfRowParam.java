package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.doc.XEasyPdfPositionStyle;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf表格行组件参数
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
@Data
@Accessors(chain = true)
class XEasyPdfRowParam implements Serializable {

    private static final long serialVersionUID = -8085463000293380631L;

    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode;
    /**
     * 是否重置上下文
     */
    private Boolean isResetContext;
    /**
     * 单元格列表
     */
    private List<XEasyPdfCell> cells = new ArrayList<>(10);
    /**
     * 是否带有边框
     */
    private Boolean hasBorder;
    /**
     * 背景颜色
     */
    private Color backgroundColor;
    /**
     * 边框颜色
     */
    private Color borderColor;
    /**
     * 边框宽度
     */
    private Float borderWidth;
    /**
     * 左边距
     */
    private Float marginLeft;
    /**
     * 上边距
     */
    private Float marginTop = 0F;
    /**
     * 行高
     */
    private Float height;
    /**
     * X轴起始坐标
     */
    private Float beginX;
    /**
     * Y轴起始坐标
     */
    private Float beginY;
    /**
     * 默认字体样式
     */
    private XEasyPdfDefaultFontStyle defaultFontStyle;
    /**
     * 字体路径
     */
    private String fontPath;
    /**
     * 字体
     */
    private PDFont font;
    /**
     * 字体大小
     */
    private Float fontSize;
    /**
     * 字体颜色
     */
    private Color fontColor;
    /**
     * 表格样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfPositionStyle horizontalStyle;
    /**
     * 表格样式（居上、居中、居下）
     * 默认居上
     */
    private XEasyPdfPositionStyle verticalStyle;
    /**
     * 分页标识
     */
    private Boolean isPaging = false;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @param row pdf表格行
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table, XEasyPdfRow row) {
        if (!this.cells.isEmpty()) {
            // 获取表格参数
            XEasyPdfTableParam tableParam = table.getParam();
            // 如果边框标记为空，则初始化边框标记
            if (this.hasBorder==null) {
                // 初始化边框标记
                this.hasBorder = tableParam.getHasBorder();
            }
            // 如果开启边框，则初始化边框宽度
            if (this.hasBorder) {
                // 如果边框宽度为空，则初始化边框宽度
                if (this.borderWidth==null) {
                    // 初始化边框宽度
                    this.borderWidth = tableParam.getBorderWidth();
                }
            }
            // 如果内容模式未初始化，则初始化为页面内容模式
            if (this.contentMode==null) {
                // 初始化为页面内容模式
                this.contentMode = tableParam.getContentMode();
            }
            // 如果是否重置上下文未初始化，则初始化为页面是否重置上下文
            if (this.isResetContext==null) {
                // 初始化为页面是否重置上下文
                this.isResetContext = tableParam.getIsResetContext();
            }
            // 如果默认字体未初始化，则初始化为页面默认字体
            if (this.defaultFontStyle==null) {
                // 初始化为页面默认字体
                this.defaultFontStyle = tableParam.getDefaultFontStyle();
            }
            // 如果字体路径未初始化，则初始化为默认字体路径
            if (this.fontPath==null) {
                // 初始化为默认字体路径
                this.fontPath = this.defaultFontStyle.getPath();
            }
            // 初始化字体
            this.font = XEasyPdfFontUtil.getFont(document, this.fontPath, tableParam.getFont());
            // 如果字体大小未初始化，则进行初始化
            if (this.fontSize==null) {
                // 初始化字体大小
                this.fontSize = tableParam.getFontSize();
            }
            // 如果字体颜色未初始化，则进行初始化
            if (this.fontColor==null) {
                // 初始化字体颜色
                this.fontColor = tableParam.getFontColor();
            }
            // 如果边框宽度未初始化，则进行初始化
            if (this.borderWidth==null) {
                // 初始化边框宽度
                this.borderWidth = tableParam.getBorderWidth();
            }
            // 如果边框颜色未初始化，则进行初始化
            if (this.borderColor==null) {
                // 初始化边框颜色
                this.borderColor = tableParam.getBorderColor();
            }
            // 如果背景颜色未初始化，则进行初始化
            if (this.backgroundColor==null) {
                // 初始化背景颜色
                this.backgroundColor = tableParam.getBackgroundColor();
            }
            // 如果水平样式未初始化，则进行初始化
            if (this.horizontalStyle==null) {
                // 初始化水平样式
                this.horizontalStyle = tableParam.getHorizontalStyle();
            }
            // 如果垂直样式未初始化，则进行初始化
            if (this.verticalStyle==null) {
                // 初始化垂直样式
                this.verticalStyle = tableParam.getVerticalStyle();
            }
            // 定义行高
            float rowHeight = 0F;
            // 遍历单元格列表
            for (XEasyPdfCell cell : this.cells) {
                // 如果单元格开启垂直合并，则跳过
                if (cell.getParam().getIsVerticalMerge()) {
                    // 跳过
                    continue;
                }
                // 初始化行高
                rowHeight = Math.max(rowHeight, cell.init(document, page, row));
            }
            // 如果行高未初始化，则进行初始化
            if (this.height==null) {
                // 初始化行高
                this.height = rowHeight;
            }
            // 初始化Y轴起始坐标 = 当前Y轴起始坐标
            this.beginY = this.checkPage(document, page, table);
            // 如果X轴起始坐标为初始化，则进行初始化
            if (this.beginX==null) {
                // 如果表格X轴起始坐标不为空，则重置为表格X轴起始坐标
                if (tableParam.getBeginX()!=null) {
                    // 初始化X轴起始坐标 = 表格X轴起始坐标
                    this.beginX = tableParam.getBeginX();
                }
                // 否则重置为左边距
                else {
                    // 如果左边距不为空，则X轴起始坐标 = 左边距
                    if (this.marginLeft!=null) {
                        // 初始化X轴起始坐标 = 左边距
                        this.beginX = this.marginLeft;
                    }else {
                        // 初始化X轴起始坐标 = 表格左边距
                        this.beginX = tableParam.getMarginLeft();
                    }
                }
            }
        }
    }

    /**
     * 分页检查
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @return 返回Y轴起始坐标
     */
    private float checkPage(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table) {
        // 定义页脚高度
        float footerHeight = 0F;
        // 如果允许添加页脚，且页脚不为空则初始化页脚高度
        if (page.isAllowFooter()&&page.getFooter()!=null) {
            // 初始化页脚高度
            footerHeight = page.getFooter().getHeight(document, page);
        }
        // 获取当前页面Y轴起始坐标
        Float pageY = page.getPageY();
        // 获取当前Y轴起始坐标 = 当前页面Y轴起始坐标 - 上边距
        float currentY = pageY - this.marginTop;
        // 如果未设置分页标识，则重置分页标识
        if (!this.isPaging) {
            // 重置分页标识为当前Y轴起始坐标-页脚高度-行高小于表格下边距
            this.isPaging = currentY - footerHeight - this.height < table.getParam().getMarginBottom();
            // 如果分页标识需要分页，则进行分页
            if (this.isPaging) {
                // 分页
                this.paging(document, page, table);
                // 获取当前页面Y轴起始坐标
                pageY = page.getPageY();
                // 获取当前Y轴起始坐标 = 当前页面Y轴起始坐标 - 上边距
                currentY = pageY - this.marginTop;
            }
        }
        return currentY;
    }

    /**
     * 分页
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     */
    private void paging(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table) {
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 获取表格参数
        XEasyPdfTableParam tableParam = table.getParam();
        // 获取单元格边框列表
        List<XEasyPdfCellBorder> cellBorderList = tableParam.getCellBorderList();
        // 如果单元格边框列表不为空，则绘制单元格边框
        if (!cellBorderList.isEmpty()) {
            // 遍历单元格边框列表
            for (XEasyPdfCellBorder cellBorder : cellBorderList) {
                // 绘制单元格边框
                cellBorder.drawBorder();
            }
            // 重置单元格边框列表
            tableParam.setCellBorderList(new ArrayList<>(256));
        }
        // 开启页面自动定位
        page.enablePosition();
        // 添加新页面
        page.addNewPage(document, rectangle);
        // 关闭页面自动定位
        page.disablePosition();
        // 如果当前页面Y轴起始坐标未初始化，则进行初始化
        if (page.getPageY()==null) {
            // 重置当前页面Y轴起始坐标 = 页面高度 - 表格上边距
            page.setPageY(rectangle.getHeight() - tableParam.getMarginTop());
        }else {
            // 重置当前页面Y轴起始坐标 = 当前页面Y轴起始坐标 - 表格上边距
            page.setPageY(page.getPageY() - tableParam.getMarginTop());
        }
        // 如果开启自动表头，则绘制表头行
        if (tableParam.getIsAutoTitle()) {
            // 获取表头行
            XEasyPdfRow titleRow = tableParam.getTitleRow();
            // 如果表头行不为空，则绘制表头行
            if (titleRow!=null) {
                // 绘制表头行
                titleRow.doDraw(document, page, table);
            }
        }
    }
}
