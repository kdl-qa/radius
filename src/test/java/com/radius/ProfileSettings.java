package com.radius;

import com.radius.data_providers.UserDataProvider;
import com.radius.drivers.Driver;
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
    UserSettingsPage profiler;


    @BeforeClass
    public void settings() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 15);
        touchScreen = new TouchActions(driver);
        profiler = new UserSettingsPage(driver, touchScreen);
    }


    @Description("Check edit main profile username")
    @Test(groups = {"editMainUsername"}, dataProviderClass = UserDataProvider.class, dataProvider = "getEditMainName")
    public void editMainUsername(String editMainUsername) throws InterruptedException {

        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
        profiler.editMainUsername(editMainUsername);
        profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        assertTrue(profiler.checkEditedMainUsername(editMainUsername));

    }

    @Description("Add the avatar to main profile  Save profile without avatar")
    @Test(groups = {"addMainUserAvatar"})
    public void uploadMainAvatar() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
        profiler.addProfileAvatar(1, 0);
        profiler.submitEditProfile();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки"));
    }

    @Description("Save profile without user avatar")
    @Test(groups = {"saveProfileAvatarEmpty"})
    public void saveMainProfileAvatarEmpty() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
        profiler.addProfileAvatar(1, 0);
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки"));
    }

    @Description("Delete main profile avatar. Save profile without avatar")
    @Test(groups = {"deleteMainUserAvatar"})
    public void deleteMainAvatar() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки");
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, "Редактирование профиля");
//        profiler.addProfileAvatar(1, 0)
//        profiler.deleteProfileAvatar();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, "Настройки"));
    }


    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}
