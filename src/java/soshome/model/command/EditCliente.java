/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.ClienteJpaController;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Cliente;

/**
 *
 * @author Admin
 */
public class EditCliente implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        ClienteJpaController clienteDao = new ClienteJpaController(factory);
        Cliente cliente = clienteDao.findCliente(id);
        cliente.setEndereco(request.getParameter("enderecotextbox"));
        cliente.setTelefone(request.getParameter("teltextbox"));

        try {
            clienteDao.edit(cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("cliente", cliente);
        return "LoadCliente.jsp";
    }

}
