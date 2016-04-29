package br.com.developeranalyst;

import java.util.List;

import br.com.developeranalyst.entidade.Usuario;
import br.com.developeranalyst.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		testCadastrar();
		//testAlterar();
		//testExcluir();
		//testSalvar();
		//testBuscarPorId();
		//testBuscarTodos();
		//testAutenticar();

	}
	private static void testAutenticar() {
		
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("jao");
		usu.setSenha("1234");
		
		Usuario usuRetorno = dao.autenticar(usu);
		System.out.println(usuRetorno);
		
	}
	private static void testBuscarPorId() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.buscaPorId(1);
		System.out.println(usuario);
		
		
	}
	private static void testBuscarTodos() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = dao.buscarTodos();
		for(Usuario u: lista){
		System.out.println(u);
	}
}
	private static void testAlterar() {
		Usuario usu = new Usuario();
		
		usu.setId(6);
		usu.setNome("Joazinho da Silva");
		usu.setLogin("jzaoss");
		usu.setSenha("777888");
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.alterar(usu);
		
		System.out.println("Alterado com Sucesso");
	}


	private static void testCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Joazinho");
		usu.setLogin("jzao");
		usu.setSenha("777");
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.cadastrar(usu);
		
		System.out.println("Cadastrado com Sucesso");
	}
	
	private static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(6);
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.excluir(usu);
		
		System.out.println("Excluido com Sucesso");
	}
	
	public static void testSalvar(){
		
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("Mario de souza");
		usuario.setLogin("marios");
		usuario.setSenha("44555");
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(usuario);
		
		System.out.println("Salvo com Sucesso");
		
	}
	

}





