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
import javax.persistence.FetchType;
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
@Table(name = "tb_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEstudiante.findAll", query = "SELECT t FROM TbEstudiante   t"),
    @NamedQuery(name = "TbEstudiante.findById", query = "SELECT t FROM TbEstudiante t WHERE t.id = :id"),
    @NamedQuery(name = "TbEstudiante.findByProgram", query = "SELECT t FROM TbEstudiante t WHERE t.program = :program"),
    @NamedQuery(name = "TbEstudiante.findByCode", query = "SELECT t FROM TbEstudiante t WHERE t.code = :code"),
    @NamedQuery(name = "TbEstudiante.findByName", query = "SELECT t FROM TbEstudiante t WHERE t.name = :name"),
    @NamedQuery(name = "TbEstudiante.findByLastName", query = "SELECT t FROM TbEstudiante t WHERE t.lastName = :lastName")})
public class TbEstudiante implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstudiante")
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
    @Column(name = "program")
    private String program;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private TbUniversidad tbUniversidad;

    public TbEstudiante() {
    }

    public TbEstudiante(Integer id) {
        this.id = id;
    }

    public TbEstudiante(Integer id, String program, String code, String name, String lastName) {
        this.id = id;
        this.program = program;
        this.code = code;
        this.name = name;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TbUniversidad getTbUniversidad() {
        return tbUniversidad;
    }

    public void setTbUniversidad(TbUniversidad tbUniversidad) {
        this.tbUniversidad = tbUniversidad;
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
        if (!(object instanceof TbEstudiante)) {
            return false;
        }
        TbEstudiante other = (TbEstudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.api.web.end.TbEstudiante[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TbEstudianteCurso> getTbEstudianteCursoCollection() {
        return tbEstudianteCursoCollection;
    }

    public void setTbEstudianteCursoCollection(Collection<TbEstudianteCurso> tbEstudianteCursoCollection) {
        this.tbEstudianteCursoCollection = tbEstudianteCursoCollection;
    }
    
}
