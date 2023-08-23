package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;


public class PurchasePage {
    private SelenideElement heading = $$("h2").find(Condition.text("Путешествие дня"));
    private SelenideElement paymentButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public PurchasePage() {
        heading.shouldBe(visible);
    }

    public PaymentPage clickPaymentPage() {
        paymentButton.click();
        return new PaymentPage();
    }

    public CreditPage clickCreditPage() {
        creditButton.click();
        return new CreditPage();
    }
}