package test.webface;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public final class JettyServerRunner {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(0);
		server.addHandler(new WebAppContext("WebContent", "/SkillTestWeb"));
		server.stop();
		server.start();
		
		int port = server.getConnectors()[0].getLocalPort();
		System.out.println(port);
		server.join();
	}

}
