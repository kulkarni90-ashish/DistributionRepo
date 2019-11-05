package distribution.pages;

import org.openqa.selenium.WebDriver;
import distribution.library.AppLibrary;

public class CompanyPage {
	
	private AppLibrary appLibrary;
	private WebDriver driver;
	//public static String  = "xpath:";
	
	
	
	public static String companymenu = "xpath:xpath://a[@href='/companies']";
	public static String createCompanyButton = "xpath:xpath://button[@data-title='Create company']";
	
	public static String companylabel = "xpath://label[text()='Company Name *']";
	public static String companyNameField = "id:name";
	
}
