package com.goropai.dao.impl;

import com.goropai.connection.ConnectionPool;
import com.goropai.dao.UserDAO;
import com.goropai.entity.Course;
import com.goropai.entity.User;
import com.goropai.entity.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySqlUserDAO implements UserDAO {

    private static MySqlUserDAO instance;

    private static final int COLUMN_USER_ID = 1;
    private static final int COLUMN_USER_TYPE_ID = 2;
    private static final int COLUMN_LOGIN = 3;
    private static final int COLUMN_PASSWORD = 4;
    private static final int COLUMN_NAME = 5;
    private static final int COLUMN_SURNAME = 6;
    private static final long USER_TYPE_LECTURER = 1L;
    private static final long USER_TYPE_STUDENT = 2L;
    private static final Logger LOGGER = Logger.getLogger(MySqlUserDAO.class.getSimpleName());

    private MySqlUserDAO() {
    }

    public static synchronized MySqlUserDAO getInstance() {
        if (instance == null) {
            instance = new MySqlUserDAO();
        }
        return instance;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        final String sql = "SELECT * FROM USER;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
            LOGGER.info("List of all users was found");
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return users;
    }

    @Override
    public boolean insertUser(User user) {
        final String sql = "INSERT INTO USER(login, password, name, surname, user_type_id) VALUES(?, ?, ?, ?, ?)";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setLong(5, USER_TYPE_STUDENT);
            int code = preparedStatement.executeUpdate();
            if (code != 0) {
                LOGGER.info("User was inserted " + user.getName());
                return true;
            } else {
                LOGGER.info("Nothing was inserted " + user.getName());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return false;
    }


    @Override
    public User findUserWithUserId(Long userId) {
        final String sql = "SELECT * FROM USER WHERE user_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info("User was found by userId " + userId);
                    return createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }


    @Override
    public boolean updateUser(User user) {
        long userId = user.getUserId();
        final String sql = "UPDATE USER SET login=?, password=?, name=?, surname=? WHERE user_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setLong(5, userId);
            preparedStatement.executeUpdate();
            LOGGER.info("User was updated " + user.getName());
            return true;
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return false;
    }

    @Override
    public User findWhereUserLoginEquals(String login) {
        final String sql = "SELECT * FROM USER WHERE login=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info("User was found by login " + login);
                    return createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }

    @Override
    public User findLecturerWithLoginAndPassword(String login, String password) {
        final String sql = "SELECT * FROM USER WHERE login=? AND password=? AND user_type_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, USER_TYPE_LECTURER);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = createUser(resultSet);
                    LOGGER.info("Lecturer was found by login and pass " + user.getLogin());
                    return user;
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }

    @Override
    public User findStudentWithLoginAndPassword(String login, String password) {
        final String sql = "SELECT * FROM USER WHERE login=? AND password=? AND user_type_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3, USER_TYPE_STUDENT);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = createUser(resultSet);
                    LOGGER.info("Student was found by login and pass " + user.getLogin());
                    return user;
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }


    private User createUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setUserId(resultSet.getLong(COLUMN_USER_ID));
            user.setLogin(resultSet.getString(COLUMN_LOGIN));
            user.setPassword(resultSet.getString(COLUMN_PASSWORD));
            user.setName(resultSet.getString(COLUMN_NAME));
            user.setSurname(resultSet.getString(COLUMN_SURNAME));
            setCourseListToUser(resultSet.getLong(COLUMN_USER_ID), user);
            setUserType(resultSet.getLong(COLUMN_USER_TYPE_ID), user);
            LOGGER.info("User was created " + user.getLogin());
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        }
        return user;
    }

    private void setCourseListToUser(Long userId, User user) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM COURSE WHERE course_id IN (SELECT course_id FROM USER_STORE WHERE user_id=?);";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    course.setCourseId(resultSet.getLong("course_id"));
                    course.setNameCourse(resultSet.getString("course_name"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        LOGGER.info("Courses were added to user " + user.getLogin());
        user.setCourseStore(courses);
    }


    private void setUserType(Long userTypeId, User user) {
        String sql = "SELECT user_type FROM USER_TYPE WHERE user_type_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userTypeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserType userType = new UserType();
                    userType.setUserTypeName(resultSet.getString("user_type"));
                    user.setUserType(userType);
                    LOGGER.info("User type was added for user" + user.getLogin());
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }
}
