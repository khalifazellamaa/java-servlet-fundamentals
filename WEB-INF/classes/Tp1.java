import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*; // Nécessaire pour HttpServlet et Cookie

public class Tp1 extends HttpServlet { // ⬅️ On utilise HttpServlet

    // On utilise doPost car le formulaire est en method="post"
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Encodage UTF-8 pour les accents
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // 2. Récupération des paramètres
        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String sex       = request.getParameter("sex");
        
        // 3. Création des cookies
        Cookie c1 = new Cookie("firstName", firstName != null ? firstName : "");
        Cookie c2 = new Cookie("lastName", lastName != null ? lastName : "");
        Cookie c3 = new Cookie("birthDay", birthDate != null ? birthDate : "");
        Cookie c4 = new Cookie("Sex", sex != null ? sex : "");
        
        // Durée de vie : 1 semaine
        c1.setMaxAge(60 * 60 * 24 * 7); 
        c2.setMaxAge(60 * 60 * 24 * 7);
        c3.setMaxAge(60 * 60 * 24 * 7);
        c4.setMaxAge(60 * 60 * 24 * 7);
        
        // Ajout des cookies (Maintenant ça marche car response est HttpServletResponse)
        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);
        
        // 4. Formatage de la date
        String formattedDate = "";
        if (birthDate != null && birthDate.contains("-")) {
            String[] parts = birthDate.split("-");
            formattedDate = parts[1] + "/" + parts[2] + "/" + parts[0];
        }
        
        // 5. Génération du HTML
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"fr\">");
        out.println("<head>");
        out.println("  <meta charset=\"UTF-8\">");
        out.println("  <title>Résultat</title>");
        out.println("  <style>body { font-family: Arial; margin: 40px; } table { border-collapse: collapse; } th, td { border: 1px solid #333; padding: 10px; }</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <h1>Données reçues</h1>");
        out.println("  <table>");
        out.println("    <tr><th>Champ</th><th>Valeur</th></tr>");
        out.println("    <tr><td>First Name</td><td>" + firstName + "</td></tr>");
        out.println("    <tr><td>Last Name</td><td>" + lastName + "</td></tr>");
        out.println("    <tr><td>Birth Date</td><td>" + formattedDate + "</td></tr>");
        out.println("    <tr><td>Sex</td><td>" + sex + "</td></tr>");
        out.println("  </table>");
        out.println("  <br><a href=\"Tp1.html\">← Retour au formulaire</a>");
        out.println("  <br><br><a href=\"ReadCookie\"><strong>Voir mes cookies →</strong></a>"); 
        out.println("</body></html>");
    }

    // Optionnel : Rediriger si quelqu'un tape l'URL directement (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("Tp1.html");
    }
}