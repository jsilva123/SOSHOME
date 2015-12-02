/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.command;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.RandomStringUtils;

import soshome.dao.PedidoservicoJpaController;
import soshome.dao.TiposervicoJpaController;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Tiposervico;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class GenerateTipoServicoQtd implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        Properties properties = new Properties();
        ArrayList<ModeloRelatorio> lista = new ArrayList();

        String path = "";
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_PATH));
            path = properties.getProperty("report_dir");
        } catch (IOException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        TiposervicoJpaController tipoDao = new TiposervicoJpaController(factory);
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        ModeloRelatorio mr;

        List<Tiposervico> p = tipoDao.findTiposervicoEntities();
        InputStream jasperTemplate = this.getClass().getResourceAsStream("/soshome/util/PedidosRelatorio.jrxml");
   
        List models = new ArrayList();
       
        for(int i = 0;i<p.size();i++)
        {
            if(p.get(i).getIdtiposervico()!= null)
            {
            models.add(new ModeloRelatorio((long)pedidoDao.findCountTiposervico(p.get(i)), p.get(i).getNome()));
            }
        }
        
        
        JasperReport report = null;
        try {
            report = JasperCompileManager.compileReport(jasperTemplate);

        } catch (JRException ex) {
            Logger.getLogger(GenerateReport.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        JasperPrint print = null;
        try {
            print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(models, false));

        } catch (JRException ex) {
            Logger.getLogger(GenerateReport.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String rs = RandomStringUtils.randomAlphanumeric(10);
        try {
            JasperExportManager.exportReportToPdfFile(print, path + "\\" + "Quantidade Por Tipo " + rs + ".pdf");

        } catch (JRException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);

        } catch (Exception ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("msg", true);
        request.setAttribute("msg_txt", "Relat&oacute;rio gerado com sucesso!");

        return "ServletController?cmd=soshome.model.command.LoadPedidosCliente&id=" + request.getSession().getAttribute("id");

    }

    public class ModeloRelatorio {

        String nome;
        Long count;
    

        public ModeloRelatorio(long count,String nome) {
            this.nome = nome;
            this.count = count;
            

        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

    }

}
