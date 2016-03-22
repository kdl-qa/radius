package com.radius.pages;

import com.radius.helpers.AppData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kdl on 22.03.16.
 */
public class WelcomePage extends MobilePage {
    public WelcomePage(AndroidDriver driver, TouchActions touchScreen) {
        super(driver, touchScreen);
    }

    /*===================================== Locators ===================================================*/

    @FindBy(id = "title_welcome")
    public static WebElement slideTitle;

    @FindBy(id = "info_welcome")
    public static WebElement slideText;

    @FindBy(xpath = "//android.support.v7.app.ActionBar$Tab[@index='0']/android.widget.ImageView")
    public static WebElement welcomeSlide1;

    @FindBy(xpath = "//android.support.v7.app.ActionBar$Tab[@index='1']/android.widget.ImageView")
    public static WebElement welcomeSlide2;

    @FindBy(xpath = "//android.support.v7.app.ActionBar$Tab[@index='2']/android.widget.ImageView")
    public static WebElement welcomeSlide3;

    @FindBy(xpath = "//android.support.v7.app.ActionBar$Tab[@index='3']/android.widget.ImageView")
    public static WebElement welcomeSlide4;

    @FindBy(xpath = "//android.support.v7.app.ActionBar$Tab[@index='4']/android.widget.ImageView")
    public static WebElement welcomeSlide5;

    @FindBy(id = "button_start")
    public static WebElement startButton;

    /*===================================== Methods ===================================================*/
    /**
     * Check welcome slide title
     */
    public boolean checkWelcomeTitle(int slide) {
        switch (slide){
            case 1:
                welcomeSlide1.click();
                return slideTitle.getText().equalsIgnoreCase(AppData.welcomeTitle1);
            case 2:
                welcomeSlide3.click();
                return slideTitle.getText().equalsIgnoreCase(AppData.welcomeTitle2);
            case 3:
                welcomeSlide3.click();
                return slideTitle.getText().equalsIgnoreCase(AppData.welcomeTitle3);
            case 4:
                welcomeSlide4.click();
                return slideTitle.getText().equalsIgnoreCase(AppData.welcomeTitle4);
            case 5:
                welcomeSlide5.click();
                return slideTitle.getText().equalsIgnoreCase(AppData.welcomeTitle5);
            default:
                return false;
        }
    }

    /**
     * Check welcome slide text
     */
    public boolean checkWelcomeSlideText(int slide) {
        switch (slide){
            case 1:
                return slideText.getText().equalsIgnoreCase(AppData.welcomeText1);
            case 2:
                return slideText.getText().equalsIgnoreCase(AppData.welcomeText1);
            case 3:
                return slideText.getText().equalsIgnoreCase(AppData.welcomeText1);
            case 4:
                return slideText.getText().equalsIgnoreCase(AppData.welcomeText1);
            case 5:
                return slideText.getText().equalsIgnoreCase(AppData.welcomeText1);
            default:
                return false;
        }
    }

    public void startCommunication() {
        startButton.click();
    }

    


}
