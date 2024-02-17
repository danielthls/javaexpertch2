package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTests {
	
	@Test
	public void depositShouldIncreaseBalanceWhenPositiveAmount() {
		double amount = 200.0;
		double expectedValue = 196;
		Account acc = AccountFactory.createEmptyAccount();
		
		acc.deposit(amount);
		Assertions.assertEquals(expectedValue, acc.getBalance()); 
	}
	
	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {
		double amount = -200.0;
		double expectedValue = 100.0;
		Account acc = AccountFactory.createAccount(expectedValue);
		
		Assertions.assertEquals(expectedValue, acc.getBalance());		
	}
	
	@Test
	public void fullWithdrawShouldClearBalance() {
		double initialBalance = 800;
		double expectedValue = 0.0;
		Account acc = AccountFactory.createAccount(initialBalance);
		double result = acc.fullWithdraw();
		
		Assertions.assertTrue(expectedValue == acc.getBalance());
		Assertions.assertTrue(result == initialBalance);
	}
	
	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
		double balance = 200.0;
		double expectedValue = 100;
		Account acc = AccountFactory.createAccount(balance);
		acc.withdraw(100);
		
		Assertions.assertTrue(acc.getBalance() == expectedValue);
	}
	
	@Test
	public void withdrawShouldThrowWhenInsufficientBalance() {
		
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Account acc = AccountFactory.createAccount(800);
			acc.withdraw(801);
		});		
	}
	
	
}
