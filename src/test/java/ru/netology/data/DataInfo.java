package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataInfo {
    private DataInfo() {
    }
    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class CardInfo {
        private String cardId;
        private String cardMonth;
        private String cardYear;
        private String cardFIO;
        private String cardCVC;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static CardInfo getCardInfo() {
        return new CardInfo("1111 2222 3333 4444", "01", getYear(1), faker.name().fullName(), faker.number().digits(3));
    }

    public static CardInfo getBadCardInfo() {
        return new CardInfo("5555 6666 7777 8888", "01", getYear(1), faker.name().fullName(), faker.number().digits(3));
    }

    public static CardInfo getRandomCardInfo() {
        return new CardInfo("9999 6666 7777 8888", "01", getYear(1), faker.name().fullName(), faker.number().digits(3));
    }

    public static String getYear(long year) {
        String text = LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return text.substring(8, 10);
    }

    public static String getMonth(long month) {
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
