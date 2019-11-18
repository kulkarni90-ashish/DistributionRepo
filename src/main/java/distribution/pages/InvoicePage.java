package distribution.pages;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import distribution.library.AppLibrary;

public class InvoicePage {

	private AppLibrary appLibrary;
	private WebDriver driver;
	// public static String = "xpath:";

	public String InvoiceMenu = "xpath://a[@href='/invoices']";
	public String addInvoiceButton = "xpath://a[@href='/add-invoices']";
	public String companyNameList = "xpath://div[label[text()='Company Name *']]//select";
	public String contactList = "xpath://div[label[text()='Contact']]//select";
	public String licenseList = "xpath://div[label[text()='License']]//select";
	public String salespersonList = "xpath://div[label[text()='Salesperson']]//select";
	public String termsList = "xpath://div[label[text()='Terms']]//select";
	public String invoiceSuccessMessage = "xpath://div[text()='success ! Invoice added successfully.']";

	public String defaultPayType = "id:invoiceDiscountType";
	public String productSaveButton = "xpath://td//button[text()='Save']";
	public String invoiceSave = "xpath://div//button[text()='Save']";

	public String vInvInList = "xpath:(//span//div[@class='headtitle'])[1]";
	public String vInvStatus = "xpath://span//b";
	public String vInvId = "xpath://p[b]";
	public String vDueBalance = "xpath:(//div[h2]//div)[2]";
	public String vCompName = "xpath:(//div[div[text()='Bill to:']]//p)[1]";
	public String vInvDate = "xpath://p[span[text()='Invoice Date:']]";
	public String vInvDueDate = "xpath://p[span[text()='Due Date:']]";
	public String rowCount = "xpath://table[@class='table table-condensed table-hover']//tbody//tr";
	public String rowData = "xpath:(//table[@class='table table-condensed table-hover']//tbody//tr)[index]";
	public String vSubTotal = "xpath://tr[td[text()='Sub Total ']]";
	public String vTotal = "xpath://tr[td[text()='Total ']]";
	public String vBalanceDue = "xpath://tr[td[text()='Balance Due ']]";

	// Shipping Manifest Creation
	public String shippingManifesttab = "id:panel-body-header-tab-footer-tab-shippingmanifest";
	public String createManifestButton = "xpath://button[text()='Create Manifest']";
	public String companyList = "xpath://select[@name='customerCompanyId']";

	// public String = "xpath:";

	public InvoicePage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void navigateToInvoice(WebDriver driver) {
		AppLibrary.clickElement(driver, InvoiceMenu);
		AppLibrary.sleep(3000);
	}

	public void createInvoice(WebDriver driver, String compName, String license, String term, String payType,
			String sperson, String productName, String qty, String price) throws Exception {
		PurchaseOrderPage pop = new PurchaseOrderPage(appLibrary);
		AppLibrary.clickElement(driver, addInvoiceButton);
		AppLibrary.sleep(3000);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, companyNameList), compName);
		// AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver,
		// contactList), contact);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, licenseList), license);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, salespersonList), sperson);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, termsList), term);
		AppLibrary.clickElement(driver, pop.addProductbutton);
		WebElement element = AppLibrary.findElement(driver, pop.productSearchField);
		element.click();

		pop.selectProduct(driver, pop.productSearchField, productName);
		AppLibrary.enterText(driver, pop.productQuantityField, qty);
		AppLibrary.enterText(driver, pop.productUnitPriceField, price);
		AppLibrary.clickByJavascript(driver, productSaveButton);
		pop.verifyProductDetails(driver, qty, price, productName, "inv");
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, defaultPayType), payType);
		AppLibrary.clickByJavascript(driver, invoiceSave);
		driver.navigate().refresh();
		AppLibrary.clickElement(driver, vInvInList);
		// JavascriptExecutor jse = (JavascriptExecutor)driver;
		// jse.executeScript("window.scrollBy(0,-500)");
		AppLibrary.sleep(3000);
		String invId = verifyInvoiceDetails(driver, compName, qty, price, productName, "0.7", "UNPAID/IN_PROGRESS");
	}

	public void createShippingManifest(String invId) {
		AppLibrary.clickElement(driver, shippingManifesttab);
		AppLibrary.clickElement(driver, createManifestButton);

	}

	public String verifyInvoiceDetails(WebDriver driver, String compName, String quantity, String price,
			String productName, String eTax, String status) {
		PurchaseOrderPage pop = new PurchaseOrderPage(appLibrary);
		List<WebElement> rows = AppLibrary.findElements(driver, rowCount);
		int count = rows.size();
		System.out.println(count);

		Assert.assertTrue(AppLibrary.findElement(driver, vInvInList).getText().contains(compName));
		Assert.assertTrue(AppLibrary.findElement(driver, vInvStatus).getText().contains(status));		
		String invId= AppLibrary.findElement(driver, vInvId).getText();
		Assert.assertTrue(AppLibrary.findElement(driver, vDueBalance).getText().contains(pop.totalQuantity(quantity, price)));
		Assert.assertTrue(AppLibrary.findElement(driver, vCompName).getText().contains(compName));
		Assert.assertTrue(AppLibrary.findElement(driver, vInvDate).getText().contains(AppLibrary.getDatePO()));
		
//		Tomorrows Date specification and verification
//		Assert.assertTrue(AppLibrary.findElement(driver, vCompName).getText().contains(compName));

		int subTotal = 0;
		for (int i = 1; i <= count; i++) {
			String ind = String.valueOf(i);
			System.out.println(AppLibrary.findElement(driver, rowData.replace("index", ind)).getText());
			Assert.assertTrue(
					AppLibrary.findElement(driver, rowData.replace("index", ind)).getText().contains(productName));
			Assert.assertTrue(
					AppLibrary.findElement(driver, rowData.replace("index", ind)).getText().contains(quantity));
			Assert.assertTrue(AppLibrary.findElement(driver, rowData.replace("index", ind)).getText().contains(price));
			// Assert.assertTrue(AppLibrary.findElement(driver, rowData.replace("index",
			// ind)).getText().contains(eTax));
			Assert.assertTrue(AppLibrary.findElement(driver, rowData.replace("index", ind)).getText()
					.contains(pop.totalQuantity(quantity, price)));

			subTotal = subTotal + Integer.parseInt(pop.totalQuantity(quantity, price));
		}
		Assert.assertTrue(AppLibrary.findElement(driver, vSubTotal).getText().contains(String.valueOf(subTotal)));
		Assert.assertTrue(AppLibrary.findElement(driver, vTotal).getText().contains(String.valueOf(subTotal)));
		Assert.assertTrue(AppLibrary.findElement(driver, vBalanceDue).getText().contains(String.valueOf(subTotal)));
		return invId;
	}
}
