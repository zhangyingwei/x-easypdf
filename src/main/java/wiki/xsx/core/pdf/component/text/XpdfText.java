package wiki.xsx.core.pdf.component.text;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XpdfComponent;
import wiki.xsx.core.pdf.doc.XpdfDocument;
import wiki.xsx.core.pdf.doc.XpdfPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文本组件
 * @author xsx
 * @date 2020/3/3
 * @since 1.8
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
        // 拆分文本行
        List<String> list = this.splitLines(
                // 待输入文本
                this.param.getText(),
                // 行宽度 = 页面宽度 - 左边距 - 右边距
                this.param.getMaxWidth() - this.param.getMarginLeft() - this.param.getMarginRight(),
                // 字体
                this.param.getFont(),
                // 字体大小
                this.param.getFontSize()
        );
        // 文本总行数索引
        int totalLineIndex = list.size() - 1;
        // 当前文本行索引
        int lineIndex = 0;
        // 定义内容流
        PDPageContentStream stream = null;
        // 居左样式
        if (this.param.getStyle()==null||this.param.getStyle()== XpdfTextStyle.LEFT) {
            // 循环文本输入
            for (String text : list) {
                stream = this.writeTextWithLeft(document, page, this.checkPage(page, stream), text);
                lineIndex++;
                this.lastLineIndex++;
            }
            if (stream!=null) {
                stream.endText();
                if (list.size()>0) {
                    this.param.setBeginY(
                            this.param.getBeginY() - (this.lastLineIndex - 1)  * (this.param.getFontSize() + this.param.getLeading()) - this.param.getLeading()
                    );
                }
            }
            // 居中样式
        }else if (this.param.getStyle()== XpdfTextStyle.CENTER) {
            // 循环文本输入
            for (String text : list) {
                stream = this.checkPage(page, stream);
                if (lineIndex==totalLineIndex) {
                    stream = this.writeTextWithCenter(document, page, stream, text, true);
                }else {
                    stream = this.writeTextWithCenter(document, page, stream, text, false);
                    lineIndex++;
                    this.lastLineIndex++;
                }
            }
        // 居右样式
        }else {
            // 循环文本输入
            for (String text : list) {
                stream = this.writeTextWithRight(document, page, this.checkPage(page, stream), text);
                lineIndex++;
                this.lastLineIndex++;
            }
            if (list.size()>0) {
                this.param.setBeginY(this.param.getBeginY() + this.param.getFontSize() + this.param.getLeading());
            }
        }
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
            if (stream!=null) {
                if (this.param.getStyle()!=XpdfTextStyle.RIGHT) {
                    stream.endText();
                }
                stream.close();
                stream = null;
            }
            // 开启新页
            page.getPageList().add(new PDPage(page.getLastPage().getMediaBox()));
            // 重置页面X轴Y轴起始坐标
            this.param.setBeginX(this.param.getMarginLeft())
                    .setBeginY(
                            this.param.getMaxHeight() - this.param.getMarginTop() -  this.param.getFontSize() - this.param.getLeading()
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
        if (stream==null) {
            this.lastLineIndex = 0;
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
        if (stream==null) {
            this.lastLineIndex = 0;
            stream = this.initPageContentStream(document, page);
            // 开启文本输入
            stream.beginText();
            // 设置文本定位
            stream.newLineAtOffset(this.param.getBeginX(), this.param.getBeginY());
        }
        if (isLastLine) {
            // 设置文本定位
            stream.newLineAtOffset(
                    (this.param.getMaxWidth()  - this.param.getMarginLeft() - this.param.getMarginRight() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000)) / 2,
                    0
            );
            // 文本输入
            stream.showText(text);
            stream.endText();
            this.param.setBeginY(
                    this.param.getBeginY() - (this.lastLineIndex * (this.param.getFontSize() + this.param.getLeading()) - this.param.getLeading())
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
        if (stream==null) {
            this.lastLineIndex = 0;
            stream = this.initPageContentStream(document, page);
        }
        // 开启文本输入
        stream.beginText();
        // 设置文本定位
        stream.newLineAtOffset(
                (this.param.getMaxWidth() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000) - this.param.getMarginRight()),
                this.param.getBeginY()
        );
        // 文本输入
        stream.showText(text);
        stream.endText();
        // 重置Y轴坐标
        this.param.setBeginY(this.param.getBeginY() - this.param.getFontSize() - this.param.getLeading());
        return stream;
    }

    /**
     * 拆分文本段落（换行）
     * @param text 待输入文本
     * @param lineWidth 行宽度
     * @param font 字体
     * @param fontSize 字体大小
     * @return 返回文本列表
     * @throws IOException IO异常
     */
    private List<String> splitLines(String text, float lineWidth, PDFont font, float fontSize) throws IOException {
        // 定义文本列表
        List<String> lineList = new ArrayList<>(200);
        // 定义临时文本
        String tempText;
        // 计算每个字的真实宽度
        float realWidth = fontSize * font.getStringWidth(text) / 1000;
        // 计算总行数（估计）
        int count = (int) (lineWidth / realWidth);
        // 计算的总行数与文本长度取最小值
        count = Math.min(count, text.length());
        // 定义开始索引
        int beginIndex = 0;
        // 循环文本
        for (int i = count, len = text.length(); i <= len; i++) {
            // 截取临时文本
            tempText = text.substring(beginIndex, i);
            // 计算当前文本真实宽度
            realWidth = fontSize * font.getStringWidth(tempText) / 1000;
            // 如果真实宽度大于行宽度，则减少一个字符
            if (realWidth>lineWidth) {
                // 加入文本列表
                lineList.add(text.substring(beginIndex, i - 1));
                // 重置开始索引
                beginIndex = i - 1;
            }
            // 如果当前索引等于文本长度，则直接加入文本列表
            if (i==len) {
                // 加入文本列表
                lineList.add(text.substring(beginIndex, i));
            }
        }
        return lineList;
    }
}
