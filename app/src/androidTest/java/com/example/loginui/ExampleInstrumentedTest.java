package com.example.loginui;

import android.content.Context;
import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Before;
import androidx.test.uiautomator.*;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Until;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String APP_PACKAGE = "com.example.loginui";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void setUp() throws UiObjectNotFoundException {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
        // Launch the app
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE);
        context.startActivity(intent);
        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test()
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.loginui", appContext.getPackageName());
    }

    @Test
    public void verifyLoginScreenElementsIsDisplayedAsExpected() throws UiObjectNotFoundException {
        //Very Login page UI objects
        UiObject HeaderText = device.findObject(new UiSelector().resourceId("com.example.loginui:id/textView7"));
        UiObject text_input_end_icon = device.findObject(new UiSelector().resourceId("com.example.loginui:id/text_input_end_icon"));
        UiObject new_account_creation = device.findObject(new UiSelector().resourceId("com.example.loginui:id/textView6"));
        UiObject no_account = device.findObject(new UiSelector().resourceId("com.example.loginui:id/textView4"));
        UiObject forgot_Password = device.findObject(new UiSelector().resourceId("com.example.loginui:id/textView8"));

        Assert.assertTrue(HeaderText.exists() && HeaderText.getText().equals("MOBILE APPLICATION DEVELOPMENT"));
        Assert.assertTrue(text_input_end_icon.exists());
        Assert.assertTrue(new_account_creation.exists() && new_account_creation.getText().equals("Create a new account"));
        Assert.assertTrue(no_account.exists() && no_account.getText().equals("Don't have account?"));
        Assert.assertTrue(forgot_Password.exists() && forgot_Password.getText().equals("Forgot Password?"));
    }

    @Test
    public void UserLoginToApplication() throws UiObjectNotFoundException {
        //Enter Username
        UiObject userName = device.findObject(new UiSelector().resourceId("com.example.loginui:id/inputUsername"));
        userName.clearTextField();
        userName.setText("Bolaji Automation");

        //Enter Password
        UiObject password = device.findObject(new UiSelector().resourceId("com.example.loginui:id/inputPassword"));
        password.clearTextField();
        password.setText("Teladoc Health");

        //Click on Login button
        UiObject loginButton = device.findObject(new UiSelector().resourceId("com.example.loginui:id/button4"));
        loginButton.click();

        //Verify landing screen - (Not yet developed)
        //Verify Login issues error message -  (Not yet developed)
    }

    @After
    public void TearDown() {
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(APP_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }
}