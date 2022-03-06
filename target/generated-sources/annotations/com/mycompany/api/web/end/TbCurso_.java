package com.mycompany.api.web.end;

import com.mycompany.api.web.end.TbEstudiante;
import com.mycompany.api.web.end.TbEstudianteCurso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-06T09:26:18")
@StaticMetamodel(TbCurso.class)
public class TbCurso_ { 

    public static volatile SingularAttribute<TbCurso, TbEstudiante> tbEstudiante;
    public static volatile CollectionAttribute<TbCurso, TbEstudianteCurso> tbEstudianteCursoCollection;
    public static volatile SingularAttribute<TbCurso, String> name;
    public static volatile SingularAttribute<TbCurso, Integer> id;

}