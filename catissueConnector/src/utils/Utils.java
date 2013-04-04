package utils;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class Utils {
	
	public static final String USERID = "authentication.userid" ;
	public static final String PASSWORD = "authentication.password" ;
	public static final String AUTHENTICATION_URL = "athentication.service.url" ;
	public static final String CATISSUE_WS_URI = "catissue.ws.url" ;
	
	
	public static final String PROPERTIES_PATH = "/var/local/brisskit/openesb/webservice.properties" ;
	
	final static Logger logger = LogManager.getLogger( Utils.class ) ;
	
	public static Properties getConfig() {
		
		try {
			Properties properties = new Properties() ;
			properties.load( new FileInputStream( PROPERTIES_PATH ) ) ;
			return properties ;
		}
		catch( Exception ex ) {
			logger.error( "Unable to use" + PROPERTIES_PATH ) ;
		}	
		return null ;
	}
	
}
