package com.zx.system.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SysRolemodule implements Serializable {

	private String id;
	private String roleid;
	private String mcode;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setRoleid(String roleid){
		this.roleid = roleid;
	}

	public String getRoleid(){
		return this.roleid;
	}

	public void setMcode(String mcode){
		this.mcode = mcode;
	}

	public String getMcode(){
		return this.mcode;
	}


}
