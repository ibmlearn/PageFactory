package com.ibm.google.maps.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    final static Logger logger = Logger.getLogger(BasePage.class);
    
    private WebDriver driver;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /*
     * Getters and setters
     */
    public final WebDriver getDriver() {
        return driver;
    }

    public final void closeDriver() {
        logger.info("Close web driver");
        driver.close();
    }
    
    public final void quitDriver() {
        logger.info("Quit web driver");
        driver.quit();
    }
    
}
