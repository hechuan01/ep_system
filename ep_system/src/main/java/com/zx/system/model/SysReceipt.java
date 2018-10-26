package com.zx.system.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@SuppressWarnings("serial")
public class SysReceipt implements Serializable {

	private BigInteger id;
	private Integer rectype;
	private Integer targetid;
	private Integer createrid;
	private Date createtime;
	private String content;

	public void setId(BigInteger id){
		this.id = id;
	}

	public BigInteger getId(){
		return this.id;
	}

	public void setRectype(Integer rectype){
		this.rectype = rectype;
	}

	public Integer getRectype(){
		return this.rectype;
	}

	public void setTargetid(Integer targetid){
		this.targetid = targetid;
	}

	public Integer getTargetid(){
		return this.targetid;
	}

	public void setCreaterid(Integer createrid){
		this.createrid = createrid;
	}

	public Integer getCreaterid(){
		return this.createrid;
	}

	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}

	public Date getCreatetime(){
		return this.createtime;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}


}
