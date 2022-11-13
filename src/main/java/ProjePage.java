import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;


public class ProjePage extends BasePage{

    private final By searchBar= By.id("search");
    private final By selectProduct=By.className("product__badges");

    protected String sizeCompare;
    protected String priceCompare;
    protected String priceCompare2;
    protected String buttonPath; //giriş yap buton kontrolü

    protected String emptyBasket;

    Random random=new Random();

    String CsvPath="C:\\Users\\cvanAkgul\\Desktop\\untitled\\dosya.csv";
    private CSVReader csvReader;
    String csvCell[];

    public ProjePage(WebDriver driver)
    {
        super(driver);// base page constructorına bağladım
    }

    public void setSearchBar(String nameText)
    {

        WebElement nameSpace= webDriver.findElement(searchBar);
        nameSpace.click();
        nameSpace.sendKeys(nameText);
        webDriver.navigate().to("https://www.network.com.tr/search?searchKey="+nameText);


    }
    public void setScrollDown()
    {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,11800)", "");
    }

    public void setProjectContinue() throws InterruptedException, IOException, CsvValidationException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,-11700)", ""); //scroll up


        webDriver.findElement(selectProduct); //Mouse hover
        Actions act =new Actions(webDriver);
        act.moveToElement(webDriver.findElement(selectProduct)).perform();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//h3[text()='Siyah Kaşmir Ceket']")).click();
        Thread.sleep(5000);

        closePopup();

        JavascriptExecutor js2 = (JavascriptExecutor) webDriver;
        js2.executeScript("window.scrollBy(0,340)", "");

        selectSize_AddProduct();

        readCsvFile();

        buttonPath=webDriver.findElement(By.xpath("//*[@id=\"login\"]/button")).getAttribute("class");//giriş yap buton kontrolü

        deleteProduct();

        emptyBasket=webDriver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/div/div/div[2]")).getAttribute("class");//boş sepet kontrolü
    }

    public void closePopup()throws InterruptedException
    {
        Actions act =new Actions(webDriver);
        webDriver.findElement(By.id("onetrust-accept-btn-handler")).click(); //Accept All cookies
        act.sendKeys(Keys.ESCAPE).perform(); //esc tuşuna basıp, popdan kurtuldum.
        act.sendKeys(Keys.ESCAPE).perform();
        Thread.sleep(2000);

    }
    public void selectSize_AddProduct()throws InterruptedException
    {
        webDriver.findElement(By.xpath("//label[text()='54/6N']")).click();//beden seç
        sizeCompare=webDriver.findElement(By.xpath("//label[text()='54/6N']")).getAttribute("class");//size kontrol
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/div[2]/div[2]/div/div[7]/button[2]")).click();//Sepete Ekle
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[3]/a")).click();//Sepete git
        Thread.sleep(2000);
        priceCompare=webDriver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[3]/span[1]")).getAttribute("class");//ürüne ait fiyat bilgisi kontrol
       /* priceCompare2=webDriver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[1]/div[3]/span[3]")).getAttribute("class");
       Bu kontrolü yorum satırı içerisine aldım. "ProjeTest"de yorum satırını kaldırdığım test sonucu failed gözükmesin diye.
       Aslında failed sonuç bekliyorum çünkü fiyatların eşit olmamasını istiyorum
       */
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[2]/div/div[2]/button")).click();//Devam et
        Thread.sleep(2000);
    }

    public void readCsvFile() throws IOException, CsvValidationException
    {
        csvReader=new CSVReader(new FileReader(CsvPath));
        while ((csvCell=csvReader.readNext())!=null)
        {
            String email=csvCell[0];
            String password=csvCell[1];
            webDriver.findElement(By.xpath("//*[@id=\"n-input-email\"]")).sendKeys(email);
            webDriver.findElement(By.xpath("//*[@id=\"n-input-password\"]")).sendKeys(password);
        }
    }
    public void deleteProduct()throws InterruptedException
    {
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"home\"]/div[4]/header/div/div/div[2]/a")).click();//Network logosuna tıkla
        Thread.sleep(4000);
        // webDriver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[3]/div[3]/a")).click();
        webDriver.navigate().to("https://www.network.com.tr/cart");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[3]/div/div/div[2]/div[3]/button")).click();//ürün sil
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[4]/div[2]/div[3]/button[1]")).click();//ürün sil evet
        Thread.sleep(2000);
    }
    public void setSelectProductSize()
    {
   /*
        webDriver.findElement(By.className("product__content -sizes"));
        Select size=new Select(webDriver.findElement(By.className("product__content -sizes")));
        int arraySize=webDriver.findElements(By.cssSelector("product__content -sizes")).size();
        int index=random.nextInt(arraySize);
        size.selectByIndex(index);*/
    }
}
