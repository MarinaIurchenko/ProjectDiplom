package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentPage;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.getOrderCount;


public class PageTest {
    PaymentPage paymentPage = new PaymentPage();
    public static String url = System.getProperty("sut.url");


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
    public void successfulPurchaseOne() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.approvedStatus();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
    @Test
    public void successfulPurchaseTwo() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getShiftedMonth());
        paymentPage.setYearCard(DataHelper.getShiftedYear(3));
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.approvedStatus();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
    @Test
    public void shouldUnsuccessfulPurchase() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getDeclinedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.declinedStatus();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test

    public void numberEmpty() {
        paymentPage.debitCard();
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void numberLessSixteenDigits() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getCardNumberLessThan16());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void newCardNumber() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getNewCardNumber());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.declinedStatus();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthEmpty() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthDigitOne() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getDigitOne());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthMoreTwelve() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonthMoreTwelve());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.expirationError();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void monthZeroTwo() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getZeroTwo());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void yearEmpty() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void yearDigitOne() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getDigitOne());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void yearLessThanThisYear() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYearLessThanThisYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.expired();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void yearZeroTwo() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getZeroTwo());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.expired();
        assertEquals(0,getOrderCount());
    }
    @Test
    public void holderEmpty() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.requiredField();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderOneWord() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolderOneWord());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderCyrillic() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolderCyrillic());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderArabic() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolderArabic());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderHieroglyph() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolderHieroglyph());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderWithDigits() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolderWithNumbers());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void holderWithInvalidSymbol() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolderWithInvalidSymbol());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvEmpty() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvDigitOne() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitOne());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvDigitsTwo() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsTwo());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void cvvZeroThree() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getApprovedCard());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getZeroThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }

    @Test
    public void notInDatabas() {
        paymentPage.debitCard();
        paymentPage.setNumberCard(DataHelper.getNotInDatabase());
        paymentPage.setMonthCard(DataHelper.getMonth());
        paymentPage.setYearCard(DataHelper.getYear());
        paymentPage.setHolderCard(DataHelper.getHolder());
        paymentPage.setCvvCard(DataHelper.getDigitsThree());
        paymentPage.clickButtonContinue();
        paymentPage.incorrectFormat();
        assertEquals(0, getOrderCount());
    }
}