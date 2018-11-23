package autenticazione;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import model.User;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) L'utente ha già contattato Fb
		// TO-DO: controllare se l'utente ha veramente fatto l'accesso, potrebbe averlo negato e quindi qui ho un errore
		// vedere documentazione Facebook
		String code = request.getParameter("code");
	    String scope = request.getParameter("scope");
	    
	    
	    // 2) Richiesta access_token a Fb fornendo il code
	    URL oauth = new URL(" https://graph.facebook.com/v3.2/oauth/access_token?" + 
	    		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&code="+code);
	    		
        HttpURLConnection connection = (HttpURLConnection) oauth.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader in;
        if (connection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
        	in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	System.out.println(connection.getResponseCode());
        } else {
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line,token = null,tokentype;
        Integer expires;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        JSONObject json;
        try {
			json = new JSONObject(sb.toString());
			token = json.getString("access_token");
			System.out.println(token);
	        tokentype = json.getString("token_type");
	        System.out.println(tokentype);
	        expires = json.getInt("expires_in");
	        System.out.println(expires);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        // 3) Ispezione dell'access_token
        // TO-DO: se non è valido (scaduto o revocato, bisogna settare una variabile di sessione e reindirizzare l'utente a index.jsp)
       
        URL oinspect = new URL("https://graph.facebook.com/debug_token?" + 
        		"input_token="+ token + 
        		"&access_token=62d1ca33988d65db393e1bda9f06c58b");
	    		
        HttpURLConnection connection2 = (HttpURLConnection) oinspect.openConnection();
        connection2.setRequestMethod("GET");
        
        BufferedReader in2;
        if (connection2.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
        	in2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
        	System.out.println("GOT THE INFOS");
        } else {
            in2 = new BufferedReader(new InputStreamReader(connection2.getErrorStream()));
            System.out.println("400");
        }
        
        StringBuilder sb2 = new StringBuilder();
        while ((line = in2.readLine()) != null) {
            sb2.append(line);
            System.out.println(line);
        }
        in2.close();
        
        String userid="0000";
        Boolean validity=false;
        try {
			JSONObject json2 = new JSONObject(sb2.toString());
			validity = json2.getBoolean("is_valid");
	        userid = json2.getString("user_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
        
        
        // 4) Estensione dell'access_token (cfr. prima risposta di https://stackoverflow.com/questions/27294165/how-should-a-facebook-user-access-token-be-consumed-on-the-server-side)
        
        
        
        // 5) Autenticazione locale dell'utente e salvataggio dell'access_token (nel DB)
        User utente = new User(Integer.parseInt(userid), "Giggino", "Esposito", "admin", 0);        
        
        // 6) Reindirizzamento a app/user.jsp
        //if(validity) {
        	HttpSession sessione = request.getSession();
        	sessione.setAttribute("utente", utente);
        	System.out.println("[DEBUG] Loggato "+utente.getNome()+" "+utente.getCognome()+" "+utente.getUserID());
        	//request.getRequestDispatcher( "/app/user.jsp" ).forward(request,response);
        	response.sendRedirect("/MrScoreBet/app/user.jsp"); // usando questo cambia anche il link nel browser dell'utente
        //}
        
    	
	}

}