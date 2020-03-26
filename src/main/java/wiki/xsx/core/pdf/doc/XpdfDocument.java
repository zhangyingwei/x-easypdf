package wiki.xsx.core.pdf.doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import wiki.xsx.core.pdf.component.mark.XpdfWatermark;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * pdf文档
 * @author xsx
 * @date 2020/3/3
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * xpdf is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 * </p>
 */
public class XpdfDocument {
    /**
     * pdfBox文档
     */
    private PDDocument document;
    /**
     * pdf页面列表
     */
    private List<XpdfPage> pageList = new ArrayList<>(10);
    /**
     * 全局页面水印
     */
    private XpdfWatermark globalWatermark;

    /**
     * 无参构造方法
     */
    public XpdfDocument() {
        this.document = new PDDocument();
    }

    /**
     * 有参构造方法
     * @param filePath pdf文件路径
     * @throws IOException IO异常
     */
    public XpdfDocument(String filePath) throws IOException {
        // 读取pdfBox文档
        this.document = PDDocument.load(new File(filePath));
        // 获取pdfBox页面树
        PDPageTree pages = this.document.getPages();
        // 遍历pdfBox页面树
        for (PDPage page : pages) {
            // 添加pdfBox页面
            this.pageList.add(new XpdfPage(page));
        }
    }

    /**
     * 添加pdf页面
     * @param pages pdf页面
     * @return 返回pdf文档
     */
    public XpdfDocument addPage(XpdfPage ...pages) {
        // 添加页面
        Collections.addAll(this.pageList, pages);
        return this;
    }

    /**
     * 插入pdf页面
     * @param index 页面索引
     * @param pages pdf页面
     * @return 返回pdf文档
     */
    public XpdfDocument insertPage(int index, XpdfPage ...pages) {
        // 如果pdf页面列表数量大于索引，则插入页面，否则添加页面
        if (this.pageList.size()>=index) {
            // 遍历pdf页面
            for (XpdfPage page : pages) {
                // 插入页面
                this.pageList.add(Math.max(index, 0), page);
            }
        }else {
            // 添加页面
            this.addPage(pages);
        }
        return this;
    }

    /**
     * 设置文档水印（每个页面都将添加水印）
     * @param globalWatermark 页面水印
     * @return 返回pdf文档
     */
    public XpdfDocument setGlobalWatermark(XpdfWatermark globalWatermark) {
        this.globalWatermark = globalWatermark;
        return this;
    }

    /**
     * 保存（页面构建）
     * @param outputStream 文件输出流
     * @throws IOException IO异常
     */
    public void save(OutputStream outputStream) throws IOException {
        // 定义任务文档
        PDDocument target = new PDDocument();
        // 定义pdfBox页面列表
        List<PDPage> pageList;
        // 遍历pdf页面列表
        for (XpdfPage xpdfPage : this.pageList) {
            // 如果pdf页面组件数量大于0，则进行页面构建
            if (xpdfPage.getComponentList().size()>0) {
                // pdf页面构建
                xpdfPage.build(this);
            }
            // 初始化pdfBox页面列表
            pageList = xpdfPage.getPageList();
            // 遍历pdfBox页面列表
            for (PDPage page : pageList) {
                // 任务文档添加页面
                target.addPage(page);
            }
            // 如果页面水印不为空，则进行页面水印绘制
            if (xpdfPage.getWatermark()!=null) {
                // 绘制页面水印
                xpdfPage.getWatermark().draw(this, xpdfPage);
            // 如果页面水印为空，文档全局页面水印不为空且当前pdf页面允许添加页面水印，则进行页面水印绘制
            }else if (this.globalWatermark !=null&&xpdfPage.isAllowWatermark()) {
                // 绘制页面水印
                this.globalWatermark.draw(this, xpdfPage);
            }
        }
        // 保存任务文档
        target.save(outputStream);
        // 关闭任务文档
        target.close();
        // 关闭pdfBox文档
        this.document.close();
    }

    /**
     * 获取pdfBox文档
     * @return 返回pdfBox文档
     */
    public PDDocument getDocument() {
        return this.document;
    }

    /**
     * 获取pdfBox页面列表
     * @return 返回pdfBox页面列表
     */
    public List<XpdfPage> getPageList() {
        return this.pageList;
    }
}
