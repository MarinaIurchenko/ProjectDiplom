package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PageTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDataBase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void paymentPositiveCardApproved() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void paymentPositiveCardDeclined() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }
    @Test
    void paymentNegativeIfAllFieldsEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeIfCardNumberEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardNumberEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeIfMonthEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getMonthEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeIfYearEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getYearEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeIfCardHolderEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolderEmpty());
        payment.waitNotificationRequiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeIfCVCEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCVCEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeCardNumber15Symbols() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getNumberCard15Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeMonth1() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeMonth00() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardMonth00ThisYear());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeYear1() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeYearBefore() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardYearUnder());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeYearOver() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardYearOver());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeYear00() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeOwner1Word() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardHolder1Word());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeOwnerCyrillic() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardHolderCyrillic());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void paymentNegativeHolderArabic() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardHolderArabic());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void creditNegativeHolderHieroglyph() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolderHieroglyph());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void paymentNegativeOwnerWithNumbers() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardHolderNumeric());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeOwnerWithSymbols() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardHolderWithSymbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeCVC1() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCvс1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeCVC2() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCvс2Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void paymentNegativeNotInDataBase() {
        val startPage = new PurchasePage();
        val payment = startPage.clickPaymentPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitNotificationFailure();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
