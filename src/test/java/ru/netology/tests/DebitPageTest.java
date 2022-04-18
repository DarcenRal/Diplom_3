package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;

import ru.netology.pages.PaymentPage;
import ru.netology.data.SqlHelper;
import ru.netology.data.DataHelper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class DebitPageTest {
    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {
        SqlHelper.clearDB();
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldPayByApprovedCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitForApprovalNotification();
        assertEquals("APPROVED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldPayByDeclinedCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitForNotificationFailure();
        assertEquals("DECLINED", SqlHelper.getPaymentStatus());
    }

    @Test
    void shouldPayByNotValidNumberCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.getInvalidCardNumber());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByEmptyNumberCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.getCardEmptyNumber());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByLatinSymbolsNumberCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enteringCardNumberLatinAlphabet());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByCyrillicSymbolsNumberCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enteringCardNumberCyrillicAlphabet());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayBySpecialSymbolsNumberCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enteringCardNumberSpecialCharacters());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayBy15SymbolsNumberCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enteringCardNumberWith15Characters());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByEmptyFieldMonthCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterCardDetailsWithoutMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByEmptyFieldYearCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterCardDetailsWithoutYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByInvalidFieldMonthCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterCardDetailsWithOneDigitMonthField());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByInvalidFieldYearCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterCardDetailsWithOneDigitYearField());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByWrongDateCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterCardInput13InFieldMonth());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByWrongDateCard_00() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterCardInput00InFieldMonth());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByEmptyFieldCardHolderCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.getCardWithoutNameOwner());
        payment.emptyCardHolderField();
    }

    @Test
    void shouldInputRussianInCardHolderCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterOwnersDetailsCyrillicCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputSpecialSymbolsInCardHolderCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterOwnersDetailsSpecialCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldPayByEmptyFieldCVCCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToDebitPage();
        payment.inputData(DataHelper.enterWithEmptyFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

}