package wiki.xsx.core.pdf.component.line;

import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;

import java.awt.*;

/**
 * pdf线条组件
 * @author xsx
 * @date 2020/12/8
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
public interface XEasyPdfLine extends XEasyPdfComponent {

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回线条组件
     */
    XEasyPdfLine setFontPath(String fontPath);

    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回线条组件
     */
    XEasyPdfLine setFont(PDFont font);

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回线条组件
     */
    XEasyPdfLine setMarginLeft(float margin);

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回线条组件
     */
    XEasyPdfLine setMarginRight(float margin);

    /**
     * 设置线条宽度
     * @param lineWidth 线条宽度
     * @return 返回线条组件
     */
    XEasyPdfLine setLineWidth(float lineWidth);

    /**
     * 设置线条颜色
     * @param color 线条颜色
     * @return 返回线条组件
     */
    XEasyPdfLine setColor(Color color);

    /**
     * 设置线条线型
     * @param lineCapStyle 线条线型
     * @return 返回线条组件
     */
    XEasyPdfLine setLineCapStyle(XEasyPdfLineCapStyle lineCapStyle);
}
