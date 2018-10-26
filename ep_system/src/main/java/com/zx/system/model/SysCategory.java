package com.zx.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SysCategory implements Serializable {

	private Integer id;
	private String ctcode;
	private String ctname;
	private String ctnameother;
	private Integer createrid;
	private Integer cttype;
	private Date createtime;
	private Integer state;
	private String remark;
	private List<SysCategory> childcategoryList;

	public List<SysCategory> getCategoryList() {
		return childcategoryList;
	}

	public void setCategoryList(List<SysCategory> categoryList) {
		this.childcategoryList = categoryList;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCtcode(String ctcode){
		this.ctcode = ctcode;
	}

	public String getCtcode(){
		return this.ctcode;
	}

	public void setCtname(String ctname){
		this.ctname = ctname;
	}

	public String getCtname(){
		return this.ctname;
	}

	public void setCtnameother(String ctnameother){
		this.ctnameother = ctnameother;
	}

	public String getCtnameother(){
		return this.ctnameother;
	}

	public void setCreaterid(Integer createrid){
		this.createrid = createrid;
	}

	public Integer getCreaterid(){
		return this.createrid;
	}

	public void setCttype(Integer cttype){
		this.cttype = cttype;
	}

	public Integer getCttype(){
		return this.cttype;
	}

	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}

	public Date getCreatetime(){
		return this.createtime;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}


}
