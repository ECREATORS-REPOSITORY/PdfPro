<%-- 
    Document   : sucesss
    Created on : 7 Jul, 2018, 5:44:08 PM
    Author     : vishal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
      .btn {
    background-color: DodgerBlue;
    border: none;
    color: white;
    padding: 12px 30px;
    cursor: pointer;
    font-size: 20px;
}

/* Darker background on mouse-over */
.btn:hover {
    background-color: RoyalBlue;
}
  </style>
    </head>
    <body>
    <center>
      <div class="alert alert-success">
    <strong>Success!</strong> File Uploaded Successfully.
     </div>
        <form action="DataExtractor" method="POST">
              <%
                request.setAttribute("tableKey", "VIL");
            %>
            <button class="btn" type="submit"><i class="fa fa-download"></i> Download Data in CSV</button>
        </form>
    <br>
      <button type="submit" value="Back" id="res" style="margin-left: 10px;padding-left: 0px;margin-top: 20px;text-align:center;background-color:#ff3333;height:30px;width:110px;color:white;"onclick="window.location='UploadFile.jsp'">
          <span class="glyphicon glyphicon-arrow-left"></span> </button>
      <input type="button"  value="Logout" style="padding-left: 0px;background-color:#00e64d;margin-left: 30x;height:30px;width:120px;color:white;"onclick="window.location='index.html'"/>
    </center>
</div>
    </body>
</html>
