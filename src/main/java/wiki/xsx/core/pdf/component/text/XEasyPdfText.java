package wiki.xsx.core.pdf.component.text;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;

/**
 * @author xsx
 * @date 2020/4/3
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
public interface XEasyPdfText extends XEasyPdfComponent {
    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回文本组件
     */
    XEasyPdfText setMargin(float margin);

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回文本组件
     */
    XEasyPdfText setMarginLeft(float margin);

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回文本组件
     */
    XEasyPdfText setMarginRight(float margin);

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回文本组件
     */
    XEasyPdfText setMarginTop(float margin);

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回文本组件
     */
    XEasyPdfText setMarginBottom(float margin);

    /**
     * 设置定位
     * @param x 当前页面X轴坐标
     * @param y 当前页面Y轴坐标
     * @return 返回文本组件
     */
    XEasyPdfText setPosition(float x, float y);

    /**
     * 设置行间距
     * @param leading 行间距
     * @return 返回文本组件
     */
    XEasyPdfText setLeading(float leading);

    /**
     * 设置字体
     * @param fontPath 字体路径
     * @return 返回文本组件
     */
    XEasyPdfText setFont(String fontPath);

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回文本组件
     */
    XEasyPdfText setFontSize(float fontSize);

    /**
     * 设置文本样式（居左、居中、居右）
     * @param style 样式
     * @return 返回文本组件
     */
    XEasyPdfText setStyle(XEasyPdfTextStyle style);
}
