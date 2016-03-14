package com.radius;

import com.radius.drivers.Driver;
import com.radius.pages.MobilePage;
import com.radius.data_providers.UserDataProvider;
import com.radius.pages.UserSettingsPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;

import static com.radius.helpers.MenuItems.SETTINGS;
import static com.radius.helpers.ScreenTitles.EDIT_PROFILE_SCREEN;
import static com.radius.helpers.ScreenTitles.SETTINGS_SCREEN;
import static org.testng.Assert.assertTrue;

/**
 * Created by kdl on 27.02.16.
 */
public class ProfileSettings {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    public static TouchActions touchScreen;
//    Helpers helper;
    UserSettingsPage tester;
    MobilePage mobileTester;

    @BeforeClass
    public void settings() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        mobileTester = new MobilePage(driver, wait, touchScreen);
        tester = new UserSettingsPage(driver, wait, touchScreen);
    }


    @Description("Check edit main profile username")
    @Test(groups = {"editMainUsername"}, dataProviderClass = UserDataProvider.class, dataProvider = "getEditMainName")
    public void editMainUsername(String editMainUsername) throws InterruptedException {

        mobileTester.openMenuItem(SETTINGS);
        mobileTester.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        assertTrue(mobileTester.openMenuItem(SETTINGS));
        mobileTester.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        tester.openEditMainProfileScreen();
        mobileTester.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
        tester.editMainUsername(editMainUsername);
        mobileTester.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        assertTrue(tester.checkEditedAccountMainUsername(editMainUsername));

    }

    @Description("Delete main profile avatar. Save profile without avatar")
    @Test(groups = {"deleteMainUserAvatar"})
    public void deleteMainAvatar() {
        assertTrue(mobileTester.openMenuItem(SETTINGS));
        mobileTester.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        tester.openEditMainProfileScreen();
        mobileTester.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
//        tester.addProfileAvatar(1, 0)
//        tester.deleteProfileAvatar();
        mobileTester.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
    }


    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}
