package wiki.xsx.core.pdf.doc;

import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import wiki.xsx.core.pdf.util.XEasyPdfFileUtil;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

/**
 * pdf表单填写器
 * @author xsx
 * @date 2021/10/3
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
public class XEasyPdfDocumentFormFiller {
    /**
     * pdfbox文档
     */
    private final PDDocument document;
    /**
     * pdf文档
     */
    private final XEasyPdfDocument pdfDocument;
    /**
     * pdfbox字体
     */
    private PDFont font;

    /**
     * 构造方法
     * @param pdfDocument pdf文档
     */
    @SneakyThrows
    XEasyPdfDocumentFormFiller(XEasyPdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
        this.document = this.pdfDocument.getTarget();
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回pdf表单填写器
     */
    public XEasyPdfDocumentFormFiller setFontPath(String fontPath) {
        this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, fontPath, false);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回pdf表单填写器
     */
    public XEasyPdfDocumentFormFiller setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, style.getPath(), false);
        return this;
    }

    /**
     * 填充表单
     * @param formMap 表单字典
     * @return 返回pdf表单填写器
     * @throws IOException IO异常
     */
    public XEasyPdfDocumentFormFiller fill(Map<String, String> formMap) throws IOException {
        // 如果表单字典有内容，则进行填充
        if (formMap!=null&&formMap.size()>0) {
            // 如果字体为空，则初始化字体
            if (this.font==null) {
                // 初始化字体
                this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, this.pdfDocument.getParam().getFontPath(), false);
            }
            // 定义pdfBox表单字段
            PDField field;
            // 获取pdfBox表单
            PDAcroForm acroForm = this.document.getDocumentCatalog().getAcroForm();
            // 如果pdfBox表单不为空，则进行填充
            if (acroForm!=null) {
                // 获取表单默认资源
                PDResources defaultResources = acroForm.getDefaultResources();
                // 获取默认资源字体名称
                Iterable<COSName> fontNames = defaultResources.getFontNames();
                // 遍历字体名称
                for (COSName fontName : fontNames) {
                    // 字体替换为当前文档字体
                    defaultResources.put(fontName, this.font);
                }
                // 获取表单字典键值集合
                Set<Map.Entry<String, String>> entrySet = formMap.entrySet();
                // 遍历表单字典
                for (Map.Entry<String, String> entry : entrySet) {
                    // 获取表单字典中对应的pdfBox表单字段
                    field = acroForm.getField(entry.getKey());
                    // 如果pdfBox表单字段不为空，则填充值
                    if (field!=null) {
                        // 设置值
                        field.setValue(entry.getValue());
                    }
                }
            }
        }
        return this;
    }

    /**
     * 完成操作
     * @param outputPath 文件输出路径
     * @throws IOException IO异常
     */
    public void finish(String outputPath) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(XEasyPdfFileUtil.createDirectories(Paths.get(outputPath)))) {
            this.finish(outputStream);
        }
    }

    /**
     * 完成操作
     * @param outputStream 文件输出流
     * @throws IOException IO异常
     */
    public void finish(OutputStream outputStream) throws IOException {
        // 创建写入器
        COSWriter writer = new COSWriter(outputStream);
        // 设置文档信息及保护策略
        this.pdfDocument.setInfoAndPolicy(this.document);
        // 关联字体
        XEasyPdfFontUtil.subsetFonts();
        // 写入文档
        writer.write(this.document);
        // 重置字体为空
        this.font = null;
        // 关闭文档
        this.pdfDocument.close();
    }
}
