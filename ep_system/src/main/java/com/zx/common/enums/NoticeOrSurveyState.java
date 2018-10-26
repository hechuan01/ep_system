package com.zx.common.enums;

/**
 * Created by wang on 2017/3/15.
 */
public enum NoticeOrSurveyState {
    删除(-1), 已保存(0),公告中(1),已取消(2),已结束(3);
    private int value;

    NoticeOrSurveyState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static String getName(int value) {
        for (NoticeOrSurveyState type : NoticeOrSurveyState.values()) {
            if (type.getValue() == value) {
                return type.toString();
            }
        }
        return "";
    }
}
