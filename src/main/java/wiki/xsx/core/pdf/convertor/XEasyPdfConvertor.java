package wiki.xsx.core.pdf.convertor;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import lombok.SneakyThrows;
import wiki.xsx.core.pdf.util.XEasyPdfFileUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * pdf转换器
 *
 * @author xsx
 * @date 2022/4/10
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
 * x-easypdf is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class XEasyPdfConvertor {

    /**
     * 转pdf
     * <p>支持格式：doc/docx/jpg/tiff/markdown/html/mhtml/rtf/odt/txt/mobi</p>
     *
     * @param sourcePath 源文件路径
     * @param outputPath 输出文件路径
     */
    @SneakyThrows
    public static void toPdf(String sourcePath, String outputPath) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath))) {
            toPdf(inputStream, outputPath);
        }
    }

    /**
     * 转pdf
     * <p>支持格式：doc/docx/jpg/tiff/markdown/html/mhtml/rtf/odt/txt/mobi</p>
     *
     * @param inputStream 输入流
     * @param outputPath  输出文件路径
     */
    @SneakyThrows
    public static void toPdf(InputStream inputStream, String outputPath) {
        try (
                OutputStream outputStream = new BufferedOutputStream(
                        Files.newOutputStream(XEasyPdfFileUtil.createDirectories(Paths.get(outputPath)))
                )
        ) {
            toPdf(inputStream, outputStream);
        }
    }

    /**
     * 转pdf
     * <p>支持格式：doc/docx/jpg/tiff/markdown/html/mhtml/rtf/odt/txt/mobi</p>
     *
     * @param sourcePath   源文件路径
     * @param outputStream 输出流
     */
    @SneakyThrows
    public static void toPdf(String sourcePath, OutputStream outputStream) {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(sourcePath))) {
            toPdf(inputStream, outputStream);
        }
    }

    /**
     * 转pdf
     * <p>支持格式：doc/docx/jpg/tiff/markdown/html/mhtml/rtf/odt/txt/mobi</p>
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     */
    @SneakyThrows
    public static void toPdf(InputStream inputStream, OutputStream outputStream) {
        new Document(inputStream).save(outputStream, SaveFormat.PDF);
    }
}
