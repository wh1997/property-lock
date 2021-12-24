package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_gateway`")
public class Gateway implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 网关id
     */
    @Column(name = "`gateway_id`")
    private String gatewayId;

    /**
     * 网关序列号
     */
    @Column(name = "`deviceseq`")
    private String deviceseq;

    /**
     * 网关名称
     */
    @Column(name = "`gateway_name`")
    private String gatewayName;

    /**
     * 网关mac
     */
    @Column(name = "`gateway_mac`")
    private String gatewayMac;

    /**
     * 网关类型
     */
    @Column(name = "`gateway_type`")
    private Integer gatewayType;

    /**
     * 硬件版本号
     */
    @Column(name = "`hardware_version`")
    private String hardwareVersion;

    /**
     * 软件版本号
     */
    @Column(name = "`software_version`")
    private String softwareVersion;

    /**
     * 对应项目小区
     */
    @Column(name = "`project`")
    private Integer project;

    /**
     * 厂商
     */
    @Column(name = "`vendor`")
    private String vendor;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private String createTime;

    /**
     * 废弃时间
     */
    @Column(name = "`discard_time`")
    private String discardTime;

    /**
     * 网关状态 3在线 4离线  5删除
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Gateway() {
        super();
    }

    public Gateway(Integer id, String gatewayId, String deviceseq, String gatewayName, String gatewayMac, Integer gatewayType, String hardwareVersion, String softwareVersion, Integer project, String vendor, String createTime, String discardTime, Integer status, String remark) {
        this.id = id;
        this.gatewayId = gatewayId;
        this.deviceseq = deviceseq;
        this.gatewayName = gatewayName;
        this.gatewayMac = gatewayMac;
        this.gatewayType = gatewayType;
        this.hardwareVersion = hardwareVersion;
        this.softwareVersion = softwareVersion;
        this.project = project;
        this.vendor = vendor;
        this.createTime = createTime;
        this.discardTime = discardTime;
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
     * 获取网关id
     *
     * @return gateway_id - 网关id
     */
    public String getGatewayId() {
        return gatewayId;
    }

    /**
     * 设置网关id
     *
     * @param gatewayId 网关id
     */
    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId == null ? null : gatewayId.trim();
    }

    /**
     * 获取网关序列号
     *
     * @return deviceseq - 网关序列号
     */
    public String getDeviceseq() {
        return deviceseq;
    }

    /**
     * 设置网关序列号
     *
     * @param deviceseq 网关序列号
     */
    public void setDeviceseq(String deviceseq) {
        this.deviceseq = deviceseq == null ? null : deviceseq.trim();
    }

    /**
     * 获取网关名称
     *
     * @return gateway_name - 网关名称
     */
    public String getGatewayName() {
        return gatewayName;
    }

    /**
     * 设置网关名称
     *
     * @param gatewayName 网关名称
     */
    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName == null ? null : gatewayName.trim();
    }

    /**
     * 获取网关mac
     *
     * @return gateway_mac - 网关mac
     */
    public String getGatewayMac() {
        return gatewayMac;
    }

    /**
     * 设置网关mac
     *
     * @param gatewayMac 网关mac
     */
    public void setGatewayMac(String gatewayMac) {
        this.gatewayMac = gatewayMac == null ? null : gatewayMac.trim();
    }

    /**
     * 获取网关类型
     *
     * @return gateway_type - 网关类型
     */
    public Integer getGatewayType() {
        return gatewayType;
    }

    /**
     * 设置网关类型
     *
     * @param gatewayType 网关类型
     */
    public void setGatewayType(Integer gatewayType) {
        this.gatewayType = gatewayType;
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
     * 获取软件版本号
     *
     * @return software_version - 软件版本号
     */
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    /**
     * 设置软件版本号
     *
     * @param softwareVersion 软件版本号
     */
    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion == null ? null : softwareVersion.trim();
    }

    /**
     * 获取对应项目小区
     *
     * @return Project - 对应项目小区
     */
    public Integer getProject() {
        return project;
    }

    /**
     * 设置对应项目小区
     *
     * @param project 对应项目小区
     */
    public void setProject(Integer project) {
        this.project = project;
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
     * 获取废弃时间
     *
     * @return discard_time - 废弃时间
     */
    public String getDiscardTime() {
        return discardTime;
    }

    /**
     * 设置废弃时间
     *
     * @param discardTime 废弃时间
     */
    public void setDiscardTime(String discardTime) {
        this.discardTime = discardTime;
    }

    /**
     * 获取网关状态 3在线 4离线  5删除
     *
     * @return status - 网关状态 3在线 4离线  5删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置网关状态 3在线 4离线  5删除
     *
     * @param status 网关状态 3在线 4离线  5删除
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
        sb.append(", gatewayId=").append(gatewayId);
        sb.append(", deviceseq=").append(deviceseq);
        sb.append(", gatewayName=").append(gatewayName);
        sb.append(", gatewayMac=").append(gatewayMac);
        sb.append(", gatewayType=").append(gatewayType);
        sb.append(", hardwareVersion=").append(hardwareVersion);
        sb.append(", softwareVersion=").append(softwareVersion);
        sb.append(", project=").append(project);
        sb.append(", vendor=").append(vendor);
        sb.append(", createTime=").append(createTime);
        sb.append(", discardTime=").append(discardTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}