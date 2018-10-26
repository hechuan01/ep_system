package com.zx.system.model;

/**
 * Created by wang on 2017/3/15.
 * 公告调研和角色关联表
 */

public class SysReceive {
    private int id;
    private int revtype;
    private int targetid;
    private int roleid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRevtype() {
        return revtype;
    }

    public void setRevtype(int revtype) {
        this.revtype = revtype;
    }

    public int getTargetid() {
        return targetid;
    }

    public void setTargetid(int targetid) {
        this.targetid = targetid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
}
