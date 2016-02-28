package com.radius.func;

import com.radius.data.Settings;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kdl on 25.02.16.
 */
public class UserSettings {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private TouchActions touchScreen;

    public UserSettings(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        this.driver = driver;
        this.wait = wait;
        this.touchScreen = touchScreen;
    }

    public void logout() {
        Settings.logoutBtn.click();
    }

    public boolean openEditMainProfileScreen() {
        wait.until(ExpectedConditions.visibilityOf(Settings.myMainAvatar));
        if (Settings.myMainName.isEnabled()) {
            Settings.mainProfileEditBtn.click();
        }
        return true;
    }

    public boolean editMainUsername(String updateUsername) {
//        wait.until(ExpectedConditions.visibilityOf(Settings.edit_usernameInput));
        if (Settings.edit_usernameInput.isEnabled()) {
            Settings.edit_usernameInput.clear();
            Settings.edit_usernameInput.sendKeys(updateUsername);
            Settings.update_profileBtn.click();
        }
        return true;
    }


}
