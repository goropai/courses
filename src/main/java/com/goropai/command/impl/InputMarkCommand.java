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

public class InputMarkCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";
    private static final String MARK = "mark";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        String mark = request.getParameter(MARK);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = CourseService.getInstance();
        UserService userService = UserService.getInstance();
        if (user != null) {
            if (courseService.insertMark(courseId, userId, mark)) {
                String login = user.getLogin();
                String password = user.getPassword();
                CommandHelper commandHelper = CommandHelper.getInstance();
                user = userService.findLecturer(login, password);
                commandHelper.setLecturerPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
            } else {
                request.getSession().setAttribute("error", Message.getInstance().getProperty(Message.INPUT_MARK_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
                }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
