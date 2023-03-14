package com.ass.one;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestngOne {
	private  WebDriver     driver;
    private  WebDriverWait wait;
    final private String  URL1 = "http://iamneo.ai";
    final private String  URL2 = "https://www.facebook.com";
    @BeforeClass
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,30);
        driver.manage().window().maximize();
        }
    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
  @Test
    public void T03_backForwardRefresh() throws InterruptedException {
        driver.navigate().to(URL1);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.id("neo-products-cover")));
        String s=driver.getTitle();
        String str="We are hiring!";
        if(s.equals(str))
        	{
        	  System.out.println("PASS");
        	}
        else
        	System.out.println("FAIL");
        driver.navigate().to(URL2);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.id("email")));
        Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
        
        driver.navigate().back();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated (By.id("neo-products-cover")));
        Assert.assertEquals(driver.getTitle(), "Learning and assessment solution for Universities and Enterprises");
       
       
        System.out.println(driver.getCurrentUrl());
        
        
        driver.navigate().forward();
        
        wait.until(ExpectedConditions.presenceOfElementLocated (By.id("email")));
        Assert.assertEquals("Facebook – log in or sign up", driver.getTitle());
        driver.navigate().refresh();
    }
}