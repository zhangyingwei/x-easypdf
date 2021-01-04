package wiki.xsx.core.pdf.header;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.line.XEasyPdfLine;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * pdf页眉组件
 * @author xsx
 * @date 2020/6/7
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
public class XEasyPdfDefaultHeader implements XEasyPdfHeader{

    /**
     * 页眉参数
     */
    private final XEasyPdfHeaderParam param = new XEasyPdfHeaderParam();

    /**
     * 有参构造
     * @param image pdf图片
     */
    public XEasyPdfDefaultHeader(XEasyPdfImage image) {
        this.param.setImage(image);
    }

    /**
     * 有参构造
     * @param text pdf文本
     */
    public XEasyPdfDefaultHeader(XEasyPdfText text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param image pdf图片
     * @param text pdf文本
     */
    public XEasyPdfDefaultHeader(XEasyPdfImage image, XEasyPdfText text) {
        this.param.setImage(image).setText(text);
    }

    /**
     * 添加分割线
     * @param splitLine pdf分割线
     * @return 返回页眉组件
     */
    @Override
    public XEasyPdfDefaultHeader addSplitLine(XEasyPdfLine ...splitLine) {
        this.param.getLineList().addAll(Arrays.asList(splitLine));
        return this;
    }

    /**
     * 设置边距（上左右）
     * @param margin 边距
     * @return 返回页眉组件
     */
    @Override
    public XEasyPdfDefaultHeader setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginTop(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回页眉组件
     */
    @Override
    public XEasyPdfDefaultHeader setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回页眉组件
     */
    @Override
    public XEasyPdfDefaultHeader setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回页眉组件
     */
    @Override
    public XEasyPdfDefaultHeader setMarginTop(float margin) {
        this.param.setMarginTop(margin);
        return this;
    }

    /**
     * 获取页脚高度
     * @return 返回页脚高度
     */
    @Override
    public float getHeight() {
        return this.param.getHeight()==null?0F:this.param.getHeight();
    }

    /**
     * 绘制
     * @param document pdf文档
     * @param page     pdf页面
     * @throws IOException IO异常
     */
    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化参数
        this.param.init(document, page);
        // 如果为文本定位，则对文本组件进行页面坐标重置
        if (this.param.isTextPosition()) {
            // 如果图片不为空，则进行图片绘制
            if (this.param.getImage()!=null) {
                // 关闭页面自动重置定位
                page.disablePosition();
                // 绘制图片
                this.param.getImage()
                        .setMarginTop(this.param.getMarginTop())
                        .setPosition(this.param.getBeginX(), this.param.getBeginY())
                        .setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                        .draw(document, page);
                // 开启页面自动重置定位
                page.enablePosition();
            }
            // 写入文本
            this.param.getText()
                    .setMarginTop(this.param.getMarginTop())
                    .setMarginLeft(this.param.getMarginLeft())
                    .setMarginRight(this.param.getMarginRight())
                    .setCheckPage(false)
                    .draw(document, page);
            // 如果不为文本定位，则对图片进行页面坐标重置
        } else {
            // 如果文本不为空，则进行文本绘制
            if (this.param.getText()!=null) {
                // 关闭页面自动重置定位
                page.disablePosition();
                // 写入文本
                this.param.getText()
                        .setMarginTop(this.param.getMarginTop())
                        .setMarginLeft(this.param.getMarginLeft())
                        .setMarginRight(this.param.getMarginRight())
                        .setCheckPage(false)
                        .draw(document, page);
                // 开启页面自动重置定位
                page.enablePosition();
            }
            // 绘制图片
            this.param.getImage()
                    .setMarginTop(this.param.getMarginTop())
                    .setPosition(this.param.getBeginX(), this.param.getBeginY())
                    .setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                    .draw(document, page);
        }
        // 如果分割线列表不为空，则进行分割线绘制
        if (!this.param.getLineList().isEmpty()) {
            // 获取分割线列表
            List<XEasyPdfLine> lineList = this.param.getLineList();
            // 遍历分割线列表
            for (XEasyPdfLine xEasyPdfLine : lineList) {
                // 绘制分割线
                xEasyPdfLine.setMarginLeft(this.param.getMarginLeft()).setMarginRight(this.param.getMarginRight()).draw(document, page);
            }
        }
    }
}
