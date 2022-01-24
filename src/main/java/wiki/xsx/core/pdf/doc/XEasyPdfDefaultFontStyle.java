package wiki.xsx.core.pdf.doc;

/**
 * 默认字体样式枚举
 * @author xsx
 * @date 2021/10/3
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
public enum XEasyPdfDefaultFontStyle {
    /**
     * 细体
     */
    LIGHT("/wiki/xsx/core/pdf/ttf/SourceHanSansCN-Light.ttf"),
    /**
     * 正常
     */
    NORMAL("/wiki/xsx/core/pdf/ttf/SourceHanSansCN-Normal.ttf"),
    /**
     * 粗体
     */
    BOLD("/wiki/xsx/core/pdf/ttf/SourceHanSansCN-Bold.ttf");

    /**
     * 字体路径
     */
    private final String path;

    /**
     * 有参构造
     * @param path 字体路径
     */
    XEasyPdfDefaultFontStyle(String path) {
        this.path = path;
    }

    /**
     * 获取字体路径
     * @return 返回字体路径
     */
    public String getPath() {
        return path;
    }
}
