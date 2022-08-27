package tests;

import lib.ArticleMenuTest;
import lib.pages.articleMenu.FindInArticlePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FindInArticleTest extends ArticleMenuTest {
    private FindInArticlePage findPage;

    @BeforeEach
    public void initPage() {
        findPage = new FindInArticlePage(driver);
    }

    @DisplayName("1. Поиск по статье.")
    @ParameterizedTest
    @CsvSource(value = {
            "The Diamond Arm, 2",
            "Office Romance, 0"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void findInArticleTest(String findText, int countMatches) {
        System.out.println("Тест. Поиск по статье.");
        findPage.open();
        findPage.findByText(findText);
        int count = findPage.getCountMatch();
        System.out.println("Поиск: '" + findText + "'. Найдено: " + count);
        Assertions.assertEquals(countMatches, count);
    }
}
