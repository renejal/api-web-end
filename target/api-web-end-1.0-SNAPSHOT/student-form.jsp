<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%! int fontSize; %>
<html>
    <head>
        <title>form student</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
       integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
        <form name="estudiante" action="update" method="post" id="formulario" class="formulario"">
            </c:if>
           <c:if test="${student == null}">
            <form name="estudiante" action="insert" method="post">
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
                <label>code</label> <input type="text" name="code" value="<c:out value='${student.code}' />"
               class="form-control" required="required">
            </fieldset>
            <fieldset class="form-group">
                <label>Programa</label> <input type="text" name="programa" value="<c:out value='${student.program}' />"
               class="form-control" required="required">
            </fieldset>
            <fieldset class="form-group">
                <label>nombre</label> <input type="text" name="name" value="<c:out value='${student.name}' />"
               class="form-control">
            </fieldset>
            <fieldset class="form-group">
                <label>apellido</label> <input type="text" name="apellido" value="<c:out value='${student.lastName}'/>" class="form-control">
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
                 <td colspan="2" align="center"><input type="button" value="Enviar" onclick="valida_envia()"></td>
            </div>
           
        </form>
        </div>
        </div>
                   
        </div>
                     <script type="text/javascript">
                    function valida_envia(){
                      //valido el nombre
                      dato = document.estudiante.code.value;
                      if (!dato.match(/[0123456789]{1,8}/)){
                       
                           alert("Tiene que ingresar el codigo valido")
                           document.estudiante.code.focus()
                           return 0;
                      }
                       programa = document.estudiante.programa.value;
                      if (!programa.match(/^(?!.* (?: |$))[A-Z,a-z]+$/)){
                       
                           alert("Tiene que ingresar el programa valido")
                           document.estudiante.programa.focus()
                           return 0;
                      }
                      name = document.estudiante.name.value;
                      if (!name.match(/^(?!.* (?: |$))[A-Z,a-z]+$/)){
                       
                           alert("Tiene que ingresar el nombre valido")
                           document.estudiante.name.focus()
                           return 0;
                      }
                       last = document.estudiante.apellido.value;
                      if (!last.match(/^(?!.* (?: |$))[A-Z,a-z]+$/)){
                       
                           alert("Tiene que ingresar el apellido valido")
                           document.estudiante.apellido.focus()
                           return 0;
                      }
                      alert("Muchas gracias por enviar el formulario");
                      document.estudiante.submit();
                      
                     }
                    
                 
  </script>
    </body>
</html>