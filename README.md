<p align="center">
	<img src="https://images.gitee.com/uploads/images/2021/0621/111332_1f43ae97_1494292.png" width="45%">
</p>
<p align="center">
	<strong>一个用搭积木的方式构建pdf的框架（基于pdfbox）</strong>
</p>

#### 更新说明
master分支将作为稳定版本发布，develop分支将会不定期进行更新，欢迎大家提供宝贵意见，目前稳定版本 v2.9.2，QQ交流群：15018726

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
> 内置细体、正常、粗体三种开源中文字体（华为鸿蒙字体）
10. 扩展灵活
> 只需实现框架提供的接口，即可完成自定义的组件扩展
11. 组件构建
> 页面所有内容均采用组件化的形式进行构建
 
#### 软件架构

![软件架构](https://oscimg.oschina.net/oscnet/up-4639789b72131924e62650113e6cf80597c.png "x-easypdf整体架构")

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