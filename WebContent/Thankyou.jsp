<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ThankYou</title><style>
body{
     
     background-color : #262626;
	 }
 form{

      text-align : left;
	  margin-top :100px;
	  margin-bottom:130px;
	  margin-left :490px;
	  margin-right :390px;
      background: #000;
      padding: 2em 1em;
      font-family: helvetica, sans-serif;
	  display: -webkit-box;
  display: -moz-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;
  -webkit-box-flex: center;
  -moz-box-flex: center;
  -webkit-flex: center;
  -ms-flex: center;
  flex: center;
  -webkit-justify-content: center;
  -moz-justify-content: center;
  justify-content: center;
  -webkit-box-pack: center;
  -moz-box-pack: center;
  -ms-flex-pack: center;
  -webkit-align-items: center;
  align-items: center;
      
       border-radius: 5px;
	  }
  div {
text-align: left;
margin-top :15px;
margin-left :45px;
margin-right :15px;
	
    background-color:  black;
	width: 300px;
	height: 150px;
    border:black ;
    padding: 25px;
    margin: 5px;
	 max-width: 500px;
  width: 80%;
	border-radius: 5px;
     }
h2{
   color : white;
   font-family : helvetica ,sans-serif;
   margin-left:400px;
   
  }
   input {
  width: 100%;
  margin: 2em 3% 0;
  border: none;
  background: #1fd100;
  padding: 1em 0;
  font-size: 1.3em;
  clear: both;
  color: #000;
 
  border-radius: 5px;
  
  cursor: pointer;
}
 </style>
</head>
<body>
<h2>Thanks for using automated ${requestScope.message} reporter</h2>
<form>
<div>

<a href="index.html" ><input type="button" value="Logout"></a>
</div>
</form>
</body>
</html>