<!DOCTYPE html>

<%@page import="model.*"%>
<%
	User utente = (User) session.getAttribute("utente");	
	Bet toPlayBet = utente.getToPlayBet();
%>

<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Nuova schedina</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/4.2.0/normalize.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">

  	<script>
	function Modulo() {
		if (!(document.getElementById("match1_1").checked) && !(document.getElementById("match1_X").checked) && !(document.getElementById("match1_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(0).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match2_1").checked) && !(document.getElementById("match2_X").checked) && !(document.getElementById("match2_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(1).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match3_1").checked) && !(document.getElementById("match3_X").checked) && !(document.getElementById("match3_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(2).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match4_1").checked) && !(document.getElementById("match4_X").checked) && !(document.getElementById("match4_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(3).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match5_1").checked) && !(document.getElementById("match5_X").checked) && !(document.getElementById("match5_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(4).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match6_1").checked) && !(document.getElementById("match6_X").checked) && !(document.getElementById("match6_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(5).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match7_1").checked) && !(document.getElementById("match7_X").checked) && !(document.getElementById("match7_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(6).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match8_1").checked) && !(document.getElementById("match8_X").checked) && !(document.getElementById("match8_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(7).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match9_1").checked) && !(document.getElementById("match9_X").checked) && !(document.getElementById("match9_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(8).getSquadre()%>!"); return false; }
		else if (!(document.getElementById("match10_1").checked) && !(document.getElementById("match10_X").checked) && !(document.getElementById("match10_2").checked)) {
			alert("Inserisci un pronostico per <%=toPlayBet.getGameList().get(9).getSquadre()%>!"); return false; }
		//INVIA IL MODULO
		else {
			document.getElementById("form1").action = "<%=request.getContextPath()%>/app/bets";
			document.getElementById("form1").submit();
		}
	}
	</script>

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
				<h1>Nuova schedina</h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Serie A giornata <%=toPlayBet.getNumGiornata()%></h2>

			<form class="boxed" id="form1" method="post">
				
				<div class="mrw-table mrw-grid">
				    <div class="mrw-tr">
				        <div class="mrw-th mrw-width-50 mrw-center">Partita</div>
				        <div class="mrw-th mrw-width-50 mrw-center">Pronostico</div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(0).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match1_1" name="match1" value="1"><label for="match1_1">1</label>
							<input type="radio" id="match1_X" name="match1" value="X"><label for="match1_X">X</label>
							<input type="radio" id="match1_2" name="match1" value="2"><label for="match1_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(1).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match2_1" name="match2" value="1"><label for="match2_1">1</label>
							<input type="radio" id="match2_X" name="match2" value="X"><label for="match2_X">X</label>
							<input type="radio" id="match2_2" name="match2" value="2"><label for="match2_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(2).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match3_1" name="match3" value="1"><label for="match3_1">1</label>
							<input type="radio" id="match3_X" name="match3" value="X"><label for="match3_X">X</label>
							<input type="radio" id="match3_2" name="match3" value="2"><label for="match3_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(3).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match4_1" name="match4" value="1"><label for="match4_1">1</label>
							<input type="radio" id="match4_X" name="match4" value="X"><label for="match4_X">X</label>
							<input type="radio" id="match4_2" name="match4" value="2"><label for="match4_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(4).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match5_1" name="match5" value="1"><label for="match5_1">1</label>
							<input type="radio" id="match5_X" name="match5" value="X"><label for="match5_X">X</label>
							<input type="radio" id="match5_2" name="match5" value="2"><label for="match5_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(5).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match6_1" name="match6" value="1"><label for="match6_1">1</label>
							<input type="radio" id="match6_X" name="match6" value="X"><label for="match6_X">X</label>
							<input type="radio" id="match6_2" name="match6" value="2"><label for="match6_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(6).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match7_1" name="match7" value="1"><label for="match7_1">1</label>
							<input type="radio" id="match7_X" name="match7" value="X"><label for="match7_X">X</label>
							<input type="radio" id="match7_2" name="match7" value="2"><label for="match7_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(7).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match8_1" name="match8" value="1"><label for="match8_1">1</label>
							<input type="radio" id="match8_X" name="match8" value="X"><label for="match8_X">X</label>
							<input type="radio" id="match8_2" name="match8" value="2"><label for="match8_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(8).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match9_1" name="match9" value="1"><label for="match9_1">1</label>
							<input type="radio" id="match9_X" name="match9" value="X"><label for="match9_X">X</label>
							<input type="radio" id="match9_2" name="match9" value="2"><label for="match9_2">2</label>
				        </div>
				    </div>
				    <div class="mrw-tr">
				        <div class="mrw-td mrw-width-50 mrw-center"><%=toPlayBet.getGameList().get(9).getSquadre()%></div>
				        <div class="mrw-td mrw-width-50 mrw-center">
				        	<input type="radio" id="match10_1" name="match10" value="1"><label for="match10_1">1</label>
							<input type="radio" id="match10_X" name="match10" value="X"><label for="match10_X">X</label>
							<input type="radio" id="match10_2" name="match10" value="2"><label for="match10_2">2</label>
				        </div>
				    </div>
				</div>
				
				<br><br>
				<input type="button" value="Invia" onClick="Modulo()">
  				<input type="reset" value="Resetta i campi">

			</form>

			<p>Ritorna alla tua <a href="<%=request.getContextPath()%>/app/user.jsp">Area Personale</a> senza inviare la schedina.</p>
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
