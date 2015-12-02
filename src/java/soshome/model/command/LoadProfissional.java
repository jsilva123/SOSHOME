/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.ProfissionalJpaController;
import soshome.model.bean.Profissional;
import soshome.model.connection.ConnectionFactory;

/**
 *
 * @author Jorge
 */
public class LoadProfissional implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        ProfissionalJpaController profissionalDao = new ProfissionalJpaController(factory);
        Profissional profissional = profissionalDao.findProfissional(Integer.parseInt(request.getParameter("idProfissional")));
        request.setAttribute("profissional", profissional);
        return "LoadProfissional.jsp";
    }
}
