package distribution.beans;

public class personalDetailsBean {

	public String profileName = "";
	public String title = "";
	public String overview = "";
	public String hourlyRate = "";
	public String country = "";

	public void setProfileBean(String profileNameVal, String titleVal, String overviewVal, String hourlyRateVal,
			String countryVal) {

		setProfileName(profileNameVal);
		setTitle(titleVal);
		setOverview(overviewVal);
		setHourlyRate(hourlyRateVal);
		setCountry(countryVal);
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}