/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.PedidoservicoJpaController;
import soshome.dao.ProfissionalJpaController;
import soshome.dao.ServicoprestadoJpaController;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.connection.ConnectionFactory;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Servicoprestado;
import soshome.util.Constants;

/**
 *
 * @author Admin
 */
public class ClaimPedidoServico implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        int idProfissional = Integer.parseInt(request.getParameter("idProfissional").replaceAll("'", ""));

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        ProfissionalJpaController profissionalDao = new ProfissionalJpaController(factory);
        ServicoprestadoJpaController servicoDao = new ServicoprestadoJpaController(factory);

        Date parsed = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parsed = format.parse(request.getParameter("deadline"));
        } catch (ParseException ex) {
            Logger.getLogger(ClaimPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }

        Pedidoservico pedido = pedidoDao.findPedidoservico(idPedido);
        pedido.setIdprofissional(profissionalDao.findProfissional(idProfissional));
        pedido.setStatus(Constants.STATUS_PEDIDO_RESPONDIDO);
        pedido.setDataexecucao(parsed.getDay() + "/" + parsed.getMonth() + "/" + parsed.getYear());

        try {
            pedidoDao.edit(pedido);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ClaimPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClaimPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        Servicoprestado servico = new Servicoprestado();
        servico.setPedidoservico(pedido);
        servico.setProfissional(pedido.getIdprofissional());
        servico.setIdtiposervico(pedido.getIdtiposervico());
        servico.setValorbase((Math.round(Float.parseFloat(request.getParameter("vBase").replace(",", ".")) * 100)) / 100);
        servico.setTurno(pedido.getTurnosolicitado());

        try {
            servicoDao.create(servico);
        } catch (Exception ex) {
            Logger.getLogger(ClaimPedidoServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ServletController?cmd=soshome.model.command.LoadPedidosProfissional&id=" + idProfissional;
    }

}
