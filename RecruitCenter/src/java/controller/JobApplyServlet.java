/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.AppliedDAO;
import DAL.CompaniesDAO;
import DAL.RecruitmentsDAO;
import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.UUID;
import model.Applied;
import model.Company;
import model.Recruitments;
import model.User;

/**
 *
 * @author dell
 */
public class JobApplyServlet extends HttpServlet {

    String encodeMailToRCF(String str) {
        return str
                .replace("%", "%25")
                .replace(" ", "%20")
                .replace("!", "%21")
                .replace("\"", "%22")
                .replace("#", "%23")
                .replace("$", "%24")
                .replace("&", "%26")
                .replace("'", "%27")
                .replace("(", "%28")
                .replace(")", "%29")
                .replace("*", "%2A")
                .replace("+", "%2B")
                .replace(",", "%2C")
                .replace("/", "%2F")
                .replace(":", "%3A")
                .replace(";", "%3B")
                .replace("=", "%3D")
                .replace("?", "%3F")
                .replace("@", "%40")
                .replace("[", "%5B")
                .replace("]", "%5D");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userid = request.getParameter("userid");
        String itemid = request.getParameter("itemid");
        String companyname = request.getParameter("company");
        RecruitmentsDAO rdao = new RecruitmentsDAO();
        UserDAO udao = new UserDAO();
        CompaniesDAO cdao = new CompaniesDAO();
        AppliedDAO adao = new AppliedDAO();
        Recruitments item = null;
        User user = null;
        Company company = null;
        try {
            item = rdao.getListRecruitments(UUID.fromString(itemid));
//            out.println(item);
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }
        try {
            user = udao.getUserWithID(UUID.fromString(userid));
//            out.println(user);
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }
        try {
            company = cdao.getCompanyWithName(companyname);
//            out.println(company);
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }
        try {
            Applied apply = new Applied(UUID.fromString(userid), UUID.fromString(itemid), new Date(System.currentTimeMillis()));
            adao.insertApplied(apply);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
        StringBuilder url = new StringBuilder();
        url.append("mailto:").append(item.getContactEmail()).append("?subject=An%20bright%20potential%20applicant%20has%20seen%20and%20want%20to%20work%20with%20you%20on%20RecruitCenter&body=Dear%20").append(item.getContactName()).append("%2C%0A%0AI%20was%20keenly%20interested%20in%20reading%20the%20job%20posting%20for%20the%20position%20of%20").append(item.getJobTitle()).append("%20at%20").append(company.getCompanyName()).append(".%20I%20believe%20my%20experience%20is%20a%20strong%20match%20for%20the%20responsibilities%20pertaining%20to%20this%20role%2C%20and%20I%27m%20pleased%20to%20submit%20my%20application%20for%20the%20position.%0A%0A--YOUR%20EXPERIENCE%20HERE--%0A%0AI%20have%20attached%20my%20resume%20to%20this%20letter.%20Through%20it%2C%20I%20hope%20you%20will%20learn%20more%20about%20my%20background%2C%20education%2C%20achievements%2C%20and%20awards.%0A%0AIf%20I%20can%20provide%20you%20with%20any%20further%20information%2C%20please%20let%20me%20know.%20I%20look%20forward%20to%20hearing%20from%20you%20about%20this%20opportunity.%0A%0AThank%20you%20for%20your%20consideration.%0A%0A").append(user.getLname()).append("%20").append(user.getFname()).append("%0A").append(user.getEmail()).append("%0A").append(user.getPhonenum());
        String file = request.getParameter("resume");
        request.setAttribute("attachedFile", file);
        url.append("&attechedFile" + file);

        response.sendRedirect(url.toString());
//        response.sendRedirect("home.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
