package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;
import wiki.xsx.core.pdf.util.XEasyPdfTextUtil;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf单元格参数
 * @author xsx
 * @date 2020/6/6
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
class XEasyPdfCellParam {
    /**
     * 是否带有边框
     */
    private boolean hasBorder = true;
    /**
     * 背景颜色
     */
    private Color backgroundColor = Color.WHITE;
    /**
     * 边框颜色
     */
    private Color borderColor = Color.BLACK;
    /**
     * 列宽
     */
    private Float width;
    /**
     * 列高
     */
    private Float height;
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
    private Float fontSize;
    /**
     * 字体颜色
     */
    private Color fontColor = Color.BLACK;
    /**
     * 边框宽度
     */
    private float lineWidth = 1F;
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 上边距
     */
    private Float marginTop = 0F;
    /**
     * 文本左边距
     */
    private Float textMarginLeft;
    /**
     * 文本右边距
     */
    private Float textMarginRight;
    /**
     * 文本上边距
     */
    private Float textMarginTop = 0F;
    /**
     * 文本下边距
     */
    private Float textMarginBottom = 0F;
    /**
     * 文本样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfTextStyle style;

    /**
     * 初始化
     * @param document pdf文档
     * @param row pdf表格行
     * @throws IOException IO异常
     */
    void init(XEasyPdfDocument document, XEasyPdfRow row) throws IOException {
        // 获取pdf表格行参数
        XEasyPdfRowParam rowParam = row.getParam();
        // 如果字体未初始化，则进行初始化
        if (this.font==null) {
            // 初始化字体
            this.font = XEasyPdfFontUtil.getFont(document, this.fontPath, rowParam.getFont());
        }
        // 如果字体大小未初始化，则进行初始化
        if (this.fontSize==null) {
            // 初始化字体大小
            this.fontSize = rowParam.getFontSize();
        }
        // 如果文本样式未初始化，则进行初始化
        if (this.style==null) {
            // 初始化文本样式
            this.style = rowParam.getStyle();
        }
        // 如果文本左边距未初始化，则进行初始化
        if (this.textMarginLeft==null) {
            // 初始化文本左边距 = 2F
            this.textMarginLeft = 2F;
        }
        // 如果文本右边距未初始化，则进行初始化
        if (this.textMarginRight==null) {
            // 初始化文本右边距 = 2F
            this.textMarginRight = 2F;
        }
        // 如果待添加文本不为空，则进行待添加文本列表初始化
        if (this.text!=null&&this.text.trim().length()>0) {
            // 如果宽度未初始化，则进行初始化
            if (this.width==null) {
                // 文本左边距默认设置为2F
                this.textMarginLeft = 2F;
                // 文本右边距默认设置为2F
                this.textMarginRight = 2F;
                // 初始化宽度 = 文本的真实宽度
                this.width = this.fontSize * this.font.getStringWidth(this.text) / 1000 + this.textMarginLeft + this.textMarginRight;
                // 初始化待添加文本列表
                this.splitTextList = new ArrayList<>(1);
                // 添加文本
                splitTextList.add(this.text);
            }else{
                // 初始化待添加文本列表
                this.splitTextList = XEasyPdfTextUtil.splitLines(this.text, (this.width - this.textMarginLeft - this.textMarginRight), this.font, this.fontSize);
            }
        }else {
            // 如果宽度为空，则初始化宽度与高度为0F
            if (this.width==null) {
                this.width = 0F;
                this.height = 0F;
            }
        }
        // 如果高度未初始化，则进行初始化
        if (this.height==null) {
            // 文本上边距默认设置为5F
            this.textMarginTop = 5F;
            // 初始化高度 = 字体大小 * 待添加文本列表行数 + 文本上边距 + 文本下边距
            this.height = this.fontSize * this.splitTextList.size() + this.textMarginTop + this.textMarginBottom;
        }
    }
}
