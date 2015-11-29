/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.ProfissionalJpaController;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Profissional;

/**
 *
 * @author Admin
 */
public class EditProfissional implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        ProfissionalJpaController profissionalDao = new ProfissionalJpaController(factory);
        Profissional profissional = profissionalDao.findProfissional(id);
        profissional.setEndereco(request.getParameter("enderecotextbox"));
        profissional.setTelefone(request.getParameter("teltextbox"));

        try {
            profissionalDao.edit(profissional);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("profissional", profissional);
        return "LoadProfissional.jsp";
    }

}
