package lib.pages;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MenuPage extends MainPage {

    final static String MENU_BUTTON = "id:org.wikipedia:id/menu_overflow_button";
    final static String LOG_IN = "id:org.wikipedia:id/explore_overflow_account_container";
    final static String ACCOUNT_NAME = "id:org.wikipedia:id/explore_overflow_account_name";
    final static String SETTINGS = "id:org.wikipedia:id/explore_overflow_settings";
    final static String SUPPORT_WIKI = "id:org.wikipedia:id/explore_overflow_donate";
    final static String LOG_OUT = "id:org.wikipedia:id/explore_overflow_log_out";
    final static String NAVIGATE_UP = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";

    public MenuPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void open() {
        this.waitForElementClickableAndClick(MENU_BUTTON,
                "Cannot find 'Menu' button", 10);
    }

    public void clickLogIn() {
        this.waitForElementClickableAndClick(LOG_IN,
                "Cannot find 'Log in to Wiki' container", 5);
    }

    public void clickSettings() {
        this.waitForElementClickableAndClick(SETTINGS,
                "Cannot find 'Settings'", 5);
    }

    public void clickSupportWiki() {
        this.waitForElementClickableAndClick(SUPPORT_WIKI,
                "Cannot find 'Support Wikipedia'", 5);
    }

    public void clickLogOut() {
        this.waitForElementClickableAndClick(LOG_OUT,
                "Cannot find 'Log out'", 5);
    }

    public String getAccountName() {
        WebElement accountName = this.waitForElementPresent(
                ACCOUNT_NAME,
                "Cannot find 'Account name' element"
        );
        return accountName.getText();
    }

    public void navigateUp() {
        this.waitForElementClickableAndClick(NAVIGATE_UP,
                "Cannot find 'Navigate up' button", 5);
    }
}
