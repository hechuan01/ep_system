package com.zx.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色分组 超管角色：0   内置角色：1  普通角色：2
 */
public enum RoleType {
    普通(2), 内置(1), 超管(0);
    private int value;

    RoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static String getName(int value) {
        for (RoleType type : RoleType.values()) {
            if (type.getValue() == value) {
                return type.toString();
            }
        }
        return "";
    }

    /**
     * 枚举名称转为List
     */
    public static List<SelectItem> toList() {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (RoleType type : RoleType.values()) {
            SelectItem item = new SelectItem();
            item.setText(type.toString());
            item.setValue(String.valueOf(type.getValue()));
            list.add(item);
        }
        return list;
    }

}

class SelectItem {
    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    private String Text;
    private String Value;
}