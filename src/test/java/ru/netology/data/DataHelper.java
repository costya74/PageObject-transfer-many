package ru.netology.data;

import lombok.Value;

public class DataHelper {  // помощник данных
    private DataHelper() {
    }

    @Value // назначаем значения
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCcode {
        private String code;
    }

    public static VerificationCcode getVerificationCcode(AuthInfo authInfo) {
        return new VerificationCcode("12345");
    }

    @Value
    public static class CardNumber {
        private String cardNumber;
    }

    public static CardNumber getFirstCardNumber() {
        return new CardNumber("5559 0000 0000 0001");
    }

    public static CardNumber getSecondCardNumber() {
        return new CardNumber("5559 0000 0000 0002");
    }
}
