package br.edu.uniritter.buginf.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.edu.uniritter.buginf.type.PrioridadeType;

public class RedminePrioridadeDefeitoMapper implements BugTrackingMapper<String, PrioridadeType> {
	
	private static final String LOW = "LOW";
	private static final String NORMAL = "NORMAL";
	private static final String HIGH = "HIGH";
	private static final String URGENT = "URGENT";
	
	private static final Map<String, PrioridadeType> PRIORIDADE_MAP = new HashMap<String, PrioridadeType>();
	
	static {
		PRIORIDADE_MAP.put(LOW, PrioridadeType.BAIXA);
		PRIORIDADE_MAP.put(NORMAL, PrioridadeType.NORMAL);
		PRIORIDADE_MAP.put(HIGH, PrioridadeType.ALTA);
		PRIORIDADE_MAP.put(URGENT, PrioridadeType.URGENTE);
	}
 
	@Override
	public PrioridadeType map(String prioridade) {
		if (StringUtils.isBlank(prioridade)) {
			return null;
		} 
		
		return PRIORIDADE_MAP.get(StringUtils.upperCase(prioridade));
	}

}
