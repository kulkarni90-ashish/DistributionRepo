package distribution.pages;

import org.openqa.selenium.WebDriver;

import distribution.library.AppLibrary;

public class LandingPage {

	private AppLibrary appLibrary;
	private WebDriver driver;

	public static String invitationButton = "xpath://li[contains(text(),'Invitations')]";
	public static String invitationLabel = "xpath://div[contains(@class,'ant-card-head-title ')][contains(text(),'Invitations')]";
	public static String inviteMemberButton = "xpath://button[span[contains(text(),'Invite Member')]]";
	public static String pendingInvitationButton = "xpath://button[span[contains(text(),'Pending invitations')]]";
	public static String firstNameLabel = "xpath://div[text()='First Name']";
	public static String lastNameLabel = "xpath://div[text()='Last name']";
	public static String emailLabel = "xpath://nz-card[div[div[div[text()='Invitations']]]]//div[text()='Email']";
	public static String roleLabel = "xpath://div[text()='Role']";
	

	public LandingPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	public LandingPage verifyData() {
		
		return new LandingPage(appLibrary);
		
	}

}
