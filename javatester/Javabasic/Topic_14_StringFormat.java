package Javabasic;

public class Topic_14_StringFormat {

	public static final String CUSTOMER_INFOR_LINK = "XPath=//div[contains(@class,'block-account-navigation')]//a[text()='Customer info']";
	public static final String ADDRESS_LINK = "XPATH=//div[contains(@class,'block-account-navigation')]//a[text()='Addresses']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='My product reviews']";
	public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='Reward points']";
	public static final String LOGOUT_LINK_AT_USER = "css=a[class='ico-logout']";
	public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";

	// 1 biến cho cả 14 page hoặc n page (format giốn nhau - chỉ khác nhau bởi tên page)

	public static String DYNAMIC_LINK_BY_PAGE_NAME = "xpath=//div[contains(@class,'block-account-navigation')]//a[text()='%s']";

	public static void main(String[] args) {
		clickToSideBarLink(CUSTOMER_INFOR_LINK, "Customer info");
	}

	public static void clickToSideBarLink(String locator) {
		System.out.println("click to " + locator);
	}

	public static void clickToSideBarLink(String dynamicLocator, String pageName) {
		String locator = String.format(dynamicLocator, pageName);
		System.out.println("click to" + locator);
	}

}
