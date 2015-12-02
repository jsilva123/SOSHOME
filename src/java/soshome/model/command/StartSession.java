/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartSession implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute("isProfissional").equals("true")) {
            return "ServletController?cmd=soshome.model.command.LoadPedidosProfissional&id=" + request.getSession().getAttribute("id");
        }
        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente&id=" + request.getSession().getAttribute("id");
    }

}
