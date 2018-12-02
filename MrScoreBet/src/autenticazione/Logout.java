package autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("utente");	
		session.removeAttribute("token");
		session.setAttribute("errore", "Logout effettuato!");
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Richiesta POST inviata da FB quando un utente rimuove le autorizzazione o richiede la cancellazione dei dati all'app
		// cfr. https://developers.facebook.com/docs/apps/delete-data/
		
		
		
		
		doGet(request, response);
	}

}
