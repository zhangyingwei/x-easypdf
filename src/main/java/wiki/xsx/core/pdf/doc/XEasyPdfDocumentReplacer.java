package wiki.xsx.core.pdf.doc;

import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.util.XEasyPdfFileUtil;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;
import wiki.xsx.core.pdf.util.XEasyPdfImageUtil;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * pdf文档替换器
 * @author xsx
 * @date 2022/1/11
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
public class XEasyPdfDocumentReplacer {
    /**
     * pdfbox文档
     */
    private static final String FONT_REGEX = "F\\d+|C\\d+.*";
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
    XEasyPdfDocumentReplacer(XEasyPdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
        this.document = this.pdfDocument.build();
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回pdf文档替换器
     */
    public XEasyPdfDocumentReplacer setFontPath(String fontPath) {
        this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, fontPath, true);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回pdf文档替换器
     */
    public XEasyPdfDocumentReplacer setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.font = XEasyPdfFontUtil.loadFont(this.pdfDocument, style.getPath(), true);
        return this;
    }

    /**
     * 替换文本
     * @param replaceMap 替换字典（key可为正则）
     * @return 返回pdf文档替换器
     */
    @SneakyThrows
    public XEasyPdfDocumentReplacer replaceText(Map<String, String> replaceMap) {
        return this.replaceText(replaceMap, (int[]) null);
    }

    /**
     * 替换文本
     * @param replaceMap 替换字典（key可为正则）
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    @SneakyThrows
    public XEasyPdfDocumentReplacer replaceText(Map<String, String> replaceMap, int ...pageIndex) {
        return this.replaceText(1, replaceMap, pageIndex);
    }

    /**
     * 替换文本
     * @param count 替换次数
     * @param replaceMap 替换字典（key可为正则）
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    @SneakyThrows
    public XEasyPdfDocumentReplacer replaceText(int count, Map<String, String> replaceMap, int ...pageIndex) {
        // 替换字典不为空且替换次数不为0，则替换文本
        if (replaceMap!=null&&count!=0) {
            // 替换字典有内容，则替换文本
            if (!replaceMap.isEmpty()) {
                // 初始化字体
                this.initFont();
                // 如果页面索引为空，则替换全部页面
                if (pageIndex==null||pageIndex.length==0) {
                    // 获取页面树
                    PDPageTree pages = this.document.getPages();
                    // 遍历页面树
                    for (PDPage page : pages) {
                        for (int i = 0; i < count; i++) {
                            // 替换文本
                            this.replaceText(page, replaceMap);
                        }
                    }
                }
                // 否则替换给定页面索引
                else {
                    // 遍历页面索引
                    for (int index : pageIndex) {
                        // 如果页面索引大于等于0，则替换文本
                        if (index>=0) {
                            for (int i = 0; i < count; i++) {
                                // 替换文本
                                this.replaceText(this.document.getPage(index), replaceMap);
                            }
                        }
                    }
                }
            }
        }
        return this;
    }


    /**
     * 替换图像
     * @param image 待替换图像
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    public XEasyPdfDocumentReplacer replaceImage(BufferedImage image, int ...pageIndex) {
        return this.replaceImage(image, XEasyPdfImageType.PNG, pageIndex);
    }

    /**
     * 替换图像
     * @param image 待替换图像
     * @param imageType 待替换图像类型
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    public XEasyPdfDocumentReplacer replaceImage(BufferedImage image, XEasyPdfImageType imageType, int ...pageIndex) {
        return this.replaceImage(image, imageType, null, pageIndex);
    }

    /**
     * 替换图像
     * @param image 待替换图像
     * @param replaceIndexList 待替换图像索引列表
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    @SneakyThrows
    public XEasyPdfDocumentReplacer replaceImage(BufferedImage image, List<Integer> replaceIndexList, int ...pageIndex) {
        return this.replaceImage(image, XEasyPdfImageType.PNG, replaceIndexList, pageIndex);
    }

    /**
     * 替换图像
     * @param image 待替换图像
     * @param imageType 待替换图像类型
     * @param replaceIndexList 待替换图像索引列表
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    @SneakyThrows
    public XEasyPdfDocumentReplacer replaceImage(BufferedImage image, XEasyPdfImageType imageType, List<Integer> replaceIndexList, int ...pageIndex) {
        return this.replaceImage(
                PDImageXObject.createFromByteArray(this.document, XEasyPdfImageUtil.toBytes(image, imageType.name()), imageType.name()),
                replaceIndexList,
                pageIndex
        );
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

    /**
     * 初始化资源字体字典
     * @param resources pdfbox页面资源
     * @return 返回资源字体字典
     */
    @SneakyThrows
    private Map<COSName, PDFont> initResourceFontMap(PDResources resources) {
        // 定义资源字体字典
        Map<COSName, PDFont> resourceFontMap = new HashMap<>(16);
        // 获取资源字体名称迭代器
        for (COSName cosName : resources.getFontNames()) {
            // 添加资源字体
            resourceFontMap.put(cosName, resources.getFont(cosName));
        }
        return resourceFontMap;
    }

    /**
     * 替换文本
     * @param page pdfbox页面
     * @param replaceMap 替换字典（key可为正则）
     */
    @SneakyThrows
    private void replaceText(PDPage page, Map<String, String> replaceMap) {
        // 获取页面资源
        PDResources resources = page.getResources();
        // 获取pdf解析器
        PDFStreamParser parser = new PDFStreamParser(page);
        // 解析页面
        parser.parse();
        // 获取标记列表
        List<Object> tokens = parser.getTokens();
        // 获取已替换字体字典
        Map<COSName, PDFont> replaceFontMap = this.replaceTextToken(this.initResourceFontMap(resources), tokens, replaceMap);
        // 如果已替换字体字典不为空，则更新页面内容
        if (!replaceFontMap.isEmpty()) {
            // 获取替换字体名称
            Set<Map.Entry<COSName, PDFont>> entrySet = replaceFontMap.entrySet();
            // 遍历替换字体名称
            for (Map.Entry<COSName, PDFont> entry : entrySet) {
                // 替换字体
                resources.put(entry.getKey(), entry.getValue());
            }
            // 定义更新流
            PDStream updatedStream = new PDStream(this.document);
            // 创建输出流
            try (OutputStream outputStream = updatedStream.createOutputStream(COSName.FLATE_DECODE)) {
                // 创建内容写入器
                ContentStreamWriter tokenWriter = new ContentStreamWriter(outputStream);
                // 写入标记列表
                tokenWriter.writeTokens(tokens);
                // 设置页面内容
                page.setContents(updatedStream);
            }
        }
    }

    /**
     * 替换文本标记
     * @param resourceFontMap 资源字体字典
     * @param tokens 标记列表
     * @param replaceMap 替换次数
     * @return 返回已替换字体字典
     */
    @SneakyThrows
    private Map<COSName, PDFont> replaceTextToken(
            Map<COSName, PDFont> resourceFontMap,
            List<Object> tokens,
            Map<String, String> replaceMap
    ) {
        // 获取替换字典文本列表
        Set<Map.Entry<String, String>> entrySet = replaceMap.entrySet();
        // 定义替换字体字典
        Map<COSName, PDFont> replaceFontMap = new HashMap<>(resourceFontMap.size());
        // 定义页面新字符串
        String newValue;
        // 定义资源字体名称
        COSName resourceFontName = null;
        // 定义资源字体
        PDFont resourceFont = null;
        // 定义标记名称
        String cosName;
        // 遍历标记列表
        for (Object token : tokens) {
            // 如果标记为字体名称
            if (token instanceof COSName) {
                // 获取资源名称
                cosName = ((COSName) token).getName();
                // 如果为资源字体名称，则重置资源字体
                if (cosName.matches(FONT_REGEX)) {
                    // 重置资源字体
                    resourceFont = resourceFontMap.get(token);
                    // 重置资源字体名称
                    resourceFontName = (COSName) token;
                }
                // 否则跳过
                else {
                    // 跳过
                    continue;
                }
            }
            // 如果标记为字符串，则替换文本
            if (token instanceof COSString&&resourceFontName!=null) {
                // 初始化字符串构造器
                StringBuilder builder = new StringBuilder();
                // 转换为字符串
                COSString string = (COSString) token;
                // 获取字符串输入流
                try (InputStream in = new ByteArrayInputStream(string.getBytes())) {
                    // 读取字符
                    while (in.available()>0) {
                        // 断言字体不为空
                        assert resourceFont != null;
                        // 解析字符
                        builder.append(resourceFont.toUnicode(resourceFont.readCode(in)));
                    }
                }
                // 新字符串初始化为页面原字符串
                newValue = builder.toString();
                // 遍历替换字典文本列表
                for (Map.Entry<String, String> entry : entrySet) {
                    // 替换字符串
                    newValue = newValue.replaceFirst(entry.getKey(), entry.getValue());
                    // 设置新文本
                    string.setValue(this.font.encode(newValue));
                    // 添加文本关联
                    XEasyPdfFontUtil.addToSubset(this.font, newValue);
                }
                // 添加待替换字体
                replaceFontMap.putIfAbsent(resourceFontName, this.font);
            }
        }
        return replaceFontMap;
    }

    /**
     * 替换图像
     * @param image 待替换图像
     * @param replaceIndexList 待替换图像索引列表
     * @param pageIndex 页面索引
     * @return 返回pdf文档替换器
     */
    private XEasyPdfDocumentReplacer replaceImage(PDImageXObject image, List<Integer> replaceIndexList, int ...pageIndex) {
        // 获取pdfbox文档页面树
        PDPageTree pages = this.document.getPages();
        // 如果页面索引为空，则替换全部图像
        if (pageIndex==null||pageIndex.length==0) {
            // 遍历页面树
            for (PDPage page : pages) {
                // 替换图像
                this.replaceImage(page.getResources(), image, replaceIndexList);
            }
        }
        // 否则替换给定页面索引图像
        else {
            // 遍历页面索引
            for (int index : pageIndex) {
                // 如果索引小于0，则跳过
                if (index<0) {
                    // 跳过
                    continue;
                }
                // 替换图像
                this.replaceImage(pages.get(index).getResources(), image, replaceIndexList);
            }
        }
        return this;
    }

    /**
     * 替换图像
     * @param resources 页面资源
     * @param image 待替换图像
     * @param replaceIndexList 待替换图像索引列表
     */
    @SneakyThrows
    private void replaceImage(PDResources resources, PDImageXObject image, List<Integer> replaceIndexList) {
        // 获取资源内容名称列表
        Iterable<COSName> objectNames = resources.getXObjectNames();
        // 如果待替换图像索引列表为空，则替换全部图像
        if (replaceIndexList==null||replaceIndexList.isEmpty()) {
            // 遍历资源内容名称
            for (COSName cosName : objectNames) {
                // 如果资源内容为图片，则替换
                if (resources.getXObject(cosName) instanceof PDImage) {
                    // 替换图像
                    resources.put(cosName, image);
                }
            }
        }
        // 否则替换给定图像索引图像
        else {
            // 重置页面索引列表
            Iterator<Integer> iterator = new TreeSet<>(replaceIndexList).iterator();
            // 定义当前图像索引
            int index = 0;
            // 定义替换索引
            int replaceIndex = iterator.next();
            // 遍历资源内容名称
            for (COSName cosName : objectNames) {
                // 如果资源内容为图片，则替换
                if (resources.getXObject(cosName) instanceof PDImage) {
                    // 如果当前图像索引为替换索引，则替换图像
                    if (index==replaceIndex) {
                        // 替换图像
                        resources.put(cosName, image);
                        // 如果存在下一个页面索引，则获取下一个页面索引
                        if (iterator.hasNext()) {
                            // 重置替换索引
                            replaceIndex = iterator.next();
                        }
                        else {
                            break;
                        }
                    }
                    // 当前图像索引自增
                    index++;
                }
            }
        }
    }
}
