package com.tianjian.property.bean.vo;

import java.io.Serializable;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/10
 */
public class Param implements Serializable {
    private String  lockId;	 	//是	string	门锁ID	无
    private int delLockUserId;	 	//是	int	操作的门锁用户ID	详情见附录字段说明
    private int isSound;	//是	int	门锁语音	0：不更改设置1：语音开启2：语音关闭
    private int sysVolume;	 	//是	int	门锁音量1-5	0：不更改设置1~5：系统音量
    private int sysLanguage; 	//是	int	门锁语言	1简体中文，2繁体中文，3英文，4越南语，5泰语
    private int lockOpen;	 	//是	int	开锁模式	0：不改变开锁模式，1：单一开锁，2：组合开锁
    private int normallyOpen;	 	//是	int	常开模式	0：不更改常开模式;1：启用常开模式;2：关闭常开模式;
    private int isLock;	 	//是	int	反锁功能	0：不更改设置 1：反锁功能开启 2：反锁功能关闭
    private int isTamperWarn;	 	//是	int	防撬报警	0：不更改设置 1：防撬报警开启 2：防撬报警关闭
    private int isLockCoreWarn;	 	//是	int	锁芯报警	0：不更改设置 1：锁芯报警开启 2：锁芯报警关闭
    private int isLockCap;	 	//是	int	锁头盖报警	0：不更改设置 1：锁头盖报警开启 2：锁头盖报警关闭

    public Param() {
        super();
    }

    public Param(String lockId, int delLockUserId, int isSound, int sysVolume, int sysLanguage, int lockOpen, int normallyOpen, int isLock, int isTamperWarn, int isLockCoreWarn, int isLockCap) {
        this.lockId = lockId;
        this.delLockUserId = delLockUserId;
        this.isSound = isSound;
        this.sysVolume = sysVolume;
        this.sysLanguage = sysLanguage;
        this.lockOpen = lockOpen;
        this.normallyOpen = normallyOpen;
        this.isLock = isLock;
        this.isTamperWarn = isTamperWarn;
        this.isLockCoreWarn = isLockCoreWarn;
        this.isLockCap = isLockCap;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public int getDelLockUserId() {
        return delLockUserId;
    }

    public void setDelLockUserId(int delLockUserId) {
        this.delLockUserId = delLockUserId;
    }

    public int getIsSound() {
        return isSound;
    }

    public void setIsSound(int isSound) {
        this.isSound = isSound;
    }

    public int getSysVolume() {
        return sysVolume;
    }

    public void setSysVolume(int sysVolume) {
        this.sysVolume = sysVolume;
    }

    public int getSysLanguage() {
        return sysLanguage;
    }

    public void setSysLanguage(int sysLanguage) {
        this.sysLanguage = sysLanguage;
    }

    public int getLockOpen() {
        return lockOpen;
    }

    public void setLockOpen(int lockOpen) {
        this.lockOpen = lockOpen;
    }

    public int getNormallyOpen() {
        return normallyOpen;
    }

    public void setNormallyOpen(int normallyOpen) {
        this.normallyOpen = normallyOpen;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public int getIsTamperWarn() {
        return isTamperWarn;
    }

    public void setIsTamperWarn(int isTamperWarn) {
        this.isTamperWarn = isTamperWarn;
    }

    public int getIsLockCoreWarn() {
        return isLockCoreWarn;
    }

    public void setIsLockCoreWarn(int isLockCoreWarn) {
        this.isLockCoreWarn = isLockCoreWarn;
    }

    public int getIsLockCap() {
        return isLockCap;
    }

    public void setIsLockCap(int isLockCap) {
        this.isLockCap = isLockCap;
    }

    @Override
    public String toString() {
        return "Param{" +
                "lockId='" + lockId + '\'' +
                ", delLockUserId=" + delLockUserId +
                ", isSound=" + isSound +
                ", sysVolume=" + sysVolume +
                ", sysLanguage=" + sysLanguage +
                ", lockOpen=" + lockOpen +
                ", normallyOpen=" + normallyOpen +
                ", isLock=" + isLock +
                ", isTamperWarn=" + isTamperWarn +
                ", isLockCoreWarn=" + isLockCoreWarn +
                ", isLockCap=" + isLockCap +
                '}';
    }
}
