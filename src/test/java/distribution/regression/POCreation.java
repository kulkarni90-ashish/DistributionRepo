package distribution.regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import distribution.library.AppLibrary;
import distribution.library.TestBase;
import distribution.pages.LoginPage;
import distribution.pages.PurchaseOrderPage;

public class POCreation extends TestBase {
	@DataProvider(name = "AccountDetails")
	public String[][] getAccountDetailsFromExcel() throws Exception {
		String str[][] = AppLibrary.readExcel("TestData/CompanyDetails.xls");
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

	@Test(dataProvider = "AccountDetails")
	public void createCompany(String id, String companyName, String firstName, String licenseNumber, String lastName,
			String entityTaxType, String phone, String vendorType, String licenceType, String companyType,
			String website, String fax, String vendorAddress, String vendorCity, String vendorZipCode,
			String vendorState, String exeIndicator) throws Exception {

		PurchaseOrderPage pop = new PurchaseOrderPage(appLibrary);

		pop.navigateToPO(driver);
		pop.createPO(driver, companyName, licenseNumber, "Net 30", "Cash", "11/06/2019", "11/08/2019",
				"NC_Panda Key Box Battery", "10", "80");

		pop.receivePO(driver);

	}

}
