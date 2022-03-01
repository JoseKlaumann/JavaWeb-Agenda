/**
 * Cofirmacao de exclusao de um contato
 * @author José Augusto Klaumann
 */
 
 function confirmar(idCon) {
	let resposta = confirm("Confirma a excluão deste contato?")
	if (resposta === true){
		//alert(idCon)
		window.location.href = "delete?idCon=" + idCon
	}
}