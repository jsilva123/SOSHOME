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
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.bean.Pedidoservico;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class FinalizePedidoServico implements InterfaceCommand{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        Pedidoservico pedido = pedidoDao.findPedidoservico(id);
        if(!request.getParameter("nota").equals(""))
        pedido.setAvaliacao(Integer.parseInt(request.getParameter("nota")));
        pedido.setStatus(Constants.STATUS_PEDIDO_FINALIZADO);

        try {
            pedidoDao.edit(pedido);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ConfirmPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente&id='" + pedido.getIdcliente().getIdcliente() + "'";
    }
}
