package wiki.xsx.core.pdf.component.footer;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;
import wiki.xsx.core.pdf.util.XEasyPdfTextUtil;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * pdf页脚组件参数
 * @author xsx
 * @date 2020/6/7
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
class XEasyPdfFooterParam {
    /**
     * 文本
     */
    private String text;
    /**
     * 拆分后的待添加文本列表
     */
    private List<String> splitTextList;
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
    private Float fontSize = 10F;
    /**
     * 行间距
     */
    private Float leading = 1F;
    /**
     * 字体颜色
     */
    private Color fontColor = Color.BLACK;
    /**
     * 文本样式（居左、居中、居右）
     * 默认居中
     */
    private XEasyPdfTextStyle style = XEasyPdfTextStyle.CENTER;
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 右边距
     */
    private Float marginRight = 0F;
    /**
     * 下边距
     */
    private Float marginBottom = 5F;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
    /**
     * 高度
     */
    private Float height;

    /**
     * 初始化
     * @param page pdf页面
     * @throws IOException IO异常
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        if (this.font==null) {
            this.font = XEasyPdfFontUtil.loadFont(document, page, this.fontPath);
        }
        // 如果拆分后的待添加文本列表未初始化，则进行初始化
        if (this.splitTextList==null) {
            // 初始化待添加文本列表
            this.splitTextList =  XEasyPdfTextUtil.splitLines(
                    // 待输入文本
                    this.text,
                    // 行宽度 = 页面宽度 - 左边距 - 右边距
                    page.getLastPage().getMediaBox().getWidth() - this.marginLeft - this.marginRight,
                    // 字体
                    this.font,
                    // 字体大小
                    this.fontSize
            );
        }
        // 初始化X轴起始坐标
        this.beginX = this.marginLeft;
        // 初始化Y轴起始坐标
        this.beginY = this.marginBottom + this.fontSize * (this.splitTextList.size() - 1);
        // 初始化高度
        this.height = this.beginY;
    }
}
