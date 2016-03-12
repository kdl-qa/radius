//package com.radius.config;
//
//import com.radius.helpers.MenuItems;
//import com.radius.helpers.ScreenTitles;
//import com.radius.helpers.AppTabs;
//import com.radius.pages.ContactsPage;
//import com.radius.pages.DialogAndGroupPage;
//import com.radius.pages.MobilePage;
//import com.radius.pages.UserSettingsPage;
//import io.appium.java_client.android.AndroidDriver;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.touch.TouchActions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.util.List;
//import java.util.Random;
//
//import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
//
//
///**
// * Created by kdl on 02.02.16.
// */
//public class Helpers extends MobilePage {
//
//    /**
//     * Constructor
//     */
//    public Helpers(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
//        super(driver, wait, touchScreen);
//    }
//
//
//    public static String generateText(Random rng, String characters, int length) {
//        char[] text = new char[length];
//        for (int i = 0; i < length; i++) {
//            text[i] = characters.charAt(rng.nextInt(characters.length()));
//        }
//        return new String(text);
//    }
//
//    public void findAndClickGivenElement(WebDriver driver, List<WebElement> listOfElements, WebElement lastListElem, WebDriverWait wait, TouchActions touchScreen, String elementToFind) {
//        while (true) {
//            if (checkElementPresence(listOfElements, elementToFind)) {
//                WebElement c = driver.findElement(By.name(elementToFind));
//                c.click();
//                break;
//            } else {
//                wait.until(presenceOfElementLocated(By.id("list")));
//                touchScreen.flick(lastListElem, 0, -600, 80).perform();
//            }
//        }
//    }
//
//
//    // check given list for element presence
//    public static boolean checkElementPresence(List<WebElement> listOfElements, String elementToFind) {
//        for (WebElement elem : listOfElements) {
//            System.out.println(elem.getText());
//            if (elem.getText().equals(elementToFind)) { //also we could use "contains" to search
//                System.out.println("true");
//                elem.click();
//                return true;
//            }
//        }
//        System.out.println("false");
//        return false;
//    }
//
//    public boolean openMenuItemByText(String text) {
//        if (DialogAndGroupPage.sideBar.isDisplayed()){
//            for (WebElement item : DialogAndGroupPage.sideBarMenuItems)
//                if (item.getText().equalsIgnoreCase(text)) {
//                    item.click();
//                    break;
//                } else continue;
//
//        } else {
//            touchScreen.flick(DialogAndGroupPage.sideBar, -10, 0, 80).perform();
//            return false;
//        }
//        return true;
//    }
//
//    public boolean openMenuItem(MenuItems menu_item) {
//        DialogAndGroupPage.menuIcon.click();
//        switch (menu_item) {
//            case CHATS:
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.sideBarMenuItems.get(0)));
//                DialogAndGroupPage.sideBarMenuItems.get(0).click();
//                return DialogAndGroupPage.s_text.getText().equalsIgnoreCase("Чаты");
//            case VENUES:
//                DialogAndGroupPage.sideBarMenuItems.get(1).click();
////                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(1)));
//                return DialogAndGroupPage.s_text.getText().equalsIgnoreCase("Места");
//            case CONTACTS:
//                DialogAndGroupPage.sideBarMenuItems.get(2).click();
////                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(2)));
//                return DialogAndGroupPage.s_text.getText().equalsIgnoreCase("Контакты");
//            case SETTINGS:
//                DialogAndGroupPage.sideBarMenuItems.get(3).click();
//                return DialogAndGroupPage.s_text.getText().equalsIgnoreCase("Настройки");
//            case ABOUT_APP:
//                DialogAndGroupPage.sideBarMenuItems.get(4).click();
//                return DialogAndGroupPage.s_text.getText().equalsIgnoreCase("Информация о приложении");
//            default:
//                return false;
//        }
//    }
//
//    public boolean openActionTab(AppTabs expectedTab) {
//        switch (expectedTab) {
//            case MAIN_CONTACTS_TAB:
//            case DIALOG_TAB:
//                DialogAndGroupPage.actionTabs.get(0).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(0)));
//                return DialogAndGroupPage.actionTabs.get(0).isSelected();
//            case PUBLIC_CONTACTS_TAB:
//            case GROUP_TAB:
//                DialogAndGroupPage.actionTabs.get(1).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(1)));
//                return DialogAndGroupPage.actionTabs.get(1).isSelected();
//            case VENUES_TAB:
//                DialogAndGroupPage.actionTabs.get(2).click();
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabs.get(2)));
//                return DialogAndGroupPage.actionTabs.get(2).isSelected();
//            default:
//                return false;
//        }
//    }
//
//
//
//    public boolean createChat() {
//        wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.createChatBtn));
//        DialogAndGroupPage.createChatBtn.click();
//        return true;
//    }
//
//    public boolean submitCreateChat() {
//        if (DialogAndGroupPage.contactMarked.isDisplayed()) {
//            DialogAndGroupPage.submitCrtBtn.click();
//        }
//        return true;
//    }
//
//    public boolean checkChatPresence(String chatName) {
//        for (WebElement contact : DialogAndGroupPage.chatItemName)
//            if (contact.getText().equalsIgnoreCase(chatName)) {
//                break;
//            } else System.out.println("Chat has another name!");
//        return true;
//    }
//
//    public boolean checkContactPresence(String contactName) {
//        for (WebElement contact : ContactsPage.contactName)
//            if (contact.getText().equalsIgnoreCase(contactName)) return true;
//        return false;
//    }
//
//
//    public boolean tapOnContact(String contactName) {
//        for (WebElement contact : ContactsPage.contactName)
//            if (contact.getText().equalsIgnoreCase(contactName)) {
//                contact.click();
//                break;
//            }
//        return true;
//    }
//
//    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
//        switch (screenTitle) {
//            case CHATS_SCREEN:
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.chatsScreenTitle));
//                return DialogAndGroupPage.navTitle.getText().equals(expectedScreenTitle);
//            case VENUE_SCREEN:
//            case DIALOG_SCREEN:
//            case GROUP_SCREEN:
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.chatTitle));
//                return DialogAndGroupPage.chatTitle.getText().equals(expectedScreenTitle);
//            case CREATE_CHAT_SCREEN:
//            case CONTACTS_SCREEN:
//            case CONTACT_PROFILE_SCREEN:
//            case SETTINGS_SCREEN:
//            case EDIT_PROFILE_SCREEN:
//                wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionBar));
//                return DialogAndGroupPage.navTitle.getText().equals(expectedScreenTitle);
//            default:
//                return false;
//        }
//    }
//
//    public boolean goBack() {
//        if (DialogAndGroupPage.actionBar.isDisplayed()) {
//            DialogAndGroupPage.backBtn.click();
//            return true;
//        } else return false;
//    }
//
//    public boolean checkContactListTabs(String tab_name) {
//        wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabText));
//        switch (tab_name) {
//            case "main_profile":
//                if (DialogAndGroupPage.contactList.isEnabled() & DialogAndGroupPage.actionTabs.get(0).isSelected()) {
//                    return DialogAndGroupPage.submitCrtBtn.isDisplayed();
//                }
//            case "public_profile":
//                if (DialogAndGroupPage.contactList.isEnabled() & DialogAndGroupPage.actionTabs.get(1).isSelected()) {
//                    return DialogAndGroupPage.contactPublic_openChat.isDisplayed();
//                }
//            case "no_public_profile":
//                if (DialogAndGroupPage.publicUserEmpty.isEnabled() & DialogAndGroupPage.actionTabs.get(1).isSelected()) {
//                    return DialogAndGroupPage.createPublicProfileBtn.isDisplayed();
//                }
//            default:
//                return false;
//        }
//    }
//
//    public boolean activateContactListTab(int index) {
//        wait.until(ExpectedConditions.visibilityOf(DialogAndGroupPage.actionTabText));
//        switch (index) {
//            case 0:
//                DialogAndGroupPage.actionTabs.get(0).click();
//                break;
//            case 1:
//                DialogAndGroupPage.actionTabs.get(1).click();
//                break;
//            default:
//                return false;
//        }
//        return true;
//    }
//
//    public void logout() {
//        UserSettingsPage.logoutBtn.click();
//    }
//}
