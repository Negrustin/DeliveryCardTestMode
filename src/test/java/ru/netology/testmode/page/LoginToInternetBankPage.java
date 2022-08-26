package ru.netology.testmode.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginToInternetBankPage {

    private SelenideElement loginInput = $x("//*[@data-test-id ='login']//input");
    private SelenideElement passwordInput = $x("//*[@data-test-id ='password']//input");
    private SelenideElement actionLoginButton = $x("//button[@data-test-id = 'action-login']");
    private SelenideElement personalArea = $x("//h2[contains(text(),'Личный кабинет')]");
    private SelenideElement loginError = $x("//*[@data-test-id = 'error-notification']/div[@class = 'notification__content']");


public void setLogin(String login){

    loginInput.setValue(login);
}

public void setPassword(String password){
    passwordInput.setValue(password);
}
public void pressTheActionLoginButton(){

    actionLoginButton.click();
}

public void personalArea(String text){
    personalArea.shouldBe(Condition.visible,Condition.text(text));
}
public void loginErrorWindow(String text){
    loginError.shouldBe(Condition.visible,Condition.text(text));
    }
}
