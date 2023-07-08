/**
 * confirmação de exclusão
 */

 function confirmar(idcli){
	 let resposta = confirm("Você tem certeza que deseja excluir esse cliente?")
	 if(resposta === true){
		 alerta(idcli);
	 }
 }