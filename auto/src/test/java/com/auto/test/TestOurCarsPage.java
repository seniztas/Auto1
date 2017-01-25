package com.auto.test;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.auto.AutoCommons;
import com.auto.page.HomePage;
import com.auto.page.OurCarsPage;

public class TestOurCarsPage extends AutoCommons{
	
  private StringBuffer verificationErrors = new StringBuffer();
  private String car = "BMW";
  
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", geckoDriverPath);
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(baseUrl);
  }
  
  @Test
  //Verify filter was selected
  public void testVerifyFilterwasSelected() throws Exception {
	  HomePage homepage = new HomePage(this.driver);
	  OurCarsPage ourCarsPage = homepage.getCarsPage();
	  ourCarsPage.selectManufacturer(car);
	  
	  Thread.sleep(5000);
	  assertTrue(ourCarsPage.verifySelection(car));
  }
  
  @Test
  //Verify cars are BMW
  public void testVerifyCarsareBMW() throws Exception {
	  HomePage homepage = new HomePage(this.driver);
	  OurCarsPage ourCarsPage = homepage.getCarsPage();
	  ourCarsPage.selectManufacturer(car);
	  
	  Thread.sleep(5000);
	  List<WebElement> myElements = ourCarsPage.returnCarList();
	  for(int i = 1; i<= myElements.size(); i++){
		assertTrue(ourCarsPage.verifyCarsName(i, car));
	}	  
  }
  
  @Test
  //Verify each car has picture
  public void testVerifyEachCarHasPicture() throws Exception {
	  HomePage homepage = new HomePage(this.driver);
	  OurCarsPage ourCarsPage = homepage.getCarsPage();
	  ourCarsPage.selectManufacturer(car);
	  
	  Thread.sleep(5000);
	  List<WebElement> myElements = ourCarsPage.returnCarList();
	  for(int i = 1; i<= myElements.size(); i++){
		assertNotNull(ourCarsPage.verifyImg(i));
	}
  }
  //Verify each car has complete information
  @Test
  public void testVerifyEachCarhasCompleteInformation() throws Exception {    
	  HomePage homepage = new HomePage(this.driver);
	  OurCarsPage ourCarsPage = homepage.getCarsPage();
	  ourCarsPage.selectManufacturer(car);
	  
	  Thread.sleep(5000);
	  for(int i = 1; i<= ourCarsPage.returnCarList().size(); i++){
		  for(int j = 1; j<= ourCarsPage.returnCarInfo(i).size(); j++){
			  assertNotNull(ourCarsPage.returnCarInfoText(i, j));
		  }
	  }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}

