/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.AccountDAO;
import DAL.UserDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import model.Account;
import model.User;

/**
 *
 * @author dell
 */
public class SignUpServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID userid = null;
        
        PrintWriter out = response.getWriter();
        AccountDAO accDAO = new AccountDAO();
        UserDAO uDAO = new UserDAO();
        //get name of page is sending request to
        String page = request.getParameter("page");
        if(page.equalsIgnoreCase("signup")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            userid = signUpWithEmail(email);
            request.getServletContext().setAttribute("userid", userid);
            out.println(userid);
        }
        
        if(page.equalsIgnoreCase("queryname")){
            String fname = (String)request.getParameter("firstname");
            String lname = (String)request.getParameter("lastname");
            userid = UUID.fromString(request.getServletContext().getAttribute("userid").toString());
            User user = uDAO.getUserWithID(userid);
            user.setFname(fname);
            user.setLname(lname);
            boolean successUpdateUser = false;
            try{
                successUpdateUser = uDAO.updateUser(user);
            } catch(Exception e){
                e.printStackTrace();
                out.println(e);
            }
            out.println(user);
            out.println(user.getUserID());
            out.println(uDAO.getUserWithID(userid));
            out.println(fname);
            out.println(successUpdateUser);
        }
        
//        out.println(Arrays.toString(cookieArray));

        //get value of parameter
//        String city = cookieArray[0];
//        String company = cookieArray[1]; //can be null
//        String country = cookieArray[2];
//        String district = cookieArray[3];
//        String email = cookieArray[4];
//        String fname = cookieArray[5];
//        String lname = cookieArray[6];
//        String pass = cookieArray[7];
//        String subdistrict = cookieArray[8];
//        String username = cookieArray[9];
//        String typeacc = cookieArray[10];
    }

    public static void main(String[] args) {
        UserDAO uDAO = new UserDAO();
        boolean successUpdateUser = false;
        User user = new User();
        user.setUserID(UUID.fromString("681cdf66-f4d1-4d7b-b034-4c788c92d299"));
        user.setFname("giang");
        user.setAddress("412");
        try{
            successUpdateUser = uDAO.updateUser(user);
        } catch(Exception e){
            e.printStackTrace();
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
