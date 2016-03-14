package com.radius.func;

import com.radius.data.Accounts;
import com.radius.data.Chats;
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

    public void openEditMainProfileScreen() {
        wait.until(ExpectedConditions.visibilityOf(Accounts.myMainAvatar));
        if (Accounts.myMainName.isEnabled()) {
            Accounts.mainProfileEditBtn.click();
        } else {
            System.out.println("user settings disabled");
        }
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

    public boolean deleteProfileAvatar() {
        wait.until(ExpectedConditions.visibilityOf(Accounts.avatar_deleteBtn));
        if (Accounts.avatar_deleteBtn.isEnabled()) {
            Accounts.avatar_deleteBtn.click();
            Accounts.update_profileBtn.click();
        }
        return true;
    }

    public boolean chooseAvatarFromGallery(int album, int imgIndex) {
        if (!Chats.list.isEmpty()) {
            Chats.list.get(album).click();
            Chats.list.get(imgIndex).click();
            Chats.gallerySubmit.click();
            Accounts.submitAvatarAction.click();
        }
        return true;
    }

    public boolean addProfileAvatar(int album, int img_index) {
        wait.until(ExpectedConditions.visibilityOf(Accounts.avatar_imageBtn));
        if (!Accounts.avatar_deleteBtn.isEnabled()) {
            Accounts.avatar_imageBtn.click();
            chooseAvatarFromGallery(album, img_index);
            Accounts.update_profileBtn.click();
        }
        return true;
    }
}
