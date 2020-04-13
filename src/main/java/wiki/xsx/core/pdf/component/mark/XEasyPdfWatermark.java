package wiki.xsx.core.pdf.component.mark;

import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * pdf水印接口
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
public interface XEasyPdfWatermark {
    /**
     * 设置字体
     * @param font pdfBox字体
     * @return 返回页面水印组件
     */
    XEasyPdfWatermark setFont(PDFont font);

    /**
     * 设置字体大小
     * @param fontSize 字体大小
     * @return 返回页面水印组件
     */
    XEasyPdfWatermark setFontSize(float fontSize);

    /**
     * 设置文本透明度
     * @param alpha 文本透明度
     * @return 返回页面水印组件
     */
    XEasyPdfWatermark setAlpha(float alpha);

    /**
     * 设置文本弧度
     * @param radians 文本弧度
     * @return 返回页面水印组件
     */
    XEasyPdfWatermark setRadians(double radians);

    /**
     * 设置水印文本
     * @param text 水印文本
     * @return 返回页面水印组件
     */
    XEasyPdfWatermark setText(String text);

    /**
     * 设置水印文本间距
     * @param wordSpace 水印文本间距
     * @return 返回页面水印组件
     */
    XEasyPdfWatermark setWordSpace(float wordSpace);
}
