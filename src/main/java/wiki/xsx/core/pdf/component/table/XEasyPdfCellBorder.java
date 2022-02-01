package wiki.xsx.core.pdf.component.table;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.awt.*;

/**
 * pdf单元格边框
 * @author xsx
 * @date 2022/2/1
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
 * this.beginX-easypdf is licensed under the Mulan PSL v2.
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
final class XEasyPdfCellBorder {

    /**
     * pdfbox文档
     */
    private PDDocument document;
    /**
     * pdfbox页面
     */
    private PDPage page;
    /**
     * 内容模式
     */
    private PDPageContentStream.AppendMode contentMode;
    /**
     * 宽度
     */
    private Float width;
    /**
     * 高度
     */
    private Float height;
    /**
     * X轴起始坐标
     */
    private Float beginX;
    /**
     * Y轴起始坐标
     */
    private Float beginY;
    /**
     * 边框颜色
     */
    private Color borderColor;
    /**
     * 边框宽度
     */
    private Float borderWidth;

    /**
     * 绘制边框
     */
    @SneakyThrows
    void drawBorder() {
        // 新建内容流
        PDPageContentStream contentStream = new PDPageContentStream(this.document, this.page, this.contentMode, true, false);
        // 设置颜色
        contentStream.setStrokingColor(this.borderColor);
        // 设置线宽
        contentStream.setLineWidth(this.borderWidth);
        // 移动到x,y坐标点
        contentStream.moveTo(this.beginX, this.beginY);
        // 重置X轴坐标为X轴坐标+宽度
        this.beginX = this.beginX + this.width;
        // 连线
        contentStream.lineTo(this.beginX, this.beginY);
        // 重置Y轴坐标为Y轴坐标-高度
        this.beginY = this.beginY - this.height;
        // 连线
        contentStream.lineTo(this.beginX, this.beginY);
        // 重置X轴坐标为X轴坐标-宽度
        this.beginX = this.beginX - this.width;
        // 连线
        contentStream.lineTo(this.beginX, this.beginY);
        // 重置Y轴坐标为Y轴坐标+高度
        this.beginY = this.beginY + this.height;
        // 连线
        contentStream.lineTo(this.beginX, this.beginY);
        // 结束
        contentStream.stroke();
        // 设置颜色
        contentStream.setStrokingColor(Color.BLACK);
        // 关闭内容流
        contentStream.close();
        // 重置pdfbox文档为空
        this.document = null;
        // 重置pdfbox页面为空
        this.page = null;
    }
}
