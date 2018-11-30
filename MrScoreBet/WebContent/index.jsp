<!DOCTYPE html>

<%
	String errore = (String) session.getAttribute("errore");
	if(errore!=null && !errore.equals("null")) {
		out.print("<script> alert(\""+errore+"\"); </script> ");
		session.setAttribute("errore","null");	// reset dell'errore
	}
	
	boolean logged_in = false;
	if(session.getAttribute("utente")!=null) logged_in=true;
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Mr.ScoreBet</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/4.2.0/normalize.css">
	<link rel="stylesheet" href="style.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

	<!-- TO-DO: far comparire il tasto logout solo se l'utente è loggato -->

	<!--border: 1px solid red-->
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
			<li class="header__menu__item" <% if(!logged_in) out.print("style=\"display: none\"");%>><a href="<%=request.getContextPath()%>/Logout">Logout</a></li>
		</ul>
	</header>


	<section class="cover">
		<div class="cover__filter"></div>
		<div class="cover__caption">
			<div class="cover__caption__copy">
				<h1> Mr.ScoreBet</h1>
				<h2> Bet for free, win for real!</h2>
				<a href="https://www.facebook.com/v3.2/dialog/oauth?client_id=2095469647430370&redirect_uri=https://localhost:8443<%=
				   request.getContextPath()%>/accedi&response_type=code&scope=pages_show_list" class="button">Accedi con Facebook</a>
			</div>
		</div>
	</section>


	<section class="cards clearfix">
		<div class="card">
			<img class="card__image" src="images/400x260_1.jpg">
			<div class="card__copy">
				<h3>Intuizione</h3>
				<p>Scendi in campo e piazza la tua scommessa in modo completamente gratuito. Basta avere un account Facebook.</p>
			</div>
		</div>
		<div class="card">
			<img class="card__image" src="images/400x260_3.jpg">
			<div class="card__copy">
				<h3>Emozione</h3>
				<p>Segui le partite ed esulta quando i tuoi pronostici si avverano. Accumulerai punti per ogni risultato esatto.</p>
			</div>
		</div>
		<div class="card">
			<img class="card__image" src="images/400x260_2.jpg">
			<div class="card__copy">
				<h3>Vittoria</h3>
				<p>Vinci come un vero campione e ritira la tua vincita in relazione ai punti accumulati con tutti i tuoi pronostici esatti.</p>
			</div>
		</div>
	</section>


	<section class="banner clearfix">
		<div class="banner__image"></div>
		<div class="banner__copy">
			<div class="banner__copy__text">
				<h3>Banner Title</h3>
				<h4>Banner Subtitle</h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quas autem quam, explicabo pariatur sit nisi, adipisci saepe.</p>
			</div>
		</div>
	</section>

	<section class="cards clearfix">
		<div class="card">
			<img class="card__image"src="https://source.unsplash.com/category/nature/400x260" alt="Nature">
			<div class="card__copy">
				<h3>Title Card</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Debitis sequi incidunt optio, asperiores dolorum ratione excepturi. </p>
			</div>
		</div>
		<div class="card">
			<img class="card__image"src="https://source.unsplash.com/category/food/400x260" alt="Nature">
			<div class="card__copy">
				<h3>Title Card</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Debitis sequi incidunt optio, asperiores dolorum ratione excepturi. </p>
			</div>
		</div>
		<div class="card">
			<img class="card__image"src="https://source.unsplash.com/category/people/400x260" alt="Nature">
			<div class="card__copy">
				<h3>Title Card</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Debitis sequi incidunt optio, asperiores dolorum ratione excepturi. </p>
			</div>
		</div>
	</section>

	<section class="banner clearfix">
		<div class="banner__copy">
			<div class="banner__copy__text">
				<h3>Banner Title</h3>
				<h4>Banner Subtitle</h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quas autem quam, explicabo pariatur sit nisi, adipisci saepe.</p>
			</div>
		</div>
		<div class="banner__image"></div>
	</section>


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
