package distribution.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import distribution.library.AppLibrary;
import distribution.library.TestBase;
import distribution.pages.LoginPage;

public class CompaniesCreator extends TestBase {

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
//		AppLibrary.findElement(driver,
//				"xpath://div[contains(@class,'listcompaniesgroup')]//span[@class='list-group-item']");
		AppLibrary.sleep(4000);
	}

	@Test(dataProvider = "AccountDetails")
	public void createCompany(String id, String companyName, String firstName, String licenseNumber, String lastName,
			String entityTaxType, String phone, String vendorType, String licenceType, String companyType,
			String website, String fax, String vendorAddress, String vendorCity, String vendorZipCode,
			String vendorState, String exeIndicator) throws Exception {

		String email = firstName + AppLibrary.getFDate() + "@mailiantor.com";

		AppLibrary.clickElement(driver, "xpath://a[@href='/companies']");
		AppLibrary.sleep(2000);
		AppLibrary.clickElement(driver, "xpath://button[@data-title='Create company']");
		AppLibrary.sleep(2000);

		AppLibrary.enterText(driver, "id:name", companyName);

		AppLibrary.enterText(driver, "id:licenseNumber", licenseNumber);
		AppLibrary.enterText(driver, "id:firstName", firstName);
		AppLibrary.enterText(driver, "id:lastName", lastName);
		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:armsLengthType"), entityTaxType);

		AppLibrary.enterText(driver, "name:phone", phone);
		AppLibrary.enterText(driver, "name:website", website);

		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:vendorType"), vendorType);

		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:licenceType"), licenceType);
		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:companyType"), companyType);
		AppLibrary.enterText(driver, "id:email", email);

		AppLibrary.enterText(driver, "id:fax", fax);
		AppLibrary.enterText(driver, "id:vendorAddress", vendorAddress);
		AppLibrary.enterText(driver, "id:vendorCity", vendorCity);
		AppLibrary.enterText(driver, "id:vendorZipCode", vendorZipCode);

		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:vendorState"), "Oklahoma");
		AppLibrary.clickElement(driver, "xpath://button[text()='Save']");
		AppLibrary.findElement(driver, "xpath://*[contains(text(),'Company added successfully')]");
		AppLibrary.sleep(5000);
	}

	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
