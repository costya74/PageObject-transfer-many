package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

 @BeforeEach
   public void setUp(){
     val loginPage = open("http://localhost:9999/", LoginPage.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCode(authInfo);
    verificationPage.validVerify(verificationCode);
 }
 @Test
 public void shouldTransferMoneyFromSecondCardToFirst() {
     val dashboardPage = new DashboardPage();
     int balanceFirstCard = dashboardPage.getFirstCardBalance();
     int balanceSecondCard = dashboardPage.getSecondCardBalance();
     val transferPage = dashboardPage.firstCardButton();
     val infoCard = DataHelper.getSecondCardNumber();
     String sum = "10000";
     transferPage.transferForm(sum, infoCard);
     assertEquals(balanceFirstCard + Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
     assertEquals(balanceSecondCard - Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
 }

    @Test
    public void shouldTransferMoneyFromFirstCardToSecond() {
        val dashboardPage = new DashboardPage();
        int balanceFirstCard = dashboardPage.getFirstCardBalance();
        int balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferPage = dashboardPage.secondCardButton();
        val infoCard = DataHelper.getFirstCardNumber();
        String sum = "7000";
        transferPage.transferForm(sum, infoCard);
        assertEquals(balanceFirstCard - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(balanceSecondCard + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferMoneyUpperLimitAndGetError() {
        val dashboardPage = new DashboardPage();
        val transferPage = dashboardPage.secondCardButton();
        val infoCard = DataHelper.getFirstCardNumber();
        String sum = "10200";
        transferPage.transferForm(sum, infoCard);
        transferPage.getError();
    }
}
