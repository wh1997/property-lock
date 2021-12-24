package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_lock`")
public class Lock implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应门id
     */
    @Column(name = "`door_id`")
    private Integer doorId;

    /**
     * 锁的状态0锁状态正常1锁状态异常
     */
    @Column(name = "`lock_status`")
    private Integer lockStatus;

    /**
     * 锁设备id
     */
    @Column(name = "`lock_facility_id`")
    private Integer lockFacilityId;

    /**
     * 对应的网关id  如果是网卡则为0
     如果是null则为未绑定
     */
    @Column(name = "`Lock_gateway_id`")
    private Integer lockGatewayId;

    /**
     * 设备类型 网关锁或者网卡锁  0:网关    1:网卡
     */
    @Column(name = "`facility_type`")
    private Integer facilityType;

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

    public Lock() {
        super();
    }

    public Lock(Integer id, Integer doorId, Integer lockStatus, Integer lockFacilityId, Integer lockGatewayId, Integer facilityType, String addTime, String updateTime, String remark) {
        this.id = id;
        this.doorId = doorId;
        this.lockStatus = lockStatus;
        this.lockFacilityId = lockFacilityId;
        this.lockGatewayId = lockGatewayId;
        this.facilityType = facilityType;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.remark = remark;
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
     * 获取对应门id
     *
     * @return door_id - 对应门id
     */
    public Integer getDoorId() {
        return doorId;
    }

    /**
     * 设置对应门id
     *
     * @param doorId 对应门id
     */
    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    /**
     * 获取锁的状态0锁状态正常1锁状态异常
     *
     * @return lock_status - 锁的状态0锁状态正常1锁状态异常
     */
    public Integer getLockStatus() {
        return lockStatus;
    }

    /**
     * 设置锁的状态0锁状态正常1锁状态异常
     *
     * @param lockStatus 锁的状态0锁状态正常1锁状态异常
     */
    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    /**
     * 获取锁设备id
     *
     * @return lock_facility_id - 锁设备id
     */
    public Integer getLockFacilityId() {
        return lockFacilityId;
    }

    /**
     * 设置锁设备id
     *
     * @param lockFacilityId 锁设备id
     */
    public void setLockFacilityId(Integer lockFacilityId) {
        this.lockFacilityId = lockFacilityId;
    }

    /**
     * 获取对应的网关id  如果是网卡则为0
     如果是null则为未绑定
     *
     * @return Lock_gateway_id - 对应的网关id  如果是网卡则为0
    如果是null则为未绑定
     */
    public Integer getLockGatewayId() {
        return lockGatewayId;
    }

    /**
     * 设置对应的网关id  如果是网卡则为0
     如果是null则为未绑定
     *
     * @param lockGatewayId 对应的网关id  如果是网卡则为0
    如果是null则为未绑定
     */
    public void setLockGatewayId(Integer lockGatewayId) {
        this.lockGatewayId = lockGatewayId;
    }

    /**
     * 获取设备类型 网关锁或者网卡锁  0:网关    1:网卡
     *
     * @return facility_type - 设备类型 网关锁或者网卡锁  0:网关    1:网卡
     */
    public Integer getFacilityType() {
        return facilityType;
    }

    /**
     * 设置设备类型 网关锁或者网卡锁  0:网关    1:网卡
     *
     * @param facilityType 设备类型 网关锁或者网卡锁  0:网关    1:网卡
     */
    public void setFacilityType(Integer facilityType) {
        this.facilityType = facilityType;
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

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", doorId=").append(doorId);
        sb.append(", lockStatus=").append(lockStatus);
        sb.append(", lockFacilityId=").append(lockFacilityId);
        sb.append(", lockGatewayId=").append(lockGatewayId);
        sb.append(", facilityType=").append(facilityType);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}