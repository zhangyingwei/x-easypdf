package wiki.xsx.core.pdf.component.line;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.awt.*;
import java.io.IOException;

/**
 * pdf基础线条组件
 * @author xsx
 * @date 2020/3/4
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
public class XEasyPdfBaseLine implements XEasyPdfLine {

    /**
     * 线条参数
     */
    private XEasyPdfLineParam param = new XEasyPdfLineParam();

    /**
     * 有参构造
     * @param param 线条参数
     */
    public XEasyPdfBaseLine(XEasyPdfLineParam param) {
        this.param = param;
    }

    /**
     * 有参构造
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     */
    public XEasyPdfBaseLine(float beginX, float beginY, float endX, float endY) {
        this.param.setBeginX(beginX).setBeginY(beginY).setEndX(endX).setEndY(endY);
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回基础线条组件
     */
    @Deprecated
    @Override
    public XEasyPdfBaseLine setFont(PDFont font) {
        this.param.setFont(font);
        this.param.setFontPath("");
        return this;
    }

    /**
     * 设置左边距(无效)
     * @param margin 边距
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距(无效)
     * @param margin 边距
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置线条宽度
     * @param lineWidth 线条宽度
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setLineWidth(float lineWidth) {
        this.param.setLineWidth(lineWidth);
        return this;
    }

    /**
     * 设置线条颜色
     * @param color 线条颜色
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setColor(Color color) {
        this.param.setColor(color);
        return this;
    }

    /**
     * 设置线条线型
     * @param lineCapStyle 线条线型
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setLineCapStyle(XEasyPdfLineCapStyle lineCapStyle) {
        this.param.setStyle(lineCapStyle);
        return this;
    }

    /**
     * 设置定位
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     * @return 返回基础线条组件
     */
    public XEasyPdfBaseLine setPosition(float beginX, float beginY, float endX, float endY) {
        this.param.setBeginX(beginX).setBeginY(beginY).setEndX(endX).setEndY(endY);
        return this;
    }

    /**
     * 设置坐标
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setPosition(float beginX, float beginY) {
        this.param.setBeginX(beginX).setBeginY(beginY);
        return this;
    }

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setWidth(float width) {
        this.param.setEndX(this.param.getBeginX()+width);
        return this;
    }

    /**
     * 设置高度
     * @param height 高度
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setHeight(float height) {
        this.param.setEndY(this.param.getBeginY()-height);
        return this;
    }

    /**
     * 设置内容模式
     * @param mode 内容模式
     * @return 返回基础线条组件
     */
    @Override
    public XEasyPdfBaseLine setContentMode(ContentMode mode) {
        this.param.setContentMode(mode);
        return this;
    }

    /**
     * 获取文档字体
     * @return 返回pdfBox字体
     */
    @Deprecated
    @Override
    public PDFont getFont() {
        return this.param.getFont();
    }

    /**
     * 获取线条宽度
     * @return 返回线条宽度
     */
    @Override
    public float getLineWidth() {
        return this.param.getLineWidth();
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化内容流
        PDPageContentStream contentStream = this.initStream(document, page);
        // 设置定位
        contentStream.moveTo(this.param.getBeginX(), this.param.getBeginY());
        // 设置颜色
        contentStream.setStrokingColor(this.param.getColor());
        // 连线
        contentStream.lineTo(this.param.getEndX(), this.param.getEndY());
        // 结束
        contentStream.stroke();
        // 重置为黑色
        contentStream.setStrokingColor(Color.BLACK);
        // 关闭内容流
        contentStream.close();
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

    /**
     * 初始化内容流
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream initStream(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化参数
        this.init(document, page);
        // 新建内容流
        PDPageContentStream contentStream = new PDPageContentStream(
                document.getTarget(),
                page.getLastPage(),
                this.param.getContentMode().getMode(),
                true,
                false
        );
        // 设置字体
        contentStream.setFont(this.param.getFont(), this.param.getFontSize());
        // 设置线宽
        contentStream.setLineWidth(this.param.getLineWidth());
        // 设置线型
        contentStream.setLineCapStyle(this.param.getStyle().getType());
        return contentStream;
    }

    /**
     * 初始化参数
     * @param document pdf文档
     * @param page pdf页面
     */
    private void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 如果X轴起始坐标为空，则初始化为左边距
        if (this.param.getBeginX()==null) {
            // 初始化X轴起始坐标为左边距
            this.param.setBeginX(page.getParam().getPageX()==null?this.param.getMarginLeft():page.getParam().getPageX());
        }
        // 如果Y轴起始坐标为空，则初始化为上边距
        if (this.param.getBeginY()==null) {
            // 初始化Y轴起始坐标为上边距
            this.param.setBeginY(page.getParam().getPageY()==null?this.param.getMarginTop():page.getParam().getPageY());
        }
        // 如果X轴结束坐标为空，则初始化为页面宽度
        if (this.param.getEndX()==null) {
            // 初始化X轴结束坐标为页面宽度
            this.param.setEndX(rectangle.getWidth());
        }
        // 如果Y轴结束坐标为空，则初始化为页面高度
        if (this.param.getEndY()==null) {
            // 初始化Y轴结束坐标为页面高度
            this.param.setEndY(rectangle.getHeight());
        }
        // 如果字体为空，则加载全局字体
        if (this.param.getFont()==null) {
            // 设置全局字体
            this.param.setFont(XEasyPdfFontUtil.loadFont(document, page, this.param.getFontPath()));
        }
    }
}
