<!DOCTYPE html>

<%@page import="model.*"%>
<%
	User utente = (User) session.getAttribute("utente");	
	Bet lastPlayedBet = utente.getLastPlayedBet();
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Last bet</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/4.2.0/normalize.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

	<header class="header clearfix">
		<ul class="header__left">
			<li class="header__left__item"><a href="<%=request.getContextPath()%>/index.jsp">.</a></li>
		</ul>
		<a href="" class="header__icon-bar">
			<span></span>
			<span></span>
			<span></span>
		</a>
		<ul class="header__menu animate">
			<li class="header__menu__item"><a href="<%=request.getContextPath()%>/app/user.jsp">Area Personale</a></li>
			<li class="header__menu__item"><a href="<%=request.getContextPath()%>/Logout">Logout</a></li>
		</ul>
	</header>

	<section class="cover cover--single">
		<div class="cover__filter"></div>
		<div class="cover__caption">
			<div class="cover__caption__copy">
				<h1>Ultima schedina giocata</h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Serie A giornata <%=lastPlayedBet.getNumGiornata()%></h2>

			<div class="mrw-table mrw-grid">
			    <div class="mrw-tr">
			        <div class="mrw-th mrw-width-50 mrw-center">Partita</div>
			        <div class="mrw-th mrw-width-25 mrw-center">Pronostico</div>
			        <div class="mrw-th mrw-width-25 mrw-center">Risultato</div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(0).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(0).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(0).getRisultato() == null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(0).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(1).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(1).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(1).getRisultato() == null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(1).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(2).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(2).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(2).getRisultato() == null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(2).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(3).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(3).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(3).getRisultato() == null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(3).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(4).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(4).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(4).getRisultato() == null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(4).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(5).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(5).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(5).getRisultato() == null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(5).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(6).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(6).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(6).getRisultato()==null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(6).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(7).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(7).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(7).getRisultato()==null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(7).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(8).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(8).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(8).getRisultato()==null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(8).getRisultato()); %></div>
			    </div>
			    <div class="mrw-tr">
			        <div class="mrw-td mrw-width-50 mrw-center"><%=lastPlayedBet.getGameList().get(9).getSquadre()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><%=lastPlayedBet.getGameList().get(9).getPronostico()%></div>
			        <div class="mrw-td mrw-width-25 mrw-center"><% if(lastPlayedBet.getGameList().get(9).getRisultato()==null) out.print(" "); 
			        												else out.print(lastPlayedBet.getGameList().get(9).getRisultato()); %></div>
			    </div>
			</div>			

			<h2 align="center">Punti totalizzati</h2>
			<p>Punti ottenuti: <b><% if(lastPlayedBet.getPunti()==null) out.print("?"); 
			        				  else out.print(lastPlayedBet.getPunti()); %> pts</b></p>
			<p>Punti totali: <b><%=utente.getPuntiTot()%> pts</b></p>
			<p></p>
			<p><b>Nota:</b> accumuli 10 punti per ogni esito correttamente pronosticato, e nel caso di schedina completamente esatta ulteriori 100.</p>

			<p>Ritorna alla tua <a href="<%=request.getContextPath()%>/app/user.jsp">Area Personale</a>.</p>
		</div>

	</article>



<footer class="footer">
	<p>Copyright - 2018 PicRof</p>
</footer>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
	 $(document).ready(function(){

			$(".header__icon-bar").click(function(e){

				$(".header__menu").toggleClass('is-open');
				e.preventDefault();

			});
	 });
</script>


</body>
</html>
