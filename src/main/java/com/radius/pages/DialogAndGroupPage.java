package com.radius.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class DialogAndGroupPage extends MobilePage {

    /*===================================== Locators ===================================================*/

    @FindBy(name = "Чаты")
    public static WebElement chatsScreenTitle;

//    @FindBy(className = "android.support.v7.app.ActionBar$Tab")
//    public static List<WebElement> actionTabs;
//
//    @FindBy(id = "toolbar")
//    public static WebElement actionBar;


    @FindBy(id = "username_toolbar")
    public static WebElement chatTitle;

//    @FindBy(id = "text_area")
//    public static WebElement actionTabText;
//
//    @FindBy(id = "tab_avatar")
//    public static WebElement contactsTabAvatar;
//
//    @FindBy(className = "android.widget.TextView")
//    public static WebElement s_text;

//    @FindBy(xpath = "//android.widget.TextView[@index='1']")
//    public static WebElement navTitle;

    @FindBy(xpath = "//android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='0']")
    public static WebElement chatTitle1;

    @FindBy(id = "fab_contacts")
    public static WebElement createChatBtn;

//    @FindBy(className = "android.widget.ImageButton")
//    public static WebElement menuIcon, backBtn;

    @FindBy(id = "list_chats")
    public static WebElement chatsListView;

    @FindBy(id = "text_view_chat_name")
    public static List<WebElement> chatItemName;

    /*===========================Side Bar==========================*/
//    @FindBy(id = "design_navigation_view")
//    public static WebElement sideBar;
//
//    //    @FindBy(id = "design_menu_item_text")
//    @FindBy(className = "android.widget.CheckedTextView")
//    public static List<WebElement> sideBarMenuItems;

    /*===================================== Message Box ===================================================*/

    public static String simpleMsg = "abcdefghijklmnopqrstuvwxyz";
    public static String sentanceMsg = "Hi, nice to chat with you on Talker!";
    public static final String msgStatus0 = "Доставлено";
    public static final String msgStatus1 = "Прочитано";
    public static final String msgStatus2 = "Не доставлено";

    @FindBy(id = "send_message_box")
    public static WebElement msgBox;

    @FindBy(id = "edit_text_send_message")
    public static WebElement msgTextField;

    @FindBy(id = "send_location_checkbox")
    public static WebElement locationIcon;

    @FindBy(id = "attach_something")
    public static WebElement attachIcon;

    @FindBy(id = "button_send")
    public static WebElement sendBtn;

    @FindBy(className = "android.widget.LinearLayout")
    public static List<WebElement> attachList;

    /*===================================== Message List ===================================================*/

    @FindBy(id = "layout")
    public static List<WebElement> msgsLayout;

    @FindBy(id = "bubble")
    public static List<WebElement> bubbleMsgs;

    @FindBy(id = "text_view_message")
    public static List<WebElement> bubbleMsgsText;

    @FindBy(id = "text_view_date")
    public static List<WebElement> bubbleMsgsTime;

    @FindBy(id = "text_view_status")
    public static WebElement msgStatus;

    @FindBy(id = "user_name")
    public static List<WebElement> chatUsernames;

    @FindBy(id = "user_name")
    public static WebElement chat_user_name;

    @FindBy(id = "image_view_avatar")
    public static List<WebElement> chatAvatars;


    /*===================================== Attach List ===================================================*/

    @FindBy(id = "image")
    public static List<WebElement> imagesList;

    @FindBy(id = "fab_done")
    public static WebElement gallerySubmit, previewSubmit, submitCrtBtn;

    @FindBy(id = "delete")
    public static WebElement previewDelImage;

    /*===================================== ContactsPage ===================================================*/

//    public static final String marked_contact = "selected_indicator";
//
//    @FindBy(id = "selected_indicator")
//    public static WebElement contactMarked;
//
//    @FindBy(id = "list_contacts")
//    public static WebElement contactList;
//
//    @FindBy(id = "public_user_empty")
//    public static WebElement publicUserEmpty;
//
//    @FindBy(id = "root_view")
//    public static List<WebElement> contactItems;
//
//    @FindBy(id = "info_open_chat")
//    public static WebElement contactPublic_openChat;
//
//    @FindBy(id = "create_public_profile_button")
//    public static WebElement createPublicProfileBtn;


    /*===================================== Methods ===================================================*/

    /**
     * Constructor
     */
    public DialogAndGroupPage(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        super(driver, wait, touchScreen);
        PageFactory.initElements(driver, this);
    }


    public boolean tapActionBarTab(int index) {
        actionTabs.get(index).click();
        return true;
    }

    public boolean tapMenuIcon() {
        if (actionBar.isDisplayed() & chatsScreenTitle.isDisplayed()) {
            menuIcon.click();
            return true;
        } else {
            System.out.println("Tap on the Menu button is failed!!!");
            return false;
        }
    }

    public boolean tapSideBarMenuIndex(int index) {
        if (!sideBar.isDisplayed()) {
            return false;
        } else {
            sideBarMenuItems.get(index).click();
            return true;
        }
    }

    public boolean tapSideBarMenuText(String text) {
        tapMenuIcon();
        if (sideBar.isDisplayed()) {
            for (WebElement item : sideBarMenuItems)
                if (item.getText().equalsIgnoreCase(text)) {
                    item.click();
                    break;
                } else continue;

        } else
            touchScreen.flick(sideBar, -10, 0, 80).perform();
        return true;
    }


    public boolean openChatByIndex(int index) {

        wait.until(ExpectedConditions.visibilityOf(chatsListView));
        if (actionBar.isDisplayed() & chatsScreenTitle.isDisplayed()) {
            if (chatItemName.isEmpty()) {
                System.out.println("You don't have any chats");
            } else chatItemName.get(index).click();
            return true;
        } else return false;
    }

    public boolean openDialogByName(String name) {
        wait.until(ExpectedConditions.visibilityOf(chatsListView));
        if (actionBar.isDisplayed() & chatsScreenTitle.isDisplayed()) {
            if (chatItemName.isEmpty()) {
                System.out.println("You don't have any chats");
            } else {
                for (WebElement dialog_name : chatItemName) {
                    if (dialog_name.getText().equalsIgnoreCase(name)) {
                        dialog_name.click();
                        break;
                    } else System.out.println("It isn't dialog with the " + name + " !!!");
                }
            }
        }
        return true;
    }

    public boolean sendDialogMsg() {
        if (msgBox.isDisplayed()) {
            msgTextField.click();
            msgTextField.sendKeys(simpleMsg);
            sendBtn.click();
        }
        return true;
    }

    public boolean checkSentMsgStatus() {
//        sendDialogMsg();
        if (bubbleMsgs.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bubble")));
        }
        for (WebElement msg : msgsLayout) {
            if (!msg.findElement(By.id("text_view_message")).getText().contentEquals(simpleMsg)) {
                continue;
            }
            if (msg.findElement(By.id("text_view_message")).getText().contentEquals(simpleMsg)) {
                System.out.println("You found a sent message: " + msg.findElement(By.id("text_view_message")).getText());
                System.out.println("Time of the message: " + msg.findElement(By.id("text_view_date")).getText());
            }
            try {
                Boolean status = msg.findElement(By.id("text_view_status")).isDisplayed();
                if (
                        msg.findElement(By.id("text_view_date")).isDisplayed() &&
                                msg.findElement(By.id("text_view_message")).getText().contentEquals(simpleMsg) &&
                                status) {
                    switch (msg.findElement(By.id("text_view_status")).getText()) {
                        case msgStatus0:
                            System.out.println("Message delivered to server!");
                            break;
                        case msgStatus1:
                            System.out.println("Message delivered to receiver!");
                            break;
                        default:
                            break;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Message status is not displayed!");
                return false;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Message status isn't displayed!!!");
                return false;
            }
        }
        return true;
    }

    public boolean sendDialogGeo() {
        wait.until(ExpectedConditions.visibilityOf(msgBox));
        if (msgBox.isDisplayed()) {
            attachIcon.click();
            attachList.get(0).click();
        }
        return true;
    }

    public boolean sendDialogImage(int album, int count) {
        if (msgBox.isDisplayed()) {
            attachIcon.click();
            attachList.get(1).click();
            if (!imagesList.isEmpty()) {
                imagesList.get(album).click();
                for (int i = 0; i < count; ++i) {
                    imagesList.get(i).click();
                }
                gallerySubmit.click();
                previewSubmit.click();
            }
        }
        return true;
    }

    public boolean checkDisableCreateChatBtn() {
        if (submitCrtBtn.isEnabled()) {
            System.out.println("ContactsPage marked!");
            return false;
        } else {
            System.out.println("ContactsPage aren't marked! Submit button isn't enable!");
        }
        return true;
    }

    public boolean openUserProfileFromContactList(String username) {
        for (WebElement contact : ContactsPage.contactName)
            if (contact.getText().equalsIgnoreCase(username)) {
                contact.click();
                break;
            } else continue;
        return true;
    }

    public void createChatFromUserProfile() {
        wait.until(ExpectedConditions.visibilityOf(ContactsPage.contactUserProfileAvatar));
        if (ContactsPage.contact_createChatBtn.isEnabled()) {
            ContactsPage.contact_createChatBtn.click();
        }
    }



    /*======================= Groups ==========================*/

    public boolean openChatDialogByUsername(String username) {
        if (chat_user_name.isEnabled()) {
            for (WebElement user : chatUsernames)
                if (user.getText().equalsIgnoreCase(username)) {
                    user.click();
                    System.out.println("click");
                    break;
                } else {
                    touchScreen.flick(sideBar, 0, 200, 80).perform();
                    System.out.println("else");
                }
        }
        return true;
    }


}

