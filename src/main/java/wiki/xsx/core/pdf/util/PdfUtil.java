package wiki.xsx.core.pdf.util;

import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.io.IOException;
import java.io.InputStream;
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

    /*------------------- create -------------------*/

    /**
     * 创建pdf
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(String outputPath, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            create(outputStream, pages);
        }
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
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(String outputPath, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            create(outputStream, globalWatermark, pages);
        }
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

    /*------------------- create end -------------------*/



    /*------------------- append -------------------*/

    /**
     * 追加pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(String sourcePath, String outputPath, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            append(sourcePath, outputStream, pages);
        }
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
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(InputStream sourceInputStream, String outputPath, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            append(sourceInputStream, outputStream, pages);
        }
    }

    /**
     * 追加pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(InputStream sourceInputStream, OutputStream outputStream, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourceInputStream).addPage(pages).save(outputStream);
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
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            append(sourcePath, outputStream, globalWatermark, pages);
        }
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
     * 追加pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(InputStream sourceInputStream, String outputPath, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            append(sourceInputStream, outputStream, globalWatermark, pages);
        }
    }

    /**
     * 追加pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(InputStream sourceInputStream, OutputStream outputStream, XEasyPdfWatermark globalWatermark, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).addPage(pages).save(outputStream);
    }

    /*------------------- append end -------------------*/



    /*------------------- insert -------------------*/

    /**
     * 插入pdf页面
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(String sourcePath, String outputPath, int index, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            insert(sourcePath, outputStream, index, pages);
        }
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
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(InputStream sourceInputStream, String outputPath, int index, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            insert(sourceInputStream, outputStream, index, pages);
        }
    }

    /**
     * 插入pdf页面
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(InputStream sourceInputStream, OutputStream outputStream, int index, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourceInputStream).insertPage(index, pages).save(outputStream);
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
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            insert(sourcePath, outputStream, globalWatermark, index, pages);
        }
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
     * 插入pdf页面
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(InputStream sourceInputStream, String outputPath, XEasyPdfWatermark globalWatermark, int index, XEasyPdfPage...pages) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            insert(sourceInputStream, outputStream, globalWatermark, index, pages);
        }
    }

    /**
     * 插入pdf页面
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param index pdf页面索引
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void insert(InputStream sourceInputStream, OutputStream outputStream, XEasyPdfWatermark globalWatermark, int index, XEasyPdfPage...pages) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).insertPage(index, pages).save(outputStream);
    }

    /*------------------- insert end -------------------*/



    /*------------------- fill -------------------*/

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputPath 文件输出路径
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, String outputPath, String fontPath, Map<String, String> formMap) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            fill(templateSourcePath, outputStream, fontPath, formMap);
        }
    }

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputStream 文件输出流
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, OutputStream outputStream, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateSourcePath).fillAcroForm(fontPath, formMap).save(outputStream);
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
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            fill(templateSourcePath, outputStream, globalWatermark, fontPath, formMap);
        }
    }

    /**
     * 模板填充
     * @param templateSourcePath 模板源文件路径
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(String templateSourcePath, OutputStream outputStream, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateSourcePath).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap).save(outputStream);
    }

    /**
     * 模板填充
     * @param templateInputStream 模板源文件数据流
     * @param outputPath 文件输出路径
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(InputStream templateInputStream, String outputPath, String fontPath, Map<String, String> formMap) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            fill(templateInputStream, outputStream, fontPath, formMap);
        }
    }

    /**
     * 模板填充
     * @param templateInputStream 模板源文件数据流
     * @param outputStream 文件输出流
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(InputStream templateInputStream, OutputStream outputStream, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateInputStream).fillAcroForm(fontPath, formMap).save(outputStream);
    }

    /**
     * 模板填充
     * @param templateInputStream 模板源文件数据流
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(InputStream templateInputStream, String outputPath, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            fill(templateInputStream, outputStream, globalWatermark, fontPath, formMap);
        }
    }

    /**
     * 模板填充
     * @param templateInputStream 模板源文件数据流
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(InputStream templateInputStream, OutputStream outputStream, XEasyPdfWatermark globalWatermark, String fontPath, Map<String, String> formMap) throws IOException {
        new XEasyPdfDocument(templateInputStream).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap).save(outputStream);
    }

    /*------------------- fill end -------------------*/
}
