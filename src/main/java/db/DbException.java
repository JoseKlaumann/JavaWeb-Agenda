package db;

/**
* @author Jos� Augusto Klaumann
* @version 1.0
* Classe de uma exce��o personalizada
*/
public class DbException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param msg Atribui uma mensagem a excecao
	 */
	public DbException(String msg) {
		super(msg);
	}

}
