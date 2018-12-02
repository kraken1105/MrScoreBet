<!DOCTYPE html>

<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Inserisci nuova schedina</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/4.2.0/normalize.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">

  	<script>
  	function Modulo() {
  		if (document.getElementById("numGiornata").value=="") {alert("Inserisci il numero della giornata!"); return false;}
  		else if (document.getElementById("dataScadenza").value=="") {alert("Inserisci la data di scadenza!"); return false;}
  		else if (document.getElementById("oraScadenza").value=="") {alert("Inserisci l'orario di scadenza!"); return false;}
  		else if (document.getElementById("match1").value=="") {alert("Inserisci il primo match!"); return false;}
		else if (document.getElementById("match2").value=="") {alert("Inserisci il secondo match!"); return false;}
		else if (document.getElementById("match3").value=="") {alert("Inserisci il terzo match!"); return false;}
		else if (document.getElementById("match4").value=="") {alert("Inserisci il quarto match!"); return false;}
		else if (document.getElementById("match5").value=="") {alert("Inserisci il quinto match!"); return false;}
		else if (document.getElementById("match6").value=="") {alert("Inserisci il sesto match!"); return false;}
		else if (document.getElementById("match7").value=="") {alert("Inserisci il settimo match!"); return false;}
		else if (document.getElementById("match8").value=="") {alert("Inserisci il ottavo match!"); return false;}
		else if (document.getElementById("match9").value=="") {alert("Inserisci il nono match!"); return false;}
		else if (document.getElementById("match10").value=="") {alert("Inserisci il decimo match!"); return false;}
		//INVIA IL MODULO
		else {
			document.getElementById("formBet").action = "<%=request.getContextPath()%>/admin/manage";
			document.getElementById("formBet").submit();		
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
				<h1>Inserisci nuova schedina</h1>
				<h2></h2>
			</div>
		</div>
	</section>

	<article class="panel">
		<div class="panel__copy">
			<h2 align="center">Serie A</h2>

			<form id="formBet" method="post">
				
				<input type="text" name="post_type" value="insertBet" style="display:none"/>
				
				<p><b>Numero giornata:</b> <input type="text" id="numGiornata" name="numGiornata" value="" /></p>
				<p><b>Scadenza</b> (inizio della prima partita di giornata):<br></p>
				<p>Data: <input type="date" id="dataScadenza" placeholder="aaaa-mm-gg" name="dataScadenza" value="" /><br></p>
				<p>Ora: <input type="time" id="oraScadenza" placeholder="--:--" name="oraScadenza" value="" /></p>
				
				<br><br>
				<div class="mrw-table mrw-grid">
				    <div class="mrw-tr">
				    	<div class="mrw-th mrw-width-20 mrw-center"></div>
				        <div class="mrw-th mrw-width-80 mrw-center">Squadre</div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">1</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match1" name="match1" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">2</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match2" name="match2" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">3</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match3" name="match3" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">4</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match4" name="match4" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">5</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match5" name="match5" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">6</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match6" name="match6" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">7</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match7" name="match7" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">8</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match8" name="match8" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">9</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match9" name="match9" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				    <div class="mrw-tr">
				    	<div class="mrw-td mrw-width-20 mrw-center">10</div>
				        <div class="mrw-td mrw-width-80 mrw-center">
				        	<input type="text" id="match10" name="match10" placeholder="Squadra1 - Squadra2">
				        </div>
				    </div>
				</div>
				
				<br><br>
				<input type="button" value="Invia" onClick="Modulo()">
  				<input type="reset" value="Resetta i campi">

			</form>

			<p>Ritorna alla tua <a href="<%=request.getContextPath()%>/app/user.jsp">Area Personale</a> senza inserire una nuova schedina.</p>
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
