<p align="center">
	<img src="https://images.gitee.com/uploads/images/2021/0621/111332_1f43ae97_1494292.png" width="45%">
</p>
<p align="center">
	<strong>一个用搭积木的方式构建pdf的框架（基于pdfbox）</strong>
</p>

#### 更新说明
master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，目前稳定版本 v2.9.1，QQ交流群：15018726

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
    <version>2.9.1</version>
</dependency>
```

#### 安装教程
```cmd
mvn clean install
```

#### 快速体验
```
XEasyPdfHandler.Document.build(XEasyPdfHandler.Page.build(XEasyPdfHandler.Text.build("Hello World"))).save("E:\\pdf\\hello-world.pdf").close();
```

更多教程，请查看[wiki](https://gitee.com/xsxgit/x-easypdf/wikis/pages)