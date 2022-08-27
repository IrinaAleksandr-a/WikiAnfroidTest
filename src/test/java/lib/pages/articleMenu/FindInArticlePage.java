package lib.pages.articleMenu;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FindInArticlePage extends MainPage {
    final static String FIND_IN_PAGE = "xpath://android.widget.ImageView[@content-desc=\"Find in page\"]/..";
    final static String SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
    final static String MATCH = "id:org.wikipedia:id/find_in_page_match";
    final static String FIND_PREVIOUS = "id:org.wikipedia:id/find_in_page_prev";
    final static String FIND_NEXT = "id:org.wikipedia:id/find_in_page_next";

    public FindInArticlePage(RemoteWebDriver driver) {
        super(driver);
    }

    public void open() {
        this.waitForElementClickableAndClick(FIND_IN_PAGE,
                "Cannot find 'Find in Page'  button", 5);
    }

    public void findByText(String text) {
        waitForElementAndSendKeys(SEARCH_INPUT, text,
                "Cannot find 'Search input'", 5);
    }

    public int getCountMatch() {
        WebElement countMatchElement = this.waitForElementPresent(
                MATCH,
                "Cannot find 'Count match' element", 5
        );
        String countMatchStr = countMatchElement.getText();
        int index = countMatchStr.indexOf("/");
        return Integer.parseInt(countMatchStr.substring(index + 1));
    }
}
