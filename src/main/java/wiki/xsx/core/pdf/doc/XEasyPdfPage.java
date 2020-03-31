package wiki.xsx.core.pdf.doc;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.mark.XEasyPdfWatermark;

import java.io.IOException;
import java.util.ArrayList;
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
    private List<XEasyPdfComponent> componentList = new ArrayList<>(10);
    /**
     * 页面水印
     */
    private XEasyPdfWatermark watermark;
    /**
     * 是否允许添加水印
     */
    private boolean allowWatermark = true;

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
        this.pageList.add(page);
    }

    /**
     * 有参构造
     * @param pageSize pdfBox页面尺寸
     */
    public XEasyPdfPage(PDRectangle pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置是否允许添加水印
     * @param allowWatermark 是否允许添加水印
     * @return 返回pdf页面
     */
    public XEasyPdfPage setAllowWatermark(boolean allowWatermark) {
        this.allowWatermark = allowWatermark;
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
            this.componentList.addAll(Arrays.asList(components));
        }
        return this;
    }

    /**
     * 构建pdf页面
     * @return 返回pdf页面
     * @throws IOException IO异常
     */
    public XEasyPdfPage build(XEasyPdfDocument document) throws IOException {
        return this.build(document, null);
    }

    /**
     * 构建pdf页面
     * @param pageSize 页面尺寸
     * @return 返回pdf页面
     * @throws IOException IO异常
     */
    public XEasyPdfPage build(XEasyPdfDocument document, PDRectangle pageSize) throws IOException {
        // 添加pdfBox页面，如果页面尺寸为空，则添加默认A4页面，否则添加所给尺寸页面
        this.pageList.add(pageSize==null?new PDPage(this.pageSize):new PDPage(pageSize));
        // 遍历组件列表
        for (XEasyPdfComponent component : this.componentList) {
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
