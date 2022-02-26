package wiki.xsx.core.pdf.doc;

import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import wiki.xsx.core.pdf.util.XEasyPdfFileUtil;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

/**
 * pdf文档表单填写器
 * @author xsx
 * @date 2021/10/3
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
    XEasyPdfDocumentFormFiller(XEasyPdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
        this.document = this.pdfDocument.build();
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回pdf表单填写器
     */
    public XEasyPdfDocumentFormFiller setFontPath(String fontPath) {
        this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, fontPath, true);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回pdf表单填写器
     */
    public XEasyPdfDocumentFormFiller setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, style.getPath(), true);
        return this;
    }

    /**
     * 填充表单
     * @param formMap 表单字典
     * @return 返回pdf表单填写器
     */
    @SneakyThrows
    public XEasyPdfDocumentFormFiller fill(Map<String, String> formMap) {
        // 如果表单字典有内容，则进行填充
        if (formMap!=null&&!formMap.isEmpty()) {
            // 初始化字体
            this.initFont();
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
                        // 添加文本关联
                        XEasyPdfFontUtil.addToSubset(this.font, entry.getValue());
                        // 设置值
                        field.setValue(entry.getValue());


                    }
                }
            }
        }
        return this;
    }

    /**
     * 文档签名器
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner signer() {
        return new XEasyPdfDocumentSigner(this.pdfDocument);
    }

    /**
     * 完成操作
     * @param outputPath 文件输出路径
     */
    @SneakyThrows
    public void finish(String outputPath) {
        try (OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(XEasyPdfFileUtil.createDirectories(Paths.get(outputPath))))) {
            this.finish(outputStream);
        }
    }

    /**
     * 完成操作
     * @param outputStream 文件输出流
     */
    @SneakyThrows
    public void finish(OutputStream outputStream) {
        // 设置文档信息及保护策略
        this.pdfDocument.setInfoAndPolicyAndBookmark(this.document);
        // 设置表单为空（解决编辑器乱码问题）
        this.document.getDocumentCatalog().setAcroForm(null);
        // 保存文档
        this.document.save(outputStream);
        // 重置字体为空
        this.font = null;
        // 关闭文档
        this.pdfDocument.close();
    }

    /**
     * 初始化字体
     */
    private void initFont() {
        // 如果字体为空，则初始化字体
        if (this.font==null) {
            // 初始化字体
            this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, this.pdfDocument.getParam().getFontPath(), true);
        }
    }
}
