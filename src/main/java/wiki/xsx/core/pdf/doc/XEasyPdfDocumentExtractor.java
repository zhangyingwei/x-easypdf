package wiki.xsx.core.pdf.doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * pdf文档提取器
 * @author xsx
 * @date 2020/11/15
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
final class XEasyPdfDocumentExtractor extends PDFTextStripper {

    private final PDDocument document;
    private final XEasyPdfDocumentRectangleExtractor rectangleExtractor = new XEasyPdfDocumentRectangleExtractor();

    /**
     * 构造方法
     * @param document pdfbox文档
     * @throws IOException IO异常
     */
    public XEasyPdfDocumentExtractor(PDDocument document) throws IOException {
        this.document = document;
    }

    XEasyPdfDocumentExtractor addRegion(Map<String, Rectangle> regionArea) {
        this.rectangleExtractor.addRegion(regionArea);
        return this;
    }

    void extractRegions(Map<String, String> data, int ...pageIndex) throws IOException {
        if (pageIndex!=null&&pageIndex.length>0) {
            for (int index : pageIndex) {
                data.putAll(this.rectangleExtractor.extract(this.document.getPage(index)));
            }
        }else {
            for (int index = 0, count = this.document.getNumberOfPages() - 1; index < count; index++) {
                data.putAll(this.rectangleExtractor.extract(this.document.getPage(index)));
            }
        }
    }

    /**
     * 提取文本
     * @param textList 文本列表
     * @param regex 正则表达式
     * @param pageIndex 页面索引
     */
    void extract(List<String> textList, String regex, int ...pageIndex) throws IOException {
        if (pageIndex!=null&&pageIndex.length>0) {
            for (int index : pageIndex) {
                this.setStartPage(index);
                this.setEndPage(index);
                this.extract(textList, regex);
            }
        }else {
            this.extract(textList, regex);
        }
    }


    private void extract(List<String> textList, String regex) throws IOException {
        String text = this.getText(this.document);
        if (regex!=null&&regex.trim().length()>0) {
            Matcher matcher = Pattern.compile(regex).matcher(text);
            while (matcher.find()) {
                textList.add(matcher.group());
            }
        }else {
            textList.add(text);
        }
    }

    private class XEasyPdfDocumentRectangleExtractor extends PDFTextStripper {

        private final Map<String, ArrayList<List<TextPosition>>> regionCharacterList = new HashMap<>();
        private final Map<String, StringWriter> regionText = new HashMap<>();
        private final Map<String, Rectangle> regionArea = new HashMap<>(32);

        XEasyPdfDocumentRectangleExtractor() throws IOException {
            super.setSortByPosition(true);
        }

        void addRegion(Map<String, Rectangle> regionArea) {
            this.regionArea.putAll(regionArea);
        }

        Map<String, String> extract(PDPage page) throws IOException {
            Set<String> keySet = this.regionArea.keySet();
            Map<String, String> data = new HashMap<>(keySet.size());
            for (String region : keySet) {
                this.setStartPage(this.getCurrentPageNo());
                this.setEndPage(this.getCurrentPageNo());
                ArrayList<List<TextPosition>> regionCharactersByArticle = new ArrayList<>();
                regionCharactersByArticle.add(new ArrayList<>());
                this.regionCharacterList.put(region, regionCharactersByArticle);
                this.regionText.put(region, new StringWriter());
            }
            if(page.hasContents()) {
                this.processPage(page);
            }
            for (String region : keySet) {
                data.put(region, this.regionText.get(region).toString());
            }
            return data;
        }

        @Override
        protected void processTextPosition(TextPosition text) {
            Set<Map.Entry<String, Rectangle>> entrySet = this.regionArea.entrySet();
            for (Map.Entry<String, Rectangle> regionAreaEntry : entrySet) {
                Rectangle2D rect = regionAreaEntry.getValue();
                if (rect.contains(text.getX(), text.getY())) {
                    this.charactersByArticle = regionCharacterList.get(regionAreaEntry.getKey());
                    super.processTextPosition(text);
                }
            }
        }

        @Override
        protected void writePage() throws IOException {
            for (String region : regionArea.keySet()) {
                this.charactersByArticle = regionCharacterList.get(region);
                this.output = regionText.get(region);
                super.writePage();
            }
        }
    }
}
