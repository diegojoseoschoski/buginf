package br.edu.uniritter.buginf.facade.impl;

import br.edu.uniritter.buginf.facade.ElasticSearchFacade;
import br.edu.uniritter.buginf.service.ElasticSearchService;
import br.edu.uniritter.buginf.service.impl.ElasticSearchServiceImpl;

public class ElasticSearchFacadeImpl implements ElasticSearchFacade {
	
	private ElasticSearchService elasticSearchServiceImpl;
	
	public ElasticSearchFacadeImpl() {
		if (elasticSearchServiceImpl == null) {
			elasticSearchServiceImpl = new ElasticSearchServiceImpl();
		}
	}
	
	public <T> void inserirIndice(final String indice, final String tipo, final  T pojo) {
		elasticSearchServiceImpl.addIndiceRequisicao(indice, tipo, pojo);
	}
	

}
