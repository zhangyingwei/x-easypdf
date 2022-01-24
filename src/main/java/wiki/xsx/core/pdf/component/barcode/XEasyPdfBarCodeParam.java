package wiki.xsx.core.pdf.component.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * pdf条形码参数
 * @author xsx
 * @date 2021/12/20
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
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
class XEasyPdfBarCodeParam {
    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode = XEasyPdfComponent.ContentMode.APPEND;
    /**
     * 条形码类型
     */
    private XEasyPdfBarCode.CodeType codeType;
    /**
     * 条形码前景颜色
     */
    private Color onColor = Color.BLACK;
    /**
     * 条形码背景颜色
     */
    private Color offColor = Color.WHITE;
    /**
     * 条形码内容
     */
    private String content;
    /**
     * 条形码文字
     */
    private String words;
    /**
     * 条形码文字颜色
     */
    private Color wordsColor = Color.BLACK;
    /**
     * 条形码文字样式
     */
    private XEasyPdfBarCode.WordsStyle wordsStyle = XEasyPdfBarCode.WordsStyle.NORMAL;
    /**
     * 条形码文字大小
     */
    private Integer wordsSize = 12;
    /**
     * 最大宽度
     */
    private Float maxWidth;
    /**
     * 图像宽度
     */
    private Integer width;
    /**
     * 图像高度
     */
    private Integer height;
    /**
     * 页面X轴起始坐标
     */
    private Float beginX = 0F;
    /**
     * 页面Y轴起始坐标
     */
    private Float beginY;
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 右边距
     */
    private Float marginRight;
    /**
     * 上边距
     */
    private Float marginTop = 0F;
    /**
     * 下边距
     */
    private Float marginBottom = 0F;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;
    /**
     * 是否显示文字
     */
    private boolean isShowWords = false;
    /**
     * 编码设置
     */
    private final Map<EncodeHintType, Object> encodeHints = new HashMap<>(8);

    /**
     * 设置编码设置
     * @param type 编码设置类型
     * @param value 编码设置内容
     */
    void setEncodeHints(EncodeHintType type, Object value) {
        this.encodeHints.put(type, value);
    }

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 获取页面尺寸
        PDRectangle rectangle = page.getLastPage().getMediaBox();
        // 初始化编码设置
        this.initEncodeHints();
        // 初始化宽度与高度
        this.initWidthAndHeight();
        // 初始化Y轴坐标
        this.initBeginY(document, page, rectangle);
        // 如果最大宽度未初始化，则进行初始化为页面宽度
        if (this.maxWidth==null) {
            // 初始化最大宽度 = 页面宽度
            this.maxWidth = rectangle.getWidth();
        }
        // 如果右边距不为空，则初始化页面X轴起始坐标 += 最大宽度 - 自定义宽度 - 右边距
        if (this.marginRight!=null) {
            // 页面X轴起始坐标 += 最大宽度 - 自定义宽度 - 右边距
            this.beginX += this.maxWidth - this.width - this.marginRight;
        }
        // 否则初始化X轴起始坐标为页面X轴起始坐标 += 左边距
        else {
            // 页面X轴起始坐标 += 左边距
            this.beginX += this.marginLeft;
        }
        // 如果显示文字，则重置高度
        if (this.isShowWords) {
            // 如果文字为空，则重置为条形码内容
            if (this.words==null||this.words.trim().length()==0) {
                // 重置为条形码内容
                this.words = this.content;
            }
            // 重置高度 -= 文字大小 - 1
            this.height -= this.wordsSize - 1;
        }
    }

    /**
     * 初始化编码设置
     */
    private void initEncodeHints() {
        // 初始化纠错级别
        this.initErrorLevel();
        // 设置编码为utf-8
        this.encodeHints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        // 设置边距
        this.encodeHints.put(EncodeHintType.MARGIN, this.codeType.margin);
    }

    /**
     * 初始化纠错级别
     */
    private void initErrorLevel() {
        // 获取纠错级别
        Object errorLevel = this.encodeHints.get(EncodeHintType.ERROR_CORRECTION);
        // 如果纠错级别不为空，则检查条形码格式化类型，并重置
        if (errorLevel!=null) {
            // 类型转换
            ErrorCorrectionLevel level = (ErrorCorrectionLevel) errorLevel;
            // 如果条形码格式化类型为阿兹特克码或PDF-417码，则重置纠错级别
            if(BarcodeFormat.AZTEC==this.codeType.codeFormat||BarcodeFormat.PDF_417==this.codeType.codeFormat){
                // 重置纠错级别
                this.encodeHints.put(EncodeHintType.ERROR_CORRECTION, level.getBits());
            }
        }
        else {
            // 如果条形码格式化类型为阿兹特克码或PDF-417码，则重置纠错级别
            if(BarcodeFormat.AZTEC==this.codeType.codeFormat||BarcodeFormat.PDF_417==this.codeType.codeFormat){
                // 重置纠错级别
                this.encodeHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M.getBits());
            }
            else {
                // 重置纠错级别
                this.encodeHints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            }
        }
    }

    /**
     * 初始化Y轴坐标
     * @param document pdf文档
     * @param page pdf页面
     * @param rectangle 页面尺寸
     */
    private void initBeginY(XEasyPdfDocument document, XEasyPdfPage page, PDRectangle rectangle) {
        // 如果页面Y轴起始坐标为空，则初始化
        if (this.beginY==null) {
            // 定义页脚高度
            float footerHeight = 0F;
            // 如果允许添加页脚，且页脚不为空则初始化页脚高度
            if (page.isAllowFooter()&&page.getFooter()!=null) {
                // 初始化页脚高度
                footerHeight = page.getParam().getFooter().getHeight(document, page);
            }
            // 如果pdfBox最新页面当前Y轴坐标不为空，则不为新页面
            if (page.getParam().getPageY()!=null) {
                // 定义Y轴坐标
                float initY = page.getParam().getPageY()!=null?page.getParam().getPageY():rectangle.getHeight();
                // 页面Y轴起始坐标 = pdfBox最新页面当前Y轴坐标 - 上边距 - 自定义高度
                this.beginY = initY - this.marginTop - this.height;
                // 如果页面Y轴起始坐标-页脚高度小于等于下边距，则分页
                if (this.beginY - footerHeight <= this.marginBottom) {
                    // 添加新页面
                    page.addNewPage(document, rectangle);
                    // 页面Y轴起始坐标 = 页面高度 - 上边距 - 自定义高度
                    this.beginY = rectangle.getHeight() - this.marginTop - this.height;
                }
            }
            // 如果pdfBox最新页面当前Y轴坐标为空，则为新页面
            else {
                // 页面Y轴起始坐标 = 页面高度 - 上边距 - 自定义高度
                this.beginY = rectangle.getHeight() - this.marginTop - this.height;
            }
        }
    }

    /**
     * 初始化宽度与高度
     */
    private void initWidthAndHeight() {
        // 如果宽度为空，则初始化宽度
        if (this.width==null) {
            // 初始化宽度
            this.width = this.codeType.isQrType()?100:180;
        }
        // 如果高度为空，则初始化高度
        if (this.height==null) {
            // 初始化高度
            this.height = this.codeType.isQrType()?this.width:60;
        }
    }
}
