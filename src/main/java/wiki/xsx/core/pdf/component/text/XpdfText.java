package wiki.xsx.core.pdf.component.text;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import wiki.xsx.core.pdf.component.XpdfComponent;
import wiki.xsx.core.pdf.doc.XpdfDocument;
import wiki.xsx.core.pdf.doc.XpdfPage;

import java.io.IOException;
import java.util.List;

/**
 * 文本组件
 * @author xsx
 * @date 2020/3/3
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
public class XpdfText implements XpdfComponent {

    /**
     * 文本参数
     */
    private XpdfTextParam param = new XpdfTextParam();

    /**
     * 当前页面文本最后一行索引
     */
    private int lastLineIndex = 0;

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param text 待输入文本
     */
    public XpdfText(String fontPath, String text) {
        this.param.setFontPath(fontPath).setText(text);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param fontSize 字体大小
     * @param text 待输入文本
     */
    public XpdfText(String fontPath, float fontSize, String text) {
        this.param.setFontPath(fontPath).setFontSize(fontSize).setText(text);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param fontSize 字体大小
     * @param beginX 当前页面X轴起始坐标
     * @param beginY 当前页面Y轴起始坐标
     * @param text 待输入文本
     */
    public XpdfText(String fontPath, float fontSize, float beginX, float beginY, String text) {
        this.param.setFontPath(fontPath).setFontSize(fontSize).setBeginX(beginX).setBeginY(beginY).setText(text);
    }

    /**
     * 有参构造
     * @param fontPath 字体路径
     * @param fontSize 字体大小
     * @param leading 行间距
     * @param beginX 当前页面X轴起始坐标
     * @param beginY 当前页面Y轴起始坐标
     * @param text 待输入文本
     */
    public XpdfText(String fontPath, float fontSize, float leading, float beginX, float beginY, String text) {
        this.param.setFontPath(fontPath).setFontSize(fontSize).setLeading(leading).setBeginX(beginX).setBeginY(beginY).setText(text);
    }

    /**
     * 有参构造
     * @param param 文本参数
     */
    public XpdfText(XpdfTextParam param) {
        if (param==null) {
            throw new IllegalArgumentException("the param is not invalid");
        }
        this.param = param;
    }

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回文本组件
     */
    public XpdfText setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XpdfText setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XpdfText setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XpdfText setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回文本组件
     */
    public XpdfText setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置定位
     * @param x 当前页面X轴坐标
     * @param y 当前页面Y轴坐标
     * @return 返回文本组件
     */
    public XpdfText setPosition(float x, float y) {
        this.param.setBeginX(x).setBeginY(y);
        return this;
    }

    /**
     * 设置行间距
     * @param leading 行间距
     * @return 返回文本组件
     */
    public XpdfText setLeading(float leading) {
        if (leading>0) {
            this.param.setLeading(leading);
        }
        return this;
    }

    /**
     * 设置字体
     * @param fontPath 字体路径
     * @return 返回文本组件
     */
    public XpdfText setFont(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回文本组件
     */
    public XpdfText setFontSize(float fontSize) {
        this.param.setFontSize(fontSize);
        return this;
    }

    /**
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回文本组件
     */
    public XpdfText setStyle(XpdfTextStyle style) {
        this.param.setStyle(style);
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
        this.doDraw(document, page);
    }

    /**
     * 初始化内容流
     * @param document pdf文档
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream initPageContentStream(XpdfDocument document, XpdfPage page) throws IOException {
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
        // 设置行间距
        contentStream.setLeading(this.param.getLeading() + this.param.getFontSize());
        return contentStream;
    }

    /**
     * 执行画页面
     * @param document pdf文档
     * @throws IOException IO异常
     */
    private void doDraw(XpdfDocument document, XpdfPage page) throws IOException {
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
        if (this.param.getStyle()==null||this.param.getStyle()==XpdfTextStyle.LEFT) {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居左写入文本
                stream = this.writeTextWithLeft(document, page, this.checkPage(page, stream), text);
                // 当前文本行索引自增
                lineIndex++;
                // 当前页面文本最后一行索引自增
                this.lastLineIndex++;
                if (lineIndex>=totalLineIndex) {
                    float textWidth = this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000;
                    page.setPageX(page.getPageX()==null?textWidth:textWidth+page.getPageX());
                }
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
        }else if (this.param.getStyle()==XpdfTextStyle.CENTER) {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查
                stream = this.checkPage(page, stream);
                // 判断是否为最后一行
                if (lineIndex>=totalLineIndex) {
                    // 居中写入文本
                    stream = this.writeTextWithCenter(document, page, stream, text, true);
                    float textWidth = this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000;
                    page.setPageX(page.getPageX()==null?textWidth:textWidth+page.getPageX());
                }else {
                    // 居中写入文本
                    stream = this.writeTextWithCenter(document, page, stream, text, false);
                    // 当前文本行索引自增
                    lineIndex++;
                    // 当前页面文本最后一行索引自增
                    this.lastLineIndex++;
                }
            }
        // 居右样式
        }else {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居右写入文本
                stream = this.writeTextWithRight(document, page, this.checkPage(page, stream), text);
                // 当前文本行索引自增
                lineIndex++;
                // 当前页面文本最后一行索引自增
                this.lastLineIndex++;
                if (lineIndex>=totalLineIndex) {
                    float textWidth = this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000;
                    page.setPageX(page.getPageX()==null?textWidth:textWidth+page.getPageX());
                }
            }
            // 如果文本总行数索引大于-1，则重置Y轴起始坐标
            if (totalLineIndex>-1) {
                // Y轴起始坐标 = Y轴起始坐标 + 字体大小 + 行间距，由于之前多减一行，所以现在加回来
                this.param.setBeginY(this.param.getBeginY() + this.param.getFontSize() + this.param.getLeading());
            }
        }
        // 如果内容流不为空，则关闭内容流，并重置文档页面Y轴坐标
        if (stream!=null) {
            // 关闭内容流
            stream.close();
            // 设置文档页面Y轴坐标
            page.setPageY(this.param.getBeginY() - this.param.getFontSize() / 3);
        }
    }

    /**
     * 分页检查
     * @param page pdf页面
     * @param stream 内容流
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream checkPage(XpdfPage page, PDPageContentStream stream) throws IOException {
        // 分页检查
        if (this.param.getBeginY() - (this.lastLineIndex * (this.param.getFontSize() + this.param.getLeading()) - this.param.getLeading()) <= this.param.getMarginBottom()) {
            // 如果内容流不为空，则关闭并设置为空
            if (stream!=null) {
                // 如果文本样式为居右样式，则结束文本写入
                if (this.param.getStyle()!=XpdfTextStyle.RIGHT) {
                    // 结束文本写入
                    stream.endText();
                }
                // 关闭内容流
                stream.close();
                // 设置内容流为空
                stream = null;
            }
            // 添加新页面
            page.getPageList().add(new PDPage(page.getLastPage().getMediaBox()));
            // 重置页面X轴Y轴起始坐标
            this.param.setBeginX(
                    // X轴起始坐标 = 左边距
                    this.param.getMarginLeft()
            ).setBeginY(
                    // Y轴起始坐标 = 页面高度 - 上边距 - 字体大小 - 行间距
                    this.param.getMaxHeight() - this.param.getMarginTop() - this.param.getFontSize() - this.param.getLeading()
            );
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
            XpdfDocument document,
            XpdfPage page,
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
        return stream;
    }

    /**
     * 居中写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param stream 内容流
     * @param text 待写入文本
     * @param isLastLine 是否最后一行
     * @return 返回内容流
     * @throws IOException IO异常
     */
    private PDPageContentStream writeTextWithCenter(
            XpdfDocument document,
            XpdfPage page,
            PDPageContentStream stream,
            String text,
            boolean isLastLine
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
        // 如果是最后一行，则重置文本定位
        if (isLastLine) {
            // 设置文本定位
            stream.newLineAtOffset(
                    // X轴坐标 = (页面宽度 - 左边距 - 右边距 - 文本真实宽度) /2
                    (this.param.getMaxWidth()  - this.param.getMarginLeft() - this.param.getMarginRight() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000)) / 2,
                    // Y轴坐标 = 0
                    0
            );
            // 文本输入
            stream.showText(text);
            // 结束文本写入
            stream.endText();
            // 重置Y轴起始坐标
            this.param.setBeginY(
                    // Y轴起始坐标 = Y轴起始坐标 - 当前页面文本最后一行索引 * (字体大小 + 行间距) - 行间距
                    this.param.getBeginY() - this.lastLineIndex * (this.param.getFontSize() + this.param.getLeading()) - this.param.getLeading()
            );
        }else {
            // 文本输入
            stream.showText(text);
            // 换行
            stream.newLine();
        }
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
            XpdfDocument document,
            XpdfPage page,
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
                (this.param.getMaxWidth() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000) - this.param.getMarginRight()),
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
