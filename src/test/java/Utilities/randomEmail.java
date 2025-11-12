package Utilities;

import java.util.Random;
import java.util.UUID;

public class randomEmail {
	public static String generateRandomEmail() {
		String uniqueId = UUID.randomUUID().toString().substring(0, 6);
		return "user" + uniqueId + "@testmail.com";
	}

	public static String generateRandomPhoneNumber() {
		Random random = new Random();

		int[] startDigits = { 7, 8, 9 };
		int firstDigit = startDigits[random.nextInt(startDigits.length)];
		StringBuilder phoneNumber = new StringBuilder();
		phoneNumber.append(firstDigit);

		for (int i = 0; i < 9; i++) {
			phoneNumber.append(random.nextInt(10));
		}

		return phoneNumber.toString();
	}

}
