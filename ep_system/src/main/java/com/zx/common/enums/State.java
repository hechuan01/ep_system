package com.zx.common.enums;

/**
 * Created by wang on 2017/3/16.
 */
public enum State {
    删除(-1),正常(0),禁用(1);
    private int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static String getName(int value) {
        for (State type : State.values()) {
            if (type.getValue() == value) {
                return type.toString();
            }
        }
        return "";
    }
}
