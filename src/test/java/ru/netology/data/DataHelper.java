package ru.netology.data;


import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.lang.Math;

public class DataHelper {
    public static Card getApprovedCard() {
        return new Card("4444444444444441", "12", "26", "Smirnova Maria", "634");
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "12", "26", "Smirnova Maria", "634");
    }

    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }
    public static Card getCardNumberEmpty() {
        return new Card("", "12", "26", "Smirnova Maria", "634");
    }
    public static Card getMonthEmpty() {
        return new Card("4444444444444441", "", "26", "Smirnova Maria", "634");
    }

    public static Card getYearEmpty() {
        return new Card("4444444444444441", "12", "", "Smirnova Maria", "634");
    }

    public static Card getCardHolderEmpty() {
        return new Card("4444444444444441", "12", "26", "", "634");
    }


    public static Card getCVCEmpty() {
        return new Card("4444444444444441", "12", "26", "Smirnova Maria", "");
    }

    public static String getShiftedMonth() {
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static Card getNumberCard15Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        String number = faker.number().digits(15);
        return new Card(number, month, year, holder, cvc);
    }



    public static Card getCardMonth1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = faker.number().digit();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardMonthOver12() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", "13", year, holder, cvc);
    }

    public static Card getCardMonth00ThisYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(0);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", "00", year, holder, cvc);
    }



    public static Card getCardYear1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = faker.number().digit();
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardYearOver() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(10);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardYearUnder() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(-5);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardYear00() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, "00", holder, cvc);
    }

    public static Card getCvс1Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(1);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCvс2Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(2);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardHolder1Word() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardHolderCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardHolderNumeric() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.number().digit();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardHolderWithSymbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " %$ * &";
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvc);
    }

    public static Card getCardNotInDatabase() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvc = faker.number().digits(3);
        return new Card("1444444444444444", month, year, holder, cvc);
    }
}