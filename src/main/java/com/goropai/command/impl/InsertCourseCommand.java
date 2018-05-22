package com.goropai.command.impl;

import com.goropai.command.Command;
import com.goropai.command.CommandHelper;
import com.goropai.entity.User;
import com.goropai.manager.Config;
import com.goropai.manager.Message;
import com.goropai.service.CourseService;
import com.goropai.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCourseCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String courseName = request.getParameter("newNameCourse");
        String userId = request.getParameter("userId");
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = CourseService.getInstance();
        UserService userService = UserService.getInstance();
        if (user != null) {
            CommandHelper commandHelper = CommandHelper.getInstance();
            String login = user.getLogin();
            String password = user.getPassword();
            if (courseService.insertCourse(courseName, userId)) {
                if ((user = userService.findLecturer(login, password)) != null)
                    commandHelper.setLecturerPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
            } else {
                request.getSession().setAttribute("error", Message.getInstance().getProperty(Message.COURSE_NAME_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
