/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAL;

import java.util.UUID;
import static junit.framework.Assert.assertEquals;
import model.Account;
import DAL.AccountDAO;
import java.util.ArrayList;
import org.junit.Assert;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author dell
 */
public class AccountDAOTest {
    
    private static AccountDAO instance;
    public AccountDAOTest() {
        instance = new AccountDAO();
    }

    /**
     * Test of getListAccount method, of class AccountDAO.
     */
    @Test
    public void testAccountObjectEqualsWithSameAccount(){
        System.out.println("Equals");
        Account firstSample = instance.getListAccount().get(0);
        Account secondSample = new Account();
        secondSample.setId(firstSample.getId());
        secondSample.setUsername(firstSample.getUsername());
        secondSample.setPassword(firstSample.getPassword());
        secondSample.setRole(firstSample.getRole());
        assertEquals(firstSample, secondSample);
    }
    @Test
    public void testAccountObjectEqualsWithDifferentAccount(){
        System.out.println("Equals");
        Account firstSample = instance.getListAccount().get(0);
        Account secondSample = new Account();
        secondSample.setId(firstSample.getId());
        secondSample.setUsername(firstSample.getUsername() + "This must be different username");
        secondSample.setPassword(firstSample.getPassword());
        secondSample.setRole(firstSample.getRole());
        assertNotEquals(firstSample, secondSample);
    }
    @Test
    public void testAccountObjectEqualsWithNull(){
        System.out.println("Equals");
        Account firstSample = instance.getListAccount().get(0);
        Account secondSample = new Account();
        assertNotEquals(firstSample, secondSample);
    }
    @Test
    public void testFirstFromGetListAccount() {
        System.out.println("getListAccount");
        Account expResult = new Account(UUID.fromString("574A2E32-1E30-4532-8F6B-011475644F65"), "Vinh897", "Abc123", "employees");
        Account result = instance.getListAccount().get(0);
        assertEquals(expResult, result);
    }
    @Test
    public void testLastFromGetListAccount() {
        System.out.println("getListAccount");
        Account expResult = new Account(UUID.fromString("200BE17A-77E0-4CA4-82A4-FE74CF6FCC9E"), null, "vAge341", "employees");
        ArrayList<Account> resultList = instance.getListAccount();
        Account result = resultList.get(resultList.size() - 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertAccount method, of class AccountDAO.
     */
    @Test
    public void testInsertDuplicateIdAccount() throws Exception {
        System.out.println("Duplicate ID");
        Account acc = new Account(UUID.fromString("574A2E32-1E30-4532-8F6B-011475644F65"), "giang", "123", "employees");
        boolean expResult = false;
        boolean result = !expResult;
        try {
            result = instance.insertAccount(acc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsertValidIdAccount() throws Exception {
        System.out.println("Valid ID");
        Account acc = new Account(UUID.randomUUID(), "giang", "123", "employees");
        boolean expResult = true;
        boolean result = !expResult;
        try {
            result = instance.insertAccount(acc);
        } catch (Exception e) {
            
        }
        assertEquals(expResult, result);
    }
    @Test
    public void testInsertInvalidIdAccount() throws Exception {
        System.out.println("Invalid ID");
        Account acc = new Account(UUID.fromString("1"), "giang", "123", "employees");
        boolean expResult = true;
        boolean result = !expResult;
        try {
            result = instance.insertAccount(acc);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInsertNullNameAccount() throws Exception {
        System.out.println("Invalid ID");
        Account acc = new Account(UUID.fromString("574A2E32-1E30-4532-8F6B-011475644F65"), "", "123", "employees");
        boolean expResult = true;
        boolean result = !expResult;
        try {
            result = instance.insertAccount(acc);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of updateAccount method, of class AccountDAO.
//     */
    @Test
    public void testUpdateUsernameWithValidIDAccount() {
        Account acc = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = true;
        acc = new Account(UUID.fromString("574A2E32-1E30-4532-8F6B-011475644F65"), "", "123", "employers");
        boolean result = instance.updateAccount(acc);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateUsernameWithNonExistIDAccount() {
        Account acc = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = true;
        acc = new Account(UUID.randomUUID(), "giang", "123", "employers");
        boolean result = instance.updateAccount(acc);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateUsernameWithInvalidIDAccount() {
        Account acc = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = true;
        acc = new Account(UUID.fromString("1"), "giang", "123", "employers");
        boolean result = instance.updateAccount(acc);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of getAccountWithUsername method, of class AccountDAO.
//     */
//    @Test
//    public void testGetAccountWithUsername() {
//        System.out.println("getAccountWithUsername");
//        String username = "";
//        AccountDAO instance = new AccountDAO();
//        Account expResult = null;
//        Account result = instance.getAccountWithUsername(username);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAccountWithID method, of class AccountDAO.
//     */
    @Test
    public void testGetAccountWithValidID() {
        UUID ID = UUID.fromString("A8FF3700-1F10-49DF-B16B-01A193C3FCDE");
        AccountDAO instance = new AccountDAO();
        Account expResult = new Account(UUID.fromString("A8FF3700-1F10-49DF-B16B-01A193C3FCDE"), "John123456", "Abc123", "employees");
        Account result = instance.getAccountWithID(ID);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testGetAccountWithInvalidID() {
        UUID ID = UUID.randomUUID();
        AccountDAO instance = new AccountDAO();
        Account expResult = new Account(UUID.fromString("A8FF3700-1F10-49DF-B16B-01A193C3FCDE"), "John123456", "Abc123", "employees");
        Account result = instance.getAccountWithID(ID);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAccountWithNullID() {
        UUID ID = null;
        AccountDAO instance = new AccountDAO();
        Account expResult = new Account(UUID.fromString("A8FF3700-1F10-49DF-B16B-01A193C3FCDE"), "John123456", "Abc123", "employees");
        Account result = instance.getAccountWithID(ID);
        assertEquals(expResult, result);
    }
}
