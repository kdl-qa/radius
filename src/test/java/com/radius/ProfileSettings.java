package com.radius;

import com.radius.data_providers.UserDataProvider;
import com.radius.drivers.Driver;
import com.radius.helpers.AppData;
import com.radius.pages.UserSettingsPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import static com.radius.helpers.MenuItems.CHATS;
import static com.radius.helpers.MenuItems.SETTINGS;
import static com.radius.helpers.ScreenTitles.CREATE_PUBLIC_PROFILE_SCREEN;
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

    @Title("This is - Edit profile suite")
    @BeforeClass
    public void settings() {
        driver = Driver.initDriver();
        wait = new WebDriverWait(driver, 20);
        touchScreen = new TouchActions(driver);
        profiler = new UserSettingsPage(driver, touchScreen);
    }


    @Description("Check the Main profile username after edit")
    @Test(groups = {"editMainProfileUsername"}, dataProviderClass = UserDataProvider.class, dataProvider = "getEditMainName")
    public void editMainProfileUsername(String editMainUsername) {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.editProfileAccountUsername(editMainUsername);
        profiler.submitEditProfile();
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        assertTrue(profiler.checkEditedMainUsername(editMainUsername));
    }

    @Description("Add an avatar to the Main profile")
    @Test(groups = {"addMainUserAvatar"})
    public void uploadMainProfileAvatar() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.addProfileAvatar(1, 0);
        profiler.submitEditProfile();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle));
    }

    @Description("Save Main profile without user avatar")
    @Test(groups = {"saveMainProfileAvatarEmpty"})
    public void saveMainProfileAvatarEmpty() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.updateProfileAvatarEmpty();
        profiler.submitEditProfile();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle));
    }

    @Description("Delete Main profile avatar and save profile without avatar")
    @Test(groups = {"loadAndDeleteMainUserAvatar"})
    public void loadAndDeleteMainAvatar() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditMainProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.addProfileAvatar(1, 0);
        profiler.submitEditProfile();
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditMainProfileScreen();
        profiler.deleteProfileAvatar();
        profiler.submitEditProfile();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle));
    }

    @Description("Create a Public profile account without the avatar")
    @Test(groups = {"createPublicProfileAccount"}, dataProviderClass = UserDataProvider.class, dataProvider = "getPublicName")
    public void createPublicAccount(String addPublicUsername) {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.checkPublicProfileNotExist();
        profiler.checkScreenTitle(CREATE_PUBLIC_PROFILE_SCREEN, AppData.createPublicProfileScreenTitle);
        profiler.openCreatePublicProfileScreen();
        profiler.createPublicProfile(addPublicUsername);
        profiler.submitEditProfile();
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        assertTrue(profiler.checkEditedPublicUsername(addPublicUsername));
    }

    @Description("Create a Public profile account with the avatar")
    @Test(groups = {"createPublicProfileAccountWithAvatar"}, dataProviderClass = UserDataProvider.class, dataProvider = "getPublicName")
    public void createPublicAccountWithAvatar(String addPublicUsername) {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.checkPublicProfileNotExist();
        profiler.checkScreenTitle(CREATE_PUBLIC_PROFILE_SCREEN, AppData.createPublicProfileScreenTitle);
        profiler.openCreatePublicProfileScreen();
        profiler.createPublicProfile(addPublicUsername);
        profiler.addProfileAvatar(1, 0);
        profiler.submitEditProfile();
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        assertTrue(profiler.checkEditedPublicUsername(addPublicUsername));
    }

    @Description("Check the Public profile username after edit")
    @Test(groups = {"editPublicProfileUsername"}, dataProviderClass = UserDataProvider.class, dataProvider = "getEditPublicName")
    public void editPublicProfileUsername(String editPublicUsername) {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditPublicProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.editProfileAccountUsername(editPublicUsername);
        profiler.submitEditProfile();
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        assertTrue(profiler.checkEditedPublicUsername(editPublicUsername));
    }


    @Description("Add an avatar to the Public profile")
    @Test(groups = {"addPublicUserAvatar"})
    public void uploadPublicProfileAvatar() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditPublicProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.addProfileAvatar(0, 0);
        profiler.submitEditProfile();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle));
    }

    @Description("Save Public profile without user avatar")
    @Test(groups = {"savePublicProfileAvatarEmpty"})
    public void savePublicProfileAvatarEmpty() {
        profiler.openMenuItem(SETTINGS);
        profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle);
        profiler.openEditPublicProfileScreen();
        profiler.checkScreenTitle(EDIT_PROFILE_SCREEN, AppData.editProfileScreenTitle);
        profiler.updateProfileAvatarEmpty();
        profiler.submitEditProfile();
        assertTrue(profiler.checkScreenTitle(SETTINGS_SCREEN, AppData.settingsScreenTitle));
    }

    @Description("Log out from the Radius messenger")
    @Test(groups = {"savePublicProfileAvatarEmpty"})
    public void logoutMessengerApp() {
        profiler.openMenuItem(SETTINGS);
        profiler.tapLogoutButton();
        profiler.checkLogoutAlertText();
        profiler.logoutAlertDiscard();
        profiler.tapLogoutButton();
        profiler.logoutAlertSubmit();
    }



    @AfterGroups(groups = {"", "", "", ""})
    public void navigateBack() {
        profiler.openMenuItem(CHATS);
    }


    @AfterClass
    public void closeApp() {
        driver.quit();
    }
}
