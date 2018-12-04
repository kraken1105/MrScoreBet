package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.*;
import model.*;

/**
 * Servlet implementation class BetServlet
 */
@WebServlet("/app/bets")
public class BetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BetServlet() {
        super();
    }

    //** Analizza la request per capire dove reindirizzare l'utente **//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User utente = (User) session.getAttribute("utente");
		
		String toPage = request.getParameter("to");
		
		if(toPage.equals("myLastBet")) {
				if(utente.getLastPlayedBet()==null) {
					session.setAttribute("errore", "Non hai ancora giocato alcuna schedina!");
					response.sendRedirect(request.getContextPath()+"/app/user.jsp");
				}
				else response.sendRedirect(request.getContextPath()+"/app/bets/myLastBet.jsp");
				
			} else if(toPage.equals("placeMyBet")) {
						if(utente.getToPlayBet()==null) {
							session.setAttribute("errore", "Non è presente alcuna schedina da giocare!");
							response.sendRedirect(request.getContextPath()+"/app/user.jsp");
				} 		else if (utente.getToPlayBet().getOrarioScadenza().isBefore(LocalDateTime.now())) {
							session.setAttribute("errore", "Troppo tardi! Giornata in corso.");
							response.sendRedirect(request.getContextPath()+"/app/user.jsp");
						}
						
						else {session.setAttribute("to", "OK"); response.sendRedirect(request.getContextPath()+"/app/bets/placeMyBet.jsp");}
				
			} else {
				session.setAttribute("errore", "Si è verificato un errore!");
				response.sendRedirect(request.getContextPath()+"/app/user.jsp");
			}	
	}

	
	//** Consente di inserire nel sistema il pronostico dell'utente **//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User utente = (User) session.getAttribute("utente");
		Bet pron = utente.getToPlayBet();
		
		for(int i=1; i<11; i++) 
			pron.getGameList().get(i-1).setPronostico(request.getParameter("match"+i));

		// Propago le modifiche nel database
		try {
			pron.setID( PronosticoDAO.create(pron, utente.getToPlayBet()) );
			utente.setLastPlayedBet(pron);
			utente.setToPlayBet(null);
			UserDAO.update(utente);		
		} catch (SQLException e) {e.printStackTrace();}
		
		response.sendRedirect(request.getContextPath()+"/app/user.jsp");
	}

}
