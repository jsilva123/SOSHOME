/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import soshome.dao.ClienteJpaController;
import soshome.dao.PedidoservicoJpaController;
import soshome.dao.TiposervicoJpaController;
import soshome.model.bean.Cliente;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Tiposervico;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class CreatePedidoServico implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();

        ClienteJpaController clienteDao = new ClienteJpaController(factory);
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        TiposervicoJpaController tipoDao = new TiposervicoJpaController(factory);

        Cliente cliente = clienteDao.findCliente(Integer.parseInt(request.getParameter("id")));
        Tiposervico tiposervico = tipoDao.findTiposervico(Integer.parseInt(request.getParameter("selectTipo")));

        Pedidoservico pedido = new Pedidoservico();

        pedido.setIdcliente(cliente);
        pedido.setDescricao(request.getParameter("descricaoTextArea"));
        pedido.setIdtiposervico(tiposervico);

        pedido.setDatasolicitacao(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR));
        pedido.setStatus(Constants.STATUS_PEDIDO_CRIADO);
        if (request.getParameter("emergenciaSelect") == null) {
            pedido.setFlagemergencia('F');

        } else {
            pedido.setFlagemergencia('T');
        }
        pedido.setTurnosolicitado(request.getParameter("selectTurno"));
        pedidoDao.create(pedido);

        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente";
    }
}
