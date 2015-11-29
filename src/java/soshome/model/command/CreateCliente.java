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
public class CreateCliente implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        ClienteJpaController clienteDao = new ClienteJpaController(factory);
        Cliente cliente = new Cliente();

        cliente.setEndereco(request.getParameter("enderecotextbox"));
        cliente.setTelefone(request.getParameter("teltextbox"));
        cliente.setNome(request.getParameter("nometextbox"));
        cliente.setCpf(request.getParameter("cpftextbox"));

        clienteDao.create(cliente);

        request.setAttribute("cliente", cliente);
        return "LoadCliente.jsp";
    }

}
