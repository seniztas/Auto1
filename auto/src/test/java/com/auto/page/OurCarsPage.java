package com.auto.page;

import org.openqa.selenium.WebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OurCarsPage{
	
	private static final String SEARCH_FIELD_XPATH = "/html/body/div/div/div[1]/div[2]/aside/form/div/ul/li";
	private static final String SEARCH_RESULT_XPATH = "//*[@id='car-list']/li";
	private static final String TEXTBOX_XPATH = "html/body/div/div/div[1]/div[2]/aside/form/div/div/span/span[1]/span/ul/li[1]";
	private WebDriver driver;

     public OurCarsPage(WebDriver driver) {
   	  	 this.driver = driver;
   	  	 
   	  	 if(!driver.getCurrentUrl().equals("https://www.auto1.com/en/our-cars")) {
                throw new IllegalStateException("This is not our cars page, current page is: "
                           +driver.getCurrentUrl());
         }
     }

     public void selectManufacturer(String manufacturerName) throws Exception {
 		List<WebElement> manufacturers = driver.findElements(By.xpath(SEARCH_FIELD_XPATH));
 		for(WebElement e : manufacturers) {
 		 if(e.getText().contains(manufacturerName)){
             e.click();
             Thread.sleep(5000);
             return;
             }
 		}
 		
 		throw new Exception("Unable to find given manufaturer: " + manufacturerName);
     }
     
     public List<WebElement> returnCarList(){
    	 return driver.findElements(By.xpath(SEARCH_RESULT_XPATH));
     }
     
     public  boolean verifySelection(String manufacturerName){
    	return driver.findElement(By.xpath(TEXTBOX_XPATH)).getText().contains(manufacturerName);
     }
     public  boolean verifyCarsName(int i, String manufacturerName){
     	return driver.findElement(By.xpath("//*[@id='car-list']/li["+i+"]/div[3]/div")).getText().contains(manufacturerName);
      }
     
     public  String verifyImg(int i){
      	return driver.findElement(By.xpath("//*[@id='car-list']/li["+i+"]/div[2]/img")).getAttribute("src");
       }
     public List<WebElement> returnCarInfo(int i){
    	 return driver.findElements(By.xpath("//*[@id='car-list']/li["+i+"]/div[3]/table/tbody/tr"));
     }
     public String returnCarInfoText(int i, int j){
    	 return driver.findElement(By.xpath("//*[@id='car-list']/li["+i+"]/div[3]/table/tbody/tr["+j+"]/td[2]")).getText();
     }
}
