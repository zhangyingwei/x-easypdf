<p align="center">
	<img src="https://images.gitee.com/uploads/images/2021/0621/111332_1f43ae97_1494292.png" width="45%">
</p>
<p align="center">
	<strong>一个用搭积木的方式构建pdf的框架（基于pdfbox）</strong>
</p>
<p align="center">
    <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" />
    <img src="https://img.shields.io/badge/Current%20Version-v2.6.0-brightgreen" />
    <img src="https://img.shields.io/:License-MulanPSL2-yellowgreen.svg" />
    <a href='https://gitee.com/xsxgit/x-easypdf/stargazers'>
        <img src='https://gitee.com/xsxgit/x-easypdf/badge/star.svg?theme=dark' alt='star' />
    </a>
    <a href="https://qm.qq.com/cgi-bin/qm/qr?k=9zYBYsyoJ-p92S5E8UC8PX--rpfxTpGL&jump_from=webapi">
        <img src="https://img.shields.io/badge/QQ%E4%BA%A4%E6%B5%81%E7%BE%A4-15018726-blue"/>
    </a>
</p>

#### 更新说明
master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，目前稳定版本 v2.6.0

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
    <version>2.6.0</version>
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
        // 定义保存路径
        final String outputPath = "D:\\pdf\\my.pdf";
        // 定义页眉与页脚字体颜色
        Color headerAndFooterColor = new Color(10, 195, 255);
        // 定义分割线颜色
        Color lineColor = new Color(210, 0, 210);
        // 获取背景图片
        try (InputStream backgroundImageInputStream = new URL("https://i0.hdslb.com/bfs/article/1e60a08c2dfdcfcd5bab0cae4538a1a7fe8fc0f3.png").openStream()) {
            // 设置背景图片
            XEasyPdfHandler.Document.build().setGlobalBackgroundImage(
                    // 构建图片并垂直居中
                    XEasyPdfHandler.Image.build(backgroundImageInputStream, XEasyPdfImageType.PNG).enableVerticalCenterStyle()
                    // 设置全局页眉
            ).setGlobalHeader(
                    // 构建页眉
                    XEasyPdfHandler.Header.build(
                            // 构建页眉文本，并居中显示
                            XEasyPdfHandler.Text.build("这是粗体页眉")
                                    // 设置水平居中
                                    .setStyle(XEasyPdfTextStyle.CENTER)
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
                            // 构建页脚文本，并居中显示
                            XEasyPdfHandler.Text.build("这是粗体页脚")
                                    // 设置水平居中
                                    .setStyle(XEasyPdfTextStyle.CENTER)
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
                            ).setStyle(XEasyPdfTextStyle.CENTER)
                                    // 设置字体大小
                                    .setFontSize(30F)
                                    // 设置上边距
                                    .setMarginTop(20F)
                                    // 使用细体字
                                    .setDefaultFontStyle(XEasyPdfDefaultFontStyle.LIGHT)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "x-easypdf是一个基于PDFBOX的开源框架，"
                            ).setFontSize(16F).setFontColor(new Color(51, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "专注于PDF文件导出功能，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(102, 0, 153))
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "以组件形式进行拼接，"
                            ).enableTextAppend().setFontSize(16F).setFontColor(new Color(153, 0, 153))
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
                            ).setStyle(XEasyPdfTextStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                            // 构建文本
                            ,XEasyPdfHandler.Text.build(
                                    "2021.10.10"
                            ).setStyle(XEasyPdfTextStyle.RIGHT).setMarginTop(10F).setMarginRight(10F)
                            // 构建实线分割线
                            ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建虚线分割线
                            ,XEasyPdfHandler.SplitLine.DottedLine.build().setLineLength(10F).setMarginTop(10F).setLineWidth(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                            // 构建实线分割线
                            ,XEasyPdfHandler.SplitLine.SolidLine.build().setMarginTop(10F).setColor(lineColor).setContentMode(XEasyPdfComponent.ContentMode.PREPEND)
                    )
                    // 保存并关闭文档
            ).save(outputPath).close();
        }
    }
}
```

> 效果如下：

![示例效果](https://oscimg.oschina.net/oscnet/up-3f7c61cfb1a16f03d6a50975af1322b8dce.png "示例效果")

2. 使用说明

请查看[wiki](https://gitee.com/xsxgit/x-easypdf/wikis/pages)