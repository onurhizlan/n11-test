package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebDriver {
    private ChromeDriver driver;
    private String baseurl = "https://www.n11.com";


    public WebDriver() {

        System.setProperty("webdriver.chrome.driver", "C:/Users/onur_/Desktop/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseurl);
        driver.manage().window().maximize();

    }

    @BeforeClass
    public void openBrowser() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseurl);

    }

    public ChromeDriver getDriver() {
        return driver;
    }

    public void setDriver(ChromeDriver driver) {
        this.driver = driver;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }


    public WebElement findById(String id) {
        WebElement Element = driver.findElement(By.id(id));
        return Element;
    }

    public WebElement findByClassName(String name) {
        WebElement Element = driver.findElement(By.className(name));
        return Element;
    }

    public WebElement findByXpath(String xpath) {
        WebElement Element = driver.findElement(By.xpath(xpath));
        return Element;
    }
}
