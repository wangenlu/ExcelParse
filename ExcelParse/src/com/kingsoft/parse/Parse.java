package com.kingsoft.parse;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.kingsoft.stream.WorkBook;

public abstract class Parse {
	public InputStream stream;
	public SAXParser parser;

	public Parse(InputStream stream) {
		// 1.ʵ����SAXParserFactory����
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.����������
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		this.stream = stream;

	}

	public abstract void handle();

	

}
