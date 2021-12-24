package com.tianjian.property.bean;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`tj_lock_user`")
public class LockUser implements Serializable {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`door_id`")
    private Integer doorId;

    @Column(name = "`lock_user_id`")
    private Integer lockUserId;

    @Column(name = "`add_person`")
    private Integer addPerson;
    @Column(name = "`status`")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public LockUser() {
        super();
    }

    public LockUser(Integer id, Integer userId, Integer doorId, Integer lockUserId, Integer addPerson, Integer status) {
        this.id = id;
        this.userId = userId;
        this.doorId = doorId;
        this.lockUserId = lockUserId;
        this.addPerson = addPerson;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    public Integer getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(Integer lockUserId) {
        this.lockUserId = lockUserId;
    }

    public Integer getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(Integer addPerson) {
        this.addPerson = addPerson;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LockUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", doorId=" + doorId +
                ", lockUserId=" + lockUserId +
                ", addPerson=" + addPerson +
                ", status=" + status +
                '}';
    }
}