package com.lm.common.jaxb.marshall;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * This is consistent with the XML Schema spec, 
 * where it essentially states that the element defaults do not kick in when the element is absent. 
 * Attribute default values do not have this problem, 
 * so if you can change the schema, 
 * changing it to an attribute is usually a better idea. 
 * Alternatively, depending on your expectation, 
 * setting the field to a default value in Java may achieve what you are looking for.
 * 
 * @author     lubq <lubq@adv.emcom.jp>
 * @copyright  2009,Adv.EMCOM
 */
@XmlRootElement
class Foo {
  @XmlElement 
  public String a="value";
  
  
  public static void main(String[] args) throws JAXBException {
	  JAXBContext jc = JAXBContext.newInstance(Foo.class);
	  Marshaller  marshaller = jc.createMarshaller();
	  marshaller.marshal(new Foo(),System.out);
  }
}
