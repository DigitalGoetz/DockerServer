package com.digitalgoetz.dockerserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyUtil;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Jersey REST Service class
 */
public class RestService extends BasicService {

	HttpServer server = null;

	/**
	 * Application Entrypoint
	 *
	 * @param args
	 *            String[] command line args
	 * @throws Exception
	 *             thrown on error
	 */
	public static void main(final String[] args) throws Exception {
		new RestService("service.properties").buildServer().start();
		Thread.currentThread().join();
	}

	public HttpServer getServer() {
		return server;
	}

	public void start() throws IOException {
		server.start();
	}

	public void stop() {
		server.shutdownNow();
	}

	/** The Logger */
	static Logger log = Logger.getLogger(RestService.class);
	/** The Service's hostname */
	String host = "0.0.0.0";
	/** The Service's port value */
	int port = 80;

	public RestService() {
		this("service.properties");
	}

	/**
	 * Constructor
	 */
	public RestService(String propsFileName) {
		Properties properties = new Properties();
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(propsFileName)) {
			properties.load(is);
		} catch (Exception e) {
			log.error("Error reading properties file", e);
		}
		host = properties.getProperty("service.host", "0.0.0.0");
		final String portString = properties.getProperty("service.port", "80");
		port = Integer.valueOf(portString).intValue();

		serviceUri = uriBuilder.resolveTemplate("host", host).resolveTemplate("port", new Integer(port)).build();
	}

	/**
	 * Method to build an HTTPServer object
	 *
	 * @return HTTPServer
	 */
	public RestService buildServer(Pair... pairs) {
		final HttpServer httpServer = new HttpServer();
		final NetworkListener listener = new NetworkListener("listener", serviceUri.getHost(), serviceUri.getPort());
		httpServer.addListener(listener);

		final ServerConfiguration config = httpServer.getServerConfiguration();
		final CLStaticHttpHandler fileContainer = new CLStaticHttpHandler(RestService.class.getClassLoader());
		config.addHttpHandler(fileContainer, "/web");

		ServiceBinder binder = new ServiceBinder(pairs);
		final ResourceConfig rc = new ResourceConfig().register(binder).packages(getClass().getPackage().toString());
		final GrizzlyHttpContainer restContainer = new GrizzlyUtil().getContainer(rc);
		config.addHttpHandler(restContainer, "/rest");
		server = httpServer;
		return this;
	}

}
