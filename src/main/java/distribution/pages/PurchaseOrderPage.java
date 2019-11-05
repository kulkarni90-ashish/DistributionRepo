package distribution.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import distribution.library.AppLibrary;

public class PurchaseOrderPage {
	private AppLibrary appLibrary;
	private WebDriver driver;
	// public static String = "xpath:";

	public static String POMenu = "xpath://a[@href='/inventory/po']";
	public static String addPOButton = "xpath://button[@data-title='New PO']";
	public static String vendorList = "xpath://div[strong[text()='Select Vendor ']]//select";
	public static String companyLicenseList = "xpath://tr[td[text()='Company License #']]//select";
	public static String companyTypeField = "xpath://tr[td[text()='Company Type']]//input";
	public static String shopLicenseField = "xpath://tr[td[text()='Shop License #']]//input";
	public static String termList = "xpath://tr[td[text()='Term']]//select";
	public static String paymentTypeList = "xpath://tr[td[text()='Payment Type']]//select";
	public static String taxTypeField = "xpath://tr[td[text()='Tax Transaction Type']]//input";
	public static String PODateField = "name:purchaseOrderDate";
	public static String deliveryDateField = "name:deliveryDate";
	public static String deliveryAddressField = "xpath://tr[td[text()='Delivery Address']]//td[2]";
	public static String contactNumberField = "xpath://tr[td[text()='Contact Phone #']]//td[2]";
	public static String addProductbutton = "xpath://button[text()='+ Add Products']";

	public static String productSearchField = "xpath://table[thead]//div[contains(@id,'react-select-')]";
	public static String productQuantityField = "xpath:(//input[@placeholder='Qty'])[1]";
	public static String productUnitPriceField = "xpath:(//input[@placeholder='Unit Price'])[1]";
	public static String productDiscountField = "xpath:(//input[@placeholder='Discount'])[1]";
	public static String productSaveButton = "xpath:(//button[text()='Save'])[1]";
	public static String createPOButton = "xpath:(//button[text()='Create & Submit for Approval'])[1]";
	public static String poSubmitSuccessMessage = "xpath://div[text()='success ! Submitted PO successfully']";

	public static String filterList = "xpath://table[thead]//div[contains(@id,'react-select-')]";

	public static String moveToIPButton = "xpath://button[text()='Move to In Progress']";
	public static String confirmationModal = "class:modal-content";
	public static String submitButton = "xpath://button[text()='Submit']";
	// Successmessage
	public static String submitForApprovalButton = "xpath://button[text()='Submit for approval']";
	// Successmessage
	public static String approveButton = "xpath://button[text()='Approve']";
	// confirmationModal
	public static String submitPinButton = "xpath://button[text()='Pin']";

	public static String poApproveSuccessMessage = "xpath://div[text()='success ! Approved PO successfully']";
	public static String markAsWaitingbutton = "xpath://button[text()='Mark as waiting']";
	// modal message
	// submitButton
	// poSubmitSuccessMessage
	// receive Shioment
	public static String receiveShipmentButton = "xpath://button[text()='Receive Shipment']";
	// poSubmitSuccessMessage

	public static String acceptItemButton = "xpath://button[text()='Accept']";
	// poSubmitSuccessMessage
	public static String acceptAllButton = "xpath://button[text()='Accept All']";
	public static String acceptAllSuccessMessage = "xpath://div[text()='success ! Accepted all products successfully']";
	// poSubmitSuccessMessage

	public static String completePOButton = "xpath://button[text()='Complete']";
	// modal message
	// completePOButton
	public static String confirmationButton = "xpath://div[@class='modal-content']//button[text()='Complete']";

	public PurchaseOrderPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void navigateToPO(WebDriver driver) {
		AppLibrary.clickElement(driver, POMenu);
		AppLibrary.sleep(3000);
	}

	public void createPO(WebDriver driver, String vendor, String compLicense, String term, String payType,
			String poDate, String deliveryDate, String productName, String quantity, String price) throws Exception {
		AppLibrary.clickElement(driver, addPOButton);
		AppLibrary.sleep(3000);

		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, vendorList), vendor);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, companyLicenseList), compLicense);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, termList), term);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, paymentTypeList), payType);
		AppLibrary.enterText(driver, PODateField, poDate);
		AppLibrary.enterText(driver, deliveryDateField, deliveryDate);
		AppLibrary.clickElement(driver, addProductbutton);

		WebElement element = AppLibrary.findElement(driver, productSearchField);
		element.click();

		selectProduct(driver, productSearchField, productName);
		AppLibrary.enterText(driver, productQuantityField, quantity);
		AppLibrary.enterText(driver, productUnitPriceField, price);

		AppLibrary.clickElement(driver, productSaveButton);
		AppLibrary.sleep(2000);
		AppLibrary.clickElement(driver, createPOButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);
	}

	public void receivePO(WebDriver driver) throws Exception{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-1000)");

		AppLibrary.clickElement(driver, moveToIPButton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, submitButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);

		AppLibrary.clickElement(driver, submitForApprovalButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);

		AppLibrary.clickElement(driver, approveButton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, submitPinButton);
		AppLibrary.findElement(driver, poApproveSuccessMessage);

		AppLibrary.clickElement(driver, markAsWaitingbutton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, submitButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);

		AppLibrary.clickElement(driver, receiveShipmentButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);

		AppLibrary.clickElement(driver, acceptItemButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);

		AppLibrary.clickElement(driver, acceptAllButton);
		AppLibrary.findElement(driver, acceptAllSuccessMessage);

		AppLibrary.clickElement(driver, completePOButton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, confirmationButton);
		
		//Loading for long time

	}

	public static void selectProduct(WebDriver driver, String locator, String speciality) throws Exception {

		String filterCriteria = speciality.substring(0, 2);
		System.out.println(filterCriteria + "     " + speciality);

		AppLibrary.clickByJavascript(driver, locator);
		AppLibrary.enterText(driver, locator, filterCriteria);

		AppLibrary.sleep(3000);

		boolean flag = true;
		List<WebElement> all = AppLibrary.findElements(driver, filterList);
		for (WebElement element : all) {
			// System.out.println(element.getText());
			if (element.getText().contains(speciality) || element.getText().equalsIgnoreCase(speciality)) {
				element.click();
				flag = false;
				break;
			}
		}

		if (flag) {
			Assert.assertTrue(false, "Speciality filter list item was not found: " + speciality);
		}

	}

}
