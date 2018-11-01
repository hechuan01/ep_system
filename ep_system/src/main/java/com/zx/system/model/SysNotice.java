package com.zx.system.model;

import com.zx.common.enums.NoticeOrSurveyState;

import java.io.Serializable;
import java.util.Date;

/*
* 公告表
* */
@SuppressWarnings("serial")
public class SysNotice implements Serializable {


    private Integer id;
    private String title;
    private String content;
    private Date starttime;
    private Date endtime;
    private String createrid;
    private String creatername;
    private Date createtime;
    private Integer state;
    private SysAttachment sysAttachment;
    private String stateName;
    private SysReceipt sysReceipt;
    private String readState;

    //BaseController.loginInfo
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
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

    ///关联附件
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

    ///自定义状态名称
    public String getStateName() {
        return NoticeOrSurveyState.getName(state);
    }

    /*public void setStateName(String stateName) {
        this.stateName = stateName;
    }
*/
    //阅读状态名称
    public String getReadState() {
        return sysReceipt == null ? "未阅读" : "已阅读";
    }

   /* public void setReadState(String readState) {
        this.readState = readState;
    }*/
}
