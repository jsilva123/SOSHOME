/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.model.connection.ConnectionFactory;
import soshome.dao.ClienteJpaController;
import soshome.dao.ProfissionalJpaController;
import soshome.model.bean.Cliente;
import soshome.model.bean.Profissional;

public class UserLogin implements InterfaceCommand {

    private ClienteJpaController clienteDao;
    private ProfissionalJpaController profissionalDao;

    public UserLogin() {

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente;
        Profissional profissional;
        String login = request.getParameter("login");
        if ((login == null) || (request.getParameter("password") == null)) {
            //request.setAttribute("msg", true);
            request.setAttribute("msg_txt", "Usu&aacute;rio e senha n&atilde;o podem ser nulos!");
            return "login.jsp";
        }

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        clienteDao = new ClienteJpaController(factory);
        profissionalDao = new ProfissionalJpaController(factory);
        cliente = clienteDao.findClienteByLogin(login);
        profissional = profissionalDao.findProfissionalByLogin(login);
        // System.out.println(user.getNome());
        //System.out.println(user.getLoginUser());
        //System.out.println(user.getSenha());
        //String password = Encrypt.encripta(request.getParameter("password"));

        if (cliente == null || !cliente.getCpf().equals(request.getParameter("password"))) {
            if (profissional == null || !profissional.getCpf().equals(request.getParameter("password"))) {
                request.setAttribute("msg", true);
                request.setAttribute("msg_txt", "Login ou senha inv&aacute;lido!");
                request.setAttribute("mensage", "Login ou senha inválido");
                return "login.jsp";
            }
        }

        String isCliente;
        String isProfissional;

        if (cliente != null) {
            request.getSession().setAttribute("user", cliente);
            isCliente = "true";
            isProfissional = "false";
            request.getSession().setAttribute("id", cliente.getIdcliente());
            request.getSession().setAttribute("nomeCliente", cliente.getNome());
        } else {
            request.getSession().setAttribute("user", profissional);
            isCliente = "false";
            isProfissional = "true";
            request.getSession().setAttribute("id", profissional.getIdprofissional());
        }
        request.getSession().setAttribute("isCliente", isCliente);
        request.getSession().setAttribute("isProfissional", isProfissional);

        return "ServletController?cmd=soshome.model.command.StartSession";
    }

}
