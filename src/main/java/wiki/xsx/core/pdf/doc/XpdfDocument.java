package wiki.xsx.core.pdf.doc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * pdf文档
 * @author xsx
 * @date 2020/3/3
 * @since 1.8
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
        this.document = PDDocument.load(new File(filePath));
    }

    /**
     * 添加pdf页面
     * @param pages pdf页面
     * @return 返回pdf文档
     */
    public XpdfDocument addPage(XpdfPage ...pages) {
        this.pageList.addAll(Arrays.asList(pages));
        return this;
    }

    /**
     * 插入pdf页面
     * @param index 页面索引
     * @param page pdf页面
     * @return 返回pdf文档
     */
    public XpdfDocument insertPage(int index, XpdfPage page) {
        // 如果pdf页面列表数量大于索引，则插入页面，否则添加页面
        if (this.pageList.size()>index) {
            // 插入页面
            this.pageList.add(index, page);
        }else {
            // 添加页面
            this.pageList.add(page);
        }
        return this;
    }

    /**
     * 保存（页面构建）
     * @param path 保存路径
     * @throws IOException IO异常
     */
    public void save(String path) throws IOException {
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
                // pdfBox文档添加页面
                this.document.addPage(page);
            }
        }
        // 保存文档
        this.document.save(new File(path));
        // 关闭文档
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
