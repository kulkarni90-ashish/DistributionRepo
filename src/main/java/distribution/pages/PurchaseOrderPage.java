package distribution.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import distribution.library.AppLibrary;

public class PurchaseOrderPage {
	private AppLibrary appLibrary;
	private WebDriver driver;
	// public String = "xpath:";

	public String POMenu = "xpath://a[@href='/inventory/po']";
	public String addPOButton = "xpath://button[@data-title='New PO']";
	public String vendorList = "xpath://div[strong[text()='Select Vendor ']]//select";
	public String companyLicenseList = "xpath://tr[td[text()='Company License #']]//select";
	public String companyTypeField = "xpath://tr[td[text()='Company Type']]//input";
	public String shopLicenseField = "xpath://tr[td[text()='Shop License #']]//input";
	public String termList = "xpath://tr[td[text()='Term']]//select";
	public String paymentTypeList = "xpath://tr[td[text()='Payment Type']]//select";
	public String taxTypeField = "xpath://tr[td[text()='Tax Transaction Type']]//input";
	public String PODateField = "name:purchaseOrderDate";
	public String deliveryDateField = "name:deliveryDate";
	public String deliveryAddressField = "xpath://tr[td[text()='Delivery Address']]//td[2]";
	public String contactNumberField = "xpath://tr[td[text()='Contact Phone #']]//td[2]";
	public String addProductbutton = "xpath://button[text()='+ Add Products']";

	// Product Details
	public String productSearchField = "xpath://table[thead]//div[contains(@id,'react-select-')]";
	public String productQuantityField = "xpath:(//input[@placeholder='Qty'])[1]";
	public String productUnitPriceField = "xpath:(//input[@placeholder='Unit Price'])[1]";
	public String productDiscountField = "xpath:(//input[@placeholder='Discount'])[1]";
	public String productSaveButton = "xpath:(//td//button[text()='Save'])[1]";

	public String tableRow = "xpath://table[contains(@class,'table-condensed')]//tr[td[text()='index']]";
	public String rowCount = "xpath://table[contains(@class,'table-condensed')]//tbody//tr";
	public String productSubtotal = "xpath://div[span[text()='Sub Total : $']]";

	// Receiving Shipping for Purchase
	public String tbody1 = "xpath://div[@class='table-responsive']//tbody";
	public String tableRow1 = "xpath://div[@class='table-responsive']//tbody//tr";
	public String actualReceived = "xpath://div[@class='table-responsive']//tr//td[span]//input";
	public String batchStatusList = "xpath://div[@class='table-responsive']//tr//td//select";
	public String productDeclineReason = "name:productDeclineReason";
	public String productDeclinedMessage = "xpath://div[text()='success ! Declined Product successfully']";

	public String createPOButton = "xpath:(//button[text()='Create & Submit for Approval'])[1]";
	public String poSubmitSuccessMessage = "xpath://div[text()='success ! Submitted PO successfully']";

	public String filterList = "xpath://table[thead]//div[contains(@id,'react-select-')]";

	// Buttons
	public String printPOButton = "xpath://button[text()='Print']";
	public String archivePOButton = "xpath://button[text()='Archive']";
	public String declinePOButton = "xpath://button[text()='Decline']";
	public String cancelPOButton = "xpath://button[text()='Cancel PO']";
	public String moveToIPButton = "xpath://button[text()='Move to In Progress']";
	public String confirmationModal = "class:modal-content";
	public String pinInputField = "name:Pin";
	public String submitButton = "xpath://button[text()='Submit']";
	public String submitForApprovalButton = "xpath://button[text()='Submit for approval']";
	public String poApproveButton = "xpath://button[text()='Approve']";
	public String emailPOButton = "xpath://button[text()='Email PO']";
	public String editPOButton = "xpath://button[text()='Edit PO']";
	public String submitPinButton = "xpath://button[text()='Pin']";
	public String poApproveSuccessMessage = "xpath://div[text()='success ! Approved PO successfully']";
	public String submitWithoutPinFailureMsg = "xpath://div[text()='error ! Please enter pin']";
	public String enterInvalidPinFailureMsg = "xpath://div[text()='error ! Invalid pin.']";

	public String markAsWaitingbutton = "xpath://button[text()='Mark as waiting']";

	// modal message
	// submitButton
	// poSubmitSuccessMessage
	// receive Shioment
	public String receiveShipmentButton = "xpath://button[text()='Receive Shipment']";
	// poSubmitSuccessMessage
	public String acceptItemButton = "xpath://button[text()='Accept']";
	// poSubmitSuccessMessage
	public String acceptAllButton = "xpath://button[text()='Accept All']";
	public String acceptAllSuccessMessage = "xpath://div[text()='success ! Accepted all products successfully']";
	// poSubmitSuccessMessage

	public String completePOButton = "xpath://button[text()='Complete']";
	// modal message
	// completePOButton
	public String confirmationButton = "xpath://div[@class='modal-content']//button[text()='Complete']";

	// Created PO details
	public String vPONumber = "xpath://h3[@class='fg-black']";
	public String vCreatedDate = "xpath://tr[td[text()='PO Created Date']]";
	public String vStatus = "xpath://tr[td[text()='PO Status']]";
	public String vTermType = "xpath://tr[td[text()='Term']]";
	public String vTotalAmount = "xpath://tr[td[text()='Total Amount']]";
	public String vPayment = "xpath://tr[td[text()='Payment']]";
	public String vDeliveryDate = "xpath://tr[td[text()='Delivery Date']]";
	public String vPODate = "xpath://tr[td[text()='Purchase Order Date']]";
	public String vSubTotl = "xpath://tr[td[text()='Sub Total ']]";
	public String vTotalDue = "xpath://tr[td//b[text()='Total Due ']]";

	// Receiving Shipmet
	public String vReceiveShiomentHeader = "xpath://div[@class='rubix-panel-header']//h4";
	public String vreceiveShipmentDate = "xpath://div[@class='hidden-print']";
	// public String = "xpath:";
	// public String = "xpath:";
	// public String = "xpath:";
	// public String = "xpath:";

	public PurchaseOrderPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void navigateToPO(WebDriver driver) {
		AppLibrary.clickElement(driver, POMenu);
		AppLibrary.sleep(3000);
	}

	public String createPO(WebDriver driver, String vendor, String compLicense, String term, String payType,
			String poDate, String deliveryDate, String productName, String quantity, String price) throws Exception {

		AppLibrary.clickElement(driver, addPOButton);
		AppLibrary.sleep(1000);

		// Add PO details
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, vendorList), vendor);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, companyLicenseList), compLicense);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, termList), term);
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, paymentTypeList), payType);
		AppLibrary.enterText(driver, PODateField, poDate);
		AppLibrary.enterText(driver, deliveryDateField, deliveryDate);
		AppLibrary.clickElement(driver, addProductbutton);

		// Add Product
		WebElement element = AppLibrary.findElement(driver, productSearchField);
		element.click();
		selectProduct(driver, productSearchField, productName);
		AppLibrary.enterText(driver, productQuantityField, quantity);
		AppLibrary.enterText(driver, productUnitPriceField, price);
		AppLibrary.clickElement(driver, productSaveButton);
		AppLibrary.sleep(2000);
		verifyProductDetails(driver, quantity, price, productName, "po");

		// Save PO
		AppLibrary.clickElement(driver, createPOButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);
		String poNumber = AppLibrary.findElement(driver, vPONumber).getText();
		return poNumber;

	}

	public void receivePO(WebDriver driver, String poNumber, String vendor, String compLicense, String term,
			String payType, String poDate, String deliveryDate, String productName, String quantity, String price)
			throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-1000)");

		verifyPO(driver, poNumber, term, payType, poDate, deliveryDate, productName, quantity, price,
				"Required Approval");
		AppLibrary.clickElement(driver, moveToIPButton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, submitButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);
		AppLibrary.sleep(2000);

		verifyPO(driver, poNumber, term, payType, poDate, deliveryDate, productName, quantity, price, "In Progress");
		AppLibrary.clickElement(driver, submitForApprovalButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);
		AppLibrary.sleep(2000);

		// Approval Pin Validation done
		verifyPO(driver, poNumber, term, payType, poDate, deliveryDate, productName, quantity, price,
				"Required Approval");
		AppLibrary.clickElement(driver, poApproveButton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, submitPinButton);
		AppLibrary.findElement(driver, submitWithoutPinFailureMsg);
		AppLibrary.sleep(2000);
		AppLibrary.clickElement(driver, poApproveButton);
		AppLibrary.enterText(driver, pinInputField, "1234");
		AppLibrary.clickElement(driver, submitPinButton);
		AppLibrary.findElement(driver, enterInvalidPinFailureMsg);
		AppLibrary.sleep(2000);
		AppLibrary.clickElement(driver, poApproveButton);
		AppLibrary.enterText(driver, pinInputField, "0000");
		AppLibrary.clickElement(driver, submitPinButton);
		AppLibrary.findElement(driver, poApproveSuccessMessage);
		AppLibrary.sleep(2000);

		verifyPO(driver, poNumber, term, payType, poDate, deliveryDate, productName, quantity, price, "Approved");
		AppLibrary.clickElement(driver, markAsWaitingbutton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, submitButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);
		AppLibrary.sleep(2000);

		verifyPO(driver, poNumber, term, payType, poDate, deliveryDate, productName, quantity, price,
				"Waiting Shipment");
		AppLibrary.clickElement(driver, receiveShipmentButton);
		AppLibrary.findElement(driver, poSubmitSuccessMessage);
		AppLibrary.sleep(2000);

		receiveShipment(driver, quantity, quantity, productName, "RECEIVED", "PENDING", true);
		AppLibrary.clickElement(driver, completePOButton);
		AppLibrary.findElement(driver, confirmationModal);
		AppLibrary.clickElement(driver, confirmationButton);

		// Loading for long time
	}

	public void receiveShipment(WebDriver driver, String orderedQTY, String receivedQTY, String productName,
			String batchStatus, String poStatus, Boolean flag) {
		System.out.println(AppLibrary.findElement(driver, tableRow1).getText());
		Assert.assertTrue(AppLibrary.findElement(driver, tableRow1).getText().contains(productName));
		Assert.assertTrue(AppLibrary.findElement(driver, tableRow1).getText().contains(orderedQTY + " units"));
		Assert.assertTrue(AppLibrary.findElement(driver, tableRow1).getText().contains(poStatus));
		Assert.assertTrue(
				AppLibrary.findElement(driver, actualReceived).getAttribute("value").contains(receivedQTY + ".00"));
		AppLibrary.selectByPartOfVisibleText(AppLibrary.findElement(driver, batchStatusList), batchStatus);

		if (flag) {
			AppLibrary.clickElement(driver, acceptItemButton);
			AppLibrary.findElement(driver, poSubmitSuccessMessage);
			AppLibrary.sleep(2000);
			AppLibrary.clickElement(driver, acceptAllButton);
			AppLibrary.findElement(driver, acceptAllSuccessMessage);
			AppLibrary.sleep(2000);
		} else {
			AppLibrary.clickElement(driver, declinePOButton);
			AppLibrary.findElement(driver, confirmationModal);
			AppLibrary.enterText(driver, productDeclineReason, "Yes, I want to decline.");
			AppLibrary.findElement(driver, productDeclinedMessage);
			AppLibrary.sleep(2000);
		}
	}

	public void verifyPO(WebDriver driver, String poNumber, String term, String payType, String poDate,
			String deliveryDate, String productName, String quantity, String price, String status) {
		// Print PO number and Verify PO Info

		System.out.println(AppLibrary.findElement(driver, vPONumber).getText());
		Assert.assertTrue(AppLibrary.findElement(driver, vPONumber).getText().contains(poNumber));

		String todaysDate = AppLibrary.getDatePO();
		Assert.assertTrue(AppLibrary.findElement(driver, vCreatedDate).getText().contains(todaysDate));
		Assert.assertTrue(AppLibrary.findElement(driver, vStatus).getText().contains(status));
		Assert.assertTrue(AppLibrary.findElement(driver, vTermType).getText().contains(term));
		Assert.assertTrue(
				AppLibrary.findElement(driver, vTotalAmount).getText().contains(totalQuantity(quantity, price)));
		Assert.assertTrue(AppLibrary.findElement(driver, vPayment).getText().contains(payType));
		Assert.assertTrue(AppLibrary.findElement(driver, vDeliveryDate).getText().contains(deliveryDate));
		Assert.assertTrue(AppLibrary.findElement(driver, vPODate).getText().contains(poDate));
		Assert.assertTrue(AppLibrary.findElement(driver, vSubTotl).getText().contains(totalQuantity(quantity, price)));
		Assert.assertTrue(AppLibrary.findElement(driver, vTotalDue).getText().contains(totalQuantity(quantity, price)));
		AppLibrary.findElement(driver, printPOButton);
		AppLibrary.findElement(driver, archivePOButton);
		AppLibrary.findElement(driver, cancelPOButton);
		if (status.contains("Required Approval")) {
			AppLibrary.findElement(driver, declinePOButton);
			AppLibrary.findElement(driver, moveToIPButton);
			AppLibrary.findElement(driver, poApproveButton);
		} else if (status.contains("In Progress")) {
			AppLibrary.findElement(driver, editPOButton);
			AppLibrary.findElement(driver, submitForApprovalButton);
		} else if (status.contains("Approved")) {
			AppLibrary.findElement(driver, emailPOButton);
			AppLibrary.findElement(driver, moveToIPButton);
			AppLibrary.findElement(driver, markAsWaitingbutton);
		} else if (status.contains("Waiting Shipment")) {
			AppLibrary.findElement(driver, emailPOButton);
			AppLibrary.findElement(driver, receiveShipmentButton);
		}
	}

	public void selectProduct(WebDriver driver, String locator, String productName) throws Exception {

		String filterCriteria = productName.substring(0, 6);
		System.out.println(filterCriteria + "     " + productName);

		AppLibrary.clickByJavascript(driver, locator);
		driver.findElement(By.xpath("//div//input[contains(@aria-owns,'react-select')]")).sendKeys(filterCriteria);

		AppLibrary.sleep(2000);

		boolean flag = true;
		List<WebElement> all = AppLibrary.findElements(driver, filterList);
		for (WebElement element : all) {
			System.out.println(element.getText());
			if (element.getText().contains(filterCriteria)) {
				if (element.getText().equalsIgnoreCase(productName)) {
					element.click();
					flag = false;
					break;
				}
			}
		}

		if (flag) {
			Assert.assertTrue(false, "Product filter list item was not found: " + productName);
		}
	}

	public String totalQuantity(String quantity, String price) {
		int q = Integer.parseInt(quantity);
		int p = Integer.parseInt(price);
		int total = p * q;

		String s = String.valueOf(total);
		return s;
	}

	public void verifyProductDetails(WebDriver driver, String quantity, String price, String productName,
			String input) {
		List<WebElement> rows = AppLibrary.findElements(driver, rowCount);
		int count = rows.size();
		System.out.println(count);

		for (int i = 1; i <= count; i++) {
			String ind = String.valueOf(i);
			System.out.println(AppLibrary.findElement(driver, tableRow.replace("index", ind)).getText());
			Assert.assertTrue(
					AppLibrary.findElement(driver, tableRow.replace("index", ind)).getText().contains(productName));
			Assert.assertTrue(AppLibrary.findElement(driver, tableRow.replace("index", ind)).getText()
					.contains(quantity + " units"));
			Assert.assertTrue(
					AppLibrary.findElement(driver, tableRow.replace("index", ind)).getText().contains("$" + price));
			Assert.assertTrue(AppLibrary.findElement(driver, tableRow.replace("index", ind)).getText()
					.contains("$" + totalQuantity(quantity, price)));
			if(input.contains("po"))
			Assert.assertTrue(
					AppLibrary.findElement(driver, productSubtotal).getText().contains(totalQuantity(quantity, price)));
			else
				Assert.assertTrue(
						AppLibrary.findElement(driver, tableRow.replace("index", ind)).getText().contains(totalQuantity(quantity, price)));
		}
	}
}
