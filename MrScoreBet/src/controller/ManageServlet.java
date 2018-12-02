package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.*;
import model.*;

/**
 * Servlet implementation class ManageServlet
 */
@WebServlet("/admin/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManageServlet() {
        super();
    }

	
    //** Analizza la request per capire dove reindirizzare l'utente **//
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String toPage = request.getParameter("to");
		
		Bet toUpdateScore = null;
		try {
			toUpdateScore = SchedinaDAO.getToPlayBet();
		} catch (SQLException e1) {e1.printStackTrace();}
		
		if(toPage.equals("insertScore")) {			
			if(toUpdateScore == null) {
				session.setAttribute("errore", "Non esiste alcuna schedina con gli esiti dei match non inseriti!");
				response.sendRedirect(request.getContextPath()+"/app/user.jsp");
			} else {
				session.setAttribute("toUpdateScore", toUpdateScore);
				response.sendRedirect("/MrScoreBet/admin/insertScore.jsp");					
			}
		
		} else if(toPage.equals("insertBet")) {			
			if(toUpdateScore == null) {
				response.sendRedirect("/MrScoreBet/admin/insertBet.jsp");
			} else {				
				session.setAttribute("errore", "Aggiornare gli esiti dell'ultima schedina inserita prima di inserirne una nuova!");
				response.sendRedirect(request.getContextPath()+"/app/user.jsp");
			}
			
		} else {
			session.setAttribute("errore", "Si è verificato un errore!");
			response.sendRedirect(request.getContextPath()+"/app/user.jsp");
		}
	}

	
    
    //** Consente di inserire nel sistema sia una nuova schedina che i risultati di una già inserita (in base a un parametro) **//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(request.getParameter("post_type").equals("insertScore")) {			
			Bet b = (Bet) session.getAttribute("toUpdateScore");
			session.removeAttribute("toUpdateScore");
			
			for(int i=1; i<11; i++)
				b.getGameList().get(i-1).setRisultato(request.getParameter("match"+i));
			
			// Propago le modifiche nel database
			try {
				SchedinaDAO.update(b);				
			} catch (SQLException e) {e.printStackTrace();}			
						
			response.sendRedirect(request.getContextPath()+"/app/user.jsp");			
			
		} else if(request.getParameter("post_type").equals("insertBet")) {
			
			LocalDateTime orarioScadenza = LocalDateTime.parse(request.getParameter("dataScadenza")
					+"T"+request.getParameter("oraScadenza"), DateTimeFormatter.ISO_DATE_TIME);
			
			ArrayList<Game> gameList = new ArrayList<Game>();
			for(int i=1; i<11; i++)
				gameList.add(new Game(request.getParameter("match"+i), null, null));
			
			Bet newbet = new Bet(null, Integer.parseInt(request.getParameter("numGiornata")), orarioScadenza, gameList, null);
			
			// Propago le modifiche nel database
			try {
				newbet.setID( SchedinaDAO.create(newbet) );
				UserDAO.setToPlayBet(newbet);
			} catch (SQLException e) {e.printStackTrace();}
			
			response.sendRedirect(request.getContextPath()+"/app/user.jsp");		
		
		} else {
			if(session.getAttribute("toUpdateScore") != null) session.removeAttribute("toUpdateScore");
			session.setAttribute("errore", "Si è verificato un errore!");
			response.sendRedirect(request.getContextPath()+"/app/user.jsp");
		}
		
	}

}









