package com.test.xml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XmlTest {
	public static void main(String args[]) throws Exception{
		DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		File file=new File("xml");
		BufferedInputStream in=new BufferedInputStream(new FileInputStream(file));
		Document doc=builder.parse(in);
		Element root=doc.getDocumentElement();
		//	System.out.println(root.getTagName());
		NodeList children=root.getChildNodes();
		String name1="";
		for(int i=0;i<children.getLength();i++){
			Node child=children.item(i);
			
			if(child instanceof Element){
				Element childElement=(Element)child;
				//System.out.println(childElement.getTagName());
				Text textNode=(Text) childElement.getFirstChild();
				String text=textNode.getData().trim();
				if(childElement.getTagName().equals("name")){
					name1=text;
				}
				else if(childElement.getTagName().equals("p"))
					name1=text;
				System.out.println(name1);
			}
		}

	}


}
