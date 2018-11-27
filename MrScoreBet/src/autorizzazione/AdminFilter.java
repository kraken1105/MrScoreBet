package autorizzazione;

import java.io.File;
import java.io.IOException;
import java.util.*;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;


import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.CurrentEnvModule;
import com.sun.xacml.finder.impl.FilePolicyModule;


/**
 * Servlet Filter implementation class AuthFilter
 */
public class AdminFilter implements Filter {
	File[] listaFile;//contiene le policy disponibili

    /**
     * Default constructor. 
     */
    public AdminFilter() {
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
		File f;
        String policyfile;
        FilePolicyModule policyModule = new FilePolicyModule();
        PolicyFinder policyFinder = new PolicyFinder();
        Set policyModules = new HashSet();
      
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
       
        HttpSession session = req.getSession();
        if(session.getAttribute("utente")!=null){
	        String PATH_POLICY = "c:\\policy\\";//path della cartella contenente le policy
	        cercaPolicyFile(new File(PATH_POLICY));
	        
	        for(int i=0;i<listaFile.length;i++)
	        {
	             //File f = new File(PATH_POLICY);
	             f=listaFile[i];
	             policyfile = f.getAbsolutePath();
	             policyModule.addPolicy(policyfile); //aggiunge solo il nome del file
	             policyModules.add(policyModule);
	             policyFinder.setModules(policyModules);
	        }
	
	        CurrentEnvModule envModule = new CurrentEnvModule();
	        AttributeFinder attrFinder = new AttributeFinder();
	        List attrModules = new ArrayList();
	        attrModules.add(envModule);
	        attrFinder.setModules(attrModules);
	        
	        
	        try {
	        RequestCtx XACMLrequest = RequestBuilder.createXACMLRequest(req);
	  
	        PDP pdp = new PDP(new PDPConfig(attrFinder, policyFinder, null));
	
	        ResponseCtx XACMLresponse = pdp.evaluate(XACMLrequest);
	        
	        Set ris_set = XACMLresponse.getResults();
	        Result ris = null;
	        Iterator it = ris_set.iterator();
	
	        while (it.hasNext()) {
	            ris = (Result) it.next();
	        }
	        int dec = ris.getDecision();
	
	        if (dec == 0) {//permit
	            chain.doFilter(request, response);
	        } else if (dec == 1) {//deny
	            res.sendRedirect(req.getContextPath()+"/app/error.jsp");
	        } else if (dec == 2||dec==3) {//not applicable o indeterminate
	            res.sendRedirect(req.getContextPath()+"/index.jsp"); 
	        }            }
	     catch (Exception ex) {
	        ex.printStackTrace();
	    }
        }
        else
        	res.sendRedirect(req.getContextPath()+"/index.jsp");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	private void cercaPolicyFile (File dir) {
	       
	      listaFile = dir.listFiles();
	      
	  }

}