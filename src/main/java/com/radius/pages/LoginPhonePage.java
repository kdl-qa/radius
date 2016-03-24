package com.radius.pages;

import com.radius.helpers.AppData;
import com.radius.helpers.ScreenTitles;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by kdl on 24.03.16.
 */
public class LoginPhonePage extends MobilePage {
    public LoginPhonePage(AndroidDriver driver, TouchActions touchScreen) {
        super(driver, touchScreen);
    }

    /*===================================== Locators =========================================*/

    @FindBy(xpath = "//android.view.View/android.widget.TextView[@index='0']")
    public static WebElement loginPhoneScreenTitle;

    @FindBy(xpath = "//android.view.View/android.widget.TextView[@index='1']")
    public static WebElement loginCountryScreenTitle;

    @FindBy(id = "frame_select_country_code")
    public static WebElement countrySelect;

    @FindBy(id = "phone_number_edit_text")
    public static WebElement phoneNumberInput;

    @FindBy(id = "continue_button")
    public static WebElement continueButton;

    @FindBy(id = "policy_text")
    public static WebElement policyAndTerms;

    @FindBy(id = "country_name")
    public static List<WebElement> countryName;

    @FindBy(id = "selected_indicator")
    public static WebElement selectedIndicator;


    /*===================================== Methods =========================================*/

    @Override
    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
        switch (screenTitle) {
            case LOGIN_PHONE_SCREEN:
                waitForElement(countrySelect);
                System.out.println("Login phone screen");
                return loginPhoneScreenTitle.getText().equals(AppData.loginPhoneScreenTitle);
            case LOGIN_COUNTRIES_SCREEN:
                waitForListElements(countryName);
                System.out.println("Country list screen");
                return loginCountryScreenTitle.getText().equals(AppData.loginCountryScreenTitle);
            default:
                return false;
            }
    }

    public void openCountryList(){
        countrySelect.click();
    }

    public void chooseCountryFromList() {

    }


}
