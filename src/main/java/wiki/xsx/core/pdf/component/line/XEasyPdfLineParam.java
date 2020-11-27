package wiki.xsx.core.pdf.component.line;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;

import java.awt.*;
import java.io.IOException;

/**
 * pdf线条参数
 * @author xsx
 * @date 2020/3/11
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
class XEasyPdfLineParam {
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
    private XEasyPdfLineCapStyle style = XEasyPdfLineCapStyle.NORMAL;
    /**
     * 颜色（默认黑色）
     */
    private Color color = Color.BLACK;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;

    /**
     * 分页检查
     * @param document pdf文档
     * @param page pdf页面
     * @throws IOException IO异常
     */
    void checkPage(XEasyPdfDocument document, XEasyPdfPage page) throws IOException {
        // 定义页脚高度
        float footerHeight = 0F;
        // 如果允许添加页脚，且页脚不为空则初始化页脚高度
        if (page.getParam().isAllowFooter()&&page.getParam().getFooter()!=null) {
            // 初始化页脚高度
            footerHeight = page.getParam().getFooter().getHeight();
        }
        // 如果当前页面Y轴坐标不为空，则进行分页判断
        if (page.getParam().getPageY()!=null) {
            // 定义线宽
            float lineWidth = this.lineWidth / 2;
            // 分页判断，如果（当前Y轴坐标-上边距-线宽-页脚高度）小于下边距，则进行分页
            if (page.getParam().getPageY() - this.marginTop - lineWidth - footerHeight <= this.marginBottom) {
                // 添加新页面
                page.addNewPage(document, page.getLastPage().getMediaBox());
            }
        }
    }
}
