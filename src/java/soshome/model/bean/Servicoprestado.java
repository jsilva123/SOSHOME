/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "servicoprestado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicoprestado.findAll", query = "SELECT s FROM Servicoprestado s"),
    @NamedQuery(name = "Servicoprestado.findByIdprofissional", query = "SELECT s FROM Servicoprestado s WHERE s.servicoprestadoPK.idprofissional = :idprofissional"),
    @NamedQuery(name = "Servicoprestado.findByValorbase", query = "SELECT s FROM Servicoprestado s WHERE s.valorbase = :valorbase"),
    @NamedQuery(name = "Servicoprestado.findByTurno", query = "SELECT s FROM Servicoprestado s WHERE s.turno = :turno"),
    @NamedQuery(name = "Servicoprestado.findByIdpedidoservico", query = "SELECT s FROM Servicoprestado s WHERE s.servicoprestadoPK.idpedidoservico = :idpedidoservico")})
public class Servicoprestado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ServicoprestadoPK servicoprestadoPK;
    @Basic(optional = false)
    @Column(name = "valorbase")
    private float valorbase;
    @Column(name = "turno")
    private String turno;
    @JoinColumn(name = "idpedidoservico", referencedColumnName = "idpedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedidoservico pedidoservico;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profissional profissional;
    @JoinColumn(name = "idtiposervico", referencedColumnName = "idtiposervico")
    @ManyToOne(optional = false)
    private Tiposervico idtiposervico;

    public Servicoprestado() {
    }

    public Servicoprestado(ServicoprestadoPK servicoprestadoPK) {
        this.servicoprestadoPK = servicoprestadoPK;
    }

    public Servicoprestado(ServicoprestadoPK servicoprestadoPK, float valorbase) {
        this.servicoprestadoPK = servicoprestadoPK;
        this.valorbase = valorbase;
    }

    public Servicoprestado(int idprofissional, int idpedidoservico) {
        this.servicoprestadoPK = new ServicoprestadoPK(idprofissional, idpedidoservico);
    }

    public ServicoprestadoPK getServicoprestadoPK() {
        return servicoprestadoPK;
    }

    public void setServicoprestadoPK(ServicoprestadoPK servicoprestadoPK) {
        this.servicoprestadoPK = servicoprestadoPK;
    }

    public float getValorbase() {
        return valorbase;
    }

    public void setValorbase(float valorbase) {
        this.valorbase = valorbase;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Pedidoservico getPedidoservico() {
        return pedidoservico;
    }

    public void setPedidoservico(Pedidoservico pedidoservico) {
        this.pedidoservico = pedidoservico;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Tiposervico getIdtiposervico() {
        return idtiposervico;
    }

    public void setIdtiposervico(Tiposervico idtiposervico) {
        this.idtiposervico = idtiposervico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicoprestadoPK != null ? servicoprestadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicoprestado)) {
            return false;
        }
        Servicoprestado other = (Servicoprestado) object;
        if ((this.servicoprestadoPK == null && other.servicoprestadoPK != null) || (this.servicoprestadoPK != null && !this.servicoprestadoPK.equals(other.servicoprestadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soshome.model.bean.Servicoprestado[ servicoprestadoPK=" + servicoprestadoPK + " ]";
    }

}
