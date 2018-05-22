package com.goropai.manager;

import java.util.ResourceBundle;

public class Config {

    private static Config instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "config";
    public static final String DRIVER = "DRIVER";
    public static final String URL = "URL";
    public static final String LECTURER_MAIN = "LECTURER_MAIN";
    public static final String STUDENT_MAIN = "STUDENT_MAIN";
    public static final String ERROR = "ERROR";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTER_ERROR = "REGISTER_ERROR";
    public static final String SESSION_TIME = "SESSION_TIME";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
