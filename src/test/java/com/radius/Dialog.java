package com.radius;

import com.radius.config.Driver;
import com.radius.config.Helpers;
import com.radius.data.ContactsDataProvider;
import com.radius.func.DialogAndChats;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;

import static com.radius.data.ScreenTitles.*;
import static com.radius.data.Tabs.*;
import static com.radius.data.MenuItems.*;
import static org.testng.Assert.assertTrue;


/**
 * Created by kdl on 03.02.16.
 */
public class Dialog {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
    DialogAndChats chatsTester;
    Helpers helper;

    @BeforeClass
    public void dialog() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndChats(driver, wait, touchScreen);
        helper = new Helpers(driver, wait,touchScreen);
    }


    @Description("Check disable button submit when nobody marked for chat")
    @Test (groups = {"checkDisableSubmitButton"} )
    public void checkDisableChatBtn() throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(helper.checkScreenTitle(CHATS_SCREEN, "Чаты"));
        assertTrue(helper.createChat());
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(chatsTester.DisableCreateChatBtn());
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));

    }

    @Description("Check creating dialog chat using create_chat button from Dialog tab")
    @Test (groups = {"createDialogMainContactFromDialogTab"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromDialogTab(String mainContact) throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(helper.checkScreenTitle(CHATS_SCREEN, "Чаты"));
        helper.createChat();
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(helper.checkContactPresence(mainContact));
        assertTrue(helper.tapOnContact(mainContact));
        helper.submitCreateChat();
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, mainContact));
    }

    @Description("Check creating dialog chat using create_chat button from Group tab")
    @Test (groups = {"createDialogMainContactFromGroupTab"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromGroupTab(String mainContact1) throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(GROUP_TAB));
        helper.createChat();
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(helper.checkContactPresence(mainContact1));
        helper.tapOnContact(mainContact1);
        helper.submitCreateChat();
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Check opening dialog chat by name from Group chat")
    @Test (groups = {"createDialogMainContactFromGroupChat"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact2")
    public void createDialogFromGroupChat(String mainContact2) throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(GROUP_TAB));
        chatsTester.openChatByIndex(0);
        assertTrue(helper.checkScreenTitle(GROUP_SCREEN, "Групповой Чат"));
        assertTrue(chatsTester.openChatDialogByUsername(mainContact2));
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, mainContact2));
    }

    @Description("Create chat Dialog from main user profile")
    @Test (groups = {"createDialogFromMainUserProfile"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact2")
    public void createChatFromContactList(String mainContact2) throws InterruptedException {
        assertTrue(helper.openMenuItem(CONTACTS));
//        chatsTester.tapMenuIcon();
//        helper.openMenuItemByText("Контакты");
        assertTrue(helper.checkScreenTitle(CONTACTS_SCREEN, "Контакты"));
        chatsTester.openUserProfileFromContactList(mainContact2);
        assertTrue(helper.checkScreenTitle(CONTACT_PROFILE_SCREEN, mainContact2));
        chatsTester.createChatFromUserProfile();
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, mainContact2));
    }

    @Description("Check the separation of Contacts by tabs")
    @Test (groups = {"checkContactsSeparation"})
    public void checkContactsChatStructure() throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(helper.checkScreenTitle(CHATS_SCREEN, "Чаты"));
        helper.createChat();
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(helper.openActionTab(MAIN_CONTACTS_TAB));
        helper.checkContactListTabs("main_profile");
        assertTrue(helper.openActionTab(PUBLIC_CONTACTS_TAB));
        helper.checkContactListTabs("public_profile");
    }

    @AfterGroups(groups = {"checkDisableSubmitButton", "createDialogMainContactFromDialogTab", "createDialogMainContactFromGroupTab", "checkContactsSeparation"})
    public void navigateBack() {
        helper.goBack();
    }

    @AfterGroups(groups = {"createDialogFromMainUserProfile", "createDialogMainContactFromGroupChat"})
    public void navigateDualBack() {
        helper.goBack();
        helper.goBack();
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }

}
