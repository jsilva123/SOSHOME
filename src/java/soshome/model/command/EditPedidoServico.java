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
import soshome.dao.TiposervicoJpaController;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Tiposervico;

/**
 *
 * @author Admin
 */
public class EditPedidoServico implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        TiposervicoJpaController tipoDao = new TiposervicoJpaController(factory);

        Pedidoservico pedido = pedidoDao.findPedidoservico(id);
        Tiposervico tiposervico = tipoDao.findTiposervico(Integer.parseInt(request.getParameter("selectTipo")));
        pedido.setIdtiposervico(tiposervico);

        if (request.getParameter("emergenciaSelect") == null) {
            pedido.setFlagemergencia('F');

        } else {
            pedido.setFlagemergencia('T');
        }
        pedido.setTurnosolicitado(request.getParameter("selectTurno"));
        try {
            pedidoDao.edit(pedido);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente";
    }

}
