package br.edu.uniritter.buginf.service.impl;

import java.io.IOException;
import java.util.logging.Logger;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import br.edu.uniritter.buginf.constants.ElasticSearchConfig;
import br.edu.uniritter.buginf.service.ElasticSearchService;
import br.edu.uniritter.buginf.util.BugInfJSONUtil;

/**
 * Serviço para popular os dados nos indices do elastic search
 * 
 * @author Diego
 *
 */
public class ElasticSearchServiceImpl implements ElasticSearchService {

	private final Node nodeElasticSearch;
	private final Client clientElasticSearch;
	private final BulkRequestBuilder bulkRequest;
	private static final Logger LOGGER = Logger.getLogger(BugInfServiceImpl.class.getName());

	public ElasticSearchServiceImpl() {
		this.nodeElasticSearch = NodeBuilder.nodeBuilder().node();
		this.clientElasticSearch = montarConexaoClient(nodeElasticSearch);
		this.bulkRequest = clientElasticSearch.prepareBulk();
	}

	private Client montarConexaoClient(final Node node) {
		return node.client();
	}

	@Override
	public <T> void addIndiceRequisicao(final String indice,
			final String tipo, final T pojo) {
		
		LOGGER.info("Número de requisições: " + bulkRequest.add(clientElasticSearch.prepareIndex(indice, tipo)
				.setSource(paserObjectToJson(pojo))).numberOfActions());
		
		
		
		
//		clientElasticSearch.prepareIndex(indice, tipo)
//				.setSource(paserObjectToJson(pojo))
//				.execute().
//				actionGet();
	}
	
	public void executarRequisicao() {
		bulkRequest.execute().actionGet();
	}
	
	public void criarIndiceMapeamentoDefeito(){
		 
		  try {
	            clientElasticSearch.admin().indices().prepareCreate(ElasticSearchConfig.INDICE.getValor()).execute().actionGet();
	        } catch (Exception e) {}

		
		clientElasticSearch.admin().indices()
					.preparePutMapping(ElasticSearchConfig.INDICE.getValor())
					.setType(ElasticSearchConfig.TIPO_DEFEITO.getValor())
					.setSource(ElasticSearchConfig.DEFEITO_MAPPING.getValor()).execute().actionGet();
			

	}

	private <T> String paserObjectToJson(final T pojo) {
		return BugInfJSONUtil.paserObjectToJson(pojo);
	}

	@Override
	public void closeConnection() {
		
		if (!NodeBuilder.nodeBuilder().node().isClosed()) {
			clientElasticSearch.close();
			nodeElasticSearch.close();
		}
	}

}
