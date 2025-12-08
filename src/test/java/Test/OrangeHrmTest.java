package Test;

import org.testng.annotations.Test;

import OrangeHrmPage.LeavePage;
import OrangeHrmPage.Login;
import base.BaseTest;

public class OrangeHrmTest extends BaseTest {
	
	@Test(priority = 1)
	public void LoginWithCredentials() {
		Login login=new Login(driver);
		login.enterUser();
		login.enterPass();
		login.submit();
		//login.isAdminPanelVisible();
	}
	
	@Test(priority =2 )
	public void LeaveCheck() throws InterruptedException {
		LeavePage leave=new LeavePage(driver);
		leave.clickLeave();
		leave.clickMyLeave();
		leave.selectFromDate("2017","January","12");
		//leave.selectToDate("2023", "December", "24");
		leave.selectLeaveStatus("Rejected");
		Thread.sleep(10000);
	}
	

}
