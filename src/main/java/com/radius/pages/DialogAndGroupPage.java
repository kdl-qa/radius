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

    /**
     * chats screen title
     */
    //TODO: change to xpath
//    @FindBy(name = "Чаты")
    @FindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView[@text='Чаты']")
    public static WebElement chatsScreenTitle;

    /**
     * chat title
     */
    @FindBy(id = "username_toolbar")
    public static WebElement chatTitle;

    /**
     * chat title xpath
     */
    @FindBy(xpath = "//android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='0']")
    public static WebElement chatTitle1;

    /**
     * create chat button
     */
    @FindBy(id = "fab_contacts")
    public static WebElement createChatBtn;

    /**
     * chat list view
     */
    @FindBy(id = "list_chats")
    public static WebElement chatsListView;

    /**
     * chat item name
     */
    @FindBy(id = "text_view_chat_name")
    public static List<WebElement> chatItemName;


    /*===================================== Message Box ===================================================*/

    public static String simpleMsg = "abcdefghijklmnopqrstuvwxyz";
    public static String sentanceMsg = "Hi, nice to chat with you on Talker!";
    public static final String msgStatus0 = "Доставлено";
    public static final String msgStatus1 = "Прочитано";
    public static final String msgStatus2 = "Не доставлено";

    /**
     * chat message box
     */
    @FindBy(id = "send_message_box")
    public static WebElement msgBox;

    /**
     * message input field
     */
    @FindBy(id = "edit_text_send_message")
    public static WebElement msgTextField;

    /**
     * geo location mark
     */
    @FindBy(id = "send_location_checkbox")
    public static WebElement locationIcon;

    /**
     * attach button
     */
    @FindBy(id = "attach_something")
    public static WebElement attachIcon;

    /**
     * send message button
     */
    @FindBy(id = "button_send")
    public static WebElement sendBtn;

    /**
     * attach items list
     */
    @FindBy(className = "android.widget.LinearLayout")
    public static List<WebElement> attachList;

    /*===================================== Message List ===================================================*/

    @FindBy(id = "layout")
    public static List<WebElement> msgsLayout;

    /**
     * bubble message
     */
    @FindBy(id = "bubble")
    public static List<WebElement> bubbleMsgs;

    /**
     * bubble message text
     */
    @FindBy(id = "text_view_message")
    public static List<WebElement> bubbleMsgsText;

    /**
     * bubble message date
     */
    @FindBy(id = "text_view_date")
    public static List<WebElement> bubbleMsgsTime;

    /**
     * bubble message status
     */
    @FindBy(id = "text_view_status")
    public static WebElement msgStatus;

    /**
     * bubble message text
     */
    @FindBy(id = "user_name")
    public static List<WebElement> chatUsernames;

    @FindBy(id = "user_name")
    public static WebElement chat_user_name;

    @FindBy(id = "image_view_avatar")
    public static List<WebElement> chatAvatars;


    /*===================================== Attach List ===================================================*/

    /**
     * album or image
     */
    @FindBy(id = "image")
    public static List<WebElement> imagesList;

    /**
     * gallery submit button
     */
    @FindBy(id = "fab_done")
    public static WebElement gallerySubmit, previewSubmit, submitCrtBtn;

    /**
     * delete preview to send image
     */
    @FindBy(id = "delete")
    public static WebElement previewDelImage;


    /*===================================== Methods ===================================================*/

    /**
     * Constructor
     */
    public DialogAndGroupPage(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        super(driver, wait, touchScreen);
        PageFactory.initElements(driver, this);
    }


    /**
     * Open actionBar by index
     */
    public boolean tapActionBarTab(int index) {
        actionTabs.get(index).click();
        return true;
    }

    /**
     * Open menu
     */
    public boolean tapMenuIcon() {
        if (actionBar.isDisplayed() & chatsScreenTitle.isDisplayed()) {
            menuIcon.click();
            return true;
        } else {
            System.out.println("Tap on the Menu button is failed!!!");
            return false;
        }
    }

    /**
     * Open menu item by index
     */
    public boolean tapSideBarMenuIndex(int index) {
        if (!sideBar.isDisplayed()) {
            return false;
        } else {
            sideBarMenuItems.get(index).click();
            return true;
        }
    }

    /**
     * Open menu item by text
     */
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

    /**
     * Open chat by index in the list
     */
    public boolean openChatByIndex(int index) {

        wait.until(ExpectedConditions.visibilityOf(chatsListView));
        if (actionBar.isDisplayed() & chatsScreenTitle.isDisplayed()) {
            if (chatItemName.isEmpty()) {
                System.out.println("You don't have any chats");
            } else chatItemName.get(index).click();
            return true;
        } else return false;
    }

    /**
     * Open chat by name in the list
     */
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

    /**
     * Send test text message
     */
    public boolean sendDialogMsg() {
        if (msgBox.isDisplayed()) {
            msgTextField.click();
            msgTextField.sendKeys(simpleMsg);
            sendBtn.click();
        }
        return true;
    }

    /**
     * Check message status
     */
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

    /**
     * Send geo message
     */
    public boolean sendDialogGeo() {
        wait.until(ExpectedConditions.visibilityOf(msgBox));
        if (msgBox.isDisplayed()) {
            attachIcon.click();
            attachList.get(0).click();
        }
        return true;
    }

    /**
     * Send image message
     */
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

    /**
     * Check disable status of submit button
     */
    public boolean checkDisableCreateChatBtn() {
        if (submitCrtBtn.isEnabled()) {
            System.out.println("ContactsPage marked!");
            return false;
        } else {
            System.out.println("ContactsPage aren't marked! Submit button isn't enable!");
        }
        return true;
    }

    /**
     * Open user profile in contact
     */
    //todo: shift to contacts class
    public boolean openUserProfileFromContactList(String username) {
        for (WebElement contact : ContactsPage.contactName)
            if (contact.getText().equalsIgnoreCase(username)) {
                contact.click();
                break;
            } else continue;
        return true;
    }

    /**
     * Create chat from user profile
     */
    public void createChatFromUserProfile() {
        wait.until(ExpectedConditions.visibilityOf(ContactsPage.contactUserProfileAvatar));
        if (ContactsPage.contact_createChatBtn.isEnabled()) {
            ContactsPage.contact_createChatBtn.click();
        }
    }



    /*========================================== Groups ============================================================*/
    /**
     * Open dialog chat from group chat screen
     */
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

