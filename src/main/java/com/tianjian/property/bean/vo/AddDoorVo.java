package com.tianjian.property.bean.vo;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/15
 */
public class AddDoorVo {
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
    private Integer unitNo;
    private String unitName;

    //楼层
    private Integer floorNo;
    //房号
    private Integer roomNo;
    //门名称
    private String doorName;

    public AddDoorVo() {
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

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    @Override
    public String toString() {
        return "AddDoorVo{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", numId=" + numId +
                ", numName='" + numName + '\'' +
                ", buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", unitNo=" + unitNo +
                ", unitName='" + unitName + '\'' +
                ", floorNo=" + floorNo +
                ", roomNo='" + roomNo + '\'' +
                ", doorName='" + doorName + '\'' +
                '}';
    }
}
