package tests;

import lib.ArticleMenuTest;
import lib.pages.articleMenu.SaveArticlePage;
import lib.pages.MenuPage;
import lib.pages.SavedReadingListsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SaveArticleTest extends ArticleMenuTest {
    private SaveArticlePage savePage;
    private MenuPage menuPage;
    private SavedReadingListsPage savedListsPage;
    private final String nameList1 = "Actors";
    private final String nameList2 = "Сlowns";
    private String titleArticle;

    @BeforeEach
    public void initPage() {
        savePage = new SaveArticlePage(this.driver);
        menuPage = new MenuPage(this.driver);
        savedListsPage = new SavedReadingListsPage(this.driver);
        titleArticle = articlePage.getTitle();
    }

    @DisplayName("1. Сохранение статьи в новый список.")
    @Test
    public void saveArticleToNewListTest() {
        System.out.println("Тест. Сохранение статьи 'Yuri Nikulin' в новый список " + nameList1);
        createNewList(nameList1);
        savePage.clickOk();
        checkResultAddMessage(nameList1);
        // Проверка 'Сохранённых списков'
        menuPage.navigateUp();
        savedListsPage.open();
        List<String> titleLists = savedListsPage.getTitleLists();
        System.out.println("Сохранённые списки: " + titleLists.toString());
        Assertions.assertTrue(titleLists.contains(nameList1));
        Assertions.assertTrue(isContainsArticleInList(nameList1, false));
    }

    @DisplayName("2. Имя списка пустое.")
    @Test
    public void listNameIsEmptyTest() {
        System.out.println("Тест. Имя списка пустое.");
        createNewList("");
        //Проверка результирующего сообщения
        String resultMessage = savePage.getNameErrorMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals("Please enter a title.", resultMessage);
        System.out.println("Кнопка OK активна: " + savePage.isEnabledOkButton());
        Assertions.assertFalse(savePage.isEnabledOkButton());
    }

    @DisplayName("3. Cоздание списка с существующим именем.")
    @Test
    public void createListSameNameTest() {
        System.out.println("Тест. Cоздание списка " + nameList1 + " повторно");
        createNewList(nameList1);
        savePage.clickOk();
        checkResultAddMessage(nameList1);
        createAnotherNewList(nameList1);
        //Проверка результирующего сообщения
        String resultMessage = savePage.getNameErrorMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals("Reading list \"" + nameList1 + "\" already exists.", resultMessage);
        System.out.println("Кнопка OK активна: " + savePage.isEnabledOkButton());
        Assertions.assertFalse(savePage.isEnabledOkButton());
    }

    @DisplayName("4. Сохранение статьи в два новых списка.")
    @Test
    public void saveArticleToTwoNewListTest() {
        System.out.println("Тест. Сохранение статьи 'Yuri Nikulin' в два списка: " + nameList1 + " и " + nameList2);
        createNewList(nameList1);
        savePage.clickOk();
        checkResultAddMessage(nameList1);
        createAnotherNewList(nameList2);
        savePage.clickOk();
        checkResultAddMessage(nameList2);
        // Проверка 'Сохранённых списков'
        menuPage.navigateUp();
        savedListsPage.open();
        List<String> titleLists = savedListsPage.getTitleLists();
        System.out.println("Сохранённые списки: " + titleLists.toString());
        Assertions.assertTrue(titleLists.contains(nameList1));
        Assertions.assertTrue(titleLists.contains(nameList2));
        Assertions.assertTrue(isContainsArticleInList(nameList1, false));
        Assertions.assertTrue(isContainsArticleInList(nameList2, false));
    }

    @DisplayName("5. Сохранение статьи повторно в тот же список.")
    @Test
    public void saveArticleAgainToSameListTest() {
        System.out.println("Тест. Сохранение статьи 'Yuri Nikulin' повторно в список " + nameList1);
        System.out.println();
        createNewList(nameList1);
        savePage.clickOk();
        checkResultAddMessage(nameList1);
        savePage.openMenu();
        savePage.addToAnotherList();
        savePage.selectListToAdd(nameList1);
        //Проверка результирующего сообщения
        String resultMessage = savePage.getSnackbarMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals("This article is already in that list.", resultMessage);
    }

    @DisplayName("6. Удаление статьи из одного списка.")
    @Test
    public void removeArticleFromOneListTest() {
        screenshotName = "removeArticleFromOneListTest";
        System.out.println("Тест. Удаление статьи 'Yuri Nikulin' из списка " + nameList1);
        //Сохранение статьи
        createNewList(nameList1);
        savePage.clickOk();
        checkResultAddMessage(nameList1);
        //Удаление статьи
        savePage.openMenu();
        savePage.removeFromList();
        checkResultRemoveMessage();
        // Проверка 'Сохранённых списков'
        menuPage.navigateUp();
        savedListsPage.open();
        List<String> titleLists = savedListsPage.getTitleLists();
        System.out.println("Сохранённые списки: " + titleLists.toString());
        savedListsPage.takeScreenshot(screenshotName);
        Assertions.assertTrue(titleLists.contains(nameList1));
        Assertions.assertFalse(isContainsArticleInList(nameList1, true));
    }

    @DisplayName("7. Удаление статьи, выбирая список.")
    @Test
    public void removeArticleBySelectListTest() {
        screenshotName = "removeArticleBySelectListTest";
        System.out.println("Тест. Сохранение статьи 'Yuri Nikulin' в два списка: " + nameList1 + " и " + nameList2);
        System.out.println("      Удаление статьи 'Yuri Nikulin' из одного списка - " + nameList1);
        //Сохранение статьи в два списка
        createNewList(nameList1);
        savePage.clickOk();
        checkResultAddMessage(nameList1);
        createAnotherNewList(nameList2);
        savePage.clickOk();
        checkResultAddMessage(nameList2);
        // Удаление статьи из одного списка
        savePage.openMenu();
        savePage.removeFromList();
        savePage.selectListFromRemove(nameList1);
        savePage.clickOk();
        checkResultRemoveMessage();
        // Проверка 'Сохранённых списков'
        menuPage.navigateUp();
        savedListsPage.open();
        List<String> titleLists = savedListsPage.getTitleLists();
        System.out.println("Сохранённые списки: " + titleLists.toString());
        savedListsPage.takeScreenshot(screenshotName);
        Assertions.assertTrue(titleLists.contains(nameList1));
        Assertions.assertTrue(titleLists.contains(nameList2));
        Assertions.assertFalse(isContainsArticleInList(nameList1, true));
        Assertions.assertTrue(isContainsArticleInList(nameList2, true));
    }

    private void createNewList(String nameList) {
        savePage.openMenu();
        savePage.gotIt();
        savePage.fillingNameList(nameList);
    }

    private void createAnotherNewList(String nameList) {
        savePage.openMenu();
        savePage.addToAnotherList();
        savePage.createNewList();
        savePage.fillingNameList(nameList);
    }

    private void checkResultAddMessage(String nameList) {
        String resultMessage = savePage.getSnackbarMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals("Added to " + nameList + ".", resultMessage);
    }

    private void checkResultRemoveMessage() {
        String resultMessage = savePage.getSnackbarMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals(titleArticle + " removed from list", resultMessage);
    }

    private Boolean isContainsArticleInList(String nameList, boolean takeScreenshot) {
        savedListsPage.selectListByName(nameList);
        List<String> titleArticlesInLists = savedListsPage.getTitleArticlesInLists();
        System.out.println("Сохранённые статьи в списке '" + nameList + "': " + titleArticlesInLists.toString());
        if (takeScreenshot){
            savedListsPage.takeScreenshot(screenshotName + screenshotIndex);
            screenshotIndex++;
        }

        menuPage.navigateUp();
        return titleArticlesInLists.contains(titleArticle);
    }
}
