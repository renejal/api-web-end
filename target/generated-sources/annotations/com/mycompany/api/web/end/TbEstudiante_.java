package com.mycompany.api.web.end;

import com.mycompany.api.web.end.TbEstudianteCurso;
import com.mycompany.api.web.end.TbUniversidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-17T23:48:30")
@StaticMetamodel(TbEstudiante.class)
public class TbEstudiante_ { 

    public static volatile SingularAttribute<TbEstudiante, String> lastName;
    public static volatile SingularAttribute<TbEstudiante, String> code;
    public static volatile CollectionAttribute<TbEstudiante, TbEstudianteCurso> tbEstudianteCursoCollection;
    public static volatile SingularAttribute<TbEstudiante, String> name;
    public static volatile SingularAttribute<TbEstudiante, Integer> id;
    public static volatile SingularAttribute<TbEstudiante, String> program;
    public static volatile SingularAttribute<TbEstudiante, TbUniversidad> tbUniversidad;

}