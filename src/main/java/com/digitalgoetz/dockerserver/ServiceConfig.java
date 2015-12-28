package com.digitalgoetz.dockerserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Jersey Service Configuration Object
 */
public class ServiceConfig {

	/** List of Key-Value Pairs */
	List<Pair> pairs = new ArrayList<>();

	/**
	 * Get Method to obtain the Value for a contained K-V pair
	 *
	 * @param key
	 *            String
	 * @return String or null if no key matches the input String
	 */
	public String get(final String key) {
		for (final Pair pair : pairs) {
			if (pair.getKey().equals(key)) {
				return pair.getValue();
			}
		}
		return "";
	}

	/**
	 * Put Method to store a K-V pair (Pair input)
	 *
	 * @param newPair
	 *            Pair
	 */
	public boolean put(final Pair newPair) {
		for (final Pair pair : pairs) {
			if (pair.getKey().equals(newPair.getKey())) {
				return false;
			}
		}
		pairs.add(newPair);
		return true;
	}

	/**
	 * Put Method to store a K-V pair (individual Strings for input)
	 *
	 * @param key
	 *            String
	 * @param value
	 *            String
	 */
	public boolean put(final String key, final String value) {
		for (final Pair pair : pairs) {
			if (pair.getKey().equals(key)) {
				return false;
			}
		}
		pairs.add(new Pair(key, value));
		return true;

	}

	public List<String> getKeys() {
		List<String> keys = new ArrayList<>();
		for (Pair pair : pairs) {
			keys.add(pair.getKey());
		}
		return keys;
	}

}
