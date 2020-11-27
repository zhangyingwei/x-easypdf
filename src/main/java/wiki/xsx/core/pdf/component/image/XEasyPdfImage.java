package wiki.xsx.core.pdf.component.image;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfImageUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * pdf图片组件
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
public class XEasyPdfImage implements XEasyPdfComponent {

    /**
     * pdf图片参数
     */
    private final XEasyPdfImageParam param = new XEasyPdfImageParam();

    /**
     * 有参构造
     * @param imageFile 待添加图片
     */
    @SneakyThrows
    public XEasyPdfImage(File imageFile) {
        this.param.setImageType(XEasyPdfImageUtil.parseType(imageFile)).setImage(XEasyPdfImageUtil.read(imageFile));
    }

    /**
     * 有参构造
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型（扩展名）
     */
    @SneakyThrows
    public XEasyPdfImage(InputStream imageStream, String imageType) {
        this.param.setImageType(imageType).setImage(XEasyPdfImageUtil.read(imageStream));
    }

    /**
     * 有参构造
     * @param imageFile 待添加图片
     * @param width 图片宽度
     * @param height 图片高度
     */
    @SneakyThrows
    public XEasyPdfImage(File imageFile, int width, int height) {
        this.param.setImageType(XEasyPdfImageUtil.parseType(imageFile))
                .setImage(XEasyPdfImageUtil.read(imageFile))
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
    public XEasyPdfImage(InputStream imageStream, String imageType, int width, int height) {
        this.param.setImageType(imageType)
                .setImage(XEasyPdfImageUtil.read(imageStream))
                .setWidth(width)
                .setHeight(height);
    }

    /**
     * 设置图片
     * @param imageFile 待添加图片
     * @return 返回图片组件
     */
    @SneakyThrows
    public XEasyPdfImage setImage(File imageFile) {
        this.param.setImageType(XEasyPdfImageUtil.parseType(imageFile)).setImage(XEasyPdfImageUtil.read(imageFile));
        return this;
    }

    /**
     * 设置图片
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型
     * @return 返回图片组件
     */
    @SneakyThrows
    public XEasyPdfImage setImage(InputStream imageStream, String imageType) {
        this.param.setImageType(imageType).setImage(XEasyPdfImageUtil.read(imageStream));
        return this;
    }

    /**
     * 设置图片大小自适应
     * @param enableSelfAdaption 图片大小自适应
     * @return 返回图片组件
     */
    public XEasyPdfImage setEnableSelfAdaption(boolean enableSelfAdaption) {
        this.param.setEnableSelfAdaption(enableSelfAdaption);
        return this;
    }

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回图片组件
     */
    public XEasyPdfImage setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回图片组件
     */
    public XEasyPdfImage setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回图片组件
     */
    public XEasyPdfImage setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回图片组件
     */
    public XEasyPdfImage setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回图片组件
     */
    public XEasyPdfImage setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    /**
     * 设置图片缩放模式（默认、快速、质量）
     * @param scaleMode 缩放模式
     * @return 返回图片组件
     */
    public XEasyPdfImage setScaleMode(ScaleMode scaleMode) {
        this.param.setScaleMode(scaleMode);
        return this;
    }

    /**
     * 设置图片样式（居左、居中、居右）
     * @param style 样式
     * @return 返回图片组件
     */
    public XEasyPdfImage setStyle(XEasyPdfImageStyle style) {
        this.param.setStyle(style);
        return this;
    }

    /**
     * 设置定位
     * @param beginX 当前页面X轴坐标
     * @param beginY 当前页面Y轴坐标
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfImage setPosition(float beginX, float beginY) {
        this.param.setBeginX(beginX).setBeginY(beginY);
        return this;
    }

    /**
     * 设置宽度
     * @param width 宽度
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfImage setWidth(float width) {
        this.param.setWidth((int) width);
        return this;
    }

    /**
     * 设置高度
     * @param height 高度
     * @return 返回图片组件
     */
    @Override
    public XEasyPdfImage setHeight(float height) {
        this.param.setHeight((int) height);
        return this;
    }

    /**
     * 绘制
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
                document.getTarget(),
                page.getLastPage(),
                PDPageContentStream.AppendMode.APPEND,
                true,
                false
        );
        // 添加图片
        contentStream.drawImage(pdImage, this.param.getBeginX(), this.param.getBeginY());
        // 关闭内容流
        contentStream.close();
        // 如果允许页面重置定位，则进行重置
        if (page.getParam().isAllowResetPosition()) {
            // 设置文档页面X轴坐标Y轴坐标
            page.getParam().setPageX(this.param.getBeginX()).setPageY(this.param.getBeginY());
        }
        // 完成标记
        this.param.setDraw(true);
    }

    /**
     * 是否完成绘制
     * @return 返回布尔值，完成为true，未完成为false
     */
    @Override
    public boolean isDraw() {
        return this.param.isDraw();
    }

    /**
     * 图片缩放模式枚举
     */
    public enum ScaleMode {
        /**
         * 默认
         */
        DEFAULT(Image.SCALE_DEFAULT),
        /**
         * 快速
         */
        FAST(Image.SCALE_FAST),
        /**
         * 质量
         */
        SMOOTH(Image.SCALE_SMOOTH);

        /**
         * 缩放模式
         */
        private final int mode;

        /**
         * 有参构造
         * @param mode 缩放模式
         */
        ScaleMode(int mode) {
            this.mode = mode;
        }

        /**
         * 获取缩放模式
         * @return 返回缩放模式
         */
        public int getMode() {
            return mode;
        }
    }
}