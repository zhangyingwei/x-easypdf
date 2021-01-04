package wiki.xsx.core.pdf.header;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.XEasyPdfLine;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf页眉组件参数
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
class XEasyPdfHeaderParam {
    /**
     * 文本
     */
    private XEasyPdfText text;
    /**
     * 图片
     */
    private XEasyPdfImage image;
    /**
     * 分割线列表
     */
    private List<XEasyPdfLine> lineList = new ArrayList<>(6);
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 右边距
     */
    private Float marginRight = 0F;
    /**
     * 上边距
     */
    private Float marginTop = 5F;
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
     * 文本定位（重置页面X轴Y轴坐标）
     */
    private boolean isTextPosition = true;

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
        // 计算图片高度
        float imageHeight = this.image!=null?this.image.getHeight(document, page):0F;
        // 计算文本高度
        float textHeight = this.text!=null?this.text.getHeight(document, page, this.marginLeft, this.marginRight):0F;
        // 获取pdfBox最新页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 初始化X轴起始坐标
        this.beginX = this.marginLeft;
        // 初始化Y轴起始坐标
        this.beginY = rectangle.getHeight() - imageHeight - this.marginTop;
        // 如果图片高度大于文本高度，则重置文本定位为否
        if (imageHeight>textHeight) {
            // 设置文本定位为否
            this.isTextPosition = false;
        }
        // 如果高度未初始化，则进行初始化
        if (this.height==null) {
            // 初始化高度，文本高度与图片高度取最大值 + 上边距
            this.height = Math.max(imageHeight, textHeight) + this.marginTop;
        }
    }
}
