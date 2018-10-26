package com.zx.system.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/15
 */
public class UserLogin {


    public UserLogin() {
        this.lastTime = new Date();
        this.loginToken = UUID.randomUUID().toString();
    }

    public UserLogin(Integer id, String loginId, String fullName) {
        this.lastTime = new Date();
        this.loginToken = UUID.randomUUID().toString();
        this.id = id;
        this.loginId = loginId;
        this.fullName = fullName;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 登录临时Token
     */
    private String loginToken;
    /**
     * 登录用户主键
     */
    private Integer id;
    /**
     * 登录用户名
     */
    private String loginId;
    /**
     * 用户真实名称
     */
    private String fullName;
    /**
     * 用户头像
     */
    private String userPhoto;
    /**
     * 最后访问时间
     */
    private Date lastTime;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 登录IP
     */
    private String IP;

    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 登录用户角色
     */
    private SysRole role;
    /**
     * 是否超级管理员用户
     */
    private boolean isAdmin;

    /**
     * 业务权限列表（页面功能）
     */
    private List<SysModule> modules;

    public List<SysModule> getModules() {
        return modules;
    }

    public void setModules(List<SysModule> modules) {
        this.modules = modules;
    }

}


