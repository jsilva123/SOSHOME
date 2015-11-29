/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.bean;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "agenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a"),
    @NamedQuery(name = "Agenda.findByIdprofissional", query = "SELECT a FROM Agenda a WHERE a.agendaPK.idprofissional = :idprofissional"),
    @NamedQuery(name = "Agenda.findByData", query = "SELECT a FROM Agenda a WHERE a.agendaPK.data = :data"),
    @NamedQuery(name = "Agenda.findByHora", query = "SELECT a FROM Agenda a WHERE a.agendaPK.hora = :hora"),
    @NamedQuery(name = "Agenda.findByStatus", query = "SELECT a FROM Agenda a WHERE a.status = :status")})
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AgendaPK agendaPK;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "idprofissional", referencedColumnName = "idprofissional", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profissional profissional;

    public Agenda() {
    }

    public Agenda(AgendaPK agendaPK) {
        this.agendaPK = agendaPK;
    }

    public Agenda(AgendaPK agendaPK, String status) {
        this.agendaPK = agendaPK;
        this.status = status;
    }

    public Agenda(int idprofissional, Date data, Date hora) {
        this.agendaPK = new AgendaPK(idprofissional, data, hora);
    }

    public AgendaPK getAgendaPK() {
        return agendaPK;
    }

    public void setAgendaPK(AgendaPK agendaPK) {
        this.agendaPK = agendaPK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agendaPK != null ? agendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.agendaPK == null && other.agendaPK != null) || (this.agendaPK != null && !this.agendaPK.equals(other.agendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soshome.model.bean.Agenda[ agendaPK=" + agendaPK + " ]";
    }

}
