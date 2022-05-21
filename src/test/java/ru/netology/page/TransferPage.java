package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement heading = $(withText("Пополнение карты"));
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement button = $("[data-test-id=action-transfer]"); //$("[data-test-id=from] input");
    private final SelenideElement cancelButton = $("[data-test-id=action-cancel]"); //$(byText("Пополнить"));
    private final SelenideElement error = $("[data-test-id=error-notification]"); //$(withText("Ошибка"));

    public TransferPage() {
        heading.shouldBe(Condition.visible);
    }
    public DashboardPage transferForm(String sum, DataHelper.CardNumber cardNumber) {
        amount.setValue(sum);
        from.setValue(String.valueOf(cardNumber));
        button.click();
        return new DashboardPage();
    }
    public void getError() {
        error.shouldBe(Condition.visible);
    }
    public DashboardPage calcelButton() {
        cancelButton.click();
        return new DashboardPage();
    }
}
