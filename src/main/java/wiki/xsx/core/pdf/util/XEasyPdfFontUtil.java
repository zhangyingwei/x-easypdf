package wiki.xsx.core.pdf.util;

import org.apache.fontbox.ttf.OTFParser;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 字体工具
 * @author xsx
 * @date 2020/4/7
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
public class XEasyPdfFontUtil {

    /**
     * 开源字体
     */
    private static final String OTF = ".otf";

    /**
     * 获取字体
     * @param document pdf文档
     * @param fontPath 字体路径
     * @param defaultFont 默认字体
     * @return 返回pdfBox字体
     */
    public static PDFont getFont(XEasyPdfDocument document, String fontPath, PDFont defaultFont) {
        if (fontPath!=null) {
            return loadFont(document, fontPath, true);
        }
        return defaultFont;
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param fontPath 字体路径
     * @param isEmbedded 是否嵌入
     * @return 返回pdfBox字体
     */
    public static PDFont loadFont(XEasyPdfDocument document, String fontPath, boolean isEmbedded) {
        if (fontPath!=null) {
            PDFont font;
            if (isEmbedded) {
                font = document.getFont(fontPath);
                if (font!=null) {
                    return font;
                }
            }
            Path path = Paths.get(fontPath);
            try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
                if (path.getFileName().toString().endsWith(OTF)) {
                    font = PDType0Font.load(document.getTarget(), new OTFParser(isEmbedded, true).parse(inputStream), isEmbedded);
                }else {
                    font = PDType0Font.load(document.getTarget(), inputStream, isEmbedded);
                }
                if (isEmbedded) {
                    document.addFont(fontPath, font);
                }
                return font;
            }catch (Exception e) {
                return loadFontForResource(document, fontPath, isEmbedded);
            }
        }else {
            throw new IllegalArgumentException("the font can not be loaded");
        }
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param page pdf页面
     * @param fontPath 字体路径
     * @param isEmbedded 是否嵌入
     * @return 返回pdfBox字体
     */
    public static PDFont loadFont(XEasyPdfDocument document, XEasyPdfPage page, String fontPath, boolean isEmbedded) {
        if (fontPath!=null) {
            if (isEmbedded) {
                PDFont font = document.getFont(fontPath);
                if (font!=null) {
                    return font;
                }
            }
            return loadFont(document, fontPath, isEmbedded);
        }else {
            return loadFont(document, page);
        }
    }

    /**
     * 加载字体(资源路径)
     * @param document pdf文档
     * @param fontResourcePath 字体路径(资源路径)
     * @param isEmbedded 是否嵌入
     * @return 返回pdfBox字体
     */
    private static PDFont loadFontForResource(XEasyPdfDocument document, String fontResourcePath, boolean isEmbedded) {
        try (InputStream inputStream = new BufferedInputStream(XEasyPdfFontUtil.class.getResourceAsStream(fontResourcePath))) {
            PDFont font;
            if (fontResourcePath.endsWith(OTF)) {
                font = PDType0Font.load(document.getTarget(), new OTFParser(isEmbedded, true).parse(inputStream), isEmbedded);
            }else {
                font = PDType0Font.load(document.getTarget(), inputStream, isEmbedded);
            }
            if (isEmbedded) {
                document.addFont(fontResourcePath, font);
            }
            return font;
        }catch (Exception e) {
            throw new IllegalArgumentException("the font can not be loaded");
        }
    }

    /**
     * 加载字体(资源路径)
     * @param document pdf文档
     * @param page pdf页面
     * @param fontResourcePath 字体路径(资源路径)
     * @param isEmbedded 是否嵌入
     * @return 返回pdfBox字体
     */
    private static PDFont loadFontForResource(XEasyPdfDocument document, XEasyPdfPage page, String fontResourcePath, boolean isEmbedded) {
        if (fontResourcePath!=null) {
            return loadFontForResource(document, fontResourcePath, isEmbedded);
        }else {
            return loadFont(document, page);
        }
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回pdfBox字体
     */
    private static PDFont loadFont(XEasyPdfDocument document, XEasyPdfPage page) {
        PDFont font = null;
        if (page!=null) {
            font = page.getParam().getFont();
        }
        if (document!=null) {
            font = document.getFont();
        }
        if (font==null) {
            throw new IllegalArgumentException("the font can not be found");
        }
        return font;
    }


    /**
     * 获取字体高度
     * @param font pdfbox字体
     * @param fontSize 字体大小
     * @return 返回字体高度
     */
    public static float getFontHeight(PDFont font, float fontSize) {
        return font.getFontDescriptor().getCapHeight() / 1000F * fontSize;
    }

    /**
     * 添加文本关联
     * @param font pdfbox字体
     * @param text 文本
     */
    public static void addToSubset(PDFont font, String text) {
        if (font!=null&&font.willBeSubset()) {
            int offset = 0;
            int length = text.length();
            while (offset < length) {
                int codePoint = text.codePointAt(offset);
                font.addToSubset(codePoint);
                offset += Character.charCount(codePoint);
            }
        }
    }
}
