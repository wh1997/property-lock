package com.tianjian.property.bean.vo;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/2
 */
public class PasswordLock {
    //是		门锁ID	无
    private String lockId	;
    //是		门锁用户ID	操作添加钥匙的门锁用户ID 详情见附录字段说明
    private Integer delLockUserId;
    //是		门锁用户ID	钥匙添加到哪个门锁用户ID详情见附录字段说明
    private Integer lockUserId;
    //是		钥匙有效期类型	1有效期授权、2周期授权、3永久授权
    private Integer authorMode;
    //否		有效起始时间	时间戳，单位：秒，authorMode = 1或2时，有效
    private Long validStartTime;
    //否		有效结束时间	时间戳，单位：秒，authorMode = 1或2时，有效
    private Long validEndTime;
    //否		星期数据	authorMode=2时有效，按位域bit0~bit6分别表示，对应bit位数置1，则表示当天钥匙有效。bit0表示周一，bit6表示日。如周二、周三有效，weeks=6,6=0000110
    private Integer	weeks ;
    //否		每日起始时间	authorMode = 2有效，00:00~23:59
    private String dayStartTimes;
    //否		每日结束时间	authorMode = 2有效，00:00~23:59
    private String dayEndTimes;
    //是		密码/卡号	AES加密
    private String keyContent;

    public PasswordLock() {
        super();
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public Integer getDelLockUserId() {
        return delLockUserId;
    }

    public void setDelLockUserId(Integer delLockUserId) {
        this.delLockUserId = delLockUserId;
    }

    public Integer getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(Integer lockUserId) {
        this.lockUserId = lockUserId;
    }

    public Integer getAuthorMode() {
        return authorMode;
    }

    public void setAuthorMode(Integer authorMode) {
        this.authorMode = authorMode;
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

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public String getDayStartTimes() {
        return dayStartTimes;
    }

    public void setDayStartTimes(String dayStartTimes) {
        this.dayStartTimes = dayStartTimes;
    }

    public String getDayEndTimes() {
        return dayEndTimes;
    }

    public void setDayEndTimes(String dayEndTimes) {
        this.dayEndTimes = dayEndTimes;
    }

    public String getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
    }

    @Override
    public String toString() {
        return "PasswordLock{" +
                "lockId='" + lockId + '\'' +
                ", delLockUserId=" + delLockUserId +
                ", lockUserId=" + lockUserId +
                ", authorMode=" + authorMode +
                ", validStartTime=" + validStartTime +
                ", validEndTime=" + validEndTime +
                ", weeks=" + weeks +
                ", dayStartTimes='" + dayStartTimes + '\'' +
                ", dayEndTimes='" + dayEndTimes + '\'' +
                ", keyContent='" + keyContent + '\'' +
                '}';
    }
}
