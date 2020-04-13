package wiki.xsx.core.pdf.component.image;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import wiki.xsx.core.pdf.component.XEasyPdfComponentBuilder;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片组件
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
public class XEasyPdfDefaultImage implements XEasyPdfImage, XEasyPdfComponentBuilder {

    /**
     * 图片参数
     */
    private XEasyPdfImageParam param = new XEasyPdfImageParam();

    /**
     * 有参构造
     * @param imageFile 待添加图片
     */
    @SneakyThrows
    public XEasyPdfDefaultImage(File imageFile) {
        this.param.setImageType(ImageUtil.parseType(imageFile)).setImage(ImageUtil.read(imageFile));
    }

    /**
     * 有参构造
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型（扩展名）
     */
    @SneakyThrows
    public XEasyPdfDefaultImage(InputStream imageStream, String imageType) {
        this.param.setImageType(imageType).setImage(ImageUtil.read(imageStream));
    }

    /**
     * 有参构造
     * @param imageFile 待添加图片
     * @param width 图片宽度
     * @param height 图片高度
     */
    @SneakyThrows
    public XEasyPdfDefaultImage(File imageFile, int width, int height) {
        this.param.setImageType(ImageUtil.parseType(imageFile))
                .setImage(ImageUtil.read(imageFile))
                .setWidth(width)
                .setHeight(height);
    }

    /**
     * 有参构造
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型（扩展名）
     * @param width 图片宽度
     * @param height 图片高度
     */
    @SneakyThrows
    public XEasyPdfDefaultImage(InputStream imageStream, String imageType, int width, int height) {
        this.param.setImageType(imageType)
                .setImage(ImageUtil.read(imageStream))
                .setWidth(width)
                .setHeight(height);
    }

    /**
     * 有参构造
     * @param imageFile 待添加图片
     * @param width 图片宽度
     * @param height 图片高度
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     */
    @SneakyThrows
    public XEasyPdfDefaultImage(File imageFile, int width, int height, float beginX, float beginY) {
        this.param.setImageType(ImageUtil.parseType(imageFile))
                .setImage(ImageUtil.read(imageFile))
                .setWidth(width)
                .setHeight(height)
                .setBeginX(beginX)
                .setBeginY(beginY);
    }

    /**
     * 有参构造
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型（扩展名）
     * @param width 图片宽度
     * @param height 图片高度
     * @param beginX X轴起始坐标
     * @param beginY Y轴起始坐标
     */
    @SneakyThrows
    public XEasyPdfDefaultImage(InputStream imageStream, String imageType, int width, int height, float beginX, float beginY) {
        this.param.setImageType(imageType)
                .setImage(ImageUtil.read(imageStream))
                .setWidth(width)
                .setHeight(height)
                .setBeginX(beginX)
                .setBeginY(beginY);
    }

    /**
     * 设置图片
     * @param imageFile 待添加图片
     * @return 返回图片组件
     */
    @SneakyThrows
    @Override
    public XEasyPdfDefaultImage setImage(File imageFile) {
        this.param.setImageType(ImageUtil.parseType(imageFile)).setImage(ImageUtil.read(imageFile));
        return this;
    }

    /**
     * 设置图片
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型
     * @return 返回图片组件
     */
    @SneakyThrows
    @Override
    public XEasyPdfDefaultImage setImage(InputStream imageStream, String imageType) {
        this.param.setImageType(imageType).setImage(ImageUtil.read(imageStream));
        return this;
    }

    /**
     * 设置图片宽度
     * @param width 图片宽度
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setWidth(int width) {
        this.param.setWidth(width);
        return this;
    }

    /**
     * 设置图片高度
     * @param height 图片高度
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setHeight(int height) {
        this.param.setHeight(height);
        return this;
    }

    /**
     * 设置图片大小自适应
     * @param enableSelfAdaption 图片大小自适应
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setEnableSelfAdaption(boolean enableSelfAdaption) {
        this.param.setEnableSelfAdaption(enableSelfAdaption);
        return this;
    }

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置定位
     * @param x 当前页面X轴坐标
     * @param y 当前页面Y轴坐标
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setPosition(float x, float y) {
        this.param.setBeginX(x).setBeginY(y);
        return this;
    }

    /**
     * 设置图片缩放模式（默认、快速、质量）
     * @param scaleMode 缩放模式
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setScaleMode(XEasyPdfImage.ScaleMode scaleMode) {
        this.param.setScaleMode(scaleMode);
        return this;
    }

    /**
     * 设置图片样式（居左、居中、居右）
     * @param style 样式
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfDefaultImage setStyle(XEasyPdfImageStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 画图
     *
     * @param document pdf文档
     * @param page     pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化pdfBox图片
        PDImageXObject pdImage = this.param.init(document, page);
        // 新建内容流
        PDPageContentStream contentStream = new PDPageContentStream(
                document.getDocument(),
                page.getLastPage(),
                PDPageContentStream.AppendMode.APPEND,
                true,
                false
        );
        // 添加图片
        contentStream.drawImage(pdImage, this.param.getBeginX(), this.param.getBeginY());
        // 关闭内容流
        contentStream.close();
        // 设置文档页面X轴坐标
        page.getParam().setPageX(this.param.getBeginX());
        // 设置文档页面Y轴坐标
        page.getParam().setPageY(this.param.getBeginY());
    }
}