package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataInfo {

    private static final Faker faker = new Faker(new Locale("en"));

    public static final String cardsBad[] = {"1", "1111 1111 1111 111", "AAAA 2222 3333 4444", ""};
    public static final String cardGood[] = {"1111 2222 3333 4444 1", "11$11 2222 3333 4444"};

    public static final String monthBad[] = {"0", "AA", "$*", "1", ""};
    public static final String monthInvalidExpirationDate[] = {"13", "210", "001", "000"};
    public static final String monthGood[] = {"031"};

    public static final String yearBad[] = {"", "0", "AA", "$*", "1"};
    public static final String yearOld[] = {getYear(-1)};
    public static final String yearGood[] = {getYear(0) + "1"};

    public static final String fIOBad[] = {"Q"};
    public static final String fIORequiredToFill[] = {"Иванов", "$%&123456", ""};
    public static final String fIOGood[] = {"Ivanov-Ivan", "Ivanov"};

    public static final String cVCBad[] = {"0", "9", "99", "000"};
    public static final String cVCRequiredToFill[] = {"QAS", "$%&", "ЯЧС", ""};
    public static final String cVCGood[] = {"9999"};

    @Value
    public static class CardInfo {

        private String cardId;
        private String cardMonth;
        private String cardYear;
        private String cardFIO;
        private String cardCVC;
        private String cardSuccess;

    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static CardInfo getCardInfo() {
        return new CardInfo("1111 2222 3333 4444", "01", getYear(1), faker.name().fullName(), faker.number().digits(3), "APPROVED");
    }

    public static CardInfo getBadCardInfo() {
        return new CardInfo("5555 6666 7777 8888", "01", getYear(1), faker.name().fullName(), faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getRandomCardInfo() {
        return new CardInfo("9999 6666 7777 8888", "01", getYear(1), faker.name().fullName(), faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getUserCardInfo(String str) {
        return new CardInfo(str, "01", getYear(1), faker.name().fullName(), faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getUserMonthInfo(String str) {
        return new CardInfo("1111 2222 3333 4444", str, getYear(1), faker.name().fullName(), faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getUserYearInfo(String str) {
        return new CardInfo("1111 2222 3333 4444", "01", str, faker.name().fullName(), faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getUserMonthYearInfo(int i) {
        return new CardInfo("1111 2222 3333 4444", getMonth(i), getYear(0), faker.name().fullName(), faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getUserFIOInfo(String str) {
        return new CardInfo("1111 2222 3333 4444", "12", getYear(1), str, faker.number().digits(3), "DECLINED");
    }

    public static CardInfo getUserCVCInfo(String str) {
        return new CardInfo("1111 2222 3333 4444", "12", getYear(1), faker.name().fullName(), str, "DECLINED");
    }

    public static String getYear(long year) {
        String text = LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return text.substring(8, 10);
    }

    public static String getMonth(long month) {
        String text = LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return text.substring(3, 5);
    }

}
