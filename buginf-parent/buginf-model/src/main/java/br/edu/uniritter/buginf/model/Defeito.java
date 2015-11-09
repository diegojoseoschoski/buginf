package br.edu.uniritter.buginf.model;

import java.io.Serializable;
import java.util.Date;

import br.edu.uniritter.buginf.type.BugTrackingType;
import br.edu.uniritter.buginf.type.CategoriaType;
import br.edu.uniritter.buginf.type.PrioridadeType;
import br.edu.uniritter.buginf.type.SituacaoType;
import br.edu.uniritter.buginf.type.StatusType;


public class Defeito extends BugTrackingModel implements Serializable{
	
	
    private static final long serialVersionUID = -7423289327928823235L;

	private Integer id;
	
	private String descricao;
	
	private String versao;
	
	private StatusType status;
	
	private SituacaoType situacao;
	
	private CategoriaType categoria;
	
	private PrioridadeType priodade;
	
	private String atribuidoPara;
	
	private Projeto projeto;
	
	private String dataCriacao;
	
	private String dataEncerramento;
	
	//Private versaoSistema
	
	//Ver atividades do defeito historico verificar

	private Defeito(final DefeitoBuilder builder) {
		this.id = builder.id;
		this.descricao = builder.descricao;
		this.versao = builder.versao;
		this.status = builder.status;
		this.categoria = builder.categoria;
		this.priodade = builder.prioridade;
		this.atribuidoPara = builder.atribuidoPara;
		this.projeto = builder.projeto;
		this.dataCriacao = builder.dataCriacao;
		this.dataEncerramento = builder.dataEncerramento;
		this.situacao = builder.situacao;
		setBugtracking(builder.bugtrackingType);
	}
	
	public String getDescricao() {
		return descricao;
	}
	public String getVersao() {
		return versao;
	}
	public PrioridadeType getSeveridade() {
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

	public StatusType getStatus() {
		return status;
	}

	public CategoriaType getCategoria() {
		return categoria;
	}
	
	public SituacaoType getSituacao() {
		return situacao;
	}


	public static class DefeitoBuilder {
		

		private BugTrackingType bugtrackingType;
		
		private Integer id;
		
		private final String descricao;
		
		private String versao;
		
		private SituacaoType situacao;
		
		private StatusType status;
		
		private CategoriaType categoria;
		
		private PrioridadeType prioridade;

		private String atribuidoPara;

		private Projeto projeto;

		private String dataCriacao;

		private String dataEncerramento;

		public DefeitoBuilder(final BugTrackingType bugtrackingType, final Integer id, final String descricao) {
			this.bugtrackingType = bugtrackingType;
			this.id = id;
			this.descricao = descricao;
		}
		
		public DefeitoBuilder versao(final String versao) {
			this.versao = versao;
			return this;
		}

		public DefeitoBuilder situacao(final SituacaoType situacao) {
			this.situacao = situacao;
			return this;
		}
		
		public DefeitoBuilder status(final StatusType status) {
			this.status = status;
			return this;
		}
		
		public DefeitoBuilder categoria(final CategoriaType categoria) {
			this.categoria = categoria;
			return this;
		}
		
		public DefeitoBuilder prioridade(final PrioridadeType prioridade) {
			this.prioridade = prioridade;
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
