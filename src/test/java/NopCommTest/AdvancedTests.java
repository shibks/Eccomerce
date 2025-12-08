package NopCommTest;

import org.testng.annotations.Test;

import NopCommPage.ModelPopupPage;
import base.BaseTest;

public class AdvancedTests extends BaseTest{

	@Test
	public void LoginMethod() {
		driver.get("https://sweetalert2.github.io/");
		ModelPopupPage page=new ModelPopupPage();
		page.clickLogin();
		page.closePopup();
	}
	
}
