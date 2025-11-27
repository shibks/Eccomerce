package Utilities;

import java.util.HashMap;
import java.util.Map;

public class UserDataAPIUtil {
	
	public static Map<String, String> getCreateAccountData() {

        String email = "user" + System.currentTimeMillis() + "@gmail.com";
		Map<String,String> user=new HashMap<>();
		 user.put("name", "Shibi");
	        user.put("email", email);
	        user.put("password", "123456");
	        user.put("title", "Mr");
	        user.put("birth_date", "10");
	        user.put("birth_month", "12");
	        user.put("birth_year", "1998");
	        user.put("firstname", "Shibi");
	        user.put("lastname", "K");
	        user.put("company", "ABC Company");
	        user.put("address1", "Street 1");
	        user.put("address2", "Street 2");
	        user.put("country", "India");
	        user.put("zipcode", "695001");
	        user.put("state", "Kerala");
	        user.put("city", "Trivandrum");
	        user.put("mobile_number", "9999999999");

	        return user;
	}

}
