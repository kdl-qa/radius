package com.radius.func;

import com.radius.data.Chats;
import com.radius.data.Contacts;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



//public class DialogAndChats {
//
//    public static String simpleMsg = "abcdefghijklmnopqrstuvwxyz";
//    public static String sentanceMsg = "Hi, nice to chat with you on Talker!";
//
//    public static final String  msgStatus0 = "Доставлено";
//    public static final String msgStatus1 = "Прочитано";
//
//    /**
//     * Created by kdl on 02.02.16.
//     */
public class DialogAndChats {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private TouchActions touchScreen;

    public DialogAndChats(AndroidDriver driver, WebDriverWait wait, TouchActions touchScreen) {
        this.driver = driver;
        this.wait = wait;
        this.touchScreen = touchScreen;
    }


    public static String simpleMsg = "abcdefghijklmnopqrstuvwxyz";
    public static String sentanceMsg = "Hi, nice to chat with you on Talker!";
    public static final String msgStatus0 = "Доставлено";
    public static final String msgStatus1 = "Прочитано";


    public boolean tapActionBarTab(int index) {
        Chats.actionTabs.get(index).click();
        return true;
    }

    public boolean tapMenuIcon() {
        if (Chats.actionBar.isDisplayed() & Chats.chatsScreenTitle.isDisplayed()) {
            Chats.menuIcon.click();
            return true;
        } else {
            System.out.println("Tap on the Menu button is failed!!!");
            return false;
        }
    }

    public boolean tapSideBarMenuIndex(int index) {
        if (!Chats.sideBar.isDisplayed()) {
            return false;
        } else {
            Chats.sideBarMenuItems.get(index).click();
            return true;
        }
    }

    public boolean tapSideBarMenuText(String text) {
        tapMenuIcon();
        if (Chats.sideBar.isDisplayed()){
            for (WebElement item : Chats.sideBarMenuItems)
                if (item.getText().equalsIgnoreCase(text)) {
                    item.click();
                    break;
                } else continue;

        } else
            touchScreen.flick(Chats.sideBar, -10, 0, 80).perform();
        return true;
    }


    public boolean openChatByIndex(int index) {

        wait.until(ExpectedConditions.visibilityOf(Chats.dialogListView));
        if (Chats.actionBar.isDisplayed() & Chats.chatsScreenTitle.isDisplayed()) {
            if (Chats.dialogName.isEmpty()) {
                System.out.println("You don't have any chats");
            } else Chats.dialogName.get(index).click();
            return true;
        } else return false;
    }

    public boolean openDialogByName(String name) {
        wait.until(ExpectedConditions.visibilityOf(Chats.dialogListView));
        if (Chats.actionBar.isDisplayed() & Chats.chatsScreenTitle.isDisplayed()) {
            if (Chats.dialogName.isEmpty()) {
                System.out.println("You don't have any chats");
            } else {
                for (WebElement dialog_name : Chats.dialogName) {
                    if (dialog_name.getText().equalsIgnoreCase(name)) {
                        dialog_name.click();
                        break;
                    } else
                        System.out.println("It isn't dialog with the " + name + " !!!");
                }
            }
        }
        return true;
    }

    public boolean sendDialogMsg() {
        if (Chats.msgBox.isDisplayed()) {
            Chats.msgTextField.click();
            Chats.msgTextField.sendKeys(simpleMsg);
            Chats.sendBtn.click();
        }
        return true;
    }

    public boolean checkSentMsgStatus() {
//        sendDialogMsg();
        if (Chats.bubbleMsgs.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bubble")));
        }
        for (WebElement msg : Chats.msgsLayout) {
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
            }

        }
        return true;
    }

    public boolean sendDialogGeo() {
        wait.until(ExpectedConditions.visibilityOf(Chats.msgBox));
        if (Chats.msgBox.isDisplayed()) {
            Chats.attachIcon.click();
            Chats.attachList.get(0).click();
        }
        return true;
    }

    public boolean sendDialogImage(int album, int count) {
        if (Chats.msgBox.isDisplayed()) {
            Chats.attachIcon.click();
            Chats.attachList.get(1).click();
            if (!Chats.list.isEmpty()) {
                Chats.list.get(album).click();
                for (int i = 0; i < count; ++i) {
                    System.out.println(i);
                    Chats.list.get(i).click();
                }
                Chats.gallerySubmit.click();
                Chats.previewSubmit.click();
            }
        }
        return true;
    }

    public boolean DisableCreateChatBtn() {
        if (Chats.submitCrtBtn.isEnabled()) {
            System.out.println("Contacts marked!");
            return false;
            } else {
                System.out.println("Contacts aren't marked! Submit button isn't enable!");
            }
        return true;
    }

    public boolean openUserProfileFromContactList(String username) {
        for (WebElement contact : Contacts.contactsName)
            if (contact.getText().equalsIgnoreCase(username)) {
                contact.click();
                break;
            } else continue;
        return true;
    }

    public void createChatFromUserProfile() {
        wait.until(ExpectedConditions.visibilityOf(Contacts.contactUserProfileAvatar));
        if (Contacts.contact_createChatBtn.isEnabled()) {
            Contacts.contact_createChatBtn.click();
        }
    }



    /*======================= Groups ==========================*/

    public boolean openChatDialogByUsername(String username){
        for (WebElement user : Chats.chatUsernames)
            if (user.getText().equalsIgnoreCase(username)){
                user.click();
                break;
            } else continue;
        return true;
    }


}

