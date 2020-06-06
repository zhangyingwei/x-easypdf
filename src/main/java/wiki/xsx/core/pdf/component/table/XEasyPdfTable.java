package wiki.xsx.core.pdf.component.table;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * pdf表格组件
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
        Collections.addAll(this.param.getRows(), rows);
    }

    /**
     * 添加表格行
     * @param rows pdf表格行
     * @return 返回表格组件
     */
    public XEasyPdfTable addRow(XEasyPdfRow...rows) {
        Collections.addAll(this.param.getRows(), rows);
        return this;
    }

    /**
     * 设置字体
     * @param fontPath 字体路径
     * @return 返回表格组件
     */
    public XEasyPdfTable setFont(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回表格组件
     */
    public XEasyPdfTable setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
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
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回表格组件
     */
    public XEasyPdfTable setStyle(XEasyPdfTextStyle style) {
        this.param.setStyle(style);
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
     * 获取pdf表格参数
     * @return 返回表格参数
     */
    XEasyPdfTableParam getParam() {
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
        this.param.init(document);
        // 如果X轴起始坐标不为空，则设置页面X轴起始坐标
        if (this.param.getBeginX()!=null) {
            // 设置页面X轴起始坐标 = 表格X轴起始坐标
            page.getParam().setPageX(this.param.getBeginX());
        }
        // 如果Y轴起始坐标不为空，则设置页面Y轴起始坐标
        if (this.param.getBeginY()!=null) {
            // 设置页面Y轴起始坐标 = 表格Y轴起始坐标
            page.getParam().setPageY(this.param.getBeginY());
        }
        // 获取表格行列表
        List<XEasyPdfRow> rows = this.param.getRows();
        // 遍历表格行列表
        for (XEasyPdfRow row : rows) {
            // 绘制表格行
            row.doDraw(document, page, this);
        }
        // 开启页面自动重置定位
        page.enablePosition();
        // 完成标记
        this.param.setDraw(true);
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
