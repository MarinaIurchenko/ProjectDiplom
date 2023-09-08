package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CreditPageTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDataBase();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void creditPositiveCardApproved() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    void creditPositiveCardDeclined() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    void creditNegativeIfAllFieldsEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeIfCardNumberEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardNumberEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeCardNumber15Symbols() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getNumberCard15Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeIfMonthEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getMonthEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeIfYearEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getYearEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeIfCardHolderEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolderEmpty());
        payment.waitNotificationRequiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeIfCVCEmpty() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCVCEmpty());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeMonth1() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardMonth1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeMonth00() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardMonth00ThisYear());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void creditNegativeNonExistentMonth() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardNonExistentMonth());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeYear1() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardYear1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeYearBefore() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardYearUnder());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeYearOver() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardYearOver());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeYear00() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardYear00());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeOwner1Word() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolder1Word());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeOwnerCyrillic() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolderCyrillic());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeOwnerWithNumbers() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolderNumeric());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void creditNegativeHolderArabic() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
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
    void creditNegativeOwnerWithSymbols() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardHolderWithSymbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeCVC1() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCvс1Symbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeCVC2() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCvс2Symbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeCardNotInDatabase() {
        val startPage = new PurchasePage();
        val payment = startPage.clickCreditPage();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitNotificationFailure();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
