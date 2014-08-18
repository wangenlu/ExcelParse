package com.kingsoft.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.kingsoft.log.ExcelLogger;
import com.kingsoft.log.ExcelLoggerTest;
import com.kingsoft.stream.Sheet;
import com.kingsoft.stream.WorkBook;

public class WorkBookHandler extends DefaultHandler {

	public static final String WORKBOOKVIEW = "workbookview";
	public static final String SHEET = "sheet";
	public static final String CALCPR = "calcpr";

	private WorkBook workBook;
	private Sheet sheet;
	private String sheetName;
	private ExcelLogger logger = (ExcelLogger) ExcelLogger
			.getLogger("com.kingsoft");

	public WorkBookHandler(WorkBook workBook) {
		super();
		this.workBook = workBook;
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
		System.out.println("\nԪ��: " + "localname " + localName + "  ["
				+ fullName + "]" + " ��ʼ����!");
		if (SHEET.equals(fullName.toLowerCase())) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String name = attributes.getLocalName(i).toLowerCase();
				String value = attributes.getValue(i);
				sheet = new Sheet();
				if ("name".equals(name)) {

					this.sheetName = value;
					sheet.setName(value);
				} else if ("sheetid".equals(name)) {

					sheet.setSheetId(value);

				} else if ("r:id".equals(name)) {
					sheet.setRid(value);

				}
			}

		} else if (WORKBOOKVIEW.equals(fullName.toLowerCase())) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String name = attributes.getLocalName(i).toLowerCase();
				String value = attributes.getValue(i);
				if ("activetab".equals(name)) {
					workBook.setActiveTab(Integer.parseInt(value));
				} else if ("windowheight".equals(name)) {
					workBook.setWindowHeight(Integer.parseInt(value));
				} else if ("windowwidth".equals(name)) {
					workBook.setWindowWidth(Integer.parseInt(attributes
							.getValue(i)));
				} else if ("ywindow".equals(name)) {
					workBook.setyWindow(Integer.parseInt(value));
				} else if ("xwindow".equals(name)) {
					workBook.setxWindow(Integer.parseInt(value));
				}

			}

		} else if (CALCPR.equals(fullName.toLowerCase())) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String name = attributes.getLocalName(i).toLowerCase();
				String value = attributes.getValue(i);
				if ("calcid".equals(name)) {
					workBook.setCalcId(value);
				}
			}

		}

	}

	public void endElement(String namespaceURI, String localName,
			String fullName) throws SAXException {
		// ��ӡԪ�ؽ���������Ϣ
		System.out.println("Ԫ��: " + "[" + fullName + "]" + " ��������!");
		if ("sheet".equals(fullName.toLowerCase())) {
			sheet.setWorkBook(workBook);
			workBook.addSheet(sheetName, sheet);
		}
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
		logger.warn(WorkBookHandler.class.getName(), exception.getLineNumber()
				+ exception.getMessage());
	}

	public void error(SAXParseException exception) throws SAXException {
		System.out.println("******* ERROR ******");
		System.out.println("\t��:\t" + exception.getLineNumber());
		System.out.println("\t��:\t" + exception.getColumnNumber());
		System.out.println("\t������Ϣ:\t" + exception.getMessage());
		System.out.println("********************");
		logger.error(WorkBookHandler.class.getName(), exception.getLineNumber()
				+ exception.getMessage());
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("******** FATAL ERROR ********");
		System.out.println("\t��:\t" + exception.getLineNumber());
		System.out.println("\t��:\t" + exception.getColumnNumber());
		System.out.println("\t������Ϣ:\t" + exception.getMessage());
		System.out.println("*****************************");
		logger.error(WorkBookHandler.class.getName(), exception.getLineNumber()
				+ exception.getMessage());
	}
}
