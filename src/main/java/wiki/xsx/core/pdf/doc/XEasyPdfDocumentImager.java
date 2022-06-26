package wiki.xsx.core.pdf.doc;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.util.XEasyPdfFileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * pdf文档图像器
 *
 * @author xsx
 * @date 2020/12/21
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
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
public class XEasyPdfDocumentImager implements Serializable {

    private static final long serialVersionUID = 4542844990362703412L;

    /**
     * pdfbox文档
     */
    private final PDDocument document;
    /**
     * pdf文档
     */
    private final XEasyPdfDocument pdfDocument;
    /**
     * 图像DPI
     */
    private Float dpi = 72F;

    /**
     * 构造方法
     *
     * @param pdfDocument pdf文档
     */
    XEasyPdfDocumentImager(XEasyPdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
        this.document = this.pdfDocument.build(true);
    }

    /**
     * 设置图像DPI
     *
     * @param dpi 图像DPI
     * @return 返回pdf文档图像器
     */
    public XEasyPdfDocumentImager setDpi(float dpi) {
        this.dpi = Math.abs(dpi);
        return this;
    }

    /**
     * 转为图片（整个文档）
     *
     * @param outputPath 输出路径（目录）
     * @param imageType  图片类型
     * @return 返回pdf文档图像器
     */

    @SneakyThrows
    public XEasyPdfDocumentImager image(String outputPath, XEasyPdfImageType imageType) {
        return this.image(outputPath, imageType, null);
    }

    /**
     * 转为图片（整个文档）
     *
     * @param outputPath 输出路径（目录）
     * @param imageType  图片类型
     * @param prefix     图片名称前缀
     * @return 返回pdf文档图像器
     */
    @SneakyThrows
    public XEasyPdfDocumentImager image(String outputPath, XEasyPdfImageType imageType, String prefix) {
        // 如果文档名称前缀为空，则设置默认值为"x-easypdf"
        if (prefix == null) {
            // 初始化文档名称前缀
            prefix = "x-easypdf";
        }
        // 图片格式名称
        String imageTypeName = imageType.name().toLowerCase();
        // 文件名称构造器
        StringBuilder fileNameBuilder;
        // 初始化pdfBox文档渲染器
        PDFRenderer renderer = new PDFRenderer(this.document);
        // 任务文档页面总数
        int pageCount = this.document.getNumberOfPages();
        // 遍历文档页面
        for (int i = 0; i < pageCount; i++) {
            // 新建文件名称构造器
            fileNameBuilder = new StringBuilder();
            // 构建文件名称
            fileNameBuilder.append(outputPath).append(File.separator).append(prefix).append(i + 1).append('.').append(imageTypeName);
            // 获取输出流
            try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(XEasyPdfFileUtil.createDirectories(Paths.get(fileNameBuilder.toString()))))) {
                // 渲染图片
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, this.dpi);
                // 写出图片
                ImageIO.write(bufferedImage, imageTypeName, outputStream);
            }
        }
        return this;
    }

    /**
     * 转为图片（根据页面索引）
     *
     * @param outputStream 输出流
     * @param imageType    图片类型
     * @param pageIndex    页面索引
     * @return 返回pdf文档图像器
     */
    @SneakyThrows
    public XEasyPdfDocumentImager image(OutputStream outputStream, XEasyPdfImageType imageType, int pageIndex) {
        // 重置页面索引（0至文档总页面索引）
        pageIndex = Math.min(Math.max(pageIndex, 0), this.document.getNumberOfPages() - 1);
        // 初始化pdfBox文档渲染器
        PDFRenderer renderer = new PDFRenderer(this.document);
        // 渲染图片
        BufferedImage bufferedImage = renderer.renderImageWithDPI(pageIndex, this.dpi);
        // 写出图片
        ImageIO.write(bufferedImage, imageType.name().toLowerCase(), outputStream);
        return this;
    }

    /**
     * 完成操作
     *
     * @return 返回pdf文档
     */
    public XEasyPdfDocument finish() {
        return this.pdfDocument;
    }
}
