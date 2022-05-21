package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {  // страница панели инструментов
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement firstCard = $$(".list__item").first(); //пункт списка
    private final SelenideElement secondCard = $$(".list__item").last();
    private final SelenideElement reload = $("[data-test-id=action-reload]"); //обновляем
    private final SelenideElement firstCardButtton = $$("[data-test-id=action-deposit").first();
    private final SelenideElement secondCardButtton = $$("[data-test-id=action-deposit").last();
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage firstCardButton() {
        firstCardButtton.click();
        return new TransferPage();
    }

    public TransferPage secondCardButton() {
        secondCardButtton.click();
        return new TransferPage();
    }

    public int getFirstCardBalance() {
        val text = firstCard.text();
        return extractBalanceCard1(text);
    }

    private int extractBalanceCard1(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getSecondCardBalance() {
        val text = secondCard.text();
        return extractBalanceCard2(text);
    }

    private int extractBalanceCard2(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}