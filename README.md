# JavaWeb-Agenda
Projeto Java Web de uma agenda de contatos com relatório em PDF

- Adicionar as .jar do Hibernate, do MySql e do itext na pasta lib que esta dentro da pasta WEB-INF.
- Configurar o persistence.xml.
- O .jar do MySql deve ser colocado dentro da pasta lib do TomCat.

- Os aquirvos da View devem ficar dentro da pasta 'webapp'.

- Documento .jsp: É um documento HTML que também consegue executar a liguagem Java. O servidor TomCat separa o que é HTML, mandando para o navegador, e o que é Java é feito o processamento no prórprio servidor. Tem um conteúdo que é gerado de forma dinamica.

- Controller (Servlet):  Onde ficam os padrões, com o método 'doGet' (Método principal do servlet) é possível fazer requisições e respostas.

- Para a camada controller receber a requisição do usuário, ao clicar no botão, é necessário configurar o servlet. Elas sáo feitas no '@WebSevlet' com o comando 'urlPatterns'.
- Ex: @WebServlet(urlPatterns = { "/Controller", "/main", "/insert"})

- O '/main' é uma requisição que o servlet vai trabalhar.

(Inserir uma informção)
- Nesse projeto na página index.html ao clicar no link 'Acessar' uma requisição é enviada ao servlet, ele trabalha a requisição e redireciona ao documento 'agenda.jsp'. Ele envia primeiro ao servlet, e não diretamente ao documento agenda.jsp, pois o servlet precisa fazer uma consulta a camada model (Onde estão as classes Java) e obter a listagem de contatos antes de encaminhar a requisição para a agenda.jsp.

(Inserir uma informção)
- Configurar o servlet para encaminhar a requisição feita pelo documento index.html ao documento agenda.jsp. ('Acessar')

(Atualizar uma informação)
- Ao precionar o botao de 'Editar' na pagina 'agenda.jsp' uma requisição é enviada ao servlet, encaminhando o número do id que será editado.
- O servlet encaminha para o pacote model, armazenando na classe JavaBeans o número do id que será editado.
- O servlet executa o método da classe Dao responsável por fazer um select  (findById) do contato que será editado.
- O método responsável pela seleção do contato a ser editado requisita da classe JavaBeans o id do contato.
- Tendo o id, o método executa um select do contato específico no DB.
- O DB retorna o contato ao método.
- O método seta as variáveis JavaBeans com os dados do contato.
- A classe JavaBeans encaminha os dados do contato ao servlet.
- O servlet redireciona esses dados ao documento editar.jsp. (Os campos são preenchidos automáticamente com os dados do contato que será editado)
- Após precionar o botão editar, os campos obrigatórios são validados pelo documento JavaScript.
- Após a validação, as informações são enviadas ao servlet.
- O servlet seta as variáveis JavaBeans com os dados editados do contato.
- Após o armazenamento dos dados do contato na classe JavaBeans, o servlet executa o método responsável pela edição do contato no DB.
- A edição envolve a criação de dois métodos, um para selecionar e o outro para editar.
- O método responsável pela edição requisita os dados da classe JavaBeans.
- Obtendo os dados do contato, o método executa o update no DB.
- Após a edição do contato, o servlet faz um redirecionamento ao documento agenda.jsp, exibindo a listagem de todos os contatos.

Se quiser, terá a opção de gerar um relatório em pdf
- Necessário baixar do github: https://github.com/itext/itextpdf
- Utilizar a versão 5.5.13.3 
- Baixar esta opção: itextpdf-5.5.13.3.zip
- Abrir a pasta, seleionar o arquivo 'itextpdf-5.5.13.3.jar' e colocar na pasta lib que fica dentro da pasta WEB-INF.
