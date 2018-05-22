package com.goropai.manager;

import java.util.ResourceBundle;

public class Message {
    private static Message instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXCEPTION = "SERVLET_EXCEPTION";
    public static final String IO_EXCEPTION = "IO_EXCEPTION";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String REGISTER_ERROR = "REGISTER_ERROR";
    public static final String COURSE_NAME_ERROR = "COURSE_NAME_ERROR";
    public static final String REMOVE_ERROR = "REMOVE_ERROR";
    public static final String JOIN_COURSE_ERROR = "JOIN_COURSE_ERROR";
    public static final String INPUT_MARK_ERROR = "INPUT_MARK_ERROR";
    public static final String NO_MARK = "NO_MARK";

    public static Message getInstance() {
        if (instance == null) {
            instance = new Message();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
