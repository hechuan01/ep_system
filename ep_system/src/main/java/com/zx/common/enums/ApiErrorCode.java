package com.zx.common.enums;

/**
 * Created by wang on 2017/3/16.
 */
public enum ApiErrorCode {
    OK("返回正确", 0),
    PARAMETER_ERROR("参数错误，传递了接口定义外的其他字段", 1000004),
    PAGE_PARAMETERS_IS_NULL("参数错误，缺少pagesize或者pageindex等字段为空返回", 3000002),
    PAGE_PARAMETERS_IS_ERROR("参数错误，page参数大小出现问题，或者不为int类型", 1000002);

    private String msg;
    private int code;


    ApiErrorCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public static String getMsg(int code) {
        for (ApiErrorCode apiErrorCode : ApiErrorCode.values()) {
            if (apiErrorCode.getCode() == code) {
                return apiErrorCode.getMsg();
            }
        }
        return "未知错误类型.";
    }
}
