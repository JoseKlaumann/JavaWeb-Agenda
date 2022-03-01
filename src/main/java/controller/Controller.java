package controller;

import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Dao;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	/*
	 * Onde ficam os padrões, com o método 'doGet' (Método principal do servlet) é
	 * possível fazer requisições e respostas.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// action = armazena o caminho da requisicao
		String action = request.getServletPath();
		System.out.println(action);

		/*
		 * Configura o servlet para encaminhar a requisição feita pelo documento
		 * index.html ao documento agenda.jsp.
		 * 
		 * Se o conteudo da variavel 'action' = '/main' (if)
		 * 
		 * Se o metodo 'doGet' receber uma requisicao '/main' eu quero redirecionar ao
		 * metodo que ira trabalhar especificamente esta requisicao.
		 */
		if (action.equals("/main")) {
			contatos(request, response);
		}
		// Faz um insert dos dados
		else if (action.equals("/insert")) {
			adicionarContato(request, response);
		}
		// Faz um findById
		else if (action.equals("/select")) {
			listarContato(request, response);
		}
		// Faz o update
		else if (action.equals("/update")) {
			editarContato(request, response);
		}
		// Faz o delete
		else if (action.equals("/delete")) {
			removerContato(request, response);
		}
		// Faz o relatorio
		else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ira receber os dados JavaBeans
		List<JavaBeans> list = dao.findAll();
		// Teste de recebimento da lista
//		for(JavaBeans jb : lista) {
//			System.out.println(jb);
//		}
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", list);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Novo contato
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		//teste de recebimento dos dados do dormulario
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone"));
//		System.out.println(request.getParameter("email"));

		// setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Invocar o metodo insert passando o objeto contato
		dao.insert(contato);

		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	// FindById do contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do id do contato que sera editado
		String idCon = request.getParameter("idCon");

		// Setar a variavel JavaBeans
		contato.setIdCon(request.getParameter("idCon"));

		// Executar o metodo select (Dao)
		dao.findById(idCon);

		// Setar os atributos do formulario com o conteudo JavaBeans
		request.setAttribute("idCon", contato.getIdCon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	// Editar contato
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Teste de recebimento
//			System.out.println(request.getParameter("idCon"));
//			System.out.println(request.getParameter("nome"));
//			System.out.println(request.getParameter("fone"));
//			System.out.println(request.getParameter("email"));

		// Setar as variaveis JavaBeans
		contato.setIdCon(request.getParameter("idCon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// Executar o metodo update
		dao.update(contato);

		// Redirecionar para o documento agenda.jsp (atualizando)
		response.sendRedirect("main");
	}

	// Remover um contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do id do contato a ser excluido (validador.js)
		String idCon = request.getParameter("idCon");
		// System.out.println(idCon);

		// Setar a variavel idCon JavaBeans
		contato.setIdCon(idCon);

		// Executar metodo delete (Dao) passando o objeto contato
		dao.delete(idCon);

		// Redirecionar para o documento agenda.jsp (atualizando)
		response.sendRedirect("main");
	}
	
	// Gerar relatorio em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Document documento = new Document();
		try {
			// Tipo de conteudo
			response.setContentType("apllication/pdf");
			
			// Nome do documento
			response.addHeader("Content-Disposition",  "inline; filename=" + "contatos.pdf");
			
			// Criar o PDF
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			// Abrir o documento -> conteudo
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			
			// Cabecalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			
			// Popular a tabela com os contatos
			List<JavaBeans> list = dao.findAll();
			for (JavaBeans jb : list) {
				tabela.addCell(jb.getNome());
				tabela.addCell(jb.getFone());
				tabela.addCell(jb.getEmail());
			}
			documento.add(tabela);
			
			documento.close();
		}
		catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
