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

import static com.radius.data.MenuItems.*;
import static com.radius.data.ScreenTitles.*;
import static com.radius.data.Tabs.*;
import static org.testng.Assert.assertTrue;

/**
 * Created by kdl on 23.02.16.
 */
public class Messaging {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
    DialogAndChats chatsTester;
    Helpers helper;

    @BeforeClass
    public void messaging() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndChats(driver, wait, touchScreen);
        helper = new Helpers(driver, wait,touchScreen);
    }

    @Description("Send in dialog Geo message")
    @Test (groups = {"chatDialogSendGeoMessage"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void dialogSendGeoMsg(String username) throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(chatsTester.openDialogByName(username));
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, username));
        chatsTester.sendDialogGeo();
    }

    @Description("Send in dialog Image messages")
    @Test (groups = {"chatDialogSendImageMessage"},  dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void dialogSendImageMsg(String username) throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(DIALOG_TAB));
//        assertTrue(chatsTester.openChatByIndex(1));
        assertTrue(chatsTester.openDialogByName(username));
        assertTrue(chatsTester.sendDialogImage(3, 2));
    }

    @Description("Check Dialog message sent status")
    @Test(groups = {"checkDialogMainMessageSentStatus"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void checkDialogSendMsgStatus(String username) throws InterruptedException {
        assertTrue(helper.openMenuItem(CHATS));
        assertTrue(helper.openActionTab(DIALOG_TAB));
        assertTrue(chatsTester.openDialogByName(username));
        chatsTester.sendDialogMsg();
        assertTrue(chatsTester.checkSentMsgStatus());
    }

    @AfterGroups(groups = {"chatDialogSendGeoMessage", "chatDialogSendImageMessage", "checkDialogMainMessageSentStatus"})
    public void navigateBack() {
        helper.goBack();
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}

