package com.goropai.service;

import com.goropai.dao.CourseDAO;
import com.goropai.dao.UserDAO;
import com.goropai.dao.factory.DAOFactory;
import com.goropai.entity.Course;
import com.goropai.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserService {

    private static UserService instance;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getSimpleName());

    private static final String loginRegex = "^[a-zA-Z][a-zA-Z0-9-_.]{1,149}$";
    private static final String passwordRegex = "^[\\w-.]{2,50}$";
    private static final String nameAndSurnameRegex = "^[A-Z][a-z]{1,149}|[А-ЯІЇЁЄ][а-яіїёє]{1,149}$";
    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private UserDAO userDao = Objects.requireNonNull(daoFactory).getUserDAO();
    private CourseDAO courseDAO = daoFactory.getCourseDAO();


    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User findLecturer(String login, String password) {
        return userDao.findLecturerWithLoginAndPassword(login, password);
    }

    public User findStudent(String login, String password) {
        User user;
        if ((user = userDao.findStudentWithLoginAndPassword(login, hashPassword(password))) == null) {
            return null;
        }
        user.setPassword(hashPassword(password));
        return user;
    }

    public List<User> getStudentsWhereCourse(List<Course> courses) {
        if (courses.isEmpty()) {
            return new ArrayList<>();
        }
        return courseDAO.findWhereUserIsStudent(courses.get(0).getCourseId());
    }

    public List<Course> getAvailableCourses() {
        return courseDAO.findAll();
    }

    public List<Course> getStudentCourses(Long userId) {
        return courseDAO.findStudentCourses(userId);
    }

    public boolean inputStudent(String login, String password, String name, String surname) {
        User user = new User();
        if (!checkLoginAndPassword(login, password)
                || !checkNameAndSurname(name, surname)
                || userDao.findWhereUserLoginEquals(login) != null) {
            return false;
        }
        user.setLogin(login);
        user.setPassword(hashPassword(password));
        user.setName(name);
        user.setSurname(surname);
        return userDao.insertUser(user);
    }

    public User findStudentByLogin(String login) {
        return userDao.findWhereUserLoginEquals(login);
    }
    private boolean checkLoginAndPassword(String login, String password) {
        return (login.matches(loginRegex)
                && password.matches(passwordRegex));
    }

    private boolean checkNameAndSurname(String name, String surname) {
        return (name.matches(nameAndSurnameRegex)
                && surname.matches(nameAndSurnameRegex));
    }

    private String hashPassword(String password) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        }
        LOGGER.info("Password was hashed");
        return hashedPassword;
    }
}
