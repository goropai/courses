package com.goropai.dao.factory;

import com.goropai.dao.CourseDAO;
import com.goropai.dao.UserDAO;
import com.goropai.dao.UserStoreDAO;
import com.goropai.dao.UserTypeDAO;
import com.goropai.dao.impl.MySqlCourseDAO;
import com.goropai.dao.impl.MySqlUserDAO;
import com.goropai.dao.impl.MySqlUserStoreDAO;
import com.goropai.dao.impl.MySqlUserTypeDAO;

public class MySqlDAOFactory extends DAOFactory {

    @Override
    public CourseDAO getCourseDAO() {
        return MySqlCourseDAO.getInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        return MySqlUserDAO.getInstance();
    }

    @Override
    public UserStoreDAO getUserStoreDAO() {
        return MySqlUserStoreDAO.getInstance();
    }

    @Override
    public UserTypeDAO getUserTypeDAO() {
        return MySqlUserTypeDAO.getInstance();
    }
}
