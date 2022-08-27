package lib.pages;

import lib.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LogInPage extends MainPage {
    private final static String USER_NAME = "id:org.wikipedia:id/login_username_text";
    private final static String PASSWORD = "xpath://*[contains(@text,'Password')]//*[@class='android.widget.EditText']";
    private final static String PASSWORD_VISIBILITY = "id:org.wikipedia:id/text_input_password_toggle";
    private final static String LOGIN_BUTTON = "id:org.wikipedia:id/login_button";

    public LogInPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void setUserName(String userName) {
        waitForElementAndSendKeys(USER_NAME, userName,
                "Cannot find 'UserName' input", 5);
    }

    public void setPassword(String password) {
        waitForElementAndSendKeys(PASSWORD, password,
                "Cannot find 'Password' input", 5);
    }

    public void clickPasswordVisibility() {
        this.waitForElementClickableAndClick(PASSWORD_VISIBILITY,
                "Cannot find 'Password Visibility' toggle", 5);
    }

    public boolean isVisibilityPassword() {
        WebElement passwordInput = this.waitForElementPresent(
                PASSWORD,
                "Cannot find 'Password' input"
        );
        String passwordAtribute = passwordInput.getAttribute("password");
        if (passwordAtribute.equals("true")) {
            return false;
        } else return true;
    }

    public void clickLogInButton() {
        this.waitForElementClickableAndClick(LOGIN_BUTTON,
                "Cannot find 'Log In' button", 5);
    }

    public boolean isEnabledLogInButton() {
        WebElement loginButton = this.waitForElementPresent(
                LOGIN_BUTTON,
                "Cannot find 'Log In' button"
        );
        return loginButton.isEnabled();
    }
}
