package br.edu.uniritter.buginf.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.uniritter.buginf.mapper.BugTrackingMapper;
import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.PropertyLoaderUtil;

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
	
	RedmineServiceImpl(BugTrackingMapper<Issue, Defeito> redmineMapper, PropertyLoaderUtil propertyLoaderUtil) {
		if (redmineManager == null) {
			try {
				this.redmineManager = RedmineManagerFactory
						.createUnauthenticated(propertyLoaderUtil.get("bugtracking.url"));
			} catch (IOException e) {
				LOGGER.warning("Ocorreu um erro ao acessar o repositório.");
			}
			this.redmineMapper = redmineMapper;
		}
	}

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
		return projetos;
	}

	public List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto) {
		final IssueManager issueManager = redmineManager.getIssueManager();
		try {
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
