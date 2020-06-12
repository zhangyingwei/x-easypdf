package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf表格行组件参数
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
@Data
@Accessors(chain = true)
class XEasyPdfRowParam {
    /**
     * 单元格列表
     */
    private List<XEasyPdfCell> cells = new ArrayList<>(10);
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
    private Float height = 0F;
    /**
     * X轴起始坐标
     */
    private Float beginX;
    /**
     * Y轴起始坐标
     */
    private Float beginY;
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
     * 文本样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfTextStyle style;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table, XEasyPdfRow row) throws IOException {
        // 获取表格参数
        XEasyPdfTableParam tableParam = table.getParam();
        // 如果文本样式未初始化，则进行初始化
        if (this.style==null) {
            // 初始化文本样式
            this.style = tableParam.getStyle();
        }
        // 如果字体未初始化，则进行初始化
        if (this.font==null) {
            // 初始化字体
            this.font = XEasyPdfFontUtil.getFont(document, this.fontPath, tableParam.getFont());
        }
        // 如果字体大小未初始化，则进行初始化
        if (this.fontSize==null) {
            // 初始化字体大小
            this.fontSize = tableParam.getFontSize();
        }
        // 遍历单元格列表
        for (XEasyPdfCell cell : this.cells) {
            // 如果单元格不为空，则进行初始化
            if (cell!=null) {
                // 初始化单元格
                cell.init(document, row);
            }
        }
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 获取当前页面Y轴起始坐标
        Float pageY = page.getParam().getPageY();
        // 如果当前页面Y轴起始坐标未初始化，则说明是新页面
        if (pageY==null) {
            // 初始化Y轴起始坐标 = 页面高度 - 表格上边距 - 行高 - 上边距
            this.beginY = rectangle.getHeight() - tableParam.getMarginTop() - this.height - this.marginTop;
        }else {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.getParam().isAllowFooter()&&page.getParam().getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getParam().getFooter().getHeight();
            }
            // 获取当前Y轴起始坐标 = 当前页面Y轴起始坐标 - 行高 - 上边距 + 1，自动补偿1
            float currentY = pageY - this.height - this.marginTop + 1;
            // 如果当前Y轴起始坐标-页脚高度小于等于表格下边距，则进行分页
            if (currentY - footerHeight <= tableParam.getMarginBottom()) {
                // 添加新页面
                page.addNewPage(document, rectangle);
                // 初始化Y轴起始坐标 = 页面高度 - 表格上边距 - 行高 - 上边距
                this.beginY = page.getParam().getPageY()==null?
                        rectangle.getHeight() - tableParam.getMarginTop() - this.height - this.marginTop:
                        page.getParam().getPageY() - tableParam.getMarginTop() - this.height - this.marginTop;
                // 初始化X轴起始坐标 = null
                this.beginX = null;
            }else {
                // 初始化Y轴起始坐标 = 当前Y轴起始坐标
                this.beginY = currentY;
            }
        }
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
