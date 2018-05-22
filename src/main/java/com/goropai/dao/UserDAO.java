package com.goropai.dao;

import com.goropai.entity.User;

import java.util.List;

public interface UserDAO {

    /**
     * Method finds all users in database
     * @return list of all users
     */
    List<User> findAll();

    /**
     * Method inserts user in database
     * @param user user
     * @return true if insertion was successful, false if it was not
     */
    boolean insertUser(User user);

    /**
     * Method finds user by its id
     * @param userId user's id
     * @return user
     */
    User findUserWithUserId(Long userId);

    /**
     * Method updates user in database
     * @param user user
     * @return true if update was successful, false if it was not
     */
    boolean updateUser(User user);

    /**
     * Method finds user by its login
     * @param login user's login
     * @return user
     */
    User findWhereUserLoginEquals(String login);

    /**
     * Method finds lecturer by its login and password
     * @param login lecturer's login
     * @param password lecturer's password
     * @return lecturer
     */
    User findLecturerWithLoginAndPassword(String login, String password);

    /**
     * Method finds student by its login and password
     * @param login student's login
     * @param password student's password
     * @return student
     */
    User findStudentWithLoginAndPassword(String login, String password);
}
