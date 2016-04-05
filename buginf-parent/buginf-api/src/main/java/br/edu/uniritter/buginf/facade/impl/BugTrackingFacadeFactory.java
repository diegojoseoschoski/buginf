package br.edu.uniritter.buginf.facade.impl;

import br.edu.uniritter.buginf.facade.BugTrackingFacade;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.PropertyLoaderUtil;

public class BugTrackingFacadeFactory {
	
	private BugTrackingFacadeFactory(){}
	
	public static BugTrackingFacade getBugTrackingFacade(final BugTrackingType bugTrackingType, PropertyLoaderUtil propertyLoaderUtil) {
		if (bugTrackingType == null) {
			return null;
		}
		
		switch (bugTrackingType) {
		case REDMINE:
			return new RedmineFacadeImpl(bugTrackingType, propertyLoaderUtil);

		}
		return null;
	}		

}
