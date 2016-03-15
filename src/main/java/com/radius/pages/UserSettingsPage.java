package com.radius.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kdl on 25.02.16.
 */
public class UserSettingsPage extends MobilePage {

     /*================================== Common ================================================*/

    //todo: xpath Main and public blocks (class = android.widget.TextView)

    @FindBy(id = "exit_button")
    public WebElement logoutBtn;

    /*----------------------------- Main Profile -------------------------------------------------*/

    @FindBy(id = "image_view_avatar_main")
    public WebElement myMainAvatar;

    @FindBy(id = "text_view_main_name")
    public WebElement myMainName;

    @FindBy(id = "main_edit")
    public WebElement mainProfileEditBtn;

    /*------------------------------ Public Profile -----------------------------------------------*/

    @FindBy(id = "image_view_avatar_public")
    public WebElement myPublicAvatar;

    @FindBy(id = "text_view_public_name")
    public WebElement myPublicName;

    @FindBy(id = "public_edit")
    public WebElement publicProfileEditBtn;

    @FindBy(id = "create_public_profile_button")
    public WebElement createPublicProfileBtn;


    /*---------------------------------- Edit Profile -------------------------------------------*/

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

    /*--------------------------------- Image & Gallery -----------------------------------------*/

    @FindBy(id = "action_back_to_list")
    public static WebElement submitAvatarAction;


    /**
     * Constructor
     */
    public UserSettingsPage(AndroidDriver driver, TouchActions touchScreen) {
        super(driver, touchScreen);
    }

    /**
     * Logout from messenger App
     */
    public void logout() {
        logoutBtn.click();
    }


    public void submitEditProfile() {
        update_profileBtn.click();
    }

    public void openEditMainProfileScreen() {
        try {
            waitForElement(myMainAvatar);
            if (myMainName.isEnabled()) mainProfileEditBtn.click();
            else System.out.println("user settings disabled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit main profile
     */
    public boolean editMainUsername(String updateUsername) {
        waitForElement(edit_usernameInput);
        if (edit_usernameInput.isEnabled()) {
            edit_usernameInput.clear();
            edit_usernameInput.sendKeys(updateUsername);
        }
        return true;
    }

    /**
     * Check Main username
     */
    public boolean checkEditedMainUsername(String username) {
        waitForElement(myMainName);
        if (myMainName.getText().equalsIgnoreCase(username)) {
            System.out.println("Username is changed!");
        }
        return true;
    }

    /**
     * Delete profile avatar
     */
    public boolean deleteProfileAvatar() {
        waitForElement(avatar_deleteBtn);
        if (avatar_deleteBtn.isEnabled()) avatar_deleteBtn.click();
        return true;
    }

    /**
     * Update avatar from gallery
     */
    public boolean chooseAvatarFromGallery(int album, int imgIndex) {
        if (!imagesList.isEmpty()) {
            imagesList.get(album).click();
            imagesList.get(imgIndex).click();
            gallerySubmit.click();
            submitAvatarAction.click();
        }
        return true;
    }

    /**
     * Change profile avatar
     */
    public boolean addProfileAvatar(int album, int img_index) {
        waitForElement(avatar_imageBtn);
        if (!avatar_deleteBtn.isEnabled()) {
            avatar_imageBtn.click();
            chooseAvatarFromGallery(album, img_index);
        }
        return true;
    }

    /**
    * Update profile with empty avatar
     */
    public void updateProfileAvatarEmpty() {
        waitForElement(avatar_deleteBtn);
        if (avatar_deleteBtn.isEnabled()) {
            avatar_deleteBtn.click();
        }
    }

    /**
     * Check public profile exist
     */
    public void createPublicProfile() {

    }

    /**
    * Check public profile exist
    */
    public void checkPublicProfileNotExist() {
        waitForElement(createPublicProfileBtn);
        if (createPublicProfileBtn.isDisplayed()) {
            System.out.println("You don't have public profile");
        }
    }

    public void openEditPublicProfileScreen() {
        try {
            waitForElement(myPublicAvatar);
            if (myPublicName.isEnabled()) publicProfileEditBtn.click();
            else System.out.println("user settings disabled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit Public profile
     */
    public boolean editPublicUsername(String updateUsername) {
        waitForElement(edit_usernameInput);
        if (edit_usernameInput.isEnabled()) {
            edit_usernameInput.clear();
            edit_usernameInput.sendKeys(updateUsername);
        }
        return true;
    }

    /**
     * Check Public username
     */
    public boolean checkEditedPublicUsername(String username) {
        waitForElement(myPublicName);
        if (myPublicName.getText().equalsIgnoreCase(username)) {
            System.out.println("Public username is changed!");
        }
        return true;
    }


}
