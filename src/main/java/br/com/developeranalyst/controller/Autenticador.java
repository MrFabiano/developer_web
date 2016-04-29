package br.com.developeranalyst.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.developeranalyst.entidade.Usuario;
import br.com.developeranalyst.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class Autenticador extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sessao = req.getSession(false);
		if(sessao!= null){
			sessao.invalidate();
		}
		
		resp.sendRedirect("login.html");
	}
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		//colocando dados no objeto
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		//consultando se usuario existe no banco
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuAutenticado = dao.autenticar(usuario);
		
		//verificando se usuario foi encontrado
		if(usuAutenticado != null){
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			sessao.setMaxInactiveInterval(60*5);
			//redirecionado usuario para a tela principal
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		}else{
			resp.getWriter().print("<script> window.alert('Não encontrado!');location.href='login.html';</script>");
			
			
		}
	}

}
