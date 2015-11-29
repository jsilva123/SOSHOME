/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.model.bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Jorge
 */
@Embeddable
public class ServicoprestadoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idprofissional")
    private int idprofissional;
    @Basic(optional = false)
    @Column(name = "idpedidoservico")
    private int idpedidoservico;

    public ServicoprestadoPK() {
    }

    public ServicoprestadoPK(int idprofissional, int idpedidoservico) {
        this.idprofissional = idprofissional;
        this.idpedidoservico = idpedidoservico;
    }

    public int getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(int idprofissional) {
        this.idprofissional = idprofissional;
    }

    public int getIdpedidoservico() {
        return idpedidoservico;
    }

    public void setIdpedidoservico(int idpedidoservico) {
        this.idpedidoservico = idpedidoservico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idprofissional;
        hash += (int) idpedidoservico;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicoprestadoPK)) {
            return false;
        }
        ServicoprestadoPK other = (ServicoprestadoPK) object;
        if (this.idprofissional != other.idprofissional) {
            return false;
        }
        if (this.idpedidoservico != other.idpedidoservico) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soshome.model.bean.ServicoprestadoPK[ idprofissional=" + idprofissional + ", idpedidoservico=" + idpedidoservico + " ]";
    }

}
