package com.radius.config;

import com.radius.page_object.android.Chats;
import com.radius.page_object.android.Contacts;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.net.URL;

/**
 * Created by kdl on 02.02.16.
 */
public class Driver {
    public static AndroidDriver driver;

    public static AndroidDriver initDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Android");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("unicodeKeyboard", true);
//        capabilities.setCapability("resetKeyboard", true);
            capabilities.setCapability("appActivity", "ua.my.im.android.myua.screens.activities.LoginActivity");
            capabilities.setCapability("appPackage", "ua.my.im.android.myua");

            driver = new SwipeableDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//  driver = new SwipeableDriver(new URL("http://10.0.1.69:4745/wd/hub"), capabilities);

            PageFactory.initElements(driver, Chats.class); //path to locator class???
            PageFactory.initElements(driver, Contacts.class);
            PageFactory.initElements(driver, Helpers.class);
//
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return driver;
    }


}
