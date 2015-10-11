package br.edu.uniritter.buginf.constants;
/**
 * Adicionar as configura��es referentes a API 
 * do Redmine
 * @author Diego
 *
 */
public enum RedmineConfig {
	
	URI("http://www.redmine.org"),
	CHAVE_ACESSO_API("") ; // Se caso houver autentica��o usar a chave de acesso
	
	
	private String valor;
	
	private RedmineConfig(final String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
