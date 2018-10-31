package com.zx.system.model;

import com.zx.common.enums.RoleType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SysRole implements Serializable {

    private String id;
    private String rolename;
    private Integer roletype;
    private String roleTypeName;
    private Integer createrid;
    private Date createtime;
    private Integer state;
    private String remark;
    private List<SysModule> modules;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRoletype(Integer roletype) {
        this.roletype = roletype;
    }

    public Integer getRoletype() {
        return this.roletype;
    }

    public void setCreaterid(Integer createrid) {
        this.createrid = createrid;
    }

    public Integer getCreaterid() {
        return this.createrid;
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


    public List<SysModule> getModules() {
        return modules;
    }

    public void setModules(List<SysModule> modules) {
        this.modules = modules;
    }

    public String getRoleTypeName() {
        return RoleType.getName(this.getRoletype());
    }
}
