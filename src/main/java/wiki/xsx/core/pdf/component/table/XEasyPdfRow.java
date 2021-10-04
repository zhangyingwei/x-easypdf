package wiki.xsx.core.pdf.component.table;

import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.util.Collections;
import java.util.List;

/**
 * pdf表格行组件
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
public class XEasyPdfRow {

    /**
     * pdf表格行参数
     */
    private final XEasyPdfRowParam param = new XEasyPdfRowParam();

    /**
     * 有参构造
     * @param cells pdf单元格
     */
    public XEasyPdfRow(XEasyPdfCell...cells) {
        Collections.addAll(this.param.getCells(), cells);
    }

    /**
     * 有参构造
     * @param cellList pdf单元格列表
     */
    public XEasyPdfRow(List<XEasyPdfCell> cellList) {
        this.param.getCells().addAll(cellList);
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回表格行组件
     */
    public XEasyPdfRow setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回表格行组件
     */
    public XEasyPdfRow setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回表格行组件
     */
    public XEasyPdfRow setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回表格行组件
     */
    public XEasyPdfRow setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回表格行组件
     */
    public XEasyPdfRow setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置表格样式（居左、居中、居右）
     * @param style 样式
     * @return 返回表格行组件
     */
    public XEasyPdfRow setStyle(XEasyPdfTableStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 设置行高
     * @param height 行高
     * @return 返回表格行组件
     */
    public XEasyPdfRow setHeight(float height) {
        this.param.setHeight(height);
        return this;
    }

    /**
     * 添加单元格
     * @param cells pdf单元格
     * @return 返回表格行组件
     */
    public XEasyPdfRow addCell(XEasyPdfCell...cells) {
        Collections.addAll(this.param.getCells(), cells);
        return this;
    }

    /**
     * 添加单元格
     * @param cellList pdf单元格列表
     * @return 返回表格行组件
     */
    public XEasyPdfRow addCell(List<XEasyPdfCell> cellList) {
        this.param.getCells().addAll(cellList);
        return this;
    }

    /**
     * 获取pdf表格行参数
     * @return 返回表格行组件
     */
    XEasyPdfRowParam getParam() {
        return this.param;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     */
    void doDraw(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfTable table) {
        // 初始化参数
        this.param.init(document, page, table, this);
        // 获取单元格列表
        List<XEasyPdfCell> cells = this.param.getCells();
        // 遍历单元格列表
        for (XEasyPdfCell cell : cells) {
            // 如果单元格不为空，则进行绘制
            if (cell!=null) {
                this.param.setBeginX(this.param.getBeginX()+cell.getParam().getMarginLeft());
                // 绘制单元格
                cell.doDraw(document, page, this);
                // 重置X轴起始坐标
                this.param.setBeginX(this.param.getBeginX()+cell.getParam().getWidth()-1F);
            }
        }
        // 重置页面Y轴起始坐标
        page.getParam().setPageY(this.param.getBeginY());
        // 重置字体为null
        this.param.setFont(null);
    }
}
