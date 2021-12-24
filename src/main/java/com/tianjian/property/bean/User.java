package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_user`")
public class User implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "`user_id`")
    private Integer userId;
    /**
     * 备注
     */
    @Column(name = "`phone`")
    private String phone;
    /**
     * 备注
     */
    @Column(name = "`name`")
    private String name;
    /**
     * 所在小区分支id
     */
    @Column(name = "`branch_id`")
    private Integer branchId;
    /**
     * 小程序用户角色  1管理员   2普通用户
     */
    @Column(name = "`role`")
    private Integer role;

    /**
     * 添加时间
     */
    @Column(name = "`add_time`")
    private String addTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private String updateTime;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public User() {
        super();
    }

    public User(Integer id, Integer userId, String phone, String name, Integer branchId, Integer role, String addTime, String updateTime, String remark) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.name = name;
        this.branchId = branchId;
        this.role = role;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", branchId='" + branchId + '\'' +
                ", role=" + role +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}