package wiki.xsx.core.pdf.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 类工具
 *
 * @author xsx
 * @date 2022/5/19
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
public class XEasyPdfClassUtil {

    /**
     * 重置属性
     *
     * @param className 全类名
     */
    @SneakyThrows
    public static void resetField(String className) {
        Class<?> target = Class.forName(className);
        Field[] fields = target.getDeclaredFields();
        Field field1 = fields[0];
        Field field2 = fields[1];
        field1.setAccessible(true);
        field2.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field1, field2.getModifiers() & ~Modifier.FINAL);
        field1.set(null, field2.get(null));
    }
}
