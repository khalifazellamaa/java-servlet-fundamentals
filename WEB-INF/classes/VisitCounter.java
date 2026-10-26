import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        int visitCount = 1;
        Cookie[] cookies = request.getCookies();
        boolean cookieFound = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitCount")) {
                    try {
                        visitCount = Integer.parseInt(c.getValue()) + 1;
                    } catch (NumberFormatException e) {
                        visitCount = 1;
                    }
                    cookieFound = true;
                    break;
                }
            }
        }

        Cookie visitCookie = new Cookie("visitCount", String.valueOf(visitCount));
        visitCookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(visitCookie);

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"fr\">");
        out.println("<head>");
        out.println("  <meta charset=\"UTF-8\">");
        out.println("  <title>Compteur de visites</title>");
        out.println("  <style>");
        out.println("    body { font-family: Arial, sans-serif; margin: 40px; text-align: center; }");
        out.println("    .counter { font-size: 24px; color: #e74c3c; font-weight: bold; }");
        out.println("  </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <h1>Bienvenue sur le compteur de visites !</h1>");
        
        if (cookieFound) {
            out.println("  <p>Vous avez visité cette page <span class='counter'>" + visitCount + "</span> fois.</p>");
        } else {
            out.println("  <p>C'est votre <span class='counter'>première</span> visite sur cette page !</p>");
        }
        
        out.println("  <br>");
        out.println("  <a href='Tp1.html'>← Retour au formulaire</a><br><br>");
        out.println("  <a href='VisitCounter'>Rafraîchir la page pour voir le compteur augmenter</a>");
        out.println("</body>");
        out.println("</html>");
    }
}