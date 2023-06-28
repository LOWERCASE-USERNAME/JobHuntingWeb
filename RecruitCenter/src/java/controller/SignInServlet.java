package controller;

import DAL.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author dell
 */
public class SignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username_login");
        String password = request.getParameter("pwd_login");
        String remember = request.getParameter("remember");
        
        AccountDAO accDAO = new AccountDAO();
        Account rtAccount = accDAO.getAccountWithUsername(username); //retrieve account
        if(rtAccount == null){//account does not exist
            request.getSession().setAttribute("accountExisted", false);
            request.getSession().setAttribute("loginSuccess", false); //login fail, account not existed
        } else {
            String rtAccountPass = rtAccount.getPassword();
            if(rtAccountPass.equals(password)){
                request.getSession().setAttribute("loginSuccess", true);//login success
                if(remember.equals("remember_me")){
                    //set cookies here
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie passwordCookie = new Cookie("password", password);
                    Cookie useridCookie = new Cookie("userid", rtAccount.getId().toString());
                    
                    //setMaxAge
                    int maxAge = 30 * 24 * 60 * 60; // 30 days in seconds
                    usernameCookie.setMaxAge(maxAge);
                    passwordCookie.setMaxAge(maxAge);
                    useridCookie.setMaxAge(maxAge);
                    
                    //add Cookie to response
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(useridCookie);
                }
                request.getSession().setAttribute("SignInAcc", rtAccount);
                
                request.getSession().setMaxInactiveInterval(60*60); //inactivate session after 1 hour after no activity
            } else {
                request.getSession().setAttribute("passwordIncorrect", true);
                request.getSession().setAttribute("loginSuccess", false);//login fail, password wrong
            }
        }
//        request.getRequestDispatcher("/pages/auth/login.jsp").forward(request, response);
        response.sendRedirect("/RecruitCenter/pages/auth/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
