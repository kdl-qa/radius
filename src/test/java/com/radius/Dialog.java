package com.radius;

import com.radius.data_providers.ContactsDataProvider;
import com.radius.drivers.Driver;
import com.radius.pages.ContactsPage;
import com.radius.pages.DialogAndGroupPage;
import com.radius.pages.MobilePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import static com.radius.helpers.AppTabs.*;
import static com.radius.helpers.MenuItems.CHATS;
import static com.radius.helpers.MenuItems.CONTACTS;
import static com.radius.helpers.ScreenTitles.*;
import static org.testng.Assert.assertTrue;


/**
 * Created by kdl on 03.02.16.
 */
public class Dialog {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
    DialogAndGroupPage chatsTester;
    MobilePage mobileTester;

    @Title("This is - create Dialog suite")
    @BeforeClass
    public void dialog() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 20);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndGroupPage(driver, touchScreen);
    }


    @Description("Check disable button submit when nobody marked for chat")
    @Test (groups = {"checkDisableSubmitButton"} )
    public void checkDisableChatBtn() {
        chatsTester.openMenuItem(CHATS);
        chatsTester.openActionTab(DIALOG_TAB);
//        chatsTester.checkScreenTitle(CHATS_SCREEN, "Чаты");
        chatsTester.createChat();
//        chatsTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат");
        chatsTester.checkDisableCreateChatBtn();
        assertTrue(chatsTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));

    }

    @Description("Check creating dialog chat using create_chat button from Dialog tab")
    @Test (groups = {"createDialogMainContactFromDialogTab"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromDialogTab(String mainContact) {
        ContactsPage contacts = new ContactsPage(driver, touchScreen);
        chatsTester.openMenuItem(CHATS);
        chatsTester.openActionTab(DIALOG_TAB);
        chatsTester.checkScreenTitle(CHATS_SCREEN, "Чаты");
        chatsTester.createChat();
        chatsTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат");
        chatsTester.checkContactPresence(mainContact);
        contacts.tapOnContact(mainContact);
        chatsTester.submitCreateChat();
        assertTrue(chatsTester.checkScreenTitle(DIALOG_SCREEN, mainContact));
    }

    @Description("Check creating dialog chat using create_chat button from Group tab")
    @Test (groups = {"createDialogMainContactFromGroupTab"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromGroupTab(String mainContact1) {
        ContactsPage contacts = new ContactsPage(driver, touchScreen);
        chatsTester.openMenuItem(CHATS);
        chatsTester.openActionTab(GROUP_TAB);
        chatsTester.createChat();
//        assertTrue(chatsTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        chatsTester.checkContactPresence(mainContact1);
        contacts.tapOnContact(mainContact1);
        chatsTester.submitCreateChat();
        assertTrue(chatsTester.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Check opening dialog chat by name from Group chat")
    @Test (groups = {"createDialogMainContactFromGroupChat"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createDialogFromGroupChat(String mainContact1) {
        chatsTester.openMenuItem(CHATS);
        chatsTester.openActionTab(GROUP_TAB);
        chatsTester.openChatByIndex(0);
        chatsTester.checkScreenTitle(GROUP_SCREEN, "Групповой Чат");
        chatsTester.openChatDialogByUsername(mainContact1);
        assertTrue(chatsTester.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Create chat Dialog from main user profile")
    @Test (groups = {"createDialogFromMainUserProfile"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromContactList(String mainContact1) {
        chatsTester.openMenuItem(CONTACTS);
        chatsTester.checkScreenTitle(CONTACTS_SCREEN, "Контакты");
        chatsTester.openUserProfileFromContactList(mainContact1);
//        chatsTester.checkScreenTitle(CONTACT_PROFILE_SCREEN, mainContact1);
        chatsTester.createChatFromUserProfile();
        assertTrue(chatsTester.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Check the separation of ContactsPage by tabs")
    @Test (groups = {"checkContactsSeparation"})
    public void checkContactsChatStructure() {
        ContactsPage contacts = new ContactsPage(driver, touchScreen);
        chatsTester.openMenuItem(CHATS);
        chatsTester.openActionTab(DIALOG_TAB);
        chatsTester.checkScreenTitle(CHATS_SCREEN, "Чаты");
        chatsTester.createChat();
        chatsTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат");
        chatsTester.openActionTab(MAIN_CONTACTS_TAB);
        assertTrue(contacts.checkContactListTabs("main_profile"));
        chatsTester.openActionTab(PUBLIC_CONTACTS_TAB);
        assertTrue(contacts.checkContactListTabs("public_profile_empty"));
    }

    @AfterGroups(groups = {"checkDisableSubmitButton", "createDialogMainContactFromDialogTab", "createDialogMainContactFromGroupTab", "checkContactsSeparation"})
    public void navigateBack() {
        chatsTester.goBack();
    }

    @AfterGroups(groups = {"createDialogFromMainUserProfile", "createDialogMainContactFromGroupChat"})
    public void navigateDualBack() {
        chatsTester.goBack();
        chatsTester.goBack();
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }

}
