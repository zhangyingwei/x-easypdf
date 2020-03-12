package wiki.xsx.core.pdf.component.line;

/**
 * 线型样式枚举
 * @author xsx
 * @date 2020/3/4
 * @since 1.8
 */
public enum LineCapStyle {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 圆角
     */
    ROUND(1),
    /**
     * 方角
     */
    SQUARE(2);
    /**
     * 类型
     */
    private int type;

    /**
     * 有参构造
     * @param type 类型
     */
    LineCapStyle(int type) {
        this.type = type;
    }

    /**
     * 获取类型
     * @return 返回类型
     */
    public int getType() {
        return this.type;
    }
}
