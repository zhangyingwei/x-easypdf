package wiki.xsx.core.pdf.component.page;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.XEasyPdfComponentBuilder;
import wiki.xsx.core.pdf.component.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * pdf页面
 * @author xsx
 * @date 2020/3/9
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
public class XEasyPdfPage {

    /**
     * pdf页面参数
     */
    private XEasyPdfPageParam param = new XEasyPdfPageParam();

    /**
     * 无参构造
     */
    public XEasyPdfPage() {
    }

    /**
     * 有参构造
     * @param page pdfBox页面
     */
    public XEasyPdfPage(PDPage page) {
        this.param.getPageList().add(page);
    }

    /**
     * 有参构造
     * @param pageSize pdfBox页面尺寸
     */
    public XEasyPdfPage(PDRectangle pageSize) {
        if (pageSize!=null) {
            this.param.setPageSize(pageSize);
        }
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回pdf页面
     */
    public XEasyPdfPage setFontPath(String fontPath) {
        if (fontPath!=null&&fontPath.trim().length()>0) {
            this.param.setFontPath(fontPath);
        }
        return this;
    }

    /**
     * 设置字体
     * @param font 字体
     * @return 返回pdf页面
     */
    public XEasyPdfPage setFont(PDFont font) {
        this.param.setFont(font);
        return this;
    }

    /**
     * 获取字体
     * @return 返回pdfBox字体
     */
    public PDFont getFont() {
        return this.param.getFont();
    }

    /**
     * 设置水印
     * @param watermark pdf水印
     * @return 返回pdf页面
     */
    public XEasyPdfPage setWatermark(XEasyPdfWatermark watermark) {
        this.param.setWatermark(watermark);
        return this;
    }

    /**
     * 设置是否允许添加水印
     * @param allowWatermark 是否允许添加水印
     * @return 返回pdf页面
     */
    public XEasyPdfPage setAllowWatermark(boolean allowWatermark) {
        this.param.setAllowWatermark(allowWatermark);
        return this;
    }

    /**
     * 添加pdf组件
     * @param components pdf组件
     * @return 返回pdf页面
     */
    public XEasyPdfPage addComponent(XEasyPdfComponent...components) {
        // 如果组件不为空，则添加组件
        if (components!=null) {
            // 添加组件
            this.param.getComponentList().addAll(Arrays.asList(components));
        }
        return this;
    }

    /**
     * 构建pdf页面
     * @param document pdf文档
     * @return 返回pdf页面
     * @throws IOException IO异常
     */
    public XEasyPdfPage build(XEasyPdfDocument document) throws IOException {
        return this.build(document, null);
    }

    /**
     * 构建pdf页面
     * @param document pdf文档
     * @param pageSize 页面尺寸
     * @return 返回pdf页面
     * @throws IOException IO异常
     */
    public XEasyPdfPage build(XEasyPdfDocument document, PDRectangle pageSize) throws IOException {
        // 初始化字体
        this.param.initFont(document);
        // 添加pdfBox页面，如果页面尺寸为空，则添加默认A4页面，否则添加所给尺寸页面
        this.param.getPageList().add(pageSize==null?new PDPage(this.param.getPageSize()):new PDPage(pageSize));
        // 获取pdf组件列表
        List<XEasyPdfComponent> componentList = this.param.getComponentList();
        // 遍历组件列表
        for (XEasyPdfComponent component : componentList) {
            // 如果组件属于pdf组件建造器接口，则进行组件绘制
            if (component instanceof XEasyPdfComponentBuilder) {
                // 组件绘制
                ((XEasyPdfComponentBuilder) component).draw(document, this);
            }
        }
        return this;
    }

    /**
     * 获取pdfBox最新页面
     * @return 返回pdfBox最新页面
     */
    public PDPage getLastPage() {
        List<PDPage> pageList = this.param.getPageList();
        return pageList.isEmpty()?null:pageList.get(pageList.size()-1);
    }
}
