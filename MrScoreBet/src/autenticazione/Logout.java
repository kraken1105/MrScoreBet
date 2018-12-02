package autenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import FBInterlocutor.APIUser;
import dao.UserDAO;
import dao.UserNotFoundException;
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
		response.setContentType("JSON");
		PrintWriter toFB = response.getWriter();
		//1) effettuo lo split del messaggio cifrato ricevuto
		String message[] = request.getParameter("signed_request").split("'");
		//2) decodifico 
		String signedreq = new String(Base64.decodeBase64(message[0]));
		String payload = new String(Base64.decodeBase64(message[1]));
		try {
			final Charset asciiCs = Charset.forName("US-ASCII");
			final Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(asciiCs.encode("d86e6c7a71084976e0d1747467dbd580").array(), "HmacSHA256");
			final String mac_data = new String(sha256_HMAC.doFinal(asciiCs.encode(payload).array())); 
			if(!mac_data.equals(signedreq)) toFB.print("BAD REQUEST");
			else {
				JSONObject req = new JSONObject(payload);
				String user_id = req.getString("user_id");
				try {
				User u = UserDAO.read(user_id);
				UserDAO.delete(u);
				String[] answer =new String[2];
				answer[0] = "https://localhost:8443/MrScoreBet/index.jsp";
				answer[1] = "success_code";
				toFB.print(new JSONObject(answer));
				}catch(UserNotFoundException e) {toFB.print("No such user in this application");}
				
			}
		} catch (JSONException|NoSuchAlgorithmException|SQLException e) {e.printStackTrace();}
		}
		
}

