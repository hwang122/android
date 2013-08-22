package com.learning.saxactivity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler{
	String name, sex, subject;
	String tagName;
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("------------------end------------------");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		tagName = "";
		if(localName.equals("teacher")){
			this.print();
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("------------------begin------------------");
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		tagName = localName;
		if(localName.equals("teacher")){
			for(int i = 0; i < attributes.getLength(); i++){
				System.out.println(attributes.getLocalName(i) + "=" + attributes.getValue(i));
			}
		}
	}
	
	public void characters(char []ch, int start, int length) throws SAXException{
		if(tagName.equals("name")){
			name = new String(ch, start, length);
		}
		else if(tagName.equals("sex")){
			sex = new String(ch, start, length);
		}
		else if(tagName.equals("subject")){
			subject = new String(ch, start, length);
		}
	}
	
	private void print() {
		// TODO Auto-generated method stub
		System.out.println("name: " + name);
		System.out.println("sex: " + sex);
		System.out.println("subject: " + subject);
	}

}
