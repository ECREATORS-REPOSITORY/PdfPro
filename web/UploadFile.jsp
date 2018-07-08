


<!DOCTYPE html>
<html>
<head>
	<title>File Upload widget a Flat Responsive Widget Template :: w3layouts</title>
	<link rel="stylesheet" href="css/style.css">
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="File Upload widget Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
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
<h1>File Upload Widget</h1>
<div class="agile-its">
	<h2>File Upload</h2>
	<div class="w3layouts">
		<div class="photos-upload-view">
			<form id="upload" action="DecodeServlet" method="POST" enctype="multipart/form-data">
				<input type="hidden" id="MAX_FILE_SIZE" name="MAX_FILE_SIZE" value="300000" />
					<div class="agileinfo">
					<input type="submit" id="submit" class="choose" value="Choose file..">
					<h3>OR</h3>
						<input type="file" id="fileselect" name="record" multiple="multiple" />
						<div id="filedrag">drag and drop files here</div>
					</div>
                                         <input type="submit" />
			</form>
			<div id="messages">
				<p>Status Messages</p>
			</div>
            
        <form action="DataExtractor" method="POST">
            <%
                request.setAttribute("tableKey", "VIL");
            %>
            <button class="btn" type="submit" style="margin-left: 12%"><i class="fa fa-download"></i> Download Data in CSV</button>
        </form>
		</div>
		<div class="clearfix"></div>
		<script src="JS/filedrag.js"></script>


	</div>
</div>


<script type="text/javascript" src="JS/jquery.min.js"></script>


</body>
</html>