package wiki.xsx.core.pdf.component.line;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * pdf线条参数
 * @author xsx
 * @date 2020/3/11
 * @since 1.8
 */
@Data
@Accessors(chain = true)
public class XpdfLineParam {
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
    private Float fontSize = 1F;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
    /**
     * 页面X轴结束坐标
     */
    private Float endX;
    /**
     * 页面Y轴结束坐标
     */
    private Float endY;
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 右边距
     */
    private Float marginRight = 0F;
    /**
     * 上边距
     */
    private Float marginTop = 0F;
    /**
     * 下边距
     */
    private Float marginBottom = 0F;
    /**
     * 线宽
     */
    private Float lineWidth = 1F;
    /**
     * 线型
     */
    private LineCapStyle lineCapStyle = LineCapStyle.NORMAL;
}
