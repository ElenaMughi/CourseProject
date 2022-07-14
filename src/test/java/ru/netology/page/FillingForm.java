package ru.netology.page;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataInfo;

public class FillingForm {

    public DashboardPage goFillForm(DataInfo.CardInfo cardInfo) {
        $("[placeholder='0000 0000 0000 0000']").setValue(cardInfo.getCardId());
        $("[placeholder='08']").setValue(cardInfo.getCardMonth());
        $("[placeholder='22']").setValue(cardInfo.getCardYear());
        $$("[class='input__inner'] input").get(3).setValue(cardInfo.getCardFIO());
        $("[placeholder='999']").setValue(cardInfo.getCardCVC());
        $$(".button").findBy(Condition.text("Продолжить")).click();
        return  new DashboardPage();
    }

    public DashboardPage goEmptyForm() {
        $$(".button").findBy(Condition.text("Продолжить")).click();
        return  new DashboardPage();
    }

}
