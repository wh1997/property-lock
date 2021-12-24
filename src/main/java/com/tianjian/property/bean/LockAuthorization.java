package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_lock_authorization`")
public class LockAuthorization implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 门id
     */
    @Column(name = "`door_id`")
    private Integer doorId;

    /**
     * 用户id
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 用户状态
     */
    @Column(name = "`user_status`")
    private Integer userStatus;
    /**
     * 添加权限人
     */
    @Column(name = "`add_person`")
    private Integer addPerson;

    /**
     * 入住时间
     */
    @Column(name = "`enter_time`")
    private String enterTime;

    /**
     * 离开时间
     */
    @Column(name = "`leava_time`")
    private String leavaTime;

    /**
     * 授权开门方式
     */
    @Column(name = "`opendoor_status`")
    private String opendoorStatus;

    /**
     * 数据添加时间
     */
    @Column(name = "`add_time`")
    private String addTime;

    /**
     * 数据修改时间
     */
    @Column(name = "`update_time`")
    private String updateTime;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取门id
     *
     * @return door_id - 门id
     */
    public Integer getDoorId() {
        return doorId;
    }

    /**
     * 设置门id
     *
     * @param doorId 门id
     */
    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户状态
     *
     * @return user_status - 用户状态
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态
     *
     * @param userStatus 用户状态
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
    }

    /**
     * 获取入住时间
     *
     * @return enter_time - 入住时间
     */
    public String getEnterTime() {
        return enterTime;
    }

    /**
     * 设置入住时间
     *
     * @param enterTime 入住时间
     */
    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    /**
     * 获取离开时间
     *
     * @return leava_time - 离开时间
     */
    public String getLeavaTime() {
        return leavaTime;
    }

    /**
     * 设置离开时间
     *
     * @param leavaTime 离开时间
     */
    public void setLeavaTime(String leavaTime) {
        this.leavaTime = leavaTime;
    }

    /**
     * 获取授权开门方式
     *
     * @return opendoor_status - 授权开门方式
     */
    public String getOpendoorStatus() {
        return opendoorStatus;
    }

    /**
     * 设置授权开门方式
     *
     * @param opendoorStatus 授权开门方式
     */
    public void setOpendoorStatus(String opendoorStatus) {
        this.opendoorStatus = opendoorStatus == null ? null : opendoorStatus.trim();
    }

    /**
     * 获取数据添加时间
     *
     * @return add_time - 数据添加时间
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * 设置数据添加时间
     *
     * @param addTime 数据添加时间
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取数据修改时间
     *
     * @return update_time - 数据修改时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置数据修改时间
     *
     * @param updateTime 数据修改时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "LockAuthorization{" +
                "id=" + id +
                ", doorId=" + doorId +
                ", userId=" + userId +
                ", userStatus=" + userStatus +
                ", addPerson=" + addPerson +
                ", enterTime='" + enterTime + '\'' +
                ", leavaTime='" + leavaTime + '\'' +
                ", opendoorStatus='" + opendoorStatus + '\'' +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}