package wiki.xsx.core.pdf.doc;

import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * pdf文档书签
 * @author xsx
 * @date 2020/10/23
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
public class XEasyPdfDocumentBookmark {
    /**
     * pdf文档
     */
    private final XEasyPdfDocument document;
    /**
     * 书签节点列表
     */
    private final List<PDOutlineItem> itemList = new ArrayList<>(256);

    /**
     * 有参构造
     * @param document pdf文档
     */
    XEasyPdfDocumentBookmark(XEasyPdfDocument document) {
        this.document = document;
    }

    /**
     * 设置书签
     * @param pageIndex 页面索引
     * @param title 标题
     * @return 返回pdf文档书签
     */
    public XEasyPdfDocumentBookmark setBookMark(Integer pageIndex, String title) {
        PDPageFitWidthDestination destination = new PDPageFitWidthDestination();
        destination.setPageNumber(pageIndex);
        destination.setTop(Integer.MAX_VALUE);
        PDOutlineItem item = new PDOutlineItem();
        item.setDestination(destination);
        item.setTitle(title);
        this.itemList.add(item);
        return this;
    }

    public XEasyPdfDocumentBookmark setBookMark(BookmarkNode node) {
        this.itemList.add(node.getItem());
        return this;
    }

    /**
     * 完成信息设置
     * @return 返回pdf文档
     */
    public XEasyPdfDocument finish() {
        this.document.setBookmark(this);
        return this.document;
    }

    /**
     * 获取书签节点列表
     * @return 返回书签节点列表
     */
    List<PDOutlineItem> getItemList() {
        return this.itemList;
    }

    public static class BookmarkNode {

        private final PDOutlineItem outlineItem;
        private final PDPageFitWidthDestination destination;

        public BookmarkNode(PDOutlineItem outlineItem) {
            this.outlineItem = outlineItem;
            this.destination = new PDPageFitWidthDestination();
            this.destination.setTop(Integer.MAX_VALUE);
            this.outlineItem.setDestination(this.destination);
        }

        public static BookmarkNode build() {
            return new BookmarkNode(new PDOutlineItem());
        }

        public BookmarkNode addChild(BookmarkNode childNode) {
            this.outlineItem.addLast(childNode.getItem());
            return this;
        }

        public BookmarkNode setPage(int pageIndex) {
            this.destination.setPageNumber(pageIndex);
            return this;
        }

        public BookmarkNode setTop(int pageY) {
            this.destination.setTop(pageY);
            return this;
        }

        public BookmarkNode setTitle(String title) {
            this.outlineItem.setTitle(title);
            return this;
        }

        public BookmarkNode setTextColor(Color textColor) {
            this.outlineItem.setTextColor(textColor);
            return this;
        }

        public BookmarkNode enableBold() {
            this.outlineItem.setBold(true);
            return this;
        }

        public BookmarkNode enableItalic() {
            this.outlineItem.setItalic(true);
            return this;
        }

        PDOutlineItem getItem() {
            return this.outlineItem;
        }
    }
}
