package distribution.regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import distribution.library.AppLibrary;
import distribution.library.TestBase;
import distribution.pages.LoginPage;
import distribution.pages.PurchaseOrderPage;

public class PurchaseOrderTest extends TestBase {
	@DataProvider(name = "POProductDetails")
	public String[][] getAccountDetailsFromExcel() throws Exception {
		String str[][] = AppLibrary.readExcel("TestData/POProductDetails.xls");
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
		AppLibrary.sleep(4000);
	}

	@Test(dataProvider = "POProductDetails")
	public void createPO(String id, String name, String quantity, String unitPrice, String companyName,
			String licenseNumber, String term, String payType, String exeIndicator) throws Exception {
		PurchaseOrderPage pop = new PurchaseOrderPage(appLibrary);
		pop.navigateToPO(driver);
		String poNumber = pop.createPO(driver, companyName, licenseNumber, term, payType, AppLibrary.getDatePO(),
				AppLibrary.getDatePO(), name, quantity, unitPrice);

		pop.receivePO(driver, poNumber, companyName, licenseNumber, term, payType, AppLibrary.getDatePO(),
				AppLibrary.getDatePO(), name, quantity, unitPrice);
	}
}
