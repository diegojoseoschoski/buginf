package br.edu.uniritter.buginf.mapper;

import br.edu.uniritter.buginf.type.SituacaoType;

public class RedmineSituacaoDefeitoMapper implements BugTrackingMapper<Integer, SituacaoType> {

	private static final int ID_FECHADO = 5;
	

	public SituacaoType map(Integer situacaoId) {
		
		if (situacaoId == null) {
			return null;
		}
		
		switch (situacaoId) {
		case ID_FECHADO:
			return SituacaoType.FECHADO;
		default:
			return SituacaoType.ABERTO;
		}
	}

}
