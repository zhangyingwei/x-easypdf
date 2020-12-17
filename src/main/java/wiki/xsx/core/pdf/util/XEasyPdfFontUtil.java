package wiki.xsx.core.pdf.util;

import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 字体工具
 * @author xsx
 * @date 2020/4/7
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
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
     * 获取字体
     * @param document pdf文档
     * @param fontPath 字体路径
     * @param defaultFont 默认字体
     * @return 返回pdfBox字体
     */
    public static PDFont getFont(XEasyPdfDocument document, String fontPath, PDFont defaultFont) {
        if (fontPath!=null) {
            return loadFont(document, fontPath);
        }
        return defaultFont;
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param inputStream 字体数据流
     * @return 返回pdfBox字体
     */
    public static PDFont loadFont(XEasyPdfDocument document, InputStream inputStream) {
        try {
            return PDType0Font.load(document.getTarget(), inputStream);
        }catch (Exception e) {
            throw new RuntimeException("the font can not be loaded");
        }
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param fontPath 字体路径
     * @return 返回pdfBox字体
     */
    public static PDFont loadFont(XEasyPdfDocument document, String fontPath) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(fontPath))) {
            return PDType0Font.load(document.getTarget(), inputStream);
        }catch (Exception e) {
            throw new RuntimeException("the font can not be loaded");
        }
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param page pdf页面
     * @param fontPath 字体路径
     * @return 返回pdfBox字体
     */
    public static PDFont loadFont(XEasyPdfDocument document, XEasyPdfPage page, String fontPath) {
        if (fontPath!=null) {
            try (InputStream inputStream = Files.newInputStream(Paths.get(fontPath))) {
                return loadFont(document, page, inputStream);
            }catch (Exception e) {
                throw new RuntimeException("the font can not be loaded");
            }
        }else {
            return loadFont(document, page, (InputStream) null);
        }
    }

    /**
     * 加载字体
     * @param document pdf文档
     * @param page pdf页面
     * @param inputStream 字体数据流
     * @return 返回pdfBox字体
     */
    public static PDFont loadFont(XEasyPdfDocument document, XEasyPdfPage page, InputStream inputStream) {
        PDFont font = null;
        if (inputStream!=null) {
            try  {
                font = PDType0Font.load(document.getTarget(), inputStream);
            }catch (IOException ex) {
                throw new RuntimeException("the font can not be loaded");
            }
        }
        if (font==null&&page!=null) {
            font = page.getFont();
        }
        if (font==null&&document!=null) {
            font = document.getFont();
        }
        if (font==null) {
            throw new RuntimeException("the font can not be found");
        }
        return font;
    }

    /**
     * 添加文本关联
     * @param font pdfbox字体
     * @param text 文本
     */
    public static void addToSubset(PDFont font, String text) {
        if (font.willBeSubset()) {
            int offset = 0;
            while (offset < text.length()) {
                int codePoint = text.codePointAt(offset);
                font.addToSubset(codePoint);
                offset += Character.charCount(codePoint);
            }
        }
    }
}
