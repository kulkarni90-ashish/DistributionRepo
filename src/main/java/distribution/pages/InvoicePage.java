package distribution.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import distribution.library.AppLibrary;

public class InvoicePage {

	private AppLibrary appLibrary;
	private WebDriver driver;
	// public static String = "xpath:";

	public static String InvoiceMenu = "xpath://a[@href='/invoices']";

	public static String addInvoiceButton = "xpath://a[@href='/add-invoices']";
	public static String companyNameList = "xpath://div[label[text()='Company Name *']]//select";
	public static String contactList = "xpath://div[label[text()='Contact']]//select";
	public static String licenseList = "xpath://div[label[text()='License']]//select";
	public static String salespersonList = "xpath://div[label[text()='Salesperson']]//select";
	public static String termsList = "xpath://div[label[text()='Terms']]//select";
	public static String invoiceSuccessMessage = "xpath://div[text()='success ! Invoice added successfully.']";

	public static String productSaveButton = "xpath://td//button[text()='Save']";
	
	//Shipping Manifest Creation
	
	public static String shippingManifesttab = "id:panel-body-header-tab-footer-tab-shippingmanifest";
	public static String createManifestButton = "xpath://button[text()='Create Manifest']";
	
	public static String companyList= "xpath://select[@name='customerCompanyId']";
	
	// public static String = "xpath:";

	public InvoicePage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void navigateToInvoice(WebDriver driver) {
		AppLibrary.clickElement(driver, InvoiceMenu);
		AppLibrary.sleep(3000);
	}

	public void createInvoice(WebDriver driver, String vendor, String term, String license, String sperson,
			String productName, String qty, String price) throws Exception {
		AppLibrary.clickElement(driver, addInvoiceButton);
		AppLibrary.sleep(3000);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, companyNameList), vendor);
		// AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver,
		// contactList), contact);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, licenseList), license);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, salespersonList), sperson);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, termsList), term);
		AppLibrary.clickElement(driver, PurchaseOrderPage.addProductbutton);
		WebElement element = AppLibrary.findElement(driver, PurchaseOrderPage.productSearchField);
		element.click();

		PurchaseOrderPage.selectProduct(driver, PurchaseOrderPage.productSearchField, productName);
		AppLibrary.enterText(driver, PurchaseOrderPage.productQuantityField, qty);
		AppLibrary.enterText(driver, PurchaseOrderPage.productUnitPriceField, price);
		AppLibrary.clickElement(driver, productSaveButton);
		AppLibrary.clickElement(driver, PurchaseOrderPage.productSaveButton);
		AppLibrary.findElement(driver, invoiceSuccessMessage);

		AppLibrary.sleep(10000);
	}

}
