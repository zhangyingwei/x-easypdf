package wiki.xsx.core.pdf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换工具
 * @author xsx
 * @date 2020/5/24
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
public class ConvertUtil {

    /**
     * 转为基本类型数组
     * @param list 包装类型列表
     * @return 返回基本类型数组
     */
    public static int[] toInt(List<Integer> list) {
        int size = list.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 转为包装类型列表
     * @param array 基本类型数组
     * @return 返回包装类型列表
     */
    public static List<Integer> toInteger(int[] array) {
        int size = array.length;
        List<Integer> list = new ArrayList<>(size);
        for (int index : array) {
            list.add(index);
        }
        return list;
    }
}
