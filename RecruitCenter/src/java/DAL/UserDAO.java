/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author dell
 */
public class UserDAO {
//    public ArrayList<Account> getListAccount() {
//        try {
//            String query = "SELECT * FROM Accounts";
//            conn = new DBContext().getConnection();
//            PreparedStatement ps = conn.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            ArrayList<Account> list = new ArrayList<>();
//            while (rs.next()) {
//                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3));
//                list.add(a);
//                System.out.println(a);
//            }
//            return list;
//
//        } catch (Exception e) {
//        }
//        return null;
//    }
}
