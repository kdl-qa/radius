package com.radius;

import com.radius.drivers.Driver;
import com.radius.pages.MobilePage;
import com.radius.data_providers.ContactsDataProvider;
import com.radius.pages.DialogAndGroupPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;

import static com.radius.helpers.MenuItems.CHATS;
import static com.radius.helpers.MenuItems.CONTACTS;
import static com.radius.helpers.ScreenTitles.*;
import static com.radius.helpers.AppTabs.*;
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
//    Helpers helper;

    @BeforeClass
    public void dialog() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndGroupPage(driver, wait, touchScreen);
//        helper = new Helpers(driver, wait, touchScreen);
        mobileTester = new MobilePage(driver, wait, touchScreen);
    }


    @Description("Check disable button submit when nobody marked for chat")
    @Test (groups = {"checkDisableSubmitButton"} )
    public void checkDisableChatBtn() {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
//        mobileTester.checkScreenTitle(CHATS_SCREEN, "Чаты");
        mobileTester.createChat();
//        mobileTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат");
        chatsTester.checkDisableCreateChatBtn();
        assertTrue(mobileTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));

    }

    @Description("Check creating dialog chat using create_chat button from Dialog tab")
    @Test (groups = {"createDialogMainContactFromDialogTab"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromDialogTab(String mainContact) {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
        mobileTester.checkScreenTitle(CHATS_SCREEN, "Чаты");
        mobileTester.createChat();
        mobileTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат");
        mobileTester.checkContactPresence(mainContact);
        mobileTester.tapOnContact(mainContact);
        mobileTester.submitCreateChat();
        assertTrue(mobileTester.checkScreenTitle(DIALOG_SCREEN, mainContact));
    }

    @Description("Check creating dialog chat using create_chat button from Group tab")
    @Test (groups = {"createDialogMainContactFromGroupTab"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromGroupTab(String mainContact1) {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(GROUP_TAB);
        mobileTester.createChat();
//        assertTrue(mobileTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        mobileTester.checkContactPresence(mainContact1);
        mobileTester.tapOnContact(mainContact1);
        mobileTester.submitCreateChat();
        assertTrue(mobileTester.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Check opening dialog chat by name from Group chat")
    @Test (groups = {"createDialogMainContactFromGroupChat"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createDialogFromGroupChat(String mainContact1) {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(GROUP_TAB);
        chatsTester.openChatByIndex(0);
//        mobileTester.checkScreenTitle(GROUP_SCREEN, "Групповой Чат");
        chatsTester.openChatDialogByUsername(mainContact1);
        assertTrue(mobileTester.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Create chat Dialog from main user profile")
    @Test (groups = {"createDialogFromMainUserProfile"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void createChatFromContactList(String mainContact1) {
        mobileTester.openMenuItem(CONTACTS);
        mobileTester.checkScreenTitle(CONTACTS_SCREEN, "Контакты");
        chatsTester.openUserProfileFromContactList(mainContact1);
//        mobileTester.checkScreenTitle(CONTACT_PROFILE_SCREEN, mainContact1);
        chatsTester.createChatFromUserProfile();
        assertTrue(mobileTester.checkScreenTitle(DIALOG_SCREEN, mainContact1));
    }

    @Description("Check the separation of ContactsPage by tabs")
    @Test (groups = {"checkContactsSeparation"})
    public void checkContactsChatStructure() {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
        mobileTester.checkScreenTitle(CHATS_SCREEN, "Чаты");
        mobileTester.createChat();
        mobileTester.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат");
        mobileTester.openActionTab(MAIN_CONTACTS_TAB);
        assertTrue(mobileTester.checkContactListTabs("main_profile"));
        mobileTester.openActionTab(PUBLIC_CONTACTS_TAB);
        assertTrue(mobileTester.checkContactListTabs("no_public_profile"));
    }

    @AfterGroups(groups = {"checkDisableSubmitButton", "createDialogMainContactFromDialogTab", "createDialogMainContactFromGroupTab", "checkContactsSeparation"})
    public void navigateBack() {
        mobileTester.goBack();
    }

    @AfterGroups(groups = {"createDialogFromMainUserProfile", "createDialogMainContactFromGroupChat"})
    public void navigateDualBack() {
        mobileTester.goBack();
        mobileTester.goBack();
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }

}
