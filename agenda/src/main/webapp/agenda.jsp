<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("clientes");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de Clientes</title>
<link rel="icon" href="images/cliente-mini.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Clientes</h1>
	<a href="novo.html" class="botaoPrimary">Novo Cliente</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Endereco</th>
				<th>Valor da Conta</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td> <%= lista.get(i).getIdcli()%> </td>
					<td> <%= lista.get(i).getNome()%> </td>
					<td> <%= lista.get(i).getFone()%> </td>
					<td> <%= lista.get(i).getEmail()%> </td>
					<td> <%= lista.get(i).getEndereco()%> </td>
					<td> <%= lista.get(i).getValorConta()%> </td>
					<td> <a href="select?idcli=<%=lista.get(i).getIdcli() %>" class="botaoPrimary">Editar</a></td>
				</tr>
			
			<% } %>
			
		</tbody>
	
	</table>
</body>
</html>