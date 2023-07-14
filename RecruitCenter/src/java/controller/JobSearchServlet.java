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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Degree;
import model.Workplace;

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
        Map<String, Object> filterMap = new HashMap<>();
        List<String> list = null;
        //retrieve parameter
        String jobTerm = request.getParameter("job-search"); //jobtitle and skill and description: done
        String cityTerm = request.getParameter("city-search"); //location :done
        String distTerm = request.getParameter("dist-search"); //location :done
        String wardTerm = request.getParameter("ward-search"); //location :done
        String datePostedFilter = request.getParameter("datePosted"); //date posted: processing
        String salaryFilter = request.getParameter("salary"); //salary: null
        String experienceFilter = request.getParameter("experience"); //null
        String fieldFilter = request.getParameter("field"); //fieldid :done
        String jobTypeFilter = request.getParameter("jobType"); //jobtype:done
        String educationFilter = request.getParameter("education"); //degree:done
        String workplaceFilter = request.getParameter("workplace"); //workplace:done
        String languageFilter = request.getParameter("language"); //null

        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("home.jsp?");
        int count = 0;
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String[] val = entry.getValue();
            if(count != 0) urlBuilder.append("&");
            urlBuilder.append(key).append("=").append(URLEncoder.encode(val[0], "UTF-8"));
            count++;
        }
        //field filter
        if (Integer.parseInt(fieldFilter) != 0) {
            filterMap.put("FieldID", fieldFilter);

        }
        //job type filter
        if (Integer.parseInt(jobTypeFilter) != 0) {
            filterMap.put("JobTypeID", jobTypeFilter);
        }
        //minimum degree
        int educationScore = Integer.parseInt(educationFilter);
        if (educationScore != 0) {
            String[] conditionArr = Arrays.copyOfRange(Degree.DEGREES, 1, educationScore + 1);
            filterMap.put("Degree", conditionArr);
        }
        //workplace
        if (Integer.parseInt(workplaceFilter) != 0) {
            filterMap.put("JobDescription", "Preferred Workplace: " + Workplace.WORKPLACES[Integer.parseInt(workplaceFilter)]);
        }
        List<String> tempList = null;
        try {
            list = rdao.getRecruitmentsWithFilter(filterMap);
            tempList = rdao.getRecruitmentsWithJobTerm(jobTerm);
            list = intersection(list, tempList);
            tempList = rdao.getRecruitmentsWithLocationTerm(cityTerm, distTerm, wardTerm);
            list = intersection(list, tempList);
            out.print(list);
            request.getSession().setAttribute("recruitmentSearchList", list);
            
            response.sendRedirect(urlBuilder.toString());
//            request.getRequestDispatcher("home.jsp").include(request, response);
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }

        //testing
        out.println("jobTerm: " + jobTerm);
        out.println("cityTerm: " + cityTerm);
        out.println("distTerm: " + distTerm);
        out.println("wardTerm: " + wardTerm);
        out.println("datePostedFilter: " + datePostedFilter);
        out.println("salaryFilter: " + salaryFilter);
//        out.println("salaryFilter: " + Integer.parseInt("Integer.MAX_VALUE"));
        out.println("experienceFilter: " + experienceFilter);
        out.println("fieldFilter: " + fieldFilter);
        out.println("jobTypeFilter: " + jobTypeFilter);
        out.println("educationFilter: " + educationFilter);
        out.println("workplaceFilter: " + workplaceFilter);
        out.println("languageFilter: " + languageFilter);
        
    }

    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }
        return list;
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

    public void TrashCan() {
        //salary filter:
//        if (Integer.parseInt(salaryFilter) != 0) {
//            String[] salaryCondition = new String[2];
//            switch (salaryFilter) {
//                case "1":
//                    salaryCondition[0] = "> 0";
//                    salaryCondition[1] = "<= 1000";
//                    filterMap.put("Salaries", salaryCondition);
//                    break;
//                case "2":
//                    salaryCondition[0] = "> 1000";
//                    salaryCondition[1] = "<= 5000";
//                    filterMap.put("Salaries", salaryCondition);
//                    break;
//                case "3":
//                    salaryCondition[0] = "> 5000";
//                    salaryCondition[1] = "<= 10000";
//                    filterMap.put("Salaries", salaryCondition);
//                    break;
//                case "4":
//                    salaryCondition[0] = "> 10000";
//                    salaryCondition[1] = "<= 50000";
//                    filterMap.put("Salaries", salaryCondition);
//                    break;
//                case "5":
//                    salaryCondition[0] = "> 50000";
//                    salaryCondition[1] = "<= 100000";
//                    filterMap.put("Salaries", salaryCondition);
//                    break;
//                case "6":
//                    filterMap.put("FieldID", "> 100000");
//                    break;
//            }
//        }
    }

}
