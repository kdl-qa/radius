package com.radius;

import com.radius.config.Driver;
import com.radius.config.Helpers;
import com.radius.func.DialogAndChats;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;

import static com.radius.data.ScreenTitles.DIALOG_SCREEN;
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
        helper = new Helpers(driver, wait);
    }

    @Test (groups = {"messaging"})
    public void dialogSendGeoMsg() throws InterruptedException {
        assertTrue(chatsTester.openDialogByName("алексей берлин"));
        assertTrue(helper.checkScreenTitle(DIALOG_SCREEN, "Алексей Берлин"));
        assertTrue(chatsTester.sendDialogGeo());
        assertTrue(helper.goBack());
    }

    @Test (groups = {"messaging"})
    public void dialogSendImageMsg() throws InterruptedException {
        assertTrue(chatsTester.openChatByIndex(1));
        assertTrue(chatsTester.sendDialogImage(3, 2));
        assertTrue(helper.goBack());
    }

    @Description("Check Dialog message sent status")
    @Test(groups = {"checkDialogMainMessageSentStatus"})
    public void checkDialogSendMsgStatus() throws InterruptedException {
        assertTrue(chatsTester.openDialogByName("алексей берлин"));
        assertTrue(chatsTester.sendDialogMsg());
        assertTrue(chatsTester.checkSentMsgStatus());
        assertTrue(helper.goBack());
    }

    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}

