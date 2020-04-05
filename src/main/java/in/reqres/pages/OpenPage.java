package in.reqres.pages;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

public class OpenPage {

    public void checkExchangeRates() {
        List<Double> rates = new ArrayList<>();
        for (String rate : $$(".main-page-exchange__rate").texts()) {
            rates.add(Double.parseDouble(rate.replace(",", ".")));
        }
        assertTrue(rates.get(0) < rates.get(1));
        assertTrue(rates.get(2) < rates.get(3));
    }
}
