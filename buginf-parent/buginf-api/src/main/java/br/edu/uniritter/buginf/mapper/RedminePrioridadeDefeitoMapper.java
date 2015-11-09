package br.edu.uniritter.buginf.mapper;

import org.apache.commons.lang3.StringUtils;

import br.edu.uniritter.buginf.type.PrioridadeType;

public class RedminePrioridadeDefeitoMapper implements BugTrackingMapper<String, PrioridadeType> {

	private static final String URGENT = "URGENT";
	private static final String HIGH = "HIGH";
	private static final String NORMAL = "NORMAL";
	private static final String LOW = "LOW";

	@Override
	public PrioridadeType map(String prioridade) {
		if (StringUtils.isBlank(prioridade)) {
			return null;
		} else {
			prioridade = StringUtils.upperCase(prioridade);
		}
		
		switch (prioridade) {
		case LOW:
			return PrioridadeType.BAIXA;
		case NORMAL:
			return PrioridadeType.NORMAL;
		case HIGH:
			return PrioridadeType.ALTA;
		case URGENT:
			return PrioridadeType.URGENTE;
		default:
			return PrioridadeType.NAO_DEFINIDA;
		}
	}

}
