<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
Seminario sem=(Seminario)request.getAttribute("sem");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEGUNDO PARCIAL</title>
    </head>
    <body>
        <h1><c:if test="${sem.id==0}">
                NUEVO REGISTRO
            </c:if>
            <c:if test="${sem.id!=0}">
                EDITAR REGISTRO
            </c:if>
        </h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${sem.id}">
            <table>
                <tr>
                    <td>TITULO</td>
                    <td><input type="text" name="titulo" value="${sem.titulo}"></td>
                </tr>
                <tr>
                    <td>EXPOSITOR</td>
                    <td><input type="text" name="expositor" value="${sem.expositor}"></td>
                </tr>
                <tr>
                    <td>FEHA</td>
                    <td><input type="text" name="fecha" value="${sem.fecha}"></td>
                </tr>
                <tr>
                    <td>HORAS</td>
                    <td><input type="text" name="horas" value="${sem.horas}"></td>
                </tr>
                <tr>
                    <td>CUPOS</td>
                    <td><input type="number" name="cupos" value="${sem.cupos}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
