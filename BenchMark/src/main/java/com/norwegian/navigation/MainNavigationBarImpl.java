package com.norwegian.navigation;

import com.norwegian.base.CommonImpl;
import com.norwegian.explore.PortsImpl;
import com.norwegian.explore.ShoreExcursionImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author vloparevich
 */
public class MainNavigationBarImpl extends CommonImpl {

    private static final String MAIN_MENU_ENTITY = "//ul[@class='listing -inline']//a[@class='linkNav' and contains(text(), '%s')]";
    private static final String SUB_MENU_ENTITY = "//ul[@class='listing -block']//a[@class='linkItem' and contains(text(), '%s')]";

    public MainNavigationBarImpl(WebDriver driver) {
        super(driver);
    }

    public enum Tab {
        EXPLORE,
        MANAGE
    }

    public enum SubTab {
        PORTS,
        NORWEGIANS_PRIVATE_ISLAND,
        SHORE_EXCURSIONS
    }

    public void getMainBarOption(Tab entity) {
        switch (entity) {
            case EXPLORE:
                clickElement(By.xpath(String.format(MAIN_MENU_ENTITY, "Explore")));
                break;
            case MANAGE:
                clickElement(By.xpath(String.format(MAIN_MENU_ENTITY, "Manage")));
                break;
        }
    }

    public <T> T getSubBarOption(SubTab entity) {
        switch (entity) {
            case PORTS:
                clickElement(By.xpath(String.format(SUB_MENU_ENTITY, "Ports")));
                return (T) new PortsImpl(driver);
            case NORWEGIANS_PRIVATE_ISLAND:
                clickElement(By.xpath(String.format(SUB_MENU_ENTITY, "Norwegian's Private Island")));
                return (T) this;
            case SHORE_EXCURSIONS:
                clickElement(By.xpath(String.format(SUB_MENU_ENTITY, "Shore Excursions")));
                return (T) new ShoreExcursionImpl(driver);
            default: return (T) this;
        }
    }
}
