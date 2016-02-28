package com.radius.func;

import com.radius.data.Account;
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
        Account.logoutBtn.click();
    }

    public void openEditMainProfileScreen() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Account.myMainAvatar));
//       SettingsData.logoutBtn.click();
        if (Account.myMainName.isEnabled()) {
            System.out.println("user settings enabled");
            Account.mainProfileEditBtn.click();
        }else{
            System.out.println("user settings disabled");
        }
//        return true;
    }

    public boolean editMainUsername(String updateUsername) {
        wait.until(ExpectedConditions.visibilityOf(Account.edit_usernameInput));
        if (Account.edit_usernameInput.isEnabled()) {
            Account.edit_usernameInput.clear();
            Account.edit_usernameInput.sendKeys(updateUsername);
            Account.update_profileBtn.click();
        }
        return true;
    }


}
