package com.tianjian.property.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`tj_module`")
public class Module implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父id
父id为0的栏目代表系统
     */
    @Column(name = "`parent_id`")
    private Integer parentId;

    /**
     * 栏目名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * url
     */
    @Column(name = "`url`")
    private String url;

    /**
     * 图标
     */
    @Column(name = "`icon`")
    private String icon;

    /**
     * 是否为基础
0：不是基础栏目
1：基础栏目
     */
    @Column(name = "`base`")
    private Integer base;

    /**
     * 状态
0：启用
1：未启用
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 排序
     */
    @Column(name = "`sort`")
    private Integer sort;

    /**
     * 创建人
     */
    @Column(name = "`create_by`")
    private Integer createBy;

    /**
     * 更新人
     */
    @Column(name = "`update_by`")
    private Integer updateBy;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Module() {
        super();
    }

    public Module(Integer id, Integer parentId, String name, String url, String icon, Integer base, Integer status, Integer sort, Integer createBy, Integer updateBy, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.base = base;
        this.status = status;
        this.sort = sort;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
     * 获取父id
父id为0的栏目代表系统
     *
     * @return parent_id - 父id
父id为0的栏目代表系统
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父id
父id为0的栏目代表系统
     *
     * @param parentId 父id
父id为0的栏目代表系统
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取栏目名
     *
     * @return name - 栏目名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置栏目名
     *
     * @param name 栏目名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取url
     *
     * @return url - url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url
     *
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取是否为基础
0：不是基础栏目
1：基础栏目
     *
     * @return base - 是否为基础
0：不是基础栏目
1：基础栏目
     */
    public Integer getBase() {
        return base;
    }

    /**
     * 设置是否为基础
0：不是基础栏目
1：基础栏目
     *
     * @param base 是否为基础
0：不是基础栏目
1：基础栏目
     */
    public void setBase(Integer base) {
        this.base = base;
    }

    /**
     * 获取状态
0：启用
1：未启用
     *
     * @return status - 状态
0：启用
1：未启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
0：启用
1：未启用
     *
     * @param status 状态
0：启用
1：未启用
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
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
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", base=").append(base);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}