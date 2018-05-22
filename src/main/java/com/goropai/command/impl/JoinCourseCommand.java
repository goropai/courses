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

public class JoinCourseCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = CourseService.getInstance();
        UserService userService = UserService.getInstance();
        if (user != null) {
            String login = user.getLogin();
            CommandHelper commandHelper = CommandHelper.getInstance();
            if (courseService.insertStudent(courseId, userId)) {
                user = userService.findStudentByLogin(login);
                commandHelper.setStudentPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.STUDENT_MAIN);

            } else {
                request.setAttribute("error",
                        Message.getInstance().getProperty(Message.JOIN_COURSE_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
