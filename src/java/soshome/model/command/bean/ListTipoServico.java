/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command.bean;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import soshome.dao.TiposervicoJpaController;
import soshome.model.bean.Tiposervico;
import soshome.model.connection.ConnectionFactory;

/**
 *
 * @author Jorge
 */
public class ListTipoServico {

    public List<String> getTipos() {
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        TiposervicoJpaController tipoServicoDao = new TiposervicoJpaController(factory);
        List<String> items = null;
        List<Tiposervico> tipo = tipoServicoDao.findTiposervicoEntities();
        for (int i = 0; i < tipo.size(); i++) {
            items.add(tipo.get(i).getNome());
        }
        return items;
    }

}
