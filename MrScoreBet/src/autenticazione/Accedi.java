package autenticazione;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import model.Bet;
import model.Image;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String userid=null, name = null, picture=null;
		int width=0, height=0;
		Boolean validity = false;
		
		try {
	    URL oauth = new URL(" https://graph.facebook.com/v3.2/oauth/access_token?" + 
	    		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&code="+code);
	    JSONObject json = useFBAPIs(oauth);
	    String token = json.getString("access_token"); //INUTILE PER ORA LEGGGERE EXPIRES_IN E TOKEN_TYPE
        
        URL mytoken = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"+
        		"client_id=2095469647430370" + 
	    		"&redirect_uri=https://localhost:8443"+request.getContextPath()+"/accedi"+ 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" +
	    		"&grant_type=client_credentials");
        
        JSONObject json2 = useFBAPIs(mytoken);
        String apptoken = json2.getString("access_token");
       
        URL oinspect = new URL("https://graph.facebook.com/debug_token?" + 
        		"input_token="+ token + 
        		"&access_token="+apptoken);
        
        JSONObject json3 = useFBAPIs(oinspect);
        validity = json3.getJSONObject("data").getBoolean("is_valid");
		userid = json3.getJSONObject("data").getString("user_id");
		

        URL extend = new URL("https://graph.facebook.com/v3.2/oauth/access_token?"+
        			 "grant_type=fb_exchange_token" + 
        			 "&client_id=2095469647430370" + 
        			 "&client_secret=d86e6c7a71084976e0d1747467dbd580" + 
        			 "&fb_exchange_token="+token);
        
        JSONObject json4 = useFBAPIs(extend);
        String longtermtoken = json4.getString("access_token");
        
        URL userinfos = new URL("https://graph.facebook.com/me?fields=" +
        		"name,picture"+ //tanti altri disponibili: RUOLO CI SERVE
        		"&access_token=" + longtermtoken);
        
        JSONObject json5 = useFBAPIs(userinfos);
        name = json5.getString("name");
        picture = json5.getJSONObject("picture").getJSONObject("data").getString("url");
        width = json5.getJSONObject("picture").getJSONObject("data").getInt("width");;
        height = json5.getJSONObject("picture").getJSONObject("data").getInt("height");;
        
		} catch(JSONException jex) {jex.printStackTrace();}
        
        if(validity) {
        	 LocalDateTime data = LocalDateTime.of(2017, Month.DECEMBER, 1, 15, 00);
             System.out.println(data.toString());
             
             Image img = new Image(picture,width,height);
             Bet lastPlayedBet = new Bet(1, null, null, 0);
             Bet toPlayBet = new Bet(2, null, data, 0);
             
             User utente = new User(userid, name, "admin", img, 0, lastPlayedBet, toPlayBet); 
             HttpSession sessione = request.getSession();
         	 sessione.setAttribute("utente", utente );
     		 response.sendRedirect("/MrScoreBet/app/user.jsp"); // usando questo cambia anche il link nel browser dell'utente
        	
        }
        
        
        // 5) Autenticazione locale dell'utente e salvataggio dell'access_token (nel DB)
        
        
        
        // 6) Reindirizzamento a app/user.jsp
        
        
    	
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