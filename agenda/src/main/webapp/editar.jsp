<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Clientes</title>
<link rel="icon" href="images/cliente-mini.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar cliente</h1>
	<form name="frmCliente" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcli" id="box3" readonly value="<%out.print(request.getAttribute("idcli"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="box" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="box2" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="box" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="endereco" class="box" value="<%out.print(request.getAttribute("endereco"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="valorConta" class="box2" value="<%out.print(request.getAttribute("valorConta"));%>"></td>
			</tr>
		</table>
		<input type="button" class="botaoPrimary" value="Salvar" onClick="validar()">
	</form>
	<script src="scripts/validator.js"></script>
</body>
</html>