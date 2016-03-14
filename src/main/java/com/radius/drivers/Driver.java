package com.radius.drivers;

//import com.radius.config.Helpers;

import com.radius.helpers.AppActivities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by kdl on 02.02.16.
 */
public class Driver {
    public static AndroidDriver driver;
    public static String APPIUM_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
//    public static String NODE_DEVICE1_URL = "http://10.0.1.69:4744/wd/hub";
//    public static String NODE_DEVICE2_URL = "http://10.0.1.69:4745/wd/hub";


    public static AndroidDriver initDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("deviceName", "192.168.56.101:5555");
            capabilities.setCapability("deviceName", "Redmi");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("appPackage", AppActivities.STAGE_PACKAGE);
            capabilities.setCapability("appActivity", AppActivities.STAGE_ACTIVITY);
//            capabilities.setCapability("appActivity", AppActivities.STAGE_EMULATOR_ACTIVITY);
            driver = new SwipeableDriver(new URL(APPIUM_SERVER_URL), capabilities);

//            //TODO: Delete PageFactory in future refactoring
//            PageFactory.initElements(driver, UserSettingsPage.class);
//            PageFactory.initElements(driver, DialogAndGroupPage.class); //path to locator class???
//            PageFactory.initElements(driver, Contacts.class);
////            PageFactory.initElements(driver, Helpers.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return driver;
    }


}
