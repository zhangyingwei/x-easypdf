package wiki.xsx.core.pdf.component.table.simple;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * pdf表格行组件参数（简单表格）
 * @author xsx
 * @date 2021/4/25
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
class XEasyPdfSimpleRowParam {
    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode;
    /**
     * 单元格列表
     */
    private List<XEasyPdfSimpleCell> cells = new ArrayList<>(10);
    /**
     * 是否带有边框
     */
    private boolean hasBorder = false;
    /**
     * 边框宽度
     */
    private float borderWidth = 0F;
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
     * 表格样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfTableStyle horizontalStyle;
    /**
     * 表格样式（居上、居中、居下）
     * 默认居上
     */
    private XEasyPdfTableStyle verticalStyle;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @param row pdf表格行
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleTable table, XEasyPdfSimpleRow row) {
        if (!this.cells.isEmpty()) {
            // 获取表格参数
            XEasyPdfSimpleTableParam tableParam = table.getParam();
            // 初始化边框标记
            this.hasBorder = tableParam.isHasBorder();
            // 如果开启边框，则初始化边框宽度
            if (this.hasBorder) {
                // 初始化边框宽度
                this.borderWidth = tableParam.getBorderWidth();
            }
            // 如果内容模式未初始化，则进行初始化
            if (this.contentMode==null) {
                // 初始化内容模式
                this.contentMode = tableParam.getContentMode();
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
            // 如果字体路径为空，且默认字体样式不为空，则进行初始化字体路径
            if (this.fontPath==null&&this.defaultFontStyle!=null) {
                // 初始化字体路径
                this.fontPath = this.defaultFontStyle.getPath();
            }
            // 初始化字体
            this.font = XEasyPdfFontUtil.getFont(document, this.fontPath, tableParam.getFont());
            // 如果字体大小未初始化，则进行初始化
            if (this.fontSize==null) {
                // 初始化字体大小
                this.fontSize = tableParam.getFontSize();
            }
            // 定义行高
            float rowHeight = 0F;
            // 遍历单元格列表
            for (XEasyPdfSimpleCell cell : this.cells) {
                // 初始化行高
                rowHeight = Math.max(rowHeight + tableParam.getBorderWidth() * 2, cell.init(document, page, row));
            }
            // 如果行高未初始化，则进行初始化
            if (this.height==null) {
                // 初始化行高
                this.height = rowHeight;
            }
            // 获取页面尺寸
            PDRectangle rectangle = page.getLastPage().getMediaBox();
            // 获取当前页面Y轴起始坐标
            Float pageY = page.getParam().getPageY();
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.isAllowFooter()&&page.getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getParam().getFooter().getHeight(document, page);
            }
            // 获取当前Y轴起始坐标 = 当前页面Y轴起始坐标 - 行高 - 上边距
            float currentY = pageY - this.height - this.marginTop;
            // 如果当前Y轴起始坐标-页脚高度小于等于表格下边距，则进行分页
            if (currentY - footerHeight <= tableParam.getMarginBottom()) {
                // 开启页面自动定位
                page.enablePosition();
                // 添加新页面
                page.addNewPage(document, rectangle);
                // 关闭页面自动定位
                page.disablePosition();
                // 如果当前页面Y轴起始坐标未初始化，则进行初始化
                if (page.getParam().getPageY()==null) {
                    // 重置当前页面Y轴起始坐标 = 页面高度 - 表格上边距
                    page.getParam().setPageY(rectangle.getHeight() - tableParam.getMarginTop());
                }else {
                    // 重置当前页面Y轴起始坐标 = 当前页面Y轴起始坐标 - 表格上边距
                    page.getParam().setPageY(page.getParam().getPageY() - tableParam.getMarginTop());
                }
                // 获取当前页面Y轴起始坐标
                pageY = page.getParam().getPageY();
                // 获取当前Y轴起始坐标 = 当前页面Y轴起始坐标 - 行高 - 上边距
                currentY = pageY - this.height - this.marginTop;
            }
            // 初始化Y轴起始坐标 = 当前Y轴起始坐标 + 1，自动补偿1
            this.beginY = currentY + 1;
            // 如果X轴起始坐标为初始化，则进行初始化
            if (this.beginX==null) {
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
