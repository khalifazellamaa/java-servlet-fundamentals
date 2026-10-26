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
        
        HttpSession session = request.getSession();
        
        Map<String, Integer> itemMap = (Map<String, Integer>) session.getAttribute("itemMap");
        if (itemMap == null) {
            itemMap = new HashMap<>();
        }
        
        String newItem = request.getParameter("newItem");
        
        if (newItem != null && !newItem.trim().isEmpty()) {
            newItem = newItem.trim();
            itemMap.put(newItem, itemMap.getOrDefault(newItem, 0) + 1);
            session.setAttribute("itemMap", itemMap);
        }
        
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("items.html");
    }
}