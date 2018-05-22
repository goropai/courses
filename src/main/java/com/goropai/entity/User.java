package com.goropai.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private Long userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private List<Course> courseStore;
    private UserType userType;

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Course> getCourseStore() {
        return courseStore;
    }

    public void setCourseStore(List<Course> courseStore) {
        this.courseStore = courseStore;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof User)) return false;

        User user = (User) obj;

        if (!getUserId().equals(user.getUserId())) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getName().equals(user.getName())) return false;
        if (!getSurname().equals(user.getSurname())) return false;
        if (getCourseStore() != null ? !getCourseStore().equals(user.getCourseStore()) : user.getCourseStore() != null)
            return false;
        return getUserType() != null ? getUserType().equals(user.getUserType()) : user.getUserType() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + (getCourseStore() != null ? getCourseStore().hashCode() : 0);
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User: [FullName - " + name + " " + surname + ", login: " + login + ", password: " + password + "]";
    }

}
