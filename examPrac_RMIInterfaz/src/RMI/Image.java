/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Beto Lafarc
 */
@Entity
@Table(name = "IMAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
    , @NamedQuery(name = "Image.findByIdimagen", query = "SELECT i FROM Image i WHERE i.idimagen = :idimagen")
    , @NamedQuery(name = "Image.findByFecha", query = "SELECT i FROM Image i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Image.findByIdcliente", query = "SELECT i FROM Image i WHERE i.idcliente = :idcliente")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDIMAGEN")
    private Integer idimagen;
    @Basic(optional = false)
    @Lob
    @Column(name = "URL")
    private String url;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "IDCLIENTE")
    private Integer idcliente;

    public Image() {
    }

    public Image(Integer idimagen) {
        this.idimagen = idimagen;
    }

    public Image(Integer idimagen, String url) {
        this.idimagen = idimagen;
        this.url = url;
    }

    public Integer getIdimagen() {
        return idimagen;
    }

    public void setIdimagen(Integer idimagen) {
        this.idimagen = idimagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idimagen != null ? idimagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.idimagen == null && other.idimagen != null) || (this.idimagen != null && !this.idimagen.equals(other.idimagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RMI.Image[ idimagen=" + idimagen + " ]";
    }
    
}
