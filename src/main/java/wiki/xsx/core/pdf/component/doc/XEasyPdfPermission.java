package wiki.xsx.core.pdf.component.doc;

import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.PublicKeyProtectionPolicy;
import org.apache.pdfbox.pdmodel.encryption.PublicKeyRecipient;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * pdf权限
 * @author xsx
 * @date 2020/4/1
 * @since 1.8
 * <p>
 * Copyright (c) 2020 xsx All Rights Reserved.
 * x-easypdf is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 * </p>
 */
public class XEasyPdfPermission {
    /**
     * pdf文档
     */
    private final XEasyPdfDocument document;
    /**
     * pdf访问权限
     */
    private final AccessPermission accessPermission = new AccessPermission();
    /**
     * 公钥类型
     */
    private static final String PUBLIC_KEY_TYPE = "X.509";

    /**
     * 有参构造
     * @param document pdf文档
     */
    public XEasyPdfPermission(XEasyPdfDocument document) {
        this.document = document;
    }

    /**
     * 设置能否打印
     * @param allowPrinting 能否打印
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanPrint(boolean allowPrinting) {
        this.accessPermission.setCanPrint(allowPrinting);
        return this;
    }

    /**
     * 设置能否编辑
     * @param allowModifications 能否编辑
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanModify(boolean allowModifications) {
        this.accessPermission.setCanModify(allowModifications);
        return this;
    }

    /**
     * 设置能否修改批注
     * @param allowAnnotationModification 能否修改批注
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanModifyAnnotations(boolean allowAnnotationModification) {
        this.accessPermission.setCanModifyAnnotations(allowAnnotationModification);
        return this;
    }

    /**
     * 设置能否填充表单
     * @param allowFillingInForm 能否填充表单
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanFillInForm(boolean allowFillingInForm) {
        this.accessPermission.setCanFillInForm(allowFillingInForm);
        return this;
    }

    /**
     * 设置能否提取内容
     * @param allowExtraction 能否提取内容
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanExtractContent(boolean allowExtraction) {
        this.accessPermission.setCanExtractContent(allowExtraction);
        return this;
    }

    /**
     * 设置能否提取内容（在权限范围内）
     * @param allowExtraction 能否提取内容
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanExtractForAccessibility(boolean allowExtraction) {
        this.accessPermission.setCanExtractForAccessibility(allowExtraction);
        return this;
    }

    /**
     * 设置能否组装文档（新增/调整/删除页面）
     * @param allowAssembly 能否组装文档
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanAssembleDocument(boolean allowAssembly) {
        this.accessPermission.setCanAssembleDocument(allowAssembly);
        return this;
    }

    /**
     * 设置能否打印降级
     * @param canPrintDegraded 能否打印降级
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setCanPrintDegraded(boolean canPrintDegraded) {
        this.accessPermission.setCanPrintDegraded(canPrintDegraded);
        return this;
    }

    /**
     * 设置为只读（只读时，其他设置将失效）
     * @return 返回pdf权限
     */
    public XEasyPdfPermission setReadOnly() {
        this.accessPermission.setReadOnly();
        return this;
    }

    /**
     * 完成权限设置（标准策略，空密码）
     * @return 返回pdf文档
     */
    public XEasyPdfDocument finish() {
        return this.finishWithStandardPolicy(false, XEasyPdfPermission.PWLength.LENGTH_40, "", "");
    }

    /**
     * 完成权限设置（标准策略）
     * @param preferAES AES加密（密码长度为128位时生效）
     * @param length 密码长度（40位，128位，256位）
     * @param ownerPassword 拥有者密码
     * @param userPassword 用户密码
     * @return 返回pdf文档
     */
    public XEasyPdfDocument finishWithStandardPolicy(boolean preferAES, PWLength length, String ownerPassword, String userPassword) {
        // 初始化标准保护策略
        StandardProtectionPolicy policy = new StandardProtectionPolicy(
                ownerPassword,
                userPassword,
                this.accessPermission
        );
        // 设置AES加密
        policy.setPreferAES(preferAES);
        // 设置密钥长度
        policy.setEncryptionKeyLength(length.length);
        // 设置pdfBox文档保护策略
        this.document.setProtectionPolicy(policy);
        return this.document;
    }

    /**
     * 完成权限设置（公钥策略，仅支持"X.509"）
     * @param certificateInputStream 公钥证书数据流
     * @return 返回pdf文档
     */
    public XEasyPdfDocument finishWithPublicKeyPolicy(InputStream certificateInputStream) throws CertificateException {
        // 初始化公钥接收者
        PublicKeyRecipient recipient = new PublicKeyRecipient();
        // 设置访问权限
        recipient.setPermission(this.accessPermission);
        // 设置X509证书
        recipient.setX509(
                (X509Certificate) CertificateFactory.getInstance(PUBLIC_KEY_TYPE)
                        .generateCertificate(certificateInputStream)
        );
        // 初始化公钥保护策略
        PublicKeyProtectionPolicy policy = new PublicKeyProtectionPolicy();
        // 设置接收者
        policy.addRecipient(recipient);
        // 设置pdfBox文档保护策略
        this.document.setProtectionPolicy(policy);
        return this.document;
    }

    /**
     * 密码长度枚举
     */
    public enum  PWLength {
        /**
         * 长度40
         */
        LENGTH_40(40),
        /**
         * 长度128
         */
        LENGTH_128(128),
        /**
         * 长度256
         */
        LENGTH_256(256);
        /**
         * 长度
         */
        private final int length;

        /**
         * 构造方法
         * @param length 长度
         */
        PWLength(int length) {
            this.length = length;
        }
    }
}
