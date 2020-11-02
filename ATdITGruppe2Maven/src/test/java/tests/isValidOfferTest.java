package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gemuese4you.Validator;

/**
 * @author I518189
 * This class tests the funcionality of the isValidOffer method of the Validator class.
 */
class isValidOfferTest {
	
	/**
	 * This test tests if empty input is invalid.
	 */
	@Test
	void testEmptyInput() {
		String[] inputArray = new String[4];
		inputArray[0] = "";
		inputArray[1] = "";
		inputArray[2] = "";
		inputArray[3] = "";
		assertFalse(Validator.isValidOffer(inputArray));
	}
	
	/**
	 * This test tests if alphabetic values as a price are valid.
	 */
	@Test
	void testStringPrice() {
		String[] inputArray = new String[4];
		inputArray[0] = "abc";
		inputArray[1] = "23";
		inputArray[2] = "2020-12-12";
		inputArray[3] = "apple";
		assertFalse(Validator.isValidOffer(inputArray));
	}
	
	/**
	 * This test tests if alphabetic values as a distance are valid.
	 */
	@Test
	void testStringDistance() {
		String[] inputArray = new String[4];
		inputArray[0] = "12";
		inputArray[1] = "abc";
		inputArray[2] = "2020-12-12";
		inputArray[3] = "apple";
		assertFalse(Validator.isValidOffer(inputArray));
	}
	
	/**
	 * This test tests if numeric values as a date are valid.
	 */
	@Test
	void testIntDate() {
		String[] inputArray = new String[4];
		inputArray[0] = "12";
		inputArray[1] = "12";
		inputArray[2] = "20201212";
		inputArray[3] = "apple";
		assertFalse(Validator.isValidOffer(inputArray));
	}
	
	/**
	 * This test tests if numeric values as a product are valid.
	 */
	@Test
	void testIntProduct() {
		String[] inputArray = new String[4];
		inputArray[0] = "12";
		inputArray[1] = "12";
		inputArray[2] = "20201212";
		inputArray[3] = "12";
		assertFalse(Validator.isValidOffer(inputArray));
	}
	
	/**
	 * This test tests if valid inputs are identified as such by the Validator.
	 */
	@Test
	void testValidInput() {
		String[] inputArray = new String[4];
		inputArray[0] = "12";
		inputArray[1] = "12";
		inputArray[2] = "2020-12-12";
		inputArray[3] = "apple";
		assertTrue(Validator.isValidOffer(inputArray));
	}

}
