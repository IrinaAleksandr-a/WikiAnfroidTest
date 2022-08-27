package tests;

import lib.ArticleMenuTest;
import lib.pages.articleMenu.ChangeLanguageOfArticlePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChangeLanguageOfArticleTest extends ArticleMenuTest {

    private ChangeLanguageOfArticlePage changeLangPage;

    @BeforeEach
    public void initPage() {
        changeLangPage = new ChangeLanguageOfArticlePage(driver);
    }

    @DisplayName("1. Изменение языка статьи на русский.")
    @Test
    public void changeLanguageInArticleTest() {
        System.out.println("Тест. Изменение языка статьи на русский.");
        String titleBefore = articlePage.getTitle();
        System.out.println("Имя статьи до смены языка: '" + titleBefore + "'");
        //Смена языка на русский
        changeLangPage.open();
        changeLangPage.findByText("ru");
        changeLangPage.selectByText("Русский");
        //Проверка имени статьи на русском
        String titleRu = articlePage.getTitle();
        System.out.println("Имя статьи на русском языке: '" + titleRu + "'");
        Assertions.assertEquals("Никулин, Юрий Владимирович", titleRu);
    }
}
