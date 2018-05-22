package com.goropai.dao.impl;

import com.goropai.connection.ConnectionPool;
import com.goropai.dao.UserTypeDAO;
import com.goropai.entity.User;
import com.goropai.entity.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySqlUserTypeDAO implements UserTypeDAO {

    private static MySqlUserTypeDAO instance;

    private static final int COLUMN_USER_TYPE_ID = 1;
    private static final int COLUMN_USER_TYPE = 2;
    private static final Logger LOGGER = Logger.getLogger(MySqlUserTypeDAO.class.getSimpleName());

    private MySqlUserTypeDAO() {
    }

    public static synchronized MySqlUserTypeDAO getInstance() {
        if (instance == null) {
            instance = new MySqlUserTypeDAO();
        }
        return instance;
    }

    @Override
    public List<UserType> findAll() {
        List<UserType> userTypes = new ArrayList<>();
        final String sql = "SELECT * FROM USER_TYPE;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                UserType userType = new UserType();
                long userTypeId = resultSet.getLong(COLUMN_USER_TYPE_ID);
                userType.setUserTypeId(userTypeId);
                userType.setUserTypeName(resultSet.getString(COLUMN_USER_TYPE));
                setUserListToUserType(userTypeId, userType);
                userTypes.add(userType);
                LOGGER.info("List of all user types was found");

            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return userTypes;
    }

    @Override
    public boolean insertUserType(UserType userType) {
        final String sql = "INSERT INTO USER_TYPE(user_type) values(?);";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userType.getUserTypeName());
            preparedStatement.executeUpdate();
            LOGGER.info("User types was inserted " + userType.getUserTypeName());
            return true;
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return false;
    }

    @Override
    public UserType findUserTypeWithUserTypeId(Long userTypeId) {
        final String sql = "SELECT * FROM USER_TYPE WHERE user_type_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userTypeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserType findUserType = new UserType();
                    findUserType.setUserTypeId(userTypeId);
                    findUserType.setUserTypeName(resultSet.getString(COLUMN_USER_TYPE));
                    setUserListToUserType(userTypeId, findUserType);
                    LOGGER.info("User type was found by userTypeId " + userTypeId);
                    return findUserType;
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
    public UserType findUserTypeWithUserTypeName(String userTypeName) {
        final String sql = "SELECT * FROM USER_TYPE WHERE user_type=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userTypeName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    UserType findUserType = new UserType();
                    long userTypeId = resultSet.getLong(COLUMN_USER_TYPE_ID);
                    findUserType.setUserTypeId(userTypeId);
                    findUserType.setUserTypeName(resultSet.getString(COLUMN_USER_TYPE));
                    setUserListToUserType(userTypeId, findUserType);
                    LOGGER.info("User type was found by userTypeName " + userTypeName);
                    return findUserType;
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
    public boolean updateUserType(UserType userType) {
        long userTypedId = userType.getUserTypeId();
        final String sql = "UPDATE USER_TYPE SET user_type=? WHERE user_type_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userType.getUserTypeName());
            preparedStatement.setLong(2, userTypedId);
            int code = preparedStatement.executeUpdate();
            if (code != 0) {
                LOGGER.info("User type was updated " + userType.getUserTypeName());
                return true;
            } else {
                LOGGER.info("Nothing was updated " + userType.getUserTypeName());
                return false;
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }

        return false;
    }

    private void setUserListToUserType(Long userTypeId, UserType userType) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USER WHERE user_type_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userTypeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getLong("user_id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        LOGGER.info("Users added to user type with name " + userType.getUserTypeName());
        userType.setUsers(users);
    }
}
