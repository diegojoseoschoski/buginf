package br.edu.uniritter.buginf.client;
import br.edu.uniritter.buginf.facade.BugInfFacade;
import br.edu.uniritter.buginf.facade.impl.BugInfFacadeImpl;
import br.edu.uniritter.buginf.type.BugTrackingType;

/**
 * @author Diego
 *
 */
public class BugInfClient {
	
	public static void main(String[] args) {
		
		BugInfFacade bugInfFacade = new BugInfFacadeImpl(BugTrackingType.REDMINE);
		bugInfFacade.executarIntegracaoDefeitos();
		
	}

}
