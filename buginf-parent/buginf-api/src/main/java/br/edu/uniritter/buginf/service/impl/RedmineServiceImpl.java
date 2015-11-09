package br.edu.uniritter.buginf.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.uniritter.buginf.constants.RedmineConfig;
import br.edu.uniritter.buginf.mapper.BugTrackingMapper;
import br.edu.uniritter.buginf.mapper.RedmineDefeitoMapper;
import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.type.BugTrackingType;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.ProjectManager;
import com.taskadapter.redmineapi.RedmineAuthenticationException;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

public class RedmineServiceImpl implements BugTrackingService {

	private static final Logger LOGGER = Logger
			.getLogger(RedmineServiceImpl.class.getName());

	private RedmineManager redmineManager;
	
	private BugTrackingMapper<Issue, Defeito> redmineMapper;

	RedmineServiceImpl(BugTrackingMapper<Issue, Defeito> redmineMapper) {
		if (redmineManager == null) {
			this.redmineManager = RedmineManagerFactory
					.createUnauthenticated(RedmineConfig.URI.getValor());
			this.redmineMapper = redmineMapper;
		}
	}

	@Override
	public List<Projeto> recuperarTodosProjetos() {
		final ProjectManager projectManager = redmineManager
				.getProjectManager();
		try {
			return montaProjetosRetorno(projectManager.getProjects());
		} catch (RedmineException e) {
			LOGGER.log(Level.SEVERE, "Erro : ", e);
		}
		return Collections.emptyList();
	}

	private List<Projeto> montaProjetosRetorno(final List<Project> projects) {
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (Project project : projects) {
			projetos.add(new Projeto.ProjetoBuilder(BugTrackingType.REDMINE,
					project.getIdentifier(), project.getId()).build());
		}
		// Adicionar validação vazio botar java lang
		return projetos;
	}

	@Override
	public List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto) {
		final IssueManager issueManager = redmineManager.getIssueManager();
		Map<String, String> parametros = new HashMap<String, String>();
		try {
//			parametros.put("project_id", projeto.getNome());
//			parametros.put("status_id", "*");
//			
//			//issueManager.getIssues(parametros);
			
			return montarDefeitosRetorno(issueManager.getIssues(
					projeto.getNome(), null));
		} catch (RedmineAuthenticationException e) {
			LOGGER.log(Level.SEVERE, "Erro de Autenticação: ", e);
		} catch (RedmineException e) {
			LOGGER.log(Level.SEVERE, "Erro: ", e);
		} finally {
			closeConnection();
		}
		return Collections.emptyList();
	}

	private List<Defeito> montarDefeitosRetorno(final List<Issue> issues) {
		List<Defeito> defeitos = new ArrayList<Defeito>();

		for (Issue issue : issues) {
			try {
				Defeito defeito = redmineMapper.map(issue);
				defeitos.add(defeito);
				
				LOGGER.info(String.format(
						"Data Criação: %s e Data Encerramento: %s",
						defeito.getDataCriacao(), defeito.getDataEncerramento()));
			} catch (Exception e) {
				LOGGER.warning(String.format(
						"Ocorreu erro ao recuperar o defeito: %s",
						issue.getDescription()));
			}

		}
		return defeitos;
	}

	/**
	 * Fecha conexão com a API do Redmine
	 */
	private void closeConnection() {
		redmineManager.shutdown();
	}

}
