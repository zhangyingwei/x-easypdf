package wiki.xsx.core.pdf.component;

import wiki.xsx.core.pdf.doc.XpdfDocument;
import wiki.xsx.core.pdf.doc.XpdfPage;

import java.io.IOException;

/**
 * pdf组件标记
 * @author xsx
 * @date 2020/3/3
 * @since 1.8
 */
public interface XpdfComponent {

    /**
     * 画图
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    void draw(XpdfDocument document, XpdfPage page) throws IOException;
}
