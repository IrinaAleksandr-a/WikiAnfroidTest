package tests;

import lib.CoreTestCase;
import lib.pages.ArticlePage;
import lib.pages.SearchPage;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SearchTest extends CoreTestCase {
    private SearchPage searchPage;
    private ArticlePage articlePage;

    @BeforeEach
    public void initPage() {
        searchPage = new SearchPage(this.driver);
        articlePage = new ArticlePage(this.driver);
    }

    @DisplayName("1. Поиск статьи 'Yuri Nikulin'.")
    @Test
    public void searchTest() {
        System.out.println("Тест. Поиск статьи 'Yuri Nikulin'.");
        //Поиск
        searchPage.open();
        searchPage.findArticleByText("nikulin");
        searchPage.selectByText("Yuri Nikulin");
        //Проверка имени статьи
        String titleArticle = articlePage.getTitle();
        System.out.println("Результат. Имя открытой статьи: '" + titleArticle + "'");
        Assertions.assertEquals("Yuri Nikulin", titleArticle);
    }

    @DisplayName("2. Смена языка поиска.")
    @Test
    public void changeLanguageSearchTest() {
        System.out.println("Тест. Смена языка поиска на русский.");
        System.out.println("Поиск статьи 'Никулин, Юрий Владимирович'.");
        //Смена языка на русский
        searchPage.open();
        searchPage.openSearchLanguages();
        searchPage.findLanguagesByText("ru");
        searchPage.selectByText("Русский");
        //Поиск
        searchPage.findArticleByText("Никулин");
        searchPage.selectByText("Никулин, Юрий");
        //Проверка имени статьи
        String titleArticle = articlePage.getTitle();
        System.out.println("Результат.Имя открытой статьи: '" + titleArticle + "'");
        Assertions.assertEquals("Никулин, Юрий Владимирович", titleArticle);
    }

    @DisplayName("3. Поиск по слову 'wfewfewfwegweg'.")
    @Test
    public void searchNotResultFoundTest() {
        System.out.println("Тест. Поиск по слову 'wfewfewfwegweg'.");
        //Поиск
        searchPage.open();
        searchPage.findArticleByText("wfewfewfwegweg");
        //Проверка результирующего сообщения
        String resultMessage = searchPage.getNotResultFoundMessage();
        System.out.println("Результат: " + resultMessage);
        Assertions.assertEquals("No results found", resultMessage);
    }
}
