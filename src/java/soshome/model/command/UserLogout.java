/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.model.connection.ConnectionFactory;

/**
 *
 * @author Admin
 */
public class UserLogout implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
            if (ConnectionFactory.getEntityManager() != null) {
                ConnectionFactory.closeFactory();
            }
        }

        return "login.jsp";
    }

}
