package com.zx.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class SysUser implements Serializable {

    private Integer id;
    private String loginid;
    private String fullname;
    private String psd;
    private String deptcode;
    private Integer sex;
    private Date birthday;
    private String mobile;
    private String phone;
    private String email;
    private String messionid;
    private String ip;
    private String photo;
    private Date createtime;
    private Date lasttime;
    private Integer state;
    private String remark;

    private SysDepartment sysDepartment;
    private SysRole sysRole;
    private List<SysModule> modules;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getLoginid() {
        return this.loginid;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getPsd() {
        return this.psd;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getDeptcode() {
        return this.deptcode;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setMessionid(String messionid) {
        this.messionid = messionid;
    }

    public String getMessionid() {
        return this.messionid;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getLasttime() {
        return this.lasttime;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysRole getSysRole() {
        return sysRole;
    }


    public List<SysModule> getModules() {
        return modules;
    }

    public void setModules(List<SysModule> modules) {
        this.modules = modules;
    }

    public SysDepartment getSysDepartment() {
        return sysDepartment;
    }

    public void setSysDepartment(SysDepartment sysDepartment) {
        this.sysDepartment = sysDepartment;
    }


//    public List<SysRole> getSysRoles() {
//        return sysRoles;
//    }
//
//    public void setSysRoles(List<SysRole> sysRoles) {
//        this.sysRoles = sysRoles;
//    }
//
//    private List<SysRole> sysRoles;

}
