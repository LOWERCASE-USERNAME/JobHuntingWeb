/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import model.Account;

/**
 *
 * @author dell
 */
public class SignUpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        AccountDAO accDAO = new AccountDAO();
        //get data from cookies
        String cookies = request.getParameter("Cookie");
        String decodedCookie = URLDecoder.decode(cookies, "UTF-8");
        String[] cookieArray = decodedCookie.split(";");
        Arrays.sort(cookieArray);
        for (int i = 0; i < cookieArray.length; i++) {
            String[] temp = cookieArray[i].split("=");
            cookieArray[i] = temp[1];
        }
        out.println(Arrays.toString(cookieArray));

        //get value of parameter
        String city = cookieArray[0];
        String company = cookieArray[1];
        String country = cookieArray[2];
        String district = cookieArray[3];
        String email = cookieArray[4];
        String fname = cookieArray[5];
        String lname = cookieArray[6];
        String pass = cookieArray[7];
        String subdistrict = cookieArray[8];
        String username = cookieArray[9];
        String typeacc = cookieArray[10];
        String id = signUpWithEmail(email); //get userID
        Account acc = new Account(id, username, pass);
        accDAO.insertAccount(acc); //update all of the column with the same ID
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("org.apache.tomcat.websocket.DISABLE_BUILTIN_EXTENSIONS", Boolean.TRUE);
        processRequest(request, response);
    }

    public static String signUpWithEmail(String email) {
        AccountDAO accountDB = new AccountDAO();
        accountDB.initUserWithEmail(email);
        String id = accountDB.searchID(email);
        return id;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
