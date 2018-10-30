package com.zx.xt.model.eicbc;

public class User {

	//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String subscribe;
	
	//用户的标识，对当前公众号唯一 
	private String openid;
	
	//用户所在省份
	private String province;
	
	//用户的性别，值为1时是男性，值为0时是女性
	private String sex;
	
	//用户所在城市
	private String city;
	
	//用户头像
	private String portrait;
	
	//用户的昵称
	private String nickname;
	
	//客户信息号，行内公众号返回，不存在则返回空
	private String cisno;
	
	//统一认证号，行内公众号返回，不存在则返回空
	private String unino;
	
	//统一通行证号，行内公众号返回，不存在则返回空
	private String ICBCUserid;
	
	//手机号，scope为base、basevisit、baseinfo、userallow、companybase返回，不存在则返回空。其他的scope类型，该字段不返回（不是返回为空）
	private String mobileno;
	
	//最近关注时间
	private String subscribe_time;
	
	//备注名，不存在则返回空
	private String remark;
	
	//用户所属群组id，默认群组返回0,黑名单返回1
	private String groupid;

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCisno() {
		return cisno;
	}

	public void setCisno(String cisno) {
		this.cisno = cisno;
	}

	public String getUnino() {
		return unino;
	}

	public void setUnino(String unino) {
		this.unino = unino;
	}

	public String getICBCUserid() {
		return ICBCUserid;
	}

	public void setICBCUserid(String iCBCUserid) {
		ICBCUserid = iCBCUserid;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
}
