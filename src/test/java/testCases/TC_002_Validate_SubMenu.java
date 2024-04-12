package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.Base_Class;

public class TC_002_Validate_SubMenu extends Base_Class{
	
	@Test
	public void test_002() throws InterruptedException
	{
		logger.debug(".......Application Logs Started for TC_002.......");
		logger.info("**** Start TC_002_Validate_SubMenu ****");
		try
		{
			navigate_to();
			logger.info("Navigated");
			System.out.println("------------From Living, retrieve all items under any one of sub-menu items-----------");
			hp.click_living();
			logger.info("Clicked Living");
			for(int item = 0;item<hp.living_storage_elements().size();item++)
			{
				System.out.println(hp.living_storage_ele_name(item));
			}
			logger.info("Displayed Living Contents");
			Thread.sleep(2000);
			System.out.println("-----------------Gift cards--------------------");
			logger.debug(".......Application Logs Ended for TC_002.......");
			logger.info("**** End TC_002_Validate_SubMenu ****");
			Assert.assertTrue(true);
		}
		catch(Exception e)
		{
			logger.error("TC002 Test Failed..");
			logger.debug(".......Application Logs Ended for TC_002.......");
			logger.info("**** End TC_002_Validate_SubMenu ****");
			Assert.fail("An exception occurred: " + e.getMessage());
		}
	}
}
