package wiki.xsx.core.pdf.component.text;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * pdf文本组件
 * @author xsx
 * @date 2020/3/3
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
public class XEasyPdfText implements XEasyPdfComponent {

    /**
     * 文本参数
     */
    private final XEasyPdfTextParam param = new XEasyPdfTextParam();

    /**
     * 当前页面文本最后一行索引
     */
    private int lastLineIndex = 0;

    /**
     * 有参构造
     * @param text 待输入文本
     */
    public XEasyPdfText(String text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param fontSize 字体大小
     * @param text 待输入文本
     */
    public XEasyPdfText(float fontSize, String text) {
        this.param.setText(text);
    }

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回文本组件
     */
    public XEasyPdfText setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XEasyPdfText setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XEasyPdfText setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XEasyPdfText setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XEasyPdfText setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置行间距
     * @param leading 行间距
     * @return 返回文本组件
     */
    public XEasyPdfText setLeading(float leading) {
        if (leading>0) {
            this.param.setLeading(leading);
        }
        return this;
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回文本组件
     */
    public XEasyPdfText setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体
     * @param font 字体
     * @return 返回文本组件
     */
    public XEasyPdfText setFont(PDFont font) {
        this.param.setFont(font);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回文本组件
     */
    public XEasyPdfText setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置字体颜色
     * @param fontColor 字体颜色
     * @return 返回文本组件
     */
    public XEasyPdfText setFontColor(Color fontColor) {
        this.param.setFontColor(fontColor);
        return this;
    }

    /**
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回文本组件
     */
    public XEasyPdfText setStyle(XEasyPdfTextStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 设置拆分后的待添加文本列表
     * @param splitTextList 拆分后的待添加文本列表
     * @return 返回文本组件
     */
    public XEasyPdfText setSplitTextList(List<String> splitTextList) {
        this.param.setSplitTextList(splitTextList);
        return this;
    }

    /**
     * 设置是否换行（影响下一组件是否换行）
     * @param isNewLine 是否换行
     * @return 返回文本组件
     */
    public XEasyPdfText setNewLine(boolean isNewLine) {
        this.param.setNewLine(isNewLine);
        return this;
    }

    /**
     * 设置是否分页检查
     * @param isCheckPage 是否分页检查
     * @return 返回文本组件
     */
    public XEasyPdfText setCheckPage(boolean isCheckPage) {
        this.param.setCheckPage(isCheckPage);
        return this;
    }

    /**
     * 设置定位
     * @param beginX 当前页面X轴坐标
     * @param beginY 当前页面Y轴坐标
     * @return 返回文本组件
     */
    @Override
    public XEasyPdfText setPosition(float beginX, float beginY) {
        this.param.setBeginX(beginX).setBeginY(beginY);
        return this;
    }

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回文本组件
     */
    @Override
    public XEasyPdfText setWidth(float width) {
        this.param.setMaxWidth(width);
        return this;
    }

    /**
     * 设置高度
     * @param height 高度
     * @return 返回文本组件
     */
    @Override
    public XEasyPdfText setHeight(float height) {
        this.param.setMaxHeight(height);
        return this;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page     pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        this.doDraw(document, page);
    }

    /**
     * 是否已经绘制
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
    private PDPageContentStream initPageContentStream(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
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
        // 设置字体颜色
        contentStream.setNonStrokingColor(this.param.getFontColor());
        // 设置行间距
        contentStream.setLeading(this.param.getLeading() + this.param.getFontSize());
        return contentStream;
    }

    /**
     * 执行画页面
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    private void doDraw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 如果设置不自动换行，则关闭页面自动重置定位
        if (!this.param.isNewLine()) {
            // 关闭页面自动重置定位
            page.disablePosition();
        }
        // 参数初始化
        this.param.init(document, page);
        // 拆分后的待添加文本列表
        List<String> splitTextList = this.param.getSplitTextList();
        // 文本总行数索引
        int totalLineIndex = splitTextList.size() - 1;
        // 当前文本行索引
        int lineIndex = 0;
        // 定义内容流
        PDPageContentStream stream = null;
        // 居左样式
        if (this.param.getStyle()==null||this.param.getStyle()==XEasyPdfTextStyle.LEFT) {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居左写入文本
                stream = this.writeTextWithLeft(document, page, this.checkPage(document, page, stream), text);
            }
            //如果内容流不为空，则结束文本写入，并重置Y轴起始坐标
            if (stream!=null) {
                // 结束文本写入
                stream.endText();
                // 如果文本总行数索引大于-1，则重置Y轴起始坐标
                if (totalLineIndex>-1) {
                    // 重置Y轴起始坐标
                    this.param.setBeginY(
                            // Y轴起始坐标 = Y轴起始坐标 - (当前页面文本最后一行索引 - 1) * (字体大小 + 行间距) - 行间距
                            this.param.getBeginY() - (this.lastLineIndex - 1)  * (this.param.getFontSize() + this.param.getLeading()) - this.param.getLeading()
                    );
                }
            }
            // 居中样式
        }else if (this.param.getStyle()== XEasyPdfTextStyle.CENTER) {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居中写入文本
                stream = this.writeTextWithCenter(document, page, this.checkPage(document, page, stream), text);
            }
            // 如果文本总行数索引大于-1，则重置Y轴起始坐标
            if (totalLineIndex>-1) {
                // Y轴起始坐标 = Y轴起始坐标 + 字体大小 + 行间距，由于之前多减一行，所以现在加回来
                this.param.setBeginY(this.param.getBeginY() + this.param.getFontSize() + this.param.getLeading());
            }
        // 居右样式
        }else {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居右写入文本
                stream = this.writeTextWithRight(document, page, this.checkPage(document, page, stream), text);
            }
            // 如果文本总行数索引大于-1，则重置Y轴起始坐标
            if (totalLineIndex>-1) {
                // Y轴起始坐标 = Y轴起始坐标 + 字体大小 + 行间距，由于之前多减一行，所以现在加回来
                this.param.setBeginY(this.param.getBeginY() + this.param.getFontSize() + this.param.getLeading());
            }
        }
        if (splitTextList.size()>0) {
            float textWidth = this.param.getFontSize() * this.param.getFont().getStringWidth(splitTextList.get(totalLineIndex)) / 1000;
            page.getParam().setPageX(page.getParam().getPageX()==null?textWidth:textWidth+page.getParam().getPageX());
        }
        // 如果内容流不为空，则关闭内容流，并重置文档页面Y轴坐标
        if (stream!=null) {
            // 内容流重置颜色为黑色
            stream.setNonStrokingColor(Color.BLACK);
            // 关闭内容流
            stream.close();
            // 如果允许页面重置定位，则进行重置
            if (page.getParam().isAllowResetPosition()) {
                // 设置文档页面Y轴坐标
                page.getParam().setPageY(this.param.getBeginY() - this.param.getFontSize() / 3);
            }
        }
        // 完成标记
        this.param.setDraw(true);
    }

    /**
     * 分页检查
     * @param page pdf页面
     * @param stream 内容流
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream checkPage(XEasyPdfDocument document, XEasyPdfPage page, PDPageContentStream stream) throws IOException {
        if (this.param.isCheckPage()) {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.getParam().isAllowFooter()&&page.getParam().getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getParam().getFooter().getHeight();
            }
            // 分页检查
            if (this.param.getBeginY() - (this.lastLineIndex * (this.param.getFontSize() + this.param.getLeading()) - this.param.getLeading()) - footerHeight <= this.param.getMarginBottom()) {
                // 如果内容流不为空，则关闭并设置为空
                if (stream!=null) {
                    // 关闭内容流
                    stream.close();
                    // 设置内容流为空
                    stream = null;
                }
                PDRectangle rectangle = page.getLastPage().getMediaBox();
                // 添加新页面
                page.addNewPage(document, rectangle);
                // 重置页面X轴Y轴起始坐标
                this.param.setBeginX(
                        // X轴起始坐标 = 左边距
                        this.param.getMarginLeft()
                ).setBeginY(
                        // 初始化页面Y轴起始坐标，如果当前页面Y轴坐标为空，则起始坐标 = 最大高度 - 上边距 - 字体大小 - 行间距，否则起始坐标 = 当前页面Y轴起始坐标 - 上边距 - 字体大小 - 行间距
                        page.getParam().getPageY()==null?
                                rectangle.getHeight() - this.param.getMarginTop() - this.param.getFontSize() - this.param.getLeading():
                                page.getParam().getPageY() - this.param.getMarginTop() - this.param.getFontSize() - this.param.getLeading()
                );
            }
        }
        return stream;
    }

    /**
     * 居左写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param stream 内容流
     * @param text 待写入文本
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream writeTextWithLeft(
            XEasyPdfDocument document,
            XEasyPdfPage page,
            PDPageContentStream stream,
            String text
    ) throws IOException {
        // 如果内容流为空，则初始化内容流
        if (stream==null) {
            // 重置当前页面文本最后一行索引为0
            this.lastLineIndex = 0;
            // 初始化内容流
            stream = this.initPageContentStream(document, page);
            // 开启文本输入
            stream.beginText();
            // 设置文本定位
            stream.newLineAtOffset(this.param.getBeginX(), this.param.getBeginY());
        }
        // 文本输入
        stream.showText(text);
        // 换行
        stream.newLine();
        // 重置Y轴起始坐标，Y轴起始坐标 = Y轴起始坐标 - 字体大小 - 行间距
        this.param.setBeginY(this.param.getBeginY() - this.param.getFontSize() - this.param.getLeading());
        return stream;
    }

    /**
     * 居中写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param stream 内容流
     * @param text 待写入文本
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream writeTextWithCenter(
            XEasyPdfDocument document,
            XEasyPdfPage page,
            PDPageContentStream stream,
            String text
    ) throws IOException {
        // 如果内容流为空，则初始化内容流
        if (stream==null) {
            // 重置当前页面文本最后一行索引为0
            this.lastLineIndex = 0;
            // 初始化内容流
            stream = this.initPageContentStream(document, page);
        }
        // 开启文本输入
        stream.beginText();
        // 设置文本定位
        stream.newLineAtOffset(
                this.param.getBeginX() + (this.param.getMaxWidth()  - this.param.getMarginLeft() - this.param.getMarginRight() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000)) / 2,
                this.param.getBeginY()
        );
        // 文本输入
        stream.showText(text);
        // 结束文本写入
        stream.endText();
        // 重置Y轴起始坐标，Y轴起始坐标 = Y轴起始坐标 - 字体大小 - 行间距
        this.param.setBeginY(this.param.getBeginY() - this.param.getFontSize() - this.param.getLeading());
        return stream;
    }

    /**
     * 居右写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param stream 内容流
     * @param text 待写入文本
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream writeTextWithRight(
            XEasyPdfDocument document,
            XEasyPdfPage page,
            PDPageContentStream stream,
            String text
    ) throws IOException {
        // 如果内容流为空，则初始化内容流
        if (stream==null) {
            // 重置当前页面文本最后一行索引为0
            this.lastLineIndex = 0;
            // 初始化内容流
            stream = this.initPageContentStream(document, page);
        }
        // 开启文本输入
        stream.beginText();
        // 设置文本定位
        stream.newLineAtOffset(
                // X轴坐标 = 页面宽度 - 文本真实宽度 - 右边距
                (this.param.getBeginX() + this.param.getMaxWidth() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000) - this.param.getMarginRight()),
                // Y轴坐标 = Y轴起始坐标
                this.param.getBeginY()
        );
        // 文本输入
        stream.showText(text);
        // 结束文本写入
        stream.endText();
        // 重置Y轴起始坐标，Y轴起始坐标 = Y轴起始坐标 - 字体大小 - 行间距
        this.param.setBeginY(this.param.getBeginY() - this.param.getFontSize() - this.param.getLeading());
        return stream;
    }
}
