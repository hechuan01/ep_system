package com.zx.system.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 描述：${sys_query系统订阅}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-20 15:14
 **/
public class SysQuery implements Serializable {
    private Integer id;

    private Byte querytype;

    private String keywords;

    private String createid;

    private Date createtime;

    private Date finishtime;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getQuerytype() {
        return querytype;
    }

    public void setQuerytype(Byte querytype) {
        this.querytype = querytype;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getCreateid() {
        return createid;
    }

    public void setCreateid(String createid) {
        this.createid = createid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}