package in.reqres;

import in.reqres.pages.GooglePage;
import in.reqres.pages.OpenPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class Tests {

    GooglePage googlePage = new GooglePage();
    OpenPage openPage = new OpenPage();
    Api api = new Api();
    public final static String NAME = "name";
    public final static String JOB = "job";
    private String checkUrl = "www.open.ru";
    private String url = "https://www.google.com/";
    private String searchText = "Открытие";

    private String baseUrl = "https://reqres.in";
    private String postUrl = "/api/users";
    private String getUrl = "/api/users?page=2";

    @Test
    public void checkExchangeRates() {
        open(url);
        googlePage.searchField().append(searchText).pressEnter();
        googlePage.checkEntryAndGoToWebsite(checkUrl);
        openPage.checkExchangeRates();
    }

    @Test
    public void postApiTest() {
        api.setValueToJson(NAME, "Sergey Ivonin");
        api.setValueToJson(JOB, "QA");
        api.checkResponse(api.postRequest(baseUrl, postUrl));

    }

    @Test
    public void getApiTest() {
        api.usersAssertNotNull(baseUrl, getUrl);

    }
}
