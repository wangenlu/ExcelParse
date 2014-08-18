package com.kingsoft.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.kingsoft.log.ExcelLogger;
import com.kingsoft.log.ExcelLoggerTest;
import com.kingsoft.stream.Sheet;
import com.kingsoft.stream.WorkBook;

public class SheetHandler extends DefaultHandler {
	private StringBuffer buf;
	private WorkBook workBook;
	private Sheet sheet;
	private String sheetName;
	private ExcelLogger logger= (ExcelLogger) ExcelLogger.getLogger("com.kingsoft");
	
	
	public SheetHandler(Sheet sheet) {
		super();
		this.sheet = sheet;
	}

	public void startDocument() throws SAXException {
		System.out.println("*******��ʼ�����ĵ�*******");
	}

	public void endDocument() throws SAXException {
		System.out.println("*******�����ĵ�����*******");
	}

	public void startPrefixMapping(String prefix, String uri) {
		System.out.println("\nǰ׺ӳ��: " + prefix + " ��ʼ!" + "  ����URI��:" + uri);
	}

	public void endPrefixMapping(String prefix) {
		System.out.println("\nǰ׺ӳ��: " + prefix + " ����!");
	}

	public void startElement(String namespaceURI, String localName,
			String fullName, Attributes attributes) throws SAXException {
		System.out.println("\nԪ��: " + "[" + fullName + "]" + " ��ʼ����!");
		if("sheet".equals(fullName.toLowerCase())){	
			sheet=new Sheet();
		}
		// ��ӡ��������Ϣ
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println("\t��������:" + attributes.getLocalName(i) + " ����ֵ:"
					+ attributes.getValue(i));
			
			
		}
		

	}

	public void endElement(String namespaceURI, String localName,
			String fullName) throws SAXException {
		// ��ӡԪ�ؽ���������Ϣ
		System.out.println("Ԫ��: " + "[" + fullName + "]" + " ��������!");
	
	}

	public void characters(char[] chars, int start, int length)
			throws SAXException {
		
	}

	public void warning(SAXParseException exception) {
		
		System.out.println("*******WARNING******");
		System.out.println("\t��:\t" + exception.getLineNumber());
		System.out.println("\t��:\t" + exception.getColumnNumber());
		System.out.println("\t������Ϣ:\t" + exception.getMessage());
		System.out.println("********************");
		logger.warn(SheetHandler.class.getName(), exception.getLineNumber()+exception.getMessage());	
	}

	public void error(SAXParseException exception) throws SAXException {
		System.out.println("******* ERROR ******");
		System.out.println("\t��:\t" + exception.getLineNumber());
		System.out.println("\t��:\t" + exception.getColumnNumber());
		System.out.println("\t������Ϣ:\t" + exception.getMessage());
		System.out.println("********************");
		logger.error(SheetHandler.class.getName(), exception.getLineNumber()+exception.getMessage());	
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("******** FATAL ERROR ********");
		System.out.println("\t��:\t" + exception.getLineNumber());
		System.out.println("\t��:\t" + exception.getColumnNumber());
		System.out.println("\t������Ϣ:\t" + exception.getMessage());
		System.out.println("*****************************");
		logger.error(SheetHandler.class.getName(), exception.getLineNumber()+exception.getMessage());	
	}
}
