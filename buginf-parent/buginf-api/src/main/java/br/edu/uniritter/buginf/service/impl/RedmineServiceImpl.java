package br.edu.uniritter.buginf.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.format.DateTimeFormatter;
import org.elasticsearch.common.joda.time.format.ISODateTimeFormat;

import br.edu.uniritter.buginf.constants.RedmineConfig;
import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.BugInfDateUtil;

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

	RedmineServiceImpl() {
		if (redmineManager == null) {
			this.redmineManager = RedmineManagerFactory
					.createUnauthenticated(RedmineConfig.URI.getValor());
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
			projetos.add(new Projeto.ProjetoBuilder(BugTrackingType.REDMINE, project.getIdentifier(), project.getId())
					.build());
		}
		// Adicionar validação vazio botar java lang
		return projetos;
	}

	@Override
	public List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto) {
		final IssueManager issueManager = redmineManager.getIssueManager();
		Map<String, String> parametros = new HashMap<String, String>();
		try {
			parametros.put("project_id", projeto.getNome());
			parametros.put("status_id", "*");
			//issueManager.getIssues(parametros)
			return montarDefeitosRetorno(issueManager.getIssues(projeto.getNome(),null));
		} catch (RedmineAuthenticationException e) {
			LOGGER.log(Level.SEVERE, "Erro de Autenticação: ", e);
		} catch (RedmineException e) {
			LOGGER.log(Level.SEVERE, "Erro: ", e);
		}
		return Collections.emptyList();
	}

	private List<Defeito> montarDefeitosRetorno(final List<Issue> issues) {
		List<Defeito> defeitos = new ArrayList<Defeito>();

		for (Issue issue : issues) {
			try {
				Defeito defeito = montarDefeitoRetorno(issue);
				defeitos.add(defeito);	
				LOGGER.info(String.format("Data Criação: %s e Data Encerramento: %s", defeito.getDataCriacao(), defeito.getDataEncerramento()));
			} catch (Exception e) {
				LOGGER.warning(String.format("Ocorreu erro ao recuperar o defeito: %s", issue.getDescription()));
			}
		
		}
		return defeitos;
	}

	private Defeito montarDefeitoRetorno(final Issue issue) {
			
			return new Defeito.DefeitoBuilder(
					BugTrackingType.REDMINE, issue.getId(),
					StringUtils.isNotBlank(issue.getSubject()) ? issue
							.getSubject() : StringUtils.EMPTY)
					.atribuidoPara(
							issue.getAssignee() != null ? issue.getAssignee()
									.getFirstName() : StringUtils.EMPTY)
					.status(
							StringUtils.isNotBlank(issue.getStatusName())  ? issue
									.getStatusName() : StringUtils.EMPTY)	
					.tipo(
							issue.getTracker() != null ? issue
									.getTracker().getName() : StringUtils.EMPTY)
					.categoria(
							issue.getCategory() != null ? issue
									.getCategory().getName() : StringUtils.EMPTY)				
					.prioridade(
							StringUtils.isNotBlank(issue.getPriorityText()) ? issue
									.getPriorityText() : StringUtils.EMPTY)
					.dataCriacao(
							issue.getCreatedOn() != null ? BugInfDateUtil.formatToISO8601(issue.getCreatedOn())
									: null)
					.dataEncerramento(
							issue.getDueDate() != null ?  BugInfDateUtil.formatToISO8601(issue.getDueDate())
									: null)
					.projeto(
							new Projeto.ProjetoBuilder(BugTrackingType.REDMINE,
									issue.getProject().getIdentifier(),issue.getProject().getId()).build())
					.build();
		
	}

}
