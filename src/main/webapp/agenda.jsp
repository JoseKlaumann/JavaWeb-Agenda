<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!--Os dois imports sao necessarios para buscar os dados do banco -->
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.List"%>
<%
List<JavaBeans> list = (List<JavaBeans>) 
request.getAttribute("contatos");
//for (JavaBeans jb : list){
//	out.println(jb);
//}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo contato</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%for(int i = 0; i < list.size(); i++){%>
				<tr>
				 	<td><%=list.get(i).getIdCon()%></td>
				 	<td><%=list.get(i).getNome()%></td>
				 	<td><%=list.get(i).getFone()%></td>
				 	<td><%=list.get(i).getEmail()%></td>
				 	<!-- O '?' determina que vai receber um valor, que no 
				 	caso vai ser o id a ser editado -->
				 	<td><a href="select?idCon=<%=list.get(i).getIdCon()%>" 
				 	class="Botao1">Editar</a>
				 	<a href="javascript: confirmar(<%=list.get(i).getIdCon()%>)" class="Botao2">Excluir</a>
				 	</td>
				</tr>
			<%}%>	
		</tbody>	
	</table>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>