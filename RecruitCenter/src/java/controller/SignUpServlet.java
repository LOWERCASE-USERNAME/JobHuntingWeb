/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.AccountDAO;
import DAL.CompaniesDAO;
import DAL.UserDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.User;
import model.Company;

/**
 *
 * @author dell
 */
public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID userid = null;
        boolean successUpdateUser = false;
        boolean successUpdateAccount = false;
        PrintWriter out = response.getWriter();
        AccountDAO accDAO = new AccountDAO();
        UserDAO uDAO = new UserDAO();
        CompaniesDAO cdao = new CompaniesDAO();
        //get name of page is sending request to
        String page = request.getParameter("page");

        if (page == null) {
            page = "";
        }

        //request from page signup, change href to page queryname
        if (page.equalsIgnoreCase("signup")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (uDAO.getUserWithEmail(email) != null) {//email already occupied
                //send toast
                request.getSession().setAttribute("emailExisted", "true");
                response.setHeader("X-NextPage", "false");
                return;
            } else {
                request.getSession().setAttribute("emailExisted", "false");
                response.setHeader("X-NextPage", "true");
            }

            userid = signUpWithEmail(email);
            //set password to accountDB
            Account newAcc = new Account();
            newAcc.setId(userid);
            newAcc.setPassword(password);
            try {
                successUpdateAccount = accDAO.insertAccount(newAcc);
            } catch (Exception ex) {
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println(successUpdateAccount);
            request.getSession().setAttribute("userid", userid);
        }
        //request from page queryname, change href to page querylocation
        if (page.equalsIgnoreCase("queryname")) {
            String fname = (String) request.getParameter("firstname");
            String lname = (String) request.getParameter("lastname");
            userid = UUID.fromString(request.getSession().getAttribute("userid").toString());
            User user = uDAO.getUserWithID(userid);
            user.setFname(fname);
            user.setLname(lname);
            try {
                successUpdateUser = uDAO.updateUser(user);
            } catch (Exception ex) {
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //request from page querylocation, change href to page querytypeaccount
        if (page.equalsIgnoreCase("querylocation")) {
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String subdistrict = request.getParameter("sub-district");
            if (subdistrict == null) {//if subdistrict input is null
                subdistrict = "\b\b"; //backspace two character
            }
            userid = UUID.fromString(request.getSession().getAttribute("userid").toString());
            User user = uDAO.getUserWithID(userid);
            user.setAddress(country + ", " + city + ", " + district + ", " + subdistrict);
            try {
                successUpdateUser = uDAO.updateUser(user);
            } catch (Exception ex) {
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //request from page querytypeaccount, change href to page queryusername
        if (page.equalsIgnoreCase("querytypeaccount")) {
            String company = request.getParameter("company");
            String typeaccount = request.getParameter("typeaccount"); //output to console to know
            User.AccountType accounttype = null;
            switch (typeaccount) {
                case "employees":
                    accounttype = User.AccountType.EMPLOYEE;
                    break;
                case "employers":
                    accounttype = User.AccountType.EMPLOYER;
                    break;
            }
//            out.println(accounttype);
            userid = UUID.fromString(request.getSession().getAttribute("userid").toString());
            User user = uDAO.getUserWithID(userid);
            Company comp = cdao.getCompanyWithName(company);
            if (comp == null) {//not already exists
                if (!company.equals("")) {
                    comp = new Company();
                    comp.setCompanyName(company);
                    comp.setCompanyAddress("");
                    comp.setCompanyNumOfRatings(0);
                    comp.setCompanyRatings(5);
                    comp.setCompanyReviews("");
                    comp.setCompanyWebsite("");
                    try {
                        cdao.insertCompany(comp);
                    } catch (Exception ex) {
                        Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                user.setCompanyID(comp.getCompanyID());
                user.setAccType(accounttype);
            }
            Account a = accDAO.getAccountWithID(userid);
            a.setRole(typeaccount);
            /*TODO: add to company*/
            try {
//                out.println(user.getAccType());
                successUpdateUser = uDAO.updateUser(user);
                successUpdateAccount = accDAO.updateAccount(a);

            } catch (Exception ex) { //output to toast
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //request from page queryusername, change href to page welcome
        if (page.equalsIgnoreCase("queryusername")) {
            String username = request.getParameter("username");
            /*TODO: check if username is already occupied*/
            if (accDAO.getAccountWithUsername(username) != null) {//username already occupied
                //send toast
                request.getSession().setAttribute("usernameExisted", "true");
                response.setHeader("X-NextPage", "false");
                return;
            } else {
                request.getSession().setAttribute("usernameExisted", "false");

                response.setHeader("X-NextPage", "true");
            }
            userid = UUID.fromString(request.getSession().getAttribute("userid").toString());
            out.println(UUID.fromString(request.getSession().getAttribute("userid").toString()));
            Account acc = accDAO.getAccountWithID(userid);
            acc.setUsername(username);
//            out.println(acc);
            request.getSession().setAttribute("account", acc);
            try {
                successUpdateAccount = accDAO.updateAccount(acc);
            } catch (Exception ex) {
                Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
//            response.sendRedirect("pages/welcome/welcome.html");
        }

    }

    public static void main(String[] args) {
        UserDAO uDAO = new UserDAO();
        boolean successUpdateUser = false;
        User user = new User();
        user.setUserID(UUID.fromString("681cdf66-f4d1-4d7b-b034-4c788c92d299"));
        user.setFname("giang");
        user.setAddress("412");
        try {
            successUpdateUser = uDAO.updateUser(user);
        } catch (Exception ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(successUpdateUser);
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

    public static UUID signUpWithEmail(String email) {
        UserDAO userDB = new UserDAO();
        userDB.initUserWithEmail(email);
        User user = userDB.getUserWithEmail(email);
        return user.getUserID();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
