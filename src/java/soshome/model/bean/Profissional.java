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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "profissional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profissional.findAll", query = "SELECT p FROM Profissional p"),
    @NamedQuery(name = "Profissional.findByIdprofissional", query = "SELECT p FROM Profissional p WHERE p.idprofissional = :idprofissional"),
    @NamedQuery(name = "Profissional.findByIdprofissional", query = "SELECT p FROM Profissional p WHERE p.idprofissional = :idprofissional"),
    @NamedQuery(name = "Profissional.findByNome", query = "SELECT p FROM Profissional p WHERE p.nome = :nome"),
    @NamedQuery(name = "Profissional.findByCpf", query = "SELECT p FROM Profissional p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Profissional.findByTelefone", query = "SELECT p FROM Profissional p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Profissional.findByEndereco", query = "SELECT p FROM Profissional p WHERE p.endereco = :endereco")})
public class Profissional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofissional")
    private Integer idprofissional;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "endereco")
    private String endereco;
    @OneToMany(mappedBy = "idprofissional")
    private List<Pedidoservico> pedidoservicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profissional")
    private List<Agenda> agendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profissional")
    private List<Servicoprestado> servicoprestadoList;

  
    public Profissional() {
    }

    public Profissional(Integer idprofissional) {
        this.idprofissional = idprofissional;
    }

  

    public Profissional(Integer idprofissional, String nome, String cpf, String telefone) {
        this.idprofissional = idprofissional;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Integer getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(Integer idprofissional) {
        this.idprofissional = idprofissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    @XmlTransient
    public List<Pedidoservico> getPedidoservicoList() {
        return pedidoservicoList;
    }

    public void setPedidoservicoList(List<Pedidoservico> pedidoservicoList) {
        this.pedidoservicoList = pedidoservicoList;
    }

    @XmlTransient
    public List<Agenda> getAgendaList() {
        return agendaList;
    }

    public void setAgendaList(List<Agenda> agendaList) {
        this.agendaList = agendaList;
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
        hash += (idprofissional != null ? idprofissional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissional)) {
            return false;
        }
        Profissional other = (Profissional) object;
        if ((this.idprofissional == null && other.idprofissional != null) || (this.idprofissional != null && !this.idprofissional.equals(other.idprofissional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soshome.model.bean.Profissional[ idprofissional=" + idprofissional + " ]";
    }



}
