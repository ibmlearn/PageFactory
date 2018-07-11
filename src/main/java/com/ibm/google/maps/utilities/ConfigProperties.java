
package com.ibm.google.maps.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Venkata_Kurapati
 *
 */
public final class ConfigProperties
{
	private static final Logger LOGGER =
	        Logger.getLogger(ConfigProperties.class);

	private static Map<String, String> content = null;

	private static Map<String, String> configMap = null;

	public static ConfigProperties configProp;

	public static ConfigProperties getInstance()
	{
		if (configProp == null)
		{
			configProp = new ConfigProperties();
			return configProp;
		}
		return configProp;
	}

	private ConfigProperties()
	{
		super();
	}

	/**
	 * @param configName
	 * @return
	 */
	public String getConfigProperty(final String configName)
	{
		LOGGER.debug("Try to fecth the content form Configuration file with key"
		        + configName);
		final String configValue = configMap.get(configName);
		LOGGER.debug("Fetching the value from the Configuration file with key '"
		        + configName + "' and the result is '" + configValue + "'");
		if (null == configValue)
		{
			LOGGER.error("The specified '" + configName
			        + "'configuartion property is not available");

		}
		return configValue;

	}

	/**
	 * @param configName
	 * @param updatedValue
	 */
	public void updateConfigProperty(final String configName,
	        final String updatedValue)
	{
		LOGGER.info("Updating the Configuration file with the property '"
		        + configName + "' and with the value '" + updatedValue + "'");
		if (content.containsKey(configName.toUpperCase()))
		{
			LOGGER.debug("Updating the existing property '" + configName
			        + "'in the Configuration file with new value '"
			        + updatedValue + "'");
			content.put(configName.toUpperCase(), updatedValue);
		}
		else
		{
			LOGGER.debug("Adding new property '" + configName
			        + "'to the Configuration file with value '" + updatedValue
			        + "'");
			content.put(configName.toUpperCase(), updatedValue);
		}
	}

	/**
	 * @param configFile
	 * @return
	 * @throws FrameworkException
	 */
	public Map<String, String> readConfigProperties(final String configFile)
	        throws FrameworkException
	{
		configMap = new HashMap<String, String>();
		final Document doc = XMLReader.getInstance().getXMLDoc(configFile);

		final NodeList nodeList = doc.getElementsByTagName("config");
		final NodeList list = nodeList.item(0).getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++)
		{
			for (int j = 0; j < list.getLength(); j++)
			{
				if (list.item(j).getNodeType() == Node.ELEMENT_NODE)
				{

					configMap.put(list.item(j).getNodeName(), list.item(j)
					        .getTextContent());
				}
			}
			// Set the configPath so that it can be resued across
			if (configMap != null)
			{
				setConfigMap(configMap);
			}
		}
		return configMap;
	}

	/**
	 * @param map
	 */
	public static void setConfigMap(Map<String, String> map)
	{
		map = ConfigProperties.configMap;
	}
}
