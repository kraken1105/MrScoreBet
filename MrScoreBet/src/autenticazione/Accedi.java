package autenticazione;


import java.io.*;
import java.net.*;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.*;

import FBInterlocutor.APIUser;
import dao.*;
import model.*;

@WebServlet(urlPatterns={"/accedi"})
public class Accedi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String apptoken = null;
       
    public Accedi() {    	
        super();
    }
	
    public static String getApptoken() {return apptoken;}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String userid=null, nome_cognome = null, picture=null, ruolo=null, token = null;
		int width=0, height=0;
		Boolean validity = false;
		Image img = null;
		//page-id Mrscorebet = 1047502742098391
		// (2) Conferma dell'identità
		try {
	    URL oauth = new URL(" https://graph.facebook.com/v3.2/oauth/access_token?" + 
	    		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&code="+code);
	    JSONObject json = APIUser.useFBAPIs(oauth);
	    token = json.getString("access_token"); //INUTILE PER ORA LEGGERE EXPIRES_IN E TOKEN_TYPE
        
	    //Prelievo del ruolo (che in realtà adesso sono ATTIVITA') dell'utente nella pagina Mrscorebet
	    
	    URL activity = new URL("https://graph.facebook.com/v3.2/me/accounts?"
	    		+ "access_token="+token);
	    JSONObject jsonRole = APIUser.useFBAPIs(activity);
	    ruolo = verifyTasks(jsonRole.getJSONArray("data").getJSONObject(0).getJSONArray("tasks"));
	    
	    // Prelievo app token
        URL mytoken = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"+
        		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&grant_type=client_credentials");
        
        JSONObject json2 = APIUser.useFBAPIs(mytoken);
        apptoken = json2.getString("access_token");
       
        
        // (3) Ispezione dell'access token
        URL oinspect = new URL("https://graph.facebook.com/debug_token?" + 
        		"input_token="+ token + 
        		"&access_token="+apptoken);
        
        JSONObject json3 = APIUser.useFBAPIs(oinspect);
        validity = json3.getJSONObject("data").getBoolean("is_valid");
		userid = json3.getJSONObject("data").getString("user_id");
		
		
		// (4) Estensione dell'access token
        URL extend = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"+
        			 "grant_type=fb_exchange_token" + 
        			 "&client_id=2095469647430370" + 
        			 "&client_secret=d86e6c7a71084976e0d1747467dbd580" + 
        			 "&fb_exchange_token="+token);
        
        JSONObject json4 = APIUser.useFBAPIs(extend);
        String longtermtoken = json4.getString("access_token");
        
        
        // Prelievo delle informazioni personali
        URL userinfos = new URL("https://graph.facebook.com/me?fields=" +
        		"name,picture"+ //tanti altri disponibili: RUOLO CI SERVE
        		"&access_token=" + longtermtoken);
        
        JSONObject json5 = APIUser.useFBAPIs(userinfos);
        nome_cognome = json5.getString("name");
        picture = json5.getJSONObject("picture").getJSONObject("data").getString("url");
        width = json5.getJSONObject("picture").getJSONObject("data").getInt("width");
        height = json5.getJSONObject("picture").getJSONObject("data").getInt("height");
        
        img = new Image(picture,width,height);
        
		} catch(JSONException jex) {jex.printStackTrace();}
        
		
		// (5) Login dell'utente localmente al server
		User utente = null;
		try {
			
			try {
				utente = UserDAO.read(userid);	// utente già presente nel db
				utente.setImage(img);
			} catch(UserNotFoundException|SQLException e) {
				///////////// [TO-DO] bisogna leggere il ruolo veramente
				utente = new User(userid, nome_cognome, ruolo, 0, null, SchedinaDAO.getToPlayBet(), img);
				UserDAO.create(utente); // creazione nuovo utente
			}		
		
		} catch (SQLException e) {e.printStackTrace();}
		
		
		// (6) Reindirizzamento
        if(validity) {
        	 HttpSession sessione = request.getSession();
         	 sessione.setAttribute("utente", utente );
         	 sessione.setAttribute("token", token);
         	 sessione.setAttribute("errore", "null");	// reset dell'errore
     		 response.sendRedirect("/MrScoreBet/app/user.jsp");
        	
        } else response.sendRedirect("/MrScoreBet/app/user.jsp");
    	
	}
	
	
	
	
	private String verifyTasks(JSONArray jsonArray) throws JSONException {
		String ruolo = "utente";
		for(int i=0; i<jsonArray.length();i++) {
			if(jsonArray.getString(i).equals("MANAGE")) {
				ruolo = "admin";
				break;
			}
		}
		return ruolo;
	}

}