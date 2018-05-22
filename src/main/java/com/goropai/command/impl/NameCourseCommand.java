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

public class NameCourseCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String NEW_NAME_COURSE = "nameCourse";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String nameCourse = request.getParameter(NEW_NAME_COURSE);
        String courseId = request.getParameter(COURSE_ID);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = CourseService.getInstance();
        UserService userService = UserService.getInstance();
        if (user != null) {
            String login = user.getLogin();
            String password = user.getPassword();
            CommandHelper commandHelper = CommandHelper.getInstance();
            if (courseService.changeNameCourse(nameCourse, courseId)) {
                user = userService.findLecturer(login, password);
                commandHelper.setLecturerPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
            } else {
                request.setAttribute("error", Message.getInstance().getProperty(Message.COURSE_NAME_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
