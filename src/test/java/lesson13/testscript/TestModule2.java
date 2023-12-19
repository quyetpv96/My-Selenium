package lesson13.testscript;

import lesson13.common.DriverManager;
import lesson13.common.TestBase;
import lesson13.pages.*;
import lesson13.provider.Module2Provider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestModule2 extends TestBase {
    private InvoicesPage invoicesPage;
    private ManageLabelPage manageLabelPage;
    private AddPaymentPage addPaymentPage;
    private AddInvoicePage addInvoicePage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void beforeTestMethod() {
        gotoInvoicePage();
    }

    @AfterClass
    public void afterClass() {
        DriverManager.quit();
    }

    @Test(enabled = false, priority = 1, description = "Add 2 labels", dataProvider = "RISE_Module2_Data", dataProviderClass = Module2Provider.class)
    public void RISE_AddLabel_001_DataDriven(HashMap<String, String> data) {

        gotoInvoicePage();

        // Click [Manage labels]
        manageLabelPage = invoicesPage.gotoManageLabelPage();

        // Verify manageLabelPage
        manageLabelPage.verifyManageLabelPage();

        // Add 2 labels
        manageLabelPage.addLabel(data);

        // Verify new label Integration On [Add invoice]
        addInvoicePage = invoicesPage.gotoAddInvoicePage();
        addInvoicePage.verifyNewLabelIntegrationOnAddInvoice(data);

    }
    @Test(enabled = false, priority = 2, description = "Add 3 payment", dataProvider = "RISE_Module2_Data", dataProviderClass = Module2Provider.class)
    public void RISE_AddPayment_001_DataDriven(HashMap<String, String> data) {
        gotoInvoicePage();
        // Click [Add payment]
        addPaymentPage = invoicesPage.gotoAddPaymentPage();

        // Verify addPaymentPage
        addPaymentPage.verifyAddPaymentPage();

        // Add 3 payment
        addPaymentPage.inputPaymentInfo(data);

        // Verify 3 payment
        paymentPage = homePage.gotoPaymentPage();
        paymentPage.verifyPayment(data);

    }
    @Test(description = "Test data", dataProvider = "RISE_Module2_Data", dataProviderClass = Module2Provider.class)
    public void RISE_Test_001_AddPayment_DataDriven(HashMap<String, String> data) {
        //gotoInvoicePage();
        // addLabel = 1 : Thực hiện add label - addLabel = 0 : Không thực hiện add label
        if (data.get("addLabel").equals("1")) {
            // Click [Manage labels]
            manageLabelPage = invoicesPage.gotoManageLabelPage();

            // Verify manageLabelPage
            manageLabelPage.verifyManageLabelPage();

            // Add 2 labels
            manageLabelPage.addLabel(data);

            // Verify new label Integration On [Add invoice]
            addInvoicePage = invoicesPage.gotoAddInvoicePage();
            addInvoicePage.verifyNewLabelIntegrationOnAddInvoice(data);
        }
        // Click [Add payment]
        addPaymentPage = invoicesPage.gotoAddPaymentPage();

        // Verify addPaymentPage
        addPaymentPage.verifyAddPaymentPage();

        // Add 3 payment
        addPaymentPage.inputPaymentInfo(data);

        // Verify 3 payment
        paymentPage = homePage.gotoPaymentPage();
        paymentPage.verifyPayment(data);
    }
    private void gotoInvoicePage(){
        // Click [Sale] -> Click [Invoices] Truy cập vào InvoicesPage
        invoicesPage = homePage.gotoInvoicesPage();

        // Verify truy cap InvoicePape
        invoicesPage.verifyInvoicePage();
    }
}
