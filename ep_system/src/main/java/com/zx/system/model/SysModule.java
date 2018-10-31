package com.zx.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SysModule implements Serializable {

    private String id;
    private String mname;
    private String mcode;
    private Integer mtype;
    private String micon;
    private String murl;
    private Integer sortnum;
    private Date createtime;
    private Integer state;
    private String remark;
    private String parentCode;
    private String parentName;

    private SysModule parentModule;
    //角色模块列表，获取用户业务权限
    public List<SysModule> modules;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMname() {
        return this.mname;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getMcode() {
        return this.mcode;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public Integer getMtype() {
        return this.mtype;
    }

    public void setMicon(String micon) {
        this.micon = micon;
    }

    public String getMicon() {
        return this.micon;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public String getMurl() {
        return this.murl;
    }

    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    public Integer getSortnum() {
        return this.sortnum;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }


    public String getParentCode() {
        if (parentCode != null && parentCode.trim().length() >= 3) {
            return parentCode;
        }
        if (mcode != null && mcode.trim() != "" && mcode.length() > 3) {
            return mcode.substring(0, mcode.length() - 3);
        }
        return "";
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public SysModule getParentModule() {
        return parentModule;
    }

    public void setParentModule(SysModule parentModule) {
        this.parentModule = parentModule;
    }
}
