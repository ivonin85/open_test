package in.reqres.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class GooglePage {

    public SelenideElement searchField() {
        return $(By.name("q"));
    }

    public void checkEntryAndGoToWebsite(String text) {
        for (int i = 0; i < $$(".iUh30").size(); i++) {
            boolean val = $$(".iUh30").get(i).getAttribute("innerText").contains(text);
            if (val) {
                $$(".iUh30").get(i).click();
                break;
            } else {
                assertTrue(val);
            }
        }
        switchTo().window(1);
    }
}
