package com.goropai.service;

import com.goropai.dao.CourseDAO;
import com.goropai.dao.UserStoreDAO;
import com.goropai.dao.factory.DAOFactory;
import com.goropai.entity.Course;

import java.util.Objects;

public class CourseService {

    private static CourseService instance;

    private static final String nameCourseRegex = "^[A-Z][a-z\\s]{1,149}|[А-ЯІЇЁ][а-яіїё\\s]{1,149}$";
    private static final String markRegex = "^[1-5]$";
    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private CourseDAO courseDAO = Objects.requireNonNull(daoFactory).getCourseDAO();
    private UserStoreDAO userStoreDAO = daoFactory.getUserStoreDAO();

    private CourseService() {
    }

    public static CourseService getInstance() {
        if (instance == null) {
            instance = new CourseService();
        }
        return instance;
    }

    public boolean insertCourse(String nameCourse, String userId) {

        if (!checkNameCourse(nameCourse)) {
            return false;
        }

        Course findCourse = courseDAO.findCourseWithCourseName(nameCourse);
        if (findCourse != null) {
            return false;
        }

        Course course = new Course();
        course.setNameCourse(nameCourse);
        boolean insertResult = courseDAO.insertCourse(course);
        if (!insertResult) {
            return false;
        }
        Course findNewCourse = courseDAO.findCourseWithCourseName(nameCourse);
        userStoreDAO.insertStudentIntoCourse(findNewCourse.getCourseId(), Long.parseLong(userId));
        return true;
    }

    public boolean changeNameCourse(String nameCourse, String courseId) {
        Course findCourse = courseDAO.findCourseWithCourseId(Long.parseLong(courseId));
        if (findCourse == null) {
            return false;
        }
        if (!checkNameCourse(nameCourse)) {
            return false;
        }
        Course newCourse = new Course();
        newCourse.setCourseId(findCourse.getCourseId());
        newCourse.setNameCourse(nameCourse);
        courseDAO.updateCourse(newCourse);
        return true;
    }

    public boolean removeCourse(String courseId) {
        boolean result = userStoreDAO.removeCourse(Long.parseLong(courseId));
        if (!result) {
            return false;
        }
        result = courseDAO.removeCourseByCourseId(Long.parseLong(courseId));
        return result;
    }

    public Integer findMark(String courseId, String userId) {
        return userStoreDAO.findMark(Long.parseLong(courseId), Long.parseLong(userId));
    }

    public boolean insertMark(String courseId, String userId, String mark) {
        return checkMark(mark)
                && userStoreDAO.insertMark(Long.parseLong(courseId),
                Long.parseLong(userId), Integer.parseInt(mark));
    }


    public boolean removeStudent(String courseId, String userId) {
        return userStoreDAO.removeStudent(Long.parseLong(courseId), Long.parseLong(userId));
    }

    public boolean insertStudent(String courseId, String userId) {
        return userStoreDAO.insertStudentIntoCourse(Long.parseLong(courseId),
                Long.parseLong(userId));
    }

    //todo
    private boolean checkNameCourse(String nameCourse) {
        return (nameCourse.matches(nameCourseRegex));
    }

    private boolean checkMark(String mark) {
        return mark.matches(markRegex);
    }
}
