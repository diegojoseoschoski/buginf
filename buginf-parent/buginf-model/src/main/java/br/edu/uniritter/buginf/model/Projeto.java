package br.edu.uniritter.buginf.model;

import java.io.Serializable;

import br.edu.uniritter.buginf.type.BugTrackingType;

public class Projeto extends BugTrackingModel implements Serializable{
	
	private static final long serialVersionUID = 7763007127733961240L;
	
	private String nome;
	
	private int id;

	private Projeto(final ProjetoBuilder builder) {
		setBugtracking(builder.bugtrackingType);
		this.nome = builder.nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	public static class ProjetoBuilder {
		
		private final String nome;
		private int id;
		private final BugTrackingType bugtrackingType;
		
		public ProjetoBuilder(final BugTrackingType bugtrackingType, final String nome, final int id) {
			this.bugtrackingType = bugtrackingType;
			this.nome = nome;
			this.id = id;
		}
		
		
		public Projeto build() {
			Projeto projeto = new Projeto(this);
			return projeto;
		}
	}

	public int getId() {
		return id;
	}
}
