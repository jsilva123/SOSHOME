/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.PedidoservicoJpaController;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Pedidoservico;
import soshome.util.Constants;

/**
 *
 * @author Admin
 */
public class LoadPedidoProfissional implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidosDao = new PedidoservicoJpaController(factory);
        Pedidoservico pedido = pedidosDao.findPedidoservico(Integer.parseInt(request.getParameter("id")));
        if (!pedido.getStatus().equals(Constants.STATUS_PEDIDO_CRIADO)) {
            request.setAttribute("profissional", pedido.getIdprofissional());
        }
        request.setAttribute("pedido", pedido);
        return "LoadPedido.jsp";
    }

}
