package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDefaultFontStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.doc.XEasyPdfPositionStyle;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.awt.*;

/**
 * pdf单元格参数
 * @author xsx
 * @date 2020/6/6
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
class XEasyPdfCellParam {
    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode;
    /**
     * 是否重置上下文
     */
    private Boolean isResetContext;
    /**
     * 是否水平合并
     */
    private boolean isHorizontalMerge = false;
    /**
     * 是否垂直合并
     */
    private boolean isVerticalMerge = false;
    /**
     * 是否组件换行
     */
    private boolean isNewLine = true;
    /**
     * 是否组件样式
     */
    private boolean isComponentSelfStyle = false;
    /**
     * 是否带有边框
     */
    private Boolean hasBorder;
    /**
     * 是否带有上边框
     */
    private boolean hasTopBorder = true;
    /**
     * 是否带有下边框
     */
    private boolean hasBottomBorder = true;
    /**
     * 是否带有左边框
     */
    private boolean hasLeftBorder = true;
    /**
     * 是否带有右边框
     */
    private boolean hasRightBorder = true;
    /**
     * 背景颜色
     */
    private Color backgroundColor;
    /**
     * 边框颜色
     */
    private Color borderColor;
    /**
     * 边框宽度
     */
    private Float borderWidth;
    /**
     * 列宽
     */
    private Float width;
    /**
     * 列高
     */
    private Float height;
    /**
     * pdf组件
     */
    private XEasyPdfComponent component;
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
    private Float fontSize;
    /**
     * 字体颜色
     */
    private Color fontColor;
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 上边距
     */
    private Float marginTop = 0F;
    /**
     * 表格样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfPositionStyle horizontalStyle;
    /**
     * 表格样式（居上、居中、居下）
     * 默认居上
     */
    private XEasyPdfPositionStyle verticalStyle;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @param row pdf表格行
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfRow row) {
        // 获取pdf表格行参数
        XEasyPdfRowParam rowParam = row.getParam();
        // 如果边框标记为空，则初始化边框标记
        if (this.hasBorder==null) {
            // 初始化边框标记
            this.hasBorder = rowParam.getHasBorder();
        }
        // 如果内容模式未初始化，则初始化为页面内容模式
        if (this.contentMode==null) {
            // 初始化为页面内容模式
            this.contentMode = rowParam.getContentMode();
        }
        // 如果是否重置上下文未初始化，则初始化为页面是否重置上下文
        if (this.isResetContext==null) {
            // 初始化为页面是否重置上下文
            this.isResetContext = rowParam.getIsResetContext();
        }
        // 如果默认字体未初始化，则初始化为页面默认字体
        if (this.defaultFontStyle==null) {
            // 初始化为页面默认字体
            this.defaultFontStyle = rowParam.getDefaultFontStyle();
        }
        // 如果字体路径未初始化，则初始化为默认字体路径
        if (this.fontPath==null) {
            // 初始化为默认字体路径
            this.fontPath = this.defaultFontStyle.getPath();
        }
        // 初始化字体
        this.font = XEasyPdfFontUtil.getFont(document, this.fontPath, rowParam.getFont());
        // 如果字体大小未初始化，则进行初始化
        if (this.fontSize==null) {
            // 初始化字体大小
            this.fontSize = rowParam.getFontSize();
        }
        // 如果字体颜色未初始化，则进行初始化
        if (this.fontColor==null) {
            // 初始化字体颜色
            this.fontColor = rowParam.getFontColor();
        }
        // 如果边框宽度未初始化，则进行初始化
        if (this.borderWidth==null) {
            // 初始化边框宽度
            this.borderWidth = rowParam.getBorderWidth();
        }
        // 如果边框颜色未初始化，则进行初始化
        if (this.borderColor==null) {
            // 初始化边框颜色
            this.borderColor = rowParam.getBorderColor();
        }
        // 如果背景颜色未初始化，则进行初始化
        if (this.backgroundColor==null) {
            // 初始化背景颜色
            this.backgroundColor = rowParam.getBackgroundColor();
        }
        // 如果水平样式未初始化，则进行初始化
        if (this.horizontalStyle ==null) {
            // 初始化水平样式
            this.horizontalStyle = rowParam.getHorizontalStyle();
        }
        // 如果垂直样式未初始化，则进行初始化
        if (this.verticalStyle ==null) {
            // 初始化垂直样式
            this.verticalStyle = rowParam.getVerticalStyle();
        }
    }
}
