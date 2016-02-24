package com.radius;

import com.radius.config.Driver;
import com.radius.config.Helpers;
import com.radius.data.DialogAndChats;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.radius.page_object.android.ScreenTitles.*;
import static com.radius.page_object.android.Tabs.*;
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

    Dialog() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 5);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndChats(driver, wait, touchScreen);
        helper = new Helpers(driver, wait);
    }

//    @Test (groups = {"sideBarNavigation"}, priority = 0)
//    public void openMenu() throws InterruptedException {
//        assertTrue(chatsTester.tapMenuIcon());
//        assertTrue(chatsTester.tapSideBarMenuText("чаТы"));
//    }

   @Test (groups = {"createDialog"}, priority = 1)
    public void checkDisableChatBtn() throws InterruptedException {
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(helper.checkScreenTitle(CHATS_SCREEN, "Чаты"));
        assertTrue(helper.createChat());
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(chatsTester.DisableCreateChatBtn());
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(helper.goBack());
    }

    @Test (groups = {"createDialog"}, priority = 2)
    public void createChatFromDialogTab() throws InterruptedException {
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(helper.checkScreenTitle(CHATS_SCREEN, "Чаты"));
        assertTrue(helper.createChat());
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
//        chatsTester.createChatFromDialogTab("алексей берлин");
        assertTrue(helper.checkContactPresence("Алексей Берлин"));
        assertTrue(helper.tapOnContact("Алексей Берлин"));
        assertTrue(helper.submitCreateChat());
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, "Алексей Берлин"));
        assertTrue(helper.goBack());
    }

    @Test (groups = {"createDialog"}, priority = 3)
    public void createChatFromGroupTab() throws InterruptedException {
        assertTrue(helper.openActionTab(GROUP_TAB));
        helper.createChat();
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(helper.checkContactPresence("Алексей Берлин"));
        assertTrue(helper.tapOnContact("Алексей Берлин"));
        helper.submitCreateChat();
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, "Алексей Берлин"));
        assertTrue(helper.goBack());
    }

    @Test (groups = {"createDialog"}, priority = 4)
    public void createDialogFromGroupChat() throws InterruptedException {
        assertTrue(helper.openActionTab(GROUP_TAB));
        chatsTester.openChatByIndex(0);


    }

    @Test (groups = {"createDialog"}, priority = 5)
    public void createChatFromContactList() throws InterruptedException {
        chatsTester.tapMenuIcon();
        helper.openMenuItemByText("Контакты");
        assertTrue(helper.checkScreenTitle(CONTACTS_SCREEN, "Контакты"));
        chatsTester.openUserProfileFromContactList("сергей босовский");
        assertTrue(helper.checkScreenTitle(CONTACT_PROFILE_SCREEN, "сергей босовский"));
        chatsTester.createChatFromUserProfile();
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, "сергей босовский"));
//        assertTrue(helper.goBack());
    }

    @Test (groups = {"contacts"}, priority = 1 )
    public void checkContactsChatStructure() throws InterruptedException {
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(helper.checkScreenTitle(CHATS_SCREEN, "Чаты"));
        assertTrue(helper.createChat());
        assertTrue(helper.checkScreenTitle(CREATE_CHAT_SCREEN, "Создать Чат"));
        assertTrue(helper.openActionTab(MAIN_CONTACTS_TAB));
        assertTrue(helper.checkContactListTabs("main_profile"));
        assertTrue(helper.openActionTab(PUBLIC_CONTACTS_TAB));
        assertTrue(helper.checkContactListTabs("public_profile"));
        assertTrue(helper.goBack());
    }



    @AfterClass
    public void closeApp() {
        driver.quit();
    }

}
