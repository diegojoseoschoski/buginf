package br.edu.uniritter.buginf.facade;


public interface ElasticSearchFacade {
	<T> void inserirIndice(String indice, String tipo, T pojo);
}
