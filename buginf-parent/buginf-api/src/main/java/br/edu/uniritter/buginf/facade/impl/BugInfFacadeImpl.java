package br.edu.uniritter.buginf.facade.impl;

import br.edu.uniritter.buginf.facade.BugInfFacade;
import br.edu.uniritter.buginf.service.BugInfService;
import br.edu.uniritter.buginf.service.impl.BugInfServiceImpl;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.PropertyLoaderUtil;

public class BugInfFacadeImpl implements BugInfFacade {
	
	private BugInfService bugInfServiceImpl;
	
	
	public BugInfFacadeImpl(final BugTrackingType bugTrackingType, PropertyLoaderUtil propertyLoaderUtil) {
		bugInfServiceImpl = new BugInfServiceImpl(bugTrackingType, propertyLoaderUtil);
	}
	
	public void executarIntegracaoDefeitos() {
		bugInfServiceImpl.executarIntegracaoDefeitos();
	}


}
