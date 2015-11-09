package br.edu.uniritter.buginf.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.edu.uniritter.buginf.type.StatusType;

public class RedmineStatusDefeitoMapper implements BugTrackingMapper<String, StatusType> {

	private static final String REOPENED = "REOPENED";
	private static final String CLOSED = "CLOSED";
	private static final String RESOLVED = "RESOLVED";
	private static final String NEEDS_FEEDBACK = "NEEDS FEEDBACK";
	private static final String NEW = "NEW";
	
	private static final Map<String, StatusType> STATUS_MAP = new HashMap<String, StatusType>();
	
	static {
		STATUS_MAP.put(NEW, StatusType.NOVO);
		STATUS_MAP.put(NEEDS_FEEDBACK, StatusType.PENDENTE_FEEDBACK);
		STATUS_MAP.put(CLOSED, StatusType.FECHADO);
		STATUS_MAP.put(REOPENED, StatusType.REABERTO);
		STATUS_MAP.put(RESOLVED, StatusType.RESOLVIDO);
	}

	@Override
	public StatusType map(String status) {
		
		if (StringUtils.isBlank(status)) {
			return null;	
		} else {
			status  = StringUtils.upperCase(status);
		}
		return STATUS_MAP.get(status);
	}

}
