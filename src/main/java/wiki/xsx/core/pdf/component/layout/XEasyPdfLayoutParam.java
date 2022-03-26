package wiki.xsx.core.pdf.component.layout;

import lombok.Data;
import wiki.xsx.core.pdf.component.table.XEasyPdfTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xsx
 * @date 2022/3/17
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
class XEasyPdfLayoutParam {

    /**
     * 是否包含表格边框
     */
    private boolean hasTableBorder = false;
    /**
     * pdf表格
     */
    private XEasyPdfTable table = new XEasyPdfTable();
    /**
     * 组件列表
     */
    private List<XEasyPdfLayoutComponent> components = new ArrayList<>(10);
    /**
     * 宽度
     */
    private Float width;
    /**
     * 高度
     */
    private Float height;
    /**
     * 是否完成绘制
     */
    private boolean isDraw = false;
}
