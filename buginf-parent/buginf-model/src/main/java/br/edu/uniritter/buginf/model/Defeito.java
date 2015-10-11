package br.edu.uniritter.buginf.model;

import java.io.Serializable;
import java.util.Date;

import br.edu.uniritter.buginf.type.BugTrackingType;


public class Defeito extends BugTrackingModel implements Serializable{
	
	
    private static final long serialVersionUID = -7423289327928823235L;

	private Integer id;
	
	private String titulo;
	
	private String status;
	
	private String categoria;
	
	private String tipo;
	
	private String priodade;
	
	private String atribuidoPara;
	
	private Projeto projeto;
	
	private String dataCriacao;
	
	private String dataEncerramento;
	
	//Private versaoSistema
	
	//Ver atividades do defeito historico verificar

	private Defeito(final DefeitoBuilder builder) {
		this.id = builder.id;
		this.titulo = builder.titulo;
		this.status = builder.status;
		this.categoria = builder.categoria;
		this.tipo = builder.tipo;
		this.priodade = builder.severidade;
		this.atribuidoPara = builder.atribuidoPara;
		this.projeto = builder.projeto;
		this.dataCriacao = builder.dataCriacao;
		this.dataEncerramento = builder.dataEncerramento;
		setBugtracking(builder.bugtrackingType);
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getSeveridade() {
		return priodade;
	}

	public String getAtribuidoPara() {
		return atribuidoPara;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Projeto getProjeto() {
		return projeto;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "#"+id;
	}

	public String getStatus() {
		return status;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public static class DefeitoBuilder {
		
		private BugTrackingType bugtrackingType;
		
		private Integer id;
		
		private final String titulo;
		
		private String status;
		
		private String categoria;
		
		private String tipo;

		private String severidade;

		private String atribuidoPara;

		private Projeto projeto;

		private String dataCriacao;

		private String dataEncerramento;

		public DefeitoBuilder(final BugTrackingType bugtrackingType, final Integer id, final String titulo) {
			this.bugtrackingType = bugtrackingType;
			this.id = id;
			this.titulo = titulo;
		}
		
		public DefeitoBuilder status(final String status) {
			this.status = status;
			return this;
		}
		
		public DefeitoBuilder categoria(final String categoria) {
			this.categoria = categoria;
			return this;
		}
		
		public DefeitoBuilder tipo(final String tipo) {
			this.tipo = tipo;
			return this;
		}
		
		public DefeitoBuilder prioridade(final String prioridade) {
			this.severidade = prioridade;
			return this;
		}
		
		public DefeitoBuilder atribuidoPara(final String nome) {
			
			this.atribuidoPara = nome;
			return this;
		}
		
		public DefeitoBuilder projeto(final Projeto projeto) {
			this.projeto = projeto;
			return this;
		}
		
		public DefeitoBuilder dataCriacao(String dataCriacao) {
			this.dataCriacao = dataCriacao;
			return this;
		}
		
		public DefeitoBuilder dataEncerramento(String dataEncerramento) {
			this.dataEncerramento = dataEncerramento;
			return this;
		}
		
		public Defeito build() {
			Defeito defeito = new Defeito(this);
			return defeito;
		}
	}	

}
