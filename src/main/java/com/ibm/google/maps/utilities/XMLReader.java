package com.ibm.google.maps.utilities;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XMLReader
{
	private static final Logger LOGGER = Logger.getLogger(XMLReader.class);

	private static XMLReader readXML = null;

	private XMLReader()
	{
		super();
	}

	public static XMLReader getInstance()
	{
		if (null == readXML)
		{
			readXML = new XMLReader();
		}
		return readXML;
	}

	public Document getXMLDoc(final String xmlFilePath)
	        throws FrameworkException
	{
		try
		{
			LOGGER.info("Started reading of the xml file: " + xmlFilePath);
			final DocumentBuilderFactory dbFactory =
			        DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			final Document xmlDOC = dBuilder.parse(new File(xmlFilePath));
			xmlDOC.getDocumentElement().normalize();
			LOGGER.info("Completed reading of the xml file: " + xmlFilePath);
			return xmlDOC;
		}
		catch (ParserConfigurationException e)
		{
			LOGGER.error("Exception occured while reading the xml file: "
			        + xmlFilePath);
			throw new FrameworkException(
			        "Exception occured while reading the xml file. Exception is:: ",
			        e);
		}
		catch (SAXException e)
		{
			throw new FrameworkException("SAXException : ", e);
		}
		catch (IOException e)
		{
			throw new FrameworkException("IOException:: ", e);
		}
	}

	
}