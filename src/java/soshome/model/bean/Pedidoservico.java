/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "pedidoservico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidoservico.findAll", query = "SELECT p FROM Pedidoservico p"),
    @NamedQuery(name = "Pedidoservico.findByIdpedido", query = "SELECT p FROM Pedidoservico p WHERE p.idpedido = :idpedido"),
    @NamedQuery(name = "Pedidoservico.findByDescricao", query = "SELECT p FROM Pedidoservico p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Pedidoservico.findByStatus", query = "SELECT p FROM Pedidoservico p WHERE p.status = :status"),
    @NamedQuery(name = "Pedidoservico.findByStatusCliente", query = "SELECT p FROM Pedidoservico p WHERE p.status = :status and p.idcliente = :idcliente"),
    @NamedQuery(name = "Pedidoservico.findByStatusProfissional", query = "SELECT p FROM Pedidoservico p WHERE p.status = :status and p.idprofissional = :idprofissional"),
    @NamedQuery(name = "Pedidoservico.findCountTipoServico", query = "SELECT COUNT(p.idtiposervico) FROM Pedidoservico p WHERE p.idtiposervico = :idtiposervico GROUP BY p.idtiposervico"),

    @NamedQuery(name = "Pedidoservico.findByDatasolicitacao", query = "SELECT p FROM Pedidoservico p WHERE p.datasolicitacao = :datasolicitacao"),
    @NamedQuery(name = "Pedidoservico.findByDataexecucao", query = "SELECT p FROM Pedidoservico p WHERE p.dataexecucao = :dataexecucao"),
    @NamedQuery(name = "Pedidoservico.findByAvaliacao", query = "SELECT p FROM Pedidoservico p WHERE p.avaliacao = :avaliacao"),
    @NamedQuery(name = "Pedidoservico.findByTurnosolicitado", query = "SELECT p FROM Pedidoservico p WHERE p.turnosolicitado = :turnosolicitado"),
    @NamedQuery(name = "Pedidoservico.findByFlagemergencia", query = "SELECT p FROM Pedidoservico p WHERE p.flagemergencia = :flagemergencia")})

public class Pedidoservico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Integer idpedido;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "datasolicitacao")
    private String datasolicitacao;
    @Column(name = "dataexecucao")
    private String dataexecucao;
    @Column(name = "avaliacao")
    private Integer avaliacao;
    @Basic(optional = false)
    @Column(name = "turnosolicitado")
    private String turnosolicitado;
    @Basic(optional = false)
    @Column(name = "flagemergencia")
    private Character flagemergencia;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional")
    @ManyToOne
    private Profissional idprofissional;
    @JoinColumn(name = "idtiposervico", referencedColumnName = "idtiposervico")
    @ManyToOne(optional = false)
    private Tiposervico idtiposervico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoservico")
    private List<Servicoprestado> servicoprestadoList;

    public Pedidoservico() {
    }

    public Pedidoservico(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedidoservico(Integer idpedido, String descricao, String status, String datasolicitacao, String turnosolicitado, Character flagemergencia) {
        this.idpedido = idpedido;
        this.descricao = descricao;
        this.status = status;
        this.datasolicitacao = datasolicitacao;
        this.turnosolicitado = turnosolicitado;
        this.flagemergencia = flagemergencia;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatasolicitacao() {
        return datasolicitacao;
    }

    public void setDatasolicitacao(String datasolicitacao) {
        this.datasolicitacao = datasolicitacao;
    }

    public String getDataexecucao() {
        return dataexecucao;
    }

    public void setDataexecucao(String dataexecucao) {
        this.dataexecucao = dataexecucao;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTurnosolicitado() {
        return turnosolicitado;
    }

    public void setTurnosolicitado(String turnosolicitado) {
        this.turnosolicitado = turnosolicitado;
    }

    public Character getFlagemergencia() {
        return flagemergencia;
    }

    public void setFlagemergencia(Character flagemergencia) {
        this.flagemergencia = flagemergencia;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Profissional getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Profissional idprofissional) {
        this.idprofissional = idprofissional;
    }

    public Tiposervico getIdtiposervico() {
        return idtiposervico;
    }

    public void setIdtiposervico(Tiposervico idtiposervico) {
        this.idtiposervico = idtiposervico;
    }

    @XmlTransient
    public List<Servicoprestado> getServicoprestadoList() {
        return servicoprestadoList;
    }

    public void setServicoprestadoList(List<Servicoprestado> servicoprestadoList) {
        this.servicoprestadoList = servicoprestadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidoservico)) {
            return false;
        }
        Pedidoservico other = (Pedidoservico) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soshome.model.bean.Pedidoservico[ idpedido=" + idpedido + " ]";
    }

}
