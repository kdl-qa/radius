package com.radius.data;

import org.testng.annotations.DataProvider;

/**
 * Created by kdl on 28.02.16.
 */
public class UserDataProvider {
    @DataProvider
    public static Object[][] getEditMainName() {
        return new Object[][]{
                {"Дима Кравченко"}
        };
    }
}
