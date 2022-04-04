package wiki.xsx.core.pdf.doc;

import org.junit.Test;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xsx
 * @date 2022/4/4
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
public class XEasyPdfDocumentFormTest {

    private static final String OUTPUT_PATH = "E:\\pdf\\test\\doc\\form\\";

    @Test
    public void testCreate(){
        String filePath = OUTPUT_PATH + "testCreate.pdf";
        XEasyPdfHandler.Document
                .build()
                .addPage(
                        XEasyPdfHandler.Page.build()
                ).formFiller()
                .create()
                .createTextField()
                .setMappingName("test1")
                .setPosition(50F,500F)
                .enablePrint()
                .finish()
                .createTextField()
                .setMappingName("test2")
                .setPosition(200F,500F)
                .finish()
                .finish()
                .finish(filePath);
    }

    @Test
    public void testFill(){
        String sourcePath = OUTPUT_PATH + "testCreate.pdf";
        String filePath = OUTPUT_PATH + "testFill.pdf";
        Map<String, String> map = new HashMap<>(2);
        map.put("test1", "贵阳1");
        map.put("test2", "贵阳2");
        XEasyPdfHandler.Document
                .load(sourcePath)
                .formFiller()
                .fill(map)
                .finish(filePath);
    }
}
