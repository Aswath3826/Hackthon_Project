package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.Base_Class;
import utilities.Excel_Utilities;
import utilities.dataProvider;

public class TC_003_Validate_Gift_Cards extends Base_Class{
	
	@Test(dataProvider="form_to_data",dataProviderClass=dataProvider.class)
	public void test_003(String r_name,String r_email,String r_mobile,String c_name,String c_email, String c_mobile, String c_address, String pincode, String exp_result,String row) throws InterruptedException, IOException
	{
		logger.debug(".......Application Logs Started for TC_003.......");
		logger.info("**** Start TC_003_Validate_Gift_Cards ****");
		int flag=0;
		Excel_Utilities excel = new Excel_Utilities("C:\\Users\\2318646\\eclipse-workspace\\Hackathon_Project\\testData\\HackathonData.xlsx");
		int int_row = Integer.parseInt(row);
		try
		{			
			navigate_to();
			logger.info("Navigated");
			hp.click_gift_cards();
			logger.info("Clicked Gift Cards");
			gp.click_bday_an();
			logger.info("Clicked Birthday Anniversary");
			gp.click_rupees();
			logger.info("Set Rupees");
			gp.click_custom_next();
			logger.info("Clicked Next");
			gp.recipient_name_ele().sendKeys(r_name);
			logger.info("Set Recipient Name Value");
			gp.recipient_email_ele().sendKeys(r_email);
			logger.info("Set Recipient Email Value");
			gp.recipient_mobile_number_ele().sendKeys(r_mobile);
			logger.info("Set Recipient Mobile Number Value");
			gp.customer_name_ele().sendKeys(c_name);
			logger.info("Set Customer Name Value");
			gp.customer_email_ele().sendKeys(c_email);
			logger.info("Set Customer Email Value");
			gp.customer_mobile_number_ele().sendKeys(c_mobile);
			logger.info("Set Customer Mobile Value");
			gp.customer_address_ele().sendKeys(c_address);
			logger.info("Set Customer Address Value");
			gp.zipcode_ele().sendKeys(pincode);
			logger.info("Set Zipcode Value");
			Thread.sleep(1000);
			gp.click_submit_btn();
			logger.info("Clicked Submit");
			Thread.sleep(1000);
			flag=1;
			gp.details_confirm_sec_ele();
			if(exp_result.equalsIgnoreCase("pass"))
			{
				System.out.println("Info Valid = "+r_name);
				excel.setCellData("Sheet1", int_row, 9,"Valid");
				logger.debug(".......Application Logs Ended for TC_003.......");
				logger.info("**** End TC_003_Validate_Gift_Cards ****");
				Assert.assertTrue(true);
			}
		}		
		catch(Exception e)
		{
			String msg = "";
			if(flag==1)
			{
				System.out.println("Info Invalid = "+r_name);
				excel.setCellData("Sheet1", int_row, 9,"Invalid");
				captureScreen(r_name);
				msg = "Section Element Not found: " + e.getMessage();
			}
			else
			{
				msg = "An exception occurred: " + e.getMessage();
			}
			logger.error("TC003 Test Failed..");
			logger.debug(".......Application Logs Ended for TC_003.......");
			logger.info("**** End TC_003_Validate_Gift_Cards ****");
			Assert.fail(msg);
		}
	}
}
