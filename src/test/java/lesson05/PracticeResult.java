package lesson05;

import io.cucumber.java.de.Wenn;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

/**
 * Hoc vien hoan thanh bai tap trong class nay
 */
public class PracticeResult {
    // The driver for interacting with the webpage
    private WebDriver webDriver;

    /**
     * Method thực thi trước mỗi class, tiến hành cấu hình Chrome Driver và khởi tạo
     */
    @BeforeClass
    public void beforeClass() {
        String chromeDriverPath = "src/test/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--max-window-size");
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
    }

    /**
     * Method thực thi cuối cùng mỗi class, sẽ tiến hành đóng toàn bộ các chrome session đang chạy - Debug Mode
     **/
//    @AfterClass
//    public void afterClass() {
//        if (Objects.nonNull(webDriver)) webDriver.quit();
//    }


    /**
     * Description:
     */
    @Test(description = "Luyen tap tim kiem phan tu")
    public void practices_findingLocator_01() {
        // Wait element
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        gotoRiseWebsite();

        System.out.println("quyetpv-Debug: Access website");
        // TODO: 10/05/2023 : Tìm kiếm với nhiều kịch bản advance khác nhau cho cùng component
        //Dynamic Locator
        String DYNAMIC_tagInfo = "//h4[normalize-space()='%s']";
        String RADIO_FORM = "//input[@value='%s']";
        String PLACEHOLDER_FORM = "//div[@id='%s']/descendant::input[@placeholder='%s']";
        String DYNAMIC_menuOfMyProfile = "//ul[@id='client-contact-tabs']/descendant::a[normalize-space()='%s']";
        String DYNAMIC_btnSave = "//div[@id='%s']/descendant::button[@type='submit']";
        String DYNAMIC_COMBOBOX_btn = "//label[@for='%s']/../descendant::b";
        String DYNAMIC_COMBOBOX_selection = "//li[@class]/div[normalize-space()='%s']";

        //region General Info
        //Wait for General Info show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "General Info"))));
        //Input First Name
        String firstNameXPath = String.format(PLACEHOLDER_FORM, "tab-general-info", "First name");
        WebElement firstNameEle = webDriver.findElement(By.xpath(firstNameXPath));
        firstNameEle.clear();
        firstNameEle.sendKeys("quyet");

        //Input Last Name
        String lastNameXPath = String.format(PLACEHOLDER_FORM, "tab-general-info", "Last name");
        WebElement lastNameEle = webDriver.findElement(By.xpath(lastNameXPath));
        lastNameEle.clear();
        lastNameEle.sendKeys("pv");

        //Input Phone for person
        String phoneXPath = String.format(PLACEHOLDER_FORM, "tab-general-info", "Phone");
        WebElement phoneEle = webDriver.findElement(By.xpath(phoneXPath));
        phoneEle.clear();
        phoneEle.sendKeys("123456789");

        //Input Skype
        String skypeXPath = String.format(PLACEHOLDER_FORM, "tab-general-info", "Skype");
        WebElement skypeEle = webDriver.findElement(By.xpath(skypeXPath));
        skypeEle.clear();
        skypeEle.sendKeys("Anime");

        //Input Job title
        String jobTitleXPath = String.format(PLACEHOLDER_FORM, "tab-general-info", "Job Title");
        WebElement jobTitleEle = webDriver.findElement(By.xpath(jobTitleXPath));
        jobTitleEle.clear();
        jobTitleEle.sendKeys("Professional");

        //Gender selection
        String maleXPath = String.format(RADIO_FORM, "male");
        WebElement maleEle = webDriver.findElement(By.xpath(maleXPath));
        maleEle.click();

        String femaleXPath = String.format(RADIO_FORM, "female");
        WebElement femaleEls = webDriver.findElement(By.xpath(femaleXPath));

        String otherXPath = String.format(RADIO_FORM, "other");
        WebElement otherEle = webDriver.findElement(By.xpath(otherXPath));

        //Save General Info
        String btnSaveGeneralXPath = String.format(DYNAMIC_btnSave, "tab-general-info");
        WebElement btnSaveGeneralEle = webDriver.findElement(By.xpath(btnSaveGeneralXPath));
        clickAndWait(btnSaveGeneralEle, 3000);

        System.out.println("quyetpv-Debug: Save General Info success");
        //endregion

        //region Company Info/Contact info
        //Access on Company Info/Contact info tab
        String companyXPath = String.format(DYNAMIC_menuOfMyProfile, "Company");
        //String companyXPath = String.format(DYNAMIC_menuOfMyProfile,"Contact info");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(companyXPath)));
        WebElement companyEle = webDriver.findElement(By.xpath(companyXPath));
        companyEle.click();

        //Wait for Company Info show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "Client info"))));
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo,"Contact info"))));
        System.out.println("quyetpv-Debug: Access on Company Info/Contact info tab");

        //Type selection
        String typeOrganizationXPath = String.format(RADIO_FORM, "organization");
        WebElement organizationEle = webDriver.findElement(By.xpath(typeOrganizationXPath));
        organizationEle.click();

        String typePersonXPath = String.format(RADIO_FORM, "person");
        WebElement personEle = webDriver.findElement(By.xpath(typePersonXPath));
        //personEle.click();

        //Input Company name
        //String companyNameXPath = String.format(PLACEHOLDER_FORM,"tab-company-info","Company name");
        String companyNameXPath = "//input[@name='company_name']";
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(companyNameXPath)));
        WebElement companyNameEle = webDriver.findElement(By.xpath(companyNameXPath));
        companyNameEle.clear();
        companyNameEle.sendKeys("SRV");

        //Input Address
        String addressXPath = "//textarea[@placeholder='Address']";
        WebElement addressEle = webDriver.findElement(By.xpath(addressXPath));
        addressEle.clear();
        addressEle.sendKeys("Tay Ho");

        //Input City
        String cityXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "City");
        WebElement cityEle = webDriver.findElement(By.xpath(cityXPath));
        cityEle.clear();
        cityEle.sendKeys("HaNoi");

        //Input State
        String stateXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "State");
        WebElement stateEle = webDriver.findElement(By.xpath(stateXPath));
        stateEle.clear();
        stateEle.sendKeys("HMT");

        //Input Zip
        String zipXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "Zip");
        WebElement zipEle = webDriver.findElement(By.xpath(zipXPath));
        zipEle.clear();
        zipEle.sendKeys("100000");

        //Input country
        String countryXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "Country");
        WebElement countryEle = webDriver.findElement(By.xpath(countryXPath));
        countryEle.clear();
        countryEle.sendKeys("MB");

        //Input Phone of Company
        String phoneCompanyXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "Phone");
        WebElement phoneCompanyEle = webDriver.findElement(By.xpath(phoneCompanyXPath));
        phoneCompanyEle.clear();
        phoneCompanyEle.sendKeys("987654321");

        //Input Website
        String websiteXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "Website");
        WebElement websiteEle = webDriver.findElement(By.xpath(websiteXPath));
        websiteEle.clear();
        websiteEle.sendKeys("http://abc.com");

        //Input VAT
        String vatXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "VAT Number");
        WebElement vatEle = webDriver.findElement(By.xpath(vatXPath));
        vatEle.clear();
        vatEle.sendKeys("5%");

        //Input GST
        String gstXPath = String.format(PLACEHOLDER_FORM, "tab-company-info", "GST Number");
        WebElement gstEle = webDriver.findElement(By.xpath(gstXPath));
        gstEle.clear();
        gstEle.sendKeys("50");

        //Save Company Info
        String btnSaveCompanyInfoXPath = String.format(DYNAMIC_btnSave, "tab-company-info");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(btnSaveCompanyInfoXPath)));
        WebElement btnCompanyInfoEle = webDriver.findElement(By.xpath(btnSaveCompanyInfoXPath));
        clickAndWait(btnCompanyInfoEle, 12000);

        System.out.println("quyetpv-Debug: Save Company Info success");
        //endregion

        //region Social Links
        //Access Social Links tab
        String socialLinksXPath = String.format(DYNAMIC_menuOfMyProfile, "Social Links");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(socialLinksXPath)));
        WebElement socialLinksEle = webDriver.findElement(By.xpath(socialLinksXPath));
        socialLinksEle.click();

        //Wait Social Links tab show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "Social Links"))));
        System.out.println("quyetpv-Debug: Access on Social Links tab");

        //Input Facebook
        String facebookXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://www.facebook.com/");
        WebElement facebookEle = webDriver.findElement(By.xpath(facebookXPath));
        facebookEle.clear();
        facebookEle.sendKeys("https://www.facebook.com/quyetpv");

        //Input Twitter
        String twitterXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://twitter.com/");
        WebElement twitterEle = webDriver.findElement(By.xpath(twitterXPath));
        twitterEle.clear();
        twitterEle.sendKeys("https://twitter.com/quyetpv");

        //Input Linkedin
        String linkedinXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://www.linkedin.com/");
        WebElement linkedinEle = webDriver.findElement(By.xpath(linkedinXPath));
        linkedinEle.clear();
        linkedinEle.sendKeys("https://www.linkedin.com/quyetpv");

        //Input WhatsApp
        String whatsAppXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://wa.me/+001XXXXXXX");
        WebElement whatsAppEle = webDriver.findElement(By.xpath(whatsAppXPath));
        whatsAppEle.clear();
        whatsAppEle.sendKeys("https://wa.me/+001XXXXXXX/quyetpv");

        //Input Digg
        String diggXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "http://digg.com/");
        WebElement diggEle = webDriver.findElement(By.xpath(diggXPath));
        diggEle.clear();
        diggEle.sendKeys("http://digg.com/quyetpv");

        //Input Youtube
        String youtubeXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://www.youtube.com/");
        WebElement youtubeEle = webDriver.findElement(By.xpath(youtubeXPath));
        youtubeEle.clear();
        youtubeEle.sendKeys("https://www.youtube.com/quyetpv");

        //Input Pinterest
        String pinterestXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://www.pinterest.com/");
        WebElement pinterestEle = webDriver.findElement(By.xpath(pinterestXPath));
        pinterestEle.clear();
        pinterestEle.sendKeys("https://www.pinterest.com/quyetpv");

        //Input Instagram
        String igXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://instagram.com/");
        WebElement igEle = webDriver.findElement(By.xpath(igXPath));
        igEle.clear();
        igEle.sendKeys("https://instagram.com/quyetpv");

        //Input Github
        String githubXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://github.com/");
        WebElement githubEle = webDriver.findElement(By.xpath(githubXPath));
        githubEle.clear();
        githubEle.sendKeys("https://github.com/quyetpv");

        //Input Tumblr
        String tumblrXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://www.tumblr.com/");
        WebElement tumblrEle = webDriver.findElement(By.xpath(tumblrXPath));
        tumblrEle.clear();
        tumblrEle.sendKeys("https://www.tumblr.com/quyetpv");

        //Input Vine
        String vineXPath = String.format(PLACEHOLDER_FORM, "tab-social-links", "https://vine.co/");
        WebElement vineEle = webDriver.findElement(By.xpath(vineXPath));
        vineEle.clear();
        vineEle.sendKeys("https://vine.co/quyetpv");

        //Save Social Links
        String btnSavesocialLinksXPath = String.format(DYNAMIC_btnSave, "tab-social-links");
        WebElement btnSavesocialLinksEle = webDriver.findElement(By.xpath(btnSavesocialLinksXPath));
        clickAndWait(btnSavesocialLinksEle, 10000);

        System.out.println("quyetpv-Debug: Save Social Links success");
        //endregion

        //region Account settings
        //Access Account settings tab
        String accountSettingsXPath = String.format(DYNAMIC_menuOfMyProfile, "Account settings");
        WebElement accountSettingsEle = webDriver.findElement(By.xpath(accountSettingsXPath));
        accountSettingsEle.click();

        //Wait Account settings tab show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "Account settings"))));
        System.out.println("quyetpv-Debug: Access on Account settings tab");

        //Input Email
        String emailXPath = String.format(PLACEHOLDER_FORM, "tab-account-settings", "Email");
        WebElement emailEle = webDriver.findElement(By.xpath(emailXPath));
        emailEle.clear();
        emailEle.sendKeys("client@demo.com");

        //Input Password
        String pwXPath = String.format(PLACEHOLDER_FORM, "tab-account-settings", "Password");
        WebElement pwEle = webDriver.findElement(By.xpath(pwXPath));
        pwEle.clear();
        pwEle.sendKeys("riseDemo");

        //Input Retype password
        String retypeOfPwXPath = String.format(PLACEHOLDER_FORM, "tab-account-settings", "Retype password");
        WebElement retypeOfPwEle = webDriver.findElement(By.xpath(retypeOfPwXPath));
        retypeOfPwEle.clear();
        retypeOfPwEle.sendKeys("riseDemo");

        //Save Account settings
        String btnAccountSettingsXPath = String.format(DYNAMIC_btnSave, "tab-account-settings");
        WebElement btnAccountSettingsEle = webDriver.findElement(By.xpath(btnAccountSettingsXPath));
        clickAndWait(btnAccountSettingsEle, 10000);

        System.out.println("quyetpv-Debug: Save Account settings success");
        //endregion

        //region My preferences
        //Access My preferences tab
        String myPreferencesXPath = String.format(DYNAMIC_menuOfMyProfile, "My preferences");
        WebElement myPreferencesEle = webDriver.findElement(By.xpath(myPreferencesXPath));
        myPreferencesEle.click();

        //Wait My preferences tab show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "My preferences"))));
        System.out.println("quyetpv-Debug: Access on My preferences tab");

        //Select Notification sound volume
        String soundXPath = String.format(DYNAMIC_COMBOBOX_btn, "notification_sound_volume");
        WebElement soundEle = webDriver.findElement(By.xpath(soundXPath));
        soundEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"-"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"|"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"||"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"|||"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"||||"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"|||||"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"||||||"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"|||||||"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"||||||||"))).click();
        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection, "|||||||||"))).click();

        //Select Enable web notification
        String enableWebNotiXPath = String.format(DYNAMIC_COMBOBOX_btn, "enable_web_notification");
        WebElement enableWebNotiEle = webDriver.findElement(By.xpath(enableWebNotiXPath));
        enableWebNotiEle.click();
        //webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Yes"))).click();
        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection, "No"))).click();

        //Select Enable email notification
        String enableEmailNotiXPath = String.format(DYNAMIC_COMBOBOX_btn, "enable_email_notification");
        WebElement enableEmailNotiEle = webDriver.findElement(By.xpath(enableEmailNotiXPath));
        enableEmailNotiEle.click();
        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection, "Yes"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"No"))).click();

        //Select Language
        String languageXPath = String.format(DYNAMIC_COMBOBOX_btn, "personal_language");
        WebElement languageEle = webDriver.findElement(By.xpath(languageXPath));
        languageEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,""French"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Dutch"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Russian"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Spanish"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Czech"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Norwegian"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Portuguese"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Italian"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Polish"))).click();
        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection, "English"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"German"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Greek"))).click();

        //Hide menus from topbar
//        String hideMenuXPath = "//label[normalize-space()='Hide menus from topbar']/following-sibling::input";
//        WebElement hideMenuEle = webDriver.findElement(By.xpath(hideMenuXPath));
//        String clearHideMenu = "//label[normalize-space()='Hide menus from topbar']/../descendant::a";
//
//        if ( webDriver.findElement(By.xpath(clearHideMenu)) != null){
//            webDriver.findElement(By.xpath(clearHideMenu)).click();
//        }
//
//        hideMenuEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"To do"))).click();
//        hideMenuEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Favorite projects"))).click();
//        hideMenuEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Dashboard customization"))).click();
//        hideMenuEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Quick add"))).click();
//        hideMenuEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Language"))).click();

        //Disable keyboard shortcuts
        String disableKbShortcutsXPath = String.format(DYNAMIC_COMBOBOX_btn, "disable_keyboard_shortcuts");
        WebElement disableKbShortcutsEle = webDriver.findElement(By.xpath(disableKbShortcutsXPath));
        disableKbShortcutsEle.click();
        //webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"Yes"))).click();
        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection, "No"))).click();

        //Snooze length
        String soonzeLengthXPath = String.format(DYNAMIC_COMBOBOX_btn, "reminder_snooze_length");
        WebElement soonzeLengthEle = webDriver.findElement(By.xpath(soonzeLengthXPath));
        soonzeLengthEle.click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"5 Minutes"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"10 Minutes"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"15 Minutes"))).click();
        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection, "20 Minutes"))).click();
//        webDriver.findElement(By.xpath(String.format(DYNAMIC_COMBOBOX_selection,"30 Minutes"))).click();

        //Save My preferences
        String btnmyPreferencesXPath = String.format(DYNAMIC_btnSave, "tab-my-preferences");
        WebElement btnmyPreferencesEle = webDriver.findElement(By.xpath(btnmyPreferencesXPath));
        clickAndWait(btnmyPreferencesEle, 10000);

        System.out.println("quyetpv-Debug: Save My preferences success");
        //endregion

        //region Left Menu
        //Access Left Menu tab
        String leftMenuXPath = String.format(DYNAMIC_menuOfMyProfile, "Left menu");
        WebElement leftMenuEle = webDriver.findElement(By.xpath(leftMenuXPath));
        leftMenuEle.click();

        //Wait Left Menu tab show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "Left menu"))));
        System.out.println("quyetpv-Debug: Access on Left Menu tab");

        //Add menu item
        webDriver.findElement(By.xpath("//a[contains(@class,'menu-item-add-button')]")).click();
        //Wait Add menu item show
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo, "Add menu item"))));

        //region Input Information form for new item
        String inputNewItem_FORM = "//input[@id='%s']";

        //Input title
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(String.format(inputNewItem_FORM,"title")))));
        webDriver.findElement(By.xpath(String.format(inputNewItem_FORM,"title"))).sendKeys("TestAddMenu");
        //Input Language Key
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(String.format(inputNewItem_FORM,"language_key")))));
        webDriver.findElement(By.xpath(String.format(inputNewItem_FORM,"language_key"))).sendKeys("Test_Add_Menu_quyetpv");
        //Input URL
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(String.format(inputNewItem_FORM,"url")))));
        webDriver.findElement(By.xpath(String.format(inputNewItem_FORM,"url"))).sendKeys("https://rise.fairsketch.com/");
        //Open in new tab checkbox
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(String.format(inputNewItem_FORM,"open_in_new_tab")))));
        webDriver.findElement(By.xpath(String.format(inputNewItem_FORM,"open_in_new_tab"))).click();
        //Select icon for menu
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-icon='aperture']")));
        webDriver.findElement(By.xpath("//span[@data-icon='aperture']")).click();
        //Save new menu item
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ajaxModalContent']//button[@type='submit']")));
        WebElement btnSaveAddMenu = webDriver.findElement(By.xpath("//div[@id='ajaxModalContent']//button[@type='submit']"));
        clickAndWait(btnSaveAddMenu,5000);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'menu-item-add-button')]")));

        System.out.println("quyepv-Debug: Add new item TestAddMenu on Left Menu success");
        //Close new menu item
        //webDriver.findElement(By.xpath("//div[@id='ajaxModalContent']//button[@type='button']")).click();

        //endregion

//        //region Interact with new menu item "TestAddMenu"
//        String interactNewItem_FORM = "//div[@data-value='TestAddMenu']//%s[contains(@class,'%s')]";
//
//        //Make submenu for new menu item
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(interactNewItem_FORM,"span","make-sub-menu"))));
//        webDriver.findElement(By.xpath(String.format(interactNewItem_FORM,"span","make-sub-menu"))).click();
//
//        //Unsub for new menu item
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(interactNewItem_FORM,"span","toggle-menu-icon"))));
//        webDriver.findElement(By.xpath(String.format(interactNewItem_FORM,"span","toggle-menu-icon"))).click();
//
//        //Edit for new menu item
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(interactNewItem_FORM,"a","edit-button"))));
//        webDriver.findElement(By.xpath(String.format(interactNewItem_FORM,"a","edit-button"))).click();
//
//        //Wait Edit menu item show
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(DYNAMIC_tagInfo,"Edit"))));
//
//        //Save edit menu item
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ajaxModalContent']//button[@type='submit']")));
//        clickAndWait(btnSaveAddMenu,5000);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'menu-item-add-button')]")));
//        System.out.println("quyetpv-Debug: Edit item success");
//
////        //Close edit menu
////        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ajaxModalContent']//button[@type='button']")));
////        webDriver.findElement(By.xpath("//div[@id='ajaxModalContent']//button[@type='button']")).click();
//
//        //Delite new menu item
//        webDriver.findElement(By.xpath(String.format(interactNewItem_FORM,"span","delete-left-menu"))).click();
//        System.out.println("quyetpv-Debug: Delete item success");
//
//        //endregion

        //Save Left Menu
//        String btnLeftMenuXPath = String.format(DYNAMIC_btnSave, "tab-user-left-menu");
//        WebElement btnLeftMenuEle = webDriver.findElement(By.xpath(btnLeftMenuXPath));
//        clickAndWait(btnLeftMenuEle, 10000);
//
//        System.out.println("quyetpv-Debug: Save Left Menu success");
        //endregion

    }

    /**
     * Click save button + doi lam moi trang web sau khi save
     */
    public void clickAndWait(WebElement element, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Go to DemoQA Website
     */
    private void gotoRiseWebsite() {
        String url = "https://rise.fairsketch.com/signin";
        webDriver.get(url);

        String LOGIN_FORM = "//input[@placeholder='%s']";

        // Wait for login shown
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(LOGIN_FORM, "Email"))));

        // Input username
        WebElement loginEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM, "Email")));
        loginEle.clear();
        loginEle.sendKeys("client@demo.com");

        // Input password
        WebElement passwordEle = webDriver.findElement(By.xpath(String.format(LOGIN_FORM, "Password")));
        passwordEle.clear();
        passwordEle.sendKeys("riseDemo");

        // Click login button
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        // Wait for user icon shown
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='user-dropdown']//img")));

        // Access my profile
        WebElement myProfileEle = webDriver.findElement(By.xpath("//ul[@id='sidebar-menu']//span[text()='My Profile']"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", myProfileEle);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@id='profile-image-preview']")));

    }
}
