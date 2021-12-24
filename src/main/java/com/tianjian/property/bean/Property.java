package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_property`")
public class Property implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分区id
     */
    @Column(name = "`branch_id`")
    private Integer branchId;

    /**
     * 百为项目id
     */
    @Column(name = "`bw_property_id`")
    private Integer bwPropertyId;

    /**
     * 老门禁项目id

     */
    @Column(name = "`tj_oldProperty_id`")
    private Integer tjOldpropertyId;

    @Column(name = "`address_id`")
    private Integer addressId;

    /**
     * 项目名称
     */
    @Column(name = "`property_name`")
    private String propertyName;

    /**
     * 添加时间
     */
    @Column(name = "`add_time`")
    private String addTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private String updateTime;

    /**
     * 0启用1未启用
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Property() {
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
     * 获取分区id
     *
     * @return branch_id - 分区id
     */
    public Integer getBranchId() {
        return branchId;
    }

    /**
     * 设置分区id
     *
     * @param branchId 分区id
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    /**
     * 获取百为项目id
     *
     * @return bw_property_id - 百为项目id
     */
    public Integer getBwPropertyId() {
        return bwPropertyId;
    }

    /**
     * 设置百为项目id
     *
     * @param bwPropertyId 百为项目id
     */
    public void setBwPropertyId(Integer bwPropertyId) {
        this.bwPropertyId = bwPropertyId;
    }

    /**
     * 获取老门禁项目id

     *
     * @return tj_oldProperty_id - 老门禁项目id

     */
    public Integer getTjOldpropertyId() {
        return tjOldpropertyId;
    }

    /**
     * 设置老门禁项目id

     *
     * @param tjOldpropertyId 老门禁项目id

     */
    public void setTjOldpropertyId(Integer tjOldpropertyId) {
        this.tjOldpropertyId = tjOldpropertyId;
    }

    /**
     * 获取项目名称
     *
     * @return property_name - 项目名称
     */
    public String getPropertyName() {
        return propertyName;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 设置项目名称
     *
     * @param propertyName 项目名称
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取0启用1未启用
     *
     * @return status - 0启用1未启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0启用1未启用
     *
     * @param status 0启用1未启用
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
        return "Property{" +
                "id=" + id +
                ", branchId=" + branchId +
                ", bwPropertyId=" + bwPropertyId +
                ", tjOldpropertyId=" + tjOldpropertyId +
                ", addressId=" + addressId +
                ", propertyName='" + propertyName + '\'' +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}