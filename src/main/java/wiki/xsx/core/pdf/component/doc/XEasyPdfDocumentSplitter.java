package wiki.xsx.core.pdf.component.doc;

import wiki.xsx.core.pdf.util.ConvertUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * pdf文档拆分器
 * @author xsx
 * @date 2020/5/24
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
public class XEasyPdfDocumentSplitter {

    /**
     * 拆分文档列表
     */
    private final List<List<Integer>> documentList = new ArrayList<>(10);

    /**
     * 构造方法私有化
     */
    private XEasyPdfDocumentSplitter() {}

    /**
     * 构建文档拆分器
     * @return 返回pdf文档拆分器
     */
    public static XEasyPdfDocumentSplitter build() {
        return new XEasyPdfDocumentSplitter();
    }

    /**
     * 添加拆分文档
     * @param pageIndex 拆分页面索引
     * @return 返回pdf文档拆分器
     */
    public XEasyPdfDocumentSplitter addDocument(int ...pageIndex) {
        this.documentList.add(ConvertUtil.toInteger(pageIndex));
        return this;
    }

    /**
     * 获取拆分文档列表
     * @return 返回pdf文档拆分器
     */
    public List<List<Integer>> getDocumentList() {
        return this.documentList;
    }
}
