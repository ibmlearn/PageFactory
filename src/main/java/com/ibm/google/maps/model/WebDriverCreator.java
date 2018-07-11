package com.ibm.google.maps.model;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverCreator {

    protected WebDriver driver;
    
    public abstract WebDriver factoryMethod();
    
}
