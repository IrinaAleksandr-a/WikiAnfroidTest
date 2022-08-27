package lib.pages;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePage extends MainPage {

    final static String TITLE_TEXT = "id:org.wikipedia:id/view_page_title_text";
    final static String SUBTITLE_TEXT = "id:org.wikipedia:id/view_page_subtitle_text";

    public ArticlePage(RemoteWebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        WebElement title = this.waitForElementPresent(
                TITLE_TEXT,
                "Cannot find 'Title' element"
        );
        return title.getText();
    }

    public String getSubTitle() {
        WebElement title = this.waitForElementPresent(
                SUBTITLE_TEXT,
                "Cannot find 'SubTitle' element"
        );
        return title.getText();
    }
}
