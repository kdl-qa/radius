package com.radius.pages;

import com.radius.helpers.AppTabs;
import com.radius.helpers.MenuItems;
import com.radius.helpers.ScreenTitles;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by kdl on 11.03.16.
 */
public class MobilePage {

    /*===================================== Locators ===================================================*/

    /*------------------------------------ Navigation -------------------------------------------*/

    /**
     * action bar tabs
     */
    //todo => tab by class or id (for search by index)
    @FindBy(className = "android.support.v7.app.ActionBar$Tab")
    public List<WebElement> actionTabs;

    /**
     * action bar
     */
    @FindBy(id = "toolbar")
    public WebElement actionBar;

//
//    @FindBy(id = "username_toolbar")
//    public static WebElement chatTitle;

    /**
     * action bar tabs name
     */
    @FindBy(id = "text_area")
    public WebElement actionTabText;

    /**
     * action bar contacts tab avatar
     */
    @FindBy(id = "tab_avatar")
    public WebElement contactsTabAvatar;

    /**
     * text by class TextView
     */
    @FindBy(className = "android.widget.TextView")
    public WebElement s_text;

    /**
     * navigation title text
     */
    @FindBy(xpath = "//android.widget.TextView[@index='1']")
    public WebElement navTitle;

    /**
     * menu or back button on action bar
     */
    @FindBy(className = "android.widget.ImageButton")
    public WebElement menuIcon, backBtn;

    /**
     * chat name title
     */
    @FindBy(id = "text_view_chat_name")
    public List<WebElement> chatItemName;


    /*-------------------------------------- Side Bar ----------------------------------------*/
    /**
     * side bar view
     */
    @FindBy(id = "design_navigation_view")
    public WebElement sideBar;

    //    @FindBy(id = "design_menu_item_text")
    @FindBy(className = "android.widget.CheckedTextView")
    public List<WebElement> sideBarMenuItems;

    /*------------------------------------ Buttons and gallery -----------------------------------------*/
    /**
     * gallery button, submit button
     */
    @FindBy(id = "fab_done")
    public static WebElement gallerySubmit, previewSubmit, submitCrtBtn;

    /**
     * album or image
     */
    @FindBy(id = "image")
    public static List<WebElement> imagesList;

    /**
     * delete preview to send image
     */
    @FindBy(id = "delete")
    public static WebElement previewDelImage;


    /*===================================== Methods ===================================================*/

    /**
     * Define AndroidDriver
     */
    public AndroidDriver driver;
    public WebDriverWait wait;
    public TouchActions touchScreen;
    public TouchActions actions;


    public MobilePage (AndroidDriver driver, TouchActions touchScreen) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        this.touchScreen = touchScreen;
        this.actions = new TouchActions(driver);
    }


    public void waitForElement(WebElement element) {
        wait.until(visibilityOf(element));
    }

    public void waitForElement(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(elementToBeClickable(element));
    }

    public void waitForListElements(List<WebElement> list) {
        wait.until(visibilityOfAllElements(list));
    }



    /**
     * Get driver
     */
    public AndroidDriver getDriver() {
        return driver;
    }

    /**
     * Generate random text
     */
    public String generateText(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    /**
     * Find and click element from list
     */
    public void findAndClickGivenElement(WebDriver driver, List<WebElement> listOfElements, WebElement lastListElem, WebDriverWait wait, TouchActions touchScreen, String elementToFind) {
        while (true) {
            if (checkElementPresence(listOfElements, elementToFind)) {
                WebElement c = driver.findElement(By.name(elementToFind));
                c.click();
                break;
            } else {
                wait.until(presenceOfElementLocated(By.id("imagesList")));
                touchScreen.flick(lastListElem, 0, -600, 80).perform();
            }
        }
    }


    /**
     * Check presence element in the list
     */
    // check given imagesList for element presence
    public boolean checkElementPresence(List<WebElement> listOfElements, String elementToFind) {
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


    /**
     * Open actionBar by index
     */
    public boolean tapActionBarTab(int index) {
        actionTabs.get(index).click();
        return true;
    }

    /**
     * Open menu
     */
    public boolean tapMenuIcon() {
        if (actionBar.isDisplayed()) {
            menuIcon.click();
            return true;
        } else {
            System.out.println("Tap on the Menu button is failed!!!");
            return false;
        }
    }


    /**
     * Open menu item by index
     */
    public boolean tapSideBarMenuIndex(int index) {
        if (!sideBar.isDisplayed()) {
            return false;
        } else {
            sideBarMenuItems.get(index).click();
            return true;
        }
    }

    /**
     * Open menu item by text
     */
    public boolean tapSideBarMenuText(String text) {
        tapMenuIcon();
        if (sideBar.isDisplayed()) {
            for (WebElement item : sideBarMenuItems)
                if (item.getText().equalsIgnoreCase(text)) {
                    item.click();
                    break;
                } else continue;

        } else
            touchScreen.flick(sideBar, -10, 0, 80).perform();
        return true;
    }

    /**
     * Open menu item by text from SideBar
     */
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

    /**
     * Open menu item using CONSTANT
     */
    public boolean openMenuItem(MenuItems menu_item) {
        waitForElement(actionBar);
        menuIcon.click();
        switch (menu_item) {
            case CHATS:
                waitForElement(sideBarMenuItems.get(0));
                sideBarMenuItems.get(0).click();
                return s_text.getText().equalsIgnoreCase("Чаты");
            case VENUES:
                sideBarMenuItems.get(1).click();
//                wait.until(ExpectedConditions.visibilityOf(CreateDialogChatPage.actionTabs.get(1)));
                return s_text.getText().equalsIgnoreCase("Места");
            case CONTACTS:
                sideBarMenuItems.get(2).click();
//                wait.until(ExpectedConditions.visibilityOf(CreateDialogChatPage.actionTabs.get(2)));
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

    /**
     * Open ActionTab by index
     */
    public boolean openActionTab(AppTabs expectedTab) {
        return true;
    }


//    /**
//     * Open ActionTab by index
//     */
//    public boolean openActionTab(AppTabs expectedTab) {
//        switch (expectedTab) {
//            case MAIN_CONTACTS_TAB:
//            case DIALOG_TAB:
//                actionTabs.get(0).click();
//                waitForElement(actionTabs.get(0));
//                return actionTabs.get(0).isSelected();
//            case PUBLIC_CONTACTS_TAB:
//            case GROUP_TAB:
//                actionTabs.get(1).click();
//                waitForElement(actionTabs.get(1));
//                return actionTabs.get(1).isSelected();
//            case VENUES_TAB:
//                actionTabs.get(2).click();
//                waitForElement(actionTabs.get(2));
//                return actionTabs.get(2).isSelected();
//            default:
//                return false;
//        }
//    }


    /**
     * Check contact presence
     */
    public boolean checkContactPresence(String contacter) {
        for (WebElement contact : ContactsPage.contactName)
            if (contact.getText().equalsIgnoreCase(contacter)) {
                return true;
            }
        return false;
    }

    /**
     * Check title of the screen presence
     */
    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
        return true;
    }

//    /**
//     * Check title of the screen presence
//     */
//    public boolean checkScreenTitle(ScreenTitles screenTitle, String expectedScreenTitle) {
//        switch (screenTitle) {
//            case CHATS_SCREEN:
//                wait.until(ExpectedConditions.visibilityOf(CreateDialogChatPage.chatsScreenTitle));
//                System.out.println("chats screen");
//                return navTitle.getText().equals(expectedScreenTitle);
//            case VENUE_SCREEN:
//            case DIALOG_SCREEN:
//            case GROUP_SCREEN:
//                wait.until(ExpectedConditions.visibilityOf(CreateDialogChatPage.chatTitle));
//                System.out.println("dialog / group / venues screen");
//                return CreateDialogChatPage.chatTitle.getText().equals(expectedScreenTitle);
//            case CREATE_CHAT_SCREEN:
//            case CONTACTS_SCREEN:
//            case CONTACT_PROFILE_SCREEN:
//            case SETTINGS_SCREEN:
//            case EDIT_PROFILE_SCREEN:
//                wait.until(ExpectedConditions.visibilityOf(actionBar));
//                System.out.println("create chat / contact / settings / edit");
//                return navTitle.getText().equals(expectedScreenTitle);
//            default:
//                return false;
//        }
//    }

    /**
     * Back to the previous screen
     */
    public boolean goBack() {
        if (actionBar.isDisplayed()) {
            backBtn.click();
            return true;
        } else return false;
    }


    /**
     * Open contact tab
     */
    public boolean activateContactListTab(int index) {
        waitForElement(actionTabText);
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



}
