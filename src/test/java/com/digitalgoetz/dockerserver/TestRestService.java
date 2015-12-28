package com.digitalgoetz.dockerserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;

import com.digitalgoetz.dockerserver.RestService;

public class TestRestService {

	@Test
	public void testConstructorDefaults() {
		RestService testService = new RestService();
		assertNotNull(testService);

		String uriString = testService.getUri();
		assertEquals("http://0.0.0.0:9999/", uriString);
	}

	@Test
	public void testConstructorBadProps() {
		RestService testService = new RestService("badProps");
		assertNotNull(testService);

		String uriString = testService.getUri();
		assertEquals("http://0.0.0.0:80/", uriString);
	}

	@Test
	public void testBuildServer() {
		RestService testService = new RestService();
		assertNotNull(testService);

		String uriString = testService.getUri();
		assertEquals("http://0.0.0.0:9999/", uriString);

		HttpServer testServer = testService.buildServer().getServer();
		assertNotNull(testServer);
		assertFalse(testServer.isStarted());
	}

	@Test
	public void testBuildServerMappings() {
		RestService testService = new RestService();
		assertNotNull(testService);

		String uriString = testService.getUri();
		assertEquals("http://0.0.0.0:9999/", uriString);

		HttpServer testServer = testService.buildServer().getServer();
		assertNotNull(testServer);
		assertFalse(testServer.isStarted());
	}
}
