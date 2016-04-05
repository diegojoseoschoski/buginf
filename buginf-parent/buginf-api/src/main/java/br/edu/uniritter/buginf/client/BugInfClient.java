package br.edu.uniritter.buginf.client;

import java.io.IOException;
import java.util.logging.Logger;

import br.edu.uniritter.buginf.facade.BugInfFacade;
import br.edu.uniritter.buginf.facade.impl.BugInfFacadeImpl;
import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.util.PropertyLoaderUtil;

/**
 * @author Diego
 *
 */
public class BugInfClient {
	
	private static final Logger LOGGER = Logger.getLogger(BugInfClient.class.getName());
	
	public static void main(String[] args) {

		if (args != null) {
			final String propertiesFile = args[0];
			PropertyLoaderUtil propertyLoader = new PropertyLoaderUtil(
					propertiesFile);
			try {
				BugInfFacade bugInfFacade = new BugInfFacadeImpl(
						BugTrackingType.valueOf(propertyLoader
								.get("bugtracking.name")), propertyLoader);
				
				bugInfFacade.executarIntegracaoDefeitos();

			} catch (IOException e) {
				LOGGER.warning("Erro ao executar a integração do repositório");
			}
		}

	}

}
