package lib;

import lib.*;
import lib.pages.ArticlePage;
import lib.pages.SearchPage;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ArticleMenuTest extends CoreTestCase {

    protected ArticlePage articlePage;
    protected String screenshotName;
    protected int screenshotIndex;

    @BeforeEach
    public void openArticle() {
        SearchPage search = new SearchPage(this.driver);
        search.open();
        search.findArticleByText("nikulin");
        search.selectByText("Yuri Nikulin");
        articlePage = new ArticlePage(this.driver);
        screenshotName = "nameTest";
        screenshotIndex = 1;
    }
}
