package com.zx.system.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SysDepartment implements Serializable {

	private Integer id;
	private String deptname;
	private String deptcode;
	private Integer createrid;
	private Date createtime;
	private Integer state;
	private String remark;

	private String parentDept;
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setDeptname(String deptname){
		this.deptname = deptname;
	}

	public String getDeptname(){
		return this.deptname;
	}

	public void setDeptcode(String deptcode){
		this.deptcode = deptcode;
	}

	public String getDeptcode(){
		return this.deptcode;
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
