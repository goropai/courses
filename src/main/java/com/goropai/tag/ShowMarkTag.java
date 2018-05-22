package com.goropai.tag;

import com.goropai.service.CourseService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.logging.Logger;

public class ShowMarkTag extends BodyTagSupport {

    private static final String NO_MARK = "No mark";
    private static final int ZERO = 0;
    private String userId;
    private String courseId;
    private CourseService courseService = CourseService.getInstance();
    private static final Logger LOGGER = Logger.getLogger(ShowMarkTag.class.getSimpleName());

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    @Override
    public int doStartTag() throws JspException {
        Integer mark = courseService.findMark(courseId, userId);
        try {
            if (mark.equals(ZERO)) {
                pageContext.getOut().print(NO_MARK);
            } else {
                pageContext.getOut().print(mark);
            }
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        }

        return super.doStartTag();
    }
}
