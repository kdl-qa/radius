package com.radius.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by kdl on 17.02.16.
 */
public class ContactsPage extends MobilePage {

    /*===================================== Locators ===================================================*/

    @FindBy(id = "root_view")
    public static List<WebElement> contactsList;

    @FindBy(id = "text_view_contact_name")
    public static List<WebElement> contactsName;

    @FindBy(id = "room_picture")
    public static WebElement contactUserProfileAvatar;

    @FindBy(id = "start_chat")
    public static WebElement contact_createChatBtn;

    @FindBy(id = "block_user_button")
    public static WebElement contact_blockUserBtn;


    public ContactsPage(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        super(driver, wait, touchScreen);
    }

    /*==================================== Methods =====================================================*/


}
