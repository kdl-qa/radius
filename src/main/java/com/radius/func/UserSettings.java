package com.radius.func;

import com.radius.data.Accounts;
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
        Accounts.logoutBtn.click();
    }

    public void openEditMainProfileScreen() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Accounts.myMainAvatar));
//       SettingsData.logoutBtn.click();
        if (Accounts.myMainName.isEnabled()) {
            Accounts.mainProfileEditBtn.click();
        } else {
            System.out.println("user settings disabled");
        }
//        return true;
    }

    public boolean editMainUsername(String updateUsername) {
        wait.until(ExpectedConditions.visibilityOf(Accounts.edit_usernameInput));
        if (Accounts.edit_usernameInput.isEnabled()) {
            Accounts.edit_usernameInput.clear();
            Accounts.edit_usernameInput.sendKeys(updateUsername);
            Accounts.update_profileBtn.click();
        }
        return true;
    }

    public boolean checkEditedAccountMainUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(Accounts.myMainName));
        if (Accounts.myMainName.getText().equalsIgnoreCase(username)) {
            System.out.println("Username is changed!");
        }
        return true;
    }

}
