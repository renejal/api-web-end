package com.mycompany.api.web.end;

import com.mycompany.api.web.end.TbCurso;
import com.mycompany.api.web.end.TbEstudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-18T02:13:28")
@StaticMetamodel(TbEstudianteCurso.class)
public class TbEstudianteCurso_ { 

    public static volatile SingularAttribute<TbEstudianteCurso, TbCurso> idCurso;
    public static volatile SingularAttribute<TbEstudianteCurso, TbEstudiante> idEstudiante;
    public static volatile SingularAttribute<TbEstudianteCurso, Integer> id;

}