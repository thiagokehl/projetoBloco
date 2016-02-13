package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * * Classe responsável pela Conexão com o Banco de dados. É utilizada por
 * outras * classes de persistência de dados. *
 */
public class DAO {
	public Connection getConexao() {
		Connection conexao = null;
		String usuario = "root";
		String senha = "root";
		String nomeBancoDados = "projeto";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + nomeBancoDados, usuario,
					senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
