package distribution.regression;

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
		// AppLibrary.findElement(driver,
		// "xpath://div[contains(@class,'listcompaniesgroup')]//span[@class='list-group-item']");
		AppLibrary.sleep(4000);
	}

	@Test(dataProvider = "AccountDetails")
	public void createCompany(String id, String companyName, String firstName, String licenseNumber, String lastName,
			String entityTaxType, String phone, String vendorType, String licenceType, String companyType,
			String website, String fax, String vendorAddress, String vendorCity, String vendorZipCode,
			String vendorState, String exeIndicator) throws Exception {

		InvoicePage ip = new InvoicePage(appLibrary);

		ip.navigateToInvoice(driver);

		ip.createInvoice(driver, "THC Labs LLC", "Net 30", "Lic_MH31NBOSCO", "Michael Brown", "NC_Panda Key Box Battery", "10", "10");

	}

}
