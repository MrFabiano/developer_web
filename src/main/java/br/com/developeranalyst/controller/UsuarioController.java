package br.com.developeranalyst.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.developeranalyst.entidade.Usuario;
import br.com.developeranalyst.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet{
	
	public UsuarioController(){
		System.out.println("Construtor");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    resp.setContentType("text/html");
            String acao = req.getParameter("acao");
            if(acao.equals("exc")){
		    String id = req.getParameter("id");
		    Usuario usu = new Usuario();
		    if(id != null)
		    	usu.setId(Integer.parseInt(id));
		    
         UsuarioDAO dao = new UsuarioDAO();
         dao.excluir(usu);
         
        resp.sendRedirect("usucontroller.do?acao=lis"); 
	    }else if (acao.equals("lis")){
		
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.buscarTodos();
		
		req.setAttribute("lista", lista);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
		dispatcher.forward(req, resp);
		
	    }else if(acao.equals("alt")){
			String id = req.getParameter("id");
			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = dao.buscaPorId(Integer.parseInt(id));
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		
	    }else if (acao.equals("cad")){
	    	
	    	Usuario usuario = new Usuario();
	    	usuario.setId(0);
	    	usuario.setNome("");
	    	usuario.setLogin("");
	    	usuario.setSenha("");
	    	
	    	req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
	    }
            
   }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        
        Usuario usu = new Usuario();
        if(id!= null){
        usu.setId(Integer.parseInt(id));
        }
        usu.setNome(nome);
        usu.setLogin(login);
        usu.setSenha(senha);
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.salvar(usu);
        
        resp.getWriter().print("<h1>sucesso<h1>");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
	}
}
