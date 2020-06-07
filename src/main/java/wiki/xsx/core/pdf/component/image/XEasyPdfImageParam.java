package wiki.xsx.core.pdf.component.image;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * pdf图片参数
 * @author xsx
 * @date 2020/3/30
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
class XEasyPdfImageParam {
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
     * 自适应图片大小
     */
    private boolean enableSelfAdaption = true;
    /**
     * 图片缩放模式（默认）
     */
    private XEasyPdfImage.ScaleMode scaleMode = XEasyPdfImage.ScaleMode.DEFAULT;
    /**
     * 图片样式（居左、居中、居右）
     */
    private XEasyPdfImageStyle style = XEasyPdfImageStyle.CENTER;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX;
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
     * @throws IOException IO异常
     */
    PDImageXObject init(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
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
                document.getDocument(),
                ImageUtil.toBytes(this.image, this.imageType),
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
        if (enableSelfAdaption&&(this.width + this.marginLeft + this.marginRight)>pageWidth) {
            // 自定义宽度 = 页面宽度 - 左边距 - 右边距
            this.width = (int) (pageWidth - this.marginLeft - this.marginRight);
            // 自定义高度 = 自定义宽度 / 图片宽度 * 图片高度，即图片宽度的缩放比率 * 图片高度
            this.height = (int) (this.width / (double) imageWidth * imageHeight);
        }
        // 如果自定义宽度不等于图片宽度，或自定义高度不等于图片高度，则进行图片缩放
        if (this.width!=imageWidth||this.height!=imageHeight) {
            // 重新创建pdfBox图片
            pdImage = LosslessFactory.createFromImage(
                    // pdfBox文档
                    document.getDocument(),
                    // 图片缩放
                    ImageUtil.scale(
                            this.image,
                            this.width,
                            this.height,
                            this.scaleMode.getMode()
                    )
            );
        }
        // 如果页面Y轴起始坐标为空，则初始化
        if (this.beginY==null) {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.getParam().isAllowFooter()&&page.getParam().getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getParam().getFooter().getHeight();
            }
            // 如果pdfBox最新页面当前Y轴坐标不为空，则不为新页面
            if (page.getParam().getPageY()!=null) {
                // 页面Y轴起始坐标 = pdfBox最新页面当前Y轴坐标 - 上边距 - 自定义高度
                this.beginY = page.getParam().getPageY() - this.marginTop - this.height;
                // 如果页面Y轴起始坐标-页脚高度小于等于下边距，则分页
                if (this.beginY - footerHeight <= this.marginBottom) {
                    // 添加新页面
                    page.addNewPage(rectangle, document);
                    // 重置页面Y轴起始坐标 = 页面高度 - 上边距 - 自定义高度
                    this.beginY = page.getParam().getPageY()==null?
                            pageHeight - this.marginTop - this.height:
                            page.getParam().getPageY() - this.marginTop - this.height;
                }
                // 如果pdfBox最新页面当前Y轴坐标为空，则为新页面
            }else {
                // 页面Y轴起始坐标 = 页面高度 - 上边距 - 自定义高度
                this.beginY = pageHeight - this.marginTop - this.height;
            }
        }
        // 如果页面X轴起始坐标为空，则初始化
        if (this.beginX==null) {
            // 如果图片样式为空，或图片样式为居中，则初始化页面X轴起始坐标为居中
            if (this.style==null || this.style == XEasyPdfImageStyle.CENTER) {
                // 页面X轴起始坐标 = （页面宽度 - 自定义宽度）/ 2
                this.beginX = (pageWidth - this.width) / 2;
            // 如果图片样式为居左，则初始化页面X轴起始坐标为居左
            }else if (this.style == XEasyPdfImageStyle.LEFT) {
                // 页面X轴起始坐标 = 左边距
                this.beginX = this.marginLeft;
            // 如果图片样式为居右，则初始化页面X轴起始坐标为居右
            }else {
                // 页面X轴起始坐标 = 页面宽度 - 自定义宽度 - 右边距
                this.beginX = pageWidth - this.width - this.marginRight;
            }
        }
        return pdImage;
    }
}
