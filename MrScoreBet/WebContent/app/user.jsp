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
	<title>Area Personale</title>
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
				<h1>Area Personale</h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Dati personali</h2>

			<div class="panel__card">
				<img class="panel__card__image" src="<%=utente.getImage().getUrl() %>" 
												width="<%=utente.getImage().getWidth()%>"
												height ="<%=utente.getImage().getHeight()%>">
				<div class="panel__card__copy">
					<div class="panel__card__copy__text">
						<p>Nome e Cognome: <br></br><b><%=""+utente.getNome_cognome() %></b></p>
						<p>Facebook UserID: <br></br><b><%=""+utente.getUserID() %></b></p>
					</div>
				</div>
			</div>

			<h2 align="center">Schedine</h2>
			<p>Punti totali: <b><%=""+utente.getPuntiTot() %> pts</b></p>
			<!-- TO-DO: sistemare query string per indirizzamneto alla servlet -->
			<p>Ultima schedina giocata: <a href="<%=request.getContextPath()%>/app/bets?to=myLastBet"><%=lastPlayedGiornata%> giornata </a></p>
			<p>Nuova schedina da giocare: <a href="<%=request.getContextPath()%>/app/bets?to=placeMyBet"><%=toPlayGiornata%> giornata </a><b id="mytimer"></b></p>
			
			<br><br>
			<p><b>ATTENZIONE:</b> premendo sul seguente bottone cancellerai tutti i tuoi dati dall'applicazione e rimuoverai l'accesso tramite il tuo account Facebook.</p>
			<p align="center"><a href="<%=request.getContextPath()%>/Logout?delete=true" class="button2">Chiudi il tuo account</a></p>
		
		</div>

		<!-- TO-DO: far comparire il pannello solo se Ã¨ loggato un admin -->
		<div class="panel__copy2" <% if(!utente.getRuolo().equals("admin")) out.print("style=\"display: none\"");%> > 
			<h2 align="center">Area admin</h2>

			<p>Inserisci i risultati della <a href="<%=request.getContextPath()%>/admin/manage?to=insertScore"><%=toInsertScoreGiornata%> giornata</a></p>
			<p>Inserisci una <a href="<%=request.getContextPath()%>/admin/manage?to=insertBet">nuova schedina</a></p>
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

<script>
	function countDown(second,endMinute,endHour,endDay,endMonth,endYear) {
		var now = new Date();
		second = (arguments.length == 1) ? second + now.getSeconds() : second;
		endYear =  typeof(endYear) != 'undefined' ?  endYear : now.getFullYear();            
		endMonth = endMonth ? endMonth - 1 : now.getMonth();  //numero del mese cominciando da 0 esempio 03- marzo 
		endDay = typeof(endDay) != 'undefined' ? endDay :  now.getDate();    
		endHour = typeof(endHour) != 'undefined' ?  endHour : now.getHours();
		endMinute = typeof(endMinute) != 'undefined' ? endMinute : now.getMinutes();  
		//agiungiamo un secondo alla data finale (il nostro taimer mostrera tempo gia dopo 1 secondo.)
		var endDate = new Date(endYear,endMonth,endDay,endHour,endMinute,second+1);
		var interval = setInterval(function() { //faciamo partire taimer con intervallo di 1 secondo  
		    var time = endDate.getTime() - now.getTime();
		    if (time < 0) {                      //se la data finale impostata è meno di 1 secondo
		    	document.getElementById('mytimer').innerHTML = '(giornata iniziata)';           
		    } else {           
		        var days = Math.floor(time / 864e5);
		        var hours = Math.floor(time / 36e5) % 24;
		        var minutes = Math.floor(time / 6e4) % 60;
		        var seconds = Math.floor(time / 1e3) % 60; 
		        var digit='<div style="width:70px;float:left;text-align:center">'+
		        '<div style="font-family:Stencil;font-size:55px;">';// potete cambiare alteza e famiglia di font
		        var text='</div><div>'
		        var end='</div></div><div style="float:left;font-size:45px;">:</div>'
		        
		        if (!seconds && !minutes && !days && !hours) {             
		        	document.getElementById('mytimer').innerHTML = '(giornata iniziata)';             
		        } else {
		        	document.getElementById('mytimer').innerHTML = '(' + days +'g '+ hours + 'h ' + minutes + 'm ' + seconds +'s rimanenti)';
		        }         
		    }
		    now.setSeconds(now.getSeconds() + 1); //aumentiamo il tempo corrente per 1 secondo
		}, 1000);
	}
	
	<%if(data != null) out.print("countDown("+data.getSecond()+","+data.getMinute()+","+data.getHour()+","+
		data.getDayOfMonth()+","+data.getMonthValue()+","+data.getYear()+");");
	  else
		  out.print("document.getElementById('mytimer').innerHTML = '(non disponibile)';");%>
</script>



</body>
</html>