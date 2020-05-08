package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

/**
 * @author vloparevich
 **/
public class NavBarImpl extends BasePage {
    String currentClassName = NavBarImpl.class.getSimpleName();

    private static final String MAIN_MENU_ENTITY = "//ul[@class='listing -inline']//a[@class='linkNav' and contains(text(), '%s')]";
    private static final String SUB_MENU_ENTITY = "//ul[@class='listing -block']//a[@class='linkItem' and contains(text(), '%s')]";

    public enum Tab {
        EXPLORE,
        MANAGE
    }

    public enum SubTab {
        PORTS,
        NORWEGIANS_PRIVATE_ISLAND,
        SHORE_EXCURSIONS
    }

    @When("^user goes to \"(.*?)\" tab$")
    public void user_goes_to_tab(Tab menuTab) {
        logger.debug(currentClassName + ":: user is about to click navigation menu of " + menuTab.toString());
        switch (menuTab) {
            case EXPLORE:
                clickElement(By.xpath(String.format(MAIN_MENU_ENTITY, "Explore")));
                break;
            case MANAGE:
                clickElement(By.xpath(String.format(MAIN_MENU_ENTITY, "Manage")));
                break;
        }
    }

    @And("^user selects \"([^\"]*)\" submenu$")
    public void user_selects_submenu_of_tab(SubTab subTab) {
        logger.debug(currentClassName + ":: user is about to click subtab menu of " + subTab.toString());
        switch (subTab) {
            case PORTS:
                clickElement(By.xpath(String.format(SUB_MENU_ENTITY, "Ports")));
                break;
            case NORWEGIANS_PRIVATE_ISLAND:
                clickElement(By.xpath(String.format(SUB_MENU_ENTITY, "Norwegian's Private Island")));
                break;
            case SHORE_EXCURSIONS:
                clickElement(By.xpath(String.format(SUB_MENU_ENTITY, "Shore Excursions")));
                break;
        }
    }
}
