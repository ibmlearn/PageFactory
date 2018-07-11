
package com.ibm.google.maps.utilities;

import org.apache.log4j.Logger;

public class FrameworkException extends Exception
{
	private static final Logger LOGGER =
	        Logger.getLogger(FrameworkException.class);

	private static final long serialVersionUID = 1L;

	private String errorMessage = null;

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public FrameworkException(final String page, final String object)
	{
		super();
		final StringBuilder message = new StringBuilder();
		try
		{
			if (page != null && !"".equals(page) && object != null
			        && !"".equals(object))
			{
				final String key =
				        page.toUpperCase() + "." + object.toUpperCase();
				message.append(key);
			}
		}
		catch (Exception e)
		{
			message.append(e.getMessage());
			LOGGER.error(
			        "Exception occurred in FrameworkException constructor", e);
		}

		this.errorMessage = message.toString();
	}

	public FrameworkException(final String message)
	{
		super(message);
		errorMessage = message;
	}

	public FrameworkException(final Throwable e)
	{
		super(e.getLocalizedMessage().split("\n")[0]);
		errorMessage = e.getMessage();
	}

	public FrameworkException(final String page, final Throwable e)
	{
		super(page, e);
		errorMessage = e.getMessage();
	}
}
