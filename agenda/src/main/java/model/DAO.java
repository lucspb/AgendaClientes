package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	// CRUD READ
	public ArrayList<JavaBeans> listarClientes(){
		//criando um objeto pra acessar a classe JavaBeans
		ArrayList<JavaBeans> clientes = new ArrayList<>();
		String read = "select * from clientes order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o laço while será executado enquanto houver clientes
			while(rs.next()) {
				//variaveis de suporte para receber dados do banco
				String idcli = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				String endereco = rs.getString(5);
				String valorConta = rs.getString(6);
				//populando o arrayList
				clientes.add(new JavaBeans(idcli, nome, fone, email, endereco, valorConta));
			}
			con.close();
			return clientes; 
		} catch(Exception e) {
			System.out.println(e);
			return null;
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
	

