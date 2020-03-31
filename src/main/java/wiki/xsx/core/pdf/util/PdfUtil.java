package wiki.xsx.core.pdf.util;

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
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * pdf工具类
 * @author xsx
 * @date 2019/12/19
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
public class PdfUtil {

    /**
     * 创建pdf
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(String outputPath, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument().addPage(pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 创建pdf
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(String outputPath, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument().setGlobalWatermark(globalWatermark).addPage(pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 创建pdf
     * @param outputStream 文件输出流
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(OutputStream outputStream, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument().addPage(pages).save(outputStream);
    }

    /**
     * 创建pdf
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(OutputStream outputStream, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument().setGlobalWatermark(globalWatermark).addPage(pages).save(outputStream);
    }

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, String outputPath, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).addPage(pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, String outputPath, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).addPage(pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, OutputStream outputStream, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).addPage(pages).save(outputStream);
    }

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, OutputStream outputStream, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).addPage(pages).save(outputStream);
    }

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, String outputPath, int index, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).insertPage(index, pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, String outputPath, XEasyPdfWatermark globalWatermark, int index, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).insertPage(index, pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, OutputStream outputStream, int index, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).insertPage(index, pages).save(outputStream);
    }

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, OutputStream outputStream, XEasyPdfWatermark globalWatermark, int index, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).insertPage(index, pages).save(outputStream);
    }

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputPath 文件输出路径
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, String outputPath, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateSourcePath).fillAcroForm(fontPath, formMap).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, String outputPath, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateSourcePath).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputPath 文件输出流
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, OutputStream outputPath, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateSourcePath).fillAcroForm(fontPath, formMap).save(outputPath);
    }

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputPath 文件输出流
     * @param globalWatermark 全局水印
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, OutputStream outputPath, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateSourcePath).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap).save(outputPath);
    }

    /**
     * 页面组件
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
    }
}
