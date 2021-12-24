package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_fingerprint`")
public class Fingerprint implements Serializable {
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
     * 钥匙id
     */
    @Column(name = "`lockKey_id`")
    private Integer lockKeyId;

    /**
     * 指纹添加人
     */
    @Column(name = "`add_person`")
    private Integer addPerson;

    /**
     * 有效起始时间
     */
    @Column(name = "`validstart_time`")
    private Long validStartTime;

    /**
     * 有效结束时间
     */
    @Column(name = "`validend_time`")
    private Long validEndTime;

    /**
     * 数据添加时间
     */
    @Column(name = "`add_time`")
    private String addTime;

    /**
     * 用户组 ID
     */
    @Column(name = "`added_key_groupId`")
    private Integer addedKeyGroupId;
    /**
     * 状态
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Fingerprint() {
        super();
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

    public Integer getLockKeyId() {
        return lockKeyId;
    }

    public void setLockKeyId(Integer lockKeyId) {
        this.lockKeyId = lockKeyId;
    }

    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
    }

    public Long getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Long validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Long getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Long validEndTime) {
        this.validEndTime = validEndTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getAddedKeyGroupId() {
        return addedKeyGroupId;
    }

    public void setAddedKeyGroupId(Integer addedKeyGroupId) {
        this.addedKeyGroupId = addedKeyGroupId;
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

    @Override
    public String toString() {
        return "Fingerprint{" +
                "id=" + id +
                ", doorId=" + doorId +
                ", lockKeyId=" + lockKeyId +
                ", addPerson=" + addPerson +
                ", validStartTime=" + validStartTime +
                ", validEndTime=" + validEndTime +
                ", addTime='" + addTime + '\'' +
                ", addedKeyGroupId=" + addedKeyGroupId +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}