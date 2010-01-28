package com.lm.common.jaxb;

import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;


@XmlRootElement
public class MyHashMapEntryType {

    @XmlAttribute // @XmlElement and @XmlValue are also fine
    public String key; 
    
    @XmlAttribute // @XmlElement and @XmlValue are also fine
    public int value;
    
    public MyHashMapEntryType() {}
    
    public MyHashMapEntryType(Map.Entry<String,Integer> e) {
       key = e.getKey();
       value = e.getValue();
    }
    
    
    public static void main(String[] args) throws JAXBException {
    	
    	JAXBContext jc = JAXBContext.newInstance();
    	Marshaller marshaller = jc.createMarshaller();
//    	marshaller.marshal(new JAXBElement(
//    			new QName("root"),MyHashMapType.class,new MyHashMapType(m)),System.out);
	}
}
