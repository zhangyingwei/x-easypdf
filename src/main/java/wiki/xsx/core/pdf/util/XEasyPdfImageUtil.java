package wiki.xsx.core.pdf.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片工具
 * @author xsx
 * @date 2020/3/30
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
public class XEasyPdfImageUtil {

    /**
     * 读取文件
     * @param imageFile 图片文件
     * @return 返回图片对象
     * @throws IOException IO异常
     */
    public static BufferedImage read(File imageFile) throws IOException {
        if (imageFile==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        return ImageIO.read(imageFile);
    }

    /**
     * 读取文件
     * @param imageStream 图片数据流
     * @return 返回图片对象
     * @throws IOException IO异常
     */
    public static BufferedImage read(InputStream imageStream) throws IOException {
        if (imageStream==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        return ImageIO.read(imageStream);
    }

    /**
     * 解析图片类型
     * @param imageFile 图片文件
     * @return 返回图片类型
     */
    public static String parseType(File imageFile) {
        if (imageFile==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        String name = imageFile.getName();
        int dot = imageFile.getName().lastIndexOf('.');
        if (dot==-1) {
            throw new IllegalArgumentException("Image type not supported: " + name);
        }
        return name.substring(dot + 1).toLowerCase();
    }

    /**
     * 缩放图片
     * @param sourceImage 源图片
     * @param width 缩放宽度
     * @param height 缩放高度
     * @param scaleMode 缩放模式
     * @return 返回缩放后的图片对象
     */
    public static BufferedImage scale(BufferedImage sourceImage, int width, int height, int scaleMode) {
        if (sourceImage==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        Image temp = sourceImage.getScaledInstance(width, height, scaleMode);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(temp, 0, 0, null);
        graphics.dispose();
        return image;
    }

    /**
     * 转为字节数组
     * @param sourceImage 源图片
     * @param imageType 图片类型
     * @return 返回字节数组
     * @throws IOException IO异常
     */
    public static byte[] toBytes(BufferedImage sourceImage, String imageType) throws IOException {
        if (sourceImage==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(sourceImage, imageType, outputStream);
            return outputStream.toByteArray();
        }
    }
}
