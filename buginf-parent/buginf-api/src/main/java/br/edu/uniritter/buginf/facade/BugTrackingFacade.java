package br.edu.uniritter.buginf.facade;

import java.util.List;

import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;

/**
 * Inteface para a implementação da fachada que deve ser implementado para cada
 * sistema de Bug Tracking
 * 
 * @author Diego
 *
 */
public interface BugTrackingFacade {

	List<Projeto> recuperarTodosProjetos();

	List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto);
	

}
