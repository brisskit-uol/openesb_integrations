package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

/**
 * @author Sujit Biswas
 *
 */
public class AuthClient {

	
	private static final String username = "jondoe";
    private static final String password = "jondoe";
    private Authenticator authenticator = new TestAuthenticator();
    
    private class TestAuthenticator extends java.net.Authenticator {
        public TestAuthenticator() {
            setDefault(this);
        }
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password.toCharArray());
        }
    }
	
	
	
	 public void testAccessManagerAuthorization() throws Exception {
	    	Service service = Service.create(new QName("http://j2ee.netbeans.org/wsdl/AuthAM", "AuthAMService"));
	        QName echoPort = new QName("http://j2ee.netbeans.org/wsdl/AuthAM", "AuthAMPort");
	        String url = "http://localhost:9080/AuthAMService/AuthAMPort";
	        service.addPort(echoPort, null, url); 
	        Dispatch<Source> dispatch = service.createDispatch(echoPort,
	                                                           Source.class,
	                                                           Service.Mode.PAYLOAD);

	        SOAPMessage outSoapMsg = MessageFactory.newInstance().createMessage();
	        
	        // Build the message.
	        String request = 
	             "<AuthAMOperation xmlns=\"http://j2ee.netbeans.org/wsdl/AuthAM\">" +
	             "<part1>hello</part1>" +
	             "</AuthAMOperation>";
	                    
	        final String expectedResponse = 
	             "<?xml version=\"1.0\" ?>" +
	             "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
	                "<SOAP-ENV:Body>" +
	                   "<m:AuthAMOperationResponse xmlns:m=\"http://j2ee.netbeans.org/wsdl/AuthAM\">" +
	                      "<part1 xmlns:aut=\"http://j2ee.netbeans.org/wsdl/AuthAM\" xmlns:msgns=\"http://j2ee.netbeans.org/wsdl/AuthAM\" xmlns=\"\">hello</part1>" +
	                   "</m:AuthAMOperationResponse>" +
	                "</SOAP-ENV:Body>" +
	             "</SOAP-ENV:Envelope>";

	        
	        ByteArrayInputStream bais = new ByteArrayInputStream(request.getBytes());
	        Source input = new StreamSource(bais);

	        Source output = dispatch.invoke(input);
	        System.out.println ("After invoke");

	        assert (output != null);
	        
	        StreamResult result = new StreamResult(new ByteArrayOutputStream());
	        Transformer trans = TransformerFactory.newInstance().newTransformer();
	        trans.transform(output, result);
	        ByteArrayOutputStream baos = (ByteArrayOutputStream) result.getOutputStream();
	        // Check the response content.
	        String responseContent = new String(baos.toByteArray());
	        System.out.println("Got response: \n" + responseContent + "\n");
	        assert (expectedResponse.equals(responseContent));
	    }
	    
	 

		/**
		 * @param args
		 */
		public static void main(String[] args) {
			try {
				new AuthClient().testAccessManagerAuthorization();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

}

