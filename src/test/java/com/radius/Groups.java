package com.radius;

import com.radius.drivers.Driver;
import com.radius.pages.DialogAndGroupPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by kdl on 16.02.16.
 */
public class Groups {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
    DialogAndGroupPage chatsTester;

    @BeforeClass
    public void group() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        chatsTester = new DialogAndGroupPage(driver, wait, touchScreen);
    }

    @Test (groups = {"createGroup"}, priority = 0, enabled = false)
    public void createGroupFromDialog() {
//    assertTrue(chatsTester.createChatFromDialogTab("алексей кононенко"));
    }

    @AfterTest
    public void closeApp() {
        driver.quit();
    }

}
