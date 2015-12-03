<%-- 
    Document   : createRecord
    Created on : Nov 20, 2015, 5:19:26 PM
    Author     : Dylan Lozo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Video Game Library</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">My Video Game Library</a></h1>
        <h2>Create New Video Game Record</h2>
        <form action="create" method="get">

            <!-- Used the new HTML5 email type to force the user to enter a name.-->
            Game Name: <input type="name" name="name" size="30"  
                          placeholder="Enter game's name here" required>
            <br><br>
            
            Genre: <input type="name" name="genre" size="30"  
                          placeholder="Enter game's genre here" required>
            <br><br>

            Description: <br>
            <textarea  name="description" maxlength="200" cols="60" rows="3"></textarea>
            <br><br>
            
            ESRB Rating: <input type="name" name="esrbRating" size="30"  
                          placeholder="Enter game's rating here" required>
            <br><br>
            
            <!-- Used the new HTML5 number type to force the user to enter a number.-->
            Your Five Star Rating: <input type="number" name="fiveStarRating" value='1' min="1" max="5" required>
            <br><br>
            
            <!-- Used the new HTML5 number type to force the user to enter a number.-->
            Price you Paid: <input type="number" name="price" value='0.00' step="any" min="0" required>
            <br><br>

            

            <input type="hidden" name="action" value="createRecord">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>

