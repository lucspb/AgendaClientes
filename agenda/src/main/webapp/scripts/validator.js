function validar(){
	let nome = frmCliente.nome.value;
	let fone = frmCliente.fone.value;
	let endereco = frmCliente.endereco.value;
	if(nome === ""){
		alert("Preencha o campo Nome");
		frmCliente.nome.focus();
		return false;
	} else if(fone === ""){
		alert("Preencha o campo Fone");
		frmCliente.fone.focus();
		return false;
	} else if(endereco === ""){
		alert("Preencha o campo Endereco");
		frmCliente.endereco.focus();
		return false;
	} else{
		document.forms["frmCliente"].submit();
	}
}

