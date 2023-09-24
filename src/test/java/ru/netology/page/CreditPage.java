package ru.netology.page;

import com.codeborne.selenide.SelenideElement;


import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CreditPage {
    private SelenideElement heading = $$("h2").find(exactText("Путешествие дня"));
    private SelenideElement creditButton = $$(".button__text").find(exactText("Купить в кредит"));
    private SelenideElement numberCard = $(byText("Номер карты")).parent().$("[class='input__control']");
    private SelenideElement monthCard = $(byText("Месяц")).parent().$("[class='input__control']");
    private SelenideElement yearCard = $(byText("Год")).parent().$("[class='input__control']");
    private SelenideElement holderCard = $(byText("Владелец")).parent().$("[class='input__control']");
    private SelenideElement cvvCard = $(byText("CVC/CVV")).parent().$("[class='input__control']");
    private SelenideElement approvedStatus = $(byText("Операция одобрена Банком.")).parent().$("[class='notification__content']");
    private SelenideElement declinedStatus = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class='notification__content']");
    private SelenideElement incorrectFormat = $(byText("Неверный формат"));
    private SelenideElement cardErrorExpiration = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expired = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement buttonContinue = $$("button").find(exactText("Продолжить"));

    public void creditCard() {
        heading.shouldBe(visible);
        creditButton.click();
    }

    public void setNumberCard(String number) {
        numberCard.setValue(number);
    }

    public void setMonthCard(String month) {
        monthCard.setValue(month);
    }

    public void setYearCard(String year) {
        yearCard.setValue(year);
    }

    public void setHolderCard(String user) {
        holderCard.setValue(user);
    }

    public void setCvvCard(String cvv) {
        cvvCard.setValue(cvv);
    }

    public void clickButtonContinue() {
        buttonContinue.click();
    }

    public void approvedStatus() {
        approvedStatus.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void declinedStatus() {
        declinedStatus.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void incorrectFormat() {
        incorrectFormat.shouldBe(visible);
    }

    public void expirationError() {
        cardErrorExpiration.shouldBe(visible);
    }

    public void expired() {
        expired.shouldBe(visible);
    }
    public void requiredField() {
        requiredField.shouldBe(visible);
    }
}
