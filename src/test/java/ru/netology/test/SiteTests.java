package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
//import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataInfo;
import ru.netology.page.DashboardPage;
import ru.netology.page.GoToPaymentForm;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteTests {
    private DashboardPage dashboardPage;

    @BeforeAll
    static void setUpAll() {
//        WebDriverManager.chromedriver().setup();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openSite() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void forClose() {
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

    @Test
    @DisplayName("Проверка карт|Неверный формат")
    void checkCardBadTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.cardsBad) {
            var cardInfo = DataInfo.getUserCardInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormat();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка карт|Допустимые значения")
    void checkCardGoodTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.cardGood) {
            var cardInfo = DataInfo.getUserCardInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormatNot();
            assertFalse(expected);
        }
    }

    @Test
    @DisplayName("Проверка месяца|Неверный формат")
    void checkMonthBadTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.monthBad) {
            var cardInfo = DataInfo.getUserMonthInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormat();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка месяца|Неверно указан срок действия карты")
    void checkInvalidExpirationDateTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.monthInvalidExpirationDate) {
            var cardInfo = DataInfo.getUserMonthInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isInvalidExpirationDate();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка месяца|Допустимые значения")
    void checkMonthGoodTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.monthGood) {
            var cardInfo = DataInfo.getUserMonthInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormatNot();
            assertFalse(expected);
        }
    }

    @Test
    @DisplayName("Проверка года|Неверный формат")
    void checkYearBadTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.yearBad) {
            var cardInfo = DataInfo.getUserYearInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormat();
            assertTrue(expected);
        }
    }
    @Test
    @DisplayName("Проверка года|Допустимые значения")
    void checkYearGoodTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.yearGood) {
            var cardInfo = DataInfo.getUserYearInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormatNot();
            assertFalse(expected);
        }
    }

    @Test
    @DisplayName("Проверка месяца-года|Неверно указан срок действия карты")
    void checkMonthAndYearBadTest() {
        var paymentForm = new GoToPaymentForm();
            var cardInfo = DataInfo.getUserMonthYearInfo(-1);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isInvalidExpirationDate();
            assertTrue(expected);
    }

    @Test
    @DisplayName("Проверка месяца-года|Текущий месяц-год")
    void checkMonthAndYearNowTest() {
        var paymentForm = new GoToPaymentForm();
        var cardInfo = DataInfo.getUserMonthYearInfo(0);
        var fillForm = paymentForm.goPaymentCashThenCredit();
        dashboardPage = fillForm.goFillForm(cardInfo);
        boolean expected = dashboardPage.isWrongFormatNot();
        assertFalse(expected);
    }

    @Test
    @DisplayName("Проверка FIO|Неверный формат")
    void checkFIOBadTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.fIOBad) {
            var cardInfo = DataInfo.getUserFIOInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormat();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка FIO|Поле требует заполнения")
    void checkFIORequiredToFillTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.fIORequiredToFill) {
            var cardInfo = DataInfo.getUserFIOInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isRequiredToFill();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка FIO|Допустимые значения")
    void checkFIOGoodTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.fIOGood) {
            var cardInfo = DataInfo.getUserFIOInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormatNot();
            assertFalse(expected);
        }
    }

    @Test
    @DisplayName("Проверка CVC/CVV|Неверный формат")
    void checkCVCBadTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.cVCBad) {
            var cardInfo = DataInfo.getUserCVCInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormat();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка CVC/CVV|Поле требует заполнения")
    void checkCVCRequiredToFillTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.cVCRequiredToFill) {
            var cardInfo = DataInfo.getUserCVCInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isRequiredToFill();
            assertTrue(expected);
        }
    }

    @Test
    @DisplayName("Проверка CVC/CVV|Допустимые значения")
    void checkCVCGoodTest() {
        var paymentForm = new GoToPaymentForm();
        for (String str : DataInfo.cVCGood) {
            var cardInfo = DataInfo.getUserCVCInfo(str);
            var fillForm = paymentForm.goPaymentCashThenCredit();
            dashboardPage = fillForm.goFillForm(cardInfo);
            boolean expected = dashboardPage.isWrongFormatNot();
            assertFalse(expected);
        }
    }
}
