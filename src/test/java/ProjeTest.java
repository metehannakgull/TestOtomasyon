import com.opencsv.exceptions.CsvValidationException;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import static java.lang.Thread.*;

public class ProjeTest extends BaseTest {

    @Test
    public void testSearchBar(){

        projePage.setSearchBar("Ceket");
     String urlControl=driver.getCurrentUrl();
     Assertions.assertEquals("https://www.network.com.tr/search?searchKey=Ceket",urlControl,"2.URL KONTROLÜ: URLLER EŞLEŞMİYOR.");
     }
    @Test
    public void setProjectContinue() throws InterruptedException, IOException, CsvValidationException {
        sleep(2000);
        projePage.setScrollDown();
        sleep(2000);
        projePage.setProjectContinue();
        sleep(2000);
       Assertions.assertEquals("radio-box__label",projePage.sizeCompare,"SEÇİLİ BEDEN UYUŞMUYOR");
       Assertions.assertEquals("cartItem__price -sale",projePage.priceCompare,"SEÇİLİ FİYAT UYUŞMUYOR");
      // Assertions.assertEquals("cartItem__price -old -seasonPrice",projePage.priceCompare,"ESKİ FİYATI İNDİRİMLİDEN BÜYÜKTÜR");
       Assertions.assertEquals("n-button large block text-center -primary ",projePage.buttonPath,"Button kontrolü");
       Assertions.assertEquals("cartEmpty__title",projePage.emptyBasket,"Boş sepet kontrolü");
    }

}
