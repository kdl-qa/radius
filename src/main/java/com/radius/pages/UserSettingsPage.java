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

    /**
     * logout button
     */
    @FindBy(id = "exit_button")
    public static WebElement logoutBtn;

    /*----------------------------- Main Profile -------------------------------------------------*/

    /**
     * main profile avatar
     */
    @FindBy(id = "image_view_avatar_main")
    public static WebElement myMainAvatar;

    /**
     * main profile username
     */
    @FindBy(id = "text_view_main_name")
    public static WebElement myMainName;

    /**
     * main edit profile button
     */
    @FindBy(id = "main_edit")
    public static WebElement mainProfileEditBtn;

    /*------------------------------ Public Profile -----------------------------------------------*/

    /**
     * public profile avatar
     */
    @FindBy(id = "image_view_avatar_public")
    public static WebElement myPublicAvatar;

    /**
     * public profile username
     */
    @FindBy(id = "text_view_public_name")
    public static WebElement myPublicName;

    /**
     * public edit profile button
     */
    @FindBy(id = "public_edit")
    public static WebElement publicProfileEditBtn;

    /*---------------------------------- Edit Profile -------------------------------------------*/

    /**
     * image gallery picker
     */
    @FindBy(id = "avatar_picker")
    public static WebElement avatar_imageBtn;

    /**
     * camera picker
     */
    @FindBy(id = "avatar_camera")
    public static WebElement avatar_cameraBtn;

    /**
     * delete profile avatar button
     */
    @FindBy(id = "delete_avatar")
    public static WebElement avatar_deleteBtn;

    /**
     * edit username field
     */
    @FindBy(id = "username_text_with_edit")
    public static WebElement edit_usernameInput;

    /**
     * update profile button
     */
    @FindBy(id = "profile_update_fab")
    public static WebElement update_profileBtn;

    /*--------------------------------- Image & Gallery -----------------------------------------*/

    /**
     * submit new avatar button
     */
    @FindBy(id = "action_back_to_list")
    public static WebElement submitAvatarAction;


    /**
     * Constructor
     */
    public UserSettingsPage(AndroidDriver driver, TouchActions touchScreen) {
        super(driver, touchScreen);
    }

    /**
     * Logout from App
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
            update_profileBtn.click();
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
        if (avatar_deleteBtn.isEnabled()) {
            avatar_deleteBtn.click();
            update_profileBtn.click();
        }
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
}
