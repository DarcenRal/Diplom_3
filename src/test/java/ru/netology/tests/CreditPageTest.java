package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;

import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class CreditPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openPage() {
        SqlHelper.clearDB();
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldByPayApprovedCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitForApprovalNotification();
        assertEquals("APPROVED", SqlHelper.getCreditRequestStatus());
    }

    @Test
    void shouldByPayDeclinedCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitForNotificationFailure();
        assertEquals("DECLINED", SqlHelper.getCreditRequestStatus());
    }

    @Test
    void shouldPayByNotValidNumberCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.getInvalidCardNumber());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByEmptyNumberCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.getCardEmptyNumber());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByLatinSymbolsNumberCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enteringCardNumberLatinAlphabet());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByCyrillicSymbolsNumberCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enteringCardNumberCyrillicAlphabet());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayBySpecialSymbolsNumberCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enteringCardNumberSpecialCharacters());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayBy15SymbolsNumberCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enteringCardNumberWith15Characters());
        payment.wrongFormatCardNumber();
    }

    @Test
    void shouldPayByEmptyFieldMonthCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterCardDetailsWithoutMonth());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByEmptyFieldYearCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterCardDetailsWithoutYear());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByInvalidFieldMonthCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterCardDetailsWithOneDigitMonthField());
        payment.wrongFormatMonth();
    }

    @Test
    void shouldPayByInvalidFieldYearCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterCardDetailsWithOneDigitYearField());
        payment.wrongFormatYear();
    }

    @Test
    void shouldPayByWrongDateCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterCardInput13InFieldMonth());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByWrongDateCreditCard_00() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterCardInput00InFieldMonth());
        payment.wrongFormatDateCard();
    }

    @Test
    void shouldPayByEmptyFieldCardHolderCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.getCardWithoutNameOwner());
        payment.emptyCardHolderField();
    }

    @Test
    void shouldInputRussianInCardHolderCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterOwnersDetailsCyrillicCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldInputSpecialSymbolsInCardHolderCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterOwnersDetailsSpecialCharacters());
        payment.wrongFormatCardHolderField();
    }

    @Test
    void shouldPayByEmptyFieldCVCCreditCard() {
        val paymentPage = new PaymentPage();
        val payment = paymentPage.goToCreditPage();
        payment.inputData(DataHelper.enterWithEmptyFieldCVV2_CVS2());
        payment.wrongFormatCVC();
    }

}