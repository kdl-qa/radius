package com.radius.config;

import com.radius.data.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by kdl on 02.02.16.
 */
public class Helpers {

    private WebDriverWait wait;
    private TouchActions touchScreen;
    private AndroidDriver driver;

    public Helpers(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.touchScreen = touchScreen;
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
        if (Chats.sideBar.isDisplayed()){
            for (WebElement item : Chats.sideBarMenuItems)
                if (item.getText().equalsIgnoreCase(text)) {
                    item.click();
                    break;
                } else continue;

        } else {
            touchScreen.flick(Chats.sideBar, -10, 0, 80).perform();
            return false;
        }
        return true;
    }

    public boolean openMenuItem(MenuItems menu_item) {
        Chats.menuIcon.click();
        switch (menu_item) {
            case CHATS:
                wait.until(ExpectedConditions.visibilityOf(Chats.sideBarMenuItems.get(0)));
                Chats.sideBarMenuItems.get(0).click();
                return Chats.s_text.getText().equalsIgnoreCase("Чаты");
            case VENUES:
                Chats.sideBarMenuItems.get(1).click();
//                wait.until(ExpectedConditions.visibilityOf(Chats.actionTabs.get(1)));
                return Chats.s_text.getText().equalsIgnoreCase("Места");
            case CONTACTS:
                Chats.sideBarMenuItems.get(2).click();
//                wait.until(ExpectedConditions.visibilityOf(Chats.actionTabs.get(2)));
                return Chats.s_text.getText().equalsIgnoreCase("Контакты");
            case SETTINGS:
                Chats.sideBarMenuItems.get(3).click();
                return Chats.s_text.getText().equalsIgnoreCase("Настройки");
            case ABOUT_APP:
                Chats.sideBarMenuItems.get(4).click();
                return Chats.s_text.getText().equalsIgnoreCase("Информация о приложении");
            default:
                return false;
        }
    }

    public boolean openActionTab(Tabs expectedTab) {
        switch (expectedTab) {
            case MAIN_CONTACTS_TAB:
            case DIALOG_TAB:
                Chats.actionTabs.get(0).click();
                wait.until(ExpectedConditions.visibilityOf(Chats.actionTabs.get(0)));
                return Chats.actionTabs.get(0).isSelected();
            case PUBLIC_CONTACTS_TAB:
            case GROUP_TAB:
                Chats.actionTabs.get(1).click();
                wait.until(ExpectedConditions.visibilityOf(Chats.actionTabs.get(1)));
                return Chats.actionTabs.get(1).isSelected();
            case VENUES_TAB:
                Chats.actionTabs.get(2).click();
                wait.until(ExpectedConditions.visibilityOf(Chats.actionTabs.get(2)));
                return Chats.actionTabs.get(2).isSelected();
            default:
                return false;
        }
    }



    public boolean createChat() {
        wait.until(ExpectedConditions.visibilityOf(Chats.createChatBtn));
        Chats.createChatBtn.click();
        return true;
    }

    public boolean submitCreateChat() {
        if (Chats.contactMarked.isDisplayed()) {
            Chats.submitCrtBtn.click();
        }
        return true;
    }

    public boolean checkChatPresence(String chatName) {
        for (WebElement contact : Chats.dialogName)
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
                wait.until(ExpectedConditions.visibilityOf(Chats.chatsScreenTitle));
                return Chats.chatsScreenTitle.getText().equals(expectedScreenTitle);
            case DIALOG_SCREEN:
            case GROUP_SCREEN:
//                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(Chats.chat_title)));
                wait.until(ExpectedConditions.visibilityOf(Chats.chatTitle));
                return Chats.chatTitle.getText().equals(expectedScreenTitle);
            case VENUE_SCREEN:
                return true;
            case CREATE_CHAT_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(Chats.actionBar));
                return Chats.s_text.getText().equals(expectedScreenTitle);
            case CONTACTS_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(Chats.actionBar));
                return Chats.s_text.getText().equals(expectedScreenTitle);
            case CONTACT_PROFILE_SCREEN:
                wait.until(ExpectedConditions.visibilityOf(Chats.actionBar));
                return Chats.s_text.getText().equals(expectedScreenTitle);
            default:
                return false;
        }
    }

    public boolean goBack() {
        if (Chats.actionBar.isDisplayed()) {
            Chats.backBtn.click();
            return true;
        } else return false;
    }

    public boolean checkContactListTabs(String tab_name) {
        wait.until(ExpectedConditions.visibilityOf(Chats.contactListTab));
        switch (tab_name) {
            case "main_profile":
                if (Chats.contactList.isEnabled() & Chats.actionTabs.get(0).isSelected()) {
                    return Chats.submitCrtBtn.isDisplayed();
                }
            case "public_profile":
                if (Chats.contactList.isEnabled() & Chats.actionTabs.get(1).isSelected()) {
                    return Chats.contactPublic_openChat.isDisplayed();
                }
            default:
                return false;
        }
    }

    public boolean activateContactListTab(int index) {
        wait.until(ExpectedConditions.visibilityOf(Chats.contactListTab));
        switch (index) {
            case 0:
                Chats.actionTabs.get(0).click();
                break;
            case 1:
                Chats.actionTabs.get(1).click();
                break;
            default:
                return false;
        }
        return true;
    }
}
