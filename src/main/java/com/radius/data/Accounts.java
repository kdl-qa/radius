package com.radius.data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by kdl on 28.02.16.
 */
public class Accounts {
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

}
