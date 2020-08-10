package org.example.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class HelperParserXML {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelperParserXML.class);

	public void parseXml() throws Exception {

		File xmlFile = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\parserXML\\orders.xml");// only for test purpose..this path should no be hardcoded!!!
		DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
		Document document = dBuilder.parse(xmlFile);
		document.getDocumentElement().normalize();

		String idNode = document.getElementsByTagName("order").item(1).getAttributes().getNamedItem("ID").getNodeValue();
		String substringNode = idNode.substring(0, idNode.length() - 2);

		dFactory.setNamespaceAware(true);
		XPathFactory xFactory = XPathFactory.newInstance();
		XPath xPath = xFactory.newXPath();
		XPathExpression allProductsExpression = xPath.compile("//orders/order/product/supplier/text()");
		NodeList productNodes = (NodeList) allProductsExpression.evaluate(document, XPathConstants.NODESET);

		List<String> supplierNames = new ArrayList<>();

		for (int i = 0; i < productNodes.getLength(); ++i) {
			Node productName = productNodes.item(i);
			String textContent = productName.getTextContent();
			supplierNames.add(textContent);
		}

		LOGGER.info("Found suppliers for parsing: " + supplierNames);

		for (String supplier : supplierNames) {
			String xpathQuery = "/orders/order/product[supplier='" + supplier + "']";
			xPath = xFactory.newXPath();
			XPathExpression query = xPath.compile(xpathQuery);
			NodeList productNodesFiltered = (NodeList) query.evaluate(document, XPathConstants.NODESET);

			Document suppXml = dBuilder.newDocument();

			Element rootElement = suppXml.createElement("products");
			suppXml.appendChild(rootElement);

			for (int i = 0; i < productNodesFiltered.getLength(); i++) {
				Node productNode = productNodesFiltered.item(i);
				Node clonedNode = productNode.cloneNode(true);
				suppXml.adoptNode(clonedNode);
				rootElement.appendChild(clonedNode);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(suppXml);
			StreamResult result = new StreamResult(new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\parserXML\\" + supplier + substringNode + ".xml"));
			transformer.transform(source, result);

			LOGGER.info("Done for supplier: " + supplier);
		}
	}
}
