package br.edu.uniritter.buginf.mapper;

public interface BugTrackingMapper<O, D> {
	
	/**
	 * Responsavel por converter os objetos das API's do 
	 * sistema de bugtracking para o POJO da API BugInf
	 * @param origem
	 * @return
	 */
	D map(O origem);
}
