<p align="center">
	<img src="https://images.gitee.com/uploads/images/2021/0621/111332_1f43ae97_1494292.png" width="45%">
</p>
<p align="center">
	<strong>一个用搭积木的方式构建pdf的框架（基于pdfbox）</strong>
</p>
<p align="center">
    <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" />
    <img src="https://img.shields.io/badge/Current%20Version-v2.7.7-brightgreen" />
    <img src="https://img.shields.io/:License-MulanPSL2-yellowgreen.svg" />
    <a href='https://gitee.com/xsxgit/x-easypdf/stargazers'>
        <img src='https://gitee.com/xsxgit/x-easypdf/badge/star.svg?theme=dark' alt='star' />
    </a>
    <a href="https://qm.qq.com/cgi-bin/qm/qr?k=9zYBYsyoJ-p92S5E8UC8PX--rpfxTpGL&jump_from=webapi">
        <img src="https://img.shields.io/badge/QQ%E4%BA%A4%E6%B5%81%E7%BE%A4-15018726-blue"/>
    </a>
</p>

#### 更新说明
master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，目前稳定版本 v2.7.7，QQ交流群：15018726

#### 介绍
x-easypdf基于pdfbox二次封装，极大降低使用门槛，以组件化的形式进行pdf的构建。简单易用，仅需一行代码，便可完成pdf的相关操作

#### API文档
https://apidoc.gitee.com/xsxgit/x-easypdf

#### 特性
1. 体积轻量
> 仅依赖pdfbox，无其他依赖（数字签名需单独添加bouncycastle依赖，条形码需单独添加zxing依赖）
2. 简单智能
> 文本超出单行显示时，即可自动换行；内容超出单页显示时，即可自动分页。只需记住一个类，学习成本低
3. 表单填充
> 可轻松实现pdf表单的填充
4. 图片转换
> 可轻松实现pdf转为图片
5. 提取内容
> 可提取pdf页面中的文本及图片
6. 文本替换
> 可将pdf中的文本内容进行替换，支持正则
7. 拆分合并
> 可将pdf按照单页或多页进行拆分与合并
8. 数字签名
> 可对已有pdf或新创建的pdf进行数字签名
9. 内置字体
> 内置细体、正常、粗体三种开源中文字体（思源字体）
10. 扩展灵活
> 只需实现框架提供的接口，即可完成自定义的组件扩展
11. 组件构建
> 页面所有内容均采用组件化的形式进行构建

#### 软件架构

![软件架构](https://oscimg.oschina.net/oscnet/up-0ac3aeedb9264d9bac0d54adc95ea1e597d.png "x-easypdf整体架构")

1. document(文档)：PDF文档
2. page(页面)：若干个页面组成PDF文档
3. watermark(水印)：每个页面可设置页面级别的独立水印，也可设置文档级别的全局水印，优先级为：页面级别>文档级别
4. header(页眉)：每个页面可设置页面级别的独立页眉，也可设置文档级别的全局页眉，优先级为：页面级别>文档级别
5. footer(页脚)：每个页面可设置页面级别的独立页脚，也可设置文档级别的全局页脚，优先级为：页面级别>文档级别
6. component(组件)：核心，每个页面由若干个组件构成
> text(文本组件)：已提供，文本写入组件

> line(线条组件)：已提供，线条写入组件

> image(图片组件)：已提供，图片写入组件

> table(表格组件)：已提供，表格写入组件，cell(单元格)->row(行)->table(表格)

> rect(矩形组件)：已提供，矩形写入组件

> circle(圆形组件)：已提供，圆形写入组件

> barcode(条形码组件)：已提供，条形码写入组件(包含一维码/二维码)
> 
> layout(布局组件)：已提供，包含水平布局与垂直布局，支持相互嵌套

> 后续将添加更多其他方便实用的组件。。。

#### maven坐标
```maven
<dependency>
    <groupId>wiki.xsx</groupId>
    <artifactId>x-easypdf</artifactId>
    <version>2.7.7</version>
</dependency>
```

#### 安装教程
```cmd
mvn clean install
```

#### 使用说明
1. 简单小示例
> 代码如下：
```java
import wiki.xsx.core.pdf.component.XEasyPdfComponent;
import wiki.xsx.core.pdf.component.image.XEasyPdfImageType;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class XpdfTest {
    
    @SneakyThrows
    public static void main(String[] args) {
        // 定义图片路径
        final String imagePath = "E:\\pdf\\test\\doc\\test.png";
        // 定义保存路径
        final String outputPath = "E:\\pdf\\test\\doc\\my.pdf";
        // 定义页眉与页脚字体颜色
        Color headerAndFooterColor = new Color(10, 195, 255);
        // 定义分割线颜色
        Color lineColor = new Color(210, 0, 210);
        // 获取背景图片
        try (InputStream backgroundImageInputStream = new URL("https://i0.hdslb.com/bfs/article/1e60a08c2dfdcfcd5bab0cae4538a1a7fe8fc0f3.png").openStream()) {
            // 设置背景图片
            XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                    // 构建图片并垂直居中
                    XEasyPdfHandler.Image.build(backgroundImageInputStream, XEasyPdfImageType.PNG).setVerticalStyle(XEasyPdfPositionStyle.CENTER)
                    // 设置全局页眉
            ).setGlobalHeader(
                    // 构建页眉
                    XEasyPdfHandler.Header.build(
                            // 构建页眉图片，并居中显示
                            XEasyPdfHandler.Image.build(new File(imagePath)).setHeight(50F).enableCenterStyle(),
                            // 构建页眉文本，并居中显示
                            XEasyPdfHandler.Text.build("这是粗体页眉")
                                    // 设置水平垂直居中
                                    .enableCenterStyle()
                                    // 设置字体大小
                                    .setFontSize(20F)
                                    // 设置字体颜色
                                    .setFontColor(headerAndFooterColor)
                                    // 使用粗体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.BOLD)
                    )
                    // 设置全局页脚
            ).setGlobalFooter(
                    // 构建页脚
                    XEasyPdfHandler.Footer.build(
                            // 构建页眉图片，并居中显示
                            XEasyPdfHandler.Image.build(new File(imagePath)).setHeight(50F).setVerticalStyle(XEasyPdfPositionStyle.BOTTOM),
                            // 构建页脚文本（手动分行），并居中显示
                            XEasyPdfHandler.Text.build(Arrays.asList("这是粗体页脚第一行", "这是粗体页脚第二行"))
                                    // 设置水平居中
                                    .setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                                    // 设置垂直居下
                                    .setVerticalStyle(XEasyPdfPositionStyle.BOTTOM)
                                    // 设置字体大小
                                    .setFontSize(20F)
                                    // 设置字体颜色
                                    .setFontColor(headerAndFooterColor)
                                    // 使用粗体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.BOLD)
                    )
                    // 添加页面
            ).addPage(
                    // 构建页面
                    XEasyPdfHandler.Page.build(
                            // 构建文本
                            XEasyPdfHandler.Text.build(
                                    "x-easypdf简介（细体）"
                                    // 设置水平居中
                            ).setHorizontalStyle(XEasyPdfPositionStyle.CENTER)
                                    // 设置字体大小
                                    .setFontSize(16F)
                                    // 使用细体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.LIGHT)
                                    // 开启删除线
                                    .enableDeleteLine()
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "x-easypdf是一个基于PDFBOX的开源框架，"
                            ).setFontSize(16F).setFontColor(new Color(51, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "专注于PDF文件导出功能，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(102, 0, 153))
                                    // 开启下划线并设置为红色
                                    .enableUnderline().setUnderlineColor(Color.RED)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "以组件形式进行拼接，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(153, 0, 153))
                                    // 开启高亮并设置为橘色
                                    .enableHighlight().setHighlightColor(Color.ORANGE)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "简单、方便，功能丰富，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(204, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "欢迎大家试用并提出宝贵意见。"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(255, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "-- by xsx"
                            ).setHorizontalStyle(XEasyPdfPositionStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "2021.10.10"
                            ).setHorizontalStyle(XEasyPdfPositionStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                            // 构建实线分割线
                            ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建虚线分割线
                            ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建实线分割线
                            ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建表格
                            ,XEasyPdfHandler.Table.build(
                                    // 构建行
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                    // 添加文本
                                                    XEasyPdfHandler.Text.build("第一行第一列")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                    XEasyPdfHandler.Text.build("第一行第二列")
                                            ),
                                            // 构建单元格并设置字体大小为15，边框颜色为绿色
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                    XEasyPdfHandler.Text.build("第一行第三列")
                                            ).setFontSize(15F).setBorderColor(Color.GREEN),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                    XEasyPdfHandler.Text.build("第一行第四列")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 50F).addContent(
                                                    XEasyPdfHandler.Text.build("第一行第五列")
                                            )
                                            // 设置该行字体大小为20
                                    ).setFontSize(20F),
                                    // 构建行
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格，合并三行
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 300F).addContent(
                                                    XEasyPdfHandler.Text.build(Arrays.asList("第二行", "第一列", "合并三行"))
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(300F).addContent(
                                                    XEasyPdfHandler.Text.build("第二行第二列，合并三列")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 300F).addContent(
                                                    XEasyPdfHandler.Text.build("第二行第三列，合并三行")
                                            )
                                            // 设置行高为100（合并行需要设置平均行高）
                                    ).setHeight(100F),
                                    // 构建行
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格，开启垂直合并
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).enableVerticalMerge(),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第三行第一列")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第三行第二列")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第三行第三列")
                                            )
                                            // 设置行高为100，设置边框颜色为红色，设置字体颜色为蓝色
                                    ).setHeight(100F).setBorderColor(Color.RED).setFontColor(Color.BLUE),
                                    // 构建行（单元格已设置高度，则合并行无需设置行高）
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格，开启垂直合并
                                            XEasyPdfHandler.Table.Row.Cell.build(100F, 100F).enableVerticalMerge(),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(300F, 100F).addContent(
                                                    XEasyPdfHandler.Text.build("第四行第一列，合并三列")
                                            )
                                    ),
                                    // 构建行（根据文本高度自适应行高）
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第五行第一列，自适应高度！")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第五行第二列，自适应高度！！！！！！！！！！")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第五行第三列，自适应高度！！！！！！！！！！！！！！！！！！！！！！")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第五行第四列，自适应高度！！！！！！！！！！")
                                            ),
                                            // 构建单元格
                                            XEasyPdfHandler.Table.Row.Cell.build(100F).addContent(
                                                    XEasyPdfHandler.Text.build("第五行第五列，自适应高度！")
                                            )
                                    ),
                                    // 构建行
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格，并设置边框颜色为橘色
                                            XEasyPdfHandler.Table.Row.Cell.build(500F, 100F).addContent(
                                                    XEasyPdfHandler.Text.build("分页测试1")
                                            ).setBorderColor(Color.ORANGE).setFontColor(Color.PINK)
                                    )
                                    // 设置表头行
                            ).setTileRow(
                                    // 构建行
                                    XEasyPdfHandler.Table.Row.build(
                                            // 构建单元格，并设置边框颜色为黑色，字体大小为30，字体颜色为紫色
                                            XEasyPdfHandler.Table.Row.Cell.build(500F, 100F).addContent(
                                                    XEasyPdfHandler.Text.build("表头")
                                            ).setBorderColor(Color.BLACK).setFontSize(30F).setFontColor(Color.MAGENTA)
                                    )
                                    // 开启表格内容上下左右居中
                            ).enableCenterStyle()
                                    // 设置左边距为50
                                    .setMarginLeft(50F)
                                    // 设置上边距为10
                                    .setMarginTop(10F)
                                    // 设置边框颜色为灰色
                                    .setBorderColor(Color.GRAY)
                                    // 开启自动表头（分页时，自动添加表头行）
                                    .enableAutoTitle()
                    )
                    // 保存、关闭
            ).save(outputPath).close();
        }
    }
}
```

> 效果如下：

![示例效果](https://oscimg.oschina.net/oscnet/up-d335d0d5e10d3763b795f3825cdc2670dfb.png "示例效果")

2. 使用说明

请查看[wiki](https://gitee.com/xsxgit/x-easypdf/wikis/pages)