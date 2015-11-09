package br.edu.uniritter.buginf.mapper;

import org.apache.commons.lang3.StringUtils;

import br.edu.uniritter.buginf.type.StatusType;

public class RedmineStatusDefeitoMapper implements BugTrackingMapper<String, StatusType> {

	private static final String REOPENED = "REOPENED";
	private static final String CLOSED = "CLOSED";
	private static final String RESOLVED = "RESOLVED";
	private static final String NEEDS_FEEDBACK = "NEEDS FEEDBACK";
	private static final String NEW = "NEW";

	@Override
	public StatusType map(String status) {
		
		if (StringUtils.isBlank(status)) {
			return null;	
		} else {
			status  = StringUtils.upperCase(status);
		}
		
		switch (status) {
		case NEW:
			return StatusType.NOVO;
		case NEEDS_FEEDBACK:
			return StatusType.PENDENTE_FEEDBACK;
		case RESOLVED:
			return StatusType.RESOLVIDO;
		case CLOSED:
			return StatusType.FECHADO;	
		case REOPENED:
			return StatusType.REABERTO;		
		default:
			return StatusType.NAO_DEFINIDO;		
		}
	}

}
