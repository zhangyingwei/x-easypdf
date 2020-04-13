package wiki.xsx.core.pdf.component.text;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.FontUtil;
import wiki.xsx.core.pdf.util.TextUtil;

import java.io.IOException;
import java.util.List;

/**
 * pdf文本参数
 * @author xsx
 * @date 2020/3/9
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
@Data
@Accessors(chain = true)
public class XEasyPdfTextParam {
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
     * 行间距
     */
    private Float leading = 0F;
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
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    public void init(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 获取pdfBox最新页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 如果最大宽度未初始化，则进行初始化
        if (this.maxWidth==null) {
            // 初始化最大宽度，最大宽度 = 页面宽度
            this.maxWidth = rectangle.getWidth();
        }
        // 如果最大高度未初始化，则进行初始化
        if (this.maxHeight==null) {
            // 初始化最大高度，最大高度 = 页面高度
            this.maxHeight = rectangle.getHeight();
        }
        // 如果字体未初始化，则进行初始化
        if (this.font==null) {
            // 初始化字体
            this.font = FontUtil.loadFont(document, page, this.fontPath);
        }
        // 如果页面X轴起始坐标未初始化，则进行初始化
        if (this.beginX==null) {
            // 初始化页面X轴起始坐标，起始坐标 = 左边距
            this.beginX = this.marginLeft;
        }
        // 如果页面Y轴起始坐标未初始化，则进行初始化
        if (this.beginY==null) {
            // 初始化页面Y轴起始坐标，如果当前页面Y轴坐标为空，则起始坐标 = 最大高度 - 上边距 - 字体大小 - 行距，否则起始坐标 = 当前页面Y轴起始坐标 - 上边距 - 字体大小 - 行距
            this.beginY = page.getParam().getPageY() == null?
                    // 最大高度 - 上边距 - 字体大小 - 行距
                    this.maxHeight - this.marginTop - this.fontSize - this.leading :
                    // 当前页面Y轴起始坐标 - 上边距 - 字体大小 - 行距
                    page.getParam().getPageY() - this.marginTop - this.fontSize - this.leading;
        }
        // 如果拆分后的待添加文本列表未初始化，则进行初始化
        if (this.splitTextList==null) {
            // 初始化待添加文本列表
            this.splitTextList =  TextUtil.splitLines(
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
}
