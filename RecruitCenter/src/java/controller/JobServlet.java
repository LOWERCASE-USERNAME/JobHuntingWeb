/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.CompaniesDAO;
import DAL.RecruitmentsDAO;
import DAL.SkillAndTitleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.Field;
import model.JobType;
import model.Recruitments;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Degree;
import model.Workplace;
/**
 *
 * @author dell
 */
public class JobServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JobServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JobPostingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    //TODO: handles retrieving job
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    //TODO: handles posting job
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RecruitmentsDAO rcDAO = new RecruitmentsDAO();
        CompaniesDAO cdao = new CompaniesDAO();
        SkillAndTitleDAO satdao = new SkillAndTitleDAO();
        PrintWriter out = response.getWriter();
        
        //retrieving parameter data
        String company_param = request.getParameter("company");
        String company_size_param = request.getParameter("company_size");
        String company_website_param = request.getParameter("company_website");
        String contact_name_param = request.getParameter("contact_name");
        String contact_email_param = request.getParameter("contact_email");
        String contact_phone_param = request.getParameter("contact_phone");
        String job_title_param = request.getParameter("job_title");
        int job_type_param = Integer.parseInt(request.getParameter("job_type")); //index of value in JOB_TYPES array
        int job_field_param = Integer.parseInt(request.getParameter("job_field")) ;//index of value in FIELD array
        String job_location_param = request.getParameter("job_location");
        String job_salary_param = request.getParameter("job_salary");
        String neg_param = request.getParameter("neg");
        String job_expire_param = request.getParameter("job_expire");
        String job_skill_param = request.getParameter("job_skill");
        String job_gender_param = request.getParameter("job_gender");
        int job_degree_param = Integer.parseInt(request.getParameter("job_degree")) ;
        int job_workplace_param = Integer.parseInt(request.getParameter("job_workplace"));
        String job_desc_param = request.getParameter("job_desc");
        String job_qualification_param = request.getParameter("job_qualification");
        String job_benefit_param = request.getParameter("job_benefit");

        //testing
//        out.println("company_param = " + company_param);
//        out.println("company_size_param = " + company_size_param);
//        out.println("company_website_param = " + company_website_param);
//        out.println("contact_name_param = " + contact_name_param);
//        out.println("contact_email_param = " + contact_email_param);
//        out.println("contact_phone_param = " + contact_phone_param);
//        out.println("job_title_param = " + job_title_param);
//        out.println("job_type_param = " + job_type_param);
//        out.println("job_field_param = " + job_field_param);
//        out.println("job_location_param = " + job_location_param);
//        out.println("job_salary_param = " + job_salary_param);
//        out.println("neg_param = " + neg_param);
//        out.println("job_expire_param = " + job_expire_param);
//        out.println("job_skill_param = " + job_skill_param);
//        out.println("job_gender_param = " + job_gender_param);
//        out.println("job_degree_param = " + job_degree_param);
//        out.println("job_workplace_param = " + job_workplace_param);
//        out.println("job_desc_param = " + job_desc_param);
//        out.println("job_qualification_param = " + job_qualification_param);
//        out.println("job_benefit_param = " + job_benefit_param);

        //create a recruitment object
        Recruitments rc = new Recruitments();
        rc.setContactName(contact_name_param);
        rc.setContactEmail(contact_email_param);
        rc.setContactPhoneNumber(contact_phone_param);
        rc.setJobTitle(job_title_param);
        rc.setJobTypeID(job_type_param);
        Company existedCompany = null;
        if((existedCompany = cdao.getCompanyWithName(company_param)) == null){//if company do not exist in database already
//            TODO: add new company to database
            Company comp = new Company();
            comp.setCompanyName(company_param);
            comp.setCompanyWebsite(company_website_param);
            comp.setCompanyAddress(job_location_param);
            comp.setCompanyReviews("");
            comp.setCompanyRatings(0);
            comp.setCompanyNumOfRatings(0);
            try{
                cdao.insertCompany(comp);
                rc.setCompanyID(cdao.getCompanyWithName(company_param).getCompanyID());
            }catch(Exception e){
                e.printStackTrace(out);
            }
        } else {
            rc.setCompanyID(existedCompany.getCompanyID()); //if exist then set company id into recruitments record
        }
        rc.setFieldID(job_field_param);
        rc.setLocation(job_location_param);
        if(neg_param != null){
            rc.setSalaries(job_salary_param + "(negotiable)");
        } else {
            rc.setSalaries(job_salary_param);
        }
        
        rc.setPostedDate(new java.sql.Date(System.currentTimeMillis()));
        
        //TODO: expire date resolve
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        java.util.Date utilDate = null;
        try {
            utilDate = format.parse(job_expire_param);
        } catch (Exception e) {
            // Handle the parse exception appropriately
            e.printStackTrace(out);
        }
        Date expireSQLDate = new Date(utilDate.getTime());
        
        rc.setExpDate(expireSQLDate);
        rc.setSkillAndTitleID(UUID.fromString(job_skill_param));
        rc.setGender(job_gender_param);
        rc.setDegree(Degree.DEGREES[job_degree_param]);
        rc.setJobDescription(
                "Company size: " + company_size_param + "\n"
                + "Preferred Workplace: " + Workplace.WORKPLACES[job_workplace_param]  + "\n"
                + "Job Description:\n " + job_desc_param + "\n"
                + "Job Qualification: \n" + job_qualification_param + "\n"
                + "Job Benefit: \n" + job_benefit_param + "\n"
        );
        
        out.println(rc.toString());
        //send it to the server
        try {
            rcDAO.insertRecruitment(rc);
        } catch (Exception ex) {
            ex.printStackTrace(out);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
