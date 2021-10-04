package wiki.xsx.core.pdf.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 文件工具
 * @author xsx
 * @date 2021/7/14
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
public class XEasyPdfFileUtil {

    /**
     * 创建目录
     * @param path 路径
     * @return 返回路径
     */
    public static Path createDirectories(Path path) {
        if (!Files.exists(path)) {
            try {
                Path directoryPath;
                if (!Files.isDirectory(path)) {
                    directoryPath = path.getParent();
                }else {
                    directoryPath = path;
                }
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                throw new IllegalArgumentException("the path is illegal and create directory fail");
            }
        }
        return path;
    }
}
