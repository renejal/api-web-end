<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%! int fontSize; %>
<html>
    <head>
        <title>form student</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
       integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="js/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="js/default.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/alertify.min.js" type="text/javascript"></script>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkblue">
            <div>
            <a href="https://www.unicauca.edu.co" class="navbar-brand"> Universidad del cauca </a>
            </div>
            <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Estudiantes</a></li>
            </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
        <div class="card">
        <div class="card-body">
        <c:if test="${student != null}">
        <form name="estudiante" action="update" method="post" id="formulario" class="formulario" onsubmit="return validar_nombre()">
            </c:if>
           <c:if test="${student == null}">
            <form action="insert" method="post">
            </c:if>
           <caption>
            <h2>
            <c:if test="${student != null}">
                Editar Estudiantes
            </c:if>
           <c:if test="${student == null}">
                Matricular Estudiante
            </c:if>
            </h2>
            </caption>
           <c:if test="${student != null}">
            <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
            </c:if>
             <fieldset class="form-group">
                <label>code</label> <input type="text" value="<c:out value='${student.code}' />"
               class="form-control" name="code" required="required">
            </fieldset>
            <fieldset class="form-group">
                <label>Programa</label> <input type="text" value="<c:out value='${student.program}' />"
               class="form-control" name="program" required="required">
            </fieldset>
            <fieldset class="form-group">
                <label>nombre</label> <input type="text" value="<c:out value='${student.name}' />"
               class="form-control" name="name">
            </fieldset>
            <fieldset class="form-group">
                <label>apellido</label> <input type="text" value="<c:out value='${student.lastName}'/>" class="form-control" name="lastName">
            </fieldset>
            <div class ="row">
                <div class="col-md-8">
                 <label for="inputState">Curso</label>
                 <select id="inputState" class="form-control">
                   <option selected>Select curso</option>
                    <%for ( fontSize = 1; fontSize <= 3; fontSize++){ %>
                    <option>'${student.lastName}'</option>
                    <%}%>
                   <option>rene</option>
                 </select>
                </div>
                 <button type="submit" class="btn btn-success">Guardar</button>
            </div>
           
        </form>
        </div>
        </div>
                   
        </div>
                   <script>
                       function validar_nombre(){
                           nom = document.estudiante.name;
                           if(nom.length == 0){
                               alertify.alert("Error", "Ingrese nombre del Estudiante").set('label', 'Ok');
                               return false;
                               
                           }
                           alertify.success("Categoria registadad");
                           return true;
                       }
                       funtion validarTexto(){
                           tecla = e.keyCode;
                           teclado = String.fromCharCode(tecla)
                           if((teclado < 'A' || teclado > 'Z') && teclado != " "){
                               return false;
                           }
                       }
                   </script>
    </body>
</html>