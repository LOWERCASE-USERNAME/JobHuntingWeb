/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

public class FilterUser implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter out=resp.getWriter();  
        out.println(request.getServletContext().getAttribute("userid"));
        if(request.getServletContext().getAttribute("userid") != null){  
            chain.doFilter(req, resp);//sends request to next resource  
        }  
        else{  
            response.sendError(404, "You are accessing without the role: user");
//            out.print("username or password error!");  
        //RequestDispatcher rd=req.getRequestDispatcher("index.html");  
        //rd.include(req, resp);  
        }  

    }

    public void destroy() {
    }

}