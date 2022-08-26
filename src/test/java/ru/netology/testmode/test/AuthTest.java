package ru.netology.testmode.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.page.LoginToInternetBankPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;

class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        LoginToInternetBankPage loginPage = new LoginToInternetBankPage();
        var registeredUser = getRegisteredUser("active");

        loginPage.setLogin(registeredUser.getLogin());
        loginPage.setPassword(registeredUser.getPassword());
        loginPage.pressTheActionLoginButton();

        loginPage.personalArea("Личный кабинет");
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        LoginToInternetBankPage loginPage = new LoginToInternetBankPage();
        var notRegisteredUser = getUser("active");

        loginPage.setLogin(notRegisteredUser.getLogin());
        loginPage.setPassword(notRegisteredUser.getPassword());
        loginPage.pressTheActionLoginButton();

        loginPage.loginErrorWindow("Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        LoginToInternetBankPage loginPage = new LoginToInternetBankPage();
        var blockedUser = getRegisteredUser("blocked");

        loginPage.setLogin(blockedUser.getLogin());
        loginPage.setPassword(blockedUser.getPassword());
        loginPage.pressTheActionLoginButton();

        loginPage.loginErrorWindow("Пользователь заблокирован");
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        LoginToInternetBankPage loginPage = new LoginToInternetBankPage();
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();

        loginPage.setLogin(wrongLogin);
        loginPage.setPassword(registeredUser.getPassword());
        loginPage.pressTheActionLoginButton();

        loginPage.loginErrorWindow("Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        LoginToInternetBankPage loginPage = new LoginToInternetBankPage();
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();

        loginPage.setLogin(registeredUser.getLogin());
        loginPage.setPassword(wrongPassword);
        loginPage.pressTheActionLoginButton();

        loginPage.loginErrorWindow("Неверно указан логин или пароль");
    }
}
