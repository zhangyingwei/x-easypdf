package wiki.xsx.core.pdf.component.line;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;

/**
 * pdf线条组件接口
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
public interface XEasyPdfLine extends XEasyPdfComponent {

    /**
     * 设置定位
     * @param beginX 页面X轴起始坐标
     * @param beginY 页面Y轴起始坐标
     * @param endX 页面X轴结束坐标
     * @param endY 页面Y轴结束坐标
     * @return 返回线条组件
     */
    XEasyPdfLine setPosition(float beginX, float beginY, float endX, float endY);

    /**
     * 设置线条宽度
     * @param lineWidth 线条宽度
     * @return 返回线条组件
     */
    XEasyPdfLine setLineWidth(float lineWidth);

    /**
     * 设置线条线型
     * @param lineCapStyle 线条线型
     * @return 返回线条组件
     */
    XEasyPdfLine setLineCapStyle(XEasyPdfLineCapStyle lineCapStyle);
}
