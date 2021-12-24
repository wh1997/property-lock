package com.tianjian.property.bean;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`tj_auth`")
public class Auth implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`role_id`")
    private Integer roleId;

    /**
     * 如果模块是property对应tj_baiwei_old_id表id
     */
    @Column(name = "`resources_id`")
    private Integer resourcesId;
    /**
     * 添加人
     */
    @Column(name = "`person_id`")
    private Integer personId;

    @Column(name = "`type`")
    private String type;

    /**
     * 1:未启用0:启用
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Auth() {
        super();
    }

    public Auth(Integer id, Integer roleId, Integer resourcesId, Integer personId, String type, Integer status) {
        this.id = id;
        this.roleId = roleId;
        this.resourcesId = resourcesId;
        this.personId = personId;
        this.type = type;
        this.status = status;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取1:未启用0:启用
     *
     * @return status - 1:未启用0:启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1:未启用0:启用
     *
     * @param status 1:未启用0:启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourcesId=" + resourcesId +
                ", personId=" + personId +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}