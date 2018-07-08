/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf.servlets;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.util.json.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.jni.FileInfo;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author vishal
 */
@MultipartConfig
public class DecodeServlet extends HttpServlet {
String filePath="";
static  AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("ap-south-1").build();
DynamoDBMapper mapper=new DynamoDBMapper(client);
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
            out.println("<title>Servlet DecodeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DecodeServlet at " + request.getContextPath() + "</h1>");
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
        //PrintWriter out=response.getWriter();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"userDirectory.csv\"");
       
        // client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        
         Deque<FileInfo> files = new LinkedList<>();
          String fileName ="";
           StringBuilder test=new StringBuilder();
           Part str=request.getPart("record");
         InputStream is = null;
         try {
         is = str.getInputStream();
         ZipInputStream zin = new ZipInputStream(is );
         readZip(zin,str.getSubmittedFileName());
         //for Checking
        // JSONObject config = new JSONObject();
        //config.put("accessKeyId","AKIAJTDUQON57HZIW5JA");
        //config.put("secretAccessKey","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");
        //config.put("region","ap-south-1");
        //config.put("tableName","VIL_PDF_DATA");
        //d2csv d = new d2csv(config);
        // d2csv d = new d2csv();
        //String recordData=d.getData(config);
        //OutputStream outputStream = response.getOutputStream();
        //outputStream.write(recordData.getBytes());
        //outputStream.flush();
        //outputStream.close();
         
         response.sendRedirect("sucesss.jsp");
         
    }catch (Exception e) {
        e.printStackTrace();
    }
    }
    public  void readZip( ZipInputStream zin,String fileName ) throws Exception
    {
          ZipEntry ze = null;
      while ((ze = zin.getNextEntry()) != null) {
        System.out.println("Unzipping " + ze.getName());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    IOUtils.copy(zin, out);
       // ByteArrayInputStream(out);
    InputStream isFromFirstData=new ByteArrayInputStream(out.toByteArray());
    readData(isFromFirstData);
        zin.closeEntry();
      }
     
      zin.close();
    }
    public void init(){
      // Get the file location where it would be stored.
      filePath = getServletContext().getInitParameter("file-upload"); 
   }
@SuppressWarnings("ThrowableResultIgnored")
    public void readData(InputStream is) throws Exception{
         PDDocument document = PDDocument.load(is);
   String finalline="";
            document.getClass();
        System.out.println("inside read data");
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                String lines[] = pdfFileInText.split("\\r?\\n");
                //String lines[] = pdfFileInText.split("\\n");
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                InputStream input = classLoader.getResourceAsStream("pdfData.properties");
                Properties prop = new Properties();
		prop.load(input);
                System.out.println("kona-->"+prop.get("key"));
             String[] header=prop.get("key").toString().split(",");
            Map dataMap=new HashMap<String,String>();
             for(int i=0;i<header.length;i++){  
             
                for (String line : lines) {
                    finalline+=line;
                    if(line.contains(header[i]) && !line.contains("DOB :")){
                        //1header[i].
                        System.out.println("last index-->"+line.lastIndexOf(header[i]));
                        dataMap.put(header[i],line.split(header[i])[1].trim());
                        //int lastIndexOf = line.lastIndexOf(line); 
                       // line.las
                    }
                    
                    System.out.println(line);
                }
            }
             //System.out.println("datamap-->"+dataMap);
             PdfDataBean dataBean =new PdfDataBean();
             dataBean.setCustAadhaar(dataMap.get("Customer Aadhar Number").toString());
             dataBean.setCustFatherName(dataMap.get("Name of Father/Husband").toString());
             dataBean.setCustName(dataMap.get("Name of the Subscriber").toString());
             dataBean.setNationality(dataMap.get("Nationality").toString());
             dataBean.setGender(dataMap.get("Gender").toString());
             dataBean.setMobileNumber(Long.valueOf(dataMap.get("Mobile number").toString()));
             dataBean.setSimNumber(dataMap.get("SIM number").toString());
             dataBean.setSubStatus(dataMap.get("Status of Subscriber").toString());
             dataBean.setSubType(dataMap.get("Type of Connection").toString());
             dataBean.setTariffPlan(dataMap.get("Tariff Plan").toString().split(":")[1]);
             dataBean.setImsNumber(dataMap.get("IMSI number").toString());
             mapper.save(dataBean);
            }
    }
}
