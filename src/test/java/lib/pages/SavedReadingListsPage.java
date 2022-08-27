package lib.pages;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public class SavedReadingListsPage extends MainPage {
    final static String MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc=\"My lists\"]";
    final static String NAMES_LISTS = "xpath://*[@resource-id=\"org.wikipedia:id/item_title\"]";
    final static String NAMES_ARTICLES_LISTS = "xpath://*[@resource-id=\"org.wikipedia:id/page_list_item_title\"]";

    public SavedReadingListsPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void open() {
        this.waitForElementClickableAndClick(MY_LISTS,
                "Cannot find 'My lists'  button", 10);
    }

    public List<String> getTitleLists() {
        List<WebElement> list = waitForElementsPresent(NAMES_LISTS,
                "Cannot find 'item title'  ", 5);
        return getTitles(list);
    }

    public List<String> getTitleArticlesInLists() {
        try {
            List<WebElement> list = waitForElementsPresent(NAMES_ARTICLES_LISTS,
                    "Cannot find 'list_item_title' ", 5);
            return getTitles(list);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private List<String> getTitles(List<WebElement> listElements) {
        List<String> titles = new ArrayList<>();
        for (WebElement listElement : listElements) {
            titles.add(listElement.getText());
        }
        return titles;
    }

    public void selectListByName(String name) {
        selectByTextAndClick(name, 5);
    }

    public void selectArticleByName(String name) {
        selectByTextAndClick(name, 5);
    }
}
