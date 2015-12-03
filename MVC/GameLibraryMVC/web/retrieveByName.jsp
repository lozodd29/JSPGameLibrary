<%-- 
    Document   : getReportByDateRange.jsp
    Created on : Nov 20, 2015, 5:19:26 PM
    Author     : Dylan Lozo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Video Game Library</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">My Video Game Library</a></h1>
        <h2>Game Record</h2>
        <form action="retrieve" method="get">
            <input type="hidden" name="action" value="report">
            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>