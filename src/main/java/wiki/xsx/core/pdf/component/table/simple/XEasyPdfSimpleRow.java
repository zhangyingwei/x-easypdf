package wiki.xsx.core.pdf.component.table.simple;

import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * pdf表格行组件（简单表格）
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
public class XEasyPdfSimpleRow {

    /**
     * pdf表格行参数
     */
    private final XEasyPdfSimpleRowParam param = new XEasyPdfSimpleRowParam();

    /**
     * 有参构造
     * @param cells pdf单元格
     */
    public XEasyPdfSimpleRow(XEasyPdfSimpleCell...cells) {
        Collections.addAll(this.param.getCells(), cells);
    }

    /**
     * 有参构造
     * @param cellList pdf单元格列表
     */
    public XEasyPdfSimpleRow(List<XEasyPdfSimpleCell> cellList) {
        this.param.getCells().addAll(cellList);
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置表格样式（居左、居中、居右）
     * @param style 样式
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setStyle(XEasyPdfTableStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 设置行高
     * @param height 行高
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow setHeight(float height) {
        this.param.setHeight(height);
        return this;
    }

    /**
     * 添加单元格
     * @param cells pdf单元格
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow addCell(XEasyPdfSimpleCell...cells) {
        Collections.addAll(this.param.getCells(), cells);
        return this;
    }

    /**
     * 添加单元格
     * @param cellList pdf单元格列表
     * @return 返回表格行组件
     */
    public XEasyPdfSimpleRow addCell(List<XEasyPdfSimpleCell> cellList) {
        this.param.getCells().addAll(cellList);
        return this;
    }

    /**
     * 获取pdf表格行参数
     * @return 返回表格行组件
     */
    XEasyPdfSimpleRowParam getParam() {
        return this.param;
    }

    /**
     * 初始化行
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @throws IOException IO异常
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleTable table) throws IOException {
        // 初始化参数
        this.param.init(document, page, table, this);
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @param table pdf表格
     * @throws IOException IO异常
     */
    void doDraw(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfSimpleTable table) throws IOException {
        // 初始化表格行
        this.init(document, page, table);
        // 获取单元格列表
        List<XEasyPdfSimpleCell> cells = this.param.getCells();
        // 遍历单元格列表
        for (XEasyPdfSimpleCell cell : cells) {
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
        // 字体路径不为空，说明该组件设置字体，则直接进行字体关联
        if (this.param.getFontPath()!=null&&this.param.getFontPath().length()>0) {
            // 重置字体为null
            this.param.setFont(null);
        }
    }
}
