package tests;

import lib.pages.LogInPage;
import lib.CoreTestCase;
import lib.pages.MenuPage;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class LogInTest extends CoreTestCase {
    private MenuPage menu;
    private LogInPage logInPage;
    private final static String USER_NAME = "Alirina";
    private final static String PASSWORD = "qetuadgj";
    private final static String ERROR_MESSAGE = "Incorrect username or password entered.\n" + "Please try again.";
    private final static String LOG_IN_MESSAGE = "Logged in successfully!";
    private final static String LOG_OUT_MESSAGE = "Logged out";

    @BeforeEach
    public void initPage() {
        menu = new MenuPage(this.driver);
        menu.open();
        menu.clickLogIn();
        logInPage = new LogInPage(this.driver);
    }

    @DisplayName("1. Авторизация и выход из системы.")
    @Test
    public void logInTest() {
        System.out.println("Тест. Авторизация с существующими данными и выход из системы.");
        //Авторизация
        System.out.println("Авторизация:");
        logInPage.setUserName(USER_NAME);
        logInPage.setPassword(PASSWORD);
        logInPage.clickLogInButton();
        //Проверка результирующего сообщения
        String resultMessageLogIn = logInPage.getSnackbarMessage();
        System.out.println("Результат: " + resultMessageLogIn);
        Assertions.assertEquals(LOG_IN_MESSAGE, resultMessageLogIn);
        //Проверка имени учетной записи
        menu.open();
        String accountName = menu.getAccountName();
        System.out.println("Имя учетной записи: " + accountName);
        Assertions.assertEquals(USER_NAME, accountName);

        //Выход из системы
        System.out.println("Выход из системы:");
        menu.clickLogOut();
        //Проверка результирующего сообщения
        String resultMessageLogOut = menu.getSnackbarMessage();
        System.out.println("Результат: " + resultMessageLogOut);
        Assertions.assertEquals(LOG_OUT_MESSAGE, resultMessageLogOut);
        //Проверка имени учетной записи
        menu.open();
        accountName = menu.getAccountName();
        System.out.println("Имя учетной записи: " + accountName);
        Assertions.assertEquals("Log in to Wikipedia", accountName);
    }

    @DisplayName("2. Авторизация. Не существующие данные.")
    @Test
    public void logInInvalidDataTest() {
        System.out.println("Тест. Авторизация. Не существующие данные.");
        //Авторизация
        logInPage.setUserName("Masha");
        logInPage.setPassword("qqq");
        logInPage.clickLogInButton();
        //Проверка результирующего сообщения
        String resultMessage = logInPage.getSnackbarMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals(ERROR_MESSAGE, resultMessage);
    }

    @DisplayName("3. Авторизация. Пустые данные.")
    @Test
    public void logInEmptyDataTest() {
        System.out.println("Тест. Авторизация. Пустые данные.");
        Assertions.assertFalse(logInPage.isEnabledLogInButton());
        logInPage.setUserName("Masha");
        Assertions.assertFalse(logInPage.isEnabledLogInButton());
        System.out.println("Кнопка OK активна: " + logInPage.isEnabledLogInButton());
    }

    @DisplayName("4. Видимость пароля.")
    @Test
    public void passwordVisibilityTest() {
        System.out.println("Тест. Проверка видимости пароля.");
        logInPage.setPassword(PASSWORD);
        Assertions.assertFalse(logInPage.isVisibilityPassword());
        logInPage.clickPasswordVisibility();
        Assertions.assertTrue(logInPage.isVisibilityPassword());
        logInPage.clickPasswordVisibility();
        Assertions.assertFalse(logInPage.isVisibilityPassword());
    }
}
