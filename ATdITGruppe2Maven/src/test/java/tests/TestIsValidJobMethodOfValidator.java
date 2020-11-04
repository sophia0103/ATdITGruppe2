package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gemuese4you.Validator;

/**
 * @author Luis
 * This class tests the funcionality of the isValidOffer method of the Validator class.
 */
class TestIsValidJobMethodOfValidator {
	
	/**
	 * This test tests if empty input is invalid.
	 */
	@Test
	void testEmptyInput() {
		String[] inputArray = new String[7];
		inputArray[0] = "";
		inputArray[1] = "";
		inputArray[2] = "";
		inputArray[3] = "";
		inputArray[4] = "";
		inputArray[5] = "";
		inputArray[6] = "";
		assertFalse(Validator.isValidJob(inputArray));
	}
	
	/**
	 * This test tests if alphabetic values as a price are valid.
	 */
	@Test
	void testStringDuration() {
		String[] inputArray = new String[7];
		inputArray[0] = "Job";
		inputArray[1] = "12";
		inputArray[2] = "2 Monate";
		inputArray[3] = "45";
		inputArray[4] = "2021-10-10";
		inputArray[5] = "Vollzeit";
		inputArray[6] = "Arbeiten";
		assertFalse(Validator.isValidJob(inputArray));
	}
	
	/**
	 * This test tests if alphabetic values as a distance are valid.
	 */
	@Test
	void testStringDistance() {
		String[] inputArray = new String[7];
		inputArray[0] = "Job";
		inputArray[1] = "12 Meter";
		inputArray[2] = "2";
		inputArray[3] = "45";
		inputArray[4] = "2021-10-10";
		inputArray[5] = "Vollzeit";
		inputArray[6] = "Arbeiten";
		assertFalse(Validator.isValidJob(inputArray));
	}
	
	/**
	 * This test tests if alphabetical values get recognized as invalid for salary.
	 */
	@Test
	void testStringSalary() {
		String[] inputArray = new String[7];
		inputArray[0] = "Job";
		inputArray[1] = "12";
		inputArray[2] = "2";
		inputArray[3] = "45€/h";
		inputArray[4] = "20211010";
		inputArray[5] = "Vollzeit";
		inputArray[6] = "Arbeiten";
		assertFalse(Validator.isValidJob(inputArray));
	}
	
	/**
	 * This test tests if numeric values as a date are valid.
	 */
	@Test
	void testIntDate() {
		String[] inputArray = new String[7];
		inputArray[0] = "Job";
		inputArray[1] = "12";
		inputArray[2] = "2";
		inputArray[3] = "45";
		inputArray[4] = "20211010";
		inputArray[5] = "Vollzeit";
		inputArray[6] = "Arbeiten";
		assertFalse(Validator.isValidJob(inputArray));
	}
	
	
	/**
	 * This test tests if valid inputs are identified as such by the Validator.
	 */
	@Test
	void testValidInput() {
		String[] inputArray = new String[7];
		inputArray[0] = "Job";
		inputArray[1] = "12";
		inputArray[2] = "2";
		inputArray[3] = "45";
		inputArray[4] = "2021-10-10";
		inputArray[5] = "Vollzeit";
		inputArray[6] = "Arbeiten";
		assertTrue(Validator.isValidJob(inputArray));
	}

}
