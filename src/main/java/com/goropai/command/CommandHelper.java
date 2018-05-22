package com.goropai.command;

import com.goropai.entity.User;
import com.goropai.manager.Config;
import com.goropai.service.UserService;

import javax.servlet.http.HttpSession;

public class CommandHelper {

    private static CommandHelper instance;

    private CommandHelper() {
    }

    public static CommandHelper getInstance() {
        if (instance == null) {
            instance = new CommandHelper();
        }
        return instance;
    }

    public void setLecturerPage(HttpSession session, User user) {
        UserService userService = UserService.getInstance();
        session.setAttribute("user", user);
        session.setAttribute("courses", user.getCourseStore());
        session.setAttribute("students", userService.getStudentsWhereCourse(user.getCourseStore()));
    }

    public void setStudentPage(HttpSession session, User user) {
        UserService userService = UserService.getInstance();
        session.setAttribute("user", user);
        session.setAttribute("availableCourses",
                userService.getAvailableCourses());
        session.setAttribute("studentCourses",
                userService.getStudentCourses(user.getUserId()));
    }

    public String checkUser(HttpSession session, User user) {
        String page;
        UserService userService = UserService.getInstance();
        String login = user.getLogin();
        String password = user.getPassword();
        if ((user = userService.findLecturer(login, password)) != null) {
            setLecturerPage(session, user);
            page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
        } else {
            user = userService.findStudentByLogin(login);
            setStudentPage(session, user);
            page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
        }
        return page;
    }
}
