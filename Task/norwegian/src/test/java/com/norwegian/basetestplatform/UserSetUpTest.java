package com.norwegian.basetestplatform;

import com.norwegian.pageobjects.LoginImpl;
import com.norwegian.pageobjects.secondary.PopupImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
/**
 * @author vloparevich
 */
public class UserSetUpTest extends BaseTest {
    private LoginImpl signIn;
    private PopupImpl popUp;

    @Parameters({"isLoginRequired"})
    @BeforeClass
    public void setUpLogin(boolean isLoginRequired) throws IOException{
        signIn = new LoginImpl(driver);
        popUp = new PopupImpl(driver);
        popUp.destroyPopUp();
        signIn.login(isLoginRequired);
    }
}