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
	 * Cria o mapeamento do defeito
	 */
	void criarIndiceMapeamentoDefeito();

	/**
	 * Fecha a conexão com o cliente do ES
	 */
	void closeConnection();
}
