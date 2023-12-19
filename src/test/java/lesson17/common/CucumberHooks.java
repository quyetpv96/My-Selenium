package lesson17.common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lesson12.common.DriverManager;

public class CucumberHooks {
    @Before
    public  static void beforeTest(){

    }

    @After
    public static void afterTest(){
        if(DriverManager.getWebDriver() != null){
            DriverManager.quit();
        }
    }
}
