package com.goropai.dao;

import com.goropai.entity.Course;
import com.goropai.entity.User;

import java.util.List;

public interface CourseDAO {

    /**
     * Method finds all courses in database
     * @return list of all courses
     */
    List<Course> findAll();

    /**
     * Method inserts course into database
     * @param course course
     * @return true if insertion was successful, false if it was not
     */
    boolean insertCourse(Course course);

    /**
     * Method finds course by its id
     * @param courseId course's id
     * @return course
     */
    Course findCourseWithCourseId(Long courseId);

    /**
     * Method finds course by its name
     * @param courseName courseName of course
     * @return course
     */
    Course findCourseWithCourseName(String courseName);

    /**
     * Method updates course in database
     * @param course course
     * @return true if update was successful, false if it was not
     */
    boolean updateCourse(Course course);

    /**
     * Method finds list of students who are studying on it
     * @param courseId course's id
     * @return list of students
     */
    List<User> findWhereUserIsStudent(Long courseId);

    /**
     * Method finds all courses of user by user's id
     * @param userId user's id
     * @return list of courses
     */
    List<Course> findStudentCourses(Long userId);

    /**
     * Method removes course from database by its id
     * @param courseId course's id
     * @return true if removing was successful, false if it was not
     */
    boolean removeCourseByCourseId(Long courseId);
}
