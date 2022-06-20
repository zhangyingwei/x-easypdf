package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.experimental.Accessors;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.doc.XEasyPdfDocument;
import wiki.xsx.core.pdf.doc.XEasyPdfPage;
import wiki.xsx.core.pdf.doc.XEasyPdfPositionStyle;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf表格组件参数
 * @author xsx
 * @date 2020/6/6
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
class XEasyPdfTableParam implements Serializable {

    private static final long serialVersionUID = 8648004404874837308L;

    /**
     * 内容模式
     */
    private XEasyPdfComponent.ContentMode contentMode;
    /**
     * 是否重置上下文
     */
    private Boolean isResetContext;
    /**
     * 单元格边框列表
     */
    private List<XEasyPdfCellBorder> cellBorderList = new ArrayList<>(256);
    /**
     * 自动拆分行
     */
    private Boolean isAutoSplit = Boolean.TRUE;
    /**
     * 是否自动表头
     */
    private Boolean isAutoTitle = Boolean.FALSE;
    /**
     * 表头行
     */
    private XEasyPdfRow titleRow;
    /**
     * 行列表
     */
    private List<XEasyPdfRow> rows = new ArrayList<>(256);
    /**
     * 是否带有边框
     */
    private Boolean hasBorder = Boolean.TRUE;
    /**
     * 背景颜色
     */
    private Color backgroundColor;
    /**
     * 边框颜色
     */
    private Color borderColor = Color.BLACK;
    /**
     * 边框宽度
     */
    private Float borderWidth = 1F;
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
     * 字体大小
     */
    private Float fontSize = 12F;
    /**
     * 字体颜色
     */
    private Color fontColor = Color.BLACK;
    /**
     * 水平样式（居左、居中、居右）
     * 默认居左
     */
    private XEasyPdfPositionStyle horizontalStyle = XEasyPdfPositionStyle.LEFT;
    /**
     * 垂直样式（居上、居中、居下）
     * 默认居上
     */
    private XEasyPdfPositionStyle verticalStyle = XEasyPdfPositionStyle.TOP;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) {
        // 如果内容模式未初始化，则初始化为页面内容模式
        if (this.contentMode==null) {
            // 初始化为页面内容模式
            this.contentMode = page.getContentMode();
        }
        // 如果是否重置上下文未初始化，则初始化为页面是否重置上下文
        if (this.isResetContext==null) {
            // 初始化为页面是否重置上下文
            this.isResetContext = page.isResetContext();
        }
        // 如果字体路径未初始化，则初始化为页面字体路径
        if (this.fontPath==null) {
            // 初始化为页面字体路径
            this.fontPath = page.getFontPath();
        }
        // 如果背景颜色未初始化，则进行初始化
        if (this.backgroundColor==null) {
            // 初始化背景颜色
            this.backgroundColor = page.getBackgroundColor();
        }
    }
}
