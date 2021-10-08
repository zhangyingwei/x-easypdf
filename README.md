<p align="center">
	<img src="https://images.gitee.com/uploads/images/2021/0621/111332_1f43ae97_1494292.png" width="45%">
</p>
<p align="center">
	<strong>一个用搭积木的方式构建pdf的框架（基于pdfbox）</strong>
</p>
<p align="center">
    <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" />
    <img src="https://img.shields.io/badge/Current%20Version-v2.4.0-brightgreen" />
    <img src="https://img.shields.io/:License-MulanPSL2-yellowgreen.svg" />
    <a href='https://gitee.com/xsxgit/x-easypdf/stargazers'>
        <img src='https://gitee.com/xsxgit/x-easypdf/badge/star.svg?theme=dark' alt='star' />
    </a>
    <a href="https://qm.qq.com/cgi-bin/qm/qr?k=9zYBYsyoJ-p92S5E8UC8PX--rpfxTpGL&jump_from=webapi">
        <img src="https://img.shields.io/badge/QQ%E4%BA%A4%E6%B5%81%E7%BE%A4-15018726-blue"/>
    </a>
</p>

#### 更新说明
master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，目前稳定版本 v2.4.0

#### 介绍
x-easypdf基于pdfbox构建而来，极大降低使用门槛，以组件化的形式进行pdf的构建。简单易用，仅需一行代码，便可完成pdf的操作

#### API文档
https://apidoc.gitee.com/xsxgit/x-easypdf

#### 特性
1. 轻量级
> 仅添加pdfbox相关依赖，无其他任何依赖
2. 简单易用
> 仅需一行代码，便可完成pdf的操作
2. 自动换行分页
> 文本超出单行显示时，即可自动换行；内容超出单页显示时，即可自动分页
3. 模板填充
> 提供内置方法，可轻松实现模板填充
4. 组件化
> 页面所有内容均采用组件化形式进行构建，使用不同的组件组合方式，即可构造出理想的文档
5. 扩展灵活
> 只需实现系统提供的接口，即可完成自定义的组件扩展

#### 软件架构

![软件架构](https://images.gitee.com/uploads/images/2020/1217/110553_4e3dfc03_1494292.png "xpdf整体架构.png")

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

> 后续将添加更多其他方便实用的组件。。。

#### maven坐标
```maven
<dependency>
    <groupId>wiki.xsx</groupId>
    <artifactId>x-easypdf</artifactId>
    <version>2.4.0</version>
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
public class XpdfTest {
    public static void main(String[] args) throws IOException {
        final String fontPath = "C:\\Windows\\Fonts\\STSONG.TTF";
        final String backgroundImagePath = "D:\\temp\\background.jpg";
        final String outputPath = "C:\\Users\\xsx\\Desktop\\pdf\\text.pdf";

        // 设置背景图片
        XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                // 构建图片
                XEasyPdfHandler.Image.build(new File(backgroundImagePath)).setHeight(800F)
                // 设置全局页眉
        ).setGlobalHeader(
                // 构建页眉
                XEasyPdfHandler.Header.build(
                        // 构建页眉文本，并居中显示
                        XEasyPdfHandler.Text.build("这是页眉").setStyle(XEasyPdfTextStyle.CENTER)
                )
                // 设置全局页脚
        ).setGlobalFooter(
                // 构建页脚
                XEasyPdfHandler.Footer.build(
                        // 构建页脚文本
                        XEasyPdfHandler.Text.build("这是页脚")
                )
                // 添加页面
        ).addPage(
                // 构建页面
                XEasyPdfHandler.Page.build(
                        // 构建文本
                        XEasyPdfHandler.Text.build(
                                "Hello World(这是一个DEMO)"
                        ).setStyle(XEasyPdfTextStyle.CENTER).setFontSize(20F).setMargin(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "        这里是正文（这是一个基于PDFBOX开源工具，专注于PDF文件导出功能，" +
                                        "以组件形式进行拼接，简单、方便，上手及其容易，" +
                                        "目前有TEXT(文本)、LINE(分割线)等组件，后续还会补充更多组件，满足各种需求）。"
                        ).setStyle(XEasyPdfTextStyle.LEFT).setFontSize(14F).setMargin(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "-- by xsx"
                        ).setStyle(XEasyPdfTextStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build(
                                "2020.03.12"
                        ).setStyle(XEasyPdfTextStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建实线分割线
                        ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F)
                        // 构建虚线分割线
                        ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F)
                        // 构建实线分割线
                        ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F)
                        // 构建文本
                        ,XEasyPdfHandler.Text.build( "完结").setStyle(XEasyPdfTextStyle.CENTER)
                )
                // 设置字体路径，并保存
        ).setFontPath(fontPath).save(outputPath).close();
    }
}
```

> 效果如下：

![示例效果](https://images.gitee.com/uploads/images/2020/1217/113846_115f5e8f_1494292.png "示例效果")

2. 使用说明

请查看[wiki](https://gitee.com/xsxgit/x-easypdf/wikis/pages)