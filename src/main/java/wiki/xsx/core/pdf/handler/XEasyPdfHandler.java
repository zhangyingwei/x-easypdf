package wiki.xsx.core.pdf.handler;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.XEasyPdfDottedSplitLine;
import wiki.xsx.core.pdf.component.line.XEasyPdfLine;
import wiki.xsx.core.pdf.component.line.XEasyPdfSolidSplitLine;
import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
         * 创建pdf
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithCreate(XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument().addPage(pages);
        }

        /**
         * 创建pdf
         * @param globalWatermark 全局水印
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithCreate(XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument().setGlobalWatermark(globalWatermark).addPage(pages);
        }

        /**
         * 追加pdf
         * @param sourcePath 源文件路径
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithAppend(String sourcePath, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourcePath).addPage(pages);
        }

        /**
         * 追加pdf
         * @param sourcePath 源文件路径
         * @param globalWatermark 全局水印
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithAppend(String sourcePath, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).addPage(pages);
        }

        /**
         * 追加pdf
         * @param sourceInputStream 源文件数据流
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithAppend(InputStream sourceInputStream, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourceInputStream).addPage(pages);
        }

        /**
         * 追加pdf
         * @param sourceInputStream 源文件数据流
         * @param globalWatermark 全局水印
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithAppend(InputStream sourceInputStream, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).addPage(pages);
        }

        /**
         * 插入pdf页面
         * @param sourcePath 源文件路径
         * @param index pdf页面索引
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithInsert(String sourcePath, int index, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourcePath).insertPage(index, pages);
        }

        /**
         * 插入pdf页面
         * @param sourcePath 源文件路径
         * @param globalWatermark 全局水印
         * @param index pdf页面索引
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithInsert(String sourcePath, XEasyPdfWatermark globalWatermark, int index, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).insertPage(index, pages);
        }

        /**
         * 插入pdf页面
         * @param sourceInputStream 源文件数据流
         * @param index pdf页面索引
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithInsert(InputStream sourceInputStream, int index, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourceInputStream).insertPage(index, pages);
        }

        /**
         * 插入pdf页面
         * @param sourceInputStream 源文件数据流
         * @param globalWatermark 全局水印
         * @param index pdf页面索引
         * @param pages pdf页面
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithInsert(InputStream sourceInputStream, XEasyPdfWatermark globalWatermark, int index, XEasyPdfPage...pages) throws IOException {
            return new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).insertPage(index, pages);
        }

        /**
         * 模板填充
         * @param templateSourcePath 模板源文件路径
         * @param formMap 表单字典
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithFill(String templateSourcePath, String fontPath, Map<String, String> formMap) throws IOException {
            return new XEasyPdfDocument(templateSourcePath).fillAcroForm(fontPath, formMap);
        }

        /**
         * 模板填充
         * @param templateSourcePath 模板源文件路径
         * @param globalWatermark 全局水印
         * @param formMap 表单字典
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithFill(String templateSourcePath, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
            return new XEasyPdfDocument(templateSourcePath).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap);
        }

        /**
         * 模板填充
         * @param templateInputStream 模板源文件数据流
         * @param formMap 表单字典
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithFill(InputStream templateInputStream, String fontPath, Map<String, String> formMap) throws IOException {
            return new XEasyPdfDocument(templateInputStream).fillAcroForm(fontPath, formMap);
        }

        /**
         * 模板填充
         * @param templateInputStream 模板源文件数据流
         * @param globalWatermark 全局水印
         * @param formMap 表单字典
         * @throws IOException IO异常
         */
        public static XEasyPdfDocument buildWithFill(InputStream templateInputStream, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
            return new XEasyPdfDocument(templateInputStream).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap);
        }
    }

    /**
     * pdf页面
     */
    public static class Page {
        /**
         * 创建页面
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(XEasyPdfComponent...components) {
            return new XEasyPdfPage().addComponent(components);
        }

        /**
         * 创建页面
         * @param components 组件
         * @param watermark 页面水印组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(XEasyPdfWatermark watermark, XEasyPdfComponent...components) {
            return new XEasyPdfPage().setWatermark(watermark).addComponent(components);
        }

        /**
         * 创建页面
         * @param pageSize pdfBox页面尺寸
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XEasyPdfPage build(PDRectangle pageSize, XEasyPdfComponent...components) {
            return new XEasyPdfPage(pageSize).addComponent(components);
        }

        /**
         * 创建页面
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
         * 创建页面水印
         * @param fontPath 字体路径
         * @param text 水印文本
         * @return 返回pdf页面水印组件
         */
        public static XEasyPdfWatermark build(String fontPath, String text) {
            return new XEasyPdfWatermark(fontPath, text);
        }

        /**
         * 创建页面水印
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param text 水印文本
         * @return 返回pdf页面水印组件
         */
        public static XEasyPdfWatermark build(String fontPath, float fontSize, String text) {
            return new XEasyPdfWatermark(fontPath, fontSize, text);
        }
    }

    /**
     * 文本组件
     */
    public static class Text {
        /**
         * 创建文本
         * @param fontPath 字体路径
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, String text) {
            return new XEasyPdfText(fontPath, text);
        }

        /**
         * 创建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, float fontSize, String text) {
            return new XEasyPdfText(fontPath, fontSize, text);
        }

        /**
         * 创建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, float fontSize, float beginX, float beginY, String text) {
            return new XEasyPdfText(fontPath, fontSize, beginX, beginY, text);
        }

        /**
         * 创建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param leading 行间距
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XEasyPdfText build(String fontPath, float fontSize, float leading, float beginX, float beginY, String text) {
            return new XEasyPdfText(fontPath, fontSize, leading, beginX, beginY, text);
        }
    }

    /**
     * 线条组件
     */
    public static class Line {
        /**
         * 创建线条
         * @param fontPath 字体路径
         * @param beginX 当前页面X轴起始坐标
         * @param beginY 当前页面Y轴起始坐标
         * @param endX 当前页面X轴结束坐标
         * @param endY 当前页面Y轴结束坐标
         * @return
         */
        public static XEasyPdfLine build(String fontPath, float beginX, float beginY, float endX, float endY) {
            return new XEasyPdfLine(fontPath, beginX, beginY, endX, endY);
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
             * 创建实线分割线
             * @param fontPath 字体路径
             * @return 返回pdf实线分割线组件
             */
            public static XEasyPdfSolidSplitLine build(String fontPath) {
                return new XEasyPdfSolidSplitLine(fontPath);
            }
        }

        /**
         * 虚线
         */
        public static class DottedLine {
            /**
             * 创建虚线分割线
             * @param fontPath 字体路径
             * @return 返回pdf虚线分割线组件
             */
            public static XEasyPdfDottedSplitLine build(String fontPath) {
                return new XEasyPdfDottedSplitLine(fontPath);
            }
        }
    }

    /**
     * 图片组件
     */
    public static class Image {
        /**
         * 创建图片
         * @param image 待添加图片
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image) {
            return new XEasyPdfImage(image);
        }

        /**
         * 创建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType) {
            return new XEasyPdfImage(imageInputStream, imageType);
        }

        /**
         * 创建图片
         * @param image 待添加图片
         * @param width 图片宽度
         * @param height 图片高度
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image, int width, int height) {
            return new XEasyPdfImage(image, width, height);
        }

        /**
         * 创建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @param width 图片宽度
         * @param height 图片高度
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType, int width, int height) {
            return new XEasyPdfImage(imageInputStream, imageType, width, height);
        }

        /**
         * 创建图片
         * @param image 待添加图片
         * @param width 图片宽度
         * @param height 图片高度
         * @param beginX X轴起始坐标
         * @param beginY Y轴起始坐标
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(File image, int width, int height, float beginX, float beginY) {
            return new XEasyPdfImage(image, width, height, beginX, beginY);
        }

        /**
         * 创建图片
         * @param imageInputStream 待添加图片数据流
         * @param imageType 待添加图片类型（扩展名）
         * @param width 图片宽度
         * @param height 图片高度
         * @param beginX X轴起始坐标
         * @param beginY Y轴起始坐标
         * @return 返回pdf图片组件
         */
        public static XEasyPdfImage build(InputStream imageInputStream, String imageType, int width, int height, float beginX, float beginY) {
            return new XEasyPdfImage(imageInputStream, imageType, width, height, beginX, beginY);
        }
    }
}
