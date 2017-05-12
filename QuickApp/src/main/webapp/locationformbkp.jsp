    <!DOCTYPE html>  
    <html lang="en"><head>  
    <meta charset="utf-8">  
    <style>
    h1 {
margin-left: 70px;
}
form li {
list-style: none;
margin-bottom: 5px;
}

form ul li label{
float: left;
clear: left;
width: 150px;
text-align: right;
margin-right: 10px;
font-family:Verdana, Arial, Helvetica, sans-serif;
font-size:14px;
}

form ul li input, select, span {
float: left;
margin-bottom: 10px;
}

form textarea {
float: left;
width: 350px;
height: 150px;
}

[type="submit"] {
clear: left;
margin: 20px 0 0 230px;
font-size:18px
}

p {
margin-left: 70px;
font-weight: bold;
}
    
    </style>	
    
    <title>Location  registration form</title>  
    </head>  
    <body onload="document.registration.district.focus();">  
    <h1>Location Registration Form</h1>  
    <p>Use tab keys to move from one input field to the next.</p>  
    <form name='registration' action="user" method="post">  
    <ul>  
    <li><label for="userid">District Name:</label></li>  
    <li><input type="text" name="district" size="12" /></li>  
    <li><label for="passid">Mandal Name:</label></li>  
    <li><input type="text" name="mname" size="12" /></li>  
    <li><label for="username">Village Name:</label></li>  
    <li><input type="text" name="vname" size="50" /></li>  
	<li><input type="submit" name="submit" value="Submit" /></li>	
    </form>  
    </body>  
    </html>  