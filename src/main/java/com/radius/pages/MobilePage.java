package com.radius.pages;

import com.radius.helpers.MenuItems;
import com.radius.helpers.ScreenTitles;
import com.radius.helpers.Tabs;
import com.radius.pages.Contacts;
import com.radius.pages.DialogAndChats;
import com.radius.pages.UserSettings;
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

    /*===========================Side Bar==========================*/
    @FindBy(id = "design_navigation_view")
    public static WebElement sideBar;

    //    @FindBy(id = "design_menu_item_text")
    @FindBy(className = "android.widget.CheckedTextView")
    public static List<WebElement> sideBarMenuItems;

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
        DialogAndChats.menuIcon.click();
        switch (menu_item) {
            case CHATS:
                wait.until(ExpectedConditions.visibilityOf(sideBarMenuItems.get(0)));
                sideBarMenuItems.get(0).click();
                return DialogAndChats.s_text.getText().equalsIgnoreCase("Чаты");
            case VENUES:
                sideBarMenuItems.get(1).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.actionTabs.get(1)));
                return DialogAndChats.s_text.getText().equalsIgnoreCase("Места");
            case CONTACTS:
                sideBarMenuItems.get(2).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.actionTabs.get(2)));
                return DialogAndChats.s_text.getText().equalsIgnoreCase("Контакты");
            case SETTINGS:
                sideBarMenuItems.get(3).click();
                return DialogAndChats.s_text.getText().equalsIgnoreCase("Настройки");
            case ABOUT_APP:
                sideBarMenuItems.get(4).click();
                return DialogAndChats.s_text.getText().equalsIgnoreCase("Информация о приложении");
            default:
                return false;
        }
    }

    public boolean openActionTab(Tabs expectedTab) {
        switch (expectedTab) {
            case MAIN_CONTACTS_TAB:
            case DIALOG_TAB:
                DialogAndChats.actionTabs.get(0).click();
                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.actionTabs.get(0)));
                return DialogAndChats.actionTabs.get(0).isSelected();
            case PUBLIC_CONTACTS_TAB:
            case GROUP_TAB:
                DialogAndChats.actionTabs.get(1).click();
                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.actionTabs.get(1)));
                return DialogAndChats.actionTabs.get(1).isSelected();
            case VENUES_TAB:
                DialogAndChats.actionTabs.get(2).click();
                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.actionTabs.get(2)));
                return DialogAndChats.actionTabs.get(2).isSelected();
            default:
                return false;
        }
    }



    public boolean createChat() {
        wait.until(ExpectedConditions.visibilityOf(DialogAndChats.createChatBtn));
        DialogAndChats.createChatBtn.click();
        return true;
    }

    public boolean submitCreateChat() {
        if (DialogAndChats.contactMarked.isDisplayed()) {
            DialogAndChats.submitCrtBtn.click();
        }
        return true;
    }

    public boolean checkChatPresence(String chatName) {
        for (WebElement contact : DialogAndChats.dialogName)
            if (contact.getText().equalsIgnoreCase(chatName)) {
                break;
            } else System.out.println("Chat has another name!");
        return true;
    }

    public boolean checkContactPresence(String contactName) {
        for (WebElement contact : Contacts.contactsName)
            if (contact.getText().equalsIgnoreCase(contactName)) return true;
        return false;
    }


    public boolean tapOnContact(String contactName) {
        for (WebElement contact : Contacts.contactsName)
            if (contact.getText().equalsIgnoreCase(contactName)) {
                contact.click();
                break;
            }
        return true;
    }

    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
        switch (screenTitle) {
            case CHATS_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.chatsScreenTitle));
                return DialogAndChats.navTitle.getText().equals(expectedScreenTitle);
            case VENUE_SCREEN:
            case DIALOG_SCREEN:
            case GROUP_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.chatTitle));
                return DialogAndChats.chatTitle.getText().equals(expectedScreenTitle);
            case CREATE_CHAT_SCREEN:
            case CONTACTS_SCREEN:
            case CONTACT_PROFILE_SCREEN:
            case SETTINGS_SCREEN:
            case EDIT_PROFILE_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(DialogAndChats.actionBar));
                return DialogAndChats.navTitle.getText().equals(expectedScreenTitle);
            default:
                return false;
        }
    }

    public boolean goBack() {
        if (DialogAndChats.actionBar.isDisplayed()) {
            DialogAndChats.backBtn.click();
            return true;
        } else return false;
    }

    public boolean checkContactListTabs(String tab_name) {
        wait.until(ExpectedConditions.visibilityOf(DialogAndChats.contactListTab));
        switch (tab_name) {
            case "main_profile":
                if (DialogAndChats.contactList.isEnabled() & DialogAndChats.actionTabs.get(0).isSelected()) {
                    return DialogAndChats.submitCrtBtn.isDisplayed();
                }
            case "public_profile":
                if (DialogAndChats.contactList.isEnabled() & DialogAndChats.actionTabs.get(1).isSelected()) {
                    return DialogAndChats.contactPublic_openChat.isDisplayed();
                }
            case "no_public_profile":
                if (DialogAndChats.publicUserEmpty.isEnabled() & DialogAndChats.actionTabs.get(1).isSelected()) {
                    return DialogAndChats.createPublicProfileBtn.isDisplayed();
                }
            default:
                return false;
        }
    }

    public boolean activateContactListTab(int index) {
        wait.until(ExpectedConditions.visibilityOf(DialogAndChats.contactListTab));
        switch (index) {
            case 0:
                DialogAndChats.actionTabs.get(0).click();
                break;
            case 1:
                DialogAndChats.actionTabs.get(1).click();
                break;
            default:
                return false;
        }
        return true;
    }

    public void logout() {
        UserSettings.logoutBtn.click();
    }

}
