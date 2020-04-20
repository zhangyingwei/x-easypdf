package wiki.xsx.core.pdf.component.line;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;

/**
 * 实线分割线组件接口
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
public interface XEasyPdfSolidSplitLine extends XEasyPdfComponent {
    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setMargin(float margin);

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setMarginLeft(float margin);

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setMarginRight(float margin);

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setMarginTop(float margin);

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setMarginBottom(float margin);

    /**
     * 设置分割线宽度
     * @param lineWidth 分割线宽度
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setLineWidth(float lineWidth);

    /**
     * 设置分割线线型
     * @param lineCapStyle 分割线线型
     * @return 返回实线分割线组件
     */
    XEasyPdfSolidSplitLine setLineCapStyle(XEasyPdfLineCapStyle lineCapStyle);
}
