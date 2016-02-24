package com.radius.page_object.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by kdl on 17.02.16.
 */
public class Contacts {

    /*======================== List contacts ============================*/
    public static final String main_username = "Дмитрий Кравченко";
    public static final String public_username = "паблик дмитрий";


    @FindBy(id = "root_view")
    public static List<WebElement> contactsList;

    @FindBy(id = "text_view_contact_name")
    public static List<WebElement> contactsName;

    @FindBy(id = "room_picture")
    public static WebElement contactUserProfileAvatar;

    @FindBy(id = "start_chat")
    public static WebElement contact_createChatBtn;

    @FindBy(id = "block_user_button")
    public static WebElement contact_blockUserBtn;



}
