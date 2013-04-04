package connector;

import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Patient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.Utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class Participant {

	Patient patient = null;
	ArrayList<Patient> patientList = new ArrayList<Patient>();

	public void getPatient(Node node) {

		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node currentNode = nodeList.item(i);

			if (currentNode != null) {

				if (currentNode.getNodeName().equals("cat:patient")) {
					if (patient != null) {
						patientList.add(patient);
					}
					patient = new Patient();
					System.out.println("X");
				}

				if (currentNode.getNodeName().equals("cat:patient_id")) {
					patient.setPatient_id(currentNode.getTextContent());
				}

				if (currentNode.getNodeName().equals("cat:vital_status_cd")) {
					patient.setVital_status_cd(currentNode.getTextContent());
				}

				if (currentNode.getNodeName().equals("cat:birth_date")) {
					patient.setBirth_date(currentNode.getTextContent());
				}

				if (currentNode.getNodeName().equals("cat:age_in_years_num")) {
					patient.setAge_in_years_num(currentNode.getTextContent());
				}

				if (currentNode.getNodeName().equals("cat:race_cd")) {
					patient.setRace_cd(currentNode.getTextContent());
				}

				if (currentNode.getNodeName().equals("cat:sex_cd")) {
					patient.setSex_cd(currentNode.getTextContent());
				}

				if (currentNode.getNodeName().equals("cat:study_name")) {
					patient.setStudy_name(currentNode.getTextContent());
				}

			}

			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				// calls this method for all the children which is Element

				getPatient(currentNode);
			}
		}

	}

	public Document performCall(Node xmls) {
		getPatient(xmls);
		patientList.add(patient);

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		System.out.println("X" + xmls.getTextContent() + "X");

		/*
		 * String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		 * "<pdo:patient_data xmlns:pdo=\"http://www.i2b2.org/xsd/hive/pdo/1.1/pdo\">"
		 * + "        			<pdo:patient_set>" +
		 * "   <patient download_date=\"2012-10-02T12:30:04 00:00\" import_date=\"2012-10-02T12:30:04 00:00\" sourcesystem_cd=\"BRICCS\" update_date=\"2012-10-02T12:30:04 00:00\" upload_id=\"1\">"
		 * + "   <patient_id source=\"BRICCS\">dejmo-835619175</patient_id>" +
		 * "   <param column=\"vital_status_cd\" name=\"date interpretation code\">N</param>"
		 * +
		 * "   <param column=\"birth_date\" name=\"birthdate\">1912-10-15T00:00:00.000 01:00</param>"
		 * + "   <param column=\"age_in_years_num\" name=\"age\">99</param>" +
		 * "   <param column=\"race_cd\" name=\"ethnicity\">Unknown</param>" +
		 * "   <param column=\"sex_cd\" name=\"sex\">UNSPECIFIED</param>" +
		 * "   <study_name source=\"BRICCS\">cp1356016280136</study_name>" +
		 * "  </patient>" + " </pdo:patient_set>" + "</pdo:patient_data>";
		 */

		// System.out.println(xml);

		// Form form = new Form();
		// form.add( "incomingXML", xml );
		// form.add( "activity_id", "941" );

		List<String> out = new ArrayList<String>();

		if (patientList != null) {
			int j = 0;
			for (Patient lpatient : patientList) {

				j++;
				System.out.println(j);
				System.out.println(lpatient);
				System.out.println();

				String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<pdo:patient_data xmlns:pdo=\"http://www.i2b2.org/xsd/hive/pdo/1.1/pdo\">"
						+ "        			<pdo:patient_set>"
						+ "   <patient download_date=\"2012-10-02T12:30:04 00:00\" import_date=\"2012-10-02T12:30:04 00:00\" sourcesystem_cd=\"BRICCS\" update_date=\"2012-10-02T12:30:04 00:00\" upload_id=\"1\">"
						+ "   <patient_id source=\"BRICCS\">"
						+ lpatient.getPatient_id()
						+ "</patient_id>"
						+ "   <param column=\"vital_status_cd\" name=\"date interpretation code\">"
						+ lpatient.getVital_status_cd()
						+ "</param>"
						+ "   <param column=\"birth_date\" name=\"birthdate\">"
						+ lpatient.getBirth_date()
						+ "</param>"
						+ "   <param column=\"age_in_years_num\" name=\"age\">"
						+ lpatient.getAge_in_years_num()
						+ "</param>"
						+ "   <param column=\"race_cd\" name=\"ethnicity\">"
						+ lpatient.getRace_cd()
						+ "</param>"
						+ "   <param column=\"sex_cd\" name=\"sex\">"
						+ lpatient.getSex_cd()
						+ "</param>"
						+ "   <study_name source=\"BRICCS\">"
						+ lpatient.getStudy_name()
						+ "</study_name>"
						+ "  </patient>"
						+ " </pdo:patient_set>"
						+ "</pdo:patient_data>";

				Form form = new Form();
				form.add("incomingXML", xml);
				form.add("activity_id", "941");

				System.out.println(xml);

				ClientResponse response = service.path("service").path("pdo")
						.type(MediaType.APPLICATION_XML)
						.post(ClientResponse.class, form);

				System.out.println("performCall Output from Server .... \n");

				String output = response.getEntity(String.class);

				out.add(output);
				// out.add("Success");

			}
		}

		// ClientResponse response =
		// service.path("service").path("pdo").type(MediaType.APPLICATION_XML).post(ClientResponse.class,
		// form);
		// System.out.println("performCall Output from Server .... \n");
		// String output = response.getEntity(String.class);
		// System.out.println(output);
		// return output;

		Document document = createContactNode(out);

		//return document.toString();
		return document;
		
		// return
		// "<result><status>Success</status></result><result><status>Failure</status></result>";
		// return createContactNode(out);
	}

	public Document createContactNode(List<String> outp) {

		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			Element root = document.createElement("results");
			document.appendChild(root);

			for (String res : outp) {
				Element result = document.createElement("result");
				Element status = document.createElement("status");
				status.appendChild(document.createTextNode(res));
				result.appendChild(status);
				
				//document.appendChild(result); //added
				
				root.appendChild(result);
			}

		} catch (ParserConfigurationException parserException) {
			parserException.printStackTrace();
		}

		// set up a transformer
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans;
		try {
			trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(document);
			try {
				trans.transform(source, result);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String xmlString = sw.toString();

			// print xml
			System.out.println("Here's the xml:\n\n" + xmlString);

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document;
	}

	// /catissueplusWS/rest/service/pdo

	private static URI getBaseURI() {
		return UriBuilder.fromUri( Utils.getConfig().getProperty( Utils.CATISSUE_WS_URI ) ).build() ;
	}

}
