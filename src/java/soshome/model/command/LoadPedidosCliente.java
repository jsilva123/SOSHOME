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
import soshome.dao.PedidoservicoJpaController;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Pedidoservico;
import soshome.util.Constants;

/**
 *
 * @author Admin
 */
public class LoadPedidosCliente implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidosDao = new PedidoservicoJpaController(factory);
        ClienteJpaController clienteDao = new ClienteJpaController(factory);

        String test = request.getParameter("id");
        
        List<Pedidoservico> pedidosCriados = pedidosDao.findByStatusCliente(Constants.STATUS_PEDIDO_CRIADO, clienteDao.findCliente(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosRespondidos = pedidosDao.findByStatusCliente(Constants.STATUS_PEDIDO_RESPONDIDO, clienteDao.findCliente(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosConfirmados = pedidosDao.findByStatusCliente(Constants.STATUS_PEDIDO_CONFIRMADO, clienteDao.findCliente(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosAceitos = pedidosDao.findByStatusCliente(Constants.STATUS_PEDIDO_ACEITO, clienteDao.findCliente(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosFinalizados = pedidosDao.findByStatusCliente(Constants.STATUS_PEDIDO_FINALIZADO, clienteDao.findCliente(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));

        request.setAttribute("pedidosCriados", pedidosCriados);
        request.setAttribute("pedidosRespondidos", pedidosRespondidos);
        request.setAttribute("pedidosConfirmados", pedidosConfirmados);
        request.setAttribute("pedidosAceitos", pedidosAceitos);
        request.setAttribute("pedidosFinalizados", pedidosFinalizados);

        return "LoadPedidosList.jsp";
    }

}
