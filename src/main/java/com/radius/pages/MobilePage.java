package com.radius.pages;

import com.radius.helpers.MenuItems;
import com.radius.helpers.ScreenTitles;
import com.radius.helpers.AppTabs;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by kdl on 11.03.16.
 */
public class MobilePage {

    /*===================================== Locators ===================================================*/

    /*------------------------------------ Navigation -------------------------------------------*/
//
//    @FindBy(name = "Чаты")
//    public static WebElement chatsScreenTitle;

    //todo => tab by class or id (for search by index)
    @FindBy(className = "android.support.v7.app.ActionBar$Tab")
    public static List<WebElement> actionTabs;

    @FindBy(id = "toolbar")
    public static WebElement actionBar;

//
//    @FindBy(id = "username_toolbar")
//    public static WebElement chatTitle;

    @FindBy(id = "text_area")
    public static WebElement actionTabText;

    @FindBy(id = "tab_avatar")
    public static WebElement contactsTabAvatar;

    @FindBy(className = "android.widget.TextView")
    public static WebElement s_text;

    @FindBy(xpath = "//android.widget.TextView[@index='1']")
    public static WebElement navTitle;

//    @FindBy(xpath = "//android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='0']")
//    public static WebElement chatTitle1;

//    @FindBy(id = "fab_contacts")
//    public static WebElement createChatBtn;

    @FindBy(className = "android.widget.ImageButton")
    public static WebElement menuIcon, backBtn;

//    @FindBy(id = "list_chats")
//    public static WebElement chatsListView;

    @FindBy(id = "text_view_chat_name")
    public static List<WebElement> chatItemName;


    /*-------------------------------------- Side Bar ----------------------------------------*/
    @FindBy(id = "design_navigation_view")
    public static WebElement sideBar;

    //    @FindBy(id = "design_menu_item_text")
    @FindBy(className = "android.widget.CheckedTextView")
    public static List<WebElement> sideBarMenuItems;

    @FindBy(id = "list_contacts")
    public static WebElement contactList;



    @FindBy(id = "text_view_contact_name")
    public static List<WebElement> contactsName;


    /*===================================== Methods ===================================================*/

    /**
     * Define AndroidDriver
     */
    public AndroidDriver driver;
    public WebDriverWait wait;
    public TouchActions touchScreen;


    public MobilePage (AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
        this.touchScreen = touchScreen;
    }


    /**
     * Get driver
     */
    public AndroidDriver getDriver() {
        return driver;
    }

    public static String generateText(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public void findAndClickGivenElement(WebDriver driver, List<WebElement> listOfElements, WebElement lastListElem, WebDriverWait wait, TouchActions touchScreen, String elementToFind) {
        while (true) {
            if (checkElementPresence(listOfElements, elementToFind)) {
                WebElement c = driver.findElement(By.name(elementToFind));
                c.click();
                break;
            } else {
                wait.until(presenceOfElementLocated(By.id("list")));
                touchScreen.flick(lastListElem, 0, -600, 80).perform();
            }
        }
    }


    // check given list for element presence
    public static boolean checkElementPresence(List<WebElement> listOfElements, String elementToFind) {
        for (WebElement elem : listOfElements) {
            System.out.println(elem.getText());
            if (elem.getText().equals(elementToFind)) { //also we could use "contains" to search
                System.out.println("true");
                elem.click();
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

    public boolean openMenuItemByText(String text) {
        if (sideBar.isDisplayed()){
            for (WebElement item : sideBarMenuItems)
                if (item.getText().equalsIgnoreCase(text)) {
                    item.click();
                    break;
                } else continue;

        } else {
            touchScreen.flick(sideBar, -10, 0, 80).perform();
            return false;
        }
        return true;
    }

    public boolean openMenuItem(MenuItems menu_item) {
        menuIcon.click();
        switch (menu_item) {
            case CHATS:
                wait.until(ExpectedConditions.visibilityOf(sideBarMenuItems.get(0)));
                sideBarMenuItems.get(0).click();
                return s_text.getText().equalsIgnoreCase("Чаты");
            case VENUES:
                sideBarMenuItems.get(1).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(1)));
                return s_text.getText().equalsIgnoreCase("Места");
            case CONTACTS:
                sideBarMenuItems.get(2).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(2)));
                return s_text.getText().equalsIgnoreCase("Контакты");
            case SETTINGS:
                sideBarMenuItems.get(3).click();
                return s_text.getText().equalsIgnoreCase("Настройки");
            case ABOUT_APP:
                sideBarMenuItems.get(4).click();
                return s_text.getText().equalsIgnoreCase("Информация о приложении");
            default:
                return false;
        }
    }

    public boolean openActionTab(AppTabs expectedTab) {
        switch (expectedTab) {
            case MAIN_CONTACTS_TAB:
            case DIALOG_TAB:
                actionTabs.get(0).click();
                wait.until(ExpectedConditions.visibilityOf(actionTabs.get(0)));
                return actionTabs.get(0).isSelected();
            case PUBLIC_CONTACTS_TAB:
            case GROUP_TAB:
                actionTabs.get(1).click();
                wait.until(ExpectedConditions.visibilityOf(actionTabs.get(1)));
                return actionTabs.get(1).isSelected();
            case VENUES_TAB:
                actionTabs.get(2).click();
                wait.until(ExpectedConditions.visibilityOf(actionTabs.get(2)));
                return actionTabs.get(2).isSelected();
            default:
                return false;
        }
    }



    public boolean createChat() {
        wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.createChatBtn));
        DialogAndGroupPage.createChatBtn.click();
        return true;
    }

    public boolean submitCreateChat() {
        if (ContactsPage.contactMarked.isDisplayed()) {
            DialogAndGroupPage.submitCrtBtn.click();
        }
        return true;
    }

    public boolean checkChatPresence(String chatName) {
        for (WebElement contact : DialogAndGroupPage.chatItemName)
            if (contact.getText().equalsIgnoreCase(chatName)) {
                break;
            } else System.out.println("Chat has another name!");
        return true;
    }

    public boolean checkContactPresence(String contacter) {
        for (WebElement contact : /*ContactsPage.*/contactsName)
            if (contact.getText().equalsIgnoreCase(contacter)) {
                return true;
            }
        return false;
    }


    public boolean tapOnContact(String contacter) {
        for (WebElement contact : /*ContactsPage.*/contactsName)
            if (contact.getText().equalsIgnoreCase(contacter)) {
                contact.click();
                break;
            }
        return true;
    }

    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
        switch (screenTitle) {
            case CHATS_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.chatsScreenTitle));
                return MobilePage.navTitle.getText().equals(expectedScreenTitle);
            case VENUE_SCREEN:
            case DIALOG_SCREEN:
            case GROUP_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.chatTitle));
                return DialogAndGroupPage.chatTitle.getText().equals(expectedScreenTitle);
            case CREATE_CHAT_SCREEN:
            case CONTACTS_SCREEN:
            case CONTACT_PROFILE_SCREEN:
            case SETTINGS_SCREEN:
            case EDIT_PROFILE_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(actionBar));
                return navTitle.getText().equals(expectedScreenTitle);
            default:
                return false;
        }
    }

    public boolean goBack() {
        if (actionBar.isDisplayed()) {
            backBtn.click();
            return true;
        } else return false;
    }

    public boolean checkContactListTabs(String tab_name) {
        wait.until(ExpectedConditions.visibilityOf(actionTabText));
        switch (tab_name) {
            case "main_profile":
                if (/*ContactsPage.*/contactList.isEnabled() & actionTabs.get(0).isSelected()) {
                    return DialogAndGroupPage.submitCrtBtn.isDisplayed();
                }
            case "public_profile":
                if (/*ContactsPage.*/contactList.isEnabled() & actionTabs.get(1).isSelected()) {
                    return ContactsPage.contactPublic_openChat.isDisplayed() || ContactsPage.contactPublic_listEmpty.isDisplayed();
                }
            case "no_public_profile":
                if (ContactsPage.publicUserEmpty.isEnabled() & actionTabs.get(1).isSelected()) {
                    return ContactsPage.createPublicProfileBtn.isDisplayed();
                }
            default:
                return false;
        }
    }

    public boolean activateContactListTab(int index) {
        wait.until(ExpectedConditions.visibilityOf(actionTabText));
        switch (index) {
            case 0:
                actionTabs.get(0).click();
                break;
            case 1:
                actionTabs.get(1).click();
                break;
            default:
                return false;
        }
        return true;
    }

    public void logout() {
        UserSettingsPage.logoutBtn.click();
    }

}
