/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.web.end;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjalvin
 */
@Entity
@Table(name = "tb_universidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUniversidad.findAll", query = "SELECT t FROM TbUniversidad t"),
    @NamedQuery(name = "TbUniversidad.findById", query = "SELECT t FROM TbUniversidad t WHERE t.id = :id"),
    @NamedQuery(name = "TbUniversidad.findByName", query = "SELECT t FROM TbUniversidad t WHERE t.name = :name"),
    @NamedQuery(name = "TbUniversidad.findByDescription", query = "SELECT t FROM TbUniversidad t WHERE t.description = :description")})
public class TbUniversidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tbUniversidad", fetch = FetchType.EAGER)
    private TbEstudiante tbEstudiante;

    public TbUniversidad() {
    }

    public TbUniversidad(Integer id) {
        this.id = id;
    }

    public TbUniversidad(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TbEstudiante getTbEstudiante() {
        return tbEstudiante;
    }

    public void setTbEstudiante(TbEstudiante tbEstudiante) {
        this.tbEstudiante = tbEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUniversidad)) {
            return false;
        }
        TbUniversidad other = (TbUniversidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.api.web.end.TbUniversidad[ id=" + id + " ]";
    }
    
}
