package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static String getShiftedMonth(int monthCount) {
        return LocalDate.now().plusMonths(monthCount).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static CardInfo getApprovedCard() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static CardInfo getDeclinedCard() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4442", month, year, cardHolder, cvs2);
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getInvalidCardNumber() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("1111 2222 3333 4444", month, year, cardHolder, cvs2);
    }

    public static CardInfo getCardEmptyNumber() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo(" ", month, year, cardHolder, cvs2);
    }

    public static CardInfo enteringCardNumberLatinAlphabet() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("aaaa bbbb cccc dddd", month, year, cardHolder, cvs2);
    }

    public static CardInfo enteringCardNumberCyrillicAlphabet() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("аааа бббб вввв гггг", month, year, cardHolder, cvs2);
    }

    public static CardInfo enteringCardNumberSpecialCharacters() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("!@#$ !@#$ !@#$ !@#$", month, year, cardHolder, cvs2);
    }

    public static CardInfo enteringCardNumberWith15Characters() {
        Faker faker = new Faker();
        String cardNumber = faker.number().digits(15);
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo(cardNumber, month, year, cardHolder, cvs2);
    }

    public static CardInfo enterCardDetailsWithoutMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", " ", year, cardHolder, cvs2);
    }

    public static CardInfo enterCardDetailsWithoutYear() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, " ", cardHolder, cvs2);
    }

    public static CardInfo enterCardDetailsWithOneDigitMonthField() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = faker.number().digit();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static CardInfo enterCardDetailsWithOneDigitYearField() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = faker.number().digit();
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static CardInfo enterCardInput13InFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", "13", year, cardHolder, cvs2);
    }

    public static CardInfo enterCardInput00InFieldMonth() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", "00", year, cardHolder, cvs2);
    }

    public static CardInfo getCardWithoutNameOwner() {
        Faker faker = new Faker();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, year, " ", cvs2);
    }

    public static CardInfo enterOwnersDetailsCyrillicCharacters() {
        Faker faker = new Faker(new Locale("ru"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, year, cardHolder, cvs2);
    }

    public static CardInfo enterOwnersDetailsSpecialCharacters() {
        Faker faker = new Faker();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        String cvs2 = faker.number().digits(3);
        return new CardInfo("4444 4444 4444 4441", month, year, "!@#$$%^", cvs2);
    }

    public static CardInfo enterWithEmptyFieldCVV2_CVS2() {
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth(8);
        String year = getShiftedYear(1);
        return new CardInfo("4444 4444 4444 4441", month, year, cardHolder, " ");
    }

}
