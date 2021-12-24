package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_malfunction`")
public class Malfunction implements Serializable {
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
     * 门id
     */
    @Column(name = "`door_id`")
    private Integer doorId;

    /**
     * 门状态
     */
    @Column(name = "`door_status`")
    private Integer doorStatus;

    /**
     * 故障原因
     */
    @Column(name = "`reason`")
    private String reason;

    /**
     * 设备名称
     */
    @Column(name = "`facility_name`")
    private String facilityName;

    /**
     * 设备id
     */
    @Column(name = "`facility_id`")
    private Integer facilityId;

    /**
     * 故障时间
     */
    @Column(name = "`add_time`")
    private String addTime;

    /**
     * 维修时间
     */
    @Column(name = "`update_time`")
    private String updateTime;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Malfunction() {
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
     * 获取门状态
     *
     * @return door_status - 门状态
     */
    public Integer getDoorStatus() {
        return doorStatus;
    }

    /**
     * 设置门状态
     *
     * @param doorStatus 门状态
     */
    public void setDoorStatus(Integer doorStatus) {
        this.doorStatus = doorStatus;
    }

    /**
     * 获取故障原因
     *
     * @return reason - 故障原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置故障原因
     *
     * @param reason 故障原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 获取设备名称
     *
     * @return facility_name - 设备名称
     */
    public String getFacilityName() {
        return facilityName;
    }

    /**
     * 设置设备名称
     *
     * @param facilityName 设备名称
     */
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName == null ? null : facilityName.trim();
    }

    /**
     * 获取设备id
     *
     * @return facility_id - 设备id
     */
    public Integer getFacilityId() {
        return facilityId;
    }

    /**
     * 设置设备id
     *
     * @param facilityId 设备id
     */
    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    /**
     * 获取故障时间
     *
     * @return add_time - 故障时间
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * 设置故障时间
     *
     * @param addTime 故障时间
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取维修时间
     *
     * @return update_time - 维修时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置维修时间
     *
     * @param updateTime 维修时间
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
        sb.append(", userId=").append(userId);
        sb.append(", doorId=").append(doorId);
        sb.append(", doorStatus=").append(doorStatus);
        sb.append(", reason=").append(reason);
        sb.append(", facilityName=").append(facilityName);
        sb.append(", facilityId=").append(facilityId);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}