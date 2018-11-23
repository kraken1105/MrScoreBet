<!DOCTYPE html>

<%@ page import="model.User" %>
<% User utente = (User) session.getAttribute("utente");%>

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
			<li class="header__menu__item"><a href="boh.jsp">Logout</a></li>
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
				<img class="panel__card__image" src="https://source.unsplash.com/category/people/400x260" alt="Nature">
				<div class="panel__card__copy">
					<div class="panel__card__copy__text">
						<p>Nome: <b><%=""+utente.getNome().toString() %></b></p>
						<p>Cognome: <b><%=""+utente.getCognome() %></b></p>
						<p>Facebook UserID: <b><%=""+utente.getUserID() %></b></p>
					</div>
				</div>
			</div>	

			<h2 align="center">Schedine</h2>
			<p>Punti totali: <b><%=""+utente.getPuntiTot() %> pts</b></p>
			<!-- TO-DO: sistemare query string per indirizzamneto alla servlet -->
			<p>Ultima schedina giocata: <a href="<%=request.getContextPath()%>/app/bet">1 giornata </a></p>
			<p>Nuova schedina da giocare: <a href="<%=request.getContextPath()%>/app/bet">2 giornata </a><b>(2 h rimanenti)</b></p>
		</div>

		<!-- TO-DO: far comparire il pannello solo se è loggato un admin -->
		<div class="panel__copy2" <% if(!utente.getRuolo().equals("admin")) out.print("style=\"display: none\"");%> > 
			<h2 align="center">Area admin</h2>

			<p>Inserisci i risultati della <a href="<%=request.getContextPath()%>/app/bet">1 giornata</a></p>
			<p>Inserisci le partite della <a href="<%=request.getContextPath()%>/app/bet">2 giornata</a></p>
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
