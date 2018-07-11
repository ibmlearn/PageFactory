package com.ibm.google.maps.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverCreator extends WebDriverCreator {

    final static Logger logger = Logger.getLogger(ChromeDriverCreator.class);
    
    @Override
    public WebDriver factoryMethod() {
        PropertyConfigurator.configure("log4j.properties");
        
        WebDriver driver;
                
        File currentDirectory = new File(new File(".").getAbsolutePath());
        String absolutePath = currentDirectory.getAbsolutePath();
        absolutePath = absolutePath.substring(0, absolutePath.length()-1);
        System.setProperty("webdriver.chrome.driver", "" + absolutePath + "libs\\chromedriver.exe");

        logger.info("Instantiating Chrome driver");

        final ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-web-security");
		Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    options.setExperimentalOption("prefs", prefs);
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        return driver;
    }
    
}
