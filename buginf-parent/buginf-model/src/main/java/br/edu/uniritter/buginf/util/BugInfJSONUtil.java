package br.edu.uniritter.buginf.util;

import com.google.gson.Gson;

/**
 * Classe útilitaria para conversões em JSON
 * 
 * @author Diego
 *
 */
public class BugInfJSONUtil {
	
	private static final Gson gson = new Gson();

	public static <T> String paserObjectToJson(final T pojo) {
		return gson.toJson(pojo);
	}
	
	public static <T> T paserJsonToObject(final String json, final Class<T> pojo) {
		return gson.fromJson(json, pojo);
	}
}