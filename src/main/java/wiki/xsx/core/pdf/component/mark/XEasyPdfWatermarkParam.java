package wiki.xsx.core.pdf.component.mark;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

/**
 * pdf页面水印参数
 * @author xsx
 * @date 2020/3/25
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
class XEasyPdfWatermarkParam {
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
     * 透明度（值越小越透明，0.0-1.0）
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
     * 文本间隔
     */
    private Float wordSpace;
    /**
     * 文本行间距
     */
    private Float leading;


    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     * @return 返回pdfBox扩展图形对象
     */
    PDExtendedGraphicsState init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果字体未初始化，则进行初始化
        if (this.font==null) {
            // 初始化字体
            this.font = XEasyPdfFontUtil.loadFont(document, page, this.fontPath);
        }
        // 如果文本间隔未初始化，则进行初始化
        if (this.wordSpace==null) {
            // 初始化文本间隔为6倍字体大小
            this.wordSpace = this.fontSize * 6;
        }
        // 如果文本行间距未初始化，则进行初始化
        if (this.leading==null) {
            // 初始化文本行间距为2倍字体大小
            this.leading = this.fontSize * 2;
        }
        // 初始化水印文本
        this.initText(
                this.text==null?"XEasyPdf":this.text,
                page.getLastPage().getMediaBox().getWidth()
        );
        // 初始化pdfBox扩展图形对象
        PDExtendedGraphicsState state = new PDExtendedGraphicsState();
        // 设置文本透明度
        state.setNonStrokingAlphaConstant(this.alpha);
        // 设置透明度标记
        state.setAlphaSourceFlag(true);
        return state;
    }

    /**
     * 初始化水印文本
     * @param text 水印文本
     * @param pageWidth 页面宽度
     */
    private void initText(String text, float pageWidth) {
        // 获取文本循环数量
        int wordCount = (int) (pageWidth / (this.fontSize)) + 1;
        // 获取空格循环数量
        int blankCount = (int) (this.wordSpace / this.fontSize);
        // 创建字符串构建器
        StringBuilder builder = new StringBuilder();
        // 循环构建文本
        for (int i = 0; i < wordCount; i++) {
            // 添加文本
            builder.append(text);
            // 循环添加空格
            for (int j = 0; j < blankCount; j++) {
                // 添加空格
                builder.append(" ");
            }
        }
        // 初始化水印文本
        this.text = builder.toString();
    }
}
