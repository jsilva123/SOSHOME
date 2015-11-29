/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.ClienteJpaController;
import soshome.dao.PedidoservicoJpaController;
import soshome.dao.TiposervicoJpaController;
import soshome.dao.exceptions.IllegalOrphanException;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.bean.Cliente;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Tiposervico;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class DeletePedidoServico implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();

        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        try {
            pedidoDao.destroy(Integer.parseInt(request.getParameter("id")));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DeletePedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(DeletePedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente";
    }
}
