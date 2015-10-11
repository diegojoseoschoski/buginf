package br.edu.uniritter.buginf.constants;
/**
 * Verificar refatorar para uma única INTERFACE COM AS CONSTANTES
 * @author Diego
 *
 */
public enum ElasticSearchConfig {
	
	INDICE("bug_inf_dashboard"),//bug_inf_integracao
	TIPO_DEFEITO("defeito"),
	TIPO_PROJETO("projeto"),
	DEFEITO_MAPPING(
			"{\"defeito\":{\"properties\":{\"atribuidoPara\":{\"type\":\"string\",\"index\":\"not_analyzed\"},\"categoria\":{\"type\":\"string\"},\"dataCriacao\":{\"type\":\"string\"},\"dataEncerramento\":{\"type\":\"string\"},\"id\":{\"type\":\"long\"},\"prioridade\":{\"type\":\"string\"},\"status\":{\"type\":\"string\"},\"tipo\":{\"type\":\"string\"},\"titulo\":{\"type\":\"string\"}}}}");
	

	
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
