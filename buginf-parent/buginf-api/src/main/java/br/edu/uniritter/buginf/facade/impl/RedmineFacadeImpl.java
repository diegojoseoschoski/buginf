package br.edu.uniritter.buginf.facade.impl;

import java.util.List;

import br.edu.uniritter.buginf.facade.BugTrackingFacade;
import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.service.impl.BugTrackingServiceFactory;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.PropertyLoaderUtil;

public class RedmineFacadeImpl implements BugTrackingFacade {
	
	private BugTrackingService redmineServiceImpl;
	
	RedmineFacadeImpl(final BugTrackingType bugTrackingType, final PropertyLoaderUtil propertyLoaderUtil) {
		redmineServiceImpl = BugTrackingServiceFactory.getBugTrackingService(bugTrackingType, propertyLoaderUtil);
	}

	public List<Projeto> recuperarTodosProjetos() {
		return redmineServiceImpl.recuperarTodosProjetos();
	}

	public List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto) {
		return redmineServiceImpl.recuperarTodosDefeitosProjeto(projeto);
	}
	
}
