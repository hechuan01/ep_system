package com.zx.xt.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CmtInfo implements Serializable {

	private Integer id;
	private Integer targettype;
	private Integer targetid;
	private String targetname;
	private Integer userid;
	private String username;
	private Date createtime;
	private Integer star;
	private Integer score;
	private String content;
	private Integer state;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setTargettype(Integer targettype){
		this.targettype = targettype;
	}

	public Integer getTargettype(){
		return this.targettype;
	}

	public void setTargetid(Integer targetid){
		this.targetid = targetid;
	}

	public Integer getTargetid(){
		return this.targetid;
	}

	public void setTargetname(String targetname){
		this.targetname = targetname;
	}

	public String getTargetname(){
		return this.targetname;
	}

	public void setUserid(Integer userid){
		this.userid = userid;
	}

	public Integer getUserid(){
		return this.userid;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}

	public Date getCreatetime(){
		return this.createtime;
	}

	public void setStar(Integer star){
		this.star = star;
	}

	public Integer getStar(){
		return this.star;
	}

	public void setScore(Integer score){
		this.score = score;
	}

	public Integer getScore(){
		return this.score;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}


}
