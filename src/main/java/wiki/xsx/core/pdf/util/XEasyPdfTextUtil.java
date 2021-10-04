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
     * 拆分文本段落（换行）
     * @param text 待输入文本
     * @param lineWidth 行宽度
     * @param font 字体
     * @param fontSize 字体大小
     * @return 返回文本列表
     */
    @SneakyThrows
    public static List<String> splitLines(String text, float lineWidth, PDFont font, float fontSize) {
        // 如果待输入文本为空，或文本长度为0，则直接返回空列表
        if (text==null||text.trim().length()==0) {
            // 返回空列表
            return new ArrayList<>(0);
        }
        // 定义文本列表
        List<String> lineList = new ArrayList<>(200);
        // 定义临时文本
        String tempText;
        // 计算文本真实宽度
        float realWidth = fontSize * font.getStringWidth(text) / 1000;
        // 计算总行数（估计）
        int count = (int) (lineWidth / realWidth);
        // 计算的总行数与文本长度取最小值
        count = Math.min(count, text.length());
        // 定义开始索引
        int beginIndex = 0;
        // 遍历文本
        for (int i = count, len = text.length(); i <= len; i++) {
            // 截取临时文本
            tempText = text.substring(beginIndex, i);
            // 计算当前文本真实宽度
            realWidth = fontSize * font.getStringWidth(tempText) / 1000;
            // 如果真实宽度大于行宽度，则减少一个字符
            if (realWidth>lineWidth) {
                // 加入文本列表
                lineList.add(text.substring(beginIndex, i - 1));
                // 重置开始索引
                beginIndex = i - 1;
            }
            // 如果当前索引等于文本长度，则直接加入文本列表
            if (i==len) {
                // 加入文本列表
                lineList.add(text.substring(beginIndex, i));
            }
        }
        return lineList;
    }
}
