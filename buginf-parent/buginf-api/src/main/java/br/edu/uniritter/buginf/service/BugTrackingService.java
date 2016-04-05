package br.edu.uniritter.buginf.service;

import java.util.List;

import br.edu.uniritter.buginf.model.Defeito;
import br.edu.uniritter.buginf.model.Projeto;

/**
 * Inteface para a implementação de serviços que deve ser implementado para cada
 * sistema de Bug Tracking
 * 
 * @author Diego
 *
 */
public interface BugTrackingService {

	List<Projeto> recuperarTodosProjetos();

	List<Defeito> recuperarTodosDefeitosProjeto(Projeto projeto);
	

}
