package com.digitalgoetz.dockerserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Jersey Resource class for all REST request processing
 */
@Path("api")
public class RestHandler {

	@Inject
	ServiceConfig config;

	@GET
	public String get() {
		return "hello";
	}

	@GET
	@Path("cfg")
	public String cfg() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		Map<String, String> map = getConfig();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String value = map.get(key);
			sb.append("{");
			sb.append(key);
			sb.append(" , ");
			sb.append(value);
			sb.append("}");
		}

		sb.append("]");
		return sb.toString();
	}

	private Map<String, String> getConfig() {
		Map<String, String> cfgPairs = new HashMap<>();

		if (config != null) {
			List<String> keys = config.getKeys();
			for (String key : keys) {
				String value = config.get(key);
				cfgPairs.put(key, value);
			}
		}

		return cfgPairs;
	}

}
