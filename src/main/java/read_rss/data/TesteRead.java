package read_rss.data;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdom2.input.DOMBuilder;
import org.jsoup.Jsoup;
//  w  w w  .j ava 2s.  c om
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class TesteRead {
	
	public String readLink(String papel) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    Document doc = factory.newDocumentBuilder().parse("https://br.tradingview.com/feed/?symbol="+papel);
	
	    Element root 				= doc.getDocumentElement();
	    XPath xPath 				= XPathFactory.newInstance().newXPath();
	    XPathExpression expression 	= xPath.compile("//item");
	    NodeList nl 				= (NodeList) expression.evaluate(root, XPathConstants.NODESET);
	    
	    System.out.println("Found " + nl.getLength() + " items...");
	    
	    for (int index = 0; index < nl.getLength(); index++) {
	        
	    	Node node 	= nl.item(index);
	        expression 	= xPath.compile("title");
	        Node child 	= (Node) expression.evaluate(node, XPathConstants.NODE);
	        
	        expression = xPath.compile("link");
	        Node test 	= (Node) expression.evaluate(node, XPathConstants.NODE);
	        
//	        System.out.println(child.getTextContent());
//	        System.out.println(test.getTextContent());
//	        String value = test.getTextContent();
	               
	        String url 			= test.getTextContent();
	        Document document 	= ((Document) Jsoup.connect(url).get());
			String value 		= ((org.jsoup.nodes.Document) document).body().getElementsByClass("tv-idea-label").get(0).text();
			
			System.out.println(value);
	        	        
	    }
	
	    return papel;
	    
	}    
	
}
