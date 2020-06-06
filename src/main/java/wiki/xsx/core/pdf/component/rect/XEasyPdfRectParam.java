package wiki.xsx.core.pdf.component.rect;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;

import java.awt.*;

/**
 * pdf矩形参数
 * @author xsx
 * @date 2020/5/23
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
@Data
@Accessors(chain = true)
class XEasyPdfRectParam {
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
    private Float marginTop = 0F;
    /**
     * 下边距
     */
    private Float marginBottom = 0F;
    /**
     * 宽度
     */
    private Float width;
    /**
     * 高度
     */
    private Float height;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
    /**
     * 是否有边框
     */
    private boolean hasBorder = false;
    /**
     * 背景颜色
     */
    private Color backgroundColor = Color.WHITE;
    /**
     * 边框颜色
     */
    private Color borderColor = Color.BLACK;
    /**
     * 页面检查
     */
    private boolean checkPage = true;
    /**
     * 是否自动换行
     */
    private boolean isNewLine = true;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果宽度未设置，则抛异常
        if (this.width==null) {
            throw new RuntimeException("the width can not null");
        }
        // 如果高度未设置，则抛异常
        if (this.height==null) {
            throw new RuntimeException("the height can not null");
        }
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 如果页面Y轴起始坐标未初始化，则进行初始化
        if (this.beginY==null) {
            // 获取当前页面Y轴起始坐标
            Float pageY = page.getParam().getPageY();
            // 初始化页面Y轴起始坐标，如果当前页面Y轴坐标为空，则起始坐标 = 最大高度 - 上边距 - 矩形高度，否则起始坐标 = 当前页面Y轴起始坐标 - 上边距 - 矩形高度
            this.beginY = pageY == null?
                    rectangle.getHeight() - this.marginTop - this.height :
                    pageY - this.marginTop - this.height;
        }
        // 如果检查页面为真，并且Y轴起始坐标小于等于下边距，则进行分页
        if (checkPage && this.beginY <= this.marginBottom) {
            // 添加新页面
            page.addPage(new PDPage(rectangle)).getParam().setPageX(null).setPageY(null);
            // 初始化页面Y轴起始坐标 = 最大高度 - 上边距 - 矩形高度
            this.beginY = rectangle.getHeight() - this.marginTop - this.height;
        }
        // 如果页面Y轴起始坐标未初始化，则进行初始化
        if (this.beginX==null) {
            // 获取当前页面X轴起始坐标
            Float pageX = page.getParam().getPageX();
            // 初始化页面X轴起始坐标，如果当前页面Y轴坐标为空，则起始坐标 = 左边距，否则起始坐标 = 当前页面X轴起始坐标 + 左边距
            this.beginX = pageX == null?
                    this.marginLeft :
                    pageX + this.marginLeft;
        }
    }
}
