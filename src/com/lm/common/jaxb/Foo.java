package com.lm.common.jaxb;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;





/**
 *  则在属性变量前不需要声明  @XmlAttribute ，@XmlElement  等 ，但需要 getter，setter
 *  
 *  在类名前需要声明@XmlRootElement
 *  
 *  
 *  
 * @author     lubq <lubq@adv.emcom.jp>
 * @copyright  2009,Adv.EMCOM
 */




//@XmlRootElement(name = "foo")
//@XmlType(name = "Foo")


@XmlRootElement
public class Foo {
	@XmlElement(defaultValue="helen")
	  String name;
//	@XmlElement
	  String content;
	  
	
	
	
////	  @XmlAttribute
////	  String name;
	  
//	  @XmlElement
//	  String name;
//	  @XmlElement
//	  String content;
	  
	  
	  
	  public static void main(String[] args) throws JAXBException, URISyntaxException, InterruptedException {
			JAXBContext jc = JAXBContext.newInstance(Foo.class);
			
			System.out.println(jc.toString());

			
			Unmarshaller um = jc.createUnmarshaller();
			
			um.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			
//			URL url = Foo.class.getResource("/");
			URL url = Foo.class.getResource("/com/lm/common/jaxb/xml/foo.xml");
			System.out.println(url.toString());
			System.out.println(url.toURI());

			
//			URI uri = Foo.class.getClassLoader().getResourceAsStream("com.lm.common.jaxb.xml.foo.xml").toURI();
			
			File f = new File(url.toURI());
			System.out.println(f.getName());
			
			Foo fo = (Foo)um.unmarshal(
					new File(url.toURI()));
			
			System.out.println("Foo = "+ToStringBuilder.reflectionToString(fo));
		
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}



	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	  
	  
	  
}
