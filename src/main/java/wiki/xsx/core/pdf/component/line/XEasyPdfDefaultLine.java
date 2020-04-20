package wiki.xsx.core.pdf.component.line;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponentBuilder;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.FontUtil;

import java.io.IOException;

/**
 * pdf线条组件
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
public class XEasyPdfDefaultLine implements XEasyPdfLine, XEasyPdfComponentBuilder {

    /**
     * 线条参数
     */
    private XEasyPdfLineParam param = new XEasyPdfLineParam();

    /**
     * 有参构造
     * @param param 线条参数
     */
    public XEasyPdfDefaultLine(XEasyPdfLineParam param) {
        this.param = param;
    }

    /**
     * 有参构造
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     */
    public XEasyPdfDefaultLine(float beginX, float beginY, float endX, float endY) {
        this.param.setBeginX(beginX).setBeginY(beginY).setEndX(endX).setEndY(endY);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     */
    public XEasyPdfDefaultLine(String fontPath, float beginX, float beginY, float endX, float endY) {
        this.param.setFontPath(fontPath).setBeginX(beginX).setBeginY(beginY).setEndX(endX).setEndY(endY);
    }

    /**
     * 有参构造
     * @param font 字体
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     */
    public XEasyPdfDefaultLine(PDFont font, float beginX, float beginY, float endX, float endY) {
        this.param.setFont(font).setBeginX(beginX).setBeginY(beginY).setEndX(endX).setEndY(endY);
    }

    /**
     * 设置定位
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     * @return 返回线条组件
     */
    @Override
    public XEasyPdfDefaultLine setPosition(float beginX, float beginY, float endX, float endY) {
        this.param.setBeginX(beginX).setBeginY(beginY).setEndX(endX).setEndY(endY);
        return this;
    }

    /**
     * 设置线条宽度
     * @param lineWidth 线条宽度
     * @return 返回线条组件
     */
    @Override
    public XEasyPdfDefaultLine setLineWidth(float lineWidth) {
        this.param.setLineWidth(lineWidth);
        return this;
    }

    /**
     * 设置线条线型
     * @param lineCapStyle 线条线型
     * @return 返回线条组件
     */
    @Override
    public XEasyPdfDefaultLine setLineCapStyle(XEasyPdfLineCapStyle lineCapStyle) {
        this.param.setStyle(lineCapStyle);
        return this;
    }

    /**
     * 画图
     *
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // X轴Y轴起始结束坐标判断
        if (
                this.param.getBeginX()==null ||
                this.param.getBeginY()==null ||
                this.param.getEndX()==null ||
                this.param.getEndY()==null
        ) {
            throw new RuntimeException("beginX or beginY or endX or endY can not null");
        }
        // 字体判断
        if (this.param.getFont()==null) {
            // 设置字体
            this.param.setFont(FontUtil.loadFont(document, page, this.param.getFontPath()));
        }
        // 初始化内容流
        PDPageContentStream contentStream = this.initStream(document, page);
        // 设置定位
        contentStream.moveTo(this.param.getBeginX(), this.param.getBeginY());
        // 连线
        contentStream.lineTo(this.param.getEndX(), this.param.getEndY());
        // 结束
        contentStream.stroke();
        // 关闭内容流
        contentStream.close();
    }

    /**
     * 初始化内容流
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream initStream(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 新建内容流
        PDPageContentStream contentStream = new PDPageContentStream(
                document.getDocument(),
                page.getLastPage(),
                PDPageContentStream.AppendMode.APPEND,
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
}
