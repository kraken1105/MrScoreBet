package autorizzazione;

import com.sun.xacml.EvaluationCtx;

import com.sun.xacml.attr.StringAttribute;

import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

import model.User;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashSet;
import java.util.Set;
/*
     * Sets up the Subject section of the request. This Request only has
     * one Subject section, and it uses the default category. To create a
     * Subject with a different category, you simply specify the category
     * when you construct the Subject object.
     *
     * @return a Set of Subject instances for inclusion in a Request
     *
     * @throws URISyntaxException if there is a problem with a URI
 * 
     */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RequestBuilder
{

  static final String ACTION_IDENTIFIER = "urn:oasis:names:tc:xacml:1.0:action:action-id";
  //static final String RESOURCE_IDENTIFIER = "urn:oasis:names:tc:xacml:1.0:resource:resource-id";
  static final String SUBJECT_IDENTIFIER = "urn:oasis:names:tc:xacml:1.0:subject:subject-id";
  //static final String SUBJECT_ROLE_IDENTIFIER = "urn:oasis:names:tc:xacml:2.0:subject:role";  
  static final String SUBJECT_ROLE_IDENTIFIER = "role";           
            
   
    
    public static Set setupSubjects(HttpServletRequest request ) throws URISyntaxException {
        HashSet attributes = new HashSet();
        HashSet subjects = new HashSet();
          
        HttpSession session=request.getSession();
        User utente = (User) session.getAttribute("utente");
        String ruolo = utente.getRuolo();
        
        if(ruolo==null){
            subjects=null;
        }
        else{   
        attributes.add(new Attribute(new URI(SUBJECT_ROLE_IDENTIFIER),null, null,new StringAttribute(ruolo)));
        subjects.add(new Subject(attributes));
        }

        return subjects;
    }

    
    /**
     * Creates a Resource specifying the resource-id, a required attribute.
     *
     * @return a Set of Attributes for inclusion in a Request
     *
     * @throws URISyntaxException if there is a problem with a URI
     */
    public static Set setupResource(HttpServletRequest request) throws URISyntaxException {
        HashSet resource = new HashSet();
        
        // the resource being requested : � una URI ma passata come stringa
        StringAttribute value =
            new StringAttribute("https://localhost:8443"+request.getRequestURI());
       
        // create the resource using a standard, required identifier for
        // the resource being requested
        resource.add(new Attribute(new URI(EvaluationCtx.RESOURCE_ID),null, null, value));
        
        return resource;
    }

    /**
     * Creates an Action specifying the action-id, an optional attribute.
     *
     * @return a Set of Attributes for inclusion in a Request
     *
     * @throws URISyntaxException if there is a problem with a URI
     */
    public static Set setupAction() throws URISyntaxException {
        HashSet action = new HashSet();

        // this is a standard URI that can optionally be used to specify
        // the action being requested
        URI actionId =
            new URI(ACTION_IDENTIFIER);

        // create the action
        action.add(new Attribute(actionId, null, null, new StringAttribute("view")));

        return action;
    }

    
    public static RequestCtx createXACMLRequest(HttpServletRequest request) throws Exception{
        
      RequestCtx XACMLrequest = new RequestCtx(setupSubjects(request), setupResource(request),setupAction(), new HashSet());
      
      return XACMLrequest;
    }


}