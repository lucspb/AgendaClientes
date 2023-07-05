package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans cliente = new JavaBeans();

    public Controller() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/main")) {
			clientes(request, response);
		} else if(action.equals("/insert")) {
			novoCliente(request, response);
		} else if(action.equals("/select")) {
			listarClientes(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	//listar clientes
	protected void clientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//criando um objeto que ira receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarClientes();
		//encaminhar a lista ao agenda.jsp´
		request.setAttribute("clientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp"); 
		rd.forward(request, response);
	}
	
	//novo cliente
	protected void novoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// setar as variaveis do JavaBeans
		cliente.setNome(request.getParameter("nome"));
		cliente.setFone(request.getParameter("fone"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setEndereco(request.getParameter("endereco"));
		cliente.setValorConta(request.getParameter("valorConta"));
		// invocar o metodo inserirCliente passando o objeto cliente
		dao.inserirCliente(cliente);
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}
	
	//editar cliente
	protected void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recebimento do id do cliente que será editado
		String idcli = request.getParameter("idcli");
		// setar a variavel javabeans
		cliente.setIdcli(idcli);
		//executar o metodo selecionarContato (DAO)
		dao.selecionarCliente(cliente);
		//setar os atributos do form com o conteudo javaBeans
		request.setAttribute("idcli", cliente.getIdcli());
		request.setAttribute("nome", cliente.getNome());
		request.setAttribute("fone", cliente.getFone());
		request.setAttribute("email", cliente.getEmail());
		request.setAttribute("endereco", cliente.getEndereco());
		request.setAttribute("valorConta", cliente.getValorConta());
		// encaminahr ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
}
