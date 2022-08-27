package lib.pages.articleMenu;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SaveArticlePage extends MainPage {

    final static String ADD_READING_LIST = "xpath://android.widget.ImageView[@content-desc=\"Add this article to a reading list\"]/..";
    final static String GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
    final static String CREATE_NEW_BUTTON = "id:org.wikipedia:id/create_button";
    final static String NAME_INPUT = "id:org.wikipedia:id/text_input";
    final static String NAME_ERROR_INPUT = "id:org.wikipedia:id/textinput_error";
    final static String OK_BUTTON = "id:android:id/button1";
    final static String CANCEL_BUTTON = "id:android:id/button2";
    final static String VIEW_LIST = "id:org.wikipedia:id/snackbar_action";

    public SaveArticlePage(RemoteWebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        this.waitForElementNotPresent(SNACKBAR_TEXT,
                "Cannot find 'ResultMessage' text", 10);


        this.waitForElementClickableAndClick(ADD_READING_LIST,
                "Cannot find 'Add this article to a reading list'  button", 20);

    }

    public void gotIt() {
        this.waitForElementClickableAndClick(GOT_IT_BUTTON,
                "Cannot find 'Got it'  button", 5);
    }

    public void createNewList() {
        this.waitForElementClickableAndClick(CREATE_NEW_BUTTON,
                "Cannot find 'CreateList'  button", 5);

    }

    public void addToAnotherList() {
        selectByTextAndClick("Add to another", 5);
    }

    public void selectListToAdd(String nameList) {
        selectByTextAndClick(nameList, 5);
    }

    public void removeFromList() {
        selectByTextAndClick("Remove", 5);
    }

    public void selectListFromRemove(String nameList) {
        selectByTextAndClick(nameList, 5);
    }

    public void fillingNameList(String nameList) {
        WebElement nameListInput = this.waitForElementPresent(
                NAME_INPUT,
                "Cannot find 'Name'  input"
        );
        nameListInput.clear();
        nameListInput.sendKeys(nameList);
    }

    public String getNameErrorMessage() {
        WebElement nameErrorInput = this.waitForElementPresent(
                NAME_ERROR_INPUT,
                "Cannot find 'Name Input Error'  input"
        );
        return nameErrorInput.getText();
    }

    public void clickOk() {
        this.waitForElementClickableAndClick(OK_BUTTON,
                "Cannot find 'OK'  button", 5
        );
    }

    public void clickCansel() {
        this.waitForElementClickableAndClick(CANCEL_BUTTON,
                "Cannot find 'CANCEL'  button", 5
        );
    }

    public boolean isEnabledOkButton() {
        WebElement okButton = this.waitForElementPresent(
                OK_BUTTON,
                "Cannot find 'OK'  button"
        );
        return okButton.isEnabled();
    }

    public void clickViewList() {
        this.waitForElementClickableAndClick(VIEW_LIST,
                "Cannot find 'ViewList'  button", 5);
    }
}
