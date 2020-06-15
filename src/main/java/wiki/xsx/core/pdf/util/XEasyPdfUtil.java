package wiki.xsx.core.pdf.util;


import wiki.xsx.core.pdf.component.mark.XEasyPdfDefaultWatermark;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfDocumentSplitter;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * pdf工具
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
public class XEasyPdfUtil {

    /*------------------- create -------------------*/

    /**
     * 创建pdf
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(
            String outputPath,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void create(
            OutputStream outputStream,
            XEasyPdfPage...pages
    ) throws IOException {
        new XEasyPdfDocument().addPage(pages).save(outputStream);
    }

    /**
     * 创建pdf
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void create(
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void create(
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            String sourcePath,
            String outputPath,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfPage...pages
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).addPage(pages).save(outputStream);
    }

    /**
     * 追加pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param pages pdf页面
     * @throws IOException IO异常
     */
    public static void append(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void append(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            String sourcePath,
            String outputPath,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            String sourcePath,
            OutputStream outputStream,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            InputStream sourceInputStream,
            String outputPath,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            InputStream sourceInputStream,
            OutputStream outputStream,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void insert(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            int index,
            XEasyPdfPage...pages
    ) throws IOException {
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
    public static void fill(
            String templateSourcePath,
            String outputPath,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
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
    public static void fill(
            String templateSourcePath,
            OutputStream outputStream,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
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
    public static void fill(
            String templateSourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
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
    public static void fill(
            String templateSourcePath,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
        new XEasyPdfDocument(templateSourcePath).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap).save(outputStream);
    }

    /**
     * 模板填充
     * @param templateInputStream 模板源文件数据流
     * @param outputPath 文件输出路径
     * @param formMap 表单字典
     * @throws IOException IO异常
     */
    public static void fill(
            InputStream templateInputStream,
            String outputPath,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
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
    public static void fill(
            InputStream templateInputStream,
            OutputStream outputStream,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
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
    public static void fill(
            InputStream templateInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
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
    public static void fill(
            InputStream templateInputStream,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            String fontPath,
            Map<String, String> formMap
    ) throws IOException {
        new XEasyPdfDocument(templateInputStream).setGlobalWatermark(globalWatermark).fillAcroForm(fontPath, formMap).save(outputStream);
    }

    /*------------------- fill end -------------------*/



    /*------------------- merge -------------------*/

    /**
     * 合并pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            String sourcePath,
            String outputPath,
            XEasyPdfDocument...documents
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            merge(sourcePath, outputStream, documents);
        }
    }

    /**
     * 合并pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfDocument...documents
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).merge(documents).save(outputStream);
    }

    /**
     * 合并pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDocument...documents
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            merge(sourceInputStream, outputStream, documents);
        }
    }

    /**
     * 合并pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfDocument...documents
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).merge(documents).save(outputStream);
    }

    /**
     * 合并pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocument...documents
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            merge(sourcePath, outputStream, globalWatermark, documents);
        }
    }

    /**
     * 合并pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocument...documents
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).merge(documents).save(outputStream);
    }

    /**
     * 合并pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocument...documents
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            merge(sourceInputStream, outputStream, globalWatermark, documents);
        }
    }

    /**
     * 合并pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param documents pdf文档
     * @throws IOException IO异常
     */
    public static void merge(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocument...documents
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).merge(documents).save(outputStream);
    }

    /*------------------- merge end -------------------*/



    /*------------------- split -------------------*/

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath
    ) throws IOException {
        split(sourcePath, outputPath, (XEasyPdfDocumentSplitter) null);
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param splitter pdf文档拆分器
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDocumentSplitter splitter
    ) throws IOException {
        split(sourcePath, outputPath, splitter, null);
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark
    ) throws IOException {
        split(sourcePath, outputPath, globalWatermark, (XEasyPdfDocumentSplitter) null);
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param splitter pdf文档拆分器
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocumentSplitter splitter
    ) throws IOException {
        split(sourcePath, outputPath, globalWatermark, splitter, null);
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath
    ) throws IOException {
        split(sourceInputStream, outputPath, (XEasyPdfDocumentSplitter) null);
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param splitter pdf文档拆分器
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDocumentSplitter splitter
    ) throws IOException {
        split(sourceInputStream, outputPath, splitter, null);
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark
    ) throws IOException {
        split(sourceInputStream, outputPath, globalWatermark, (XEasyPdfDocumentSplitter) null);
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param splitter pdf文档拆分器
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocumentSplitter splitter
    ) throws IOException {
        split(sourceInputStream, outputPath, globalWatermark, splitter, null);
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).split(outputPath, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param splitter pdf文档拆分器
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDocumentSplitter splitter,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).split(outputPath, splitter, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).split(outputPath, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param splitter pdf文档拆分器
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocumentSplitter splitter,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).split(outputPath, splitter, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).split(outputPath, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param splitter pdf文档拆分器
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDocumentSplitter splitter,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).split(outputPath, splitter, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).split(outputPath, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param splitter pdf文档拆分器
     * @param prefix 文档名称前缀
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            XEasyPdfDocumentSplitter splitter,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).split(outputPath, splitter, prefix).close();
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            int ...pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            split(sourcePath, outputStream, pageIndex);
        }
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            OutputStream outputStream,
            int ...pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).split(outputStream, pageIndex).close();
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            int ...pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            split(sourcePath, outputStream, globalWatermark, pageIndex);
        }
    }

    /**
     * 拆分pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            int ...pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).split(outputStream, pageIndex).close();
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            int ...pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            split(sourceInputStream, outputStream, pageIndex);
        }
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            OutputStream outputStream,
            int ...pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).split(outputStream, pageIndex).close();
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            int ...pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            split(sourceInputStream, outputStream, globalWatermark,pageIndex);
        }
    }

    /**
     * 拆分pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param pageIndex 页面索引
     * @throws IOException IO异常
     */
    public static void split(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            int ...pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).split(outputStream, pageIndex).close();
    }

    /*------------------- split end -------------------*/



    /*------------------- image -------------------*/

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param imageType 图片类型
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            String outputPath,
            String imageType
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).image(outputPath, imageType).close();
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).image(outputPath, imageType).close();
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param imageType 图片类型
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            String outputPath,
            String imageType
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).image(outputPath, imageType).close();
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).image(outputPath, imageType).close();
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param imageType 图片类型
     * @param prefix 图片名称前缀
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            String outputPath,
            String imageType,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).image(outputPath, imageType, prefix).close();
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @param prefix 图片名称前缀
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).image(outputPath, imageType, prefix).close();
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param imageType 图片类型
     * @param prefix 图片名称前缀
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            String outputPath,
            String imageType,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).image(outputPath, imageType, prefix).close();
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径（目录）
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @param prefix 图片名称前缀
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType,
            String prefix
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).image(outputPath, imageType, prefix).close();
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            String outputPath,
            String imageType,
            int pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            image(sourcePath, outputStream, imageType, pageIndex);
        }
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType,
            int pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            image(sourcePath, outputStream, globalWatermark, imageType, pageIndex);
        }
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            String outputPath,
            String imageType,
            int pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            image(sourceInputStream, outputStream, imageType, pageIndex);
        }
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputPath 文件输出路径
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            String outputPath,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType,
            int pageIndex
    ) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(outputPath))) {
            image(sourceInputStream, outputStream, globalWatermark, imageType, pageIndex);
        }
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            OutputStream outputStream,
            String imageType,
            int pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).image(outputStream, imageType, pageIndex).close();
    }

    /**
     * 图片化pdf
     * @param sourcePath 源文件路径
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            String sourcePath,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType,
            int pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourcePath).setGlobalWatermark(globalWatermark).image(outputStream, imageType, pageIndex).close();
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            OutputStream outputStream,
            String imageType,
            int pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).image(outputStream, imageType, pageIndex).close();
    }

    /**
     * 图片化pdf
     * @param sourceInputStream 源文件数据流
     * @param outputStream 文件输出流
     * @param globalWatermark 全局水印
     * @param imageType 图片类型
     * @param pageIndex 文档页面索引
     * @throws IOException IO异常
     */
    public static void image(
            InputStream sourceInputStream,
            OutputStream outputStream,
            XEasyPdfDefaultWatermark globalWatermark,
            String imageType,
            int pageIndex
    ) throws IOException {
        new XEasyPdfDocument(sourceInputStream).setGlobalWatermark(globalWatermark).image(outputStream, imageType, pageIndex).close();
    }

    /*------------------- image end -------------------*/
}
