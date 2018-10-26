package com.zx.system.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysRolemodule implements Serializable {

	private Integer id;
	private Integer roleid;
	private String mcode;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setRoleid(Integer roleid){
		this.roleid = roleid;
	}

	public Integer getRoleid(){
		return this.roleid;
	}

	public void setMcode(String mcode){
		this.mcode = mcode;
	}

	public String getMcode(){
		return this.mcode;
	}


}
