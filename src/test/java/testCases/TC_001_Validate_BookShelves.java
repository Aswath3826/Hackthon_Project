package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.Base_Class;

public class TC_001_Validate_BookShelves extends Base_Class{
	
	@Test
	public void test_001() throws InterruptedException
	{
		logger.debug(".......Application Logs Started for TC_001.......");
		logger.info("**** Start TC001_Validate_BookShelves ****");
		try
		{
			hp.s_box().sendKeys("bookshelves");
			hp.click_bookshelves();
			logger.info("Clicked BookShelves");
			bp.click_pop_up_close();
			logger.info("Closed PopUp");
			
			//1. Display the name & price of first 3 Bookshelves below Rs. 15000, with Storage type as open & exclude out of stock
			System.out.println("------------Display the name & price of first 3 Bookshelves below Rs. 15000---------");
			bp.click_price();
			logger.info("Clicked Price");
			bp.set_price();
			logger.info("Set Price");
			Thread.sleep(3000);
			bp.click_category();
			logger.info("Clicked Category");
			bp.click_category_bookshelf();
			logger.info("Set Category Type As Bookshelf");
			Thread.sleep(2000);
			
			bp.click_ex_out_of_stock();
			logger.info("Clicked Exclude Out of Stock");
			Thread.sleep(2000);
			
			bp.click_recommended();
			logger.info("Clicked Sort By");
			bp.click_price_high_to_low();
			logger.info("Clicked High to Low");
			Thread.sleep(3000);
			
			for(int item = 0 ;item<3 ; item++)
			{
				System.out.println(bp.searched_items(item));
			}
			logger.info("Displayed Top 3 BookShelves");
			logger.debug(".......Application Logs Ended for TC_001.......");
			logger.info("**** End TC_001_Validate_BookShelves ****");
			Assert.assertTrue(true);
		}
		catch(Exception e)
		{
			logger.error("TC001 Test Failed..");
			logger.debug(".......Application Logs Ended for TC_001.......");
			logger.info("**** End TC_001_Validate_BookShelves ****");
			Assert.fail("An exception occurred: " + e.getMessage());
		}
	}
}
