package br.edu.uniritter.buginf.mapper;

import java.util.HashMap;
import java.util.Map;

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
	
	private static final Map<String, CategoriaType> CATEGORIA_MAP = new HashMap<String, CategoriaType>();
	
	
	static {
		CATEGORIA_MAP.put(API, CategoriaType.API);
		CATEGORIA_MAP.put(UI, CategoriaType.INTERFACE);
		CATEGORIA_MAP.put(PLUGIN_API, CategoriaType.PLUGINS);
		CATEGORIA_MAP.put(PLUGIN_REQUEST, CategoriaType.PLUGINS);
		CATEGORIA_MAP.put(ISSUES, CategoriaType.ISSUE);
		CATEGORIA_MAP.put(ISSUES_PERMISSIONS, CategoriaType.ISSUE);
		CATEGORIA_MAP.put(EMAIL_NOTIFICATIONS, CategoriaType.EMAILS);
		CATEGORIA_MAP.put(EMAIL_RECEIVING, CategoriaType.EMAILS);
		CATEGORIA_MAP.put(WIKI, CategoriaType.WIKI);
		CATEGORIA_MAP.put(SCM, CategoriaType.CONTROLE_VERSAO);
		CATEGORIA_MAP.put(SCM_EXTRA, CategoriaType.CONTROLE_VERSAO);
		CATEGORIA_MAP.put(TEXT_FORMATTING, CategoriaType.TEXTO);
		CATEGORIA_MAP.put(ACCOUNTS_AUTHENTICATION, CategoriaType.AUTENTICACAO);
		CATEGORIA_MAP.put(PERFORMANCE, CategoriaType.PERFORMACE);
		CATEGORIA_MAP.put(DATABASE, CategoriaType.BANCO_DADOS);
	}

	@Override
	public CategoriaType map(String categoria) {
		
		if (StringUtils.isBlank(categoria)) {
			return null;
		} 

		return CATEGORIA_MAP.get(StringUtils.upperCase(categoria));
	}

}
