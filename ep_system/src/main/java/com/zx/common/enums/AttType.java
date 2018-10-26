package com.zx.common.enums;

/**
 * Created by wang on 2017/3/18.
 * 附件类型
 */
public enum AttType {
    军火信息附件(1),申请表附件(2),系统公告(3),系统调研(4);
    private int value;

    AttType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static String getName(int value) {
        for (AttType type : AttType.values()) {
            if (type.getValue() == value) {
                return type.toString();
            }
        }
        return "";
    }
}
