package ie.gmit.sw;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private String username;
	private String ip;
	private int port;
	private String downloadDir;

	public Parser() {
		parse();
	}

	public void parse() {
		try {

			File fXmlFile = new File("conf.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName("client-config");
			Node root = nList.item(0);

			if (root.getNodeType() == Node.ELEMENT_NODE) {

				Element e = (Element) root;
				setUsername(e.getAttribute("username"));
				setIp(e.getElementsByTagName("server-host").item(0).getTextContent());
				setPort(Integer.parseInt(e.getElementsByTagName("server-port").item(0).getTextContent()));
				setDownloadDir(e.getElementsByTagName("download-dir").item(0).getTextContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDownloadDir() {
		return downloadDir;
	}

	public void setDownloadDir(String downloadDir) {
		this.downloadDir = downloadDir;
	}
}
