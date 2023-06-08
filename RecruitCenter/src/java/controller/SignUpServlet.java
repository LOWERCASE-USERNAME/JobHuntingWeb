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
        String cookies = request.getParameter("Cookie");
        String[] cookieArray = cookies.split("%3B%20");
        for(String cookie:cookieArray){
            out.println(cookie);
        }
        Arrays.sort(cookieArray);
        out.println(Arrays.toString(cookieArray));
        //signup
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String id = signUpWithEmail(email);
        //queryname
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        //querylocation
//        String country = requ
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
