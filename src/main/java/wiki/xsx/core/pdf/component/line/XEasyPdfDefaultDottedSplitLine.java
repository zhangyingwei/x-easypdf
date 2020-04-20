package wiki.xsx.core.pdf.component.line;

import org.apache.pdfbox.pdmodel.PDPage;
import wiki.xsx.core.pdf.component.XEasyPdfComponentBuilder;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.FontUtil;

import java.io.IOException;

/**
 * 虚线分割线组件
 * @author xsx
 * @date 2020/3/4
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
public class XEasyPdfDefaultDottedSplitLine implements XEasyPdfDottedSplitLine, XEasyPdfComponentBuilder {

    /**
     * 分割线参数
     */
    private XEasyPdfLineParam param = new XEasyPdfLineParam();
    /**
     * 点线长度
     */
    private Float lineLength = 10F;
    /**
     * 点线间隔
     */
    private Float lineSpace = 10F;

    /**
     * 无参构造
     */
    public XEasyPdfDefaultDottedSplitLine() {}

    /**
     * 有参构造
     * @param fontPath 字体路径
     */
    public XEasyPdfDefaultDottedSplitLine(String fontPath) {
        this.param.setFontPath(fontPath);
    }

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置分割线宽度
     * @param lineWidth 分割线宽度
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setLineWidth(float lineWidth) {
        this.param.setLineWidth(lineWidth);
        return this;
    }

    /**
     * 设置分割线线型
     * @param lineCapStyle 分割线线型
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setLineCapStyle(XEasyPdfLineCapStyle lineCapStyle) {
        this.param.setStyle(lineCapStyle);
        return this;
    }

    /**
     * 设置点线长度
     * @param lineLength 点线长度
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setLineLength(float lineLength) {
        this.lineLength = lineLength;
        return this;
    }

    /**
     * 设置点线间隔
     * @param lineSpace 点线间隔
     * @return 返回虚线分割线组件
     */
    @Override
    public XEasyPdfDefaultDottedSplitLine setLineSpace(float lineSpace) {
        this.lineSpace = lineSpace;
        return this;
    }

    /**
     * 画图
     *
     * @param document pdf文档
     * @param page     pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化虚线分割线参数
        this.init(document, page);
        // 定义线条组件
        XEasyPdfDefaultLine xpdfLine;
        // 初始化线条组件
        xpdfLine = new XEasyPdfDefaultLine(this.param);
        // 执行画图
        xpdfLine.draw(document, page);
        // 计算点线数量，点线数量 = (pdfBox最新页面宽度 - 左边距 - 右边距) / (点线长度 + 点线间隔)
        int count = (int) Math.floor(
                (page.getLastPage().getMediaBox().getWidth() - this.param.getMarginLeft() - this.param.getMarginRight())
                /
                (this.lineLength + this.lineSpace)
        );
        // 循环点线数量进行画图
        for (int j = 1; j <= count; j++) {
            // 设置页面X轴起始坐标，起始坐标 = 结束坐标 + 点线间隔
            this.param.setBeginX(
                    this.param.getEndX() + this.lineSpace
            // 设置页面X轴结束坐标，结束坐标 = 起始坐标 + 点线长度
            ).setEndX(
                    this.param.getBeginX() + this.lineLength
            );
            // 重新初始化线条组件
            xpdfLine = new XEasyPdfDefaultLine(this.param);
            // 执行画图
            xpdfLine.draw(document, page);
        }
        // 设置pdf页面Y轴起始坐标，起始坐标 = 起始坐标 - 线宽 / 2
        page.getParam().setPageY(this.param.getBeginY() - this.param.getLineWidth() / 2);
    }

    /**
     * 初始化参数
     * @param document pdf文档
     * @param page pdf页面
     */
    private void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 定义线宽
        float lineWidth = this.param.getLineWidth() / 2;
        // 如果当前页面Y轴坐标不为空，则进行分页判断
        if (page.getParam().getPageY()!=null) {
            // 分页判断，如果（当前Y轴坐标-上边距-线宽）小于下边距，则进行分页
            if (page.getParam().getPageY() - this.param.getMarginTop() - lineWidth <= this.param.getMarginBottom()) {
                // 添加新的pdfBox页面
                page.getParam().getPageList().add(new PDPage(page.getLastPage().getMediaBox()));
                // 设置当前Y轴坐标为空，表示新页面
                page.getParam().setPageY(null);
            }
        }
        // 设置X轴Y轴起始结束坐标
        this.param.setBeginX(
                // 左边距
                this.param.getMarginLeft()
        ).setBeginY(
                // 如果当前页面Y轴坐标为空，则起始坐标 = pdfBox最新页面高度 - 上边距 - 线宽，否则起始坐标 = 当前页面Y轴坐标 - 上边距 - 线宽
                page.getParam().getPageY()==null?
                // pdfBox最新页面高度 - 上边距 - 线宽
                page.getLastPage().getMediaBox().getHeight() - this.param.getMarginTop() - lineWidth:
                // 当前页面Y轴坐标 - 上边距 - 线宽
                page.getParam().getPageY() - this.param.getMarginTop() - lineWidth
        ).setEndX(
                // X轴起始坐标 + 点线长度
                this.param.getBeginX() + this.lineLength
        ).setEndY(
                // Y轴起始坐标
                this.param.getBeginY()
        );
        if (this.param.getFont()==null) {
            // 设置字体
            this.param.setFont(FontUtil.loadFont(document, page, this.param.getFontPath()));
        }
    }
}
