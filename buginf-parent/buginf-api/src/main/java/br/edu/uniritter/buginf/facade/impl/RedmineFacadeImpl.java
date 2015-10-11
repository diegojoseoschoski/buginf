package br.edu.uniritter.buginf.facade.impl;

import java.util.List;

import br.edu.uniritter.buginf.facade.BugTrackingFacade;
import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.service.impl.BugTrackingServiceFactory;
import br.edu.uniritter.buginf.type.BugTrackingType;

public class RedmineFacadeImpl implements BugTrackingFacade {
	
	private BugTrackingService redmineServiceImpl;
	
	RedmineFacadeImpl(final BugTrackingType bugTrackingType) {
		redmineServiceImpl = BugTrackingServiceFactory.getBugTrackingService(bugTrackingType);
	}

	@Override
	public List<Projeto> recuperarTodosProjetos() {
		return redmineServiceImpl.recuperarTodosProjetos();
	}

	@Override
	public List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto) {
		return redmineServiceImpl.recuperarTodosDefeitosProjeto(projeto);
	}
	
}
