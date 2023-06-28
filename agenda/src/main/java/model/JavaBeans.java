package model;

public class JavaBeans {
	private String idcli;
	private String nome;
	private String fone;
	private String email;
	private String endereco;
	private String valorConta;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String idcli, String nome, String fone, String email, String endereco, String valorConta) {
		super();
		this.idcli = idcli;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
		this.endereco = endereco;
		this.valorConta = valorConta;
	}

	public String getIdcli() {
		return idcli;
	}
	public void setIdcli(String idcli) {
		this.idcli = idcli;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getValorConta() {
		return valorConta;
	}
	public void setValorConta(String valorConta) {
		this.valorConta = valorConta;
	}
}
