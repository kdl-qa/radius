package com.radius.pages;

import com.radius.helpers.ScreenTitles;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by kdl on 17.02.16.
 */
public class ContactsPage extends MobilePage {

    /*===================================== Locators ===================================================*/

    /**
     * contacts list
     */
    @FindBy(id = "root_view")
    public static List<WebElement> contactListItem;

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
     * contact list
     */
    @FindBy(id = "list_contacts")
    public WebElement contactList;



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






    /*==================================== Methods =====================================================*/

    public ContactsPage(AndroidDriver driver, TouchActions touchScreen) {
        super(driver, touchScreen);
        PageFactory.initElements(driver,this);
    }

    /**
     * Check contact tab presence
     */
    public boolean checkContactListTabs(String tab_name) {
        waitForElement(actionTabText);
        switch (tab_name) {
            case "main_profile":
                if (contactList.isEnabled() & actionTabs.get(0).isSelected()) {
                    return submitCrtBtn.isDisplayed();
                }
            case "public_profile":
                if (contactList.isEnabled() & actionTabs.get(1).isSelected()) {
                    System.out.println("public contacts is present");
                    return contactPublic_openChat.isDisplayed();
                }
            case "public_profile_empty":
                if (actionTabs.get(1).isSelected()) {
                    System.out.println("public contacts is empty");
                    return contactPublic_listEmpty.isDisplayed();
                }
            case "no_public_profile":
                if (publicUserEmpty.isEnabled() & actionTabs.get(1).isSelected()) {
                    return createPublicProfileBtn.isDisplayed();
                }
            default:
                return false;
        }
    }

    /**
     * Check title of the screen presence
     */
    @Override
    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
        switch (screenTitle) {

            case CONTACTS_SCREEN:
            case CONTACT_PROFILE_SCREEN:
                waitForElement(actionBar);
                System.out.println("contacts screen");
                return navTitle.getText().equals(expectedScreenTitle);
            default:
                return false;
        }
    }

    /**
     * Choose contact from list
     */
    public boolean tapOnContact(String contacter) {
        for (WebElement contact : contactName)
            if (contact.getText().equalsIgnoreCase(contacter)) {
                contact.click();
                break;
            }
        return true;
    }

}
