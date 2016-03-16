package com.radius.data_providers;

import org.testng.annotations.DataProvider;

public class UserDataProvider {
    @DataProvider
    public static Object[][] getEditMainName() {
        return new Object[][]{
                {"Дима Кравченко"}
        };
    }

    @DataProvider
    public static Object[][] getPublicName(){
        return new Object[][]{
                {"Дмитрий PublicMan"}
        };
    }

    @DataProvider
    public static Object[][] getEditPublicName(){
        return new Object[][]{
                {"kdl Public"}
        };
    }
}
