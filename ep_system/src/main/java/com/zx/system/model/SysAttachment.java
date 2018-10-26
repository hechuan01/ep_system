package com.zx.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wang on 2017/3/17.
 * 系统附件表
 */
public class SysAttachment implements Serializable {

    private Integer id;

    /*附件类型(1军火信息附件2申请表附件3系统公告4系统调研)*/
    private Integer attType;

    /*附件名称*/
    private String attname;
    /*附件地址*/
    private String attpath;

    /*附件对象主键*/
    private Integer targetid;

    /*附件描述*/
    private String remark;
    /*创建人*/
    private Integer createrid;
    /*创建时间*/
    private Date createtime;
    /*状态(-1删除0默认)*/
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttType() {
        return attType;
    }

    public void setAttType(Integer attType) {
        this.attType = attType;
    }

    public String getAttname() {
        return attname;
    }

    public void setAttname(String attname) {
        this.attname = attname;
    }

    public String getAttpath() {
        return attpath;
    }

    public void setAttpath(String attpath) {
        this.attpath = attpath;
    }

    public Integer getTargetid() {
        return targetid;
    }

    public void setTargetid(Integer targetid) {
        this.targetid = targetid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreaterid() {
        return createrid;
    }

    public void setCreaterid(Integer createrid) {
        this.createrid = createrid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
