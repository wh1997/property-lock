package com.tianjian.property.bean.vo;

/**
 * @description:
 * @author: ManolinCoder
 * @time: 2021/11/26
 */
public class AddressVo {
    private Integer id;

    /**
     * 地址编码
     */
    private String code;

    /**
     * 地址名称
     */
    private String name;

    /**
     * 地址简称
     */
    private String shortName;

    /**
     * 父地址id
     */
    private Integer parentId;

    /**
     * 地区级别
     1：省、自治区、直辖市
     2：地级市、地区、自治州、盟
     3：市辖区、县级市、县
     4：街道办、乡
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     0：未启用
     1：正常
     9：已失效
     */
    private Short status;

    public AddressVo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AddressVo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", sort=" + sort +
                ", status=" + status +
                '}';
    }
}
