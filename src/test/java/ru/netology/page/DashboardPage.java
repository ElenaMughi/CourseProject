package ru.netology.page;

import java.time.Duration;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.withText;

public class DashboardPage {

    public boolean isSuccess() {
        boolean note1 = $(withText("Операция одобрена Банком.")).shouldBe(Condition.visible, Duration.ofSeconds(15)).exists();
        boolean note2 = $(withText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.hidden, Duration.ofSeconds(15)).exists();
        return note1 & note2;

    }

    public boolean isNotSuccess() {
        boolean note2 = $(withText("Ошибка! Банк отказал в проведении операции.")).shouldBe(Condition.visible, Duration.ofSeconds(15)).exists();
        boolean note1 = $(withText("Операция одобрена Банком.")).shouldBe(Condition.hidden, Duration.ofSeconds(15)).exists();
        return note1 & note2;
    }

    public boolean isEmpty() {
        boolean note1 = $$("[class='input__sub']").get(0).shouldBe(Condition.text("Неверный формат")).exists();
        boolean note2 = $$("[class='input__sub']").get(1).shouldBe(Condition.text("Неверный формат")).exists();
        boolean note3 = $$("[class='input__sub']").get(2).shouldBe(Condition.text("Неверный формат")).exists();
        boolean note4 = $$("[class='input__sub']").get(3).shouldBe(Condition.text("Поле обязательно для заполнения")).exists();
        boolean note5 = $$("[class='input__sub']").get(4).shouldBe(Condition.text("Неверный формат")).exists();
        return note1 & note2 & note3 & note4 & note5;
    }
}
