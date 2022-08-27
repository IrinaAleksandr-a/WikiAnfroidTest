package lib.pages.articleMenu;

import lib.MainPage;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChangeLanguageOfArticlePage extends MainPage {
    final static String CHANGE_LANGUAGE = "xpath://android.widget.ImageView[@content-desc=\"Change language\"]/..";
    final static String SEARCH_LANGUAGE = "id:org.wikipedia:id/langlinks_filter";

    public ChangeLanguageOfArticlePage(RemoteWebDriver driver) {
        super(driver);
    }

    public void open() {
        this.waitForElementClickableAndClick(CHANGE_LANGUAGE,
                "Cannot find 'Change language'  button", 5);
    }

    public void findByText(String text) {
        waitForElementAndSendKeys(SEARCH_LANGUAGE, text,
                "Cannot find 'Search Language' input", 5);
    }

    public void selectByText(String text) {
        selectByTextAndClick(text, 5);
    }
}
