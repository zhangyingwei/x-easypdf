package wiki.xsx.core.pdf.component.text;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
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
     * 页面宽度
     */
    private Float maxWidth;
    /**
     * 页面高度
     */
    private Float maxHeight;
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
     * 文本样式（居左、居中、居右）
     */
    private XEasyPdfTextStyle style;
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
     * 是否完成绘制
     */
    private boolean isDraw = false;

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
        // 如果最大高度未初始化，则进行初始化
        if (this.maxHeight==null) {
            // 初始化最大高度(页面高度)
            this.maxHeight = rectangle.getHeight();
        }
        // 如果字体路径为空，且默认字体样式不为空，则进行初始化字体路径
        if (this.fontPath==null&&this.defaultFontStyle!=null) {
            // 初始化字体路径
            this.fontPath = this.defaultFontStyle.getPath();
        }
        // 初始化字体
        this.font = XEasyPdfFontUtil.loadFont(document, page, this.fontPath);
        // 初始化字体高度
        this.fontHeight = this.fontSize;
        // 如果页面X轴起始坐标未初始化，则进行初始化
        if (this.beginX==null) {
            // 初始化页面X轴起始坐标
            this.beginX = this.isTextAppend?
                    // 左边距 + 当前页面X轴起始坐标
                    this.marginLeft + (page.getParam().getPageX()==null?0F:page.getParam().getPageX()) :
                    // 左边距
                    this.marginLeft;
        }
        // 如果页面Y轴起始坐标未初始化，则进行初始化
        if (this.beginY==null) {
            // 初始化页面Y轴起始坐标
            this.beginY = this.isTextAppend?
                    // 当前页面Y轴起始坐标是否为空
                    (page.getParam().getPageY() == null?
                    // 最大高度 - 上边距 - 字体高度 - 行距
                    this.maxHeight - this.marginTop - this.fontHeight - this.leading :
                    // 当前页面Y轴起始坐标
                    page.getParam().getPageY()) :
                    // 当前页面Y轴起始坐标是否为空
                    (page.getParam().getPageY() == null?
                    // 最大高度 - 上边距 - 字体高度 - 行距
                    this.maxHeight - this.marginTop - this.fontHeight - this.leading :
                    // 当前页面Y轴起始坐标 - 上边距 - 字体高度 - 行距
                    page.getParam().getPageY() - this.marginTop - this.fontHeight - this.leading);
        }
        // 如果拆分后的待添加文本列表未初始化，则进行初始化
        if (this.splitTextList==null) {
            // 开启文本追加
            if (this.isTextAppend) {
                // 首次拆分文本列表
                List<String> splitLines = XEasyPdfTextUtil.splitLines(
                        // 待输入文本
                        this.text,
                        // 行宽度 = 页面宽度 - X轴开始坐标 - 右边距
                        this.maxWidth - this.beginX - this.marginRight,
                        // 字体
                        this.font,
                        // 字体大小
                        this.fontSize
                );
                // 如果拆分文本列表不为空，则初始化待添加文本列表
                if (!splitLines.isEmpty()) {
                    // 初始化待添加文本列表
                    this.splitTextList = new ArrayList<>(splitLines.size());
                    // 获取第一行文本
                    String firstLineText = splitLines.get(0);
                    // 添加第一行文本
                    this.splitTextList.add(firstLineText);
                    // 添加剩余文本
                    this.splitTextList.addAll(
                            XEasyPdfTextUtil.splitLines(
                                    // 待输入文本
                                    this.text.substring(firstLineText.length()),
                                    // 行宽度 = 页面宽度 - 左边距 - 右边距
                                    this.maxWidth - this.marginLeft - this.marginRight,
                                    // 字体
                                    this.font,
                                    // 字体大小
                                    this.fontSize
                            )
                    );
                }else {
                    // 初始化待添加文本列表
                    this.splitTextList = splitLines;
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
            // 添加模板列表
            this.splitTemplateTextList =  new ArrayList<>(splitTextList);
        }
    }

    /**
     * 获取宽度
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回文本宽度
     */
    float getWidth(XEasyPdfDocument document, XEasyPdfPage page, float marginLeft, float marginRight) {
        if (this.maxWidth!=null) {
            return this.maxWidth;
        }
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.init(document, page);
        return this.maxWidth;
    }

    /**
     * 获取高度
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回文本高度
     */
    float getHeight(XEasyPdfDocument document, XEasyPdfPage page, float marginLeft, float marginRight) {
        if (this.maxHeight!=null) {
            return this.maxHeight;
        }
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.init(document, page);
        return (this.fontHeight + this.leading) * this.splitTextList.size() - this.leading + this.marginTop + this.marginBottom;
    }
}
