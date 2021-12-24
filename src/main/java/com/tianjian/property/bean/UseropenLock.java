package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_useropen_lock`")
public class UseropenLock implements Serializable {
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
     * 慧享佳锁id
     */
    @Column(name = "`lock_id`")
    private String lockId;

    /**
     * 用户id
     */
    @Column(name = "`lock_user_id`")
    private Integer lockUserId;

    /**
     * 卡号或密码
     */
    @Column(name = "`keyContent`")
    private String keyContent;
    /**
     * 钥匙编号
     */
    @Column(name = "`lockKey_id`")
    private Integer lockKeyId;

    /**
     * 有效起始时间
     */
    @Column(name = "`validstart_time`")
    private Long validstartTime;

    /**
     * 有效结束时间
     */
    @Column(name = "`validend_time`")
    private Long validendTime;

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
     * 数据添加(修改)人
     */
    @Column(name = "`add_person`")
    private Integer addPerson;
    /**
     * 数据修改时间
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public UseropenLock() {
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

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public Integer getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(Integer userId) {
        this.lockUserId = userId;
    }

    public String getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(String key) {
        this.keyContent = key;
    }

    public Integer getLockKeyId() {
        return lockKeyId;
    }

    public void setLockKeyId(Integer lockKeyId) {
        this.lockKeyId = lockKeyId;
    }

    public Long getValidstartTime() {
        return validstartTime;
    }

    public void setValidstartTime(Long validstartTime) {
        this.validstartTime = validstartTime;
    }

    public Long getValidendTime() {
        return validendTime;
    }

    public void setValidendTime(Long validendTime) {
        this.validendTime = validendTime;
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

    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
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
        return "UseropenLock{" +
                "id=" + id +
                ", doorId=" + doorId +
                ", lockId='" + lockId + '\'' +
                ", lockUserId=" + lockUserId +
                ", keyContent='" + keyContent + '\'' +
                ", lockKeyId='" + lockKeyId + '\'' +
                ", validstartTime=" + validstartTime +
                ", validendTime=" + validendTime +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", addPerson=" + addPerson +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}