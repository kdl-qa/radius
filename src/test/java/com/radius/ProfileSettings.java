package com.radius;

import com.radius.config.Driver;
import com.radius.config.Helpers;
import com.radius.data.UserDataProvider;
import com.radius.func.UserSettings;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;

import static com.radius.data.MenuItems.*;
import static com.radius.data.ScreenTitles.*;
import static org.testng.Assert.assertTrue;

/**
 * Created by kdl on 27.02.16.
 */
public class ProfileSettings {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
    Helpers helper;
    UserSettings tester;

    @BeforeClass
    public void settings() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        helper = new Helpers(driver, wait, touchScreen);
        tester = new UserSettings(driver, wait, touchScreen);
    }


    @Description("Check edit main profile username")
    @Test(groups = {"editMainUsername"}, dataProviderClass = UserDataProvider.class, dataProvider = "getEditMainName")
    public void editMainUsername(String editMainUsername) throws InterruptedException {
        assertTrue(helper.openMenuItem(SETTINGS));
        helper.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        tester.openEditMainProfileScreen();
        helper.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
        assertTrue(tester.editMainUsername(editMainUsername));
        helper.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        assertTrue(tester.checkEditedAccountMainUsername(editMainUsername));
    }

    @Description("Delete main profile avatar. Save profile without avatar")
    @Test(groups = {"deleteMainUserAvatar"})
    public void deleteMainAvatar() {
        assertTrue(helper.openMenuItem(SETTINGS));
        helper.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        tester.openEditMainProfileScreen();
        helper.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
//        tester.addProfileAvatar(1, 0)
        tester.deleteProfileAvatar();
        helper.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
    }


    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}
