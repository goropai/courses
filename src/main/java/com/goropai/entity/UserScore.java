package com.goropai.entity;

import java.io.Serializable;

public class UserScore implements Serializable {

    private Integer mark;
    private Course relationshipCourse;
    private User relationshipUser;

    public UserScore() {

    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Course getRelationshipCourse() {
        return relationshipCourse;
    }

    public void setRelationshipCourse(Course relationshipCourse) {
        this.relationshipCourse = relationshipCourse;
    }

    public User getRelationshipUser() {
        return relationshipUser;
    }

    public void setRelationshipUser(User relationshipUser) {
        this.relationshipUser = relationshipUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof UserScore)) return false;

        UserScore userScore = (UserScore) obj;

        if (getMark() != null ? !getMark().equals(userScore.getMark()) : userScore.getMark() != null) return false;
        if (!getRelationshipCourse().equals(userScore.getRelationshipCourse())) return false;
        return getRelationshipUser().equals(userScore.getRelationshipUser());
    }

    @Override
    public int hashCode() {
        int result = getMark() != null ? getMark().hashCode() : 0;
        result = 31 * result + getRelationshipCourse().hashCode();
        result = 31 * result + getRelationshipUser().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Course name: " + relationshipCourse.getNameCourse() + ", student login: " + relationshipUser.getLogin() +
                ", mark: " + ((mark != null) ? mark : "");
    }
}
