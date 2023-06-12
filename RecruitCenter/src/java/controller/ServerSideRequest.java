/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ServerSideRequest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String baseURL = request.getParameter("URL");
        String searchTerm = request.getParameter("term");
        boolean usingCode = (boolean) Boolean.parseBoolean(request.getParameter("usingCode"));
        URL url;
        if(!searchTerm.isEmpty()){
            String encodedSearchTerm = URLEncoder.encode(searchTerm, "UTF-8");
            if(usingCode){
                baseURL = baseURL + "" + searchTerm;
            } else {
                baseURL = baseURL + "search/?q=" + encodedSearchTerm;
            }
        }
        url = new URL(baseURL);
        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method (GET, POST, etc.)
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();
//        out.println("Response Code: " + responseCode);

        // Read the response from the input stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        StringBuilder responseData = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseData.append(line);
        }
        reader.close();
        
        Gson gson = new Gson();
        String jsonData = gson.toJson(responseData);
//        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(jsonData);
        // Print the response
//        out.println("Response: " + responseData.toString());

        // Disconnect the connection
        connection.disconnect();
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
