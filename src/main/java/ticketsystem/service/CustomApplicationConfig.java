package ticketsystem.service;


import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomApplicationConfig extends ResourceConfig
{
	public static final String DATA_ENVIRONMENT = "Memory";
	public static final String DATA_ENVIRONMENTTEST = "Memory";
	public CustomApplicationConfig()
	{
		packages("ticketsystem.service.controllers"); // find all resource endpoint classes in this package
		// log exchanged http messages
		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));
		register(new CorsFilter());
		register(new AuthenticationFilter());
	}
}
