package com.goropai.dao;

import com.goropai.entity.UserType;

import java.util.List;

public interface UserTypeDAO {

    /**
     * Method finds all user types in database
     * @return list of all user types
     */
    List<UserType> findAll();

    /**
     * Method insert user type in database
     * @param userType type of user (student, lecturer)
     * @return userTypeId of inserted user type
     */
    boolean insertUserType(UserType userType);

    /**
     * Method finds user type by its id
     * @param userTypeId userType's id
     * @return userType
     */
    UserType findUserTypeWithUserTypeId(Long userTypeId);

    /**
     * Method finds user type by its name
     * @param userTypeName userType's name
     * @return userType
     */
    UserType findUserTypeWithUserTypeName(String userTypeName);

    /**
     * Method updates user type in database
     * @param userType userType
     * @return true if update was successful, false if it was not
     */
    boolean updateUserType(UserType userType);
}
