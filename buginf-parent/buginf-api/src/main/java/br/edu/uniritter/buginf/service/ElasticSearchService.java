package br.edu.uniritter.buginf.service;


public interface ElasticSearchService {
	
	/**
	 * Adiciona indice na Requisicao Bulk ES
	 *  
	 * @param indice
	 * @param tipo
	 * @param pojo
	 */
	<T> void addIndiceRequisicao(final String indice, final String tipo, T pojo);
	
	/**
	 * Executa a execu��o do bulk request
	 * para inserir os indices
	 */
	void executarRequisicao();
	
	/**
	 * Cria o mapeamento do defeito
	 */
	void criarIndiceMapeamentoDefeito();

	/**
	 * Fecha a conex�o com o cliente do ES
	 */
	void closeConnection();
}
