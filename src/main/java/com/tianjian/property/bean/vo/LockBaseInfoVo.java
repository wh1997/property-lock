package com.tianjian.property.bean.vo;

public class LockBaseInfoVo {
    private String lockTag;	 		//必填	维护版本	对应小程序DNA信息的initFlag
    private String lockMac; 		//必填	门锁MAC	—
    private  String hardwareVersion;	 	//	必填	硬件版本号	—
    private String softwareVersion;	 		//必填	软件版本	对应小程序DNA信息的firmwareVersion
    private int lockType;	 		//必填	门锁类型	1-网关锁，2-WIFI锁，3-蓝牙锁，4-NB锁，5-HXJ01锁对应小程序DNA信息的slaveType
    private int initStatus;	 		//可选	初始化状态标识	0~1,0—门锁为初始化状态; 1—非初始状态,已经添加了管理钥匙
    private Long sysTime;	 		//可选	门锁系统当前时间	时间戳，单位秒
    private int electricNum;	 		//可选	最新电量	—
    private int noPowerOpenNum;	 		//可选	低电量后剩余开锁次数	—
    private int noPowerOpenNo;	 		//可选	低电量后剩余开锁次数	—
    private int normallyOpenFlag;	 		//可选	锁常开标识	0已关锁1已开锁常开状态
    private int isLockFlag;	 		//可选	反锁状态标识	0无反锁开关1未打反锁2已打反锁
    private int bigBoltFlag;	 		//可选	大方舌标志	0无大方舌开关; 1 大方舌缩进状态; 2大方舌伸出状态(锁门状态)
    private int boltFlag;	 		//可选	斜舌标志	0—无斜舌开关; 1—斜舌缩进状态; 2—斜舌伸出状态;
    private int isNoOpenFlag;	 		//可选	防撬开关标志	0—无防撬开关检测; 1—防撬开关未动作; 2—防撬开关已动作(可能触发强拆事件)
    private int isClose	; 		//可选	已关门标志	0—无关门开关检测; 1—已开门; 2—已关门
    private int coreFlag;	 		//可选	锁芯开关	0—无锁芯检测;1—锁芯未插入钥匙; 2—锁芯已插入钥匙
    private String lockServiceState;	//可选	业务状态	—
    private int functionFlag;	 		//可选	门锁命令集	表示门锁支持哪些命令
    private int maxVolume;	 		//可选	最大音量	0~5
    private int maxUser;	 		//可选	最大用户数	—
    private  int bleProtocolVer; 		//必填	蓝牙锁支持的命令版本号	蓝牙锁支持的命令版本号对应小程序DNA信息的bleProtocolVersion
    private int rfModuleType;	 		//必填	无线模组类型	0-TCV433，1-LPRX315，2-TCV868，4-HXJ-WIFI，5-HXJ-NBDX，6-HXJ-LoRa，7-HXJ-ZigBee，255-没有无线模组
    private String rfModuleMac;	 		//可选	联网模块的MAC	带433模块必填
    private int appCmdSets;	 		//可选	APP命令集	—
    private int fingerPrintfNum;	 		//可选	指纹需要识别多少次
    private int nbConnect;	 		//可选	NB是否注册	0未注册，1已注册
    private int lockConfig;	 		//可选	门锁功能信息

    public LockBaseInfoVo(String lockTag, String lockMac, String hardwareVersion, String softwareVersion, int lockType, int initStatus, Long sysTime, int electricNum, int noPowerOpenNum, int noPowerOpenNo, int normallyOpenFlag, int isLockFlag, int bigBoltFlag, int boltFlag, int isNoOpenFlag, int isClose, int coreFlag, String lockServiceState, int functionFlag, int maxVolume, int maxUser, int bleProtocolVer, int rfModuleType, String rfModuleMac, int appCmdSets, int fingerPrintfNum, int nbConnect, int lockConfig) {
        this.lockTag = lockTag;
        this.lockMac = lockMac;
        this.hardwareVersion = hardwareVersion;
        this.softwareVersion = softwareVersion;
        this.lockType = lockType;
        this.initStatus = initStatus;
        this.sysTime = sysTime;
        this.electricNum = electricNum;
        this.noPowerOpenNum = noPowerOpenNum;
        this.noPowerOpenNo = noPowerOpenNo;
        this.normallyOpenFlag = normallyOpenFlag;
        this.isLockFlag = isLockFlag;
        this.bigBoltFlag = bigBoltFlag;
        this.boltFlag = boltFlag;
        this.isNoOpenFlag = isNoOpenFlag;
        this.isClose = isClose;
        this.coreFlag = coreFlag;
        this.lockServiceState = lockServiceState;
        this.functionFlag = functionFlag;
        this.maxVolume = maxVolume;
        this.maxUser = maxUser;
        this.bleProtocolVer = bleProtocolVer;
        this.rfModuleType = rfModuleType;
        this.rfModuleMac = rfModuleMac;
        this.appCmdSets = appCmdSets;
        this.fingerPrintfNum = fingerPrintfNum;
        this.nbConnect = nbConnect;
        this.lockConfig = lockConfig;
    }

    public LockBaseInfoVo() {
        super();
    }

    public String getLockTag() {
        return lockTag;
    }

    public void setLockTag(String lockTag) {
        this.lockTag = lockTag;
    }

    public String getLockMac() {
        return lockMac;
    }

    public void setLockMac(String lockMac) {
        this.lockMac = lockMac;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public int getLockType() {
        return lockType;
    }

    public void setLockType(int lockType) {
        this.lockType = lockType;
    }

    public int getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(int initStatus) {
        this.initStatus = initStatus;
    }

    public Long getSysTime() {
        return sysTime;
    }

    public void setSysTime(Long sysTime) {
        this.sysTime = sysTime;
    }

    public int getElectricNum() {
        return electricNum;
    }

    public void setElectricNum(int electricNum) {
        this.electricNum = electricNum;
    }

    public int getNoPowerOpenNum() {
        return noPowerOpenNum;
    }

    public void setNoPowerOpenNum(int noPowerOpenNum) {
        this.noPowerOpenNum = noPowerOpenNum;
    }

    public int getNoPowerOpenNo() {
        return noPowerOpenNo;
    }

    public void setNoPowerOpenNo(int noPowerOpenNo) {
        this.noPowerOpenNo = noPowerOpenNo;
    }

    public int getNormallyOpenFlag() {
        return normallyOpenFlag;
    }

    public void setNormallyOpenFlag(int normallyOpenFlag) {
        this.normallyOpenFlag = normallyOpenFlag;
    }

    public int getIsLockFlag() {
        return isLockFlag;
    }

    public void setIsLockFlag(int isLockFlag) {
        this.isLockFlag = isLockFlag;
    }

    public int getBigBoltFlag() {
        return bigBoltFlag;
    }

    public void setBigBoltFlag(int bigBoltFlag) {
        this.bigBoltFlag = bigBoltFlag;
    }

    public int getBoltFlag() {
        return boltFlag;
    }

    public void setBoltFlag(int boltFlag) {
        this.boltFlag = boltFlag;
    }

    public int getIsNoOpenFlag() {
        return isNoOpenFlag;
    }

    public void setIsNoOpenFlag(int isNoOpenFlag) {
        this.isNoOpenFlag = isNoOpenFlag;
    }

    public int getIsClose() {
        return isClose;
    }

    public void setIsClose(int isClose) {
        this.isClose = isClose;
    }

    public int getCoreFlag() {
        return coreFlag;
    }

    public void setCoreFlag(int coreFlag) {
        this.coreFlag = coreFlag;
    }

    public String getLockServiceState() {
        return lockServiceState;
    }

    public void setLockServiceState(String lockServiceState) {
        this.lockServiceState = lockServiceState;
    }

    public int getFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag(int functionFlag) {
        this.functionFlag = functionFlag;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(int maxUser) {
        this.maxUser = maxUser;
    }

    public int getBleProtocolVer() {
        return bleProtocolVer;
    }

    public void setBleProtocolVer(int bleProtocolVer) {
        this.bleProtocolVer = bleProtocolVer;
    }

    public int getRfModuleType() {
        return rfModuleType;
    }

    public void setRfModuleType(int rfModuleType) {
        this.rfModuleType = rfModuleType;
    }

    public String getRfModuleMac() {
        return rfModuleMac;
    }

    public void setRfModuleMac(String rfModuleMac) {
        this.rfModuleMac = rfModuleMac;
    }

    public int getAppCmdSets() {
        return appCmdSets;
    }

    public void setAppCmdSets(int appCmdSets) {
        this.appCmdSets = appCmdSets;
    }

    public int getFingerPrintfNum() {
        return fingerPrintfNum;
    }

    public void setFingerPrintfNum(int fingerPrintfNum) {
        this.fingerPrintfNum = fingerPrintfNum;
    }

    public int getNbConnect() {
        return nbConnect;
    }

    public void setNbConnect(int nbConnect) {
        this.nbConnect = nbConnect;
    }

    public int getLockConfig() {
        return lockConfig;
    }

    public void setLockConfig(int lockConfig) {
        this.lockConfig = lockConfig;
    }

    @Override
    public String toString() {
        return "LockBaseInfoVo{" +
                "lockTag='" + lockTag + '\'' +
                ", lockMac='" + lockMac + '\'' +
                ", hardwareVersion='" + hardwareVersion + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", lockType=" + lockType +
                ", initStatus=" + initStatus +
                ", sysTime=" + sysTime +
                ", electricNum=" + electricNum +
                ", noPowerOpenNum=" + noPowerOpenNum +
                ", noPowerOpenNo=" + noPowerOpenNo +
                ", normallyOpenFlag=" + normallyOpenFlag +
                ", isLockFlag=" + isLockFlag +
                ", bigBoltFlag=" + bigBoltFlag +
                ", boltFlag=" + boltFlag +
                ", isNoOpenFlag=" + isNoOpenFlag +
                ", isClose=" + isClose +
                ", coreFlag=" + coreFlag +
                ", lockServiceState='" + lockServiceState + '\'' +
                ", functionFlag=" + functionFlag +
                ", maxVolume=" + maxVolume +
                ", maxUser=" + maxUser +
                ", bleProtocolVer=" + bleProtocolVer +
                ", rfModuleType=" + rfModuleType +
                ", rfModuleMac='" + rfModuleMac + '\'' +
                ", appCmdSets=" + appCmdSets +
                ", fingerPrintfNum=" + fingerPrintfNum +
                ", nbConnect=" + nbConnect +
                ", lockConfig=" + lockConfig +
                '}';
    }
}
