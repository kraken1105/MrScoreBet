package autorizzazione;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

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
import model.User;


/**
 * Servlet Filter implementation class AuthFilter
 */
public class VerifyBetExistence implements Filter {
	

    /**
     * Default constructor. 
     */
    public VerifyBetExistence() {
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
		Enumeration<String> attributes = req.getParameterNames();
		boolean fieldToPresent = checkField(attributes);
		if(!fieldToPresent) {
			session.setAttribute("errore", "E' necessario specificare l'azione all'interno della pagine user.jsp");
			res.sendRedirect(req.getContextPath()+"/app/user.jsp");
		} else chain.doFilter(request, response);
	}

	private boolean checkField(Enumeration<String> attributes) {
		while(attributes.hasMoreElements()) {
			if (attributes.nextElement().equals("to")) return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}