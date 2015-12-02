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
import soshome.dao.PedidoservicoJpaController;
import soshome.dao.ProfissionalJpaController;
import soshome.model.bean.Pedidoservico;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class LoadPedidosProfissional implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidosDao = new PedidoservicoJpaController(factory);
        ProfissionalJpaController profissionalDao = new ProfissionalJpaController(factory);

        List<Pedidoservico> pedidosCriados = pedidosDao.findCriados();
        List<Pedidoservico> pedidosRespondidos = pedidosDao.findByStatusProfissional(Constants.STATUS_PEDIDO_RESPONDIDO, profissionalDao.findProfissional(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosAceitos = pedidosDao.findByStatusProfissional(Constants.STATUS_PEDIDO_ACEITO, profissionalDao.findProfissional(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosConfirmados = pedidosDao.findByStatusProfissional(Constants.STATUS_PEDIDO_CONFIRMADO, profissionalDao.findProfissional(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));
        List<Pedidoservico> pedidosFinalizados = pedidosDao.findByStatusProfissional(Constants.STATUS_PEDIDO_FINALIZADO, profissionalDao.findProfissional(Integer.parseInt(request.getParameter("id").replaceAll("'", ""))));

        request.setAttribute("pedidosCriados", pedidosCriados);
        request.setAttribute("pedidosRespondidos", pedidosRespondidos);
        request.setAttribute("pedidosAceitos", pedidosAceitos);
        request.setAttribute("pedidosConfirmados", pedidosConfirmados);
        request.setAttribute("pedidosFinalizados", pedidosFinalizados);

        return "LoadPedidosList.jsp";
    }

}
