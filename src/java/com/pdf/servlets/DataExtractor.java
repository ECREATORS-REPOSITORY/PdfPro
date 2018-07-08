/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf.servlets;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akhil
 */

public class DataExtractor extends HttpServlet {

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
            throws ServletException, IOException, JSONException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"userDirectory.csv\"");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"userDirectory.csv\"");
        InputStream input = classLoader.getResourceAsStream("pdfData.properties");
        Properties prop = new Properties();
        prop.load(input);
        JSONObject config = new JSONObject();
        config.put("accessKeyId","AKIAJTDUQON57HZIW5JA");
        config.put("secretAccessKey","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");
        config.put("region","ap-south-1");
       // System.out.println("asdf-> "+request.getAttribute("tableKey").toString());
        config.put("tableName",prop.getProperty("VIL"));
        d2csv d = new d2csv();
        String recordData=d.getData(config);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(recordData.getBytes());
        outputStream.flush();
        outputStream.close();
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
            throws ServletException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException {
        try {
            System.out.println(request.getAttribute("tableKey"));
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
}
