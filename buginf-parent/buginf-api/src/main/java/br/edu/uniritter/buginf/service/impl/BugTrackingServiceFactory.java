package br.edu.uniritter.buginf.service.impl;

import br.edu.uniritter.buginf.service.BugTrackingService;
import br.edu.uniritter.buginf.type.BugTrackingType;

public class BugTrackingServiceFactory {
	
	private BugTrackingServiceFactory() {

	}

	public static BugTrackingService getBugTrackingService(final BugTrackingType bugTrackingType) {
		switch (bugTrackingType) {
		case REDMINE:
			return new RedmineServiceImpl();
		}
		return null;
	}

}
