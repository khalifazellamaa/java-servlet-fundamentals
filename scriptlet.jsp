<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test JSP</title>
</head>
<body>
    <h1>Informations du Système</h1>

    <% 
        Date currentDate = new Date(); 
    %>
    <div class="info-box">
        <strong>Heure actuelle sur le serveur :</strong> 
        <%= currentDate.toString() %>
    </div>

    <div class="info-box">
        <strong>Adresse IP de votre machine (Client) :</strong> 
        <%= request.getRemoteHost() %>
    </div>

</body>
</html>