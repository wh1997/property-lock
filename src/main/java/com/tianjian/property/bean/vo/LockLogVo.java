package com.tianjian.property.bean.vo;

import javax.persistence.Column;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/30
 */
public class LockLogVo {
    private Integer id;

    /**
     * 门id
     */
    private Integer doorId;

    /**
     * 门锁类型
     */
    private Integer lockType;

    /**
     * 门锁MAC
     */
    private String lockMac;

    /**
     * 开锁时间
     */
    private String recordTime;

    /**
     * 小区id
     */
    private Integer propertyId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 添加时间
     */
    private String addTime;
    /**
     * 开锁状态 0成功  1失败
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
    private String name;

    public LockLogVo() {
        super();
    }

    public LockLogVo(Integer id, Integer doorId, Integer lockType, String lockMac, String recordTime, Integer propertyId, Integer userId, String addTime, Integer status, String remark, String name) {
        this.id = id;
        this.doorId = doorId;
        this.lockType = lockType;
        this.lockMac = lockMac;
        this.recordTime = recordTime;
        this.propertyId = propertyId;
        this.userId = userId;
        this.addTime = addTime;
        this.status = status;
        this.remark = remark;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    public Integer getLockType() {
        return lockType;
    }

    public void setLockType(Integer lockType) {
        this.lockType = lockType;
    }

    public String getLockMac() {
        return lockMac;
    }

    public void setLockMac(String lockMac) {
        this.lockMac = lockMac;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LockLogVo{" +
                "id=" + id +
                ", doorId=" + doorId +
                ", lockType=" + lockType +
                ", lockMac='" + lockMac + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", propertyId=" + propertyId +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
