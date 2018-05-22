package com.goropai.command.impl;

import com.goropai.command.Command;
import com.goropai.command.CommandHelper;
import com.goropai.entity.User;
import com.goropai.manager.Config;
import com.goropai.manager.Message;
import com.goropai.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SESSION_TIME = Config.getInstance().getProperty(Config.SESSION_TIME);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        int sessionTime = Integer.parseInt(SESSION_TIME);
        session.setMaxInactiveInterval(sessionTime);
        String page;
        User user;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserService.getInstance();
        CommandHelper commandHelper = CommandHelper.getInstance();
        if ((user = userService.findLecturer(login, password)) != null) {
            commandHelper.setLecturerPage(session, user);
            page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
        } else if ((user = userService.findStudent(login, password)) != null) {
            commandHelper.setStudentPage(session, user);
            page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.LOGIN);
        }

        return page;
    }
}
