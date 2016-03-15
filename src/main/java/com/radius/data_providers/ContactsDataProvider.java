package com.radius.data_providers;

import org.testng.annotations.DataProvider;

/**
 * Created by kdl on 25.02.16.
 */
public class ContactsDataProvider {

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"aa"}
        };
    }

    @DataProvider
    public static Object[][] getMainContact1() {
        return new Object[][]{
                {"trinity tester"}
        };
    }

    @DataProvider
    public static Object[][] getMainContact2() {
        return new Object[][]{
                {"Alex Berlin Life"}
        };
    }
}
