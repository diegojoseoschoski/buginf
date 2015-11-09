package br.edu.uniritter.buginf.facade.impl;

import br.edu.uniritter.buginf.facade.BugInfFacade;
import br.edu.uniritter.buginf.service.BugInfService;
import br.edu.uniritter.buginf.service.impl.BugInfServiceImpl;
import br.edu.uniritter.buginf.type.BugTrackingType;

public class BugInfFacadeImpl implements BugInfFacade {
	
	private BugInfService bugInfServiceImpl;
	
	public BugInfFacadeImpl(final BugTrackingType bugTrackingType) {
		bugInfServiceImpl = new BugInfServiceImpl(bugTrackingType);
	}

	@Override
	public void executarIntegracaoDefeitos() {
		bugInfServiceImpl.executarIntegracaoDefeitos();
	}
	

}
