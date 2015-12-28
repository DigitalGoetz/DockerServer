package com.digitalgoetz.dockerserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.digitalgoetz.dockerserver.Pair;
import com.digitalgoetz.dockerserver.ServiceConfig;

public class TestServiceConfig {

	@Test
	public void test() {
		Pair testPair = new Pair("sample", "example");
		Pair duplicateTestPair = new Pair("sample", "neversaved");
		ServiceConfig testConfig = new ServiceConfig();

		boolean inserted = false;
		inserted = testConfig.put(testPair);
		assertTrue(inserted);
		inserted = testConfig.put("testKey", "keyValuePair");
		assertTrue(inserted);
		inserted = testConfig.put("testKey", "neversaved");
		assertFalse(inserted);
		inserted = testConfig.put(duplicateTestPair);
		assertFalse(inserted);

		String testValue1 = testConfig.get("testKey");
		assertEquals("keyValuePair", testValue1);

		String testValue2 = testConfig.get("sample");
		assertEquals("example", testValue2);

		String testValue3 = testConfig.get("nothing");
		assertEquals("", testValue3);

		List<String> keys = testConfig.getKeys();
		assertEquals(2, keys.size());
		assertTrue(keys.contains("sample"));
		assertTrue(keys.contains("testKey"));

	}

}
