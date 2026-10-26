<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test JSP</title>
</head>
<body>
    <%! 
        double x = Math.random(); 
    %>
    
    <h1>Le nombre aléatoire est :</h1>
    <%= x %>

</body>
</html>