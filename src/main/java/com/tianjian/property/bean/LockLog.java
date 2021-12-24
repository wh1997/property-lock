package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_lock_log`")
public class LockLog implements Serializable {
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
     * 门锁类型
     */
    @Column(name = "`lock_type`")
    private Integer lockType;

    /**
     * 门锁MAC
     */
    @Column(name = "`lock_mac`")
    private String lockMac;

    /**
     * 开锁时间
     */
    @Column(name = "`record_time`")
    private String recordTime;

    /**
     * 小区id
     */
    @Column(name = "`property_id`")
    private Integer propertyId;

    /**
     * 用户id
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 添加时间
     */
    @Column(name = "`add_time`")
    private String addTime;
    /**
     * 开锁状态 0成功  1失败
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public LockLog() {
        super();
    }

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
     * @return lock_id - 门id
     */
    public Integer getDoorId() {
        return doorId;
    }

    /**
     * 设置门id
     *
     * @param lockId 门id
     */
    public void setDoorId(Integer lockId) {
        this.doorId = lockId;
    }

    /**
     * 获取门锁类型
     *
     * @return lock_type - 门锁类型
     */
    public Integer getLockType() {
        return lockType;
    }

    /**
     * 设置门锁类型
     *
     * @param lockType 门锁类型
     */
    public void setLockType(Integer lockType) {
        this.lockType = lockType;
    }

    /**
     * 获取门锁MAC
     *
     * @return lock_mac - 门锁MAC
     */
    public String getLockMac() {
        return lockMac;
    }

    /**
     * 设置门锁MAC
     *
     * @param lockMac 门锁MAC
     */
    public void setLockMac(String lockMac) {
        this.lockMac = lockMac == null ? null : lockMac.trim();
    }

    /**
     * 获取开锁时间
     *
     * @return record_time - 开锁时间
     */
    public String getRecordTime() {
        return recordTime;
    }

    /**
     * 设置开锁时间
     *
     * @param recordTime 开锁时间
     */
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * 获取小区id
     *
     * @return property_id - 小区id
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 设置小区id
     *
     * @param propertyId 小区id
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
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
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "LockLog{" +
                "id=" + id +
                ", lockId=" + doorId +
                ", lockType=" + lockType +
                ", lockMac='" + lockMac + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", propertyId=" + propertyId +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}