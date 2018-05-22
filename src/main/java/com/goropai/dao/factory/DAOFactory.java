package com.goropai.dao.factory;

import com.goropai.dao.CourseDAO;
import com.goropai.dao.UserDAO;
import com.goropai.dao.UserStoreDAO;
import com.goropai.dao.UserTypeDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;

    public abstract CourseDAO getCourseDAO();

    public abstract UserDAO getUserDAO();

    public abstract UserStoreDAO getUserStoreDAO();

    public abstract UserTypeDAO getUserTypeDAO();

    public static DAOFactory getDAOFactory(int whatFactory) {
        switch (whatFactory) {
            case MYSQL:
                return new MySqlDAOFactory();
            default:
                return null;
        }
    }
}
