<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista de Clientes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
       integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkblue">
            <div>
            <a href="https://www.unicauca.edu.co" class="navbar-brand"> Aplicación Ejemplo Apliweb </a>
            </div>
            <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista de estudiantes</a></li>
            </ul>
            </nav>
        </header>
        <br>
            <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
            <h3 class="text-center">Lista de Estudiantes</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Nuevo Estudiantes</a>
            </div>
        <br>
        <table class="table table-bordered">
        <thead>
        <tr>
            <th>id</th>
            <th>code</th>
            <th>name</th>
            <th>apellido</th>
            <th>programa</th>
            <th>opciones</th>

            
        </tr>
        </thead>
       <tbody>
            <!-- for (Todo todo: todos) { -->
            <c:forEach var="student" items="${listStudent}">
                <tr>
                <td>
                <c:out value="${student.id}" />
                </td>
                <td>
                <c:out value="${student.code}" />
                </td>
                <td>
                <c:out value="${student.name}" />
                </td>
                 <td>
                <c:out value="${student.lastName}" />
                </td>
               <td>
                <c:out value="${student.program}" />
                </td>
               <td><a href="edit?id=<c:out value='${student.id}' />">Editar</a>
               &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${student.id}' />">Eliminar</a></td>
                </tr>
            </c:forEach>
       <!-- } -->
        </tbody>
        </table>
        </div>
        </div>
    </body>
</html>