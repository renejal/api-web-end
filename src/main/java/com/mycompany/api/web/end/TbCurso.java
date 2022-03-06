/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.web.end;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bjalvin
 */
@Entity
@Table(name = "tb_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCurso.findAll", query = "SELECT t FROM TbCurso t"),
    @NamedQuery(name = "TbCurso.findById", query = "SELECT t FROM TbCurso t WHERE t.id = :id"),
    @NamedQuery(name = "TbCurso.findByName", query = "SELECT t FROM TbCurso t WHERE t.name = :name")})
public class TbCurso implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<TbEstudianteCurso> tbEstudianteCursoCollection;

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
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TbEstudiante tbEstudiante;

    public TbCurso() {
    }

    public TbCurso(Integer id) {
        this.id = id;
    }

    public TbCurso(Integer id, String name) {
        this.id = id;
        this.name = name;
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
        if (!(object instanceof TbCurso)) {
            return false;
        }
        TbCurso other = (TbCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.api.web.end.TbCurso[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TbEstudianteCurso> getTbEstudianteCursoCollection() {
        return tbEstudianteCursoCollection;
    }

    public void setTbEstudianteCursoCollection(Collection<TbEstudianteCurso> tbEstudianteCursoCollection) {
        this.tbEstudianteCursoCollection = tbEstudianteCursoCollection;
    }
    
}
