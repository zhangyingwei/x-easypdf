package wiki.xsx.core.pdf.component.doc;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.encryption.ProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.FontUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * pdf文档参数
 * @author xsx
 * @date 2020/4/7
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
@Data
@Accessors(chain = true)
public class XEasyPdfDocumentParam {
    /**
     * 字体路径
     */
    private String fontPath;
    /**
     * 字体
     */
    private PDFont font;
    /**
     * pdfBox文档
     */
    private PDDocument document;
    /**
     * pdf页面列表
     */
    private List<XEasyPdfPage> pageList = new ArrayList<>(10);
    /**
     * 全局页面水印
     */
    private XEasyPdfWatermark globalWatermark;
    /**
     * pdfBox保护策略
     */
    private ProtectionPolicy policy;
    /**
     * pdfBox文档属性
     */
    private XEasyPdfDocumentInfo info;
    /**
     * pdfBox文档（目标文档）
     */
    private PDDocument target;
    /**
     * 是否重置
     */
    private boolean isReset;

    /**
     * 初始化字体
     * @param document pdf文档
     */
    protected void initFont(XEasyPdfDocument document) {
        if (this.fontPath!=null&&this.font==null) {
            this.font = FontUtil.loadFont(document, this.fontPath);
        }
    }

    /**
     * 初始化文档信息
     */
    protected void initInfo() {
        if (this.document!=null) {
            PDDocumentInformation documentInformation = this.document.getDocumentInformation();
            this.info = XEasyPdfDocumentInfo.builder()
                    .title(documentInformation.getTitle())
                    .author(documentInformation.getAuthor())
                    .subject(documentInformation.getSubject())
                    .keywords(documentInformation.getKeywords())
                    .creator(documentInformation.getCreator())
                    .creationDate(documentInformation.getCreationDate())
                    .modificationDate(documentInformation.getModificationDate()).build();
        }
    }
}
