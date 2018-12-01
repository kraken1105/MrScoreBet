package autorizzazione;

import java.io.IOException;
import java.net.URL;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import FBInterlocutor.APIUser;
import autenticazione.Accedi;


/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthenticationRequiredFilter implements Filter {
	

    /**
     * Default constructor. 
     */
    public AuthenticationRequiredFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
    
		HttpSession session = req.getSession();
        if(session.getAttribute("utente")==null)
        	res.sendRedirect(req.getContextPath()+"/index.jsp");
		else
			try {
				if(!isvalidToken(session.getAttribute("token"))) {
					session.setAttribute("token", null);
					res.sendRedirect(req.getContextPath()+"/Logout");
				}
				else       	
					chain.doFilter(request, response);
			} catch (JSONException e) {e.printStackTrace();}
	        
	}

	private boolean isvalidToken(Object attribute) throws JSONException, IOException {
		URL oinspect = new URL("https://graph.facebook.com/debug_token?" + 
        		"input_token="+ attribute.toString() + 
        		"&access_token="+Accedi.getApptoken());
        JSONObject json = APIUser.useFBAPIs(oinspect);
        return json.getJSONObject("data").getBoolean("is_valid");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}