package br.com.developeranalyst.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.developeranalyst.entidade.Usuario;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome,login,senha) values (?, ?, md5(?))";
		
		try (PreparedStatement stmt = con.prepareStatement(sql)){;
			stmt.setString(1, usu.getNome());
			stmt.setString(2, usu.getLogin());
			stmt.setString(3, usu.getSenha());
			
			//executando sql no banco
			stmt.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usu) {
       String sql = "update usuario set nome=?, login=?, senha=md5(?) where id=? ";
		
		try (PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, usu.getNome());
			stmt.setString(2, usu.getLogin());
			stmt.setString(3, usu.getSenha());
			stmt.setInt(4, usu.getId());
			
			//executando sql no banco
			stmt.execute();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	   }

	public void excluir(Usuario usu) {
		
		   String sql = "delete from usuario where id=? ";
			
			try (PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, usu.getId());
				//executando sql no banco
				stmt.execute();
			}catch (SQLException e){
				e.printStackTrace();
			}
			
		  }
	public void salvar(Usuario usuario){
		if(usuario.getId()!=null && usuario.getId()!=0){
			alterar(usuario);
		}else{
			cadastrar(usuario);
			
		}
	}
	/**
	 * Busca de um registro no banco de dados pelo numero do id do usuario
	 * @param id é um inteiro que representa o numero do id do usuario a ser buscado
	 * @return um objeto usuario quando encontra ou nullo quando não encontra.
	 */
	public Usuario buscaPorId(Integer id){
		
		String sql = "select * from usuario where id=?";
		
		
		try (PreparedStatement stmt = con.prepareStatement(sql)){;
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			//posicionando o cursor no primeiro registro
			if(rs.next()){
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				return usuario;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Realiza a busca de todos os registros da tabela usuario
	 * @param id
	 * @return Uma lista de objetos usuario contendo 0 elementos quando tiver registro
	 * ou n elementos quando tiver resultado
	 */
	
public List<Usuario> buscarTodos(){
		
		String sql = "select * from usuario";
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try (PreparedStatement stmt = con.prepareStatement(sql)){;
			
			ResultSet rs = stmt.executeQuery();
			//posicionando o cursor no primeiro registro
			while(rs.next()){
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				
				lista.add(usuario);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
  
  public Usuario autenticar(Usuario usuConsulta){
	  
	  String sql = "select * from usuario where login=? and senha=md5(?)";
	  
	  try (PreparedStatement stmt = con.prepareStatement(sql)){;
	       stmt.setString(1, usuConsulta.getLogin());
	       stmt.setString(2, usuConsulta.getSenha());
	       ResultSet rs = stmt.executeQuery();
	       
	       if(rs.next()){
	    	   Usuario usuario = new Usuario();
	    	   usuario.setId(rs.getInt("id"));
	    	   usuario.setNome(rs.getString("nome"));
	    	   usuario.setLogin(rs.getString("login"));
	    	   usuario.setSenha(rs.getString("senha"));
	    	   
	    	   return usuario;
 	       }
	  
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	  
	  return null;
  }
}

	

