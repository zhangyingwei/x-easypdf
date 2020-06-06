package wiki.xsx.core.pdf.component.doc;

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
public class XEasyPdfDocumentInfo {
    /**
     * pdf文档
     */
    private final XEasyPdfDocument document;
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
     * 有参构造
     * @param document pdf文档
     */
    XEasyPdfDocumentInfo(XEasyPdfDocument document) {
        this.document = document;
    }

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

    /**
     * 设置标题
     * @param title 标题
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置作者
     * @param author 作者
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * 设置主题
     * @param subject 主题
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * 设置关键词
     * @param keywords 关键词
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    /**
     * 设置创建者
     * @param creator 创建者
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setCreateTime(Calendar createTime) {
        this.creationDate = createTime;
        return this;
    }

    /**
     * 设置修改时间
     * @param updateTime 修改时间
     * @return 返回pdf文档信息
     */
    public XEasyPdfDocumentInfo setUpdateTime(Calendar updateTime) {
        this.modificationDate = updateTime;
        return this;
    }

    /**
     * 完成
     * @return 返回pdf文档
     */
    public XEasyPdfDocument finish() {
        this.document.setInfo(this);
        return this.document;
    }
}
