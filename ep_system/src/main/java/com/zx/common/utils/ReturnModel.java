package com.zx.common.utils;

/**
 * Created by V.E. 2017/3/13.
 * 返回通用的对象
 */
public class ReturnModel {
    /*
    * 状态：true 成功  false 失败
    * */
    private boolean state;
    /*
    * 返回提示信息
    * */
    private String message;

    private Object  model;

    public ReturnModel() {

    }

    public ReturnModel(boolean state, String message) {
        this.setState(state);
        this.setMessage(message);
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
