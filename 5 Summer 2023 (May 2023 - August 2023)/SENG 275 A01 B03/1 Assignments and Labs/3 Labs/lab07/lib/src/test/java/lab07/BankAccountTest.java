package lab07;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;
import net.jqwik.api.constraints.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

//class BankAccountTest {
//    // ... add tests IF you feel they will be helpful.
//    // Tests in this file will not be marked.
//}

public class BankAccountTest {

    @Test
    public void testGetBalance() {
        BankAccount account = new BankAccount(100, 0.05f);
        float balance = account.getBalance();
        assertEquals(100, balance);
    }

    @Test
    public void testSetBalance() {
        BankAccount account = new BankAccount(100, 0.05f);
        account.setBalance(200);
        float balance = account.getBalance();
        assertEquals(200, balance);
    }

    @Test
    public void testGetInterestRate() {
        BankAccount account = new BankAccount(100, 0.05f);
        float interestRate = account.getInterestRate();
        assertEquals(0.05f, interestRate);
    }

    @Test
    public void testSetInterestRate() {
        BankAccount account = new BankAccount(100, 0.05f);
        account.setInterestRate(0.08f);
        float interestRate = account.getInterestRate();
        assertEquals(0.08f, interestRate);
    }

    @Test
    public void testApplyInterest() {
        BankAccount account = new BankAccount(100, 0.05f);
        account.applyInterest();
        float balance = account.getBalance();
        assertEquals(105, balance);
    }

    @Test
    public void testCloseAccountWithZeroBalance() {
        assertThrows(IllegalStateException.class, () -> {
            BankAccount account = new BankAccount(0, 0.05f);
            account.close();
            assertFalse(account.isOpen());
        });
    }

    @Test
    public void testCloseAccountWithPositiveBalance() {
        BankAccount account = new BankAccount(50, 0.05f);
        assertThrows(IllegalStateException.class, () -> {
            account.close();
        });
    }
}