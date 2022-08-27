package lib.pages;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPage extends MainPage {

    final static String OPEN_SEARCH = "id:org.wikipedia:id/fragment_feed_header";
    final static String SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
    final static String RESULT_ELEMENT_BY_TEXT = "xpath://*[./*[contains(@text,'{TEXT}')]]";
    final static String NO_RESULTS_FOUND = "id:org.wikipedia:id/search_empty_text";
    final static String LANG_BUTTON = "id:org.wikipedia:id/search_lang_button";
    final static String LANG_FILTER = "id:org.wikipedia:id/preference_languages_filter";

    public SearchPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void open() {
        this.waitForElementClickableAndClick(OPEN_SEARCH,
                "Cannot find 'Search Wikipedia init' search input", 5);
    }

    public void findArticleByText(String text) {
        waitForElementAndSendKeys(SEARCH_INPUT, text,
                "Cannot find 'Search input'", 5);
    }

    public void selectByText(String text) {
        selectByTextAndClick(RESULT_ELEMENT_BY_TEXT, text, 5);
    }

    public String getNotResultFoundMessage() {
        try {
            WebElement expectedResult = this.waitForElementPresent(
                    NO_RESULTS_FOUND,
                    "Cannot find result search 'No results found'"
            );
            return expectedResult.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void openSearchLanguages() {
        this.waitForElementClickableAndClick(LANG_BUTTON,
                "Cannot find 'Search lang button'", 5);
    }

    public void findLanguagesByText(String text) {
        waitForElementAndSendKeys(LANG_FILTER, text,
                "Cannot find 'Languages filter'", 5);
    }
}
