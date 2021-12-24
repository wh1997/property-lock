package com.tianjian.property.bean.vo;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/7
 */
public class AuthorizationVo {
    private Integer aId;
    private Integer userStatus;
    private Integer addPerson;
    private String enterTime;
    private String leavaTime;
    private String opendoorStatus;
    private Integer dId;
    private Integer propertyId;
    private String propertyName;
    private String numName;
    private String buildingName;
    private String unitName;
    private Integer floorNo;
    private Integer roomNo;
    private String doorName;
    private Integer doorType;
    private Integer dStatus;

    public AuthorizationVo() {
        super();
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getLeavaTime() {
        return leavaTime;
    }

    public void setLeavaTime(String leavaTime) {
        this.leavaTime = leavaTime;
    }

    public String getOpendoorStatus() {
        return opendoorStatus;
    }

    public void setOpendoorStatus(String opendoorStatus) {
        this.opendoorStatus = opendoorStatus;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
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

    public String getNumName() {
        return numName;
    }

    public void setNumName(String numName) {
        this.numName = numName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public Integer getDoorType() {
        return doorType;
    }

    public void setDoorType(Integer doorType) {
        this.doorType = doorType;
    }

    public Integer getdStatus() {
        return dStatus;
    }

    public void setdStatus(Integer dStatus) {
        this.dStatus = dStatus;
    }

    @Override
    public String toString() {
        return "AuthorizationVo{" +
                "aId=" + aId +
                ", userStatus=" + userStatus +
                ", addPerson=" + addPerson +
                ", enterTime='" + enterTime + '\'' +
                ", leavaTime='" + leavaTime + '\'' +
                ", opendoorStatus='" + opendoorStatus + '\'' +
                ", dId=" + dId +
                ", propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", numName='" + numName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", floorNo=" + floorNo +
                ", roomNo=" + roomNo +
                ", doorName='" + doorName + '\'' +
                ", doorType=" + doorType +
                ", dStatus=" + dStatus +
                '}';
    }
}
