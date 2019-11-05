package distribution.pages;

import org.openqa.selenium.WebDriver;

import distribution.library.AppLibrary;

public class LoginPage {

	private AppLibrary appLibrary;
	private WebDriver driver;

	public static String email = "id:emailaddress";
	public static String pass = "id:password";
	public static String loginButton = "xpath://a[text()='Login']";

	public LoginPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public void Login(String userNameVal, String passVal) {

		AppLibrary.enterText(driver, email, userNameVal);
		AppLibrary.enterText(driver, pass, passVal);
		AppLibrary.clickElement(driver, loginButton);
	}

}
