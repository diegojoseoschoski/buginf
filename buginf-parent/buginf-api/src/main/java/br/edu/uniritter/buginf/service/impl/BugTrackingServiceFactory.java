package br.edu.uniritter.buginf.service.impl;

import br.edu.uniritter.buginf.mapper.RedmineDefeitoMapper;
import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.PropertyLoaderUtil;

public class BugTrackingServiceFactory {
	
	private BugTrackingServiceFactory() {

	}

	public static BugTrackingService getBugTrackingService(final BugTrackingType bugTrackingType, final PropertyLoaderUtil propertyLoaderUtil) {
		switch (bugTrackingType) {
		case REDMINE:
			return new RedmineServiceImpl(new RedmineDefeitoMapper(), propertyLoaderUtil);
		}
		return null;
	}

}
