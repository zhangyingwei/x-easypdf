package wiki.xsx.core.pdf.component.line;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import wiki.xsx.core.pdf.component.XpdfComponent;
import wiki.xsx.core.pdf.doc.XpdfDocument;
import wiki.xsx.core.pdf.doc.XpdfPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 实线分割线组件
 * @author xsx
 * @date 2020/3/4
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * xpdf is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 * </p>
 */
public class XpdfSolidSplitLine implements XpdfComponent {

    /**
     * 分割线参数
     */
    private XpdfLineParam param = new XpdfLineParam();

    /**
     * 有参构造
     * @param fontPath 字体路径
     */
    public XpdfSolidSplitLine(String fontPath) {
        this.param.setFontPath(fontPath);
    }

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置分割线宽度
     * @param lineWidth 分割线宽度
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setLineWidth(float lineWidth) {
        this.param.setLineWidth(lineWidth);
        return this;
    }

    /**
     * 设置分割线线型
     * @param xpdfLineCapStyle 分割线线型
     * @return 返回实线分割线组件
     */
    public XpdfSolidSplitLine setLineCapStyle(XpdfLineCapStyle xpdfLineCapStyle) {
        this.param.setXpdfLineCapStyle(xpdfLineCapStyle);
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
    public void draw(XpdfDocument document, XpdfPage page) throws IOException {
        // 初始化分割线参数
        this.init(document, page);
        // 执行画图
        new XpdfLine(this.param).draw(document, page);
        // 设置pdf页面Y轴起始坐标，起始坐标 = 起始坐标 - 线宽 / 2
        page.setPageY(this.param.getBeginY() - this.param.getLineWidth() / 2);
    }

    /**
     * 初始化参数
     * @param page pdf页面
     */
    private void init(XpdfDocument document, XpdfPage page) throws IOException {
        // 定义线宽
        float lineWidth = this.param.getLineWidth() / 2;
        // 获取pdfBox最新页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 如果当前页面Y轴坐标不为空，则进行分页判断
        if (page.getPageY()!=null) {
            // 分页判断，如果（当前Y轴坐标-上边距-线宽）小于下边距，则进行分页
            if (page.getPageY() - this.param.getMarginTop() - lineWidth <= this.param.getMarginBottom()) {
                // 添加新的pdfBox页面
                page.getPageList().add(new PDPage(page.getLastPage().getMediaBox()));
                // 设置当前Y轴坐标为空，表示新页面
                page.setPageY(null);
            }
        }
        // 设置X轴Y轴起始结束坐标
        this.param.setBeginX(
                // 左边距
                this.param.getMarginLeft()
        ).setBeginY(
                // 如果当前页面Y轴坐标为空，则起始坐标 = pdfBox最新页面高度 - 上边距 - 线宽，否则起始坐标 = 当前页面Y轴坐标 - 上边距 - 线宽
                page.getPageY() == null?
                // pdfBox最新页面高度 - 上边距 - 线宽
                rectangle.getHeight() - this.param.getMarginTop() - lineWidth:
                // 当前页面Y轴坐标 - 上边距 - 线宽
                page.getPageY() - this.param.getMarginTop() - lineWidth
        ).setEndX(
                // 页面宽度 - 右边距
                rectangle.getWidth() - this.param.getMarginRight()
        ).setEndY(
                // Y轴起始坐标
                this.param.getBeginY()
        );
        // 设置字体
        this.param.setFont(
                PDType0Font.load(
                        document.getDocument(),
                        Files.newInputStream(
                                Paths.get(this.param.getFontPath())
                        )
                )
        );
    }
}
