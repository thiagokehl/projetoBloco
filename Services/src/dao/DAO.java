package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * * Classe respons�vel pela Conex�o com o Banco de dados. � utilizada por
 * outras * classes de persist�ncia de dados. *
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
