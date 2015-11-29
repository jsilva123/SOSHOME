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
@Table(name = "tiposervico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposervico.findAll", query = "SELECT t FROM Tiposervico t"),
    @NamedQuery(name = "Tiposervico.findByIdtiposervico", query = "SELECT t FROM Tiposervico t WHERE t.idtiposervico = :idtiposervico"),
    @NamedQuery(name = "Tiposervico.findByNome", query = "SELECT t FROM Tiposervico t WHERE t.nome = :nome"),
    @NamedQuery(name = "Tiposervico.findByDescricao", query = "SELECT t FROM Tiposervico t WHERE t.descricao = :descricao")})
public class Tiposervico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtiposervico")
    private Integer idtiposervico;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtiposervico")
    private List<Pedidoservico> pedidoservicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtiposervico")
    private List<Servicoprestado> servicoprestadoList;

    public Tiposervico() {
    }

    public Tiposervico(Integer idtiposervico) {
        this.idtiposervico = idtiposervico;
    }

    public Tiposervico(Integer idtiposervico, String nome, String descricao) {
        this.idtiposervico = idtiposervico;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdtiposervico() {
        return idtiposervico;
    }

    public void setIdtiposervico(Integer idtiposervico) {
        this.idtiposervico = idtiposervico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Pedidoservico> getPedidoservicoList() {
        return pedidoservicoList;
    }

    public void setPedidoservicoList(List<Pedidoservico> pedidoservicoList) {
        this.pedidoservicoList = pedidoservicoList;
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
        hash += (idtiposervico != null ? idtiposervico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposervico)) {
            return false;
        }
        Tiposervico other = (Tiposervico) object;
        if ((this.idtiposervico == null && other.idtiposervico != null) || (this.idtiposervico != null && !this.idtiposervico.equals(other.idtiposervico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soshome.model.bean.Tiposervico[ idtiposervico=" + idtiposervico + " ]";
    }

}
