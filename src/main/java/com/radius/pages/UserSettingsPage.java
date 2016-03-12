package com.radius.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by kdl on 25.02.16.
 */
public class UserSettingsPage extends MobilePage {

     /*===================== Common =====================*/

    //todo: xpath Main and public blocks (class = android.widget.TextView)


    @FindBy(id = "exit_button")
    public static WebElement logoutBtn;

    /*===================== Main Profile =====================*/

    @FindBy(id = "image_view_avatar_main")
    public static WebElement myMainAvatar;

    @FindBy(id = "text_view_main_name")
    public static WebElement myMainName;

    @FindBy(id = "main_edit")
    public static WebElement mainProfileEditBtn;

    /*==================== Public Profile ====================*/

    @FindBy(id = "image_view_avatar_public")
    public static WebElement myPublicAvatar;

    @FindBy(id = "text_view_public_name")
    public static WebElement myPublicName;

    @FindBy(id = "public_edit")
    public static WebElement publicProfileEditBtn;

    /*====================== Edit Profile =========================*/

    @FindBy(id = "avatar_picker")
    public static WebElement avatar_imageBtn;

    @FindBy(id = "avatar_camera")
    public static WebElement avatar_cameraBtn;

    @FindBy(id = "delete_avatar")
    public static WebElement avatar_deleteBtn;

    @FindBy(id = "username_text_with_edit")
    public static WebElement edit_usernameInput;

    @FindBy(id = "profile_update_fab")
    public static WebElement update_profileBtn;


    /**
    * Constructor
    */
    public UserSettingsPage(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        super(driver, wait, touchScreen);
    }

    /**
     * Logout from App
     */
    public void logout() {
        logoutBtn.click();
    }

    public void openEditMainProfileScreen() {
        try {
            wait.until(ExpectedConditions.visibilityOf(myMainAvatar));
            if (myMainName.isEnabled()) mainProfileEditBtn.click();
            else System.out.println("user settings disabled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit main profile
     * @param
     */
    public boolean editMainUsername(String updateUsername) {
        wait.until(ExpectedConditions.visibilityOf(edit_usernameInput));
        if (edit_usernameInput.isEnabled()) {
            edit_usernameInput.clear();
            edit_usernameInput.sendKeys(updateUsername);
            update_profileBtn.click();
        }
        return true;
    }

    public boolean checkEditedAccountMainUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(myMainName));
        if (myMainName.getText().equalsIgnoreCase(username)) {
            System.out.println("Username is changed!");
        }
        return true;
    }

}
