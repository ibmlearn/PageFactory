package com.ibm.google.maps.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.google.maps.modules.HomeModule;

//BasePage
public class HomePage extends BasePage {

    final static Logger logger = Logger.getLogger(HomePage.class);
    
    private WebDriver driver;
    private final String BASE_URL = "https://www.google.com/maps/";
    
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    /* 
     * @FindBy, FindBys, FindByAll
     */
    @FindBy(how = How.XPATH, using = HomeModule.BUTTON_SIGN_IN_XPATH)
    private WebElement signInButton;
    
    @FindBy(how = How.XPATH, using = HomeModule.BUTTON_EXPAND_COLLAPSE)
    private WebElement expandCollapseButton;

    @FindBy(how = How.XPATH, using = HomeModule.BUTTON_WIDGET_PANE_TOGGLE_EXPAND)
    private WebElement buttonExpandSearch;

    @FindBy(how = How.XPATH, using = HomeModule.BUTTON_WIDGET_PANE_TOGGLE_COLLAPSE)
    private WebElement buttonCollapseSearch;

    @FindBy(how = How.XPATH, using = HomeModule.SEARCH_BOX)
    private WebElement searchBox;

    @FindBy(how = How.XPATH, using = HomeModule.SEARCH_BUTTON)
    private WebElement searchButton;
    
    @FindBy(how = How.XPATH, using = HomeModule.HEADER_TITLE_TEXT)
    private WebElement headerTitle;
    
    @FindBy(how = How.XPATH, using = HomeModule.ICON_TO_DIAPLAY_SATELLITE_OR_MAP_IMAGE)
    private WebElement mapOrSatelliteImageIcon;
    
    @FindBy(how = How.XPATH, using = HomeModule.LABEL_TO_DISPLAY_SATELLITE_OR_MAP_IMAGE)
    private WebElement mapOrSatelliteImageLabel;
    
    @FindBy(how = How.XPATH, using = HomeModule.BUTTON_ZOOM_IN)
    private WebElement zoomInButton;
    
    @FindBy(how = How.XPATH, using = HomeModule.BUTTON_ZOOM_OUT)
    private WebElement zoomOutButton;    
    
    @FindBys({@FindBy(how = How.XPATH, using = HomeModule.BUTTON_ZOOM_OUT), @FindBy(how = How.XPATH, using = HomeModule.BUTTON_RIPPLE)})
    private WebElement zoomOutRipple;

    public HomePage goToBaseUrl() {
        logger.info("Go to base url "+BASE_URL);
        driver.get(BASE_URL);
        //driver.navigate().to(BASE_URL);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new HomePage(driver);
    }

    public final boolean signInButtonExists() {
        logger.info("Check the sign in button displayed on home page.");
        return signInButton.isDisplayed();
    }
    
    public final boolean isSearchPanelCollapsed() {
    	logger.info("verifying the search panel expansion");
    	return expandCollapseButton.getAttribute("class").contains("widget-pane-collapsed"); 
    }
    
    public final void expandSearchPanel() {
    	logger.info("Expanding search widget slider.");
    	buttonExpandSearch = (new WebDriverWait(driver,TimeUnit.MILLISECONDS.toSeconds(10)).until(ExpectedConditions.visibilityOf(buttonExpandSearch)));
    	buttonExpandSearch.click();
    	buttonCollapseSearch = (new WebDriverWait(driver,TimeUnit.MILLISECONDS.toSeconds(10)).until(ExpectedConditions.visibilityOf(buttonCollapseSearch)));
    }
    
    public final void collapseSearchPanel() {
    	logger.info("Collapsing search widget slider.");
    	buttonCollapseSearch = (new WebDriverWait(driver,TimeUnit.MILLISECONDS.toSeconds(10)).until(ExpectedConditions.visibilityOf(buttonCollapseSearch)));
    	buttonCollapseSearch.click();
    	buttonExpandSearch = (new WebDriverWait(driver,TimeUnit.MILLISECONDS.toSeconds(10)).until(ExpectedConditions.visibilityOf(buttonExpandSearch)));
    }
    
    public final void enterSearchText(String text) {
    	searchBox.clear();
    	searchBox.sendKeys(text);
    }
    
    public final void clickSearchButton() {
    	searchButton.click();
    }
    
    public final String getHeaderTitle() {
    	return headerTitle.getText();
    }
    
    public final void clickMapOrSatelliteImageIcon() {
    	mapOrSatelliteImageIcon.click();
    }
    
    public final String getMapOrSatelliteImageLabel() {
    	return mapOrSatelliteImageLabel.getText();
    }
    
    public final void zoomIn() {
    	zoomInButton.click();
    }
    
    public final void zoomOut() {
    	zoomOutButton.click();
    	try {
    		Thread.sleep(1000);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	//new WebDriverWait(driver,TimeUnit.MILLISECONDS.toSeconds(10)).until(ExpectedConditions.invisibilityOf(zoomOutRipple));
    }

}
