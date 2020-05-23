package wiki.xsx.core.pdf.component.doc;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.util.Calendar;

/**
 * pdf文档信息
 * @author xsx
 * @date 2020/5/19
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
@Builder
public class XEasyPdfDocumentInfo {
    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;
    /**
     * 主题
     */
    private String subject;
    /**
     * 关键词
     */
    private String keywords;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private Calendar creationDate;
    /**
     * 修改时间
     */
    private Calendar modificationDate;

    /**
     * 获取pdfBox文档信息
     * @return 返回pdfBox文档信息
     */
    public PDDocumentInformation getInfo() {
        PDDocumentInformation pdInfo = new PDDocumentInformation();
        pdInfo.setTitle(this.title);
        pdInfo.setAuthor(this.author);
        pdInfo.setSubject(this.subject);
        pdInfo.setKeywords(this.keywords);
        pdInfo.setCreator(this.creator);
        pdInfo.setCreationDate(this.creationDate);
        pdInfo.setModificationDate(this.modificationDate);
        return pdInfo;
    }
}
