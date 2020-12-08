package wiki.xsx.core.pdf.component.footer;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.io.IOException;

/**
 * pdf页脚组件
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
public class XEasyPdfDefaultFooter implements XEasyPdfFooter {

    /**
     * 页脚参数
     */
    private final XEasyPdfFooterParam param = new XEasyPdfFooterParam();

    /**
     * 有参构造
     * @param image 待绘制图片
     */
    public XEasyPdfDefaultFooter(XEasyPdfImage image) {
        this.param.setImage(image);
    }

    /**
     * 有参构造
     * @param text 待写入文本
     */
    public XEasyPdfDefaultFooter(XEasyPdfText text) {
        this.param.setText(text);
    }

    /**
     * 有参构造
     * @param image 待绘制图片
     * @param text 待写入文本
     */
    public XEasyPdfDefaultFooter(XEasyPdfImage image, XEasyPdfText text) {
        this.param.setImage(image).setText(text);
    }

    /**
     * 设置页脚高度
     * @return 返回页脚组件
     */
    public XEasyPdfFooter setHeight(float height) {
        this.param.setHeight(height);
        return this;
    }

    /**
     * 设置边距（左右下）
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMargin(float margin) {
        this.param.setMarginLeft(margin).setMarginRight(margin).setMarginBottom(margin);
        return this;
    }

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMarginLeft(float margin) {
        this.param.setMarginLeft(margin);
        return this;
    }

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMarginRight(float margin) {
        this.param.setMarginRight(margin);
        return this;
    }

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回页脚组件
     */
    public XEasyPdfDefaultFooter setMarginBottom(float margin) {
        this.param.setMarginBottom(margin);
        return this;
    }

    @Override
    public void draw(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 初始化参数
        this.param.init(document, page);
        // 关闭页面自动重置定位
        page.disablePosition();
        // 如果图片不为空，则绘制图片
        if (this.param.getImage()!=null) {
            // 绘制图片
            this.param.getImage()
                    .setMarginLeft(this.param.getMarginLeft())
                    .setMarginRight(this.param.getMarginRight())
                    .setMarginBottom(this.param.getMarginBottom())
                    .setPosition(this.param.getBeginX(), 0)
                    .setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                    .draw(document, page);
        }
        // 如果文本不为空，则写入文本
        if (this.param.getText()!=null) {
            // 写入文本
            this.param.getText()
                    .setMarginLeft(this.param.getMarginLeft())
                    .setMarginRight(this.param.getMarginRight())
                    .setMarginBottom(this.param.getMarginBottom())
                    .setPosition(this.param.getBeginX(), this.param.getBeginY())
                    .setStyle(XEasyPdfTextStyle.CENTER)
                    .setCheckPage(false)
                    .draw(document, page);
        }
        // 开启页面自动重置定位
        page.enablePosition();
    }

    @Override
    public float getHeight() {
        return this.param.getHeight()==null?0F:this.param.getHeight();
    }
}
