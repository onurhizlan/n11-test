package org.example;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.junit.Assert;

import java.io.IOException;

public class TestCase{
    private static WebDriver webdriver = new WebDriver();
    private static CsvReader csvReader;
    private static ChromeDriver driver;
    private static final String email = "onur_hzln@hotmail.com";
    private String productPrice = null;



    public TestCase() throws IOException, InterruptedException, CsvValidationException {
        driver = webdriver.getDriver();
        csvReader = new CsvReader();
    }

    @After
    public static void tearDown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }

    @Test(description = "www.n11.com sitesi açılır.")
    public void t1_1_openWebsite() throws InterruptedException {

        Assert.assertTrue(driver.getTitle().equals("n11.com - Alışverişin Uğurlu Adresi"));
        System.out.println("www.n11.com sitesi açılır.");
        Thread.sleep(3000);

    }

    @Test(description = "Ana sayfanın açıldığı kontrol edilir. Siteye login olunur. Login\n" +
                        "olurken kullanıcı adı ve şifre bilgisini csv veya benzeri bir dosyadan okumalıdır.")
    public void t1_2_openPage() throws InterruptedException {

        webdriver.findByClassName("btnSignIn").click();
        Assert.assertEquals(driver.getTitle(),"Giriş Yap - n11.com");
        System.out.println("Üye Giriş Sayfası Açıldı");
        Thread.sleep(3000);

    }


    @Test(description = "Login işlemi kontrol edilir.")

    public void t1_3_checkLogin() throws InterruptedException, IOException, CsvValidationException {


        driver.findElement(By.id("email")).sendKeys(email);
      //driver.findElement(By.id("password")).sendKeys(csvReader.getUserMail());
        driver.findElement(By.id("password")).sendKeys(csvReader.getPassword());
        Thread.sleep(3000);
        webdriver.findById("loginButton").click();
        System.out.println("Kullanıcı girişi başarılı");
    }

    @Test(description = "Arama kutucuğuna bilgisayar kelimesi girilir ve aratılır")
    public void t1_4_searchComputer() throws InterruptedException {

        Thread.sleep(3000);
       webdriver.findById("searchData").sendKeys("bilgisayar");
        webdriver. findByClassName("searchBtn").click();
        System.out.println("Bilgisayar Kategorisi görülür");
    }

    @Test(description = "Arama sonuçları sayfasından 2. sayfa açılır.")
    public void t1_5_openSecondPage() throws InterruptedException {

        String chooseSecond = webdriver.findByXpath("//div[@class='pagination']//a[2]").getText();
        Thread.sleep(3000);
        System.out.println("page " + chooseSecond);
        webdriver.findByXpath("//div[@class='pagination']//a[2]").click();
        Thread.sleep(3000);
        Assert.assertTrue(chooseSecond.contains("2"));
        System.out.println("2. Sayfaya tıklanılır");
    }

    @Test(description = "2. sayfanın açıldığı kontrol edilir.")
    public void t1_6_showSecondPage() throws InterruptedException {

        String chooseSecond = webdriver.findByXpath("//*[@id=\"contentListing\"]/div/div/div[2]/div[4]/a[3]").getText();
        Assert.assertTrue(driver.getTitle().contains("Bilgisayar - n11.com - 2/50"));
        System.out.println("2. Sayfa ekranda görülür.");
        Thread.sleep(3000);
    }

    @Test(description = "Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir")
    public void t1_7_chooseProduct() throws InterruptedException {

        WebElement productElement = webdriver.findByXpath("//ul[@class = 'clearfix']//li[@class ='column'][2]//div[1]//div[@class ='pro']//a");
        String productName = productElement.getAttribute("title");
        productElement.click();
        System.out.println(productName);
        Thread.sleep(3000);
        Assert.assertNotNull(productName);
        System.out.println("Rastgele ürün seçildi");
        Thread.sleep(3000);

    }

    @Test(description = "Seçilen ürünün ürün bilgisi(ürün adı) ve tutar bilgisi text dosyasına yazdırılır.")
    public void t1_8_productInformations() throws InterruptedException, IOException {

        String productName = webdriver.findByXpath("//h1[@class='proName']").getText();
        productPrice = webdriver.findByXpath("//div[@class = 'priceDetail']//ins").getText();
        String text = productName +  " " + productPrice;
        TextReader utility = new TextReader(text);
        utility.writeText();
        Assert.assertNotNull(productName);
        Assert.assertNotNull(productPrice);
        System.out.println("Ürün adı ve tutar bilgisi text dosyasına yazıldı.");
        Thread.sleep(3000);

    }

    @Test(description = "Seçilen ürün sepete eklenir.")
    public void t1_9_addProduct() throws InterruptedException {
        Thread.sleep(3000);
        webdriver.findByXpath("//a[@class ='btn btnGrey btnAddBasket']").click();
        System.out.println("Ürün sepete eklendi.");
    }

    @Test(description = "Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.")
    public void t2_1_productComparison() throws InterruptedException {
        Thread.sleep(3000);
        webdriver.findByXpath("//a[@class ='myBasket']").click();
        Thread.sleep(3000);

        String basketPrice = webdriver.findByXpath("//div[@class = 'priceArea']//span").getText();
        System.out.println( productPrice + " " + " " + basketPrice);
        Assert.assertEquals(productPrice.trim(),basketPrice.trim());

        System.out.println("Ürün sayfa fiyatı ile sepet tutarı karşılaştırıldı.");
    }

    @Test(description = "Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.")
    public void t2_2_addNumber() throws InterruptedException {

        Thread.sleep(3000);
        webdriver.findByXpath("//span[@class='spinnerUp spinnerArrow']").click();
        String quantityElement =    webdriver.findByXpath("//input[@class='quantity']").getAttribute("value");
        Thread.sleep(3000);
        Assert.assertEquals("2", quantityElement);
        System.out.println("Ürün adeti arttırıldı");
    }

    @Test(description = "Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.")
    public void t2_3_deleteProduct() throws InterruptedException {

        webdriver.findByXpath("//span[@title ='Sil']").click();
        Thread.sleep(3000);
        String titleMessage = webdriver.findByXpath("//h2[@class = 'title']").getText();
        Assert.assertEquals(titleMessage,"Sepetiniz Boş");
      }

}
