package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {
    private SelenideElement heading = $$("h3").find(exactText("Оплата по карте"));
    private SelenideElement cardNumberField = $(".input_box>input.input_control[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement monthField = $($(".input__box>input.input__control[placeholder=\"08\"]"));
    private SelenideElement yearField = $($(".input__box>input.input__control[placeholder=\"22\"]"));
    private SelenideElement cardHolder = $(byText("Владелец")).parent().$(".input__box>input.input__control[type=\"text\"]");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__box>input.input__control[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement approvedOperation = $$(".notification__title").find(exactText("Успешно"));
    private SelenideElement failureOperation = $$(".notification__title").find(exactText("Ошибка"));

    private SelenideElement wrongFormatCardNumber = $$(".input__top").find(exactText("Номер карты")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement monthError = $$(".input__top").find(exactText("Месяц")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement wrongDateCard = $$(".input__top").find(exactText("Месяц")).parent().
            $$(".input__sub").find(exactText("Неверно указан срок действия карты"));
    private SelenideElement wrongYearCard = $$(".input__top").find(exactText("Год")).parent().
            $$(".input__sub").find(exactText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $$(".input__top").find(exactText("Год")).parent().
            $$(".input__sub").find(exactText("Истёк срок действия карты"));
    private SelenideElement yearError = $(byText("Неверный формат"));
    private SelenideElement emptyCardHolderField = $$(".input__top").find(exactText("Владелец")).parent().
            $$(".input__sub").find(exactText("Поле обязательно для заполнения"));
    private SelenideElement wrongCardHolderField = $$(".input__top").find(exactText("Владелец")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));
    private SelenideElement cvcFieldError = $$(".input__top").find(exactText("CVC/CVV")).parent().
            $$(".input__sub").find(exactText("Неверный формат"));

    public DebitPage() {
        heading.shouldBe(visible);
    }

    public void inputData(CardInfo card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolder.setValue(card.getCardHolder());
        cvcField.setValue(card.getCVC());
        continueButton.click();
    }

    public void waitForApprovalNotification() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitForNotificationFailure() {
        failureOperation.shouldBe(visible, Duration.ofSeconds(20));
    }

    public void wrongFormatCardNumber() {
        wrongFormatCardNumber.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void wrongFormatMonth() {
        monthError.shouldBe(visible);
    }

    public void wrongYearCard() {
        wrongYearCard.shouldBe(visible);
    }

    public void wrongFormatYear() {
        yearError.shouldBe(visible);
    }

    public void wrongFormatDateCard() {
        wrongDateCard.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void expiredDateCard() {
        cardExpired.shouldBe(visible);
    }

    public void emptyCardHolderField() {
        emptyCardHolderField.shouldBe(visible);
    }

    public void wrongFormatCardHolderField() {
        wrongCardHolderField.shouldBe(visible, Duration.ofSeconds(25));
    }

    public void wrongFormatCVC() {
        cvcFieldError.shouldBe(visible);
    }
}
