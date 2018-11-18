<!DOCTYPE html>
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

	<header class="header clearfix">
		<a href="<%=request.getContextPath()%>/index.jsp" class="header__logo">
			<img src="<%=request.getContextPath()%>/images/logo.png" style="width:40px; height:cover;"></img></a>
		<a href="" class="header__icon-bar">			
			<span></span>
			<span></span>
			<span></span>
		</a>
		<ul class="header__menu animate">
			<li class="header__menu__item"><a href="<%=request.getContextPath()%>/user.jsp">Area Personale</a></li>
			<li class="header__menu__item"><a href="single.html">Logout</a></li>
			<li class="header__menu__item"><a href="page.html">Page</a></li>
			<li class="header__menu__item"><a href="">Item</a></li>
			<li class="header__menu__item"><a href="">Item</a></li>
		</ul>
	</header>


	<section class="cover">
		<div class="cover__filter"></div>
		<div class="cover__caption">
			<div class="cover__caption__copy">
				<h1> Mr.ScoreBet</h1>
				<h2> Bet for free, win for real!</h2>
				<a href="https://www.facebook.com/v3.2/dialog/oauth?client_id=2095469647430370&redirect_uri=http://localhost:8080<%=request.getContextPath()%>/user.jsp&response_type=code&scope={pages_show_list}" class="button">Accedi con Facebook</a>
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
	<p>Copyright - 2016 MarchettiDesign.net</p>
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
