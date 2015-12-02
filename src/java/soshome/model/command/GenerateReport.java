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
import soshome.model.bean.Pedidoservico;
import soshome.model.connection.ConnectionFactory;
import soshome.util.Constants;

/**
 *
 * @author Jorge
 */
public class GenerateReport implements InterfaceCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManagerFactory factory = ConnectionFactory.getEntityManagerFactory();
        Properties properties = new Properties();
        ArrayList<ListaMedias> lista = new ArrayList();

        String path = "";
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(Constants.PROPERTIES_FILE_PATH));
            path = properties.getProperty("report_dir");
        } catch (IOException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        PedidoservicoJpaController pedidoDao = new PedidoservicoJpaController(factory);
        ModeloRelatorio mr;

        List<Pedidoservico> p = pedidoDao.findPedidoservicoEntities();
        InputStream jasperTemplate = this.getClass().getResourceAsStream("/soshome/util/relatorio.jrxml");

        for (int i = 0; i < p.size(); i++) {
            double media = 0;
            double count = 0;
            if (null != p.get(i).getIdprofissional()) {
                if (!lista.isEmpty()) {
                    for (int j = 0; j < lista.size(); j++) {
                        if (p.get(i).getIdprofissional().getIdprofissional() == lista.get(j).getId()) {
                            break;
                        } else if (j == lista.size() - 1) {
                            for (int k = 0; k < p.size(); k++) {
                                if (p.get(k).getStatus().equals(Constants.STATUS_PEDIDO_FINALIZADO)) {
                                    if (p.get(i).getIdprofissional().getIdprofissional() == p.get(k).getIdprofissional().getIdprofissional()) {
                                        media += p.get(k).getAvaliacao();
                                        count++;
                                    }
                                }
                                if (k == p.size() - 1) {
                                    lista.add(new ListaMedias(p.get(i).getIdprofissional().getIdprofissional(), media / count));
                                }
                            }
                        }
                    }
                } else {
                    for (int k = 0; k < p.size(); k++) {
                        if (p.get(k).getStatus().equals(Constants.STATUS_PEDIDO_FINALIZADO)) {
                            if (p.get(i).getIdprofissional().getIdprofissional() == p.get(k).getIdprofissional().getIdprofissional()) {
                                media += p.get(k).getAvaliacao();
                                count++;
                            }
                        }
                        if (k == p.size() - 1) {
                            lista.add(new ListaMedias(p.get(i).getIdprofissional().getIdprofissional(), media / count));
                        }
                    }
                }

            }
        }
        List models = new ArrayList();
        for (int i = 0; i < p.size(); i++) {
            if (null != p.get(i).getIdprofissional()) {
                for (int j = 0; j < lista.size(); j++) {
                    if (p.get(i).getIdprofissional().getIdprofissional() == lista.get(j).id) {
                        models.add(new ModeloRelatorio(p.get(i).getIdprofissional().getNome(), p.get(i).getIdprofissional().getTelefone(), p.get(i).getIdprofissional().getEndereco(), new BigDecimal(lista.get(j).getMedia(), MathContext.DECIMAL64)));

                        lista.remove(j);
                    }

                }
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
            JasperExportManager.exportReportToPdfFile(print, path + "\\" + "Melhores Profissionais " + rs + ".pdf");

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
        String telefone;
        String endereco;
        BigDecimal avg;

        public ModeloRelatorio(String nome, String telefone, String endereco, BigDecimal avg) {
            this.nome = nome;
            this.telefone = telefone;
            this.endereco = endereco;
            this.avg = avg;

        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public BigDecimal getAvg() {
            return avg;

        }

        public void setAvg(BigDecimal avg) {
            this.avg = avg;

        }

    }

    public class ListaMedias {

        private int id;
        private double media;

        public ListaMedias(int id, double media) {
            this.id = id;
            this.media = media;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMedia() {
            return media;
        }

        public void setMedia(double media) {
            this.media = media;
        }

    }

}
