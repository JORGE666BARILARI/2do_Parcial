<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
List<Seminario> lista =(List<Seminario>)request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEGUNDO PARCIAL</title>
    </head>
    <body>
        <center>
            <table border="2">
                <tr>
                    <th>
                    <h2> SEGUNDO PARCIAL TEM-742</h2>
                    <h2> NOMBRE: JORGE LUIS ILARI ILANQUI</h2>
                    <h2> CARNET: 9232710</h2>
                    </th>
                </tr>
            </table>

        <h1>REGISTRO DE SEMINARIOS</h1>
        <p><a href="MainController?op=nuevo">NUEVO</a></p>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>TITULO</th>
                <th>EXPOSITOR</th>
                <th>FECHA</th>
                <th>HORAS</th>
                <th>CUPOS</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.expositor}</td>
                    <td>${item.fecha}</td>
                    <td>${item.horas}</td>
                    <td>${item.cupos}</td>
                    <td><a href="MainController?op=editar&id=${item.id}">EDITAR</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('ESTA SEGURO?'))">ELIMINAR</a></td>
                </tr>
            </c:forEach>
        </table>
        </center>
    </body>
</html>
