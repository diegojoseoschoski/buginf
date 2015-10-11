package br.edu.uniritter.buginf.facade.impl;

import br.edu.uniritter.buginf.facade.BugTrackingFacade;
import br.edu.uniritter.buginf.type.BugTrackingType;

public class BugTrackingFacadeFactory {
	
	private BugTrackingFacadeFactory(){}
	
	public static BugTrackingFacade getBugTrackingFacade(final BugTrackingType bugTrackingType) {
		if (bugTrackingType == null) {
			return null;
		}
		
		switch (bugTrackingType) {
		case REDMINE:
			return new RedmineFacadeImpl(bugTrackingType);

		}
		return null;
	}		

}
