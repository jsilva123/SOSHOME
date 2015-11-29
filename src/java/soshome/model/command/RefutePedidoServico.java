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
import soshome.dao.PedidoservicoJpaController;
import soshome.dao.ServicoprestadoJpaController;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Servicoprestado;
import soshome.model.bean.ServicoprestadoPK;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class RefutePedidoServico implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idPedido"));

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        ServicoprestadoJpaController servicoDao = new ServicoprestadoJpaController(factory);

        Pedidoservico pedido = pedidoDao.findPedidoservico(id);
        Servicoprestado servico = servicoDao.findServicoprestado(new ServicoprestadoPK(pedido.getIdprofissional().getIdprofissional(), pedido.getIdpedido()));
        pedido.setIdprofissional(null);
        pedido.setStatus(Constants.STATUS_PEDIDO_CRIADO);

        try {
            pedidoDao.edit(pedido);
            servicoDao.destroy(servico.getServicoprestadoPK());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ConfirmPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente&id='" + pedido.getIdcliente().getIdcliente() + "'";
    }

}
