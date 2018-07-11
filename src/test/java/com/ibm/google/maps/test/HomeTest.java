package com.ibm.google.maps.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ibm.google.maps.model.ChromeDriverCreator;
import com.ibm.google.maps.model.WebDriverCreator;
import com.ibm.google.maps.pages.HomePage;

public class HomeTest {

    private HomePage homePage;

    private WebDriver driver;
    
    final static Logger logger = Logger.getLogger(HomeTest.class);
    
    private final static String dateTimeStatic = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()).replace(":", "_").replace(" ", "_");

    @BeforeClass(description="@BeforeClass")
    public void beforeClass() {
        PropertyConfigurator.configure("log4j.properties");
        WebDriverCreator creator = new ChromeDriverCreator();
        driver = creator.factoryMethod();
        homePage = new HomePage(driver);
    }

    @BeforeMethod(description = "@BeforeMethod")
    public void beforeTest() {
        homePage.goToBaseUrl();
    }

    @Test(description="captureGoogleMapScreens", dataProvider="NEWS")
    public void captureGoogleMapScreens(String firstDirection, String secondDirection, String firstDimensionStart, String secondDimensionStart, String firstDimensionEnd, String secondDimensionEnd) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("RUN test method: " + methodName);
        int firstCountStart = Integer.valueOf(firstDimensionStart);
        int secondCountStart = Integer.valueOf(secondDimensionStart);
        int firstCountEnd = Integer.valueOf(firstDimensionEnd);
        int secondCountEnd = Integer.valueOf(secondDimensionEnd);
        String userHome = System.getProperty("user.dir");
		String resultFolder = userHome + "\\googlemaps\\"+ dateTimeStatic;
		File dir = new File(resultFolder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
        if(homePage.isSearchPanelCollapsed())
        	homePage.expandSearchPanel();
        for(;firstCountStart<=firstCountEnd;firstCountStart++) {
        	String folderName = resultFolder+"\\"+firstDirection+" "+firstCountStart+"\u00B0\\";
	        File dir2 = new File(folderName);
			if (!dir2.exists()) {
				dir2.mkdirs();
			}
        	for(;secondCountStart<secondCountEnd;secondCountStart++) {
        		homePage.enterSearchText(firstDirection+" "+firstCountStart+"\u00B0 "+secondDirection+" "+secondCountStart+"\u00B0");
		        homePage.clickSearchButton();
				for(int zoomClicks=1;zoomClicks<=15;zoomClicks++) {
					homePage.zoomOut();
					try {
						String filename = folderName+homePage.getHeaderTitle().replace("\"", "")+"_zoomout_"+zoomClicks+".png";
						File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screenshot, new File(filename));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				
        	}
        }
			        
        
    }
    
    @AfterClass(description = "@AfterClass")
    public void afterClass() {
        homePage.quitDriver();
    }
    
    @DataProvider(name="NEWS")
    public String[][] combinationOfDirections(){
    	return new String[][] {
    		{"N","E","8","68","38","98"},
    	};
    }

}
