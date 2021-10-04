package wiki.xsx.core.pdf.doc;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.footer.XEasyPdfFooter;
import wiki.xsx.core.pdf.header.XEasyPdfHeader;
import wiki.xsx.core.pdf.mark.XEasyPdfWatermark;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * pdf页面
 * @author xsx
 * @date 2020/3/9
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
        // 添加pdfBox页面
        this.param.getPageList().add(page);
        // 总页数自增
        this.param.totalPageIncrement();
    }

    /**
     * 有参构造
     * @param pageSize pdfBox页面尺寸
     */
    public XEasyPdfPage(PDRectangle pageSize) {
        // 如果pdfBox页面尺寸不为空，则设置pdfBox页面尺寸
        if (pageSize!=null) {
            // 设置pdfBox页面尺寸
            this.param.setPageSize(pageSize);
        }
    }

    /**
     * 添加pdfBox页面
     *  @param page pdfBox页面
     * @return 返回pdf页面
     */
    public XEasyPdfPage addPage(PDPage page) {
        // 添加pdfBox页面
        this.param.getPageList().add(page);
        // 总页数自增
        this.param.totalPageIncrement();
        return this;
    }

    /**
     * 添加新页面
     * @param pageSize 页面尺寸
     * @param document pdf文档
     * @return 返回pdf页面
     */
    public XEasyPdfPage addNewPage(XEasyPdfDocument document, PDRectangle pageSize) {
        // 添加pdfBox页面，如果页面尺寸为空，则添加默认A4页面，否则添加所给尺寸页面
        this.param.getNewPageList().add(pageSize==null?new PDPage(this.param.getPageSize()):new PDPage(pageSize));
        // 总页数自增
        this.param.totalPageIncrement();
        // 重置页面X轴Y轴起始坐标
        this.getParam().setPageX(null).setPageY(null);
        // 绘制页眉与页脚
        this.drawHeaderAndFooter(document);
        // 绘制背景图片
        this.drawBackgroundImage(document);
        // 设置背景颜色
        this.setLastPageBackgroundColor(document);
        // 初始化总页数
        document.getParam().initTotalPage(1);
        return this;
    }

    /**
     * 设置字体路径
     * @param fontPath 字体路径
     * @return 返回pdf页面
     */
    public XEasyPdfPage setFontPath(String fontPath) {
        this.param.setFontPath(fontPath);
        return this;
    }

    /**
     * 设置默认字体样式
     * @param style 默认字体样式
     * @return 返回pdf页面
     */
    public XEasyPdfPage setDefaultFontStyle(XEasyPdfDefaultFontStyle style) {
        this.param.setDefaultFontStyle(style);
        return this;
    }

    /**
     * 设置页面背景图片
     * @param backgroundImage 背景图片
     * @return 返回pdf页面
     */
    public XEasyPdfPage setBackgroundImage(XEasyPdfImage backgroundImage) {
        this.param.setBackgroundImage(backgroundImage);
        return this;
    }

    /**
     * 获取页面背景图片
     * @return 返回pdf页面
     */
    public XEasyPdfImage getBackgroundImage() {
        return this.param.getBackgroundImage();
    }

    /**
     * 设置页面背景色
     * @param backgroundColor 背景色
     * @return 返回pdf页面
     */
    public XEasyPdfPage setBackgroundColor(Color backgroundColor) {
        this.param.setBackgroundColor(backgroundColor);
        return this;
    }

    /**
     * 获取页面背景色
     * @return 返回页面背景色
     */
    public Color getBackgroundColor() {
        return this.param.getBackgroundColor();
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
     * 获取水印
     * @return 返回pdf水印
     */
    public XEasyPdfWatermark getWatermark() {
        return this.param.getWatermark();
    }

    /**
     * 设置页眉
     * @param header pdf页眉
     * @return 返回pdf页面
     */
    public XEasyPdfPage setHeader(XEasyPdfHeader header) {
        this.param.setHeader(header);
        return this;
    }

    /**
     * 获取页眉
     * @return 返回pdf页眉
     */
    public XEasyPdfHeader getHeader() {
        return this.param.getHeader();
    }

    /**
     * 设置页脚
     * @param footer pdf页脚
     * @return 返回pdf页面
     */
    public XEasyPdfPage setFooter(XEasyPdfFooter footer) {
        this.param.setFooter(footer);
        return this;
    }

    /**
     * 获取页脚
     * @return 返回pdf页脚
     */
    public XEasyPdfFooter getFooter() {
        return this.param.getFooter();
    }


    /**
     * 获取pdfBox最新页面
     * @return 返回pdfBox最新页面
     */
    public PDPage getLastPage() {
        // 定义pdfbox页面
        PDPage pdPage;
        // 获取新页面列表
        List<PDPage> pageList = this.param.getNewPageList();
        // 如果新页面列表为空，则取旧页面列表，否则取新页面列表中最新一个页面
        if (pageList.isEmpty()) {
            // 获取旧页面列表
            List<PDPage> oldPageList = this.param.getPageList();
            // 初始化pdfbox页面，如果旧页面列表为空，则初始化为null，否则初始化为最新一个页面
            pdPage = oldPageList.isEmpty()?null:oldPageList.get(oldPageList.size()-1);
        }else {
            // 初始化pdfbox页面为新页面列表中最新一个页面
            pdPage = pageList.get(pageList.size()-1);
        }
        return pdPage;
    }

    /**
     * 获取pdfBox最新页码
     * @return 返回pdfBox最新页码
     */
    public int getLastPageNum() {
        return this.param.getPageList().size() + this.param.getNewPageList().size();
    }

    /**
     * 开启背景色
     * @return 返回pdf页面
     */
    public XEasyPdfPage enableBackgroundColor() {
        this.param.setAllowBackgroundColor(true);
        return this;
    }

    /**
     * 关闭背景色
     * @return 返回pdf页面
     */
    public XEasyPdfPage disableBackgroundColor() {
        this.param.setAllowBackgroundColor(false);
        return this;
    }

    /**
     * 开启背景图片
     * @return 返回pdf页面
     */
    public XEasyPdfPage enableBackgroundImage() {
        this.param.setAllowBackgroundImage(true);
        return this;
    }

    /**
     * 关闭背景图片
     * @return 返回pdf页面
     */
    public XEasyPdfPage disableBackgroundImage() {
        this.param.setAllowBackgroundImage(false);
        return this;
    }

    /**
     * 开启水印
     * @return 返回pdf页面
     */
    public XEasyPdfPage enableWatermark() {
        this.param.setAllowWatermark(true);
        return this;
    }

    /**
     * 关闭水印
     * @return 返回pdf页面
     */
    public XEasyPdfPage disableWatermark() {
        this.param.setAllowWatermark(false);
        return this;
    }

    /**
     * 开启页眉
     * @return 返回pdf页面
     */
    public XEasyPdfPage enableHeader() {
        this.param.setAllowHeader(true);
        return this;
    }

    /**
     * 关闭页眉
     * @return 返回pdf页面
     */
    public XEasyPdfPage disableHeader() {
        this.param.setAllowHeader(false);
        return this;
    }

    /**
     * 开启页脚
     * @return 返回pdf页面
     */
    public XEasyPdfPage enableFooter() {
        this.param.setAllowFooter(true);
        return this;
    }

    /**
     * 关闭页脚
     * @return 返回pdf页面
     */
    public XEasyPdfPage disableFooter() {
        this.param.setAllowFooter(false);
        return this;
    }

    /**
     * 开启页面自动重置定位
     * @return 返回pdf页面
     */
    public XEasyPdfPage enablePosition() {
        this.param.setAllowResetPosition(true);
        return this;
    }

    /**
     * 关闭页面自动重置定位
     * @return 返回pdf页面
     */
    public XEasyPdfPage disablePosition() {
        this.param.setAllowResetPosition(false);
        return this;
    }

    /**
     * 是否允许添加页眉
     * @return 返回布尔值，true为是，false为否
     */
    public boolean isAllowHeader() {
        return this.param.isAllowHeader();
    }

    /**
     * 是否允许添加页脚
     * @return 返回布尔值，true为是，false为否
     */
    public boolean isAllowFooter() {
        return this.param.isAllowFooter();
    }

    /**
     * 是否允许添加水印
     * @return 返回布尔值，true为是，false为否
     */
    public boolean isAllowWatermark() {
        return this.param.isAllowWatermark();
    }

    /**
     * 是否允许添加背景图片
     * @return 返回布尔值，true为是，false为否
     */
    public boolean isAllowBackgroundImage() {
        return this.param.isAllowBackgroundImage();
    }

    /**
     * 是否允许添加背景色
     * @return 返回布尔值，true为是，false为否
     */
    public boolean isAllowBackgroundColor() {
        return this.param.isAllowBackgroundColor();
    }

    /**
     * 添加pdf组件
     * @param components pdf组件
     * @return 返回pdf页面
     */
    public XEasyPdfPage addComponent(XEasyPdfComponent ...components) {
        // 如果组件不为空，则添加组件
        if (components!=null) {
            // 添加组件
            this.param.getComponentList().addAll(Arrays.asList(components));
        }
        return this;
    }

    /**
     * 修改页面尺寸
     * @param pageSize pdfbox页面尺寸
     * @return 返回pdf页面
     */
    public XEasyPdfPage modifyPageSize(PDRectangle pageSize) {
        // 设置新增页面尺寸
        this.param.setPageSize(pageSize);
        // 设置原有页面尺寸
        this.param.setModifyPageSize(pageSize);
        return this;
    }

    /**
     * 构建pdf页面
     * @param document pdf文档
     * @return 返回pdf页面
     */
    public XEasyPdfPage build(XEasyPdfDocument document) {
        return this.build(document, this.param.getPageSize());
    }

    /**
     * 构建pdf页面
     * @param document pdf文档
     * @param pageSize 页面尺寸
     * @return 返回pdf页面
     */
    public XEasyPdfPage build(XEasyPdfDocument document, PDRectangle pageSize) {
        // 初始化参数
        this.param.init(document, this);
        // 如果原有pdfbox页面列表为空，则添加新页面，否则设置页面尺寸
        if (this.param.getPageList().isEmpty()) {
            // 添加新页面
            this.addNewPage(document, pageSize);
        // 如果原有pdfbox页面列表不为空，则设置页面尺寸
        }else {
            // 如果页面尺寸不为空，则进行设置
            if (this.param.getModifyPageSize()!=null) {
                // 获取页面尺寸
                PDRectangle modifyPageSize = this.param.getModifyPageSize();
                // 获取原有pdfbox页面列表
                List<PDPage> pageList = this.param.getPageList();
                // 遍历pdfbox页面列表
                for (PDPage pdPage : pageList) {
                    // 设置页面尺寸
                    pdPage.setArtBox(modifyPageSize);
                    // 设置页面尺寸
                    pdPage.setBleedBox(modifyPageSize);
                    // 设置页面尺寸
                    pdPage.setCropBox(modifyPageSize);
                    // 设置页面尺寸
                    pdPage.setMediaBox(modifyPageSize);
                    // 设置页面尺寸
                    pdPage.setTrimBox(modifyPageSize);
                }
            }
        }
        // 获取pdf组件列表
        List<XEasyPdfComponent> componentList = this.param.getComponentList();
        // 如果组件列表数量大于0，则进行组件绘制
        if (!componentList.isEmpty()) {
            // 遍历组件列表
            for (XEasyPdfComponent component : componentList) {
                // 组件绘制
                component.draw(document, this);
            }
        }
        // 绘制水印
        this.drawWatermark(document);
        return this;
    }

    /**
     * 设置最新页面背景颜色
     * @param document pdf文档
     */
    @SneakyThrows
    private void setLastPageBackgroundColor(XEasyPdfDocument document) {
        // 如果背景颜色不为空，且背景颜色不为白色，则进行背景颜色设置
        if (!Color.WHITE.equals(this.param.getBackgroundColor())) {
            // 获取pdfBox最新页面
            PDPage lastPage = this.getLastPage();
            // 如果最新页面不为空，则进行背景颜色设置
            if (lastPage!=null) {
                // 获取页面尺寸
                PDRectangle rectangle = lastPage.getMediaBox();
                // 新建内容流
                PDPageContentStream contentStream = new PDPageContentStream(
                        document.getTarget(),
                        lastPage,
                        PDPageContentStream.AppendMode.PREPEND,
                        true,
                        false
                );
                // 绘制矩形（背景矩形）
                contentStream.addRect(
                        0,
                        0,
                        rectangle.getWidth(),
                        rectangle.getHeight()
                );
                // 设置矩形颜色（背景颜色）
                contentStream.setNonStrokingColor(this.param.getBackgroundColor());
                // 填充矩形（背景矩形）
                contentStream.fill();
                // 内容流重置颜色为黑色
                contentStream.setNonStrokingColor(Color.BLACK);
                // 关闭内容流
                contentStream.close();
            }
        }
    }

    /**
     * 绘制背景图片
     * @param document pdf文档
     */
    private void drawBackgroundImage(XEasyPdfDocument document) {
        // 如果当前pdf页面允许添加页面背景图片，则进行页面背景图片绘制
        if (this.param.isAllowBackgroundImage()) {
            // 如果页面背景图片未初始化，则设置全局页面背景图片
            if (this.param.getBackgroundImage()==null) {
                // 设置全局页面背景图片
                this.param.setBackgroundImage(document.getGlobalBackgroundImage());
            }
            // 如果页面背景图片不为空，则进行绘制
            if (this.param.getBackgroundImage()!=null) {
                // 关闭页面自动重置定位
                this.disablePosition();
                // 绘制页面背景图片
                this.param.getBackgroundImage().setContentMode(XEasyPdfComponent.ContentMode.PREPEND).draw(document, this);
                // 开启页面自动重置定位
                this.enablePosition();
            }
        }
    }

    /**
     * 绘制页眉与页脚
     * @param document pdf文档
     */
    private void drawHeaderAndFooter(XEasyPdfDocument document) {
        // 绘制页眉
        this.drawHeader(document);
        // 绘制页脚
        this.drawFooter(document);
    }

    /**
     * 绘制页眉
     * @param document pdf文档
     */
    private void drawHeader(XEasyPdfDocument document) {
        // 如果当前pdf页面允许添加页眉，则进行页眉绘制
        if (this.param.isAllowHeader()) {
            // 如果页眉未初始化，则设置全局页眉
            if (this.param.getHeader()==null) {
                // 设置全局页眉
                this.param.setHeader(document.getGlobalHeader());
            }
            // 如果页眉不为空，则进行绘制
            if (this.param.getHeader()!=null) {
                // 绘制页眉
                this.param.getHeader().draw(document, this);
            }
        }
    }

    /**
     * 绘制页脚
     * @param document pdf文档
     */
    private void drawFooter(XEasyPdfDocument document) {
        // 如果当前pdf页面允许添加页脚，则进行页脚绘制
        if (this.param.isAllowFooter()) {
            // 如果页脚未初始化，则设置全局页脚
            if (this.param.getFooter()==null) {
                // 设置全局页脚
                this.param.setFooter(document.getGlobalFooter());
            }
            // 如果页脚不为空，则进行绘制
            if (this.param.getFooter()!=null) {
                // 绘制页脚
                this.param.getFooter().draw(document, this);
            }
        }
    }

    /**
     * 绘制水印
     * @param document pdf文档
     */
    private void drawWatermark(XEasyPdfDocument document) {
        // 如果当前pdf页面允许添加页面水印，则进行页面水印绘制
        if (this.param.isAllowWatermark()) {
            // 如果页面水印未初始化，则设置全局页面水印
            if (this.param.getWatermark()==null) {
                // 设置全局页面水印
                this.param.setWatermark(document.getGlobalWatermark());
            }
            // 如果页面水印不为空，则进行绘制
            if (this.param.getWatermark()!=null) {
                // 绘制页面水印
                this.param.getWatermark().draw(document, this);
            }
        }
    }
}
