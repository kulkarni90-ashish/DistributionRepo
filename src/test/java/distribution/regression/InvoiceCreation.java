package distribution.regression;

import java.time.LocalDateTime;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import distribution.library.AppLibrary;
import distribution.library.TestBase;
import distribution.pages.InvoicePage;
import distribution.pages.LoginPage;

public class InvoiceCreation extends TestBase {

	@DataProvider(name = "AccountDetails")
	public String[][] getAccountDetailsFromExcel() throws Exception {
		String str[][] = AppLibrary.readExcel("TestData/PO_INV_Products.xls");
		return str;
	}

	@BeforeClass
	public void setUp() throws Exception {
			
		appLibrary = new AppLibrary();
		System.out.println("TEST STARTS");
		driver = appLibrary.getDriverInstance();
		appLibrary.launchApp("");
		LoginPage lp = new LoginPage(appLibrary);
		lp.Login("thinktiive_testing@mailinator.com", "test");
		// AppLibrary.findElement(driver,
		// "xpath://div[contains(@class,'listcompaniesgroup')]//span[@class='list-group-item']");
		AppLibrary.sleep(4000);
	}

	@Test(dataProvider = "AccountDetails")
	public void createCompany(String id, String product, String quantity, String unitPrice, String companyName,
			String licenseNumber, String term, String payType, String exeIndicator) throws Exception {

		InvoicePage ip = new InvoicePage(appLibrary);
		ip.navigateToInvoice(driver);
		ip.createInvoice(driver, companyName, licenseNumber, term, payType, "Michael Brown", product, quantity, unitPrice);

	}
}
