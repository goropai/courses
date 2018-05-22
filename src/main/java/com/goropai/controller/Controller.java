package com.goropai.controller;

import com.goropai.command.Command;
import com.goropai.manager.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class Controller extends HttpServlet {

    private ControllerHelper controllerHelper = ControllerHelper.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getSimpleName());


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        try {
            Command command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            LOGGER.warning("Exception: " + e.getMessage());
            request.setAttribute("messageError", Message.getInstance().getProperty(Message.SERVLET_EXCEPTION));

        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
            request.setAttribute("messageError", Message.getInstance().getProperty(Message.IO_EXCEPTION));

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

}
