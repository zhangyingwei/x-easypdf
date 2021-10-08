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
import wiki.xsx.core.pdf.util.XEasyPdfImageUtil;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

/**
 * pdf图片参数
 * @author xsx
 * @date 2020/3/30
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
     * 图片样式（居左、居中、居右）
     */
    private XEasyPdfImageStyle style = XEasyPdfImageStyle.CENTER;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX = 0F;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
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
    PDImageXObject init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果pdfbox图片对象不为空，则返回该对象
        if (this.imageXObject!=null) {
            // 返回该对象
            return this.imageXObject;
        }
        // 如果图片为空，则抛出异常信息
        if (this.image==null) {
            throw new FileNotFoundException("the image can not be found");
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
        // 如果开启自适应图片大小，则自动调整自定义宽度与高度
        if (this.enableSelfAdaption) {
            // 宽度超过页面宽度，则进行调整
            if ((this.width + this.marginLeft + this.marginRight)>pageWidth) {
                // 自定义宽度 = 页面宽度 - 左边距 - 右边距
                this.width = (int) (pageWidth - this.marginLeft - this.marginRight);
                // 自定义高度 = 自定义宽度 * 图片高度 / 图片宽度
                this.height = (int) (this.width * imageHeight / (double) imageWidth );
            }
            // 高度超过页面高度，则进行调整
            else if ((this.height + this.marginTop + this.marginBottom)>pageHeight) {
                // 自定义高度 = 页面高度 - 上边距 - 下边距
                this.height = (int) (pageHeight - this.marginTop - this.marginBottom);
                // 自定义宽度 = 自定义高度 * 图片宽度 / 图片高度
                this.width = (int) (this.height * imageWidth / (double) imageHeight );
            }
        }
        // 如果自定义宽度不等于图片宽度，或自定义高度不等于图片高度，则进行图片缩放
        if (this.width!=imageWidth||this.height!=imageHeight) {
            // 重新创建pdfBox图片
            pdImage = LosslessFactory.createFromImage(
                    // pdfBox文档
                    document.getTarget(),
                    // 图片缩放
                    XEasyPdfImageUtil.scale(
                            this.image,
                            this.width,
                            this.height,
                            this.scaleMode.code
                    )
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
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 获取页面宽度
        float pageWidth = rectangle.getWidth();
        // 获取页面高度
        float pageHeight = rectangle.getHeight();
        // 如果最大宽度未初始化，则进行初始化为页面宽度
        if (this.maxWidth==null) {
            // 初始化最大宽度 = 页面宽度
            this.maxWidth = pageWidth;
        }
        // 如果页面Y轴起始坐标为空，则初始化
        if (this.beginY==null) {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.isAllowFooter()&&page.getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getParam().getFooter().getHeight(document, page);
            }
            // 如果pdfBox最新页面当前Y轴坐标不为空，则不为新页面
            if (page.getParam().getPageY()!=null) {
                // 初始化Y轴坐标
                this.initBeginY(page, pageHeight);
                // 如果页面Y轴起始坐标-页脚高度小于等于下边距，则分页
                if (this.beginY - footerHeight <= this.marginBottom) {
                    // 添加新页面
                    page.addNewPage(document, rectangle);
                    // 初始化Y轴坐标
                    this.initBeginY(page, pageHeight);
                }
            }
            // 如果pdfBox最新页面当前Y轴坐标为空，则为新页面
            else {
                // 初始化Y轴坐标
                this.initBeginY(page,pageHeight);
            }
        }
        // 如果图片样式为空，或图片样式为居中，则初始化页面X轴起始坐标为居中
        if (this.style==null || this.style == XEasyPdfImageStyle.CENTER) {
            // 页面X轴起始坐标 = （最大宽度 - 自定义宽度）/ 2
            this.beginX += (this.maxWidth - this.width) / 2;
            // 如果图片样式为居左，则初始化页面X轴起始坐标为居左
        }else if (this.style == XEasyPdfImageStyle.LEFT) {
            // 页面X轴起始坐标 = 左边距
            this.beginX += this.marginLeft;
            // 如果图片样式为居右，则初始化页面X轴起始坐标为居右
        }else {
            // 页面X轴起始坐标 = 最大宽度 - 自定义宽度 - 右边距
            this.beginX += this.maxWidth - this.width - this.marginRight;
        }
    }

    /**
     * 初始化Y轴坐标
     * @param page pdf页面
     * @param pageHeight 页面高度
     */
    private void initBeginY(XEasyPdfPage page, float pageHeight) {
        // 定义Y轴坐标
        float initY = page.getParam().getPageY()!=null?page.getParam().getPageY():pageHeight;
        // 如果开启垂直居中，则重置Y轴坐标
        if (this.enableVerticalCenterStyle) {
            // 页面Y轴起始坐标 = pdfBox最新页面当前Y轴坐标  - 自定义高度
            this.beginY = initY - this.height;
            // 重新计算Y轴起始坐标 = Y轴起始坐标 - Y轴起始坐标 / 2
            this.beginY -= this.beginY / 2;
        }else {
            // 页面Y轴起始坐标 = pdfBox最新页面当前Y轴坐标 - 上边距 - 自定义高度
            this.beginY = initY - this.marginTop - this.height;
        }
    }
}
