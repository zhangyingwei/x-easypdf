package wiki.xsx.core.pdf.component.table;

import wiki.xsx.core.pdf.component.image.XEasyPdfImageStyle;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;

/**
 * 表格样式枚举
 * @author xsx
 * @date 2020/12/29
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
public enum XEasyPdfTableStyle {
    /**
     * 居中
     */
    CENTER(XEasyPdfTextStyle.CENTER, XEasyPdfImageStyle.CENTER),
    /**
     * 居左
     */
    LEFT(XEasyPdfTextStyle.LEFT, XEasyPdfImageStyle.LEFT),
    /**
     * 居右
     */
    RIGHT(XEasyPdfTextStyle.RIGHT, XEasyPdfImageStyle.RIGHT);

    /**
     * 文本样式
     */
    private final XEasyPdfTextStyle textStyle;
    /**
     * 图片样式
     */
    private final XEasyPdfImageStyle imageStyle;

    /**
     * 有参构造
     * @param textStyle 文本样式
     * @param imageStyle 图片样式
     */
    XEasyPdfTableStyle(XEasyPdfTextStyle textStyle, XEasyPdfImageStyle imageStyle) {
        this.textStyle = textStyle;
        this.imageStyle = imageStyle;
    }

    /**
     * 获取文本样式
     * @return 返回文本样式
     */
    public XEasyPdfTextStyle getTextStyle() {
        return this.textStyle;
    }

    /**
     * 获取图片样式
     * @return 返回图片样式
     */
    public XEasyPdfImageStyle getImageStyle() {
        return this.imageStyle;
    }
}
