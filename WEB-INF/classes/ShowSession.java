import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        String fullName = (String) session.getAttribute("fullName");
        String dobStr = (String) session.getAttribute("dob");
        
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Session</title></head><body>");
        
        // CAS 1 : Première visite ou infos manquantes -> Afficher le formulaire
        if (visitCount == null || fullName == null || dobStr == null) {
            session.setAttribute("visitCount", 1);
            out.println("<h2>Welcome on my site</h2>");
            out.println("<p>Veuillez entrer vos informations :</p>");
            out.println("<form method='post'>");
            out.println("  Nom complet : <input type='text' name='fullName' required><br><br>");
            out.println("  Date de naissance : <input type='date' name='dob' required><br><br>");
            out.println("  <button type='submit'>Envoyer</button>");
            out.println("</form>");
        } 
        // CAS 2 : Utilisateur connu -> Calculer l'anniversaire
        else {
            int newCount = visitCount + 1;
            session.setAttribute("visitCount", newCount);
            
            out.println("<h2>Welcome back, " + fullName + " !</h2>");
            out.println("<p>Nombre de visites : " + newCount + "</p>");
            
            try {
                // Calcul des jours avant l'anniversaire
                LocalDate dob = LocalDate.parse(dobStr);
                LocalDate today = LocalDate.now();
                LocalDate nextBirthday = dob.withYear(today.getYear());
                
                if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
                    nextBirthday = nextBirthday.plusYears(1);
                }
                
                long daysUntilBirthday = ChronoUnit.DAYS.between(today, nextBirthday);
                
                out.println("<h3>Hi, " + fullName + ". There are <span style='color:red'>" 
                          + daysUntilBirthday + "</span> days to your birthday.</h3>");
            } catch (Exception e) {
                out.println("<p>Erreur lors du calcul de la date.</p>");
            }
        }
        
        out.println("<br><a href='ShowSession'>Rafraîchir</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        session.setAttribute("fullName", request.getParameter("fullName"));
        session.setAttribute("dob", request.getParameter("dob"));
        
        response.sendRedirect("ShowSession");
    }
}