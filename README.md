# x-easypdf

<p align="center">
    <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" />
    <img src="https://img.shields.io/badge/Current%20Version-v1.0.1-brightgreen" />
    <img src="https://img.shields.io/:License-MulanPSL-yellowgreen.svg" />
    <a href='https://gitee.com/xsxgit/x-easypdf/stargazers'>
        <img src='https://gitee.com/xsxgit/x-easypdf/badge/star.svg?theme=dark' alt='star' />
    </a>
</p>

#### 更新说明
第一个版本已基本完善，后面将会以版本进行迭代更新，master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，目前稳定版本1.0.1

#### 介绍
x-easypdf基于pdfbox构建而来，极大降低使用门槛，以组件化的形式进行pdf的构建。简单、易用，3分钟即可上手，人人都是pdf的构建高手

#### API文档
https://apidoc.gitee.com/xsxgit/x-easypdf

#### 特性
1. 快速上手
> 花费3分钟看示例，即可掌握使用方法
2. 自动换行分页
> 文本超出单行显示时，即可自动换行；内容超出单页显示时，即可自动分页
3. 模板填充
> 使用内置方法，即可轻松实现模板填充
4. 组件化
> 页面所有内容均采用组件化形式进行构建，使用不同的组件组合方式，即可构造出理想的文档
5. 扩展灵活
> 只需实现系统提供的接口，即可完成自定义的组件扩展

#### 软件架构

![软件架构](https://images.gitee.com/uploads/images/2020/0331/134211_0652923a_1494292.png "xpdf整体架构.png")

1. document(文档)：PDF文档
2. page(页面)：若干个页面组成PDF文档
3. watermark(水印)：每个页面可设置页面级别的独立水印，也可设置文档级别的全局水印，优先级为：页面级别>文档级别
3. component(组件)：核心，每个页面由若干个组件构成
> text(文本组件)：已提供，文本写入组件

> line(线条组件)：已提供，线条写入组件

> image(图片组件)：已提供，图片写入组件

> table(表格组件)：未提供，将在下一个版本提供，表格写入组件，cell(单元格)->row(行)->table(表格)

> 后续将添加更多其他方便实用的组件。。。

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
        final String outputPath = "C:\\Users\\xsx\\Desktop\\pdf\\text.pdf";
        // 创建pdf
        PdfUtil.create(
                outputPath
                // 构建页面
                ,PdfUtil.Page.build(
                        // 构建文本
                        PdfUtil.Text.build(
                                fontPath,
                                "Hello World(这是一个DEMO)"
                        ).setStyle(XEasyPdfTextStyle.CENTER).setFontSize(20F).setMargin(10F)
                        // 构建文本
                        ,PdfUtil.Text.build(
                                fontPath,
                                "        这里是正文（这是一个基于PDFBOX开源工具，专注于PDF文件导出功能，" +
                                        "以组件形式进行拼接，简单、方便，上手及其容易，" +
                                        "目前有TEXT(文本)、LINE(分割线)等组件，后续还会补充更多组件，满足各种需求）。"
                        ).setStyle(XEasyPdfTextStyle.LEFT).setFontSize(14F).setMargin(10F)
                        // 构建文本
                        ,PdfUtil.Text.build(
                                fontPath,
                                "-- by xsx"
                        ).setStyle(XEasyPdfTextStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建文本
                        ,PdfUtil.Text.build(
                                fontPath,
                                "2020.03.12"
                        ).setStyle(XEasyPdfTextStyle.RIGHT).setFontSize(12F).setMarginTop(10F).setMarginRight(10F)
                        // 构建实线分割线
                        ,PdfUtil.SplitLine.SolidLine.build(fontPath).setMarginTop(10F)
                        // 构建虚线分割线
                        ,PdfUtil.SplitLine.DottedLine.build(fontPath).setLineLength(10F).setMarginTop(10F).setLineWidth(10F)
                        // 构建实线分割线
                        ,PdfUtil.SplitLine.SolidLine.build(fontPath).setMarginTop(10F)
                        // 构建文本
                        ,PdfUtil.Text.build(fontPath, "完结").setStyle(XEasyPdfTextStyle.CENTER)
                )
        );
    }
}
```

> 效果如下：

![示例效果](https://images.gitee.com/uploads/images/2020/0326/113340_f421727f_1494292.png "pdf.png")

2. 使用说明

请查看[wiki](https://gitee.com/xsxgit/x-easypdf/wikis/pages)