package br.edu.uniritter.buginf.mapper;

import org.apache.commons.lang3.StringUtils;

import br.edu.uniritter.buginf.type.CategoriaType;

public class RedmineCategoriaDefeitoMapper implements BugTrackingMapper<String, CategoriaType> {
	
	private static final String PERFORMANCE = "PERFORMANCE";
	private static final String ISSUES = "ISSUES";
	private static final String ISSUES_PERMISSIONS = "ISSUES PERMISSIONS";
	private static final String EMAIL_NOTIFICATIONS = "EMAIL NOTIFICATIONS";
	private static final String EMAIL_RECEIVING = "EMAIL RECEIVING";
	private static final String WIKI = "WIKI";
	private static final String PLUGIN_API = "PLUGIN API";
	private static final String PLUGIN_REQUEST = "PLUGIN REQUEST";
	private static final String SCM = "SCM";
	private static final String SCM_EXTRA = "SCM EXTRA";
	private static final String TEXT_FORMATTING = "TEXT FORMATTING";
	private static final String ACCOUNTS_AUTHENTICATION = "ACCOUNTS / AUTHENTICATION";
	private static final String DATABASE = "DATABASE";
	private static final String API="REST API";
	private static final String UI="UI";
	

	@Override
	public CategoriaType map(String categoria) {
		
		if (StringUtils.isBlank(categoria)) {
			return null;
		} else {
			categoria = StringUtils.upperCase(categoria);
		}
		
		
		switch (categoria) {
		case API:
			return CategoriaType.API;
		case UI:
			return CategoriaType.INTERFACE;
		case PLUGIN_API:
			return CategoriaType.PLUGINS;
		case PLUGIN_REQUEST:
			return CategoriaType.PLUGINS;	
		case ISSUES:
			return CategoriaType.ISSUE;
		case ISSUES_PERMISSIONS:
			return CategoriaType.ISSUE;	
		case EMAIL_NOTIFICATIONS:
			return CategoriaType.EMAILS;	
		case EMAIL_RECEIVING:
			return CategoriaType.EMAILS;		
		case WIKI:
			return CategoriaType.WIKI;
		case SCM:
			return CategoriaType.CONTROLE_VERSAO;
		case SCM_EXTRA:
			return CategoriaType.CONTROLE_VERSAO;	
		case TEXT_FORMATTING:
			return CategoriaType.TEXTO;	
		case ACCOUNTS_AUTHENTICATION:
			return CategoriaType.AUTENTICACAO;	
		case PERFORMANCE:
			return CategoriaType.PERFORMACE;		
		case DATABASE:
			return CategoriaType.BANCO_DADOS;
		default:
			return CategoriaType.NAO_DEFINIDA;
		}
	}

}
