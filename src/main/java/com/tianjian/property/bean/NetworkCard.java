package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_network_card`")
public class NetworkCard implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备名称
     */
    @Column(name = "`network_name`")
    private String networkName;

    /**
     * 设备唯一码
     */
    @Column(name = "`deviceflag`")
    private String deviceflag;

    /**
     * 设备厂商
     */
    @Column(name = "`network_vendor`")
    private String networkVendor;

    /**
     * 设备使用时间
     */
    @Column(name = "`addtime`")
    private String addtime;

    /**
     * 修改时间
     */
    @Column(name = "`updatetime`")
    private String updatetime;

    /**
     * 设备状态  0表示正常1表示删除
     */
    @Column(name = "`network_status`")
    private Integer networkStatus;

    /**
     * 百为同步设备id
     */
    @Column(name = "`property_id`")
    private Integer propertyId;

    /**
     * 设备添加人
     */
    @Column(name = "`add_user`")
    private Integer addUser;

    /**
     * 绑定手机号
     */
    @Column(name = "`network_phone`")
    private String networkPhone;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public NetworkCard() {
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
     * 获取设备名称
     *
     * @return network_name - 设备名称
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * 设置设备名称
     *
     * @param networkName 设备名称
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName == null ? null : networkName.trim();
    }

    /**
     * 获取设备唯一码
     *
     * @return deviceflag - 设备唯一码
     */
    public String getDeviceflag() {
        return deviceflag;
    }

    /**
     * 设置设备唯一码
     *
     * @param deviceflag 设备唯一码
     */
    public void setDeviceflag(String deviceflag) {
        this.deviceflag = deviceflag == null ? null : deviceflag.trim();
    }

    /**
     * 获取设备厂商
     *
     * @return network_vendor - 设备厂商
     */
    public String getNetworkVendor() {
        return networkVendor;
    }

    /**
     * 设置设备厂商
     *
     * @param networkVendor 设备厂商
     */
    public void setNetworkVendor(String networkVendor) {
        this.networkVendor = networkVendor == null ? null : networkVendor.trim();
    }

    /**
     * 获取设备使用时间
     *
     * @return addtime - 设备使用时间
     */
    public String getAddtime() {
        return addtime;
    }

    /**
     * 设置设备使用时间
     *
     * @param addtime 设备使用时间
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取修改时间
     *
     * @return updatetime - 修改时间
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取设备状态  0表示正常1表示删除
     *
     * @return network_status - 设备状态  0表示正常1表示删除
     */
    public Integer getNetworkStatus() {
        return networkStatus;
    }

    /**
     * 设置设备状态  0表示正常1表示删除
     *
     * @param networkStatus 设备状态  0表示正常1表示删除
     */
    public void setNetworkStatus(Integer networkStatus) {
        this.networkStatus = networkStatus;
    }

    /**
     * 获取百为同步设备id
     *
     * @return property_id - 百为同步设备id
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 设置百为同步设备id
     *
     * @param propertyId 百为同步设备id
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * 获取设备添加人
     *
     * @return add_user - 设备添加人
     */
    public Integer getAddUser() {
        return addUser;
    }

    /**
     * 设置设备添加人
     *
     * @param addUser 设备添加人
     */
    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    /**
     * 获取绑定手机号
     *
     * @return network_phone - 绑定手机号
     */
    public String getNetworkPhone() {
        return networkPhone;
    }

    /**
     * 设置绑定手机号
     *
     * @param networkPhone 绑定手机号
     */
    public void setNetworkPhone(String networkPhone) {
        this.networkPhone = networkPhone == null ? null : networkPhone.trim();
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
        sb.append(", networkName=").append(networkName);
        sb.append(", deviceflag=").append(deviceflag);
        sb.append(", networkVendor=").append(networkVendor);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", networkStatus=").append(networkStatus);
        sb.append(", propertyId=").append(propertyId);
        sb.append(", addUser=").append(addUser);
        sb.append(", networkPhone=").append(networkPhone);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}