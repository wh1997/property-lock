package com.tianjian.property.bean.vo;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/12/16
 */
public class RoomVo {
  //小区id
  private   Integer ParkId;
  //楼栋Id
  private   Integer Building;
 //期数
  private   Integer numId;
  //单元id
  private   Integer unitNo;
  //楼层
  private   Integer Floor;
  //房号
  private   Integer DoorNo;

    public RoomVo() {
        super();
    }

    public Integer getParkId() {
        return ParkId;
    }

    public void setParkId(Integer parkId) {
        ParkId = parkId;
    }

    public Integer getBuilding() {
        return Building;
    }

    public void setBuilding(Integer building) {
        Building = building;
    }

    public Integer getNumId() {
        return numId;
    }

    public void setNumId(Integer numId) {
        this.numId = numId;
    }

    public Integer getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    public Integer getFloor() {
        return Floor;
    }

    public void setFloor(Integer floor) {
        Floor = floor;
    }

    public Integer getDoorNo() {
        return DoorNo;
    }

    public void setDoorNo(Integer doorNo) {
        DoorNo = doorNo;
    }

    @Override
    public String toString() {
        return "RoomVo{" +
                ", ParkId=" + ParkId +
                ", Building=" + Building +
                ", numId=" + numId +
                ", unitNo=" + unitNo +
                ", Floor=" + Floor +
                ", DoorNo=" + DoorNo +
                '}';
    }
}
