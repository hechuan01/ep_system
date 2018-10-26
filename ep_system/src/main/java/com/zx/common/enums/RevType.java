package com.zx.common.enums;

/**
 * Created by wang on 2017/3/16.
 *  类型(1公告2调研)sys_receipts
 */
public enum RevType {
    公告(1),调研(2);
    private int value;

    RevType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static String getName(int value) {
        for (RevType type : RevType.values()) {
            if (type.getValue() == value) {
                return type.toString();
            }
        }
        return "";
    }
}
