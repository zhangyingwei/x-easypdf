package wiki.xsx.core.pdf.component.image;

import wiki.xsx.core.pdf.component.XEasyPdfComponent;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

/**
 * pdf图片组件接口
 * @author xsx
 * @date 2020/4/3
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 * </p>
 */
public interface XEasyPdfImage extends XEasyPdfComponent {
    /**
     * 设置图片
     * @param imageFile 待添加图片
     * @return 返回图片组件
     */
    XEasyPdfImage setImage(File imageFile);

    /**
     * 设置图片
     * @param imageStream 待添加图片数据流
     * @param imageType 待添加图片类型
     * @return 返回图片组件
     */
    XEasyPdfImage setImage(InputStream imageStream, String imageType);

    /**
     * 设置图片宽度
     * @param width 图片宽度
     * @return 返回图片组件
     */
    XEasyPdfImage setWidth(int width);

    /**
     * 设置图片高度
     * @param height 图片高度
     * @return 返回图片组件
     */
    XEasyPdfImage setHeight(int height);

    /**
     * 设置图片大小自适应
     * @param enableSelfAdaption 图片大小自适应
     * @return 返回图片组件
     */
    XEasyPdfImage setEnableSelfAdaption(boolean enableSelfAdaption);

    /**
     * 设置边距（上下左右）
     * @param margin 边距
     * @return 返回图片组件
     */
    XEasyPdfImage setMargin(float margin);

    /**
     * 设置左边距
     * @param margin 边距
     * @return 返回图片组件
     */
    XEasyPdfImage setMarginLeft(float margin);

    /**
     * 设置右边距
     * @param margin 边距
     * @return 返回图片组件
     */
    XEasyPdfImage setMarginRight(float margin);

    /**
     * 设置上边距
     * @param margin 边距
     * @return 返回图片组件
     */
    XEasyPdfImage setMarginTop(float margin);

    /**
     * 设置下边距
     * @param margin 边距
     * @return 返回图片组件
     */
    XEasyPdfImage setMarginBottom(float margin);

    /**
     * 设置定位
     * @param x 当前页面X轴坐标
     * @param y 当前页面Y轴坐标
     * @return 返回图片组件
     */
    XEasyPdfImage setPosition(float x, float y);

    /**
     * 设置图片缩放模式（默认、快速、质量）
     * @param scaleMode 缩放模式
     * @return 返回图片组件
     */
    XEasyPdfImage setScaleMode(ScaleMode scaleMode);
        

    /**
     * 设置图片样式（居左、居中、居右）
     * @param style 样式
     * @return 返回图片组件
     */
    XEasyPdfImage setStyle(XEasyPdfImageStyle style);

    /**
     * 图片缩放模式枚举
     */
    enum ScaleMode {
        /**
         * 默认
         */
        DEFAULT(Image.SCALE_DEFAULT),
        /**
         * 快速
         */
        FAST(Image.SCALE_FAST),
        /**
         * 质量
         */
        SMOOTH(Image.SCALE_SMOOTH);

        /**
         * 缩放模式
         */
        private int mode;

        /**
         * 有参构造
         * @param mode 缩放模式
         */
        ScaleMode(int mode) {
            this.mode = mode;
        }

        /**
         * 获取缩放模式
         * @return 返回缩放模式
         */
        public int getMode() {
            return mode;
        }
    }
}
