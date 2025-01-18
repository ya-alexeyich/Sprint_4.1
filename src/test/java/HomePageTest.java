import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import constants.HomePageConstants;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class HomePageTest extends BaseTest {


    private final By question;
    private final By answer;
    private final By labelResult;
    private final String expected;

    public HomePageTest(By question, By answer, By labelResult, String expected) {
        this.question = question;
        this.answer = answer;
        this.labelResult = labelResult;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {HomePageConstants.QUESTION_0, HomePageConstants.ANSWER_0, HomePageConstants.ITEM_ANSWER_0, HomePageConstants.TEXT_ANSWER_0},
                {HomePageConstants.QUESTION_1, HomePageConstants.ANSWER_1, HomePageConstants.ITEM_ANSWER_1, HomePageConstants.TEXT_ANSWER_1},
                {HomePageConstants.QUESTION_2, HomePageConstants.ANSWER_2, HomePageConstants.ITEM_ANSWER_2, HomePageConstants.TEXT_ANSWER_2},
                {HomePageConstants.QUESTION_3, HomePageConstants.ANSWER_3, HomePageConstants.ITEM_ANSWER_3, HomePageConstants.TEXT_ANSWER_3},
                {HomePageConstants.QUESTION_4, HomePageConstants.ANSWER_4, HomePageConstants.ITEM_ANSWER_4, HomePageConstants.TEXT_ANSWER_4},
                {HomePageConstants.QUESTION_5, HomePageConstants.ANSWER_5, HomePageConstants.ITEM_ANSWER_5, HomePageConstants.TEXT_ANSWER_5},
                {HomePageConstants.QUESTION_6, HomePageConstants.ANSWER_6, HomePageConstants.ITEM_ANSWER_6, HomePageConstants.TEXT_ANSWER_6},
                {HomePageConstants.QUESTION_7, HomePageConstants.ANSWER_7, HomePageConstants.ITEM_ANSWER_7, HomePageConstants.TEXT_ANSWER_7},
        };
    }


    @Test
    public void checkQuestionsTest() {
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookie()
                .scrollToQuestions()
                .clickQuestion(question)
                .waitLoadAfterClickQuestion(labelResult);
        String result = driver.findElement(answer).getText();

        assertEquals(expected, result);
    }


}