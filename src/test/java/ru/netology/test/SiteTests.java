package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataInfo;
import ru.netology.page.DashboardPage;
import ru.netology.page.GoToPaymentForm;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteTests {
    private DashboardPage dashboardPage;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openSite() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void clearBase() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Отправка формы с APPROVED CARD")
    void cardGoodTest() {
        var cardInfo = DataInfo.getCardInfo();
        var paymentForm = new GoToPaymentForm();
        var fillForm = paymentForm.goPaymentCredit();
        dashboardPage = fillForm.goFillForm(cardInfo);
        boolean expected = dashboardPage.isSuccess();
        assertTrue(expected);
    }

    @Test
    @DisplayName("Проверка двойной отправки с APPROVED CARD")
    void cardGoodTestх2() {
        var cardInfo = DataInfo.getCardInfo();
        var paymentForm = new GoToPaymentForm();
        var fillForm = paymentForm.goPaymentCredit();
        dashboardPage = fillForm.goFillForm(cardInfo);
        dashboardPage.isSuccess();
        dashboardPage = fillForm.goEmptyForm();
        boolean expected = dashboardPage.isSuccess();
        assertTrue(expected);
    }

    @Test
    @DisplayName("Отправка формы с DECLINED CARD")
    void cardBadTest() {
        var cardInfo = DataInfo.getBadCardInfo();
        var paymentForm = new GoToPaymentForm();
        var fillForm = paymentForm.goPaymentCredit();
        dashboardPage = fillForm.goFillForm(cardInfo);
        boolean expected = dashboardPage.isNotSuccess();
        assertTrue(expected);
    }

    @Test
    @DisplayName("Отправка формы с RANDOM CARD")
    void cardRandomTest() {
        var cardInfo = DataInfo.getRandomCardInfo();
        var paymentForm = new GoToPaymentForm();
        var fillForm = paymentForm.goPaymentCashThenCredit();
        dashboardPage = fillForm.goFillForm(cardInfo);
        boolean expected = dashboardPage.isNotSuccess();
        assertTrue(expected);
    }

    @Test
    @DisplayName("Отправка пустой формы")
    void dataEmptyTest() {
        var paymentForm = new GoToPaymentForm();
        var fillForm = paymentForm.goPaymentCredit();
        dashboardPage = fillForm.goEmptyForm();
        boolean expected = dashboardPage.isEmpty();
        assertTrue(expected);
    }

}
