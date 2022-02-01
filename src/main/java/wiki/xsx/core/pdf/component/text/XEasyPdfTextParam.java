package wiki.xsx.core.pdf.component.text;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.doc.XEasyPdfPositionStyle;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;
import wiki.xsx.core.pdf.util.XEasyPdfTextUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf文本参数
 * @author xsx
 * @date 2020/3/9
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
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
@Data
@Accessors(chain = true)
class XEasyPdfTextParam {
    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode = XEasyPdfComponent.ContentMode.APPEND;
    /**
     * 默认字体样式
     */
    private XEasyPdfDefaultFontStyle defaultFontStyle;
    /**
     * 字体路径
     */
    private String fontPath;
    /**
     * 字体
     */
    private PDFont font;
    /**
     * 字体大小
     */
    private Float fontSize = 12F;
    /**
     * 字体高度
     */
    private Float fontHeight;
    /**
     * 行间距
     */
    private Float leading = 5F;
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 右边距
     */
    private Float marginRight = 0F;
    /**
     * 上边距
     */
    private Float marginTop = 0F;
    /**
     * 下边距
     */
    private Float marginBottom = 0F;
    /**
     * 最大宽度
     */
    private Float maxWidth;
    /**
     * 最大高度
     */
    private Float maxHeight;
    /**
     * 文本缩进值
     */
    private Integer indent;
    /**
     * 待添加文本
     */
    private String text;
    /**
     * 拆分后的待添加文本列表
     */
    private List<String> splitTextList;
    /**
     * 拆分后的待添加文本列表(模板)
     */
    private List<String> splitTemplateTextList;
    /**
     * 水平样式（居左、居中、居右）
     */
    private XEasyPdfPositionStyle horizontalStyle = XEasyPdfPositionStyle.LEFT;
    /**
     * 垂直样式（居上、居中、居下）
     */
    private XEasyPdfPositionStyle verticalStyle = XEasyPdfPositionStyle.TOP;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
    /**
     * 字体颜色
     */
    private Color fontColor = Color.BLACK;
    /**
     * 下划线颜色
     */
    private Color underlineColor;
    /**
     * 删除线颜色
     */
    private Color deleteLineColor;
    /**
     * 高亮颜色
     */
    private Color highlightColor = Color.ORANGE;
    /**
     * 下划线宽度
     */
    private Float underlineWidth = 1F;
    /**
     * 删除线宽度
     */
    private Float deleteLineWidth = 1F;
    /**
     * 透明度（值越小越透明，0.0-1.0）
     */
    private Float alpha = 1.0F;
    /**
     * 文本弧度
     */
    private Double radians = 0D;
    /**
     * 超链接地址
     */
    private String linkUrl;
    /**
     * 评论
     */
    private String comment;
    /**
     * 是否使用自身样式
     */
    private boolean useSelfStyle = false;
    /**
     * 是否换行
     */
    private boolean isNewLine = true;
    /**
     * 是否分页检查
     */
    private boolean checkPage = true;
    /**
     * 是否文本追加
     */
    private boolean isTextAppend = false;
    /**
     * 是否子组件
     */
    private boolean isChildComponent = false;
    /**
     * 是否添加下划线
     */
    private boolean isUnderline = false;
    /**
     * 是否添加删除线
     */
    private boolean isDeleteLine = false;
    /**
     * 是否高亮显示
     */
    private boolean isHighlight = false;
    /**
     * 是否整行旋转
     */
    private boolean isRotateLine = false;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;

    /**
     * 获取宽度
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回文本宽度
     */
    float getWidth(XEasyPdfDocument document, XEasyPdfPage page) {
        if (this.maxWidth!=null) {
            return this.maxWidth;
        }
        this.init(document, page);
        return this.maxWidth;
    }

    /**
     * 获取高度
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回文本高度
     */
    float getHeight(XEasyPdfDocument document, XEasyPdfPage page) {
        if (this.maxHeight!=null) {
            return this.maxHeight;
        }
        this.init(document, page);
        return (this.fontHeight + this.leading) * this.splitTextList.size() - this.marginTop - this.marginBottom;
    }

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 获取pdfBox最新页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 如果最大宽度未初始化，则进行初始化
        if (this.maxWidth==null) {
            // 初始化最大宽度，最大宽度 = 页面宽度
            this.maxWidth = rectangle.getWidth();
        }
        // 如果字体路径为空，且默认字体样式不为空，则进行初始化字体路径
        if (this.fontPath==null&&this.defaultFontStyle!=null) {
            // 初始化字体路径
            this.fontPath = this.defaultFontStyle.getPath();
        }
        // 初始化字体
        this.font = XEasyPdfFontUtil.loadFont(document, page, this.fontPath, true);
        // 初始化字体高度
        this.fontHeight = this.fontSize;
        // 如果下划线颜色为空，则重置为字体颜色
        if (this.underlineColor==null) {
            // 重置下划线颜色为字体颜色
            this.underlineColor = this.fontColor;
        }
        // 如果删除线颜色为空，则重置为字体颜色
        if (this.deleteLineColor==null) {
            // 重置下划线颜色为字体颜色
            this.deleteLineColor = this.fontColor;
        }
        // 初始化待添加文本列表
        this.initTextList(page);
        // 如果最大高度未初始化，则进行初始化
        if (this.maxHeight==null) {
            // 初始化最大高度 = (字体高度+行间距) * 文本行数 - 上边距 - 下边距
            this.maxHeight = (this.fontHeight + this.leading) * this.splitTextList.size() - this.marginTop - this.marginBottom;
        }
        // 初始化Y轴起始坐标
        this.initBeginY(document, page);
    }

    /**
     * 初始化X轴起始坐标
     * @param pageX 页面Y轴坐标
     * @param text 待添加文本
     */
    @SneakyThrows
    float initBeginX(Float pageX, String text) {
        // 定义X轴坐标
        Float x = this.beginX;
        // 如果页面X轴起始坐标未初始化，则进行初始化
        if (x==null) {
            // 如果为文本追加，则初始化为左边距+页面X轴起始坐标
            if (this.isTextAppend) {
                // 初始化为左边距+页面X轴起始坐标
                x = this.marginLeft + (pageX==null?0F:pageX);
            }
            // 否则判断文本样式
            else {
                // 初始化样式
                x = this.initBeginXForStyle(text);
            }
        }
        // 否则重置X轴坐标=当前坐标+样式坐标
        else {
            // 重置X轴坐标=当前坐标+样式坐标
            x = x + this.initBeginXForStyle(text);
        }
        return x;
    }

    /**
     * 初始化X轴起始坐标样式
     * @param text 待添加文本
     * @return 返回X轴起始坐标
     */
    @SneakyThrows
    float initBeginXForStyle(String text) {
        // 如果为居左，则初始化为左边距
        if (this.horizontalStyle==XEasyPdfPositionStyle.LEFT) {
            // 初始化为左边距
            return this.marginLeft;
        }
        // 如果为居中，则初始化为(最大宽度-左边距-右边距-文本宽度)/2
        if (this.horizontalStyle==XEasyPdfPositionStyle.CENTER) {
            // 初始化为(最大宽度-左边距-右边距-文本宽度)/2
            return (this.maxWidth  - this.marginLeft - this.marginRight - (this.fontSize * this.font.getStringWidth(text) / 1000)) / 2 + this.marginLeft - this.marginRight;
        }
        // 否则为居右，初始化为最大宽度-右边距-文本宽度
        return this.maxWidth - (this.fontSize * this.font.getStringWidth(text) / 1000) + this.marginLeft - this.marginRight;
    }

    /**
     * 初始化Y轴起始坐标
     * @param document pdf文档
     * @param page pdf页面
     */
    private void initBeginY(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果Y轴起始坐标不为空，则重置Y轴起始坐标为Y轴起始坐标-上边距+下边距
        if (this.beginY!=null) {
            // 重置Y轴起始坐标为Y轴起始坐标-上边距+下边距
            this.beginY = this.beginY - this.marginTop + this.marginBottom;
            return;
        }
        // 获取页面Y轴坐标
        Float pageY = page.getParam().getPageY();
        // 如果页面Y轴坐标为空，则获取最新页面高度
        if (pageY==null) {
            // 重置页面Y轴坐标为最新页面高度
            pageY = page.getLastPage().getMediaBox().getHeight();
        }
        // 如果为文本追加模式，则重置Y轴起始坐标为页面Y轴坐标-上边距+下边距
        if (this.isTextAppend) {
            // 重置Y轴起始坐标为页面Y轴坐标-上边距+下边距
            this.beginY = pageY - this.marginTop + this.marginBottom;
            return;
        }
        // 如果为居上样式，则重置Y轴起始坐标为页面Y轴坐标-字体高度-行间距-上边距+下边距
        if (this.verticalStyle==XEasyPdfPositionStyle.TOP) {
            // 重置Y轴起始坐标为页面Y轴坐标-字体高度-行间距-上边距+下边距
            this.beginY = pageY - this.fontHeight - this.leading - this.marginTop + this.marginBottom;
            return;
        }
        // 如果为居中样式，则重置Y轴起始坐标为页面Y轴坐标-(页面Y轴坐标-最大高度)/2-行间距-上边距+下边距
        if (this.verticalStyle==XEasyPdfPositionStyle.CENTER) {
            // 重置Y轴起始坐标为页面Y轴坐标-(页面Y轴坐标-最大高度)/2-行间距-上边距+下边距
            this.beginY = pageY - (pageY - this.maxHeight) / 2 - this.leading - this.marginTop + this.marginBottom;
            return;
        }
        // 如果为居下样式，则判断是否包含页脚
        if (this.verticalStyle==XEasyPdfPositionStyle.BOTTOM) {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.isAllowFooter()&&page.getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getFooter().getHeight(document, page);
            }
            // 如果页面Y轴坐标-最大高度小于页脚高度，则重置Y轴起始坐标为页面Y轴坐标-字体高度-行间距-上边距+下边距
            if (pageY - this.maxHeight < footerHeight) {
                // 重置Y轴起始坐标为页面Y轴坐标-字体高度-行间距-上边距+下边距
                this.beginY = pageY - this.fontHeight - this.leading - this.marginTop + this.marginBottom;
            }
            // 否则重置Y轴起始坐标为页脚高度+最大高度-上边距+下边距+0.01(防止分页)
            else {
                this.beginY = footerHeight + this.maxHeight - this.marginTop + this.marginBottom + 0.01F;
            }
        }
    }

    /**
     * 初始化待添加文本列表
     * @param page pdf页面
     */
    private void initTextList(XEasyPdfPage page) {
        // 如果拆分后的待添加文本列表未初始化，则进行初始化
        if (this.splitTextList==null) {
            // 初始化缩进
            this.initIndent();
            // 开启文本追加
            if (this.isTextAppend) {
                // 定义X轴坐标
                float x;
                // 如果X轴起始坐标为空，则初始化X轴坐标为页面X轴坐标
                if (this.beginX==null) {
                    // 初始化X轴坐标为页面X轴坐标
                    x = page.getParam().getPageX()==null?this.marginLeft:page.getParam().getPageX()+this.marginLeft;
                }
                // 否则初始化为X轴起始坐标
                else {
                    // 初始化为X轴起始坐标
                    x = this.beginX;
                }
                // 获取第一行文本
                String firstLineText = XEasyPdfTextUtil.splitText(
                        // 待输入文本
                        this.text,
                        // 行宽度 = 最大宽度 - 左边距 - 右边距
                        this.maxWidth - x - this.marginRight,
                        // 字体
                        this.font,
                        // 字体大小
                        this.fontSize
                );
                // 如果第一行文本不为空，则添加文本列表
                if (firstLineText!=null) {
                    // 初始化待添加文本列表
                    this.splitTextList = new ArrayList<>(1024);
                    // 添加第一行文本
                    this.splitTextList.add(firstLineText);
                    // 第一行文本长度小于待输入文本，则继续拆分剩余文本
                    if (firstLineText.length()<this.text.length()) {
                        // 添加剩余文本
                        this.splitTextList.addAll(
                                XEasyPdfTextUtil.splitLines(
                                        // 截取剩余待输入文本
                                        this.text.substring(firstLineText.length()),
                                        // 行宽度 = 最大宽度 - 左边距 - 右边距
                                        this.maxWidth - this.marginLeft - this.marginRight,
                                        // 字体
                                        this.font,
                                        // 字体大小
                                        this.fontSize
                                )
                        );
                    }
                }
                // 否则进行文本全拆分（换行）
                else {
                    // 初始化待添加文本列表
                    this.splitTextList = XEasyPdfTextUtil.splitLines(
                            // 待输入文本
                            this.text,
                            // 行宽度 = 最大宽度 - 左边距 - 右边距
                            this.maxWidth - this.marginLeft - this.marginRight,
                            // 字体
                            this.font,
                            // 字体大小
                            this.fontSize
                    );
                    // 重置页面X轴起始坐标（换行）
                    this.beginX = this.marginLeft;
                    // 重置页面X轴
                    page.getParam().setPageX(null);
                }
            }
            // 未开启文本追加
            else {
                // 初始化待添加文本列表
                this.splitTextList =  XEasyPdfTextUtil.splitLines(
                        // 待输入文本
                        this.text,
                        // 行宽度 = 页面宽度 - 左边距 - 右边距
                        this.maxWidth - this.marginLeft - this.marginRight,
                        // 字体
                        this.font,
                        // 字体大小
                        this.fontSize
                );
            }
        }
        // 如果模板列表未初始化，则进行初始化
        if (this.splitTemplateTextList==null) {
            // 模板列表重置为待添加文本列表
            this.splitTemplateTextList =  new ArrayList<>(this.splitTextList);
        }
    }

    /**
     * 初始化缩进
     */
    private void initIndent() {
        // 如果缩进值不为空，则重置自动缩进
        if (this.indent!=null) {
            // 定义字符串构建器
            StringBuilder builder = new StringBuilder();
            // 循环添加空格字符
            for (int i = 0; i < this.indent; i++) {
                // 添加空格字符
                builder.append(" ");
            }
            // 重置待添加文本
            this.text = builder.toString() + this.text;
        }
    }
}
