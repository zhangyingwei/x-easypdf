package wiki.xsx.core.pdf.footer;

import lombok.Data;
import lombok.experimental.Accessors;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;

/**
 * pdf页脚组件参数
 * @author xsx
 * @date 2020/6/7
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
@Data
@Accessors(chain = true)
class XEasyPdfFooterParam {
    /**
     * 文本
     */
    private XEasyPdfText text;
    /**
     * 图片
     */
    private XEasyPdfImage image;
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
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 如果文本和图片都未初始化，则抛出异常信息
        if (this.text==null&&this.image==null) {
            throw new IllegalArgumentException("text or image can not be found");
        }
        // 如果高度未初始化，则进行初始化
        if (this.height==null) {
            // 初始化高度，文本高度与图片高度取最大值，加上下边距
            this.height = Math.max(
                    this.text==null?0:this.text.getHeight(document, page, this.marginLeft, this.marginRight),
                    this.image==null?0:this.image.getHeight(document, page)
            ) + this.marginBottom;
        }
        // 初始化X轴起始坐标
        this.beginX = this.marginLeft;
        // 初始化Y轴起始坐标
        this.beginY = this.height;

    }
}
