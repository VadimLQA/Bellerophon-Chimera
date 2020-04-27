package basetest;

import com.norwegian.base.LoginImpl;
import com.norwegian.secondary.PopupImpl;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

/**
 * @author vloparevich
 **/
public class UserSetUpTest extends BaseTest {
    private LoginImpl signIn;
    private PopupImpl popUp;

  //  @Parameters({"isLoginRequired"})
    @BeforeClass
    public void setUpLogin(/*boolean isLoginRequired*/) throws IOException {
        signIn = new LoginImpl(driver);
        popUp = new PopupImpl(driver);
        popUp.destroyPopUp();
        signIn.login(false);
    }
}
