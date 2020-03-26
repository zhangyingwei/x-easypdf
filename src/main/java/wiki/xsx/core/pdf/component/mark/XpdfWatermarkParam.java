package wiki.xsx.core.pdf.component.mark;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import wiki.xsx.core.pdf.doc.XpdfDocument;
import wiki.xsx.core.pdf.doc.XpdfPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * pdf页面水印参数
 * @author xsx
 * @date 2020/3/25
 * @since 1.8
 */
@Data
@Accessors(chain = true)
public class XpdfWatermarkParam {
    /**
     * 字体路径
     */
    private String fontPath;
    /**
     * 字体
     */
    private PDFont font;
    /**
     * 字体大小
     */
    private Float fontSize = 50F;
    /**
     * 透明度（值越小越透明，0-1）
     */
    private Float alpha = 0.1F;
    /**
     * 文本弧度
     */
    private Double radians = 120D;
    /**
     * 水印文本
     */
    private String text;
    /**
     * 文本间距
     */
    private Float wordSpace;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回pdfBox扩展图形对象
     * @throws IOException IO异常
     */
    public PDExtendedGraphicsState init(XpdfDocument document, XpdfPage page) throws IOException {
        // 如果字体未初始化，则进行初始化
        if (this.font==null) {
            // 初始化字体
            this.font = PDType0Font.load(
                    document.getDocument(),
                    Files.newInputStream(Paths.get(this.fontPath))
            );
        }
        // 如果水印文本未初始化，则进行初始化
        if (this.text==null) {
            // 初始化水印文本
            this.text = "xpdf";
        }
        // 如果文本间距未初始化，则进行初始化
        if (this.wordSpace==null) {
            // 初始化文本间距，默认文本间距 = 文本长度 * 字体大小
            this.wordSpace = this.text.length() * this.fontSize;
        }
        // 初始化pdfBox扩展图形对象
        PDExtendedGraphicsState state = new PDExtendedGraphicsState();
        // 设置文本透明度
        state.setNonStrokingAlphaConstant(this.alpha);
        // 设置透明度标记
        state.setAlphaSourceFlag(true);
        return state;
    }


}
