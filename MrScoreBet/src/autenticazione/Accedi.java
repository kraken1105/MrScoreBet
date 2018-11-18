package autenticazione;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Accedi
 */
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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
	    String scope = request.getParameter("scope");
	    
	    URL oauth = new URL(" https://graph.facebook.com/v3.2/oauth/access_token?" + 
	    		"client_id=2095469647430370" + 
	    		"&redirect_uri=localhost:8080"+request.getContextPath()+"/user.jsp" + 
	    		"&client_secret=d86e6c7a71084976e0d1747467dbd580" + 
	    		"&code="+code);
	    		
        HttpURLConnection connection = (HttpURLConnection) oauth.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                connection.getInputStream()));
        
        StringBuilder sb = new StringBuilder();
        String line,token=null,tokentype,expires;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        
        try {
			JSONObject json = new JSONObject(sb.toString());
			token = json.getString("access_token");
	        tokentype = json.getString("token_type");
	        expires = json.getString("expires_in");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        URL oinspect = new URL(" https://graph.facebook.com/debug_token?" + 
        		"input_token="+ token + 
        		"&access_token=62d1ca33988d65db393e1bda9f06c58b");
	    		
        HttpURLConnection connection2 = (HttpURLConnection) oinspect.openConnection();
        connection2.setRequestMethod("GET");
        BufferedReader in2 = new BufferedReader(
                                new InputStreamReader(
                                connection2.getInputStream()));
        
        StringBuilder sb2 = new StringBuilder();
        while ((line = in2.readLine()) != null) {
            sb2.append(line);
        }
        in.close();
        
        String validity="false",userid="0000";
        try {
			JSONObject json = new JSONObject(sb2.toString());
			validity = json.getString("is_valid");
	        userid = json.getString("user_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        if(validity.equals("true")) {
        HttpSession sessione = request.getSession();
    	sessione.setAttribute("utente", userid );
    	request.getRequestDispatcher( "/user.jsp" ).forward(request,response);
        }
    	
	}

}