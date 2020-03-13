package wiki.xsx.core.pdf.util;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XpdfComponent;
import wiki.xsx.core.pdf.component.line.XpdfDottedSplitLine;
import wiki.xsx.core.pdf.component.line.XpdfLine;
import wiki.xsx.core.pdf.component.line.XpdfSolidSplitLine;
import wiki.xsx.core.pdf.component.text.XpdfText;
import wiki.xsx.core.pdf.doc.XpdfDocument;
import wiki.xsx.core.pdf.doc.XpdfPage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * pdf工具类
 * @author xsx
 * @date 2019/12/19
 * @since 1.8
 */
public class PdfUtil {

    /**
     * 创建pdf
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(String outputPath, XpdfPage...pages) throws IOException {
        new XpdfDocument().addPage(pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 创建pdf
     * @param outputStream 文件输出流
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(OutputStream outputStream, XpdfPage...pages) throws IOException {
        new XpdfDocument().addPage(pages).save(outputStream);
    }

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, String outputPath, XpdfPage...pages) throws IOException {
        new XpdfDocument(sourcePath).addPage(pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, OutputStream outputStream, XpdfPage...pages) throws IOException {
        new XpdfDocument(sourcePath).addPage(pages).save(outputStream);
    }

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, String outputPath, int index, XpdfPage...pages) throws IOException {
        new XpdfDocument(sourcePath).insertPage(index, pages).save(Files.newOutputStream(Paths.get(outputPath)));
    }

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, OutputStream outputStream, int index, XpdfPage...pages) throws IOException {
        new XpdfDocument(sourcePath).insertPage(index, pages).save(outputStream);
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
        public static XpdfPage build(XpdfComponent...components) {
            return new XpdfPage().addComponent(components);
        }

        /**
         * 创建页面
         * @param pageSize pdfBox页面尺寸
         * @param components 组件
         * @return 返回pdf页面组件
         */
        public static XpdfPage build(PDRectangle pageSize, XpdfComponent...components) {
            return new XpdfPage(pageSize).addComponent(components);
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
        public static XpdfText build(String fontPath, String text) {
            return new XpdfText(fontPath, text);
        }

        /**
         * 创建文本
         * @param fontPath 字体路径
         * @param fontSize 字体大小
         * @param text 待输入文本
         * @return 返回pdf文本组件
         */
        public static XpdfText build(String fontPath, float fontSize, String text) {
            return new XpdfText(fontPath, fontSize, text);
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
        public static XpdfText build(String fontPath, float fontSize, float beginX, float beginY, String text) {
            return new XpdfText(fontPath, fontSize, beginX, beginY, text);
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
        public static XpdfText build(String fontPath, float fontSize, float leading, float beginX, float beginY, String text) {
            return new XpdfText(fontPath, fontSize, leading, beginX, beginY, text);
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
        public static XpdfLine build(String fontPath, float beginX, float beginY, float endX, float endY) {
            return new XpdfLine(fontPath, beginX, beginY, endX, endY);
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
            public static XpdfSolidSplitLine build(String fontPath) {
                return new XpdfSolidSplitLine(fontPath);
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
            public static XpdfDottedSplitLine build(String fontPath) {
                return new XpdfDottedSplitLine(fontPath);
            }
        }
    }
}
