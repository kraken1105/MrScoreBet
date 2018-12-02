package autenticazione;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONObject;

import FBInterlocutor.APIUser;
import dao.UserDAO;
import model.User;

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
		
		if(request.getParameter("delete")!=null && request.getParameter("delete").equals("true")) { 	// Logout e cancellazione dell'account
			User u = (User) session.getAttribute("utente");
			String token = (String) session.getAttribute("token");
			session.removeAttribute("utente");	
			session.removeAttribute("token");
			
			URL deletePerms = new URL("https://graph.facebook.com/v3.2/"+u.getUserID()+"/permissions?access_token="+token);
			JSONObject json = APIUser.useFBAPIs(deletePerms, "DELETE");
			
			try {
				boolean success = json.getBoolean("success");
			
				if(success) {
					UserDAO.delete(u);
					session.setAttribute("errore", "Account correttamente rimosso!");
				} else {
					session.setAttribute("errore", "Si è verificato un errore nella rimozione dell'account!");
				}
			} catch (Exception e) {e.printStackTrace();}
		
		
		} else { 	// Solo logout
			session.removeAttribute("utente");	
			session.removeAttribute("token");
			session.setAttribute("errore", "Logout effettuato!");
		}
		
		response.sendRedirect(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Richiesta POST inviata da FB quando un utente rimuove le autorizzazione o richiede la cancellazione dei dati all'app
		// cfr. https://developers.facebook.com/docs/apps/delete-data/
		
		
		
		
		doGet(request, response);
	}

}
