package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Financing;

public class FinancingTest {

	@Test
	public void financingShouldCreateObjectWhenDataIsCorrect() {
		Financing fin = new Financing(10000.0,2000.0,80);
		Assertions.assertTrue(fin.getTotalAmount() == 10000.0 && fin.getIncome() == 2000.0 && fin.getMonths() == 80);
	}
	
	@Test
	public void financingShouldThrowWhenDataIsIncorrect() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(100000.0, 2000.0, 20);
		});
	}
	
	@Test
	public void setTotalAmountShouldUpdateValueWhenDataIsCorrect() {
		Financing fin = new Financing(100000.0,2000.0,80);
		double expectedResult = 50000.0;
		fin.setTotalAmount(50000.0);
		Assertions.assertTrue(fin.getTotalAmount() == expectedResult);
	}
	
	@Test
	public void setTotalAmountThroweWhenDataIsIncorrect() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(100000.0,2000.0,80);
			double expectedResult = 150000.0;
			fin.setTotalAmount(150000.0);
			Assertions.assertTrue(fin.getTotalAmount() == expectedResult);
		});
	}
	
	@Test
	public void setIncomeShouldUpdateValueWhenDataIsCorrect() {
		Financing fin = new Financing(100000.0,2000.0,80);
		double expectedResult = 5000.0;
		fin.setIncome(5000.0);
		Assertions.assertTrue(fin.getIncome() == expectedResult);
	}
	
	@Test
	public void setIncomeThroweWhenDataIsIncorrect() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(100000.0,2000.0,80);
			double expectedResult = 1000.0;
			fin.setIncome(1000.0);
			Assertions.assertTrue(fin.getIncome() == expectedResult);
		});
	}
	
	@Test
	public void setMonthsShouldUpdateValueWhenDataIsCorrect() {
		Financing fin = new Financing(100000.0,2000.0,80);
		int expectedResult = 100;
		fin.setMonths(100);
		Assertions.assertTrue(fin.getMonths() == expectedResult);
	}
	
	@Test
	public void setMonthsThroweWhenDataIsIncorrect() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Financing fin = new Financing(100000.0,2000.0,80);
			double expectedResult = 40;
			fin.setMonths(40);
			Assertions.assertTrue(fin.getMonths() == expectedResult);
		});
	}
	
	@Test
	public void entryShouldCalculateCorrectly() {
		Financing fin = new Financing(100000.0, 2000.0, 80);
		double expectedValue = 100000.0 * 0.2;
		Assertions.assertTrue(fin.entry() == expectedValue);
	}
	
	@Test
	public void quotaShouldCalculateCorrectly() {
		Financing fin = new Financing(100000.0, 2000.0, 80);
		double expectedValue = 80000.0 / 80;
		Assertions.assertTrue(fin.quota() == expectedValue);
	}
	
}
