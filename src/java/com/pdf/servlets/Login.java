/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vishal
 */
public class Login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
       // processRequest(request, response);
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
       String username=request.getParameter("username");
       String password=request.getParameter("password");
       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();       
       InputStream input = classLoader.getResourceAsStream("pdfData.properties");
        System.out.println("Loadinf");
        Properties prop = new Properties();
        prop.load(input);
       String Credential= prop.getProperty("Credential");
       String str[]=Credential.split(",");
          System.out.println("Loadinf222");
       boolean login=false;
       for(int i=0;i<str.length;i++)
       {
           String getCrd[]=str[i].split("@");
          if(getCrd[0].equalsIgnoreCase(username) && getCrd[1].equalsIgnoreCase(password))
          {
              login=true;
              break;
          }
       }
        
        if(login)
        {
            System.out.println("Inside login");
            response.sendRedirect("UploadFile.jsp");
        }
        else
        {
            response.sendRedirect("index.html");
            
            
         }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  
    }
}
