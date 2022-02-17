package wiki.xsx.core.pdf.util;

import lombok.SneakyThrows;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片工具
 * @author xsx
 * @date 2020/3/30
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
public class XEasyPdfImageUtil {

    /**
     * 读取文件
     * @param imageFile 图片文件
     * @return 返回图片对象
     */
    @SneakyThrows
    public static BufferedImage read(File imageFile) {
        if (imageFile==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        return ImageIO.read(imageFile);
    }

    /**
     * 读取文件
     * @param imageStream 图片数据流
     * @return 返回图片对象
     */
    @SneakyThrows
    public static BufferedImage read(InputStream imageStream) {
        if (imageStream==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        return ImageIO.read(imageStream);
    }

    /**
     * 写入文件
     * @param image 图片对象
     * @param imageType 图片类型
     * @param outputStream 输出流
     */
    @SneakyThrows
    public static void write(BufferedImage image, XEasyPdfImageType imageType, OutputStream outputStream) {
        ImageIO.write(image, imageType.name(), outputStream);
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
        return name.substring(dot+1).toLowerCase();
    }

    /**
     * 缩放图片
     * @param sourceImage 源图片
     * @param width 缩放宽度
     * @param height 缩放高度
     * @param scaleMode 缩放模式
     * @return 返回缩放后的图片对象
     */
    @SneakyThrows
    public static BufferedImage scale(BufferedImage sourceImage, int width, int height, int scaleMode) {
        if (sourceImage==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        Image temp = sourceImage.getScaledInstance(width, height, scaleMode);
        BufferedImage image = new BufferedImage(
                width,
                height,
                sourceImage.getColorModel().hasAlpha()?BufferedImage.TYPE_4BYTE_ABGR:BufferedImage.TYPE_3BYTE_BGR
        );
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawImage(temp, 0, 0, null);
        graphics.dispose();
        sourceImage.getGraphics().dispose();
        return image;
    }

    /**
     * 转为字节数组
     * @param sourceImage 源图片
     * @param imageType 图片类型
     * @return 返回字节数组
     */
    @SneakyThrows
    public static byte[] toBytes(BufferedImage sourceImage, String imageType) {
        if (sourceImage==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(sourceImage, imageType, outputStream);
            return outputStream.toByteArray();
        }
    }

    /**
     * 转为字节数组
     * @param sourceImage 源图片
     * @param imageType 图片类型
     * @return 返回字节数组
     */
    @SneakyThrows
    public static InputStream toInputStream(BufferedImage sourceImage, String imageType) {
        if (sourceImage==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(sourceImage, imageType, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    /**
     * 旋转图片
     * @param sourceImage 源图片
     * @param radians 旋转弧度
     * @return 返回旋转后的图片对象
     */
    public static BufferedImage rotate(BufferedImage sourceImage, double radians) {
        if (sourceImage==null) {
            throw new IllegalArgumentException("Image can not be null");
        }
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        Rectangle rectangle = getRotateRectangle(imageWidth, imageHeight, radians);
        BufferedImage image = new BufferedImage(
                rectangle.width,
                rectangle.height,
                sourceImage.getColorModel().hasAlpha()?BufferedImage.TYPE_4BYTE_ABGR:BufferedImage.TYPE_3BYTE_BGR
        );
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.translate((rectangle.width-imageWidth)/2D, (rectangle.height-imageHeight)/2D);
        graphics.rotate(Math.toRadians(radians), imageWidth/2D, imageHeight/2D);
        graphics.drawImage(sourceImage, 0, 0, null);
        graphics.dispose();
        sourceImage.getGraphics().dispose();
        return image;
    }

    /**
     * 获取旋转尺寸
     * @param width 宽度
     * @param height 高度
     * @param radians 旋转弧度
     * @return 返回旋转后的尺寸
     */
    private static Rectangle getRotateRectangle(int width, int height, double radians) {
        Rectangle src = new Rectangle(new Dimension(width, height));
        final int angle = 90;
        if (radians>=angle) {
            if(radians/angle%2==1){
                return new Rectangle((int) src.getHeight(), (int) src.getWidth());
            }
            radians = radians%angle;
        }
        double radius = Math.sqrt(src.getHeight()*src.getHeight()+src.getWidth()*src.getWidth())/2;
        double len = 2*Math.sin(Math.toRadians(radians)/2)*radius;
        double radiansAlpha = (Math.PI-Math.toRadians(radians))/2;
        double radiansWidth = Math.atan(src.getHeight()/src.getWidth());
        double radiansHeight = Math.atan(src.getWidth()/src.getHeight());
        int lenWidth = Math.abs((int) (len*Math.cos(Math.PI-radiansAlpha-radiansWidth)));
        int lenHeight = Math.abs((int) (len*Math.cos(Math.PI-radiansAlpha-radiansHeight)));
        return new Rectangle((src.width+lenWidth*2), (src.height+lenHeight*2));
    }
}
