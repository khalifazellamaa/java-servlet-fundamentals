<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Résultat avec JavaBean</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        table { border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #333; padding: 10px 20px; }
        th { background-color: #3498db; color: white; }
    </style>
</head>
<body>
    <h1>Données reçues (via JavaBean)</h1>

    <jsp:useBean id="person" class="isep.lab1.bean.Person" scope="request" />

    <jsp:setProperty name="person" property="*" />

    <table>
        <tr>
            <th>Champ</th>
            <th>Valeur</th>
        </tr>
        <tr>
            <td>First Name</td>
            <td><jsp:getProperty name="person" property="firstName" /></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><jsp:getProperty name="person" property="lastName" /></td>
        </tr>
        <tr>
            <td>Birth Date</td>
            <td><jsp:getProperty name="person" property="birthDate" /></td>
        </tr>
        <tr>
            <td>Sex</td>
            <td><jsp:getProperty name="person" property="sex" /></td>
        </tr>
    </table>

    <br>
    <a href="form-jsp.html">← Retour au formulaire</a>

</body>
</html>