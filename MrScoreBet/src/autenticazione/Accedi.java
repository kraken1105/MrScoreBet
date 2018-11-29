package autenticazione;


import java.io.*;
import java.net.*;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.*;

import dao.*;
import model.*;

/**
 * Servlet implementation class Accedi
 */
@WebServlet(urlPatterns={"/accedi"})
public class Accedi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accedi() {    	
        super();
        // TODO Auto-generated constructor stub
    }
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String userid=null, nome_cognome = null, picture=null;
		int width=0, height=0;
		Boolean validity = false;
		Image img = null;
		
		// (2) Confirma dell'identità
		try {
	    URL oauth = new URL(" https://graph.facebook.com/v3.2/oauth/access_token?" + 
	    		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&code="+code);
	    JSONObject json = useFBAPIs(oauth);
	    String token = json.getString("access_token"); //INUTILE PER ORA LEGGERE EXPIRES_IN E TOKEN_TYPE
        
	    
	    // Prelievo app token
        URL mytoken = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"+
        		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&grant_type=client_credentials");
        
        JSONObject json2 = useFBAPIs(mytoken);
        String apptoken = json2.getString("access_token");
       
        
        // (3) Inspezione dell'access token
        URL oinspect = new URL("https://graph.facebook.com/debug_token?" + 
        		"input_token="+ token + 
        		"&access_token="+apptoken);
        
        JSONObject json3 = useFBAPIs(oinspect);
        validity = json3.getJSONObject("data").getBoolean("is_valid");
		userid = json3.getJSONObject("data").getString("user_id");
		
		
		// (4) Estensione dell'access token
        URL extend = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"+
        			 "grant_type=fb_exchange_token" + 
        			 "&client_id=2095469647430370" + 
        			 "&client_secret=d86e6c7a71084976e0d1747467dbd580" + 
        			 "&fb_exchange_token="+token);
        
        JSONObject json4 = useFBAPIs(extend);
        String longtermtoken = json4.getString("access_token");
        
        
        // Prelievo delle informazioni personali
        URL userinfos = new URL("https://graph.facebook.com/me?fields=" +
        		"name,picture"+ //tanti altri disponibili: RUOLO CI SERVE
        		"&access_token=" + longtermtoken);
        
        JSONObject json5 = useFBAPIs(userinfos);
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
			} catch(UserNotFoundException|SQLException e) {
				///////////// [TO-DO] bisogna leggere il ruolo veramente
				utente = new User(userid, nome_cognome, "admin", 0, null, SchedinaDAO.getToPlayBet(), img); // creazione nuovo utente
			}		
		
		} catch (SQLException e) {e.printStackTrace();}
		
		
		// (6) Reindirizzamento
        if(validity) {
        	 HttpSession sessione = request.getSession();
         	 sessione.setAttribute("utente", utente );
     		 response.sendRedirect("/MrScoreBet/app/user.jsp");
        	
        } else {
        	
        	// [TO-DO] Dobbiamo gestire eventuali token scaduti reindirizzando a una pagina di errore/login
        	
        }
    	
	}
	
	
	
	
	public JSONObject useFBAPIs(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader in;
        if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
        	in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	System.out.println(connection.getResponseCode());
        } else {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        	System.out.println(connection.getResponseCode());
        	//bisognerebbe gestire le eccezioni come questa
        }
        
        StringBuilder sb = new StringBuilder();
        String line=null;
        while ((line = in.readLine()) != null) {
            sb.append(line);
            System.out.println(line);
        }
        in.close();
        
        JSONObject json=null;
        try {
			json = new JSONObject(sb.toString());
		} catch (JSONException e) {e.printStackTrace();}
        return json;
	}

}