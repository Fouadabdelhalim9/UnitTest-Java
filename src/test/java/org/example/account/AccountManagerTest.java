package org.example.account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



public class AccountManagerTest  {

    private final AccountManager accountManager = new AccountManagerImpl();
    private final Customer customer = new Customer();





//  depositAddAmountToBalanceTest
    @Test
    public void depositAddAmountToBalance() {
        // Arrange
        int amount = 50;
        customer.setBalance(100);

        // Act
        accountManager.deposit(customer, amount);

        // Assert
        Assertions.assertThat(customer.getBalance()).isEqualTo(150);
    }




//WithdrawItSucceedIfExpectedBalanceIsPositiveTest
    @Test
    public void WithdrawItSucceedIfExpectedBalanceIsPositive() {
        // Arrange
        int amount = 50;
        customer.setBalance(100);

        // Act
        String value =accountManager.withdraw(customer,amount);

        // Assert
        Assertions.assertThat(value).isEqualTo("success");
        Assertions.assertThat(customer.getBalance()).isEqualTo(50);
    }



    @Test
    public void WithdrawUnSucceedIfExpectedBalanceIsNegativeAndCreditAllowedFalse() {
        // Arrange
        int amount = 150;
        customer.setBalance(50);
        customer.setCreditAllowed(false);

        // Act
        String value =accountManager.withdraw(customer,amount);

        // Assert
        Assertions.assertThat(value).isEqualTo("insufficient account balance");
        Assertions.assertThat(customer.getBalance()).isEqualTo(50);
    }

    @Test
    public void WithdrawUnSucceedIfExpectedBalanceIsNegativeAndCreditAllowedTrue() {
        // Arrange
        int amount = 150;
        customer.setBalance(50);
        customer.setCreditAllowed(true);

        // Act
        String value =accountManager.withdraw(customer,amount);

        // Assert
        Assertions.assertThat(value).isEqualTo("success");
        Assertions.assertThat(customer.getBalance()).isEqualTo(-100);
    }
    /*




    * Math.abs(expectedBalance) > MAX_CREDIT && !customer.isVip()
    *   T  +   T    = T;
    *   F  +   T    = F;
    *   T  +   F    = F;
    *   F  +   F    = F;
    */


    @Test
    public void WithdrawUnSucceedAndExpectedBalanceGraterThanMAX_CREDITAndCustomerIsNotVip() {
        // Arrange
        int amount = 2000;
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String value =accountManager.withdraw(customer,amount);

        // Assert
        Assertions.assertThat(value).isEqualTo("maximum credit exceeded");
        Assertions.assertThat(customer.getBalance()).isEqualTo(500);
    }


    @Test
    public void WithdrawUnSucceedAndExpectedBalanceGraterThanMAX_CREDITAndCustomerIstVip() {
        // Arrange
        int amount = 2000;
        customer.setBalance(500);
        customer.setCreditAllowed(true);
        customer.setVip(true);

        // Act
        String value =accountManager.withdraw(customer,amount);

        // Assert
        Assertions.assertThat(value).isEqualTo("success");
        Assertions.assertThat(customer.getBalance()).isEqualTo(-1500);
    }


    @Test
    public void WithdrawUnSucceedAndExpectedBalanceLessThanMAX_CREDITAndCustomerIsNoVip () {
        // Arrange
        int amount = 150;
        customer.setBalance(50);
        customer.setCreditAllowed(true);
        customer.setVip(false);

        // Act
        String value =accountManager.withdraw(customer,amount);

        // Assert
        Assertions.assertThat(value).isEqualTo("success");
        Assertions.assertThat(customer.getBalance()).isEqualTo(-100);
    }








}



