package com.zx.system.model;

import com.zx.common.enums.TypeEnums;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SysNotification implements Serializable {

    private Integer id;
    private String title;
    private String content;
    private Integer type;
    private String typeName;
    private Integer sourceid;
    private Integer targettype;
    private String targettypeName;
    private String targetkey;
    private Integer createrid;
    private Date createtime;
    private Integer state;

    private String creater;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getSourceid() {
        return this.sourceid;
    }

    public void setTargettype(Integer targettype) {
        this.targettype = targettype;
    }

    public Integer getTargettype() {
        return this.targettype;
    }

    public void setTargetkey(String targetkey) {
        this.targetkey = targetkey;
    }

    public String getTargetkey() {
        return this.targetkey;
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


    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getTypeName() {
        return TypeEnums.NotificationType.getName(type);
    }

    public void setTypeName(String typeName) {
        this.typeName = TypeEnums.NotificationType.getName(type);
    }

    public String getTargettypeName() {
        return TypeEnums.TargetType.getName(targettype);
    }

    public void setTargettypeName(String targettypeName) {
        this.targettypeName = TypeEnums.NotificationType.getName(targettype);
    }
}
