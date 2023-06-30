package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DAO {
	// modulo de conexao
	
	// parametros de conexao
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "#dbcliente2023";
	
	// método de conexao
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	// CRUD CREATE
	public void inserirCliente(JavaBeans cliente) {
		String create = "insert into clientes (nome, fone, email, endereco, valorConta) values (?,?,?,?,?)";
		try {
			// abrir conexao com o banco
			Connection con = conectar();
			// preparar a query
			PreparedStatement pst = con.prepareStatement(create);
			//substituir os parametros (?) pelo conteudo das variaveis
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getFone());
			pst.setString(3, cliente.getEmail());
			pst.setString(4, cliente.getEndereco());
			pst.setString(5, cliente.getValorConta());
			//executar a query			
			pst.executeUpdate();
			// encerrar a conexao com o banco
			con.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//teste de conexao
	/* 
	 * public void testConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	 * 
	 */
	
}
	

