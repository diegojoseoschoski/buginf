package br.edu.uniritter.buginf.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.uniritter.buginf.constants.ElasticSearchConfig;
import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.service.BugInfService;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.service.ElasticSearchService;
import br.edu.uniritter.buginf.type.BugTrackingType;

public class BugInfServiceImpl implements BugInfService {
	
	private static final String MSG_DEFEITOS_NAO_ENCONTRADOS = "Não foram encontrados defeitos do Projeto: %s";

	private static final String TEMPO_TOTAL_EXECUCAO = "Tempo de execução: %s";

	private static final Logger LOGGER = Logger.getLogger(BugInfServiceImpl.class.getName());
	
	private final ElasticSearchService elasticSearchServiceImpl;

	private final BugTrackingService bugTrackingServiceImpl;
	
	public BugInfServiceImpl(final BugTrackingType bugTrackingType) {
		this.elasticSearchServiceImpl =  new ElasticSearchServiceImpl();
		this.bugTrackingServiceImpl = BugTrackingServiceFactory.getBugTrackingService(bugTrackingType);
		
	}

	@Override
	public void executarIntegracaoDefeitos() {
		LOGGER.info("Integração dos defeitos Iniciada...");
		final List<Projeto> projetos = bugTrackingServiceImpl.recuperarTodosProjetos();
		
		for (Projeto projeto : projetos) {
				
			final List<Defeito> defeitos = bugTrackingServiceImpl.recuperarTodosDefeitosProjeto(projeto);	
			if (!defeitos.isEmpty()) {
				executarInsercaoDefeitos(defeitos);	
			}
			LOGGER.info(String.format(MSG_DEFEITOS_NAO_ENCONTRADOS,projeto.getNome()));
		}
		
	}

	private void executarInsercaoDefeitos(final List<Defeito> defeitos) {
		long startTime = System.currentTimeMillis();
		
		try {
			
			elasticSearchServiceImpl.criarIndiceMapeamentoDefeito();
			
			for (Defeito defeito : defeitos) {
				elasticSearchServiceImpl.addIndiceRequisicao(ElasticSearchConfig.INDICE.getValor(), ElasticSearchConfig.TIPO_DEFEITO.getValor(), defeito);
			}
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro insercao defeitos", e);
		} finally {
			elasticSearchServiceImpl.closeConnection();
		}
		
		long endTime = System.currentTimeMillis();
		LOGGER.info(String.format(TEMPO_TOTAL_EXECUCAO, (endTime-startTime)));
		
	}

}
