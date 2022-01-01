package wiki.xsx.core.pdf.doc;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSigProperties;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.visible.PDVisibleSignDesigner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

/**
 * pdf文档签名器参数
 * @author xsx
 * @date 2021/12/15
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
@Data
@Accessors(chain = true)
class XEasyPdfDocumentSignerParam {
    /**
     * pdfbox文档
     */
    private PDDocument document;
    /**
     * pdf文档
     */
    private XEasyPdfDocument pdfDocument;
    /**
     * 密钥库类型
     */
    private XEasyPdfDocumentSigner.KeyStoreType keyStoreType;
    /**
     * 证书文件
     */
    private File certificate;
    /**
     * 证书密码
     */
    private String certificatePassword;
    /**
     * pdfbox签名
     */
    private final PDSignature signature = new PDSignature();
    /**
     * pdfbox签名选项
     */
    private final SignatureOptions signatureOptions = new SignatureOptions();
    /**
     * 签名算法
     */
    private XEasyPdfDocumentSigner.SignAlgorithm signAlgorithm;
    /**
     * 签名过滤器
     */
    private XEasyPdfDocumentSigner.SignFilter.Filter filter = XEasyPdfDocumentSigner.SignFilter.Filter.FILTER_ADOBE_PPKLITE;
    /**
     * 签名子过滤器
     */
    private XEasyPdfDocumentSigner.SignFilter.SubFilter subFilter = XEasyPdfDocumentSigner.SignFilter.SubFilter.SUBFILTER_ADBE_PKCS7_DETACHED;
    /**
     * pdfbox签名接口
     */
    private SignatureInterface customSignature;
    /**
     * pdf访问权限
     */
    private int accessPermissions = 1;
    /**
     * 签名图片
     */
    private BufferedImage image;
    /**
     * 签名图片左边距
     */
    private Float imageMarginLeft = 0F;
    /**
     * 签名图片上边距
     */
    private Float imageMarginTop = 0F;
    /**
     * 签名图片缩放比例
     */
    private Float imageScalePercent = 0F;
    /**
     * 签名内存大小（默认：250K）
     */
    private int preferredSignatureSize = 0x3e800;

    /**
     * 初始化
     * @param pageIndex 页面索引
     */
    @SneakyThrows
    public void init(int pageIndex) {
        if (this.keyStoreType==null) {
            throw new IllegalArgumentException("keyStore type can not be null");
        }
        if (this.certificate==null) {
            throw new FileNotFoundException("certificate can not be null");
        }
        if (this.certificatePassword==null) {
            throw new IllegalArgumentException("certificate password can not be null");
        }
        if (this.signAlgorithm==null) {
            throw new IllegalArgumentException("signAlgorithm can not be null");
        }

        this.signature.setFilter(this.filter.getFilter());
        this.signature.setSubFilter(this.subFilter.getFilter());
        this.signature.setSignDate(Calendar.getInstance());
        PDVisibleSigProperties signatureProperty = new PDVisibleSigProperties();
        if (this.image!=null) {
            PDVisibleSignDesigner designer = new PDVisibleSignDesigner(this.document, this.image, pageIndex+1);
            designer.zoom(this.imageScalePercent);
            designer.xAxis(this.imageMarginLeft);
            designer.yAxis(this.imageMarginTop);
            designer.adjustForRotation();
            signatureProperty.setPdVisibleSignature(designer);
            signatureProperty.signerName(this.signature.getName())
                            .signerLocation(this.signature.getLocation())
                            .signatureReason(this.signature.getReason())
                            .page(pageIndex)
                            .visualSignEnabled(true)
                            .setPdVisibleSignature(designer)
                            .buildSignature();
            this.signatureOptions.setVisualSignature(signatureProperty);
        }
        this.signatureOptions.setPreferredSignatureSize(this.preferredSignatureSize);
    }
}
