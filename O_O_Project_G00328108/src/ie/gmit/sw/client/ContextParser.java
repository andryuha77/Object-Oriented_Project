package ie.gmit.sw.client;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ContextParser {
	private Context ctx;

	public ContextParser(Context ctx) {
		super();
		this.ctx = ctx;
	}
	
	public void init() throws Throwable{
		/* These three lines are part of JAXP (Java API for XML Processing) and are designed to
		 * completely encapsulate how a DOM node tree in manufactured. The concrete classes that
		 * are doing the actual work are part of the Apache Xerces project. JAXP shields us from
		 * having to know and understand the complexity of Xerces through encapsulating the
		 * process.
		 */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(Context.CONF_FILE);
		
		/* The type Document in the line above is an org.w3c.dom.Document. From this
		 * point on in the method, we will only use the DOM "STANDARD" to do the
		 * processing. DOM is language neutral and completely abstract. As a result,
		 * it is completely stable! The DOM standard hasn't changed much since the
		 * year 2000...! Abstractions are highly stable and can be relied upon. 
		 */
		Element root = doc.getDocumentElement(); //Get the root of the node tree
		NodeList children = root.getChildNodes(); //Get the child node of the root
		
		//Find the elements database and driver
		for (int i = 0; i < children.getLength(); i++){ //Loop over the child nodes
			Node next = children.item(i); //Get the next child
			
			if (next instanceof Element){ //Check if it is an element node. There are 12 different types of Node
				Element e = (Element) next; //Cast the general node to an element node
				
				if (e.getNodeName().equals("server-details")){ //Check if it is the element <database>
					NamedNodeMap atts = e.getAttributes(); //Get the attributes as a map of name/value pairs
					
					for (int j = 0; j < atts.getLength(); j++){ //Look over the attributes. This is similar to looping over a ket set in a Map
						if (atts.item(j).getNodeName().equals("username")){ //If host="127.0.0.1"
							ctx.setUsername(atts.item(j).getNodeValue()); //Update the contex object
						}
						else if (atts.item(j).getNodeName().equals("host")){ //If host="127.0.0.1"
							ctx.setHost(atts.item(j).getNodeValue()); //Update the contex object
						}
						else if (atts.item(j).getNodeName().equals("port")){ //Any port in a storm...
							ctx.setPort(Integer.parseInt(atts.item(j).getNodeValue())); //Update the contex object
						}
					}
					
				}
				else if (e.getNodeName().equals("download-dir")){ 
					String downloadDir = e.getFirstChild().getNodeValue(); //Read the text ie.gmit.sw.MyDBDriverImpl as a String
					ctx.setDir(downloadDir);
				}
			}
		}		
	}

	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}
}
