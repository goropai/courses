package com.goropai.entity;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

    private Long courseId;

    private String nameCourse;

    private List<User> userStore;

    public Course() {

    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public List<User> getUserStore() {
        return userStore;
    }

    public void setUserStore(List<User> userStore) {
        this.userStore = userStore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Course)) return false;

        Course course = (Course) obj;

        if (!getCourseId().equals(course.getCourseId())) return false;
        if (!getNameCourse().equals(course.getNameCourse())) return false;
        return getUserStore() != null ? getUserStore().equals(course.getUserStore()) : course.getUserStore() == null;
    }

    @Override
    public int hashCode() {
        int result = getCourseId().hashCode();
        result = 31 * result + getNameCourse().hashCode();
        result = 31 * result + (getUserStore() != null ? getUserStore().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course id - " + courseId + ", Course name - " + nameCourse;
    }
}
