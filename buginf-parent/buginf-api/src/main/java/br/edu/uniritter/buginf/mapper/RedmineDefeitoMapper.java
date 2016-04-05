package br.edu.uniritter.buginf.mapper;

import org.apache.commons.lang3.StringUtils;

import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.BugInfDateUtil;

import com.taskadapter.redmineapi.bean.Issue;

public class RedmineDefeitoMapper implements BugTrackingMapper<Issue, Defeito> {
	
	private final static RedmineCategoriaDefeitoMapper CATEGORIA_MAPPER = new RedmineCategoriaDefeitoMapper();
	
	private final static RedmineStatusDefeitoMapper STATUS_MAPPER = new RedmineStatusDefeitoMapper();
	
	private final static RedmineSituacaoDefeitoMapper SITUACAO_MAPPER = new RedmineSituacaoDefeitoMapper();
	
	private final static RedminePrioridadeDefeitoMapper PRIORIDADE_MAPPER = new RedminePrioridadeDefeitoMapper();
	
	
	public Defeito map(Issue issue) {
		return new Defeito.DefeitoBuilder(BugTrackingType.REDMINE,
				issue.getId(),
				StringUtils.isNotBlank(issue.getSubject()) ? issue.getSubject()
						: StringUtils.EMPTY)
				.versao(issue.getTargetVersion() != null ? issue
						.getTargetVersion().getName() : StringUtils.EMPTY)
				.atribuidoPara(
						issue.getAssignee() != null ? issue.getAssignee()
								.getFirstName() : StringUtils.EMPTY)
				.situacao(SITUACAO_MAPPER.map(issue.getStatusId()))
				.status(STATUS_MAPPER.map(issue.getStatusName()))
				.categoria(CATEGORIA_MAPPER.map(issue.getCategory() != null ? issue.getCategory().getName() : null))
				.prioridade(PRIORIDADE_MAPPER.map(issue.getPriorityText()))
				.dataCriacao(
						issue.getCreatedOn() != null ? BugInfDateUtil
								.formatToISO8601(issue.getCreatedOn()) : null)
				.dataEncerramento(
						issue.getDueDate() != null ? BugInfDateUtil
								.formatToISO8601(issue.getDueDate()) : null)
				.projeto(
						new Projeto.ProjetoBuilder(BugTrackingType.REDMINE,
								issue.getProject().getName(), issue
										.getProject().getId()).build())
				.build();
	}
	
}
