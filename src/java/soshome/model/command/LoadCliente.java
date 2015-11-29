/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.ClienteJpaController;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Cliente;

/**
 *
 * @author Admin
 */
public class LoadCliente implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        ClienteJpaController clienteDao = new ClienteJpaController(factory);
        Cliente cliente = clienteDao.findCliente(1);
        request.setAttribute("cliente", cliente);
        return "LoadCliente.jsp";
    }

}
