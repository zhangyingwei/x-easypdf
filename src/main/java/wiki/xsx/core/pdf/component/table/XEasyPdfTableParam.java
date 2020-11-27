package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.page.XEasyPdfPage;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * pdf表格组件参数
 * @author xsx
 * @date 2020/6/6
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
class XEasyPdfTableParam {

    /**
     * 行列表
     */
    private List<XEasyPdfRow> rows = new ArrayList<>(10);
    /**
     * 左边距
     */
    private Float marginLeft = 0F;
    /**
     * 上边距
     */
    private Float marginTop = 5F;
    /**
     * 下边距
     */
    private Float marginBottom = 0F;
    /**
     * X轴起始坐标
     */
    private Float beginX;
    /**
     * Y轴起始坐标
     */
    private Float beginY;
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
    private float fontSize = 12F;
    /**
     * 文本样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfTextStyle style = XEasyPdfTextStyle.LEFT;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果字体未初始化，则进行初始化
        if (this.font==null) {
            // 初始化字体
            this.font = XEasyPdfFontUtil.loadFont(document, page, this.fontPath);
        }
    }
}
