package br.edu.uniritter.buginf.service.impl;

import java.util.logging.Logger;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
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

	private static final int REGISTROS_REQUISICAO = 1000;
	private final Node nodeElasticSearch;
	private final Client clientElasticSearch;
	private final BulkProcessor bulkProcessor;
	private static final Logger LOGGER = Logger.getLogger(BugInfServiceImpl.class.getName());

	public ElasticSearchServiceImpl() {
		this.nodeElasticSearch = NodeBuilder.nodeBuilder().node();
		this.clientElasticSearch = montarConexaoClient(nodeElasticSearch);
		this.bulkProcessor = inicializarBulkProcessor() ;
		
	}
	
	private Client montarConexaoClient(final Node node) {
		return node.client();
	}

	public <T> void addIndiceRequisicao(final String indice, final String tipo, final T pojo) {
		IndexRequest indexRequest = new IndexRequest(indice, tipo).source(paserObjectToJson(pojo));
		bulkProcessor.add(indexRequest);
	}
	
	public void criarIndiceMapeamentoDefeito(){

		try {
			clientElasticSearch.admin().indices()
			.prepareCreate(ElasticSearchConfig.INDICE.getValor())
			.execute().actionGet();
		} catch (Exception e) {
			LOGGER.info(String.format("O index: %s já existe no Elasticseach.", ElasticSearchConfig.INDICE.getValor()));
		}
		try {
		
			clientElasticSearch.admin().indices()
			.preparePutMapping(ElasticSearchConfig.INDICE.getValor())
			.setType(ElasticSearchConfig.TIPO_DEFEITO.getValor())
			.setSource(ElasticSearchConfig.DEFEITO_MAPPING.getValor()).execute().actionGet();
		} catch (Exception e) {
			LOGGER.warning("Ocorreu erro ao executar o mapeamento do defeito: " + e);
		}
		
	}

	private <T> String paserObjectToJson(final T pojo) {
		return BugInfJSONUtil.paserObjectToJson(pojo);
	}

	public void closeConnection() {
		
		if (!NodeBuilder.nodeBuilder().node().isClosed()) {
			bulkProcessor.close();
			clientElasticSearch.close();
			nodeElasticSearch.close();
		}
	}
	
	private BulkProcessor inicializarBulkProcessor() {
		BulkProcessor bulkProcessor = BulkProcessor
				.builder(clientElasticSearch, new BulkProcessor.Listener() {
					public void beforeBulk(long executionId, BulkRequest request) {
						LOGGER.info(String.format("Going to execute new bulk composed of %s actions", request.numberOfActions()));
					}

					public void afterBulk(long executionId,
							BulkRequest request, BulkResponse response) {
						LOGGER.info(String.format("Executed bulk composed of %s actions", request.numberOfActions()));
					}

					public void afterBulk(long executionId,
							BulkRequest request, Throwable failure) {
						LOGGER.warning("Falha na execução: "+ failure);
					}
				}).setBulkActions(REGISTROS_REQUISICAO).setConcurrentRequests(1).build();
		
		return bulkProcessor;
	}

}
