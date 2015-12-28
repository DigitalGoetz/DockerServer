package com.digitalgoetz.dockerserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.digitalgoetz.dockerserver.Pair;

public class TestPair {
	
	@Test
	public void testConstructor(){
		Pair testPair = new Pair("key", "value");
		
		String key = testPair.getKey();
		String value = testPair.getValue();
		
		assertEquals("key", key);
		assertEquals("value", value);
		
		testPair.setKey(null);
		testPair.setValue(null);
		
		String nullKey = testPair.getKey();
		String nullValue = testPair.getValue();
		
		assertEquals("", nullKey);
		assertEquals("", nullValue);
	}

}
