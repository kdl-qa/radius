package com.radius.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by kdl on 15.03.16.
 */
public class Cnt extends MobilePage{
        /*===================================== Locators ===================================================*/

    /**
     * contacts list
     */
    @FindBy(id = "root_view")
    public static List<WebElement> contactsList;

    /**
     * contact name
     */
    @FindBy(id = "text_view_contact_name")
    public static List<WebElement> contactName;

    /**
     * user profile avatar
     */
    @FindBy(id = "room_picture")
    public static WebElement contactUserProfileAvatar;

    /**
     * user profile start chat button
     */
    @FindBy(id = "start_chat")
    public static WebElement contact_createChatBtn;

    /**
     * user profile block user button
     */
    @FindBy(id = "block_user_button")
    public static WebElement contact_blockUserBtn;

    /**
     * user contact marked to chat
     */
    @FindBy(id = "selected_indicator")
    public static WebElement contactMarked;

    /**
     * public user profile is empty label
     */
    @FindBy(id = "public_user_empty")
    public static WebElement publicUserEmpty;

    /**
     * public user profile open chat button
     */
    @FindBy(id = "info_open_chat")
    public static WebElement contactPublic_openChat;

    /**
     * public profile empty contacts list
     */
    @FindBy(id = "empty_view")
    public static WebElement contactPublic_listEmpty;

    /**
     * create public user profile button
     */
    @FindBy(id = "create_public_profile_button")
    public static WebElement createPublicProfileBtn;

    public Cnt(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        super(driver, wait, touchScreen);
        PageFactory.initElements(driver, this);
    }




    /*==================================== Methods =====================================================*/

}
