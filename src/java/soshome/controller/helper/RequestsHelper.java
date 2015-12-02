/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.controller.helper;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import soshome.model.command.InterfaceCommand;

public class RequestsHelper {

    private HttpServletRequest request;
    private String parameter;

    public RequestsHelper() {
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public InterfaceCommand getCommand() throws ServletException {
        InterfaceCommand command = null;

        try {
            Class<?> classe = Class.forName(parameter);
            command = (InterfaceCommand) classe.newInstance();
            return command;
        } catch (Exception e) {
            throw new ServletException("A l&oacute;gica de negocio causou uma exce&ccedil;&atilde;o", e);
        }
    }

}
