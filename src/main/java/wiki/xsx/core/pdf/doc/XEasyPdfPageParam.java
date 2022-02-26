package wiki.xsx.core.pdf.doc;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.footer.XEasyPdfFooter;
import wiki.xsx.core.pdf.header.XEasyPdfHeader;
import wiki.xsx.core.pdf.mark.XEasyPdfWatermark;
import wiki.xsx.core.pdf.util.XEasyPdfFontUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf页面参数
 * @author xsx
 * @date 2020/4/7
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
public class XEasyPdfPageParam {
    /**
     * 默认字体样式
     */
    private XEasyPdfDefaultFontStyle defaultFontStyle = XEasyPdfDefaultFontStyle.NORMAL;
    /**
     * 字体路径
     */
    private String fontPath;
    /**
     * 字体
     */
    private PDFont font;
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
     * pdfBox页面尺寸（手动修改）
     */
    private PDRectangle modifyPageSize;
    /**
     * 旋转角度
     */
    private Integer rotation;
    /**
     * 包含的pdfBox页面列表
     */
    private List<PDPage> pageList = new ArrayList<>(64);
    /**
     * 新增的pdfBox页面列表
     */
    private List<PDPage> newPageList = new ArrayList<>(64);
    /**
     * pdf组件列表
     */
    private List<XEasyPdfComponent> componentList = new ArrayList<>(64);
    /**
     * 页面水印
     */
    private XEasyPdfWatermark watermark;
    /**
     * 页眉
     */
    private XEasyPdfHeader header;
    /**
     * 页脚
     */
    private XEasyPdfFooter footer;
    /**
     * 页面背景图片
     */
    private XEasyPdfImage backgroundImage;
    /**
     * 页面背景色
     */
    private Color backgroundColor;
    /**
     * 是否允许添加水印
     */
    private boolean allowWatermark = true;
    /**
     * 是否允许添加背景图片
     */
    private boolean allowBackgroundImage = true;
    /**
     * 是否允许添加背景色
     */
    private boolean allowBackgroundColor = true;
    /**
     * 是否允许添加页眉
     */
    private boolean allowHeader = true;
    /**
     * 是否允许添加页脚
     */
    private boolean allowFooter = true;
    /**
     * 是否允许重置定位
     */
    private boolean allowResetPosition = true;
    /**
     * 是否允许旋转固有页面
     */
    private boolean allowRotateInherentPage = true;

    /**
     * 初始化
     * @param document pdf文档
     * @param page pdf页面
     */
    void init(XEasyPdfDocument document, XEasyPdfPage page) {
        if (this.fontPath==null) {
            this.fontPath = document.getParam().getFontPath();
        }
        this.font = XEasyPdfFontUtil.loadFont(document, page, this.fontPath, true);
        if (this.allowBackgroundColor) {
            if (this.backgroundColor==null) {
                this.backgroundColor = document.getGlobalBackgroundColor();
            }
            if (this.backgroundColor==null) {
                this.backgroundColor = Color.WHITE;
            }
        }
    }

    /**
     * 重置包含的pdfBox页面列表
     * @param newPageList 新pdfBox页面列表
     */
    void resetPageList(List<PDPage> newPageList) {
        this.pageList.clear();
        this.pageList = newPageList;
    }
}
