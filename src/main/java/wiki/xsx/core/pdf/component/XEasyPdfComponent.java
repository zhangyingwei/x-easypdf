package wiki.xsx.core.pdf.component;


import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;

/**
 * pdf组件接口
 * @author xsx
 * @date 2020/3/3
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
public interface XEasyPdfComponent {

    /**
     * 设置坐标
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     * @return 返回pdf组件
     */
    XEasyPdfComponent setPosition(float beginX, float beginY);

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回pdf组件
     */
    XEasyPdfComponent setWidth(float width);

    /**
     * 设置高度
     * @param height 高度
     * @return 返回pdf组件
     */
    XEasyPdfComponent setHeight(float height);

    /**
     * 绘制
     * @param document pdf文档
     * @param page pdf页面
     */
    void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException;

    /**
     * 是否完成绘制
     * @return 返回布尔值，完成为true，未完成为false
     */
    boolean isDraw();
}
