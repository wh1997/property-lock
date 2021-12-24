package com.tianjian.property.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tj_address")
public class Address implements Serializable {
    /**
     * 地址id
     */
    @Id
    @Column(name = "id", insertable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 地址编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 地址名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 地址简称
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 父地址id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 地区级别
1：省、自治区、直辖市
2：地级市、地区、自治州、盟
3：市辖区、县级市、县
4：街道办、乡
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 状态
0：未启用
1：正常
9：已失效
     */
    @Column(name = "status")
    private Short status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取地址id
     *
     * @return id - 地址id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置地址id
     *
     * @param id 地址id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取地址编码
     *
     * @return code - 地址编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置地址编码
     *
     * @param code 地址编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取地址名称
     *
     * @return name - 地址名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置地址名称
     *
     * @param name 地址名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取地址简称
     *
     * @return short_name - 地址简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置地址简称
     *
     * @param shortName 地址简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    /**
     * 获取父地址id
     *
     * @return parent_id - 父地址id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父地址id
     *
     * @param parentId 父地址id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取地区级别
1：省、自治区、直辖市
2：地级市、地区、自治州、盟
3：市辖区、县级市、县
4：街道办、乡
     *
     * @return level - 地区级别
1：省、自治区、直辖市
2：地级市、地区、自治州、盟
3：市辖区、县级市、县
4：街道办、乡
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置地区级别
1：省、自治区、直辖市
2：地级市、地区、自治州、盟
3：市辖区、县级市、县
4：街道办、乡
     *
     * @param level 地区级别
1：省、自治区、直辖市
2：地级市、地区、自治州、盟
3：市辖区、县级市、县
4：街道办、乡
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取状态
0：未启用
1：正常
9：已失效
     *
     * @return status - 状态
0：未启用
1：正常
9：已失效
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置状态
0：未启用
1：正常
9：已失效
     *
     * @param status 状态
0：未启用
1：正常
9：已失效
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", shortName=").append(shortName);
        sb.append(", parentId=").append(parentId);
        sb.append(", level=").append(level);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}