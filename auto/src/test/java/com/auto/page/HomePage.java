package com.auto.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;
	public HomePage(WebDriver driver) {
  	  	 this.driver = driver;
  	  	 if(!driver.getCurrentUrl().equals("https://www.auto1.com/en/")) {
               throw new IllegalStateException("This is not home page, current page is: "
                          +driver.getCurrentUrl());
        }
    }
	public OurCarsPage getCarsPage() throws InterruptedException{
		driver.findElement(By.xpath("html/body/div/div/div[1]/header/div/nav[1]/ul/li[1]/a")).click();
		  Thread.sleep(5000);
		return new OurCarsPage(this.driver);
	}
}
