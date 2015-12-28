package com.digitalgoetz.dockerserver;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.digitalgoetz.dockerserver.Pair;
import com.digitalgoetz.dockerserver.RestService;

public class TestRestHandler {

	static RestService testService;
	static HttpServer server;
	static Client client;

	@BeforeClass
	public static void beforeClass() throws IOException {
		Pair a = new Pair("a", "val1");
		Pair b = new Pair("b", "val2");

		testService = new RestService().buildServer(a, b);
		testService.start();

		client = ClientBuilder.newClient();
	}

	@AfterClass
	public static void afterClass() {
		testService.stop();
	}

	@Test
	public void testGet() {
		WebTarget target = client.target(testService.getUri()).path("rest").path("api");
		Response response = target.request().get();
		String entity = response.readEntity(String.class);
		assertEquals("hello", entity);
	}

	@Test
	public void testCfg() {
		WebTarget target = client.target(testService.getUri()).path("rest").path("api").path("cfg");
		Response response = target.request().get();
		String entity = response.readEntity(String.class);
		assertEquals("[{a , val1}{b , val2}]", entity);
	}

}
