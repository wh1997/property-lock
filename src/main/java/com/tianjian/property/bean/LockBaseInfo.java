package com.tianjian.property.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_lockbaseinfo`")
public class LockBaseInfo implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 门锁id
     */
    @Column(name = "`lock_id`")
    private String lockId;

    /**
     * 维护版本对应小程序DNA信息的initFlag
     */
    @Column(name = "`lock_tag`")
    private String lockTag;

    /**
     * 门锁MAC
     */
    @Column(name = "`lock_mac`")
    private String lockMac;

    /**
     * 硬件版本号
     */
    @Column(name = "`hardware_version`")
    private String hardwareVersion;

    /**
     * 软件版本对应小程序DNA信息的firmwareVersion
     */
    @Column(name = "`software_version`")
    private String softwareVersion;

    /**
     * 门锁类型
     */
    @Column(name = "`lock_type`")
    private Integer lockType;

    /**
     * 初始化状态标识   0~1,0—门锁为初始化状态; 1—非初始状态,已经添加了管理钥匙
     */
    @Column(name = "`init_status`")
    private Integer initStatus;

    /**
     * 最大音量
     */
    @Column(name = "`max_volume`")
    private Integer maxVolume;

    /**
     * 最大用户数
     */
    @Column(name = "`max_user`")
    private Integer maxUser;

    /**
     * 蓝牙锁支持的命令版本号
     对应小程序DNA信息的bleProtocolVersion
     */
    @Column(name = "`bleprotocol_ver`")
    private Integer bleprotocolVer;

    /**
     * 无线模组类型
     */
    @Column(name = "`rfmodule_type`")
    private Integer rfModuleType;

    /**
     * 联网模块的MAC   带433模块必填
     */
    @Column(name = "`rfmodule_mac`")
    private String rfModuleMac;

    /**
     * 密钥
     */
    @Column(name = "`aes_key`")
    private String aesKey;

    /**
     * 管理员授权码
     */
    @Column(name = "`admin_auth_code`")
    private String adminAuthCode;

    /**
     * 普通用户授权码
     */
    @Column(name = "`general_auth_code`")
    private String generalAuthCode;

    /**
     * 临时用户授权码
     */
    @Column(name = "`temp_auth_code`")
    private String tempAuthCode;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private String updateTime;

    /**
     * 设备添加人userid
     */
    @Column(name = "`add_people`")
    private String addPeople;

    /**
     * 厂商
     */
    @Column(name = "`vendor`")
    private String vendor;

    /**
     * 门锁设备状态 0正常使用 1未绑定网关 2损坏废弃(删除)
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public LockBaseInfo() {
        super();
    }

    public LockBaseInfo(Integer id, String lockId, String lockTag, String lockMac, String hardwareVersion, String softwareVersion, Integer lockType, Integer initStatus, Integer maxVolume, Integer maxUser, Integer bleprotocolVer, Integer rfModuleType, String rfModuleMac, String aesKey, String adminAuthCode, String generalAuthCode, String tempAuthCode, String createTime, String updateTime, String addPeople, String vendor, Integer status, String remark) {
        this.id = id;
        this.lockId = lockId;
        this.lockTag = lockTag;
        this.lockMac = lockMac;
        this.hardwareVersion = hardwareVersion;
        this.softwareVersion = softwareVersion;
        this.lockType = lockType;
        this.initStatus = initStatus;
        this.maxVolume = maxVolume;
        this.maxUser = maxUser;
        this.bleprotocolVer = bleprotocolVer;
        this.rfModuleType = rfModuleType;
        this.rfModuleMac = rfModuleMac;
        this.aesKey = aesKey;
        this.adminAuthCode = adminAuthCode;
        this.generalAuthCode = generalAuthCode;
        this.tempAuthCode = tempAuthCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.addPeople = addPeople;
        this.vendor = vendor;
        this.status = status;
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
     * 获取门锁id
     *
     * @return lock_id - 门锁id
     */
    public String getLockId() {
        return lockId;
    }

    /**
     * 设置门锁id
     *
     * @param lockId 门锁id
     */
    public void setLockId(String lockId) {
        this.lockId = lockId == null ? null : lockId.trim();
    }

    /**
     * 获取维护版本对应小程序DNA信息的initFlag
     *
     * @return lock_tag - 维护版本对应小程序DNA信息的initFlag
     */
    public String getLockTag() {
        return lockTag;
    }

    /**
     * 设置维护版本对应小程序DNA信息的initFlag
     *
     * @param lockTag 维护版本对应小程序DNA信息的initFlag
     */
    public void setLockTag(String lockTag) {
        this.lockTag = lockTag == null ? null : lockTag.trim();
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
     * 获取硬件版本号
     *
     * @return hardware_version - 硬件版本号
     */
    public String getHardwareVersion() {
        return hardwareVersion;
    }

    /**
     * 设置硬件版本号
     *
     * @param hardwareVersion 硬件版本号
     */
    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion == null ? null : hardwareVersion.trim();
    }

    /**
     * 获取软件版本对应小程序DNA信息的firmwareVersion
     *
     * @return software_version - 软件版本对应小程序DNA信息的firmwareVersion
     */
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    /**
     * 设置软件版本对应小程序DNA信息的firmwareVersion
     *
     * @param softwareVersion 软件版本对应小程序DNA信息的firmwareVersion
     */
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion == null ? null : softwareVersion.trim();
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
     * 获取初始化状态标识   0~1,0—门锁为初始化状态; 1—非初始状态,已经添加了管理钥匙
     *
     * @return init_status - 初始化状态标识   0~1,0—门锁为初始化状态; 1—非初始状态,已经添加了管理钥匙
     */
    public Integer getInitStatus() {
        return initStatus;
    }

    /**
     * 设置初始化状态标识   0~1,0—门锁为初始化状态; 1—非初始状态,已经添加了管理钥匙
     *
     * @param initStatus 初始化状态标识   0~1,0—门锁为初始化状态; 1—非初始状态,已经添加了管理钥匙
     */
    public void setInitStatus(Integer initStatus) {
        this.initStatus = initStatus;
    }

    /**
     * 获取最大音量
     *
     * @return max_volume - 最大音量
     */
    public Integer getMaxVolume() {
        return maxVolume;
    }

    /**
     * 设置最大音量
     *
     * @param maxVolume 最大音量
     */
    public void setMaxVolume(Integer maxVolume) {
        this.maxVolume = maxVolume;
    }

    /**
     * 获取最大用户数
     *
     * @return max_user - 最大用户数
     */
    public Integer getMaxUser() {
        return maxUser;
    }

    /**
     * 设置最大用户数
     *
     * @param maxUser 最大用户数
     */
    public void setMaxUser(Integer maxUser) {
        this.maxUser = maxUser;
    }

    /**
     * 获取蓝牙锁支持的命令版本号
     对应小程序DNA信息的bleProtocolVersion
     *
     * @return bleprotocol_ver - 蓝牙锁支持的命令版本号
    对应小程序DNA信息的bleProtocolVersion
     */
    public Integer getBleprotocolVer() {
        return bleprotocolVer;
    }

    /**
     * 设置蓝牙锁支持的命令版本号
     对应小程序DNA信息的bleProtocolVersion
     *
     * @param bleprotocolVer 蓝牙锁支持的命令版本号
    对应小程序DNA信息的bleProtocolVersion
     */
    public void setBleprotocolVer(Integer bleprotocolVer) {
        this.bleprotocolVer = bleprotocolVer;
    }

    public Integer getRfModuleType() {
        return rfModuleType;
    }

    public void setRfModuleType(Integer rfModuleType) {
        this.rfModuleType = rfModuleType;
    }

    public String getRfModuleMac() {
        return rfModuleMac;
    }

    public void setRfModuleMac(String rfModuleMac) {
        this.rfModuleMac = rfModuleMac;
    }

    /**
     * 获取密钥
     *
     * @return aes_key - 密钥
     */
    public String getAesKey() {
        return aesKey;
    }

    /**
     * 设置密钥
     *
     * @param aesKey 密钥
     */
    public void setAesKey(String aesKey) {
        this.aesKey = aesKey == null ? null : aesKey.trim();
    }

    /**
     * 获取管理员授权码
     *
     * @return admin_auth_code - 管理员授权码
     */
    public String getAdminAuthCode() {
        return adminAuthCode;
    }

    /**
     * 设置管理员授权码
     *
     * @param adminAuthCode 管理员授权码
     */
    public void setAdminAuthCode(String adminAuthCode) {
        this.adminAuthCode = adminAuthCode == null ? null : adminAuthCode.trim();
    }

    /**
     * 获取普通用户授权码
     *
     * @return general_auth_code - 普通用户授权码
     */
    public String getGeneralAuthCode() {
        return generalAuthCode;
    }

    /**
     * 设置普通用户授权码
     *
     * @param generalAuthCode 普通用户授权码
     */
    public void setGeneralAuthCode(String generalAuthCode) {
        this.generalAuthCode = generalAuthCode == null ? null : generalAuthCode.trim();
    }

    /**
     * 获取临时用户授权码
     *
     * @return temp_auth_code - 临时用户授权码
     */
    public String getTempAuthCode() {
        return tempAuthCode;
    }

    /**
     * 设置临时用户授权码
     *
     * @param tempAuthCode 临时用户授权码
     */
    public void setTempAuthCode(String tempAuthCode) {
        this.tempAuthCode = tempAuthCode == null ? null : tempAuthCode.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
     * 获取设备添加人userid
     *
     * @return add_people - 设备添加人userid
     */
    public String getAddPeople() {
        return addPeople;
    }

    /**
     * 设置设备添加人userid
     *
     * @param addPeople 设备添加人userid
     */
    public void setAddPeople(String addPeople) {
        this.addPeople = addPeople == null ? null : addPeople.trim();
    }

    /**
     * 获取厂商
     *
     * @return vendor - 厂商
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * 设置厂商
     *
     * @param vendor 厂商
     */
    public void setVendor(String vendor) {
        this.vendor = vendor == null ? null : vendor.trim();
    }

    /**
     * 获取门锁设备状态 0正常使用 1未绑定网关 2损坏废弃(删除)
     *
     * @return status - 门锁设备状态 0正常使用 1未绑定网关 2损坏废弃(删除)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置门锁设备状态 0正常使用 1未绑定网关 2损坏废弃(删除)
     *
     * @param status 门锁设备状态 0正常使用 1未绑定网关 2损坏废弃(删除)
     */
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lockId=").append(lockId);
        sb.append(", lockTag=").append(lockTag);
        sb.append(", lockMac=").append(lockMac);
        sb.append(", hardwareVersion=").append(hardwareVersion);
        sb.append(", softwareVersion=").append(softwareVersion);
        sb.append(", lockType=").append(lockType);
        sb.append(", initStatus=").append(initStatus);
        sb.append(", maxVolume=").append(maxVolume);
        sb.append(", maxUser=").append(maxUser);
        sb.append(", bleprotocolVer=").append(bleprotocolVer);
        sb.append(", rfmoduleType=").append(rfModuleType);
        sb.append(", rfmoduleMac=").append(rfModuleMac);
        sb.append(", aesKey=").append(aesKey);
        sb.append(", adminAuthCode=").append(adminAuthCode);
        sb.append(", generalAuthCode=").append(generalAuthCode);
        sb.append(", tempAuthCode=").append(tempAuthCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", addPeople=").append(addPeople);
        sb.append(", vendor=").append(vendor);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}