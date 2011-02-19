package xmlTest;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLreader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			File xmlFile = new File("C:\\Users\\Marco\\Desktop\\iTunes Music Library.xml");
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document xmlDoc = db.parse(xmlFile);
			xmlDoc.getDocumentElement().normalize();
			System.out.println("The root element is: " + xmlDoc.getDocumentElement().getNodeName());
			NodeList nLst = xmlDoc.getElementsByTagName("dict");
			System.out.println("Here are the employees in the xml doc");
			
			for (int k=0;k<nLst.getLength();k++){
				Node kNode = nLst.item(k);
				if (kNode.getNodeType() == Node.ELEMENT_NODE){
					Element xElement = (Element) kNode;
					System.out.println("Name: " + getTagValue("firstname", xElement) + " " + getTagValue("lastname", xElement));
				}
			}
			
			
		}catch(Exception e){
			System.err.println("Shit happens");
			e.printStackTrace();
		}
	}
	
	private static String getTagValue(String xtag, Element xelem){
		NodeList getTagList = xelem.getElementsByTagName(xtag).item(0).getChildNodes();
		Node nodeValue = (Node) getTagList.item(0);
		return nodeValue.getNodeValue();
	}

}
