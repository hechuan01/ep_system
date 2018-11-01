package com.zx.system.model;

import com.zx.common.enums.NoticeOrSurveyState;

import java.io.Serializable;
import java.util.Date;

/*
* 调研表
* */
@SuppressWarnings("serial")
public class SysSurvey implements Serializable {

    private Integer id;
    private String svnumber;
    private String title;
    private String content;
    private Date starttime;
    private Date endtime;
    private String createrid;
    private Date createtime;
    private String creatername;
    private Integer state;
    private String stateName;
    private SysAttachment sysAttachment;
    private SysReceipt sysReceipt;
    private String readState;

    ///自定义状态名称
    public String getStateName() {
        return NoticeOrSurveyState.getName(state);
    }

    /*public void setStateName(String stateName) {
        this.stateName = stateName;
    }*/

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSvnumber() {
        return this.svnumber;
    }

    public void setSvnumber(String svnumber) {
        this.svnumber = svnumber;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStarttime() {
        return this.starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return this.endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getCreaterid() {
        return this.createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public SysAttachment getSysAttachment() {
        return sysAttachment;
    }

    public void setSysAttachment(SysAttachment sysAttachment) {
        this.sysAttachment = sysAttachment;
    }

    public String getCreatername() {
        return creatername;
    }

    public void setCreatername(String creatername) {
        this.creatername = creatername;
    }

    public SysReceipt getSysReceipt() {
        return sysReceipt;
    }

    public void setSysReceipt(SysReceipt sysReceipt) {
        this.sysReceipt = sysReceipt;
    }

    public String getReadState() {
        return sysReceipt == null ? "未反馈" : "已反馈";
    }

/*    public void setReadState(String readState) {
        this.readState = readState;
    }*/
}
