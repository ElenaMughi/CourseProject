package ru.netology.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class GoToPaymentForm {

    public FillingForm goPaymentCashThenCredit() {
        $$(".button").findBy(Condition.text("Купить")).click();
        $$(".button").findBy(Condition.text("Купить в кредит")).click();
        return new FillingForm();
    }

    public FillingForm goPaymentCredit() {
        $$(".button").findBy(Condition.text("Купить в кредит")).click();
        return new FillingForm();
    }

}
