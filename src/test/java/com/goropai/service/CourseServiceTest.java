package com.goropai.service;

import org.junit.Assert;
import org.junit.Test;

public class CourseServiceTest {

    @Test
    public void testWrongInputCourseName() {
        CourseService courseService = CourseService.getInstance();
        String courseId = "1";
        String courseName = "course name";
        boolean result = courseService.changeNameCourse(courseName, courseId);
        Assert.assertFalse(result);
    }

    @Test
    public void testWrongInputMark() {
        CourseService courseService = CourseService.getInstance();
        String courseId = "1";
        String userId = "1";
        String mark = "7";
        boolean result = courseService.insertMark(courseId, userId, mark);
        Assert.assertFalse(result);
    }
}
