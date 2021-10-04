package wiki.xsx.core.pdf.component.text;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.awt.*;
import java.util.List;

/**
 * pdf文本组件
 * @author xsx
 * @date 2020/3/3
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
public class XEasyPdfText implements XEasyPdfComponent {

    /**
     * 文本参数
     */
    private final XEasyPdfTextParam param = new XEasyPdfTextParam();

    /**
     * 有参构造
     * @param text 待输入文本
     */
    public XEasyPdfText(String text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param textList 待输入文本列表
     */
    public XEasyPdfText(List<String> textList) {
        this.param.setSplitTextList(textList);
        this.param.setSplitTemplateTextList(textList);
    }

    /**
     * 有参构造
     * @param fontSize 字体大小
     * @param text 待输入文本
     */
    public XEasyPdfText(float fontSize, String text) {
        this.param.setFontSize(fontSize).setText(text);
    }

    /**
     * 有参构造
     * @param fontSize 字体大小
     * @param textList 待输入文本列表
     */
    public XEasyPdfText(float fontSize, List<String> textList) {
        this.param.setFontSize(fontSize).setSplitTextList(textList);
    }

    /**
     * 开启文本追加
     * @return 返回文本组件
     */
    public XEasyPdfText enableTextAppend() {
        this.param.setTextAppend(true);
        return this;
    }

    /**
     * 开启子组件
     * @return 返回文本组件
     */
    public XEasyPdfText enableChildComponent() {
        this.param.setChildComponent(true);
        return this;
    }

    /**
     * 开启自身样式
     * @return 返回文本组件
     */
    public XEasyPdfText enableSelfStyle() {
        this.param.setUseSelfStyle(true);
        return this;
    }

    /**
     * 关闭自身样式
     * @return 返回文本组件
     */
    public XEasyPdfText disableSelfStyle() {
        this.param.setUseSelfStyle(false);
        return this;
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
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回文本组件
     */
    public XEasyPdfText setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
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
        this.param.setSplitTemplateTextList(splitTextList);
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
     * 设置内容模式
     * @param mode 内容模式
     * @return 返回文本组件
     */
    @Override
    public XEasyPdfText setContentMode(ContentMode mode) {
        this.param.setContentMode(mode);
        return this;
    }

    /**
     * 替换占位符
     * @param placeholder 占位符
     * @param value 新字符串
     * @return 返回文本组件
     */
    public XEasyPdfText replaceAllPlaceholder(String placeholder, String value) {
        // 获取待添加文本列表
        List<String> textList = this.param.getSplitTextList();
        // 获取待添加文本列表(模板)
        List<String> templateTextList = this.param.getSplitTemplateTextList();
        // 遍历待添加文本列表
        for (int i = 0, count = textList.size(); i < count; i++) {
            // 替换占位符
            textList.set(i, templateTextList.get(i).replace(placeholder, value));
        }
        return this;
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page     pdf页面
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) {
        this.doDraw(document, page);
        // 重置字体为null
        this.param.setFont(null);
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
     * 获取高度
     * @param document pdf文档
     * @param page pdf页面
     * @param marginLeft 左边距
     * @param marginRight 右边距
     * @return 返回文本高度
     */
    public float getHeight(XEasyPdfDocument document, XEasyPdfPage page, float marginLeft, float marginRight) {
        return this.param.getHeight(document, page, marginLeft, marginRight);
    }

    /**
     * 获取宽度
     * @param document pdf文档
     * @param page pdf页面
     * @param marginLeft 左边距
     * @param marginRight 右边距
     * @return 返回文本宽度
     */
    public float getWidth(XEasyPdfDocument document, XEasyPdfPage page, float marginLeft, float marginRight) {
        return this.param.getWidth(document, page, marginLeft, marginRight);
    }

    /**
     * 获取上边距
     * @return 返回上边距
     */
    public float getMarginTop() {
        return this.param.getMarginTop();
    }

    /**
     * 获取下边距
     * @return 返回下边距
     */
    public float getMarginBottom() {
        return this.param.getMarginBottom();
    }

    /**
     * 获取左边距
     * @return 返回左边距
     */
    public float getMarginLeft() {
        return this.param.getMarginLeft();
    }

    /**
     * 获取右边距
     * @return 返回右边距
     */
    public float getMarginRight() {
        return this.param.getMarginRight();
    }

    /**
     * 获取文本样式
     * @return 返回文本样式
     */
    public XEasyPdfTextStyle getStyle() {
        return this.param.getStyle();
    }

    /**
     * 获取待添加文本列表
     * @return 返回待添加文本列表
     */
    public List<String> getSplitTextList() {
        return this.param.getSplitTextList();
    }

    /**
     * 是否使用自身样式
     * @return 返回布尔值，是为true，否为false
     */
    public boolean isUseSelfStyle() {
        return this.param.isUseSelfStyle();
    }

    /**
     * 初始化内容流
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回内容流
     */
    @SneakyThrows
    private PDPageContentStream initPageContentStream(XEasyPdfDocument document, XEasyPdfPage page) {
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
        // 设置字体颜色
        contentStream.setNonStrokingColor(this.param.getFontColor());
        // 设置行间距
        contentStream.setLeading(this.param.getLeading() + this.param.getFontHeight());
        return contentStream;
    }

    /**
     * 执行画页面
     * @param document pdf文档
     * @param page pdf页面
     */
    @SneakyThrows
    private void doDraw(XEasyPdfDocument document, XEasyPdfPage page) {
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
        // 定义内容流
        PDPageContentStream stream = null;
        // 居左样式
        if (this.param.getStyle()==null||this.param.getStyle()==XEasyPdfTextStyle.LEFT) {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居左写入文本
                stream = this.writeText(
                        document,
                        page,
                        this.checkPage(document, page, stream),
                        text,
                        this.param.getBeginX(),
                        this.param.getBeginY()
                );
            }
            // 居中样式
        }else if (this.param.getStyle()== XEasyPdfTextStyle.CENTER) {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居中写入文本
                stream = this.writeText(
                        document,
                        page,
                        this.checkPage(document, page, stream),
                        text,
                        // X轴坐标 = X轴起始坐标 + 页面宽度 - 左边距 - 右边距 - 文本真实宽度
                        this.param.getBeginX() + (this.param.getMaxWidth()  - this.param.getMarginLeft() - this.param.getMarginRight() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000)) / 2,
                        this.param.getBeginY()
                );
            }
        // 居右样式
        }else {
            // 遍历文本输入
            for (String text : splitTextList) {
                // 分页检查，并居右写入文本
                stream = this.writeText(
                        document,
                        page,
                        this.checkPage(document, page, stream),
                        text,
                        // X轴坐标 = X轴起始坐标 + 页面宽度 - 文本真实宽度 - 右边距
                        (this.param.getBeginX() + this.param.getMaxWidth() - (this.param.getFontSize() * this.param.getFont().getStringWidth(text) / 1000) - this.param.getMarginRight()),
                        // Y轴坐标 = Y轴起始坐标
                        this.param.getBeginY()
                );
            }
        }
        //如果内容流不为空，则结束文本写入，并重置Y轴起始坐标
        if (stream!=null) {
            // 如果文本总行数索引大于-1，则重置Y轴起始坐标
            if (totalLineIndex>-1) {
                // 重置Y轴起始坐标，Y轴起始坐标 = Y轴起始坐标 + 字体高度 + 行间距，由于之前多减一行，所以现在加回来
                this.param.setBeginY(this.param.getBeginY() + this.param.getFontHeight() + this.param.getLeading());
            }
        }
        // 如果文本列表不为空，则设置页面X轴坐标
        if (splitTextList.size()>0) {
            // 获取文本宽度
            float textWidth = this.param.getFontSize() * this.param.getFont().getStringWidth(splitTextList.get(totalLineIndex)) / 1000;
            // 设置页面X轴坐标
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
                page.getParam().setPageY(this.param.getBeginY());
            }
        }
        // 如果不允许页面重置定位，则进行重置
        if (!page.getParam().isAllowResetPosition()) {
            // 开启页面自动重置定位
            page.enablePosition();
        }
        // 完成标记
        this.param.setDraw(true);
    }

    /**
     * 分页检查
     * @param page pdf页面
     * @param stream 内容流
     * @return 返回内容流
     */
    @SneakyThrows
    private PDPageContentStream checkPage(XEasyPdfDocument document, XEasyPdfPage page, PDPageContentStream stream) {
        if (this.param.isCheckPage()) {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.isAllowFooter()&&page.getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getFooter().getHeight(document, page);
            }
            // 分页检查
            if (this.param.getBeginY() - (this.param.getFontHeight() + footerHeight) <= this.param.getMarginBottom()) {
                // 如果内容流不为空，则关闭并设置为空
                if (stream!=null) {
                    // 关闭内容流
                    stream.close();
                    // 设置内容流为空
                    stream = null;
                }
                // 获取页面尺寸
                PDRectangle rectangle = page.getLastPage().getMediaBox();
                // 添加新页面
                page.addNewPage(document, rectangle);
                // 非子组件
                if (!this.param.isChildComponent()) {
                    // 重置页面X轴起始坐标
                    this.param.setBeginX(
                        // X轴起始坐标 = 左边距
                        this.param.getMarginLeft()
                    );
                }
                // 重置页面Y轴起始坐标
                this.param.setBeginY(
                        // 初始化页面Y轴起始坐标，如果当前页面Y轴坐标为空，则起始坐标 = 最大高度 - 上边距 - 字体高度 - 行间距，否则起始坐标 = 当前页面Y轴起始坐标 - 上边距 - 字体高度 - 行间距
                        page.getParam().getPageY()==null?
                                rectangle.getHeight() - this.param.getMarginTop() - this.param.getFontHeight() - this.param.getLeading():
                                page.getParam().getPageY() - this.param.getMarginTop() - this.param.getFontHeight() - this.param.getLeading()
                );
            }
        }
        return stream;
    }

    /**
     * 写入文本
     * @param document pdf文档
     * @param page pdf页面
     * @param stream 内容流
     * @param text 待写入文本
     * @param beginX X轴坐标
     * @param beginY Y轴坐标
     * @return 返回内容流
     */
    @SneakyThrows
    private PDPageContentStream writeText(
            XEasyPdfDocument document,
            XEasyPdfPage page,
            PDPageContentStream stream,
            String text,
            float beginX,
            float beginY
    ) {
        // 如果内容流为空，则初始化内容流
        if (stream==null) {
            // 初始化内容流
            stream = this.initPageContentStream(document, page);
        }
        // 开启文本输入
        stream.beginText();
        // 设置文本定位
        stream.newLineAtOffset(beginX, beginY);
        // 文本输入
        stream.showText(text);
        // 结束文本写入
        stream.endText();
        // 非子组件，则重置X轴起始坐标
        if (!this.param.isChildComponent()) {
            // 重置X轴起始坐标，X轴起始坐标 = 左边距
            this.param.setBeginX(this.param.getMarginLeft());
        }
        // 重置Y轴起始坐标，Y轴起始坐标 = Y轴起始坐标 - 字体高度 - 行间距
        this.param.setBeginY(this.param.getBeginY() - (this.param.getFontHeight() + this.param.getLeading()));
        return stream;
    }
}
