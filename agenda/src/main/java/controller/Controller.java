package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert"})
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
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	//listar clientes
	protected void clientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//criando um objeto que ira receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarClientes();
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
}
