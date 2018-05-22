package com.goropai.entity;

import java.io.Serializable;
import java.util.List;

public class UserType implements Serializable {

    private Long userTypeId;
    private String userTypeName;
    private List<User> users;

    public UserType() {

    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof UserType)) return false;

        UserType userType = (UserType) obj;

        if (!getUserTypeId().equals(userType.getUserTypeId())) return false;
        if (!getUserTypeName().equals(userType.getUserTypeName())) return false;
        return getUsers() != null ? getUsers().equals(userType.getUsers()) : userType.getUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserTypeId().hashCode();
        result = 31 * result + getUserTypeName().hashCode();
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User type id - " + userTypeId + ", user type name - " + userTypeName;
    }
}
