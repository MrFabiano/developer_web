<!DOCTYPE html>
<%@page import="br.com.developeranalyst.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario JSP</title>

</head>
<body>
       <%@include file="menu.jsp" %>
      <%
        Usuario u = (Usuario)request.getAttribute("usu");
      
      %>
     <form action="usucontroller.do" method="post">
           ID: <input type="number" name="id" value="<%=u.getId()%>"/>
           Nome:<input type="text" name="nome" value="<%=u.getNome()%>"/>
           Login:<input type="text" name="login" value="<%=u.getLogin()%>"/>
           Senha:<input type="text" name="senha" value="<%=u.getSenha()%>"/>
                 <input type="submit" value="Salvar">
         
      </form>
</body>
</html>