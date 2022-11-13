import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //methodlardaki static'leri kaldırmak için.
@ExtendWith(ProjeTestLogger.class)
public class BaseTest {

    protected WebDriver driver;
    ProjePage projePage;

    @BeforeAll         //Tüm methodlardan önce çalışması için
    public  void InitializeFunction() //chrome driverı başlatma
    {
     System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
     driver=new ChromeDriver();
     driver.get("https://www.network.com.tr/");
     String urlControl=driver.getCurrentUrl(); //url kontrolü
     Assertions.assertEquals("https://www.network.com.tr/",urlControl,"URLLER EŞLEŞMİYOR");//url kontrolü
        projePage=new ProjePage(driver);

    }

  /*  @AfterAll       //Tüm metotlardan sonra çalışması için
    public  void FinishFunction()
    {
        driver.quit();
        System.out.println("Test sonlandı!!..");
    }*/
}
