package wiki.xsx.core.pdf.component.table.simple;

import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * pdf表格组件（简单表格）
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
public class XEasyPdfSimpleTable implements XEasyPdfComponent {

    /**
     * pdf表格参数
     */
    private final XEasyPdfSimpleTableParam param = new XEasyPdfSimpleTableParam();

    /**
     * 有参构造
     * @param rows pdf表格行
     */
    public XEasyPdfSimpleTable(XEasyPdfSimpleRow...rows) {
        Collections.addAll(this.param.getRows(), rows);
    }

    /**
     * 有参构造
     * @param rowList pdf表格行列表
     */
    public XEasyPdfSimpleTable(List<XEasyPdfSimpleRow> rowList) {
        this.param.getRows().addAll(rowList);
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回表格组件
     */
    @Deprecated
    public XEasyPdfSimpleTable setFont(PDFont font) {
        this.param.setFont(font);
        this.param.setFontPath("");
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置表格样式（居左、居中、居右）
     * @param style 样式
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable setStyle(XEasyPdfTableStyle style) {
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
     * 开启边框
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable enableBorder() {
        this.param.setHasBorder(true);
        return this;
    }

    /**
     * 关闭边框
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable disableBorder() {
        this.param.setHasBorder(false);
        return this;
    }

    /**
     * 设置坐标
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     * @return 返回表格组件
     */
    @Override
    public XEasyPdfSimpleTable setPosition(float beginX, float beginY) {
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
    public XEasyPdfSimpleTable setWidth(float width) {
        return this;
    }

    /**
     * 设置高度（无效）
     * @param height 高度
     * @return 返回表格组件
     */
    @Deprecated
    @Override
    public XEasyPdfSimpleTable setHeight(float height) {
        return this;
    }

    /**
     * 设置内容模式
     * @param mode 内容模式
     * @return 返回表格组件
     */
    @Override
    public XEasyPdfSimpleTable setContentMode(ContentMode mode) {
        this.param.setContentMode(mode);
        return this;
    }

    /**
     * 添加表格行
     * @param rows pdf表格行
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable addRow(XEasyPdfSimpleRow...rows) {
        Collections.addAll(this.param.getRows(), rows);
        return this;
    }

    /**
     * 添加表格行
     * @param rowList pdf表格行列表
     * @return 返回表格组件
     */
    public XEasyPdfSimpleTable addRow(List<XEasyPdfSimpleRow> rowList) {
        this.param.getRows().addAll(rowList);
        return this;
    }

    /**
     * 获取pdf表格参数
     * @return 返回表格参数
     */
    XEasyPdfSimpleTableParam getParam() {
        return this.param;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 关闭页面自动重置定位
        page.disablePosition();
        // 初始化参数
        this.param.init(document, page, this);
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
        // 获取表格行列表
        List<XEasyPdfSimpleRow> rows = this.param.getRows();
        // 遍历表格行列表
        for (XEasyPdfSimpleRow row : rows) {
            // 绘制表格行
            row.doDraw(document, page, this);
        }
        // 开启页面自动重置定位
        page.enablePosition();
        // 完成标记
        this.param.setDraw(true);
        // 字体路径不为空，说明该组件设置字体，则直接进行字体关联
        if (this.param.getFontPath()!=null&&this.param.getFontPath().length()>0) {
            // 重置字体为null
            this.param.setFont(null);
        }
    }

    /**
     * 是否完成绘制
     * @return 返回布尔值，完成为true，未完成为false
     */
    @Override
    public boolean isDraw() {
        return this.param.isDraw();
    }
}
