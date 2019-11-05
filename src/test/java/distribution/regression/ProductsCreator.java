package distribution.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import distribution.library.AppLibrary;
import distribution.library.TestBase;
import distribution.pages.LoginPage;

public class ProductsCreator extends TestBase {

	@DataProvider(name = "ProductDetails")
	public String[][] getAccountDetailsFromExcel() throws Exception {
		String str[][] = AppLibrary.readExcel("TestData/ProductDetails.xls");
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

	@Test(dataProvider = "ProductDetails")
	public void createProducts(String id, String name, String categoryId, String unitPrice, String flowerType,
			String cannabisType, String sku, String vendor, String productSaleType, String description,
			String exeIndicator) throws Exception {

		driver.get(appLibrary.getBaseUrl() + "/products");
		AppLibrary.sleep(2000);
		AppLibrary.clickElement(driver, "xpath://a[@data-title='Add Product']");
		AppLibrary.sleep(2000);

		AppLibrary.enterText(driver, "id:name", name);
		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:categoryId"), categoryId);

		if (categoryId != "5da08c2dabe192428dc06ff5") {
			AppLibrary.enterText(driver, "id:unitPrice", unitPrice);
		}

		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:flowerType"), flowerType);
		AppLibrary.selectElement(AppLibrary.findElement(driver, "id:cannabisType"), cannabisType);

		AppLibrary.enterText(driver, "name:sku", sku + AppLibrary.getFDate());

		AppLibrary.clickElement(driver, "xpath://div[label[@for='vendorId']]");
		AppLibrary.enterText(driver, "xpath://div[label[@for='vendorId']]//input", vendor);
		AppLibrary.clickElement(driver,
				"xpath://div[label[@for='vendorId']]//div[@class='Select-option'][text()='" + vendor + "']");

		AppLibrary.selectElement(AppLibrary.findElement(driver, "name:productSaleType"), productSaleType);

		AppLibrary.enterText(driver, "id:description", description);

		AppLibrary.clickElement(driver, "xpath://button[text()='Save']");
		AppLibrary.findElement(driver, "xpath://*[contains(text(),'Product added successfully')]");
		AppLibrary.sleep(5000);
	}

	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
