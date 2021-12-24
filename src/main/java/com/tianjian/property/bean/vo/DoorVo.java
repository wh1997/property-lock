package com.tianjian.property.bean.vo;

public class DoorVo {
    private  Integer id;
    private Integer propertyId;
    private String propertyName;
    private Integer numId;
    //期数名称
    private String numName;
    //楼栋id
    private Integer buildingId;
    //楼栋名称
    private String buildingName;
    //楼层
    private Integer floorNo;
    //房号
    private String roomNo;
    //门名称
    private String doorName;
    //使用状态
    private Integer status;
    //单元号
    private Integer unitNo;
    //单元名称
    private String unitName;
    private Integer doorType;
    private Integer facilityType;

    public DoorVo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getNumId() {
        return numId;
    }

    public void setNumId(Integer numId) {
        this.numId = numId;
    }

    public String getNumName() {
        return numName;
    }

    public void setNumName(String numName) {
        this.numName = numName;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getDoorType() {
        return doorType;
    }

    public void setDoorType(Integer doorType) {
        this.doorType = doorType;
    }

    public Integer getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(Integer lockGatewayId) {
        this.facilityType = lockGatewayId;
    }

    @Override
    public String toString() {
        return "DoorVo{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", numId=" + numId +
                ", numName='" + numName + '\'' +
                ", buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", floorNo=" + floorNo +
                ", roomNo='" + roomNo + '\'' +
                ", doorName='" + doorName + '\'' +
                ", status=" + status +
                ", unitNo=" + unitNo +
                ", unitName='" + unitName + '\'' +
                ", doorType=" + doorType +
                ", lockGatewayId=" + facilityType +
                '}';
    }
}
