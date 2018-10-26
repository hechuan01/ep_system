package com.zx.xt.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：${searchLog}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-27 11:40
 **/

public class SearchLog implements Serializable {
    private int id;
    private int createid;
    private Date createtime;
    private String searchcontent;
    private int searchresult;
    private int searchtype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreateid() {
        return createid;
    }

    public void setCreateid(int createid) {
        this.createid = createid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSearchcontent() {
        return searchcontent;
    }

    public void setSearchcontent(String searchcontent) {
        this.searchcontent = searchcontent;
    }

    public int getSearchresult() {
        return searchresult;
    }

    public void setSearchresult(int searchresult) {
        this.searchresult = searchresult;
    }

    public int getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(int searchtype) {
        this.searchtype = searchtype;
    }
}
