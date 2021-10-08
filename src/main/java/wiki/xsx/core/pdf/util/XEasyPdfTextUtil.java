package wiki.xsx.core.pdf.util;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.util.ArrayList;
import java.util.List;

/**
 * 文本工具
 * @author xsx
 * @date 2020/3/24
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
public class XEasyPdfTextUtil {

    /**
     * 拆分文本（单行）
     * @param text 待输入文本
     * @param lineWidth 行宽度
     * @param font 字体
     * @param fontSize 字体大小
     * @return 返回文本列表
     */
    @SneakyThrows
    public static String splitText(String text, float lineWidth, PDFont font, float fontSize) {
        // 如果待输入文本为空，或文本长度为0，或行宽减字体大小小于0，则直接返回空字符串
        if (text==null||text.trim().length()==0||lineWidth-fontSize<0) {
            // 返回空字符串
            return null;
        }
        // 定义临时文本
        String tempText;
        // 定义当前行真实宽度
        float lineRealWidth;
        // 每行字数（估计）
        int fontCount = Math.max(1, (int) (lineWidth/fontSize));
        // 定义开始索引
        int beginIndex = 0;
        // 遍历文本
        for (int i = fontCount, len = text.length(); i <= len; i++) {
            // 截取临时文本
            tempText = text.substring(beginIndex, i);
            // 计算当前文本真实宽度
            lineRealWidth = fontSize * font.getStringWidth(tempText) / 1000;
            // 如果真实宽度大于行宽度，则减少一个字符
            if (lineRealWidth>lineWidth) {
                // 返回截取字符串
                return text.substring(beginIndex, i - 1);
            }
        }
        return text;
    }

    /**
     * 拆分文本段落（换行）
     * @param text 待输入文本
     * @param lineWidth 行宽度
     * @param font 字体
     * @param fontSize 字体大小
     * @return 返回文本列表
     */
    @SneakyThrows
    public static List<String> splitLines(String text, float lineWidth, PDFont font, float fontSize) {
        // 如果待输入文本为空，或文本长度为0，或行宽减字体大小小于0，则直接返回空列表
        if (text==null||text.trim().length()==0||lineWidth-fontSize<0) {
            // 返回空列表
            return new ArrayList<>(0);
        }
        // 定义文本列表
        List<String> lineList = new ArrayList<>(200);
        // 定义临时文本
        String tempText;
        // 定义当前行真实宽度
        float lineRealWidth;
        // 每行字数（估计）
        int fontCount = Math.max(1, (int) (lineWidth/fontSize));
        // 定义开始索引
        int beginIndex = 0;
        // 遍历文本
        for (int i = fontCount, len = text.length(); i <= len; i++) {
            // 截取临时文本
            tempText = text.substring(beginIndex, i);
            // 计算当前文本真实宽度
            lineRealWidth = fontSize * font.getStringWidth(tempText) / 1000;
            // 如果真实宽度大于行宽度，则减少一个字符
            if (lineRealWidth>lineWidth) {
                // 加入文本列表
                lineList.add(text.substring(beginIndex, i - 1));
                // 重置开始索引
                beginIndex = i - 1;
                // 重置文本索引
                i = i + fontCount - 1;
                // 如果文本索引大于或等于文本长度，则为最后一行，加入文本列表
                if (i>=len) {
                    // 加入文本列表
                    lineList.add(text.substring(beginIndex));
                }
            }
        }
        // 如果开始索引加每行字数小于文本长度，则为最后一行，加入文本列表
        if (beginIndex+fontCount<text.length()||lineList.isEmpty()) {
            // 加入文本列表
            lineList.add(text.substring(beginIndex));
        }
        return lineList;
    }
}
