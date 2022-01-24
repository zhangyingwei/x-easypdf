package wiki.xsx.core.pdf.component.image;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.footer.XEasyPdfFooter;
import wiki.xsx.core.pdf.header.XEasyPdfHeader;
import wiki.xsx.core.pdf.util.XEasyPdfImageUtil;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * pdf图片参数
 * @author xsx
 * @date 2020/3/30
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
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
class XEasyPdfImageParam {
    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode = XEasyPdfComponent.ContentMode.APPEND;
    /**
     * pdfbox图片对象
     */
    private PDImageXObject imageXObject;
    /**
     * 待添加图片
     */
    private BufferedImage image;
    /**
     * 待添加图片类型
     */
    private String imageType;
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
    private Integer width;
    /**
     * 高度
     */
    private Integer height;
    /**
     * 最大宽度
     */
    private Float maxWidth;
    /**
     * 最大高度
     */
    private Float maxHeight;
    /**
     * 旋转弧度
     */
    private Double radians;
    /**
     * 是否使用自身样式
     */
    private boolean useSelfStyle = false;
    /**
     * 自适应图片大小
     */
    private boolean enableSelfAdaption = true;
    /**
     * 垂直居中样式
     */
    private boolean enableVerticalCenterStyle = false;
    /**
     * 图片压缩模式（质量、速度、平衡，默认为质量）
     */
    private XEasyPdfImageScaleMode scaleMode = XEasyPdfImageScaleMode.QUALITY;
    /**
     * 水平样式（居左、居中、居右）
     */
    private XEasyPdfImageStyle horizontalStyle = XEasyPdfImageStyle.LEFT;
    /**
     * 垂直样式（居上、居中、居下）
     */
    private XEasyPdfImageStyle verticalStyle = XEasyPdfImageStyle.TOP;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX = 0F;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
    /**
     * 是否子组件
     */
    private boolean isChildComponent = false;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回pdfBox图片对象
     */
    @SneakyThrows
    PDImageXObject init(XEasyPdfDocument document, XEasyPdfPage page, XEasyPdfImage image) {
        // 如果pdfbox图片对象不为空，则返回该对象
        if (this.imageXObject!=null) {
            // 返回该对象
            return this.imageXObject;
        }
        // 如果图片为空，则抛出异常信息
        if (this.image==null) {
            throw new FileNotFoundException("the image can not be found");
        }
        // 如果需要旋转，则重置图片为旋转后的图片
        if (this.isRotate()) {
            // 重置图片为旋转后的图片
            this.image = XEasyPdfImageUtil.rotate(this.image, this.radians);
        }
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 获取页面宽度
        float pageWidth = rectangle.getWidth();
        // 获取页面高度
        float pageHeight = rectangle.getHeight();
        // 创建pdfBox图片
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(
                document.getTarget(),
                XEasyPdfImageUtil.toBytes(this.image, this.imageType),
                this.imageType
        );
        // 获取图片宽度
        int imageWidth = pdImage.getWidth();
        // 获取图片高度
        int imageHeight = pdImage.getHeight();
        // 如果自定义宽度为空，则将自定义宽度设置为图片宽度
        if (this.width==null) {
            // 自定义宽度设置为图片宽度
            this.width = imageWidth;
        }
        // 如果自定义高度为空，则将自定义高度设置为图片高度
        if (this.height==null) {
            // 自定义高度设置为图片高度
            this.height = imageHeight;
        }
        // 如果最大宽度未初始化，则进行初始化为页面宽度
        if (this.maxWidth==null) {
            // 初始化最大宽度 = 页面宽度
            this.maxWidth = pageWidth;
        }
        // 如果最大高度未初始化，则进行初始化为页面高度
        if (this.maxHeight==null) {
            // 初始化最大宽度 = 页面高度
            this.maxHeight = pageHeight;
        }
        // 如果开启自适应图片大小，则自动调整自定义宽度与高度
        if (this.enableSelfAdaption) {
            // 获取页眉
            XEasyPdfHeader header = page.getHeader();
            // 获取页脚
            XEasyPdfFooter footer = page.getFooter();
            float headerFooterHeight = 0F;
            if (header!=null&&!header.check(image)) {
                headerFooterHeight += header.getHeight(document, page);
            }
            if (footer!=null&&!footer.check(image)) {
                headerFooterHeight += footer.getHeight(document, page);
            }
            // 高度超过页面高度，则进行调整
            if ((this.height + this.marginTop + this.marginBottom + headerFooterHeight)>pageHeight) {
                // 自定义高度 = 页面高度 - 上边距 - 下边距
                this.height = (int) (page.getParam().getPageY() - this.marginTop - this.marginBottom - headerFooterHeight);
                // 自定义宽度 = 自定义高度 * 图片宽度 / 图片高度
                this.width = (int) (this.height * imageWidth / (double) imageHeight );
            }
            float maxWidth = this.width + this.marginLeft + this.marginRight;
            // 宽度超过页面宽度，则进行调整
            if (maxWidth>pageWidth) {
                maxWidth = this.width - (maxWidth - pageWidth);
                float ratio = (maxWidth) / this.width;
                // 自定义宽度 = 页面宽度 - 左边距 - 右边距
                this.width = (int) (maxWidth);
                // 自定义高度 = 自定义宽度 * 图片高度 / 图片宽度
                this.height = (int) (this.height * ratio );
            }
        }
        // 如果自定义宽度不等于图片宽度，或自定义高度不等于图片高度，则进行图片缩放
        if (this.width!=imageWidth||this.height!=imageHeight) {
            // 重新创建pdfBox图片
            pdImage = LosslessFactory.createFromImage(
                    // pdfBox文档
                    document.getTarget(),
                    // 图片缩放
                    XEasyPdfImageUtil.scale(this.image, this.width, this.height, this.scaleMode.code)
            );
        }
        // 重置pdfbox图片对象
        this.imageXObject = pdImage;
        return this.imageXObject;
    }

    /**
     * 初始化定位
     * @param document pdf文档
     * @param page pdf页面
     */
    void initPosition(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果页面Y轴起始坐标为空，则初始化
        if (this.beginY==null) {
            // 如果pdfBox最新页面当前Y轴坐标不为空，则不为新页面
            if (page.getParam().getPageY()!=null) {
                // 初始化Y轴坐标
                this.initBeginY(document, page);
                // 如果页面Y轴起始坐标-页脚高度小于下边距，则分页
                if (this.beginY < this.marginBottom) {
                    // 添加新页面
                    page.addNewPage(document, page.getLastPage().getMediaBox());
                    // 初始化Y轴坐标
                    this.initBeginY(document, page);
                }
            }
            // 如果pdfBox最新页面当前Y轴坐标为空，则为新页面
            else {
                // 初始化Y轴坐标
                this.initBeginY(document, page);
            }
        }
        // 初始化X轴起始坐标
        this.initBeginX();
    }

    /**
     * 初始化X轴起始坐标
     */
    private void initBeginX() {
        // 如果水平样式为居中，则初始化页面X轴起始坐标为居中
        if (this.horizontalStyle == XEasyPdfImageStyle.CENTER) {
            // 页面X轴起始坐标 = （最大宽度 - 自定义宽度）/ 2
            this.beginX = this.beginX + (this.maxWidth - this.width) / 2;
        }
        // 如果水平样式为居左，则初始化页面X轴起始坐标为居左
        else if (this.horizontalStyle == XEasyPdfImageStyle.LEFT) {
            // 页面X轴起始坐标 = 左边距
            this.beginX = this.beginX + this.marginLeft;
        }
        // 如果水平样式为居右，则初始化页面X轴起始坐标为居右
        else {
            // 页面X轴起始坐标 = 最大宽度 - 自定义宽度 - 右边距
            this.beginX = this.beginX + this.maxWidth - this.width - this.marginRight;
        }
    }

    /**
     * 初始化Y轴坐标
     * @param document pdf文档
     * @param page pdf页面
     */
    private void initBeginY(XEasyPdfDocument document, XEasyPdfPage page) {
        // 获取页面Y轴坐标
        Float pageY = page.getParam().getPageY();
        // 如果垂直样式为居中，则重置Y轴坐标为居中
        if (this.verticalStyle==XEasyPdfImageStyle.CENTER) {
            // 如果页面Y轴坐标为空，则初始化Y轴起始坐标为y坐标
            if (pageY==null) {
                // 初始化Y轴起始坐标为(最大高度-图片高度)/2
                this.beginY = (this.maxHeight - this.height) / 2;
            }
            // 否则初始化
            else {
                // 如果页面Y轴起始坐标未初始化，则进行初始化
                if (this.beginY==null) {
                    // 初始化Y轴起始坐标为(页面Y轴当前坐标-图片高度)/2
                    this.beginY = pageY - this.height - (pageY - this.height) / 2;
                }
                // 如果为子组件，则Y轴起始坐标-最大高度/2
                if (this.isChildComponent) {
                    // 重置Y轴起始坐标为Y轴起始坐标-最大高度/2
                    this.beginY = this.beginY - this.maxHeight / 2;
                }
            }
        }
        // 如果为垂直居上，则重置为最大高度-上边距
        else if (this.verticalStyle==XEasyPdfImageStyle.TOP) {
            // 如果页面Y轴坐标不为空， 则重置为最大高度-上边距
            if (pageY==null) {
                // 初始化Y轴起始坐标重置为最大高度-上边距-图片高度
                this.beginY = this.maxHeight - this.marginTop - this.height;
            }
            // 否则初始化为页面Y轴坐标-上边距
            else {
                if (!this.isChildComponent) {
                    // 初始化Y轴起始坐标重置为页面Y轴坐标-上边距-图片高度
                    this.beginY = pageY - this.marginTop - this.height;
                }
            }
        }
        // 否则垂直样式为居下，则重置Y轴坐标为居下
        else {
            // 定义y坐标为下边距+0.01(补偿，防止分页)
            float y = this.marginBottom + 0.01F;
            // 如果为子组件，则重置Y轴起始坐标
            if (this.isChildComponent) {
                // 重置Y轴起始坐标为Y轴起始坐标-最大高度-y坐标
                this.beginY = this.beginY - (this.maxHeight - y);
            }
            // 否则Y轴起始坐标为y坐标
            else {
                // 重置Y轴起始坐标为y坐标
                this.beginY = y;
                // 获取页脚
                XEasyPdfFooter footer = page.getFooter();
                // 如果页脚不为空，则重置页面Y轴坐标为页面Y轴当前坐标+页脚高度
                if (footer!=null) {
                    // 重置页面Y轴坐标为页面Y轴当前坐标+页脚高度
                    this.beginY = this.beginY + footer.getHeight(document, page);
                }
            }
        }
    }

    /**
     * 是否旋转
     * @return 返回布尔值，是为true，否为false
     */
    private boolean isRotate() {
        return this.radians!=null&&this.radians%360!=0;
    }
}
