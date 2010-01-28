package com.lm.common.jaxb.marshall;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

@XmlRootElement
class Point {
//	@XmlElement
	private int x;
//	@XmlElement
	private int y;
	
	Point(){
		
	}
	Point(int xvar, int yvar) {
		this.x = xvar;
		this.y = yvar;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public static void main(String[] args) throws JAXBException, ParserConfigurationException {
		
		  JAXBContext jc = JAXBContext.newInstance(Point.class);
		  Marshaller  marshaller = jc.createMarshaller();
		  
		  marshaller.marshal( new Point(1,3), System.out );
		  marshaller.marshal( new Point(1,3), new File("Point-out.xml") );
		  
		  
		  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  dbf.setNamespaceAware(true);
		  Document doc = dbf.newDocumentBuilder().newDocument();

		  marshaller.marshal( new Point(1,3), doc );
		  
	}
}