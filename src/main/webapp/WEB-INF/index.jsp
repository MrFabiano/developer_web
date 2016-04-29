<!DOCTYPE html>
<%@page import="br.com.developeranalyst.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio</title>
</head>
<body>
<%@include file="menu.jsp"%>
 Bem Vindo <%
 
 Usuario usuario = (Usuario)request.getSession().getAttribute("usuAutenticado"); 
 out.print(usuario.getNome());
 
 %>
</body>
</html>