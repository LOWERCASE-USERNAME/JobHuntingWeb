/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Account;
import DAL.AccountDAO;
import java.util.ArrayList;

public class AuthServlet {
    public static void main(String[] args) {
        AccountDAO ac = new AccountDAO();
        ArrayList<Account> accList = ac.getListAccount();
    }
}
