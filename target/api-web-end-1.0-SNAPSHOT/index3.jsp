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
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Iniciar sesion</a></li>
            </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
        <div class="card">
        <div class="card-body">
                
              <form action="data" method="post" id="formulario" target="_blank">

                <p>Usuario: <input type="text" name="user" value = '${student.user}' class="form-control" placeholder="Usuario para acceder"></p>

                <p>Contraseña: <input type="password" name="password" value = '${student.password}' class="form-control" placeholder="Elije una contraseña fuerte"></p>

                <p><input type="submit" class="btn btn-success" value="Enviar datos"></p>

              </form>
        </form>
        </div>
        </div>
                   
        </div>
    </body>
</html>