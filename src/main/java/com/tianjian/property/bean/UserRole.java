package com.tianjian.property.bean;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`tj_user_role`")
public class UserRole implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "`user_id`")
    private Integer userId;

    /**
     * 角色id
     */
    @Column(name = "`role_id`")
    private Integer roleId;
    /**
     * 角色id
     */
    @Column(name = "`add_person`")
    private Integer addPerson;

    /**
     * 1:未启用0:启用
     */
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public UserRole() {
        super();
    }

    public UserRole(Integer id, Integer userId, Integer roleId, Integer addPerson, Integer status) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.addPerson = addPerson;
        this.status = status;
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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
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
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", addPerson=" + addPerson +
                ", status=" + status +
                '}';
    }
}