package br.edu.uniritter.buginf.constants;
/**
 * @author Diego
 *
 */
public enum ElasticSearchConfig {
	
	INDICE("bug_inf_dashboard"),
	TIPO_DEFEITO("defeito"),
	TIPO_PROJETO("projeto"),
	DEFEITO_MAPPING(																															
			"{\"defeito\":{\"properties\":{\"atribuidoPara\":{\"type\":\"string\",\"index\":\"not_analyzed\"},\"categoria\":{\"type\":\"string\"},\"dataCriacao\":{\"type\":\"date\",\"format\": \"dateOptionalTime\"},\"dataEncerramento\":{\"type\":\"date\",\"format\": \"dateOptionalTime\"},\"id\":{\"type\":\"long\"},\"prioridade\":{\"type\":\"string\"},\"status\":{\"type\":\"string\"},\"situacao\":{\"type\":\"string\"},\"descricao\":{\"type\":\"string\"},\"versao\":{\"type\":\"string\"}}}}");
	
	 
	
	private String valor;
	
	private ElasticSearchConfig(final String valor) {
		this.valor = valor;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
