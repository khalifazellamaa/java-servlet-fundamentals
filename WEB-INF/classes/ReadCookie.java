import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Vos Cookies :</h1>");
        
        // Récupérer tous les cookies envoyés par le navigateur
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            out.println("<ul>");
            for (Cookie c : cookies) {
                out.println("<li><strong>" + c.getName() + "</strong> : " + c.getValue() + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>Aucun cookie trouvé. Avez-vous rempli le formulaire ?</p>");
        }
        
        out.println("<br><a href='Tp1.html'>Retour au formulaire</a>");
        out.println("</body></html>");
    }
}