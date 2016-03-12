package com.radius.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    public static List<WebElement> contactName;

    @FindBy(id = "room_picture")
    public static WebElement contactUserProfileAvatar;

    @FindBy(id = "start_chat")
    public static WebElement contact_createChatBtn;

    @FindBy(id = "block_user_button")
    public static WebElement contact_blockUserBtn;


    public static final String marked_contact = "selected_indicator";

    @FindBy(id = "selected_indicator")
    public static WebElement contactMarked;

    @FindBy(id = "list_contacts")
    public static WebElement contactList;

    @FindBy(id = "public_user_empty")
    public static WebElement publicUserEmpty;

    @FindBy(id = "root_view")
    public static List<WebElement> contactItems;

    @FindBy(id = "info_open_chat")
    public static WebElement contactPublic_openChat;

    @FindBy(id = "empty_view")
    public static WebElement contactPublic_listEmpty;

    @FindBy(id = "create_public_profile_button")
    public static WebElement createPublicProfileBtn;

    /*==================================== Methods =====================================================*/

    public ContactsPage(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        super(driver, wait, touchScreen);
        PageFactory.initElements(driver, this);
    }


}
