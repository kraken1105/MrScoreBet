<!DOCTYPE html>

<%@page import="dao.*"%>
<%@page import="model.*"%>
<%@page import="java.time.LocalDateTime"%>
<%
	User utente = (User) session.getAttribute("utente");	
	Image img = utente.getImage();
	utente = UserDAO.read(utente.getUserID()); // aggiorna i dati dell'utente in sessione
	utente.setImage(img);
	session.setAttribute("utente", utente);
	
	Bet lastPlayedBet = utente.getLastPlayedBet();
	String lastPlayedGiornata = new String("-");
	Bet toPlayBet = utente.getToPlayBet();
	String toPlayGiornata = new String("-");
	
	LocalDateTime data = null;
	
	if (lastPlayedBet != null) {
		lastPlayedGiornata = String.valueOf(lastPlayedBet.getNumGiornata());		
	}
	
	if (toPlayBet != null) {
		data = toPlayBet.getOrarioScadenza();
		toPlayGiornata = String.valueOf(toPlayBet.getNumGiornata());		
	} else {
		data = null;
	}
	
	String errore = (String) session.getAttribute("errore");
	if(!errore.equals("null")) {
		out.print("<script> alert(\""+errore+"\"); </script> ");
		session.setAttribute("errore","null");	// reset dell'errore
	}
	
	// Per admin
	String toInsertScoreGiornata = new String("-");	
	Bet toInsertScoreBet = SchedinaDAO.getToPlayBet();	
	if (toInsertScoreBet != null)
		toInsertScoreGiornata = String.valueOf(toInsertScoreBet.getNumGiornata());	
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Errore</title>
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
				<h1></h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Errore</h2>
			<p>La risorsa che hai richiesto è accessibile solo dagli <b>admin</b>.</p>
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