import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // 1. Récupérer la session
        HttpSession session = request.getSession();
        
        // 2. Récupérer ou créer la Map (Pour l'Extra : stocke l'item et son nombre)
        // On utilise un HashMap pour gérer les doublons et compter les occurrences
        Map<String, Integer> itemMap = (Map<String, Integer>) session.getAttribute("itemMap");
        if (itemMap == null) {
            itemMap = new HashMap<>();
        }
        
        // 3. Récupérer le paramètre envoyé par le formulaire
        String newItem = request.getParameter("newItem");
        
        // 4. Si l'item n'est pas vide, l'ajouter et mettre à jour le compteur
        if (newItem != null && !newItem.trim().isEmpty()) {
            newItem = newItem.trim(); // Enlever les espaces inutiles
            
            // Si l'item existe déjà, on incrémente son compteur, sinon on le crée avec 1
            itemMap.put(newItem, itemMap.getOrDefault(newItem, 0) + 1);
            
            // Sauvegarder la map mise à jour dans la session
            session.setAttribute("itemMap", itemMap);
        }
        
        // 5. Afficher la page HTML avec la liste
        out.println("<!DOCTYPE html><html lang='fr'><head>");
        out.println("<meta charset='UTF-8'><title>Liste des Items</title>");
        out.println("<style>body { font-family: Arial; margin: 40px; } table { border-collapse: collapse; } th, td { border: 1px solid #333; padding: 10px; }</style>");
        out.println("</head><body>");
        
        out.println("<h1>Contenu de votre liste :</h1>");
        
        if (itemMap.isEmpty()) {
            out.println("<p>Votre liste est vide.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Item</th><th>Fois saisi (Extra)</th></tr>");
            
            // Parcourir la Map pour afficher chaque item et son nombre
            for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
                out.println("<tr>");
                out.println("<td>" + entry.getKey() + "</td>");
                out.println("<td>" + entry.getValue() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        
        out.println("<br><a href='items.html'>← Ajouter un autre item</a>");
        out.println("</body></html>");
    }

    // Redirection si l'utilisateur tape l'URL directement (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("items.html");
    }
}