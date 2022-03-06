/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.web.end;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bjalvin
 */
@Entity
@Table(name = "tb_estudiante_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbEstudianteCurso.findAll", query = "SELECT t FROM TbEstudianteCurso t"),
    @NamedQuery(name = "TbEstudianteCurso.findById", query = "SELECT t FROM TbEstudianteCurso t WHERE t.id = :id")})
public class TbEstudianteCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_curso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TbCurso idCurso;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TbEstudiante idEstudiante;

    public TbEstudianteCurso() {
    }

    public TbEstudianteCurso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TbCurso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(TbCurso idCurso) {
        this.idCurso = idCurso;
    }

    public TbEstudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(TbEstudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
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
        if (!(object instanceof TbEstudianteCurso)) {
            return false;
        }
        TbEstudianteCurso other = (TbEstudianteCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.api.web.end.TbEstudianteCurso[ id=" + id + " ]";
    }
    
}
