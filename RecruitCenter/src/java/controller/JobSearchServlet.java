/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.RecruitmentsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Recruitments;

/**
 *
 * @author dell
 */
public class JobSearchServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RecruitmentsDAO rdao = new RecruitmentsDAO();
        Map<String, String> conditionMap = new HashMap<>();
        ArrayList<String> list = null;
        //retrieve parameter
        String jobTerm = request.getParameter("job-search"); //jobtitle and skill
        String cityTerm = request.getParameter("city-search"); //location
        String distTerm = request.getParameter("dist-search"); //location
        String wardTerm = request.getParameter("ward-search"); //location
        String datePostedFilter = request.getParameter("datePosted"); //date posted
        String salaryFilter = request.getParameter("salary"); //salary
        String experienceFilter = request.getParameter("experience"); //null
        String fieldFilter = request.getParameter("field"); //fieldid
        String jobTypeFilter = request.getParameter("jobType"); //jobtype
        String educationFilter = request.getParameter("education"); //degree
        String workplaceFilter = request.getParameter("workplace"); //workplace
        String languageFilter = request.getParameter("language"); //null

        //field filter
        if (Integer.parseInt(fieldFilter) != 0) {
            conditionMap.put("FieldID", fieldFilter);
            
        }
        //job type filter
        if (Integer.parseInt(jobTypeFilter) != 0) {
            conditionMap.put("JobTypeID", jobTypeFilter);
        }
        try {
//            out.println(conditionMap);
            list = rdao.getRecruitmentsWithSearchFilter(conditionMap);
            request.getSession().setAttribute("recruitmentSearchList", list);
//            out.println(list);
            response.sendRedirect("home.jsp");
//            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }

        //testing
//        out.println("jobTerm: " + jobTerm);
//        out.println("cityTerm: " + cityTerm);
//        out.println("distTerm: " + distTerm);
//        out.println("wardTerm: " + wardTerm);
//        out.println("datePostedFilter: " + datePostedFilter);
//        out.println("salaryFilter: " + salaryFilter);
//        out.println("experienceFilter: " + experienceFilter);
//        out.println("fieldFilter: " + fieldFilter);
//        out.println("jobTypeFilter: " + jobTypeFilter);
//        out.println("educationFilter: " + educationFilter);
//        out.println("workplaceFilter: " + workplaceFilter);
//        out.println("languageFilter: " + languageFilter);
    }

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
