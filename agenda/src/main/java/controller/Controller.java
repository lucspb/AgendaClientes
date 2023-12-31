package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete", "/print"})
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
		}else if(action.equals("/update")) {
			editarCliente(request, response);
		} else if(action.equals("/delete")) {
			removerCliente(request, response);
		} else if(action.equals("/print")) {
			imprimirRelatorio(request, response);
		}else {
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
	
	protected void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//setar as variaveis JavaBeans
		cliente.setIdcli(request.getParameter("idcli"));
		cliente.setNome(request.getParameter("nome"));
		cliente.setFone(request.getParameter("fone"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setEndereco(request.getParameter("endereco"));
		cliente.setValorConta(request.getParameter("valorConta"));
		//executar o metodo alterarCliente
		dao.alterarCliente(cliente);
		//redirecionar para o agenda.jsp (atualizando os dados)
		response.sendRedirect("main");
	}
	
	// remover um cliente
	protected void removerCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idcli = request.getParameter("idcli");
		cliente.setIdcli(idcli);
		//executar o metodo deletar cliente
		dao.deletarCliente(cliente);
		//redirecionar para o agenda.jsp (atualizando os dados)
		response.sendRedirect("main");
	}
	
	// gerar relatorio em PDF
	protected void imprimirRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo de conteudo
			response.setContentType("aplication/pdf");
			//nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "clientes.pdf");
			// criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o documento 
			documento.open();
			documento.add(new Paragraph("Lista de clientes:"));
			documento.add(new Paragraph(" "));
			// criar uma tabela
			PdfPTable tabela = new PdfPTable(5);
			//cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Endereco"));
			PdfPCell col5 = new PdfPCell(new Paragraph("Valor da Conta"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);
			//popular a tabela com os clientes
			ArrayList<JavaBeans> lista = dao.listarClientes();
			for(int i=0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
				tabela.addCell(lista.get(i).getEndereco());
				tabela.addCell(lista.get(i).getValorConta());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
	
}
