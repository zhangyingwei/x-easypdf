package wiki.xsx.core.pdf.doc;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XpdfComponent;
import wiki.xsx.core.pdf.component.mark.XpdfWatermark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * pdf页面
 * @author xsx
 * @date 2020/3/9
 * @since 1.8
 */
@Data
@Accessors(chain = true)
public class XpdfPage {

    /**
     * pdfBox最新页面当前X轴坐标
     */
    private Float pageX;
    /**
     * pdfBox最新页面当前Y轴坐标
     */
    private Float pageY;
    /**
     * pdfBox页面尺寸
     */
    private PDRectangle pageSize = PDRectangle.A4;
    /**
     * 包含的pdfBox页面列表
     */
    private List<PDPage> pageList = new ArrayList<>(10);
    /**
     * pdf组件列表
     */
    private List<XpdfComponent> componentList = new ArrayList<>(10);
    /**
     * 页面水印
     */
    private XpdfWatermark watermark;
    /**
     * 是否允许添加水印
     */
    private boolean allowWatermark = true;

    /**
     * 无参构造
     */
    public XpdfPage() {
    }

    /**
     * 有参构造
     * @param page pdfBox页面
     */
    public XpdfPage(PDPage page) {
        this.pageList.add(page);
    }

    /**
     * 有参构造
     * @param pageSize pdfBox页面尺寸
     */
    public XpdfPage(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置是否允许添加水印
     * @param allowWatermark 是否允许添加水印
     * @return 返回pdf页面
     */
    public XpdfPage setAllowWatermark(boolean allowWatermark) {
        this.allowWatermark = allowWatermark;
        return this;
    }

    /**
     * 添加pdf组件
     * @param components pdf组件
     * @return 返回pdf页面
     */
    public XpdfPage addComponent(XpdfComponent...components) {
        // 如果组件不为空，则添加组件
        if (components!=null) {
            // 添加组件
            this.componentList.addAll(Arrays.asList(components));
        }
        return this;
    }

    /**
     * 构建pdf页面
     * @return 返回pdf页面
     * @throws IOException IO异常
     */
    public XpdfPage build(XpdfDocument document) throws IOException {
        return this.build(document, null);
    }

    /**
     * 构建pdf页面
     * @param pageSize 页面尺寸
     * @return 返回pdf页面
     * @throws IOException IO异常
     */
    public XpdfPage build(XpdfDocument document, PDRectangle pageSize) throws IOException {
        // 添加pdfBox页面，如果页面尺寸为空，则添加默认A4页面，否则添加所给尺寸页面
        this.pageList.add(pageSize==null?new PDPage(this.pageSize):new PDPage(pageSize));
        // 遍历组件列表
        for (XpdfComponent component : componentList) {
            // 组件绘制
            component.draw(document, this);
        }
        return this;
    }

    /**
     * 获取pdfBox最新页面
     * @return 返回pdfBox最新页面
     */
    public PDPage getLastPage() {
        return this.pageList.isEmpty()?null:this.pageList.get(this.pageList.size()-1);
    }
}
