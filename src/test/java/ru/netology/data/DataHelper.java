package ru.netology.data;


import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Math;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static String getApprovedCard() {
        String cardNumber = "4444 4444 4444 4441";
        return cardNumber;
    }

    public static String getDeclinedCard() {
        String cardNumber = "4444 4444 4444 4442";
        return cardNumber;
    }
    public static String getCardNumberLessThan16() {
        return "4444 4444 4444 444";
    }
    public static String getNewCardNumber() {
        return faker.numerify("#### #### #### ####");
    }


    public static String generateRandomCardNumber() {
        return faker.numerify("#### #### #### ####");
    }

    public static String generateRandomMonth() {
        return faker.numerify("##");
    }

    public static String generateRandomYear() {
        return faker.numerify("##");
    }

    public static String generateRandomName() {
        return faker.name().fullName();
    }

    public static String generateRandomCode() {
        return faker.numerify("###");
    }

    public static String getDigitOne() {
        return faker.numerify("#");
    }

    public static String getDigitsTwo() {
        return faker.numerify("##");
    }

    public static String getDigitsThree() {
        return faker.numerify("###");
    }

    public static String getZeroTwo() {
        return "00";
    }

    public static String getZeroThree() {
        return "000";
    }


    public static String getShiftedMonth() {
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getMonth() {
        LocalDate currentData = LocalDate.now();
        return currentData.format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getMonthMoreTwelve() {
        int currentMonth = Integer.parseInt(getMonth());
        int moreMonth = currentMonth + 12;
        return String.format("%02d", moreMonth % 100);
    }

    public static String getYear() {
        LocalDate currentData = LocalDate.now();
        LocalDate currentYear = currentData.plusYears(5);
        return currentYear.format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getYearLessThanThisYear() {
        LocalDate currentData = LocalDate.now();
        LocalDate currentYear = currentData.minusYears(1);
        return currentYear.format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getHolder() {
        return faker.name().firstName() + faker.name().lastName();
    }

    public static String getHolderOneWord() {
        return faker.name().firstName();
    }

    public static String getHolderCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + faker.name().lastName();
    }

    public static String getHolderWithNumbers() {
        return faker.name().firstName() + faker.numerify("#####");
    }

    public static String getHolderWithInvalidSymbol() {
        return faker.name().firstName() + ":*;!)";
    }


    public static String getHolderArabic() {
        return faker.name() + "سميرنوفا ماريا";
    }

    public static String getHolderHieroglyph() {
        return faker.name() + "斯米爾諾瓦瑪麗亞";

    }

    public static String getNotInDatabase() {

        return "0000 0000 0000 0000";
    }
}