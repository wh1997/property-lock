package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_door`")
public class Door implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 小区id
     */
    @Column(name = "`property_id`")
    private Integer propertyId;

    /**
     * 小区名称
     */
    @Column(name = "`property_name`")
    private String propertyName;

    /**
     * 期数id
     */
    @Column(name = "`num_id`")
    private Integer numId;

    /**
     * 期数名称
     */
    @Column(name = "`num_name`")
    private String numName;

    /**
     * 楼栋id
     */
    @Column(name = "`building_id`")
    private Integer buildingId;

    /**
     * 楼栋名称
     */
    @Column(name = "`building_name`")
    private String buildingName;

    /**
     * 单元号
     */
    @Column(name = "`unit_no`")
    private Integer unitNo;

    /**
     * 单元名称
     */
    @Column(name = "`unit_name`")
    private String unitName;

    /**
     * 楼层
     */
    @Column(name = "`floor_no`")
    private Integer floorNo;

    /**
     * 房号
     */
    @Column(name = "`room_no`")
    private Integer roomNo;

    /**
     * 门名称
     */
    @Column(name = "`door_name`")
    private String doorName;

    /**
     * 门类型  0小区门 1楼栋门 2单元门 3公寓门
     */
    @Column(name = "`door_type`")
    private Integer doorType;

    /**
     * 使用状态 0在住  1闲置   2到期 3无锁
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 添加时间
     */
    @Column(name = "`add_time`")
    private String addTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private String updateTime;

    /**
     * 创建人
     */
    @Column(name = "`create_person`")
    private String createPerson;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Door() {
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
     * 获取小区id
     *
     * @return property_id - 小区id
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * 设置小区id
     *
     * @param propertyId 小区id
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId== null ? 0 : propertyId;
    }

    /**
     * 获取小区名称
     *
     * @return property_name - 小区名称
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置小区名称
     *
     * @param propertyName 小区名称
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? "" : propertyName.trim();
    }

    /**
     * 获取期数id
     *
     * @return num_id - 期数id
     */
    public Integer getNumId() {
        return numId;
    }

    /**
     * 设置期数id
     *
     * @param numId 期数id
     */
    public void setNumId(Integer numId) {
        this.numId = numId== null ? 0 : numId;
    }

    /**
     * 获取期数名称
     *
     * @return num_name - 期数名称
     */
    public String getNumName() {
        return numName;
    }

    /**
     * 设置期数名称
     *
     * @param numName 期数名称
     */
    public void setNumName(String numName) {
        this.numName = numName == null ? "" : numName.trim();
    }

    /**
     * 获取楼栋id
     *
     * @return building_id - 楼栋id
     */
    public Integer getBuildingId() {
        return buildingId;
    }

    /**
     * 设置楼栋id
     *
     * @param buildingId 楼栋id
     */
    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId== null ? 0 : buildingId;
    }

    /**
     * 获取楼栋名称
     *
     * @return building_name - 楼栋名称
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * 设置楼栋名称
     *
     * @param buildingName 楼栋名称
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? "" : buildingName.trim();
    }

    /**
     * 获取单元号
     *
     * @return unit_no - 单元号
     */
    public Integer getUnitNo() {
        return unitNo;
    }

    /**
     * 设置单元号
     *
     * @param unitNo 单元号
     */
    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo== null ? 0 : unitNo;
    }

    /**
     * 获取单元名称
     *
     * @return unit_name - 单元名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单元名称
     *
     * @param unitName 单元名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? "" : unitName.trim();
    }

    /**
     * 获取楼层
     *
     * @return floor_no - 楼层
     */
    public Integer getFloorNo() {
        return floorNo;
    }

    /**
     * 设置楼层
     *
     * @param floorNo 楼层
     */
    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo== null ? 0 : floorNo;
    }

    /**
     * 获取房号
     *
     * @return room_no - 房号
     */
    public Integer getRoomNo() {
        return roomNo;
    }

    /**
     * 设置房号
     *
     * @param roomNo 房号
     */
    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo== null ? 0 : roomNo;
    }

    /**
     * 获取门名称
     *
     * @return door_name - 门名称
     */
    public String getDoorName() {
        return doorName;
    }

    /**
     * 设置门名称
     *
     * @param doorName 门名称
     */
    public void setDoorName(String doorName) {
        this.doorName = doorName == null ? "" : doorName.trim();
    }

    /**
     * 获取门类型  0小区门 1楼栋门 2单元门 3公寓门
     *
     * @return door_type - 门类型  0小区门 1楼栋门 2单元门 3公寓门
     */
    public Integer getDoorType() {
        return doorType;
    }

    /**
     * 设置门类型  0小区门 1楼栋门 2单元门 3公寓门
     *
     * @param doorType 门类型  0小区门 1楼栋门 2单元门 3公寓门
     */
    public void setDoorType(Integer doorType) {
        this.doorType = doorType== null ? 0 : doorType;
    }

    /**
     * 获取使用状态 0在住  1闲置   2到期 3无锁
     *
     * @return status - 使用状态 0在住  1闲置   2到期 3无锁
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置使用状态 0在住  1闲置   2到期 3无锁
     *
     * @param status 使用状态 0在住  1闲置   2到期 3无锁
     */
    public void setStatus(Integer status) {
        this.status = status== null ? 1 : status;
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
     * 获取创建人
     *
     * @return create_person - 创建人
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * 设置创建人
     *
     * @param createPerson 创建人
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? "" : createPerson.trim();
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
        this.remark = remark == null ? "" : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", propertyId=").append(propertyId);
        sb.append(", propertyName=").append(propertyName);
        sb.append(", numId=").append(numId);
        sb.append(", numName=").append(numName);
        sb.append(", buildingId=").append(buildingId);
        sb.append(", buildingName=").append(buildingName);
        sb.append(", unitNo=").append(unitNo);
        sb.append(", unitName=").append(unitName);
        sb.append(", floorNo=").append(floorNo);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", doorName=").append(doorName);
        sb.append(", doorType=").append(doorType);
        sb.append(", status=").append(status);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}