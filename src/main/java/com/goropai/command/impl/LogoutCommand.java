package com.goropai.command.impl;

import com.goropai.command.Command;
import com.goropai.manager.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        request.setAttribute("language", session.getAttribute("language"));
        session.invalidate();
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
