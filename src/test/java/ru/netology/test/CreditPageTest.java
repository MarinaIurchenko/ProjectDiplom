package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPage;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.getOrderCount;


public class CreditPageTest {
    public static String url = System.getProperty("sut.url");
    CreditPage creditPage = new CreditPage();

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @BeforeAll
    static void setAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void cleanDataBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    public void shouldSuccessfulPurchase() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.approvedStatus();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }
    @Test
    public void successfulPurchaseTwo() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getShiftedMonth());
        creditPage.setYearCard(DataHelper.getShiftedYear(5));
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.approvedStatus();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    public void shouldUnsuccessfulPurchase() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getDeclinedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.declinedStatus();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test

    public void numberEmpty() {
        creditPage.creditCard();
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void numberLessSixteenDigits() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getCardNumberLessThan16());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, SQLHelper.getCreditStatus());
    }

    @Test
    public void newCardNumber() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getNewCardNumber());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.declinedStatus();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthEmpty() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthDigitOne() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getDigitOne());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthMoreTwelve() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonthMoreTwelve());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.expirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthZeroTwo() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getZeroTwo());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, SQLHelper.getCreditStatus());
    }

    @Test
    public void yearEmpty() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void yearDigitOne() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getDigitOne());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void yearLessThanThisYear() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYearLessThanThisYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.expired();
        assertEquals(0,getOrderCount());
    }

    @Test
    public void yearZeroTwo() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getZeroTwo());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.expired();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderEmpty() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.requiredField();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderOneWord() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolderOneWord());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderCyrillic() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolderCyrillic());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
    @Test
    public void holderArabic() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolderArabic());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
    @Test
    public void holderHieroglyph() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolderHieroglyph());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
    @Test
    public void holderWithDigits() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolderWithNumbers());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderWithInvalidSymbol() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolderWithInvalidSymbol());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvEmpty() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvDigitOne() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitOne());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvDigitsTwo() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsTwo());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvZeroThree() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getApprovedCard());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getZeroThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
    @Test
    public void notInDatabas() {
        creditPage.creditCard();
        creditPage.setNumberCard(DataHelper.getNotInDatabase());
        creditPage.setMonthCard(DataHelper.getMonth());
        creditPage.setYearCard(DataHelper.getYear());
        creditPage.setHolderCard(DataHelper.getHolder());
        creditPage.setCvvCard(DataHelper.getDigitsThree());
        creditPage.clickButtonContinue();
        creditPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
}