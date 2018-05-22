package com.goropai.command.impl;

import com.goropai.command.Command;
import com.goropai.command.CommandHelper;
import com.goropai.entity.User;
import com.goropai.manager.Config;
import com.goropai.manager.Message;
import com.goropai.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = CourseService.getInstance();
        if (user != null) {
            CommandHelper commandHelper = CommandHelper.getInstance();
            if (courseService.removeStudent(courseId, userId)) {
                page = commandHelper.checkUser(request.getSession(), user);
            } else {
                request.setAttribute("error", Message.getInstance().getProperty(Message.REMOVE_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
