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
import soshome.dao.ServicoprestadoJpaController;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Servicoprestado;
import soshome.model.bean.ServicoprestadoPK;
import soshome.util.Constants;

/**
 *
 * @author Admin
 */
public class LoadPedidoCliente implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidosDao = new PedidoservicoJpaController(factory);
        Pedidoservico pedido = pedidosDao.findPedidoservico(Integer.parseInt(request.getParameter("id")));
        ServicoprestadoJpaController servicoDao = new ServicoprestadoJpaController(factory);
        Servicoprestado servico = new Servicoprestado();

        List<Pedidoservico> pedidosProf = null;
        if (pedido.getIdprofissional() != null) {
            pedidosProf = pedidosDao.findByStatusProfissional(Constants.STATUS_PEDIDO_FINALIZADO, pedido.getIdprofissional());
            servico = servicoDao.findServicoprestado(new ServicoprestadoPK(pedido.getIdprofissional().getIdprofissional(), pedido.getIdpedido()));
        }
        int soma = 0;
        int media = 0;
        if (pedidosProf != null) {
            if (pedidosProf.size() > 0) {
                for (Pedidoservico item : pedidosProf) {
                    soma += item.getAvaliacao();
                }
                media = soma / pedidosProf.size();
            }
        }

        if (!pedido.getStatus().equals(Constants.STATUS_PEDIDO_CRIADO)) {
            request.setAttribute("profissional", pedido.getIdprofissional());
            request.setAttribute("media", media);
            request.setAttribute("servico", servico);
        }
        request.setAttribute("pedido", pedido);
        return "LoadPedido.jsp";
    }

}
