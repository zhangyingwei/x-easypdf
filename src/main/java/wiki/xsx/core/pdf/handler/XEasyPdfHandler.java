package wiki.xsx.core.pdf.handler;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfDefaultImage;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.*;
import wiki.xsx.core.pdf.component.mark.XEasyPdfDefaultWatermark;
import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;
import wiki.xsx.core.pdf.component.text.XEasyPdfDefaultText;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;

import java.io.File;
import java.io.InputStream;

/**
 * pdf助手
 * @author xsx
 * @date 2020/4/1
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
public class XEasyPdfHandler {

    /**
     * pdf文档
     */
    public static class Document {
        /**
         * 构建pdf
         */
        public static XEasyPdfDocument build() {
            return new XEasyPdfDocument();
        }

        /**
         * 构建pdf
         * @param sourcePath 源文件路径
         */
        @SneakyThrows
        public static XEasyPdfDocument build(String sourcePath) {
            return new XEasyPdfDocument(sourcePath);
        }

        /**
         * 构建pdf
         * @param sourceInputStream 源文件数据流
         */
        @SneakyThrows
        public static XEasyPdfDocument build(InputStream sourceInputStream) {
            return new XEasyPdfDocument(sourceInputStream);
        }
    }

    /**
     * pdf页面
     */
    public static class Page {
        /**
         * 构建页面
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(XEasyPdfComponent...components) {
            return new XEasyPdfPage().addComponent(components);
        }

        /**
         * 构建页面
         * @param components 组件
         * @param watermark 页面水印组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(XEasyPdfWatermark watermark, XEasyPdfComponent...components) {
            return new XEasyPdfPage().setWatermark(watermark).addComponent(components);
        }

        /**
         * 构建页面
         * @param pageSize pdfBox页面尺寸
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(PDRectangle pageSize, XEasyPdfComponent...components) {
            return new XEasyPdfPage(pageSize).addComponent(components);
        }

        /**
         * 构建页面
         * @param pageSize pdfBox页面尺寸
         * @param watermark 页面水印组件
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(PDRectangle pageSize, XEasyPdfWatermark watermark, XEasyPdfComponent...components) {
            return new XEasyPdfPage(pageSize).setWatermark(watermark).addComponent(components);
        }
    }

    /**
     * 页面水印组件
     */
    public static class Watermark {
        /**
         * 构建页面水印
         * @param fontPath 字体路径
         * @param text 水印文本
         * @return 返回pdf页面水印组件
         */
        public static XEasyPdfWatermark build(String fontPath, String text) {
            return new XEasyPdfDefaultWatermark(fontPath, text);
        }

        /**
         * 构建页面水印
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param text 水印文本
         * @return 返回pdf页面水印组件
         */
        public static XEasyPdfWatermark build(String fontPath, float fontSize, String text) {
            return new XEasyPdfDefaultWatermark(fontPath, fontSize, text);
        }
    }

    /**
     * 文本组件
     */
    public static class Text {
        /**
         * 构建文本
         * @param fontPath 字体路径
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, String text) {
            return new XEasyPdfDefaultText(fontPath, text);
        }

        /**
         * 构建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, float fontSize, String text) {
            return new XEasyPdfDefaultText(fontPath, fontSize, text);
        }

        /**
         * 构建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, float fontSize, float beginX, float beginY, String text) {
            return new XEasyPdfDefaultText(fontPath, fontSize, beginX, beginY, text);
        }

        /**
         * 构建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param leading 行间距
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, float fontSize, float leading, float beginX, float beginY, String text) {
            return new XEasyPdfDefaultText(fontPath, fontSize, leading, beginX, beginY, text);
        }
    }

    /**
     * 线条组件
     */
    public static class Line {
        /**
         * 构建线条
         * @param fontPath 字体路径
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param endX 当前页面X轴结束坐标
         * @param endY 当前页面Y轴结束坐标
         * @return 返回pdf线条组件
         */
        public static XEasyPdfLine build(String fontPath, float beginX, float beginY, float endX, float endY) {
            return new XEasyPdfDefaultLine(fontPath, beginX, beginY, endX, endY);
        }
    }

    /**
     * 分割线组件
     */
    public static class SplitLine {
        /**
         * 实线
         */
        public static class SolidLine {
            /**
             * 构建实线分割线
             * @param fontPath 字体路径
             * @return 返回pdf实线分割线组件
             */
            public static XEasyPdfSolidSplitLine build(String fontPath) {
                return new XEasyPdfDefaultSolidSplitLine(fontPath);
            }
        }

        /**
         * 虚线
         */
        public static class DottedLine {
            /**
             * 构建虚线分割线
             * @param fontPath 字体路径
             * @return 返回pdf虚线分割线组件
             */
            public static XEasyPdfDottedSplitLine build(String fontPath) {
                return new XEasyPdfDefaultDottedSplitLine(fontPath);
            }
        }
    }

    /**
     * 图片组件
     */
    public static class Image {
        /**
         * 构建图片
         * @param image 待添加图片
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image) {
            return new XEasyPdfDefaultImage(image);
        }

        /**
         * 构建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType) {
            return new XEasyPdfDefaultImage(imageInputStream, imageType);
        }

        /**
         * 构建图片
         * @param image 待添加图片
         * @param width 图片宽度
         * @param height 图片高度
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image, int width, int height) {
            return new XEasyPdfDefaultImage(image, width, height);
        }

        /**
         * 构建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @param width 图片宽度
         * @param height 图片高度
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType, int width, int height) {
            return new XEasyPdfDefaultImage(imageInputStream, imageType, width, height);
        }

        /**
         * 构建图片
         * @param image 待添加图片
         * @param width 图片宽度
         * @param height 图片高度
         * @param beginX X轴起始坐标
         * @param beginY Y轴起始坐标
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image, int width, int height, float beginX, float beginY) {
            return new XEasyPdfDefaultImage(image, width, height, beginX, beginY);
        }

        /**
         * 构建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @param width 图片宽度
         * @param height 图片高度
         * @param beginX X轴起始坐标
         * @param beginY Y轴起始坐标
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType, int width, int height, float beginX, float beginY) {
            return new XEasyPdfDefaultImage(imageInputStream, imageType, width, height, beginX, beginY);
        }
    }
}
