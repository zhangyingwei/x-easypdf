package wiki.xsx.core.pdf.component.doc;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.ProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;
import wiki.xsx.core.pdf.component.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.FontUtil;

import java.util.ArrayList;
import java.util.List;

/**
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
     * 初始化字体
     * @param document pdf文档
     */
    protected void initFont(XEasyPdfDocument document) {
        if (this.fontPath!=null&&this.font==null) {
            this.font = FontUtil.loadFont(document, this.fontPath);
        }
    }
}
