package connector;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Protocols {

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://catissue:8080/catissueplusWS/rest").build();
	}
	
	public Document getCPs() {
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());	
		ClientResponse response = service.path("service").path("getCPsXML")
				.type(MediaType.APPLICATION_XML)
				.get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    factory.setNamespaceAware(true);
	    DocumentBuilder builder;
	    Document d = null;

		try {
			builder = factory.newDocumentBuilder();
			d = builder.parse(new ByteArrayInputStream(output.getBytes()));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//return output;
	    return d;
	    
		
	}
	
	public static void main(String[] args) {
		Protocols c = new Protocols();
    	System.out.println(c.getCPs());
     	
    	/*String s = c.performCall("");*/
    }
			
}
