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
import static com.radius.helpers.ScreenTitles.DIALOG_SCREEN;
import static com.radius.helpers.AppTabs.DIALOG_TAB;
import static org.testng.Assert.assertTrue;

/**
 * Created by kdl on 23.02.16.
 */
public class Messaging {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
    DialogAndGroupPage chatsTester;
    MobilePage mobileTester;
//    Helpers helper;

    @BeforeClass
    public void messaging() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndGroupPage(driver, wait, touchScreen);
        mobileTester = new MobilePage(driver, wait, touchScreen);
//        helper = new Helpers(driver, wait,touchScreen);
    }

    @Description("Send to dialog Geo message")
    @Test (groups = {"chatDialogSendGeoMessage"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void dialogSendGeoMsg(String username) {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
        chatsTester.openDialogByName(username);
        mobileTester.checkScreenTitle(DIALOG_SCREEN, username);
        assertTrue(chatsTester.sendDialogGeo());
    }

    @Description("Send to dialog Image messages")
    @Test (groups = {"chatDialogSendImageMessage"},  dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void dialogSendImageMsg(String username) {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
//        assertTrue(chatsTester.openChatByIndex(1));
        chatsTester.openDialogByName(username);
        assertTrue(chatsTester.sendDialogImage(3, 1));
    }

    @Description("Send to Dialog Text message")
    @Test(groups = {"checkDialogSendTextMessage"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void dialogSendTextMsg(String username) {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
        chatsTester.openDialogByName(username);
        assertTrue(chatsTester.sendDialogMsg());
    }

    @Description("Check Dialog message sent status")
    @Test(groups = {"checkDialogMainMessageSentStatus"}, dataProviderClass = ContactsDataProvider.class, dataProvider = "getMainContact1")
    public void checkDialogSendMsgStatus(String username) throws InterruptedException {
        mobileTester.openMenuItem(CHATS);
        mobileTester.openActionTab(DIALOG_TAB);
        chatsTester.openDialogByName(username);
        chatsTester.sendDialogMsg();
        assertTrue(chatsTester.checkSentMsgStatus());
    }

    @AfterGroups(groups = {"chatDialogSendGeoMessage", "chatDialogSendImageMessage", "checkDialogSendTextMessage", "checkDialogMainMessageSentStatus"})
    public void navigateBack() {
        mobileTester.goBack();
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}

